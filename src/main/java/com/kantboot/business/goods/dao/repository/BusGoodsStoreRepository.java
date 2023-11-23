package com.kantboot.business.goods.dao.repository;

import com.kantboot.business.goods.domain.entity.BusGoodsStore;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusGoodsStoreRepository
extends JpaRepository<BusGoodsStore, Long> {

    /**
     * 根据名称或地址查询
     * @param name 名称
     * @param address 地址
     * @param pageable 分页参数
     * @return 分页数据
     */
    @Query("""
            select t from BusGoodsStore t where
            (:name is null or t.name like CONCAT('%',:name,'%') or :name = '') and
            (:address is null or t.address like CONCAT('%',:address,'%') or :address = '') 
            order by t.id desc
            """)
    Page<BusGoodsStore> findByNameOrAddress(
            @Param("name") String name,
            @Param("address") String address,
            Pageable pageable);

}
