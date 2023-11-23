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

/**
 * 商品仓库
 * @author 方某方
 */
@Entity
@Getter
@Setter
@Table(name = "bus_goods_warehouse")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class BusGoodsWarehouse implements Serializable {

    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 仓库名称
     */
    @Column(name = "t_name")
    private String name;

    /**
     * 仓库负责人
     */
    @Column(name = "emp_id_of_manager")
    private Long empIdOfManager;

    /**
     * 仓库负责人
     */
    @OneToOne
    @JoinColumn(name = "emp_id_of_manager",referencedColumnName = "id",insertable = false,updatable = false)
    private BusEmp empOfManager;

    /**
     * 门店id
     */
    @Column(name = "store_id")
    private Long storeId;

    /**
     * 门店
     */
    @OneToOne
    @JoinColumn(name = "store_id",referencedColumnName = "id",insertable = false,updatable = false)
    private BusGoodsStore store;

    /**
     * 备注
     */
    @Column(name = "t_remark")
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
