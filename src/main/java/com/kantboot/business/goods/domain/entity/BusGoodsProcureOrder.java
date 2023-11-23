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
import java.util.List;

/**
 * 商品采购单
 * 采购单和商品是一对多的关系
 * @author 方某方
 */
@Entity
@Getter
@Setter
@Table(name = "bus_goods_procure_order")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class BusGoodsProcureOrder implements java.io.Serializable
{

    private final static long serialVersionUID = 1L;

    /**
     * 状态编码：草稿
     */
    public final static String STATUS_CODE_DRAFT = "draft";

    /**
     * 状态编码：已采购
     */
    public final static String STATUS_CODE_PURCHASED = "purchased";

    /**
     * 状态编码：已撤销
     */
    public final static String STATUS_CODE_REVOKED = "revoked";


    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 采购单名称
     */
    @Column(name = "t_name")
    private String name;

    /**
     * 采购单号
     */
    @Column(name = "t_order", length = 64)
    private String order;

    /**
     * 采购单状态编码
     */
    @Column(name = "status_code", length = 64)
    private String statusCode;

    /**
     * 供应商id
     */
    @Column(name = "supplier_id")
    private Long supplierId;

    /**
     * 供应商
     */
    @OneToOne
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    private BusGoodsSupplier supplier;

    /**
     * 仓库id
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 仓库
     */
    @OneToOne
    @JoinColumn(name = "warehouse_id", insertable = false, updatable = false)
    private BusGoodsWarehouse warehouse;

    /**
     * 实付金额
     */
    @Column(name = "actual_amount")
    private Double actualAmount;


    /**
     * 备注
     */
    @Column(name = "t_remark")
    private String remark;

    /**
     * 总金额
     */
    @Column(name = "total_amount")
    private Double totalAmount;

    /**
     * 总数量
     */
    @Column(name = "total_number")
    private Long totalNumber;

    /**
     * 费用金额
     */
    @Column(name = "fee_amount")
    private Double feeAmount;

    /**
     * 抹零
     */
    @Column(name = "zero_amount")
    private Double zeroAmount;

    /**
     * 此单欠款
     */
    @Column(name = "due")
    private Double due;

    /**
     * 创建人id
     */
    @Column(name = "user_id_of_create")
    private Long userIdOfCreate;

    @OneToMany
    @JoinColumn(name = "procure_order_id",referencedColumnName = "id",insertable = false,updatable = false)
    private List<BusGoodsProcureOrderIn> inList;

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
