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
 * 员工
 * @Description 员工
 */
@Entity
@Getter
@Setter
@Table(name = "bus_emp")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class BusEmp implements Serializable {

    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 员工姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 员工性别编码，驼峰式
     * 男：male
     * 女：female
     * 未知：unknown
     * 保密：secret
     */
    @Column(name="gender_code")
    private String genderCode;

    /**
     * 员工生日
     */
    @Column(name = "birthday")
    private Date birthday;

    /**
     * 员工手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 员工邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 员工住址
     */
    @Column(name = "address")
    private String address;

    /**
     * 员工所属门店
     */
    @Column(name = "goods_store_id")
    private Long goodsStoreId;

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

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 账号
     * 非用户账号，而是员工账号
     * 用于员工登录，但是员工账号和用户账号基本是同一个
     */
    @Column(name="username")
    private String username;

    /**
     * 身份证
     */
    @Column(name="id_card")
    private String idCard;

    /**
     * 门店
     */
    @OneToOne
    @JoinColumn(name = "goods_store_id", referencedColumnName = "id", insertable = false, updatable = false)
    private BusGoodsStore goodsStore;

}
