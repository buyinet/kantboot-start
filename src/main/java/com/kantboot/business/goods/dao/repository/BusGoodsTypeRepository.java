package com.kantboot.business.goods.dao.repository;

import com.kantboot.business.goods.domain.entity.BusGoodsType;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * 商品类型数据访问接口
 * @author 方某方
 */
public interface BusGoodsTypeRepository extends JpaRepository<BusGoodsType, Long> {

    /**
     * 根据id和名称and条件查询，如果id为空则根据名称查询，如果名称为空则根据id查询
     * 倒序
     * @param id id
     * @param name 名称
     * @return 分页
     */

    @Query("""
            select t from BusGoodsType t where
            (:id is null or t.id = :id) and
            (:name is null or t.name like CONCAT('%',:name,'%') or :name = '') and
            (:code is null or t.code like CONCAT('%',:code,'%') or :code = '') 
            order by t.id desc
            """)
    Page<BusGoodsType> findByIdAndNameAndCode(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("code") String code,
            Pageable pageable);

    /**
     * 根据id和名称and条件查询，如果id为空则根据名称查询，如果名称为空则根据id查询
     * 且parent_id为null
     * 倒序
     * @param id id
     * @param name 名称
     * @return 分页
     */
    @Query("""
            select t from BusGoodsType t where
            (:id is null or t.id = :id) and 
            (:name is null or t.name like CONCAT('%',:name,'%') or :name = '') and
            (:code is null or t.code like CONCAT('%',:code,'%') or :code = '') and 
            t.parentId is null
            order by t.id desc
            """)
    Page<BusGoodsType> findByIdAndNameAndCodeAndParentIdIsNull(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("code") String code,
            Pageable pageable);

}
