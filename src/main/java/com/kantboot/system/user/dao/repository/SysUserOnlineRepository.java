package com.kantboot.system.user.dao.repository;

import com.kantboot.system.user.domain.entity.SysUserOnline;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户在线DAO
 * @author 方某方
 */
public interface SysUserOnlineRepository extends JpaRepository<SysUserOnline,Long> {
}
