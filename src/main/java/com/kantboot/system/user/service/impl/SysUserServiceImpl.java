package com.kantboot.system.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.kantboot.system.setting.service.ISysSettingService;
import com.kantboot.system.user.dao.repository.SysPermissionRoleRepository;
import com.kantboot.system.user.dao.repository.SysUserOnlineRepository;
import com.kantboot.system.user.dao.repository.SysUserRepository;
import com.kantboot.system.user.dao.repository.SysUserRoleRepository;
import com.kantboot.system.user.domain.dto.SecurityLoginDTO;
import com.kantboot.system.user.domain.entity.*;
import com.kantboot.system.user.domain.vo.LoginVO;
import com.kantboot.system.user.service.ISysRoleService;
import com.kantboot.system.user.service.ISysTokenService;
import com.kantboot.system.user.service.ISysUserService;
import com.kantboot.util.common.exception.BaseException;
import com.kantboot.util.common.http.HttpRequestHeaderUtil;
import com.kantboot.util.common.password.KantbootPassword;
import com.kantboot.util.core.redis.RedisUtil;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 用户服务实现类
 *
 * @author 方某方
 */
@Slf4j
@Service
public class SysUserServiceImpl implements ISysUserService {

    /**
     * redis前缀
     */
    private static final String REDIS_KEY_PREFIX = "sysUser";

    @Resource
    private SysUserRepository repository;

    @Resource
    private SysUserRoleRepository userRoleRepository;

    @Resource
    private ISysSettingService settingService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private ISysRoleService roleService;

    @Resource
    private ISysTokenService tokenService;

    @Resource
    private SysUserOnlineRepository onlineRepository;

    @Resource
    private HttpRequestHeaderUtil httpRequestHeaderUtil;

    @Resource
    private SysPermissionRoleRepository permissionRoleRepository;

    @Override
    @Cacheable(value = REDIS_KEY_PREFIX + "::defaultRoleCode")
    public String getDefaultRoleCode() {
        return settingService.getValue("user", "defaultRoleCode");
    }

    public Map<String, Object> entityToMap(SysUser entity) {

        if(entity.getOnline()==null){
            entity.setOnline(
                    new SysUserOnline()
                            .setOnline(false)
                            .setUserId(entity.getId())
                            .setOnlineStatusCode(SysUserOnline.ONLINE_STATUS_CODE_OFFLINE)
            );
        }

        entity.setPassword(null);
        Map<String, Object> result = BeanUtil.beanToMap(entity);

        Map<String, Object> attributeMap = new HashMap<>();
        List<SysUserAttribute> attributes = entity.getAttributeList();
        if (attributes == null) {
            attributes = new ArrayList<>();
        }

        for (SysUserAttribute attribute : attributes) {
            List<String> detailValues = attribute.getDetailList().stream()
                    .map(SysUserAttributeDetail::getValue)
                    .collect(Collectors.toList());
            attributeMap.put(attribute.getCode(), detailValues.isEmpty() ? attribute.getValue() : detailValues);
        }
        result.put("attr", attributeMap);

        // 移除属性列表
        entity.setAttributeList(null);

        List<String> roleCodes = new ArrayList<>();
        List<SysUserRole> roleList = entity.getRoleList();
        if (roleList == null) {
            roleList = new ArrayList<>();
        }


        roleList.forEach(role -> {
            roleCodes.add(role.getRoleCode());
            // 设置角色状态
            result.put("isRole" + StrUtil.upperFirst(role.getRoleCode()), true);
        });
        result.put("roleCodes", roleCodes);

        try{
            result.put("gmtCreate", entity.getGmtCreate().getTime());
            result.put("gmtModified", entity.getGmtModified().getTime());
        }catch (Exception e){
            result.put("gmtCreate", null);
            result.put("gmtModified", null);
        }


        return result;
    }

    private Map<String, Object> hideMap(SysUser vo) {
        Map<String, Object> map = entityToMap(vo);

        map.remove("username");
        List<SysUserRole> roleList = vo.getRoleList();
        List<SysUserRole> newRoleList = new ArrayList<>();
        for (SysUserRole role : roleList) {
            if(!role.getVisible()){
                newRoleList.add(role);
            }
        }
        map.remove("roleList");
        map.put("roleCodes", newRoleList.stream().map(SysUserRole::getRoleCode).collect(Collectors.toList()));

        return map;
    }


