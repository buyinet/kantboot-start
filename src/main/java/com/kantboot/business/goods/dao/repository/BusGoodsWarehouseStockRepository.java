package com.kantboot.business.goods.dao.repository;

import com.kantboot.business.goods.domain.dto.BusGoodsWarehouseStockDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsWarehouseStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusGoodsWarehouseStockRepository extends JpaRepository<BusGoodsWarehouseStock,Long> {

    /**
     * 仓库id集合查询库存
     */
    @Query("""
    FROM BusGoodsWarehouseStock t
    WHERE 
    (
      :#{#param.warehouseIds} IS NULL
      OR t.warehouseId IN (:#{#param.warehouseIds})
    )
    AND (
        :#{#param.goodsSynthesis} IS NULL
        OR :#{#param.goodsSynthesis} = ''
        OR t.goods.name LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
        OR t.goods.numberStr LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
        OR t.goods.barCode LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
        OR t.goods.parent.name LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
        OR t.goods.parent.numberStr LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
        OR t.goods.parent.barCode LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
    )
    ORDER BY t.id DESC
    """)
    Page<BusGoodsWarehouseStock> getBodyData(
            @Param("param")BusGoodsWarehouseStockDTO param,
            Pageable pageable
    );
    /**
     * 合并查询
     */
    @Query("""
        SELECT t.goods.id, SUM(t.stock) AS totalStock
        FROM BusGoodsWarehouseStock t
        WHERE 
            :#{#param.goodsSynthesis} IS NULL
            OR :#{#param.goodsSynthesis} = ''
            OR t.goods.name LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
            OR t.goods.numberStr LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
            OR t.goods.barCode LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
            OR t.goods.parent.name LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
            OR t.goods.parent.numberStr LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
            OR t.goods.parent.barCode LIKE CONCAT('%',:#{#param.goodsSynthesis},'%')
        GROUP BY t.goods.id
        """)
    Page<Object[]> getTotalStockPerProduct2(@Param("param") BusGoodsWarehouseStockDTO param, Pageable pageable);

    /**
     * 根据批量商品id查询库存
     */
    @Query("""
    FROM BusGoodsWarehouseStock t
    WHERE
    t.goods.id IN (:goodsIds)
    """)
    List<BusGoodsWarehouseStock> findByGoodsIds(@Param("goodsIds") List<Long> goodsIds);

    /**
     * 合并仓库的非单品查询
     * SELECT SUM(a.stock)
     * FROM bus_goods_warehouse_stock a
     * LEFT JOIN bus_goods b ON a.goods_id = b.id
     * GROUP BY
     *   CASE
     *     WHEN b.parent_id IS NOT NULL THEN b.parent_id
     *     ELSE b.id
     *   END;
     */
    @Query("""
    SELECT
      CASE
        WHEN b.parentId IS NOT NULL THEN b.parentId
        ELSE b.id
      END AS goodsId
    ,SUM(t.stock)
    FROM BusGoodsWarehouseStock t
    LEFT JOIN BusGoods b ON t.goods.id = b.id
    WHERE 
        :#{#param.goodsSynthesis} IS NULL
        OR :#{#param.goodsSynthesis} = ''
        OR (t.goods.parentId IS NULL AND t.goods.name LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NULL AND t.goods.numberStr LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NULL AND t.goods.barCode LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NOT NULL AND b.name LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NOT NULL AND b.numberStr LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NOT NULL AND b.barCode LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NOT NULL AND b.parent.name LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NOT NULL AND b.parent.numberStr LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NOT NULL AND b.parent.barCode LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
    GROUP BY
      CASE
        WHEN b.parentId IS NOT NULL THEN b.parentId
        ELSE b.id
      END
    ORDER BY goodsId DESC
    """)
    Page<Object[]> getTotalStockPerProduct3(@Param("param") BusGoodsWarehouseStockDTO param, Pageable pageable);

    /**
     * 非合并仓库的非单品查询
     * @param param
     * @param pageable
     * @return
     */
    @Query("""
    SELECT
      CASE
        WHEN b.parentId IS NOT NULL THEN b.parentId
        ELSE b.id
      END AS goodsId
    ,SUM(t.stock)
    ,t.warehouse
    FROM BusGoodsWarehouseStock t
    LEFT JOIN BusGoods b ON t.goods.id = b.id
    WHERE 
        (
      :#{#param.warehouseIds} IS NULL
      OR t.warehouseId IN (:#{#param.warehouseIds})
    ) AND (
        :#{#param.goodsSynthesis} IS NULL
        OR :#{#param.goodsSynthesis} = ''
        OR (t.goods.parentId IS NULL AND t.goods.name LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NULL AND t.goods.numberStr LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NULL AND t.goods.barCode LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NOT NULL AND b.name LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NOT NULL AND b.numberStr LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NOT NULL AND b.barCode LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NOT NULL AND b.parent.name LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NOT NULL AND b.parent.numberStr LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
        OR (t.goods.parentId IS NOT NULL AND b.parent.barCode LIKE CONCAT('%',:#{#param.goodsSynthesis},'%'))
    )
    GROUP BY
      CASE
        WHEN b.parentId IS NOT NULL THEN b.parentId
        ELSE b.id
      END, t.warehouseId
    ORDER BY goodsId DESC
    """)
    Page<Object[]> getTotalStockPerProduct4(@Param("param") BusGoodsWarehouseStockDTO param,Pageable pageable);

    /**
     * 根据商品id和仓库id查询库存
     * @param goodsId 商品id
     * @param warehouseId 仓库id
     * @return 库存
     */
    BusGoodsWarehouseStock findByGoodsIdAndWarehouseId(
            @Param("goodsId") Long goodsId,
            @Param("warehouseId") Long warehouseId);

    /**
     * 批量获取库存
     */
    @Query("""
    FROM BusGoodsWarehouseStock t
    WHERE
    t.goods.id IN (:goodsIds)
    AND t.warehouseId IN (:warehouseIds)
    """)
    List<BusGoodsWarehouseStock> findByGoodsIdsAndWarehouseIds(
            @Param("goodsIds") List<Long> goodsIds,
            @Param("warehouseIds") List<Long> warehouseIds);

    /**
     * 统计所有仓库的成本和库存
     */
    @Query("""
    SELECT
      t.warehouse,
      SUM(t.stock) AS totalStock,
    SUM(t.stock * t.goods.purchasePrice) AS totalCost
    FROM BusGoodsWarehouseStock t
    GROUP BY t.warehouseId
    """)
    List<Object[]> getTotalCostAndStockPerWarehouse();

    /**
     * 根据仓库id查询
     */
    List<BusGoodsWarehouseStock> findByWarehouseId(@Param("warehouseId") Long warehouseId);

}
