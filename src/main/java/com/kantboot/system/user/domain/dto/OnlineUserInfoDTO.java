package com.kantboot.system.user.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 在线用户dto
 * 用来向别人展示自己的在线状态
 * 所以有一些非真实的字段
 * 如gmtLastLeave，比如这回隐身的时候，就是上一次离开的时间
 * @author 方某方
 */
@Data
@Accessors(chain = true)
public class OnlineUserInfoDTO implements Serializable {

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
    private String onlineStatusCode;

    /**
     * 最后一次进入时间
     */
    private Long gmtLastEnter;

    /**
     * 最后一次离线时间
     */
    private Long gmtLastLeave;


}
