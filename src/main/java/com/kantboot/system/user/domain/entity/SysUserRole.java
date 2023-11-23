package com.kantboot.system.user.domain.entity;

import com.kantboot.util.core.jpa.KantbootGenerationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户角色关联实体类
 * @author 方某方
 */
@Entity
@Getter
@Setter
@Table(name = "sys_user_role")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public class SysUserRole implements Serializable {

    /**
     * 主键
     */
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
     * 角色编码
     * 使用code，而不是id，是为了避免角色删除后，用户角色关联表中的角色id失效
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * 是否可见，默认可见
     */
    @Column(name = "visible")
    private Boolean visible;

    /**
     * 创建时间
     */
    @CreatedBy
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "gmt_modified")
    private Date gmtModified;
}