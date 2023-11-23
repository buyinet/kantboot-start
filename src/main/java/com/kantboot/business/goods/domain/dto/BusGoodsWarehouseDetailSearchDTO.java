package com.kantboot.business.goods.domain.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 商品仓库明细搜索DTO
 * @author 方某方
 */
@Data
public class BusGoodsWarehouseDetailSearchDTO {

    /**
     * 仓库id
     */
    private List<Long> warehouseIds;

    /**
     * 商品属性
     */
    private String goodsSynthesis;

    /**
     * 操作时间区间 开始
     */
    private Date gmtOperateStart;

    /**
     * 操作时间区间 结束
     */
    private Date gmtOperateEnd;


}
