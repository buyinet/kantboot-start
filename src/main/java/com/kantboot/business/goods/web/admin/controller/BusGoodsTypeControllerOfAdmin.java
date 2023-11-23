package com.kantboot.business.goods.web.admin.controller;

import com.kantboot.business.goods.domain.entity.BusGoodsType;
import com.kantboot.business.goods.service.IBusGoodsTypeService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.controller.BaseAdminController;
import com.kantboot.util.core.param.PageParam;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-goods-web/admin/goodsType")
public class BusGoodsTypeControllerOfAdmin extends BaseAdminController<BusGoodsType,Long>
{

    @Resource
    private IBusGoodsTypeService service;

    @RequestMapping("/getBodyData")
    public RestResult getBodyData(
            @RequestBody PageParam<BusGoodsType> pageParam
    ) {
        return RestResult.success(service.getBodyData(pageParam), "getSuccess","查询成功");
    }

    @RequestMapping("/getBodyDataTop")
    public RestResult getBodyDataTop(
            @RequestBody PageParam<BusGoodsType> pageParam
    ) {
        return RestResult.success(service.getBodyDataTop(pageParam), "getSuccess","查询成功");
    }


}
