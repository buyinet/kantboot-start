package com.kantboot.system.user.dao.repository;

import com.kantboot.system.user.domain.entity.SysAdminRouter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysAdminRouterRepository extends JpaRepository<SysAdminRouter,Long> {

    /**
     * 查询父编码为空的路由
     */
    @Query("""
        FROM SysAdminRouter r
        WHERE r.parentCode IS NULL OR r.parentCode = ''
    """)
    List<SysAdminRouter> getTop();

    /**
     * 根据权限编码获取路由
     * 且父编码为空，如果子集路由的permissionCode也在permissionCodeList中，则也不会返回
     */
    @Query("""
        FROM SysAdminRouter r
        WHERE r.permissionCode IN (:permissionCodeList)
        AND (r.parentCode IS NULL OR r.parentCode = '')
    """)
    List<SysAdminRouter> getByPermissionCodeList(@Param("permissionCodeList") List<String> permissionCodeList);

}
