package com.kantboot.system.user.service;

import com.kantboot.system.user.domain.dto.SecurityLoginDTO;
import com.kantboot.system.user.domain.entity.SysUserAttribute;
import com.kantboot.system.user.domain.entity.SysUserAttributeDetail;
import com.kantboot.system.user.domain.vo.LoginVO;

import java.util.List;
import java.util.Map;

/**
 * 用户服务接口
 * @author 方某方
 */
public interface ISysUserService {

    /**
     * 获取默认角色编码
     * @return 默认角色编码
     */
    String getDefaultRoleCode();

    /**
     * 根据用户id获取用户信息
     * @param id 用户id
     * @return 用户信息
     */
    Map<String,Object> getById(Long id);

    /**
     * 获取自己的信息
     */
    Map<String,Object> getSelf();

    Long getSelfId();


    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    LoginVO login(String username, String password);

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    LoginVO register(String username, String password);

    /**
     * 安全登录
     * @param loginDTO 登录信息
     * @return token
     */
    LoginVO securityLogin(SecurityLoginDTO loginDTO);

    /**
     * 安全注册
     * @param loginDTO 注册信息
     * @return token
     */
    LoginVO securityRegister(SecurityLoginDTO loginDTO);

    /**
     * 生成密钥，并返回公钥
     * @return 公钥
     */
    String getRsaPublicKey();

    /**
     * 根据公钥获取私钥
     * @param publicKey 公钥
     * @return 私钥
     */
    String getRsaPrivateKey(String publicKey);


    /**
     * 设置属性值
     * @param userId 用户id
     * @param attributeCode 属性编码
     * @param value 属性值
     * @return 属性
     */
    SysUserAttribute setAttributeValue(Long userId, String attributeCode, String value);

    /**
     * 添加属性详情
     * @param userId 用户id
     * @param attributeCode 属性编码
     * @param details 属性详情
     */
    void addAttributeDetail(Long userId, String attributeCode, List<SysUserAttributeDetail> details);

    /**
     * 设置属性详情
     * @param userId 用户id
     * @param attributeCode 属性编码
     * @param details 属性详情
     */
    void setAttributeDetail(Long userId, String attributeCode, List<SysUserAttributeDetail> details);

    /**
     * 上线
     */
    void online(Long userId);

    /**
     * 离线
     */
    void offline(Long userId);

    /**
     * 用户名是否存在
     */
    Boolean isUsernameExist(String username);

    /**
     * 给用户添加角色
     */
    void addRole(Long userId, List<String> roleCodes);

    /**
     * 给用户设置角色
     */
    void setRole(Long userId, List<String> roleCodes);

    /**
     * 修改密码
     */
    void changePassword(String oldPassword, String newPassword);

}
