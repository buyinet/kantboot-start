package com.kantboot.system.user.web.admin.controller;

import com.kantboot.system.user.domain.entity.SysAdminRouter;
import com.kantboot.system.user.service.ISysAdminRouterService;
import com.kantboot.util.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system-user-web/admin/adminRouter")
public class SysAdminRouterControllerOfAdmin {

    @Resource
    private ISysAdminRouterService service;

    /**
     * 获取所有路由
     */
    @RequestMapping("/getAll")
    public RestResult<List<SysAdminRouter>> getAll() {
        return RestResult.success(service.getAll(),"getSuccess","获取成功");
    }

    /**
     * 获取top路由
     */
    @RequestMapping("/getTop")
    public RestResult<List<SysAdminRouter>> getTop() {
        return RestResult.success(service.getTop(),"getSuccess","获取成功");
    }

    /**
     * 获取自己的路由
     */
    @RequestMapping("/getSelf")
    public RestResult getSelf() {
        return RestResult.success(service.getSelf(),"getSuccess","获取成功");
    }

}
