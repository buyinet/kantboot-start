package com.kantboot.system.user.service.impl;

import com.kantboot.system.user.dao.repository.SysPermissionRepository;
import com.kantboot.system.user.domain.entity.SysPermission;
import com.kantboot.system.user.service.ISysPermissionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限组服务实现类
 */
@Service
public class SysPermissionServiceImpl implements ISysPermissionService {

    @Resource
    private SysPermissionRepository repository;

    /**
     * 根据权限组编码获取权限组
     */
    @Override
    public SysPermission getByCode(String code) {
        return repository.getByCode(code);
    }

    /**
     * 获取所有权限组
     */
    @Override
    public List<SysPermission> getAllTop() {
        return repository.getAllTop();
    }

    @Override
    public List<SysPermission> getAll() {
        return repository.findAll();
    }
}
