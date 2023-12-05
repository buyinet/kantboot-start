package com.kantboot.business.goods.domain.entity;

import com.kantboot.util.core.jpa.KantbootGenerationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品盘点表
 * @author 方某方
 */
@Entity
@Getter
@Setter
@Table(name = "bus_goods_warehouse_check_group")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class BusGoodsWarehouseStockCheckGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 仓库id
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 原库存
     */
    @Column(name = "stock_old")
    private Long stockOld;

    /**
     * 新库存
     */
    @Column(name = "stock_new")
    private Long stockNew;

    /**
     * 库存变化
     */
    @Column(name = "stock_change")
    private Long stockChange;

    /**
     * 业务单号
     */
    @Column(name = "business_order")
    private String businessOrder;

    /**
     * 改变前的数量采购价
     */
    @Column(name = "before_purchase_price")
    private Double beforePurchasePrice;

    /**
     * 改变后的数量采购价
     */
    @Column(name = "after_purchase_price")
    private Double afterPurchasePrice;

    /**
     * 改变的数量采购价
     */
    @Column(name = "change_purchase_price")
    private Double changePurchasePrice;

    @OneToMany
    @JoinColumn(name = "group_id")
    private List<BusGoodsWarehouseStockCheck> inList;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 创建时间
     * @return 创建时间
     */
    @CreatedDate
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 修改时间
     * @return 修改时间
     */
    @LastModifiedDate
    @Column(name = "gmt_modified")
    private Date gmtModified;


}
