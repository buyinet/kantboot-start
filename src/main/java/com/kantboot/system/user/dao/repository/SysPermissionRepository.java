package com.kantboot.system.user.dao.repository;

import com.kantboot.system.user.domain.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysPermissionRepository
extends JpaRepository<SysPermission,Long>
{

    /**
     * 根据权限组编码获取权限
     * @param code 权限编码
     * @return 权限组
     */
    SysPermission getByCode(String code);

    /**
     * 查询parentCode为空的权限
     */
    @Query("""
        FROM SysPermission p
        WHERE p.parentCode IS NULL OR p.parentCode = ''
    """)
    List<SysPermission> getAllTop();

}
