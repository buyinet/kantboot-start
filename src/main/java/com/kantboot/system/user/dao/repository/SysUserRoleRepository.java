package com.kantboot.system.user.dao.repository;

import com.kantboot.system.user.domain.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 用户角色数据访问接口
 * @author 方某方
 */
public interface SysUserRoleRepository extends JpaRepository<SysUserRole, Long>
{
//
//    /**
//     * 通过userId查询用户角色列表
//     * 必须sys_user_role和sys_role两张表的visible字段都为true，才给visible字段赋值为true，否则为false
//     * 并且根据角色的优先级进行倒序
//     * @return 角色列表
//     */
//    @Query("""
//            SELECT NEW com.kantboot.system.user.domain.vo.SysUserRoleVO(
//                ur.roleCode,
//                CASE WHEN ur.visible = true AND r.visible = true THEN true ELSE false END
//            )
//            FROM SysUserRole ur
//            LEFT JOIN SysRole r ON ur.roleCode = r.code
//            WHERE ur.userId = :userId
//            ORDER BY r.priority DESC
//            """)
//    List<SysUserRoleVO> findByUserId(@Param("userId") Long userId);
//
//    /**
//     * 通过userId查询可见的用户角色列表
//     * 必须sys_user_role和sys_role两张表的visible字段都为true，才能查询到
//     * 通过LEFT JOIN关联sys_role表，查询visible字段为true的角色，
//     * 并且根据角色的优先级进行倒序
//     * @return 角色列表
//     */
//    @Query("""
//        SELECT NEW com.kantboot.system.user.domain.vo.SysUserRoleVO(
//            ur.roleCode,ur.visible
//        ) FROM SysUserRole ur
//        LEFT JOIN
//            SysRole r
//        ON
//            ur.roleCode = r.code
//        WHERE
//            ur.userId = :userId AND ur.visible = true AND r.visible = true
//        ORDER BY r.priority DESC
//        """)
//    List<SysUserRoleVO> findVisibleByUserId(@Param("userId") Long userId);


    @Query("""
        SELECT ur FROM SysUserRole ur
        WHERE ur.userId = :userId
        AND ur.roleCode != 'admin'
        """)
    List<SysUserRole> findNotAdminByUserId(Long userId);
}
