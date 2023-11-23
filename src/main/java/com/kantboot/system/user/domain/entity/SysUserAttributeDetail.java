package com.kantboot.system.user.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "sys_user_attribute_detail")
@Accessors(chain = true)
public class SysUserAttributeDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_attribute_id")
    private Long userAttributeId;

    @Column(name = "t_value")
    private String value;
}
