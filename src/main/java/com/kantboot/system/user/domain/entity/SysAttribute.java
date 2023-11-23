package com.kantboot.system.user.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "sys_attribute")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public class SysAttribute implements Serializable {

    /**
     * 属性编码
     * 用于区分不同的角色
     * 也对应着字典中的角色编码
     */
    @Id
    @Column(name = "t_code", length = 64)
    private String code;

    /**
     * 属性名称
     */
    @Column(name = "t_name")
    private String name;

    /**
     * 是否可见
     */
    @Column(name = "t_visible")
    private Boolean visible;

    /**
     * 属性描述
     */
    @Column(name = "t_description")
    private String description;

    /**
     * 属性优先级
     */
    @Column(name = "t_priority")
    private Integer priority;


}
