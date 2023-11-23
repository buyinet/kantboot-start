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
@Table(name = "bus_goods_type")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class BusGoodsType implements Serializable {

    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 分类编码
     */
    @Column(name = "t_code")
    private String code;

    /**
     * 商品类型名称
     */
    @Column(name = "t_name")
    private String name;

    /**
     * 父id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 父类型
     */
    @OneToOne
    @JoinColumn(name = "parent_id",referencedColumnName = "id",insertable = false,updatable = false)
    private BusGoodsType parent;

    /**
     * 子类型，删除时级联删除
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id",referencedColumnName = "id")
    private List<BusGoodsType> children;

    /**
     * 分类图片
     * @return 分类图片
     */
    @Column(name = "file_id_of_img")
    private Long fileIdOfImg;

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

    public BusGoodsType getParent() {
        if (parent == null) {
            return null;
        }
        parent.children = null;
        parent.parent = null;
        return parent;
    }
}
