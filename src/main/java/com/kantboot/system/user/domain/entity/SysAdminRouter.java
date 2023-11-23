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
 * 系统管理路由表
 * 用于前端动态路由
 * @author 方某方
 */
@Entity
@Getter
@Setter
@Table(name = "sys_admin_router")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class SysAdminRouter implements Serializable {

    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "snowflakeId",strategy = KantbootGenerationType.SNOWFLAKE)
    @GeneratedValue(generator = "snowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 路由标题
     */
    @Column(name = "t_title", length = 64)
    private String title;

    /**
     * 路由路径
     */
    @Column(name = "t_path", length = 64)
    private String path;

    /**
     * 路由组件路径
     */
    @Column(name = "t_component_path", length = 64)
    private String componentPath;

    /**
     * 路由优先级
     */
    @Column(name = "t_priority")
    private Integer priority;

    /**
     * 路由图标code
     */
    @Column(name = "file_code_of_icon", length = 64)
    private String fileCodeOfIcon;

    /**
     * 路由绑定的权限
     */
    @Column(name = "permission_code", length = 64)
    private String permissionCode;

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

    /**
     * 编码
     */
    @Column(name = "t_code", length = 64)
    private String code;

    /**
     * 父编码
     */
    @Column(name = "parent_code", length = 64)
    private String parentCode;

    /**
     * children
     */
    @OneToMany
    @JoinColumn(name = "parent_code", referencedColumnName = "t_code")
    @OrderBy("priority DESC")
    private List<SysAdminRouter> children;


}
