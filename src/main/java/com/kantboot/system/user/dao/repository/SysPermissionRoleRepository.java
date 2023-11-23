package com.kantboot.system.user.dao.repository;

import com.kantboot.system.user.domain.entity.SysPermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysPermissionRoleRepository extends JpaRepository<SysPermissionRole,Long> {

    /**
     * 根据角色编码获取
     * @param roleCode 角色编码
     * @return 权限角色关联
     */
    List<SysPermissionRole> getByRoleCode(String roleCode);

    /**
     * 根据角色列表获取
     * @param roleCodeList 角色编码列表
     * @return 权限角色关联
     */
    @Query("""
        FROM SysPermissionRole pr
        WHERE pr.roleCode IN :roleCodeList
    """)
    List<SysPermissionRole> getByRoleCodeIn(List<String> roleCodeList);

}
