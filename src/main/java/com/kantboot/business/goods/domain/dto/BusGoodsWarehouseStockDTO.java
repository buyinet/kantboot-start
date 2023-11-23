package com.kantboot.business.goods.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class BusGoodsWarehouseStockDTO {

    /**
     * 仓库id集合
     */
    private List<Long> warehouseIds;

    /**
     * 商品综合
     * 名称、货号、条形编码
     */
    private String goodsSynthesis;

    /**
     * 是否合并仓库统计
     */
    private Boolean mergeWarehouse;

    /**
     * 是否单品查询
     */
    private Boolean singleProduct;



}
