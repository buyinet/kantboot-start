package com.kantboot.business.goods.domain.vo;

import com.kantboot.business.goods.domain.entity.BusGoodsWarehouse;
import lombok.Data;

@Data
public class BusGoodsWarehouseStockStatisticsInVO {

    /**
     * 仓库
     */
    private BusGoodsWarehouse warehouse;


    /**
     * 库存总数量
     */
    private Long stockCount;

    /**
     * 库存总金额
     */
    private Double stockAmount;

}
