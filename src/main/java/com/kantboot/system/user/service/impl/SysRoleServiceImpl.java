package com.kantboot.system.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.kantboot.system.user.dao.repository.SysPermissionRoleRepository;
import com.kantboot.system.user.dao.repository.SysRoleRepository;
import com.kantboot.system.user.dao.repository.SysRoleVORepository;
import com.kantboot.system.user.domain.dto.SysRoleSaveDTO;
import com.kantboot.system.user.domain.entity.SysPermissionRole;
import com.kantboot.system.user.domain.entity.SysRole;
import com.kantboot.system.user.domain.vo.SysRoleVO;
import com.kantboot.system.user.service.ISysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色服务实现类
 *
 * @author 方某方
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    /**
     * redis缓存前缀
     */
    private static final String REDIS_KEY_PREFIX = "sysRole";

    @Resource
    private SysRoleRepository repository;

    @Resource
    private SysRoleVORepository voRepository;

    @Resource
    private SysPermissionRoleRepository permissionRoleRepository;

    @Override
//    @Cacheable(value = REDIS_KEY_PREFIX+":codes")
    public List<String> getCodes() {
        return repository.findAllCode();
    }

    @Override
//    @Cacheable(value = REDIS_KEY_PREFIX+":all")
    public List<SysRole> getAll() {
        return repository.findAll();
    }

    @Override
//    @Cacheable(value = REDIS_KEY_PREFIX+":code", key = "#code")
    public SysRole getByCode(String code) {
        return repository.findByCode(code);
    }


    @Override
    public List<SysRoleVO> getAllVO() {
        return voRepository.findAll();
    }

    @Override
    public Map<String,String> getMap() {
        List<SysRole> all = getAll();
        Map<String, String> result = new HashMap<>();
        for (SysRole sysRole : all) {
            result.put(sysRole.getCode(),sysRole.getName());
        }
        return result;
    }

    @Override
    public SysRoleVO saveVO(SysRoleSaveDTO dto) {
        List<SysPermissionRole> byRoleCode = permissionRoleRepository.getByRoleCode(dto.getCode());
        permissionRoleRepository.deleteAll(byRoleCode);
        permissionRoleRepository.flush();
        SysRoleVO vo = BeanUtil.copyProperties(dto, SysRoleVO.class);
        vo.setPermissionCodeList(null);
        vo.setPermissionList(null);
        if(vo.getCode()==null){
            vo.setCode("roleBy"+ IdUtil.simpleUUID().replaceAll("-",""));
        }

        SysRoleVO save = voRepository.save(vo);

        List<String> permissionCodeList = dto.getPermissionCodeList();
        List<SysPermissionRole> permissionRoleList = new ArrayList<>();
        for (String permissionCode : permissionCodeList) {
            SysPermissionRole permissionRole = new SysPermissionRole();
            permissionRole.setRoleCode(save.getCode());
            permissionRole.setPermissionCode(permissionCode);
            permissionRoleList.add(permissionRole);
        }
        permissionRoleRepository.saveAll(permissionRoleList);
        permissionRoleRepository.flush();

        return save;
    }
}
