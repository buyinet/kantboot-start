package com.kantboot.business.goods.web.admin.controller;

import com.kantboot.business.goods.domain.entity.BusGoodsWarehouse;
import com.kantboot.business.goods.service.IBusGoodsWarehouseService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.controller.BaseAdminController;
import com.kantboot.util.core.param.PageParam;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-goods-web/admin/goodsWarehouse")
public class BusGoodsWarehouseControllerOfAdmin extends BaseAdminController<BusGoodsWarehouse,Long> {

    @Resource
    private IBusGoodsWarehouseService service;

    @RequestMapping("/getBodyData")
    public RestResult getBodyData(
            @RequestBody PageParam<BusGoodsWarehouse> pageParam
    ) {
        return RestResult.success(service.getBodyData(pageParam), "getSuccess","查询成功");
    }

}
