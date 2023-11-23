package com.kantboot.business.goods.dao.repository;

import com.kantboot.business.goods.domain.dto.BusGoodsSupplierSearchDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsSupplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 供应商DAO
 * @author 方某方
 */
public interface BusGoodsSupplierRepository extends JpaRepository<BusGoodsSupplier,Long> {

    /**
     * 综合查询
     * @param param 查询参数
     * @param pageable 分页
     * @return 分页结果
     */
    @Query(
        """
        FROM BusGoodsSupplier t
        WHERE
            (:#{#param.synthesis} IS NULL
            OR :#{#param.synthesis} = ''
            OR t.name LIKE CONCAT('%',:#{#param.synthesis},'%')
            OR t.phone LIKE CONCAT('%',:#{#param.synthesis},'%'))
            AND (:#{#param.principalName} IS NULL
                OR :#{#param.principalName} = ''
                OR t.principalName LIKE CONCAT('%',:#{#param.principalName},'%'))
        ORDER BY t.id DESC
        """
    )
    Page<BusGoodsSupplier> getBodyData(@Param("param") BusGoodsSupplierSearchDTO param, Pageable pageable);

}
