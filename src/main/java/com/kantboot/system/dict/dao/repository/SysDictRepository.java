package com.kantboot.system.dict.dao.repository;

import com.kantboot.system.dict.domain.entity.SysDict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 字典数据访问层接口
 * @author 方某方
 */
public interface SysDictRepository extends JpaRepository<SysDict, Long> {

    /**
     * 根据分组编码查询字典列表
     * @param groupCode 分组编码
     * @return 字典列表
     */
    List<SysDict> findByGroupCode(String groupCode);

    /**
     * 根据分组编码和编码查询字典
     * @param groupCode 分组编码
     * @param code 编码
     * @return 字典
     */
    SysDict findByGroupCodeAndCode(String groupCode, String code);

    /**
     * 根据分组编码和编码模糊查询字典分页
     * 如果groupCode为空，则不根据分组编码查询
     * @param groupCode 分组编码
     * @param code 编码
     * @return 字典列表
     */
    @Query("SELECT d FROM SysDict d WHERE (:groupCode IS NULL OR d.groupCode = :groupCode) AND d.code LIKE %:code%")
    Page<SysDict> findByGroupCodeAndCodeLike(@Param("groupCode") String groupCode, @Param("code") String code, Pageable pageable);



}
