package com.kantboot.system.setting.service.impl;

import com.kantboot.system.setting.dao.repository.SysSettingGroupRepository;
import com.kantboot.system.setting.domain.entity.SysSettingGroup;
import com.kantboot.system.setting.service.ISysSettingGroupService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统设置分组服务层实现类
 * @author 方某方
 */
@Service
public class SysSettingGroupServiceImpl implements ISysSettingGroupService
{

    /**
     * redis缓存前缀
     */
    private static final String REDIS_KEY_PREFIX = "sysSettingGroup:";

    @Resource
    private SysSettingGroupRepository repository;

    @Override
    public List<SysSettingGroup> getAll() {
        return repository.findAll();
    }


}
