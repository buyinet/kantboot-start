package com.kantboot.system.setting.service;

import com.kantboot.system.setting.domain.entity.SysSettingGroup;

import java.util.List;

/**
 * 系统设置分组服务层接口
 * @author 方某方
 */
public interface ISysSettingGroupService {

    /**
     * 获取所有分组
     * @return 所有分组
     */
    List<SysSettingGroup> getAll();

}
