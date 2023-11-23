package com.kantboot.business.goods.web.controller;

import com.kantboot.business.goods.domain.entity.BusEmp;
import com.kantboot.business.goods.service.IBusEmpService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.controller.BaseAdminController;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-goods-web/emp")
public class BusEmpController extends BaseAdminController<BusEmp,Long> {

    @Resource
    private IBusEmpService service;

    @RequestMapping("/getSelf")
    public RestResult getSelf() {
        return RestResult.success(service.getSelf(), "getSuccess","查询成功");
    }

}
