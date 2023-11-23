package com.kantboot.system.user.domain.entity;

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
 * 权限组
 */
@Entity
@Getter
@Setter
@Table(name = "sys_permission")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class SysPermission implements Serializable
{

    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 权限组编码
     */
    @Column(name = "t_code", length = 64)
    private String code;

    /**
     * 字典编码
     */
    @Column(name = "dict_code", length = 64)
    private String dictCode;


    /**
     * 权限名称
     */
    @Column(name = "t_name")
    private String name;

    /**
     * 权限组父级
     */
    @Column(name = "parent_code", length = 64)
    private String parentCode;


    /**
     * 权限描述
     */
    @Column(name = "t_description")
    private String description;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 最后一次修改时间
     */
    @LastModifiedDate
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 子集
     */
    @OneToMany
    @JoinColumn(name = "parent_code", referencedColumnName = "t_code", insertable = false, updatable = false)
    private List<SysPermission> children;

    /**
     * 权限的uri
     */
    @Column(name = "uri", length = 1000)
    private String uri;
}
