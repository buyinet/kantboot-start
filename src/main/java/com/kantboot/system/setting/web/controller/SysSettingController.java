package com.kantboot.system.setting.web.controller;


import com.kantboot.system.setting.domain.entity.SysSetting;
import com.kantboot.system.setting.service.ISysSettingService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.param.PageParam;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 系统设置控制层
 * @author 方某方
 */
@RestController
@RequestMapping("/system-setting-web/setting")
public class SysSettingController {

    @Resource
    private ISysSettingService service;

    /**
     * 根据分组编码获取设置Map
     * @param groupCode 分组编码
     * @return 设置Map
     */
    @RequestMapping("/getMapByGroupCode")
    public RestResult<HashMap<String,String>> getMapByGroupCode(
            @RequestParam("groupCode") String groupCode){
        return RestResult.success(service.getMapByGroupCode(groupCode),"getSuccess","获取成功");
    }

    /**
     * 根据分组编码获取设置列表
     * @param groupCode 分组编码
     * @param code 编码
     * @return 设置列表
     */
    @RequestMapping("/getValue")
    public RestResult<String> getValue(
            @RequestParam("groupCode") String groupCode,
            @RequestParam("code") String code){
        return RestResult.success(service.getValue(groupCode,code),"getSuccess","获取成功");
    }

    @RequestMapping("/getBodyData")
    public RestResult getBodyData(@RequestBody PageParam<SysSetting> pageParam){
        return RestResult.success(service.getBodyData(pageParam),"getSuccess","获取成功");
    }

}
