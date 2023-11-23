package com.kantboot.business.goods.web.admin.controller;

import com.kantboot.business.goods.domain.entity.BusGoodsStore;
import com.kantboot.business.goods.service.IBusGoodsStoreService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.controller.BaseAdminController;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-goods-web/admin/goodsStore")
public class BusGoodsStoreControllerOfAdmin extends BaseAdminController<BusGoodsStore,Long>
{

    @Resource
    private IBusGoodsStoreService service;

    @RequestMapping("/getBodyData")
    public RestResult<PageResult> getBodyData(@RequestBody PageParam<BusGoodsStore> pageParam) {
        return RestResult.success(service.getBodyData(pageParam), "getSuccess","查询成功");
    }

}
