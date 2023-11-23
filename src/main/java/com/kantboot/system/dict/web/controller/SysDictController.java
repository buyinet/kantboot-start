package com.kantboot.system.dict.web.controller;

import com.kantboot.system.dict.domain.entity.SysDict;
import com.kantboot.system.dict.service.ISysDictService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典控制器
 * @author 方某方
 */
@RestController
@RequestMapping("/system-dict-web/dict")
public class SysDictController {

    @Resource
    private ISysDictService service;

    /**
     * 获取字典列表
     * @param pageParam 分页参数
     * @return 字典列表
     */
    @RequestMapping("/search")
    public RestResult<PageResult> search(@RequestBody PageParam<SysDict> pageParam) {
        return RestResult.success(service.search(pageParam), "getSuccess", "获取成功");
    }


}
