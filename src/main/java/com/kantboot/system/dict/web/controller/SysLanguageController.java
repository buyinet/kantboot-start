package com.kantboot.system.dict.web.controller;

import com.kantboot.system.dict.domain.vo.SysLanguageVO;
import com.kantboot.system.dict.service.ISysLanguageService;
import com.kantboot.util.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 语言控制器
 * 用于获取支持的语言列表
 * @author 方某方
 */
@RestController
@RequestMapping("/system-dict-web/language")
public class SysLanguageController {

    @Resource
    private ISysLanguageService service;

    /**
     * 获取支持的语言列表
     * @return 支持的语言列表
     */
    @RequestMapping(value = "/getBySupport",method = RequestMethod.POST)
    public RestResult<List<SysLanguageVO>> getBySupport() {
        // 告诉前端，这个接口是成功的，返回的数据是什么
        return RestResult.success(service.getBySupport(), "getSuccess", "获取成功");
    }


}
