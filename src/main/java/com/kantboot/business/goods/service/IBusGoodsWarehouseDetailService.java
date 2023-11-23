package com.kantboot.business.goods.service;


import com.kantboot.business.goods.domain.dto.BusGoodsWarehouseDetailSearchDTO;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;

/**
 * 商品仓库明细服务接口
 * 用于处理商品仓库明细的相关服务
 * @author 方某方
 */
public interface IBusGoodsWarehouseDetailService {


    PageResult getBodyData(PageParam<BusGoodsWarehouseDetailSearchDTO> pageParam);

    /**
     * 根据订单号删除明细
     */
    void deleteByBusinessOrder(String order);


}
