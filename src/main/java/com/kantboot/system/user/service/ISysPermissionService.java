package com.kantboot.system.user.service;

import com.kantboot.system.user.domain.entity.SysPermission;

import java.util.List;

public interface ISysPermissionService {

    /**
     * 根据权限组编码获取权限组
     * @param code 权限组编码
     * @return 权限组
     */
    SysPermission getByCode(String code);

    /**
     * 获取所有权限组
     * @return 权限组列表
     */
    List<SysPermission> getAllTop();

    /**
     * 获取所有权限组
     * @return 权限组列表
     */
    List<SysPermission> getAll();

}
