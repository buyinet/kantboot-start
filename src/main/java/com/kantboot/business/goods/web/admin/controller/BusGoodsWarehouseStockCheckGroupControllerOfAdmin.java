package com.kantboot.business.goods.web.admin.controller;

import com.kantboot.business.goods.domain.entity.BusGoodsWarehouseStockCheckGroup;
import com.kantboot.business.goods.service.IBusGoodsWarehouseStockCheckGroupService;
import com.kantboot.util.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-goods-web/admin/goodsWarehouseStockCheckGroup")
public class BusGoodsWarehouseStockCheckGroupControllerOfAdmin {

    @Resource
    private IBusGoodsWarehouseStockCheckGroupService service;

    @RequestMapping("/save")
    public RestResult save(@RequestBody BusGoodsWarehouseStockCheckGroup entity) {
        service.save(entity);
        return RestResult.success(null,"saveSuccess","保存成功");
    }

}
