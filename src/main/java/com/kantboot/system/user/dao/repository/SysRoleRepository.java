package com.kantboot.system.user.dao.repository;

import com.kantboot.system.user.domain.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 角色数据访问接口
 * @author 方某方
 */
public interface SysRoleRepository extends JpaRepository<SysRole,Long>
{

    /**
     * 查询所有角色编码
     * @return 角色编码列表
     */
    @Query("SELECT code FROM SysRole")
    List<String> findAllCode();

    /**
     * 根据角色编码获取角色
     * @param code 角色编码
     * @return 角色
     */
    SysRole findByCode(String code);


}
