package com.kantboot.system.user.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户在线信息实体类
 * 用于记录用户在线信息
 * @author 方某方
 */
@Entity
@Getter
@Setter
@Table(name = "sys_user_online")
@Accessors(chain = true)
@DynamicUpdate
public class SysUserOnline implements Serializable {

    /**
     * 在线状态码 离线
     */
    public final static String ONLINE_STATUS_CODE_OFFLINE="offline";

    /**
     * 在线状态码 在线
     */
    public final static String ONLINE_STATUS_CODE_ONLINE="online";

    /**
     * 在线状态码 忙碌
     */
    public final static String ONLINE_STATUS_CODE_BUSY="busy";

    /**
     * 在线状态码 离开
     */
    public final static String ONLINE_STATUS_CODE_LEAVE="leave";

    /**
     * 在线状态码 隐身
     */
    public final static String ONLINE_STATUS_CODE_INVISIBLE="invisible";

    /**
     * 在线状态码 隐藏
     */
    public final static String ONLINE_STATUS_CODE_HIDDEN="hidden";

    /**
     * 在线状态码 管理员强制离线
     */
    public final static String ONLINE_STATUS_CODE_OFFLINE_BY_ADMIN="offlineByAdmin";

    /**
     * 在线状态码 系统强制离线
     */
    public final static String ONLINE_STATUS_CODE_OFFLINE_BY_SYSTEM="offlineBySystem";

    /**
     * 在线状态码 未知
     */
    public final static String ONLINE_STATUS_CODE_UNKNOWN="unknown";


    /**
     * 主键
     */
    @Id
    @Column(name = "user_id")
    private Long userId;


    /**
     * 在线状态
     * offline 离线
     * online 在线
     * busy 忙碌
     * leave 离开
     * invisible 隐身
     * hidden 隐藏
     * offlineByAdmin 管理员强制离线
     * offlineBySystem 系统强制离线
     * unknown 未知
     */
    @Column(name = "online_status_code")
    private String onlineStatusCode;

    @Column(name = "online")
    private Boolean online;

    /**
     * 过期时间
     */
    @Column(name = "gmt_expire")
    private Long gmtExpire;

    /**
     * 最后一次进入时间
     */
    @Column(name = "gmt_last_enter")
    private Long gmtLastEnter;

    /**
     * 最后一次离线时间
     */
    @Column(name = "gmt_last_leave")
    private Long gmtLastLeave;

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

}
