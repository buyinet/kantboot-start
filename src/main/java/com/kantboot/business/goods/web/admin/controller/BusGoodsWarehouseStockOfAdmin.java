package com.kantboot.business.goods.web.admin.controller;

import com.kantboot.business.goods.domain.dto.BusGoodsWarehouseStockDTO;
import com.kantboot.business.goods.service.IBusGoodsWarehouseStockService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.param.PageParam;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-goods-web/admin/goodsWarehouseStock")
public class BusGoodsWarehouseStockOfAdmin {

    @Resource
    private IBusGoodsWarehouseStockService service;

    @RequestMapping("/getBodyData")
    public RestResult getBodyData(
            @RequestBody PageParam<BusGoodsWarehouseStockDTO> pageParam
            ) {
        return RestResult.success(service.getBodyData(pageParam), "getSuccess","查询成功");
    }


    @RequestMapping("/check")
    public RestResult check(
            @RequestParam("id") Long id,
            @RequestParam("newStock") Long newStock,
            @RequestParam("oldStock") Long oldStock,
            @RequestParam("remark") String remark
            ) {
        service.check(id, newStock, oldStock, remark);
        return RestResult.success(null, "checkSuccess","盘点成功");
    }

    @RequestMapping("/getById")
    public RestResult getById(
            @RequestParam("id") Long id
            ) {
        return RestResult.success(service.getById(id), "getSuccess","查询成功");
    }

    /**
     * 计算成本
     * @return 成本
     */
    @RequestMapping("/calculateCost")
    public RestResult calculateCost() {
        return RestResult.success(service.calculateCost(), "getSuccess","查询成功");
    }


}
