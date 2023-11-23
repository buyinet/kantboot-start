package com.kantboot.system.dict.dao.repository;

import com.kantboot.system.dict.domain.entity.SysDictGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 字典分组数据访问层接口
 * @author 方某方
 */
public interface SysDictGroupRepository extends JpaRepository<SysDictGroup, String> {

    /**
     * 根据编码查询
     * @param code 编码
     * @return 字典分组
     */
    SysDictGroup findByCode(String code);


}