    @Override
    public Map<String, Object> getById(Long id) {
        SysUser sysUser = repository.findById(id).orElse(new SysUser());
        return entityToMap(sysUser);
    }

    @Override
    public Map<String, Object> getSelf() {
        String selfToken = tokenService.getSelfToken();
        Long userIdByToken = tokenService.getUserIdByToken(selfToken);
        Map<String, Object> result = getById(userIdByToken);
        List<String> roleCodes = (List<String>) result.get("roleCodes");
        List<SysPermissionRole> byRoleCodeIn = permissionRoleRepository.getByRoleCodeIn(roleCodes);
        List<String> permissionCodes = new ArrayList<>();
        for (SysPermissionRole sysPermissionRole : byRoleCodeIn) {
            permissionCodes.add(sysPermissionRole.getPermissionCode());
        }
        result.put("permissionCodes", permissionCodes);
        return result;
    }

    @Override
    public Long getSelfId() {
        return tokenService.getUserIdByToken(tokenService.getSelfToken());
    }

    @Override
    public LoginVO login(String username, String password) {
        // 去除空格
        username = username.trim();
        password = password.trim();

        // 判断用户名是否存在
        SysUser byUsername = repository.findByUsername(username);
        if (byUsername == null) {
            // 告知用户名不存在
            throw BaseException.of("usernameNotExist", "用户名不存在");
        }

        // 判断密码是否正确
        if (!new KantbootPassword().matches(password, byUsername.getPassword())) {
            // 告知密码错误
            throw BaseException.of("passwordError", "密码错误");
        }


        return new LoginVO()
                .setToken(tokenService.generateToken(byUsername.getId()))
                .setUserInfo(entityToMap(byUsername));
    }


    @Override
    public LoginVO register(String username, String password) {
        // 计时1
        long startTime1 = System.currentTimeMillis();

        // 去除空格
        username = username.trim();
        password = password.trim();

        // 判断用户名是否存在
        SysUser byUsername = repository.findByUsername(username);
        if (byUsername != null) {
            // 告知用户名已存在
            throw BaseException.of("usernameRepeat", "用户名已存在");
        }

        // 创建用户
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);

        // 密码加密开始时间
        long passwordEncodeStartTime = System.currentTimeMillis();
        sysUser.setPassword(new KantbootPassword().encode(password));
        // 密码加密结束时间
        long passwordEncodeEndTime = System.currentTimeMillis();
        log.info("密码加密耗时：{}ms", passwordEncodeEndTime - passwordEncodeStartTime);
        // 保存用户
        SysUser result = repository.save(sysUser);
        // 计时1结束
        long endTime1 = System.currentTimeMillis();
        log.info("保存用户耗时：{}ms", endTime1 - startTime1);

        // 计时2
        long startTime2 = System.currentTimeMillis();
        // 保存用户角色
        String defaultRoleCode = getDefaultRoleCode();
        log.info("默认角色:{}", defaultRoleCode);
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleCode(defaultRoleCode);
        userRoleRepository.save(sysUserRole);


        // 重新复制一份，防止修改
        long startTime4 = System.currentTimeMillis();
        SysUser sysUserVO = resultSetRoleCode(result, defaultRoleCode);
        // 计时4结束
        long endTime4 = System.currentTimeMillis();
        log.info("重新复制一份耗时：{}ms", endTime4 - startTime4);

