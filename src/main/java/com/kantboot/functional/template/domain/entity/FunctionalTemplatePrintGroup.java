package com.kantboot.functional.template.domain.entity;

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

/**
 * 打印模板分组
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "functional_template_print_group")
@DynamicUpdate
@DynamicInsert
public class FunctionalTemplatePrintGroup implements Serializable {

    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 字典编码
     */
    @Column(name = "t_code",length = 64)
    private String code;


    @OneToMany
    @JoinColumn(name = "group_code",referencedColumnName = "t_code",insertable = false,updatable = false)
    private List<FunctionalTemplatePrint> list;

    /**
     * 分组名称
     */
    @Column(name = "t_name",length = 64)
    private String name;

    @CreatedDate
    @Column(name = "gmt_create")
    private Date gmtCreate;

    @LastModifiedDate
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 关联的列名
     * 按照优先级倒序
     */
    @OneToMany
    @JoinColumn(name = "group_code",referencedColumnName = "t_code",insertable = false,updatable = false)
    @OrderBy("priority desc")
    private List<FunctionalTemplatePrintGroupColumn> columnList;

    /**
     * 关联的变量
     */
    @OneToMany
    @JoinColumn(name = "group_code",referencedColumnName = "t_code",insertable = false,updatable = false)
    @OrderBy("priority desc")
    private List<FunctionalTemplatePrintGroupVariable> variableList;

}
