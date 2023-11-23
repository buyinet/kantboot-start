package com.kantboot.system.user.web.controller;


import com.kantboot.system.user.domain.dto.SysRoleSaveDTO;
import com.kantboot.system.user.domain.entity.SysRole;
import com.kantboot.system.user.domain.vo.SysRoleVO;
import com.kantboot.system.user.service.ISysRoleService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.controller.BaseAdminController;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色控制器
 * @author 方某方
 */
@RestController
@RequestMapping("/system-user-web/role")
public class SysRoleController extends BaseAdminController<SysRole,Long> {

    @Resource
    private ISysRoleService service;

    /**
     * 获取所有编码
     * @return 编码列表
     */
    @RequestMapping("/getCodes")
    public RestResult<List<String>> getCodes() {
        return RestResult.success(service.getCodes(),"getSuccess","获取成功");
    }

    /**
     * 获取所有角色
     * @return 角色列表
     */
    @RequestMapping("/getAll")
    public RestResult<List<SysRole>> getAll() {
        return RestResult.success(service.getAll(),"getSuccess","获取成功");
    }

    /**
     * 获取所有角色VO
     * @return 角色VO列表
     */
    @RequestMapping("/getAllVO")
    public RestResult<List<SysRoleVO>> getAllVO() {
        return RestResult.success(service.getAllVO(),"getSuccess","获取成功");
    }

    /**
     * 获取角色map
     * @return 角色map
     */
    @RequestMapping("/getMap")
    public RestResult getMap() {
        return RestResult.success(service.getMap(),"getSuccess","获取成功");
    }

    /**
     * 保存所有角色
     */
    @RequestMapping("/saveVO")
    public RestResult saveVO(@RequestBody SysRoleSaveDTO dto) {
        return RestResult.success(service.saveVO(dto),"saveSuccess","保存成功");
    }

}