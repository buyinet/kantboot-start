package com.kantboot.business.goods.web.admin.controller;

import com.kantboot.business.goods.domain.dto.BusGoodsWarehouseDetailSearchDTO;
import com.kantboot.business.goods.service.IBusGoodsWarehouseDetailService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.param.PageParam;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-goods-web/admin/goodsWarehouseDetail")
public class BusGoodsWarehouseDetailControllerOfAdmin {

    @Resource
    private IBusGoodsWarehouseDetailService service;

    @RequestMapping("/getBodyData")
    public RestResult getBodyData(
            @RequestBody PageParam<BusGoodsWarehouseDetailSearchDTO> pageParam
            ) {
        return RestResult.success(service.getBodyData(pageParam), "getSuccess","查询成功");
    }

}
