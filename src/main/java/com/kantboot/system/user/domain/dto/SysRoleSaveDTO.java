package com.kantboot.system.user.domain.dto;

import com.kantboot.system.user.domain.entity.SysPermissionRole;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysRoleSaveDTO implements java.io.Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 角色编码
     * 用于区分不同的角色
     * 也对应着字典中的角色编码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 是否可见
     */
    private Boolean visible;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 角色优先级
     */
    private Integer priority;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 最后一次修改时间
     */
    private Date gmtModified;

    private List<SysPermissionRole> permissionList;

    private List<String> permissionCodeList;
}
