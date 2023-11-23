package com.kantboot.util.core.controller;

import com.alibaba.fastjson2.JSON;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.service.IBaseAdminService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
public class BaseAdminController<T extends Serializable,ID> {

    @Resource
    private IBaseAdminService<T,ID> service;

    @RequestMapping("/save")
    public RestResult save(@RequestBody T t){
        return RestResult.success(
                JSON.parseObject(JSON.toJSONString(service.save(t)))
                , "saveSuccess","保存成功");
    }

    @RequestMapping("/saveBatch")
    public RestResult<T> saveBatch(@RequestBody List<T> tList){
        service.saveBatch(tList);
        return RestResult.success(null, "saveSuccess","保存成功");
    }

    @RequestMapping("/remove")
    public RestResult<T> remove(@RequestBody T t){
        service.remove(t);
        return RestResult.success(null, "deleteSuccess","删除成功");
    }

    @RequestMapping("/removeBatch")
    public RestResult<T> removeBatch(@RequestBody List<T> tList){
        service.removeBatch(tList);
        return RestResult.success(null, "deleteSuccess","删除成功");
    }

}
