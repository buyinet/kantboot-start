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
 * 门店
 * @author 方某方
 */
@Entity
@Getter
@Setter
@Table(name = "bus_goods_store")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class BusGoodsStore implements Serializable {


    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 类型编码，驼峰式
     * 直营：direct
     * 联营：join
     * 加盟：franchise
     */
    @Column(name = "type_code")
    private String typeCode;

    /**
     * 开业时间
     */
    @Column(name = "gmt_open")
    private Date gmtOpen;

    /**
     * 面积
     */
    @Column(name = "area")
    private Double area;

    /**
     * 门店地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 门店电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 门店启用状态
     */
    @Column(name = "enable_status")
    private Boolean enableStatus;


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
