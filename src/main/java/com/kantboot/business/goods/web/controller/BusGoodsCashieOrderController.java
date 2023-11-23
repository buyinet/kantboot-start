package com.kantboot.business.goods.web.controller;

import com.kantboot.business.goods.domain.entity.BusGoodsCashieOrder;
import com.kantboot.business.goods.service.IBusGoodsCashieOrderService;
import com.kantboot.util.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-goods-web/goodsCashieOrder")
public class BusGoodsCashieOrderController {

    @Resource
    private IBusGoodsCashieOrderService service;

    @RequestMapping("/save")
    public RestResult save(@RequestBody BusGoodsCashieOrder entity) {
        return RestResult.success(service.save(entity),"saveSuccess","保存成功");
    }

    /**
     * 结账
     */
    @RequestMapping("checkout")
    public RestResult checkout(@RequestBody BusGoodsCashieOrder entity) {
        return RestResult.success(service.save(entity),"checkoutSuccess","结账成功");
    }

}
