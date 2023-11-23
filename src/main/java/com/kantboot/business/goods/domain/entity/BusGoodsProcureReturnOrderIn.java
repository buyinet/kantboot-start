package com.kantboot.business.goods.domain.entity;


import com.kantboot.util.core.jpa.KantbootGenerationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "bus_goods_procure_return_order_in")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class BusGoodsProcureReturnOrderIn {
    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 采购退货单ID
     */
    @Column(name = "procure_return_order_id")
    private Long procureReturnOrderId;

    /**
     * 折扣
     */
    @Column(name = "discount")
    private Double discount;


    /**
     * 折后价
     */
    @Column(name = "discount_price")
    private Double discountPrice;

    /**
     * 数量
     */
    @Column(name = "t_number")
    private Long number;


    /**
     * 商品ID
     */
    @Column(name = "goods_id")
    private Long goodsId;

    @OneToOne
    @JoinColumn(name = "goods_id", insertable = false, updatable = false)
    private BusGoods goods;


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
