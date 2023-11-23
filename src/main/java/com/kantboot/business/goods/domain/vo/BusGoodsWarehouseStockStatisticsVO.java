package com.kantboot.business.goods.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class BusGoodsWarehouseStockStatisticsVO {

    /**
     * 库存总数量
     */
    private Long stockCount;

    /**
     * 库存总金额
     */
    private Double stockAmount;

    List<BusGoodsWarehouseStockStatisticsInVO> list;

}
