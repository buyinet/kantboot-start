package com.kantboot.system.user.web.controller;

import com.kantboot.system.user.domain.dto.SecurityLoginDTO;
import com.kantboot.system.user.service.ISysUserService;
import com.kantboot.util.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制器
 * @author 方某方
 */
@RestController
@RequestMapping("/system-user-web/user")
public class SysUserController {

    @Resource
    private ISysUserService service;

    @RequestMapping("/getSelf")
    public RestResult getSelf(){
        return RestResult.success(service.getSelf(),"getSuccess","获取成功");
    }

    /**
     * 根据id获取用户
     * @param id 用户id
     * @return 用户
     */
    @RequestMapping("/getById")
    public RestResult getById(@RequestParam("id") Long id) {
        return RestResult.success(service.getById(id),"getSuccess","获取成功");
    }

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    @RequestMapping("/register")
    public RestResult register(@RequestParam("username") String username,
                               @RequestParam("password") String password) {
        return RestResult.success(service.register(username,password),"registerSuccess","注册成功");
    }

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    @RequestMapping("/login")
    public RestResult login(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        return RestResult.success(service.login(username,password),"loginSuccess","登录成功");
    }

    /**
     * 获取rsa公钥
     * @return 结果
     */
    @RequestMapping("/getRsaPublicKey")
    public RestResult getRsaPublicKey() {
        return RestResult.success(service.getRsaPublicKey(),"generateKeySuccess","生成成功");
    }

    /**
     * 安全登录
     * @param dto 安全登录dto
     * @return token
     */
    @RequestMapping("/securityLogin")
    public RestResult securityLogin(@RequestBody SecurityLoginDTO dto) {
        return RestResult.success(service.securityLogin(dto),"loginSuccess","登录成功");
    }

    /**
     * 安全注册
     * @param dto 安全注册dto
     * @return token
     */
    @RequestMapping("/securityRegister")
    public RestResult securityRegister(@RequestBody SecurityLoginDTO dto) {
        return RestResult.success(service.securityRegister(dto),"registerSuccess","注册成功");
    }

    /**
     * 添加角色
     * @param userId 用户id
     * @param roleCodes 角色code列表
     */
    @RequestMapping("/addRole")
    public RestResult addRole(@RequestParam("userId") Long userId,
                              @RequestParam("roleCodes") List<String> roleCodes) {
        service.addRole(userId,roleCodes);
        return RestResult.success(null,"addSuccess","添加成功");
    }

    /**
     * 设置角色
     * @param userId 用户id
     * @param roleCodes 角色code列表
     */
    @RequestMapping("/setRole")
    public RestResult setRole(@RequestParam("userId") Long userId,
                              @RequestParam("roleCodes") List<String> roleCodes) {
        service.setRole(userId,roleCodes);
        return RestResult.success(null,"setSuccess","设置成功");
    }

    /**
     * 修改密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    @RequestMapping("/changePassword")
    public RestResult changePassword(@RequestParam("oldPassword") String oldPassword,
                                     @RequestParam("newPassword") String newPassword) {
        service.changePassword(oldPassword,newPassword);
        return RestResult.success(null,"changeSuccess","修改成功");
    }

}
