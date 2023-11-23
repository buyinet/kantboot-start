package com.kantboot.business.goods.dao.repository;

import com.kantboot.business.goods.domain.dto.BusGoodsSearchDTO;
import com.kantboot.business.goods.domain.entity.BusGoods;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 商品数据访问接口
 * @author 方某方
 */
public interface BusGoodsRepository extends JpaRepository<BusGoods, Long> {

    /**
     * 根据商品编号和商品名称查询商品
     * @param dto 查询条件
     * @param pageable 分页参数
     * @return 商品分页数据
     */
    @Query("""
   FROM BusGoods t
   WHERE
   1 = 1
    AND (:#{#dto.id} IS NULL OR t.id = :#{#dto.id})
    AND (:#{#dto.code} IS NULL OR t.code LIKE CONCAT('%', :#{#dto.code}, '%'))
    AND (:#{#dto.name} IS NULL OR t.name LIKE CONCAT('%', :#{#dto.name}, '%'))
    AND (:#{#dto.numberStr} IS NULL OR t.numberStr LIKE CONCAT('%', :#{#dto.numberStr}, '%'))
    AND (:#{#dto.minPrice} IS NULL OR t.price >= :#{#dto.minPrice})
    AND (:#{#dto.maxPrice} IS NULL OR t.price <= :#{#dto.maxPrice})
    AND (:#{#dto.barCode} IS NULL OR t.barCode LIKE CONCAT('%', :#{#dto.barCode}, '%'))
    AND (:#{#dto.typeId} IS NULL OR t.typeId = :#{#dto.typeId} OR t.typeId IN :#{#dto.typeIdList})
    AND (:#{#dto.priority} IS NULL OR t.priority = :#{#dto.priority})
    AND (:#{#dto.minPurchasePrice} IS NULL OR t.purchasePrice >= :#{#dto.minPurchasePrice})
    AND (:#{#dto.maxPurchasePrice} IS NULL OR t.purchasePrice <= :#{#dto.maxPurchasePrice})
    AND (:#{#dto.minWholesalePrice} IS NULL OR t.wholesalePrice >= :#{#dto.minWholesalePrice})
    AND (:#{#dto.maxWholesalePrice} IS NULL OR t.wholesalePrice <= :#{#dto.maxWholesalePrice})
    AND (:#{#dto.minRetailPrice} IS NULL OR t.retailPrice >= :#{#dto.minRetailPrice})
    AND (:#{#dto.maxRetailPrice} IS NULL OR t.retailPrice <= :#{#dto.maxRetailPrice})
    AND (:#{#dto.minMinOrder} IS NULL OR t.minOrder >= :#{#dto.minMinOrder})
    AND (:#{#dto.maxMinOrder} IS NULL OR t.minOrder <= :#{#dto.maxMinOrder})
    AND t.parentId IS NULL
   ORDER BY t.id DESC
""")
    Page<BusGoods> getBodyDataTop(
            @Param("dto") BusGoodsSearchDTO dto,
            Pageable pageable);


    /**
     * 根据商品编号和商品名称查询商品
     * @param dto 查询条件
     * @param pageable 分页参数
     * @return 商品分页数据
     */
    @Query("""
   FROM BusGoods t
   WHERE
   1 = 1
    AND (:#{#dto.id} IS NULL OR t.id = :#{#dto.id})
    AND (:#{#dto.code} IS NULL OR t.code LIKE CONCAT('%', :#{#dto.code}, '%'))
    AND (:#{#dto.name} IS NULL OR t.name LIKE CONCAT('%', :#{#dto.name}, '%'))
    AND (:#{#dto.numberStr} IS NULL OR t.numberStr LIKE CONCAT('%', :#{#dto.numberStr}, '%'))
    AND (:#{#dto.minPrice} IS NULL OR t.price >= :#{#dto.minPrice})
    AND (:#{#dto.maxPrice} IS NULL OR t.price <= :#{#dto.maxPrice})
    AND (:#{#dto.barCode} IS NULL OR t.barCode LIKE CONCAT('%', :#{#dto.barCode}, '%'))
    AND (:#{#dto.typeId} IS NULL OR t.typeId = :#{#dto.typeId} OR t.typeId IN :#{#dto.typeIdList})
    AND (:#{#dto.priority} IS NULL OR t.priority = :#{#dto.priority})
    AND (:#{#dto.minPurchasePrice} IS NULL OR t.purchasePrice >= :#{#dto.minPurchasePrice})
    AND (:#{#dto.maxPurchasePrice} IS NULL OR t.purchasePrice <= :#{#dto.maxPurchasePrice})
    AND (:#{#dto.minWholesalePrice} IS NULL OR t.wholesalePrice >= :#{#dto.minWholesalePrice})
    AND (:#{#dto.maxWholesalePrice} IS NULL OR t.wholesalePrice <= :#{#dto.maxWholesalePrice})
    AND (:#{#dto.minRetailPrice} IS NULL OR t.retailPrice >= :#{#dto.minRetailPrice})
    AND (:#{#dto.maxRetailPrice} IS NULL OR t.retailPrice <= :#{#dto.maxRetailPrice})
    AND (:#{#dto.minMinOrder} IS NULL OR t.minOrder >= :#{#dto.minMinOrder})
    AND (:#{#dto.maxMinOrder} IS NULL OR t.minOrder <= :#{#dto.maxMinOrder})
   ORDER BY t.id DESC
""")
    Page<BusGoods> getBodyData(
            @Param("dto") BusGoodsSearchDTO dto,
            Pageable pageable);

    /**
     * 根据商品编号查询商品
     * @param numberStr 商品编号
     * @return 商品数量
     */
    List<BusGoods> findByNumberStr(String numberStr);

    @Query("""
    FROM BusGoods t
    WHERE
    t.id IN (:ids)
    """)
    List<BusGoods> findByIds(@Param("ids") List<Long> ids);

    /**
     * 根据synthesis参数查询
     * @param synthesis 商品货号、名称或条形码
     * @return 商品数组
     */
    @Query("""
    FROM BusGoods t
    WHERE
    :synthesis IS NULL OR :synthesis = ''
    OR t.numberStr LIKE CONCAT('%', :synthesis, '%')
    OR t.name LIKE CONCAT('%', :synthesis, '%')
    OR t.barCode LIKE CONCAT('%', :synthesis, '%')
    """)
    List<BusGoods> findBySynthesis(@Param("synthesis") String synthesis);

}
