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
 * 商品供应商
 * @author 方某方
 */
@Entity
@Getter
@Setter
@Table(name = "bus_goods_supplier")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class BusGoodsSupplier implements Serializable {

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
    @Column(name = "t_name")
    private String name;

    /**
     * 负责人姓名
     */
    @Column(name = "principal_name")
    private String principalName;

    /**
     * 电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 手机
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 银行账户
     */
    @Column(name = "bank_account")
    private String bankAccount;

    /**
     * 开户行
     */
    @Column(name = "bank_name")
    private String bankName;

    /**
     * 默认折扣
     */
    @Column(name = "discount")
    private Double discount;

    /**
     * 网址
     */
    @Column(name = "website")
    private String website;

    /**
     * 地址编码
     */
    @Column(name = "address_code")
    private Long addressCode;

    /**
     * 详细地址
     */
    @Column(name = "address_detail")
    private String addressDetail;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 关联尚欠供应商款(BusGoodsDueToSupplier)表的supplierId
     * 通过BusGoodsDueToSupplier.supplierId关联此表的id
     */
    @OneToOne
    @JoinColumn(name = "id",insertable = false, updatable = false)
    private BusGoodsDueToSupplier dueToSupplier;

    /**
     * 尚欠供应商款
     */
    @Transient
    private Double due;

    @Transient
    public Double getDue() {
        if (this.dueToSupplier == null) {
            return 0.0;
        }
        if (this.dueToSupplier.getDue() == null) {
            return 0.0;
        }
        return this.dueToSupplier.getDue();
    }

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
