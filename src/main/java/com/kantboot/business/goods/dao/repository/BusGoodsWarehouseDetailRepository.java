package com.kantboot.business.goods.dao.repository;

import com.kantboot.business.goods.domain.dto.BusGoodsWarehouseDetailSearchDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsWarehouseDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusGoodsWarehouseDetailRepository extends
        JpaRepository<BusGoodsWarehouseDetail,Long> {

    @Query("""
            FROM BusGoodsWarehouseDetail t WHERE 
            (
              :#{#param.warehouseIds} IS NULL
              OR t.warehouseId IN (:#{#param.warehouseIds})
            )
            AND
            (
                :#{#param.goodsSynthesis} IS NULL
                OR :#{#param.goodsSynthesis} = ''                
                OR t.goods.name LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
                OR t.goods.numberStr LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
                OR t.goods.barCode LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
                OR t.goods.parent.name LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
                OR t.goods.parent.numberStr LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
                OR t.goods.parent.barCode LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
            )
            AND (:#{#param.gmtOperateStart} IS NULL OR t.gmtOperate >= :#{#param.gmtOperateStart} )
            AND (:#{#param.gmtOperateEnd} IS NULL OR t.gmtOperate <= :#{#param.gmtOperateEnd} )
            ORDER BY t.id DESC
            """)

    Page<BusGoodsWarehouseDetail> getBodyData(
            @Param("param") BusGoodsWarehouseDetailSearchDTO param, Pageable pageable);

    /**
     * 根据单号查询
     */
    List<BusGoodsWarehouseDetail> findAllByBusinessOrder(String businessOrder);


}
