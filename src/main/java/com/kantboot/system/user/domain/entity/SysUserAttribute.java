package com.kantboot.system.user.domain.entity;

import com.kantboot.util.core.jpa.KantbootGenerationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;

/**
 * 系统用户扩展实体类
 * 用于扩展系统用户实体类，如增加用户的其他属性
 * @author 方某方
 */
@Entity
@Getter
@Setter
@Table(name = "sys_user_attribute")
@Accessors(chain = true)
public class SysUserAttribute implements Serializable {

    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 属性编码
     */
    @Column(name = "t_code")
    private String code;

    /**
     * 属性值
     */
    @Column(name = "t_value")
    private String value;


    @OneToMany
    @JoinColumn(name = "user_attribute_id", referencedColumnName = "id")
    private List<SysUserAttributeDetail> detailList;

}
