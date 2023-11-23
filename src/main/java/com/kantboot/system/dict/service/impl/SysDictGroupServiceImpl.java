package com.kantboot.system.dict.service.impl;

import com.kantboot.system.dict.dao.repository.SysDictGroupRepository;
import com.kantboot.system.dict.domain.entity.SysDictGroup;
import com.kantboot.system.dict.service.ISysDictGroupService;
import com.kantboot.util.common.exception.BaseException;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典分组服务类
 * @author 方某方
 */
@Service
public class SysDictGroupServiceImpl implements ISysDictGroupService
{

    /**
     * redis的key
     */
    private static final String REDIS_KEY = "dictGroup";

    @Resource
    private SysDictGroupRepository repository;



    @Override
    public List<SysDictGroup> getAll() {
        return repository.findAll();
    }

    @Cacheable(value = REDIS_KEY+".getMap")
    @Override
    public Map<String, Object> getMap() {
        HashMap<String, Object> result = new HashMap<>();
        List<SysDictGroup> all = getAll();
        for (SysDictGroup sysDictGroup : all) {
            result.put(sysDictGroup.getCode(), sysDictGroup.getName());
        }
        return result;
    }

    public void save(SysDictGroup sysDictGroup) {
        SysDictGroup byCode = repository.findByCode(sysDictGroup.getCode());
        if (byCode == null) {
            repository.save(sysDictGroup);
            return;
        }
        // 警告有重复的编码
        throw BaseException.of("codeRepeat","编码重复");
    }






}



