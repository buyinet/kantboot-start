package com.kantboot.business.goods.domain.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BusGoodsProcureOrderSearchDTO {

    /**
     * 开始时间
     */
    private Date gmtCreateStart;

    /**
     * 结束时间
     */
    private Date gmtCreateEnd;

    /**
     * 状态编码
     */
    private String statusCode;

    /**
     * 状态编码list
     */
    private List<String> statusCodeList;


    /**
     * 供应商id
     */
    private Long supplierId;

    /**
     * 采购单号
     */
    private String order;

    /**
     * 采购单备注
     */
    private String remark;

}
