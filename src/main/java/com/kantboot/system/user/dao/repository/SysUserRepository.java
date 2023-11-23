package com.kantboot.system.user.dao.repository;

import com.kantboot.system.user.domain.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户DAO
 * @author 方某方
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    SysUser findByUsername(String username);

}
