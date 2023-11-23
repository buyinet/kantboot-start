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

import java.util.Date;

/**
 * 商品仓库明细表
 * 用于记录商品仓库的明细
 * @author 方某方
 */
@Entity
@Getter
@Setter
@Table(name = "bus_goods_warehouse_detail")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class BusGoodsWarehouseDetail {
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
     * 仓库
     */
    @OneToOne
    @JoinColumn(name = "warehouse_id",referencedColumnName = "id",insertable = false,updatable = false)
    private BusGoodsWarehouse warehouse;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 商品
     */
    @OneToOne
    @JoinColumn(name = "goods_id",referencedColumnName = "id",insertable = false,updatable = false)
    private BusGoods goods;

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
     * 业务类别编码
     */
    @Column(name = "business_code",length = 64)
    private String businessCode;

    /**
     * 关联的业务code
     */
    @OneToOne
    @JoinColumn(name = "business_code",referencedColumnName = "t_code",insertable = false,updatable = false)
    private BusGoodsBusiness business;

    /**
     * 业务单号
     */
    @Column(name = "business_order",length = 64)
    private String businessOrder;

    /**
     * 备注
     */
    @Column(name = "t_remark")
    private String remark;

    /**
     * 操作时间
     */
    @Column(name = "gmt_operate")
    private Date gmtOperate;

    /**
     * 创建时间
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
