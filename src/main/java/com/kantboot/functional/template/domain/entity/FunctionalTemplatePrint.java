package com.kantboot.functional.template.domain.entity;

import com.kantboot.util.core.jpa.KantbootGenerationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.List;

/**
 * 可选择的打印模板
 * 用于管理功能模板
 * @author 方某方
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "functional_template_print")
@DynamicUpdate
@DynamicInsert
public class FunctionalTemplatePrint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 模板分组编码
     */
    @Column(name = "template_group_code",length = 64)
    private String templateGroupCode;


    /**
     * 模板编码
     */
    @Column(name = "template_code",length = 64)
    private String templateCode;


    @Column(name="group_code",length = 64)
    private String groupCode;


    /**
     * 宽
     */
    @Column(name = "width")
    private Integer width;

    /**
     * 高
     */
    @Column(name = "height")
    private Integer height;

    /**
     * 名称
     */
    @Column(name = "t_name",length = 64)
    private String name;

    /**
     * 字典编码
     */
    @Column(name = "dict_code")
    private String dictCode;


    /**
     * 关联的列名
     */
    @OneToMany
    @JoinColumn(name = "print_id",referencedColumnName = "id",insertable = false,updatable = false)
    @OrderBy("priority desc")
    private List<FunctionalTemplatePrintColumn> columnList;

    /**
     * 关联的变量
     */
    @OneToMany
    @JoinColumn(name = "print_id",referencedColumnName = "id",insertable = false,updatable = false)
    private List<FunctionalTemplatePrintVariable> variableList;

}
