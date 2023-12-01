package com.kantboot.business.goods.service;

import com.kantboot.business.goods.domain.entity.BusGoodsWarehouseStockCheckGroup;

public interface IBusGoodsWarehouseStockCheckGroupService {

    /**
     * 生成盘点单
     */
    void save(BusGoodsWarehouseStockCheckGroup group);

}
