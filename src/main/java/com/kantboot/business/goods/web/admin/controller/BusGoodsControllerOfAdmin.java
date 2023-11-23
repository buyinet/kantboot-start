package com.kantboot.business.goods.web.admin.controller;

import com.kantboot.business.goods.domain.dto.BusGoodsSearchDTO;
import com.kantboot.business.goods.domain.entity.BusGoods;
import com.kantboot.business.goods.service.IBusGoodsService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.controller.BaseAdminController;
import com.kantboot.util.core.param.PageParam;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-goods-web/admin/goods")
public class BusGoodsControllerOfAdmin extends BaseAdminController<BusGoods,Long> {

    @Resource
    private IBusGoodsService service;

    @Override
    public RestResult save(@RequestBody BusGoods busGoods) {

        return RestResult.success(service.save(busGoods), "saveSuccess","保存成功");
    }

    @RequestMapping("/getBodyDataTop")
    public RestResult getBodyDataTop(
            @RequestBody PageParam<BusGoodsSearchDTO> pageParam
    ) {
        return RestResult.success(service.getBodyDataTop(pageParam), "getSuccess","查询成功");
    }


    @RequestMapping("/getBodyData")
    public RestResult getBodyData(
            @RequestBody PageParam<BusGoodsSearchDTO> pageParam
    ) {
        return RestResult.success(service.getBodyData(pageParam), "getSuccess","查询成功");
    }


}
