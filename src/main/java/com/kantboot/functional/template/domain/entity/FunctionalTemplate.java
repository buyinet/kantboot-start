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

/**
 * 功能模板
 * 用于管理功能模板
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "functional_template")
@DynamicUpdate
@DynamicInsert
public class FunctionalTemplate implements Serializable {

    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 分组编码
     */
    @Column(name = "group_code")
    private String groupCode;

    /**
     * 模板编码
     */
    @Column(name = "t_code")
    private String code;

    /**
     * 模板名称
     */
    @Column(name = "t_name")
    private String name;

    /**
     * 模板内容
     * 可以存放html，length设置为最大
     */
    @Column(name = "t_content",columnDefinition="Text")
    private String content;

    /**
     * 请求参数示例
     */
    @Column(name = "request_param_example",columnDefinition="Text")
    private String requestParamExample;


    @CreatedDate
    @Column(name = "gmt_create")
    private Date gmtCreate;

    @LastModifiedDate
    @Column(name = "gmt_modified")
    private Date gmtModified;

}
