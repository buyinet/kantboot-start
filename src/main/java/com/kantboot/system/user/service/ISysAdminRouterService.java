package com.kantboot.system.user.service;

import com.kantboot.system.user.domain.entity.SysAdminRouter;

import java.util.List;

public interface ISysAdminRouterService {

    /**
     * 获取所有路由
     */
    List<SysAdminRouter> getAll();

    /**
     * 获取top路由
     */
    List<SysAdminRouter> getTop();

    /**
     * 获取自身的路由列表
     */
    List<SysAdminRouter> getSelf();

}
