package com.kantboot.system.user.web.controller;

import com.kantboot.system.user.service.ISysTokenService;
import com.kantboot.util.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system-user-web/token")
public class SysTokenController {

    @Resource
    private ISysTokenService service;

    /**
     * 获取自身的token
     * @return token
     */
    @RequestMapping("/getSelfToken")
    public RestResult<String> getSelfToken() {
        return RestResult.success(service.getSelfToken(),"getSuccess","获取成功");
    }

}
