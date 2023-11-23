package com.kantboot.business.goods.web.controller;

import com.kantboot.business.goods.service.IBusGoodsService;
import com.kantboot.util.common.http.HttpRequestHeaderUtil;
import com.kantboot.util.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-goods-web/goods")
public class BusGoodsController {

    @Resource
    private IBusGoodsService busGoodsService;

    @Resource
    private HttpRequestHeaderUtil httpRequestHeaderUtil;

    @RequestMapping("/getBySynthesis")
    public RestResult getBySynthesis(@RequestParam("synthesis") String synthesis) {
        return RestResult.success(busGoodsService.getBySynthesis(synthesis),"getSuccess","查询成功");
    }

    @RequestMapping("/getUserId")
    public RestResult getUserId() {
        return RestResult.success(httpRequestHeaderUtil.getUserId(),"getSuccess","查询成功");
    }

}
