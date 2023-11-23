package com.kantboot.business.goods.web.admin.controller;

import com.kantboot.business.goods.domain.entity.BusEmp;
import com.kantboot.business.goods.service.IBusEmpService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.controller.BaseAdminController;
import com.kantboot.util.core.param.PageParam;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-goods-web/admin/emp")
public class BusEmpControllerOfAdmin extends BaseAdminController<BusEmp,Long> {

    @Resource
    private IBusEmpService service;

    @RequestMapping("/getBodyData")
    public RestResult getBodyData(
            @RequestBody PageParam<BusEmp> pageParam
    ) {
        return RestResult.success(service.getBodyData(pageParam), "getSuccess","查询成功");
    }

}
