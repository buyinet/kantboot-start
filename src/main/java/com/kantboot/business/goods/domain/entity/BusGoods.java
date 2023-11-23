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

@Entity
@Getter
@Setter
@Table(name = "bus_goods")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class BusGoods implements Serializable {

    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 编码
     * 如果需要进行国际化，就需要使用code
     */
    @Column(name = "t_code", length = 64)
    private String code;

    /**
     * 商品名称
     */
    @Column(name = "t_name")
    private String name;

    /**
     * 商品货号
     */
    @Column(name = "t_number_str", length = 64)
    private String numberStr;


    /**
     * 商品价格
     */
    @Column(name = "t_price")
    private Double price;

    /**
     * 商品条形码
     */
    @Column(name = "t_bar_code", length = 64)
    private String barCode;

    /**
     * 商品分类编码
     */
    @Column(name = "type_id")
    private Long typeId;

    /**
     * 商品排序优先级
     */
    @Column(name = "t_priority")
    private Long priority;

    /**
     * 采购价
     */
    @Column(name = "t_purchase_price")
    private Double purchasePrice;

    /**
     * 批发价
     */
    @Column(name = "t_wholesale_price")
    private Double wholesalePrice;

    /**
     * 零售价
     */
    @Column(name = "t_retail_price")
    private Double retailPrice;

    /**
     * 库存预警上限
     * 用来提醒不用采购了
     */
    @Column(name = "t_stock_warning_upper")
    private Long stockWarningUpper;

    /**
     * 库存预警下限
     * 用来提醒库存很少了，需要采购了
     */
    @Column(name = "t_stock_warning_lower")
    private Long stockWarningLower;

    /**
     * 商品品牌id
     */
    @Column(name = "t_brand_id")
    private Long brandId;

    /**
     * 商品单位id
     */
    @Column(name = "t_unit_id")
    private Long unitId;

    /**
     * 起订量
     */
    @Column(name = "t_min_order")
    private Long minOrder;

    /**
     * 展示图json
     */
    @Column(name = "file_id_of_img_arr_str")
    private String fileIdOfImgArrStr;

    /**
     * 描述json
     */
    @Column(name = "description_json")
    private String descriptionJson;

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

    @OneToOne
    @JoinColumn(name = "type_id",referencedColumnName = "id",insertable = false,updatable = false)
    private BusGoodsType type;

    @OneToMany
    @JoinColumn(name = "goods_id",referencedColumnName = "id",insertable = false,updatable = false)
    private List<RelBusGoodsAndBusGoodsAttrSelect> selectList;

    /**
     * 上级商品id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 单品（子商品）
     */
    @OneToMany
    @JoinColumn(name = "parent_id",referencedColumnName = "id",insertable = false,updatable = false)
    @Column(name = "children")
    private List<BusGoods> children;

    /**
     * 父级商品
     */
    @OneToOne
    @JoinColumn(name = "parent_id",referencedColumnName = "id",insertable = false,updatable = false)
    private BusGoods parent;

    /**
     * 删除父级商品的子商品
     */
    public BusGoods getParent() {
        if (this.parent != null) {
            this.parent.setChildren(null);
        }
        return this.parent;
    }


}
