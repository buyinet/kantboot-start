package com.kantboot.business.goods.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BusGoodsWarehouseChangeDTO {

    // 保存模式：save
    public static final String MODE_SAVE = "save";

    // 删除模式：delete
    public static final String MODE_DELETE = "delete";

    // 不保存模式：none
    public static final String MODE_NONE = "none";


    /**
     * 模式
     */
    private String mode = MODE_SAVE;


    /**
     * 仓库id
     */
    private Long warehouseId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 要添加的数量
     */
    private Long stockChange;

    /**
     * 业务编码
     */
    private String businessCode;

    /**
     * 业务订单
     */
    private String businessOrder;

    /**
     * 操作时间
     */
    private Date gmtOperate;

    /**
     * 备注
     */
    private String remark;

}
