package com.kantboot.system.dict.service;

import com.kantboot.system.dict.domain.entity.SysDictGroup;

import java.util.List;
import java.util.Map;

/**
 * 字典分组服务类
 * @author 方某方
 */
public interface ISysDictGroupService {

    List<SysDictGroup> getAll();

    Map<String,Object> getMap();

    /**
     * 添加分组
     * @param sysDictGroup 分组
     */
    void save(SysDictGroup sysDictGroup);

}
