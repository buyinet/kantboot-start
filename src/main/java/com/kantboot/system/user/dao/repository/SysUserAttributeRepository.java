package com.kantboot.system.user.dao.repository;

import com.kantboot.system.user.domain.entity.SysUserAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserAttributeRepository extends JpaRepository<SysUserAttribute, Long> {
}