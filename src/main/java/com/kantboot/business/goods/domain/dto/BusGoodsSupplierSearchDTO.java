package com.kantboot.business.goods.domain.dto;

import lombok.Data;

@Data
public class BusGoodsSupplierSearchDTO {

    /**
     * 供应商名称、电话或手机
     */
    private String synthesis;

    /**
     * 负责人姓名
     */
    private String principalName;

}
