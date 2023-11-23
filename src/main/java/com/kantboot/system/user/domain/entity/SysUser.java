package com.kantboot.system.user.domain.entity;

import com.kantboot.util.core.jpa.KantbootGenerationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户实体类
 * 为了防止后期做分库库表，所以不用manyToMany关联角色表
 * 在VO中，使用List<String> roleCodeList来关联角色表
 * @author 方某方
 */
@Entity
@Getter
@Setter
@Table(name = "sys_user")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public class SysUser implements Serializable {

    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 用户账号
     */
    @Column(name = "username", length = 64)
    private String username;

    /**
     * 密码
     * 此处非明文密码，而是加密后的密码
     * 在我的kantboot中，当然是用了我自己的KantbootPassword方式加密
     */
    @Column(name = "password")
    private String password;

    /**
     * 昵称
     */
    @Column(name = "nickname", length = 64)
    private String nickname;

    /**
     * 头像的文件id
     */
    private Long fileIdOfAvatar;

    /**
     * 用户自我介绍
     */
    @Column(name = "introduction", length = 1024)
    private String introduction;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "gmt_modified")
    private Date gmtModified;

    @OneToMany
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private List<SysUserRole> roleList;

    /**
     * 属性
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<SysUserAttribute> attributeList;

    /**
     * 关联在线表
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "user_id")
    private SysUserOnline online;

}
