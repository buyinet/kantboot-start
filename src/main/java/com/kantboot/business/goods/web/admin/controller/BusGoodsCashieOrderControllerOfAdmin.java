package com.kantboot.business.goods.web.admin.controller;

import com.kantboot.business.goods.domain.dto.BusGoodsCashieOrderSearchDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsCashieOrder;
import com.kantboot.business.goods.service.IBusGoodsCashieOrderService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.param.PageParam;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-goods-web/admin/goodsCashieOrder")
public class BusGoodsCashieOrderControllerOfAdmin {

    @Resource
    private IBusGoodsCashieOrderService service;

    @RequestMapping("/save")
    public RestResult save(@RequestBody BusGoodsCashieOrder entity) {
        return RestResult.success(service.save(entity),"saveSuccess","保存成功");
    }

    /**
     * 撤销
     */
    @RequestMapping("/revoke")
    public RestResult revoke(
            @RequestParam("id") Long id) {
        service.revoke(id);
        return RestResult.success(null,"revokeSuccess","撤销成功");
    }

    /**
     * 获取主表数据
     */
    @RequestMapping("/getBodyData")
    public RestResult getBodyData(
            @RequestBody PageParam<BusGoodsCashieOrderSearchDTO> param) {
        return RestResult.success(service.getBodyData(param),"getSuccess","获取成功");
    }

}
