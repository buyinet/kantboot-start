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
 * 商品属性
 * @author 方某方
 */
@Entity
@Getter
@Setter
@Table(name = "bus_goods_attr")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class BusGoodsAttr implements Serializable {

    /**
     * 属性类型编码: 输入
     */
    public static final String TYPE_CODE_INPUT = "input";

    /**
     * 属性类型编码: 单选
     */
    public static final String TYPE_CODE_RADIO = "radio";

    /**
     * 属性类型编码: 多选
     */
    public static final String TYPE_CODE_CHECKBOX = "checkbox";

    /**
     * 属性类型编码: 日期
     */
    public static final String TYPE_CODE_DATE = "date";

    /**
     * 属性类型编码: 时间
     */
    public static final String TYPE_CODE_TIME = "time";

    /**
     * 属性类型编码: 日期时间
     */
    public static final String TYPE_CODE_DATETIME = "datetime";

    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 商品属性编码
     * 如果需要使用国际化，就需要使用code
     */
    @Column(name = "t_code", length = 64)
    private String code;

    /**
     * 商品属性名称
     */
    @Column(name = "t_name")
    private String name;

    /**
     * 商品属性值
     */
    @Column(name = "t_value")
    private String value;

    /**
     * 属性类型编码
     * 输入: input
     * 多值: multiple
     * 单选: radio
     * 多选: checkbox
     * 日期: date
     * 时间: time
     * 日期时间: datetime
     */
    @Column(name = "type_code")
    private String typeCode;

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
