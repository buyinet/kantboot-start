package com.kantboot.business.goods.dao.repository;

import com.kantboot.business.goods.domain.entity.BusGoodsWarehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusGoodsWarehouseRepository extends JpaRepository<BusGoodsWarehouse,Long> {


    @Query("""
    FROM BusGoodsWarehouse t WHERE 
    (:#{#param.name} IS NULL OR :#{#param.name} = '' OR t.name like CONCAT('%',:#{#param.name},'%') )
    ORDER BY t.id DESC
    """)
    Page<BusGoodsWarehouse> getBodyData(
            @Param("param") BusGoodsWarehouse busGoodsWarehouse,
            Pageable pageable);

}
