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

import java.util.Date;

/**
 * 打印模板列
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "functional_template_print_group_column")
@DynamicUpdate
@DynamicInsert
public class FunctionalTemplatePrintGroupColumn {

    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;


    /**
     * 关联选择的打印模板的code
     */
    @Column(name = "group_code",length = 64)
    private String groupCode;

    /**
     * 名称
     */
    @Column(name = "t_name")
    private String name;

    /**
     * 字典编码
     */
    @Column(name = "t_code")
    private String code;

    /**
     * 优先级
     */
    @Column(name = "priority")
    private Integer priority;



    @CreatedDate
    @Column(name = "gmt_create")
    private Date gmtCreate;

    @LastModifiedDate
    @Column(name = "gmt_modified")
    private Date gmtModified;

}
