package com.kantboot.system.dict.web.controller;

import com.kantboot.system.dict.domain.entity.SysDictGroup;
import com.kantboot.system.dict.service.ISysDictGroupService;
import com.kantboot.util.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典分组控制器
 * @author 方某方
 */
@RestController
@RequestMapping("/system-dict-web/dictGroup")
public class SysDictGroupController {

    @Resource
    private ISysDictGroupService service;

    /**
     * 获取所有字典分组
     * @return 字典分组列表
     */
    @RequestMapping(value = "/getAll",method = RequestMethod.POST)
    public RestResult getAll() {
        return RestResult.success(service.getAll(),"getSuccess","获取成功");
    }

    /**
     * 获取字典分组Map
     * @return 字典分组Map
     */
    @RequestMapping(value = "/getMap",method = RequestMethod.POST)
    public RestResult getMap() {
        return RestResult.success(service.getMap(),"getSuccess","获取成功");
    }

    /**
     * 保存字典分组
     * @param entity 字典分组
     * @return 保存结果
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public RestResult save(SysDictGroup entity) {
        service.save(entity);
        return RestResult.success(null,"saveSuccess","保存成功");
    }

}
