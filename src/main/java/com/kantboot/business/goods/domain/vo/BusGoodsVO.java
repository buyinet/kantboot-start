package com.kantboot.business.goods.domain.vo;

import com.kantboot.business.goods.domain.entity.BusGoodsType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
public class BusGoodsVO implements Serializable {

    private Long id;

    private String code;

    private String name;

    private String number;

    private Double price;

    private String barCode;

    private Long stock;

    private Long goodsTypeId;

    private Long priority;

    private Double purchasePrice;

    private Double wholesalePrice;

    private Double retailPrice;

    private Long stockWarningUpper;

    private Long stockWarningLower;

    private Long brandId;

    private Long unitId;

    private Long minOrder;

    private Date gmtCreate;

    private Date gmtModified;

    private BusGoodsType type;

    private Map<String,Object> attr;

}
