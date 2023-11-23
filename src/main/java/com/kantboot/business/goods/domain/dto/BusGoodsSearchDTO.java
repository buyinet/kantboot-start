package com.kantboot.business.goods.domain.dto;

import com.kantboot.business.goods.domain.entity.BusGoodsType;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class BusGoodsSearchDTO {

    private Long id;

    /**
     * 商品编码
     */
    private String code;

    private String name;

    private String numberStr;

    private Double price;

    /**
     * 最大价格
     */
    private Double minPrice;

    /**
     * 最小价格
     */
    private Double maxPrice;

    private String barCode;

    private Long stock;

    /**
     * 最小库存
     */
    private Long minStock;

    /**
     * 最大库存
     */
    private Long maxStock;

    private Long typeId;

    private List<Long> typeIdList;

    private Long priority;

    private Double purchasePrice;

    /**
     * 最小采购价
     */
    private Double minPurchasePrice;

    /**
     * 最大采购价
     */
    private Double maxPurchasePrice;


    private Double wholesalePrice;

    /**
     * 最小批发价
     */
    private Double minWholesalePrice;

    /**
     * 最大批发价
     */
    private Double maxWholesalePrice;

    private Double retailPrice;

    /**
     * 最小零售价
     */
    private Double minRetailPrice;

    /**
     * 最大零售价
     */
    private Double maxRetailPrice;

    private Long stockWarningUpper;

    private Long stockWarningLower;

    private Long brandId;

    private Long unitId;

    private Long minOrder;

    /**
     * 最低起订量
     */
    private Long minMinOrder;

    /**
     * 最高起订量
     */
    private Long maxMinOrder;

    private Date gmtCreate;

    private Date gmtModified;

    private BusGoodsType type;

    private Map<String,Object> attr;

}
