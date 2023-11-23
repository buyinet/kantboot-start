package com.kantboot.system.dict.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典组实体类
 * 用于存储字典组的编码和名称
 * @author 方某方
 */
@Entity
@Table(name="sys_dict_group")
@Getter
@Setter
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public class SysDictGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 字典组编码
     */
    @Column(name = "t_code",length = 64)
    private String code;

    /**
     * 字典组名称
     */
    @Column(name = "t_name",length = 64)
    private String name;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 排序
     */
    @Column(name = "t_sort")
    private Integer sort;

    /**
     * 是否启用
     */
    @Column(name = "t_enable")
    private Boolean enable;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name="gmt_create")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name="gmt_modified")
    private Date gmtModified;

}