        return new LoginVO()
                .setToken(tokenService.generateToken(result.getId()))
                .setUserInfo(entityToMap(sysUserVO));
    }


    private SysUser resultSetRoleCode(SysUser vo, String defaultRoleCode) {
        SysUser result = BeanUtil.copyProperties(vo, SysUser.class);
        List<SysUserRole> objects = new ArrayList<>();
        SysUserRole defaultRole = new SysUserRole().setRoleCode(defaultRoleCode).setVisible(
                roleService.getByCode(defaultRoleCode).getVisible()
        );
        userRoleRepository.save(defaultRole.setUserId(vo.getId()));
        objects.add(defaultRole);
        result.setRoleList(objects);
        return result;
    }



    @SneakyThrows
    @Override
    public String getRsaPublicKey() {
        // 获取ip，用于限制请求次数
        String ip = httpRequestHeaderUtil.getIp();
        String s = redisUtil.get("rsa::requestCount:" + ip);
        if (StrUtil.isBlank(s)) {
            // 第一次请求，设置请求次数为0
            redisUtil.setEx("rsa::requestCount:" + ip, "0", 2, TimeUnit.MINUTES);
            s = "0";
        }
        // 获取请求次数
        Integer requestCount = Integer.parseInt(redisUtil.get("rsa::requestCount:" + ip));
        if (requestCount > 50) {
            Thread.sleep(1000);
        }
        if (requestCount > 100) {
            Thread.sleep(2000);
        }
        if (requestCount > 200) {
            Thread.sleep(2000);
        }
        if (requestCount > 300) {
            Thread.sleep(2000);
        }
        if (requestCount > 400) {
            String redisLockKey = "res:lock:"+httpRequestHeaderUtil.getUserAgent()+":"+ httpRequestHeaderUtil.getIp();
            // 获取锁
            if(redisUtil.lock(redisLockKey, 10, TimeUnit.SECONDS)){
                // 被占线，请稍后再试
                throw BaseException.of("requestTooMany", "请求过于频繁，请稍后再试");
            }
            Thread.sleep(2000);
        }

        // 请求次数加一
        redisUtil.setEx("rsa::requestCount:" + ip, String.valueOf(Integer.parseInt(s) + 1),redisUtil.getExpire("rsa::requestCount:" + ip),
                TimeUnit.SECONDS);



        RSA rsa = new RSA();
        String publicKeyBase64 = rsa.getPublicKeyBase64();
        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        // 保存到 redis，30分钟过期
        redisUtil.setEx("rsa::publicKey:" + publicKeyBase64, privateKeyBase64, 30, TimeUnit.MINUTES);
        return publicKeyBase64;
    }

    @Override
    public String getRsaPrivateKey(String publicKey) {
        String result = redisUtil.get("rsa::publicKey:" + publicKey);
        if (StrUtil.isBlank(result)) {
            // 未找到私钥
            throw BaseException.of("privateKeyNotFound", "未找到私钥");
        }
        return result;
    }

    @Override
    public LoginVO securityLogin(SecurityLoginDTO loginDTO) {
        // 去除空格
        loginDTO.setUsername(loginDTO.getUsername().trim());
        loginDTO.setPassword(loginDTO.getPassword().trim());
        // 解密，获得真实的用户名和密码
        RSA rsa = new RSA(getRsaPrivateKey(loginDTO.getUsernamePublicKey()),loginDTO.getUsernamePublicKey());
        String username = rsa.decryptStr(loginDTO.getUsername(), KeyType.PrivateKey);
        RSA rsa2 = new RSA(getRsaPrivateKey(loginDTO.getPasswordPublicKey()),loginDTO.getPasswordPublicKey());
        String password = rsa2.decryptStr(loginDTO.getPassword(), KeyType.PrivateKey);
        return login(username, password);
    }

    @Override
    public LoginVO securityRegister(SecurityLoginDTO loginDTO) {
        // 去除空格
        loginDTO.setUsername(loginDTO.getUsername().trim());
        loginDTO.setPassword(loginDTO.getPassword().trim());
        // 解密，获得真实的用户名和密码
        RSA rsa = new RSA(getRsaPrivateKey(loginDTO.getUsernamePublicKey()),loginDTO.getUsernamePublicKey());
        String username = rsa.decryptStr(loginDTO.getUsername(), KeyType.PrivateKey);
        RSA rsa2 = new RSA(getRsaPrivateKey(loginDTO.getPasswordPublicKey()),loginDTO.getPasswordPublicKey());
        String password = rsa2.decryptStr(loginDTO.getPassword(), KeyType.PrivateKey);
        return register(username, password);
    }

    @Override
    public SysUserAttribute setAttributeValue(Long userId, String attributeCode, String value) {
        return null;
    }

    @Override
    public void addAttributeDetail(Long userId, String attributeCode, List<SysUserAttributeDetail> details) {
    }

    @Override
    public void setAttributeDetail(Long userId, String attributeCode, List<SysUserAttributeDetail> details) {
    }


    @Override
    public void online(Long userId) {
        SysUserOnline sysUserOnline = new SysUserOnline();
        sysUserOnline.setUserId(userId);
        sysUserOnline.setOnlineStatusCode(SysUserOnline.ONLINE_STATUS_CODE_ONLINE);
        sysUserOnline.setGmtLastEnter(System.currentTimeMillis());
        sysUserOnline.setOnline(true);
        sysUserOnline.setGmtExpire(System.currentTimeMillis()+1000*60*3);
        onlineRepository.save(sysUserOnline);
    }

    @Override
    public void offline(Long userId) {
        SysUserOnline sysUserOnline = new SysUserOnline();
        sysUserOnline.setUserId(userId);
        sysUserOnline.setOnlineStatusCode(SysUserOnline.ONLINE_STATUS_CODE_OFFLINE);
        sysUserOnline.setGmtLastLeave(System.currentTimeMillis());
        sysUserOnline.setOnline(false);
        onlineRepository.save(sysUserOnline);
    }

    @Override
    public Boolean isUsernameExist(String username) {
        SysUser byUsername = repository.findByUsername(username);
        return byUsername != null;
    }

    @Override
    public void addRole(Long userId, List<String> roleCodes) {
        SysUser sysUser = repository.findById(userId).orElseThrow(() -> BaseException.of("userNotExist", "用户不存在"));
        List<SysUserRole> roleList = sysUser.getRoleList();
        if (roleList == null) {
            roleList = new ArrayList<>();
        }
        List<String> roleCodeListOfAdd = new ArrayList<>();
        // 如果已经有了，就不添加了
        for (String roleCode : roleCodes) {
            boolean exist = false;
            for (SysUserRole sysUserRole : roleList) {
                if (sysUserRole.getRoleCode().equals(roleCode)) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                roleCodeListOfAdd.add(roleCode);
            }
        }


        List<SysUserRole> sysUserRoleList = new ArrayList<>();
        for (String roleCode : roleCodeListOfAdd) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleCode(roleCode);
            sysUserRoleList.add(sysUserRole);
        }
        userRoleRepository.saveAll(sysUserRoleList);
    }

    @Override
    public void setRole(Long userId, List<String> roleCodes) {
        List<SysUserRole> notAdminByUserId = userRoleRepository.findNotAdminByUserId(userId);
        List<SysUserRole> roleList = new ArrayList<>();
        for (SysUserRole sysUserRole : notAdminByUserId) {
            if(sysUserRole.getVisible()==null||sysUserRole.getVisible()){
                roleList.add(sysUserRole);
            }
        }

        // 删除所有角色，除了admin
        userRoleRepository.deleteAll(roleList);
        // 添加角色
        addRole(userId, roleCodes);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // 去除空格
        oldPassword = oldPassword.trim();
        newPassword = newPassword.trim();

        // 判断用户名是否存在
        String selfToken = tokenService.getSelfToken();
        Long userIdByToken = tokenService.getUserIdByToken(selfToken);
        SysUser self = repository.findById(userIdByToken).orElseThrow(() -> BaseException.of("userNotExist", "用户不存在"));
        // 判断密码是否正确
        if (!new KantbootPassword().matches(oldPassword, self.getPassword())) {
            // 告知密码错误
            throw BaseException.of("passwordError", "密码错误");
        }

        // 密码加密开始时间
        long passwordEncodeStartTime = System.currentTimeMillis();
        self.setPassword(new KantbootPassword().encode(newPassword));
        // 密码加密结束时间
        long passwordEncodeEndTime = System.currentTimeMillis();
        log.info("密码加密耗时：{}ms", passwordEncodeEndTime - passwordEncodeStartTime);
        // 保存用户
        repository.save(self);
    }
}