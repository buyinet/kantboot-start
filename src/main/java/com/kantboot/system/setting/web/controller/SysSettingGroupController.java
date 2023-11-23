package com.kantboot.system.setting.web.controller;

import com.kantboot.system.setting.domain.entity.SysSettingGroup;
import com.kantboot.system.setting.service.ISysSettingGroupService;
import com.kantboot.util.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统设置控制层
 * @author 方某方
 */
@RestController
@RequestMapping("/system-setting-web/settingGroup")
public class SysSettingGroupController {

    @Resource
    private ISysSettingGroupService service;

    @RequestMapping("/getAll")
    public RestResult<List<SysSettingGroup>> getAll(){
        return RestResult.success(service.getAll(),"getSuccess","获取成功");
    }

}
