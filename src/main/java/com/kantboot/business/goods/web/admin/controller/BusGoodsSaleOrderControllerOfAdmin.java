package com.kantboot.business.goods.web.admin.controller;

import com.kantboot.business.goods.domain.dto.BusGoodsProcureOrderSearchDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsSaleOrder;
import com.kantboot.business.goods.service.IBusGoodsSaleOrderService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.param.PageParam;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-goods-web/admin/goodsSaleOrder")
public class BusGoodsSaleOrderControllerOfAdmin {

    @Resource
    private IBusGoodsSaleOrderService service;

    @RequestMapping("/getBodyData")
    public RestResult getBodyData(
            @RequestBody PageParam<BusGoodsProcureOrderSearchDTO> pageParam
    ) {
        return RestResult.success(service.getBodyData(pageParam), "getSuccess","获取成功");
    }

    /**
     * 撤销
     */
    @RequestMapping("/revoke")
    public RestResult revoke(
            @RequestParam("id") Long id
    ) {
        service.revoke(id);
        return RestResult.success(null, "revokeSuccess","撤销成功");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public RestResult save(
            @RequestBody BusGoodsSaleOrder entity
            ) {
        return RestResult.success(service.save(entity), "saveSuccess","保存成功");
    }

}
