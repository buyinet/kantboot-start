package com.kantboot.system.user.web.controller;

import com.kantboot.system.user.domain.entity.SysPermission;
import com.kantboot.system.user.service.ISysPermissionService;
import com.kantboot.util.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system-user-web/permission")
public class SysPermissionController {

    @Resource
    private ISysPermissionService service;

    /**
     * 根据权限组编码获取权限组
     * @param code 权限组编码
     * @return 权限组
     */
    @RequestMapping("/getByCode")
    public RestResult<SysPermission> getByCode(String code) {
        return RestResult.success(service.getByCode(code),"getSuccess","获取成功");
    }

    /**
     * 获取所有权限组
     * @return 权限组列表
     */
    @RequestMapping("/getAllTop")
    public RestResult<List<SysPermission>> getAllTop() {
        return RestResult.success(service.getAllTop(),"getSuccess","获取成功");
    }

    @RequestMapping("/getAll")
    public RestResult<List<SysPermission>> getAll() {
        return RestResult.success(service.getAll(),"getSuccess","获取成功");
    }


}
