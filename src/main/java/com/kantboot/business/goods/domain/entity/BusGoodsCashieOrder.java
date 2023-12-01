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
 * 收银单
 */
@Entity
@Getter
@Setter
@Table(name = "bus_cashie_order")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class BusGoodsCashieOrder {


    /**
     * 状态编码：已完成
     */
    public final static String STATUS_CODE_COMPLETED = "completed";

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
     * 总金额
     */
    @Column(name = "amount")
    private Double amount;

    /**
     * 总数量
     */
    @Column(name = "number")
    private Long number;

    /**
     * 折扣
     */
    @Column(name = "discount")
    private Double discount;

    /**
     * 抹零金额
     */
    @Column(name = "zero_amount")
    private Double zeroAmount;

    /**
     * 实收金额
     */
    @Column(name = "real_amount")
    private Double realAmount;

    @OneToMany
    @JoinColumn(name = "cashie_order_id")
    private List<BusGoodsCashieOrderIn> inList;

    /**
     * 收银员id
     */
    @Column(name = "emp_id")
    private Long empId;

    @OneToOne
    @JoinColumn(name = "emp_id",insertable = false,updatable = false)
    private BusEmp emp;

    /**
     * 仓库id
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    @OneToOne
    @JoinColumn(name = "warehouse_id",insertable = false,updatable = false)
    private BusGoodsWarehouse warehouse;

    /**
     * 备注
     */
    @Column(name = "t_remark")
    private String remark;

    /**
     * 业务编码
     */
    @Column(name = "t_order")
    private String order;

    /**
     * 采购单状态编码
     */
    @Column(name = "status_code", length = 64)
    private String statusCode;


    @OneToOne
    @JoinColumn(name = "warehouse_id",insertable = false,updatable = false)
    private BusGoodsWarehouse busGoodsWarehouse;

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
