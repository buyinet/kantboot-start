package com.kantboot.business.goods.dao.repository;

import com.kantboot.business.goods.domain.entity.BusEmp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusEmpRepository extends JpaRepository<BusEmp, Long>
{

    /**
     * 根据账号和姓名查询
     */
    @Query("""
    FROM BusEmp t
    WHERE
    1=1
        AND (:username IS NULL OR t.username LIKE CONCAT('%',:username,'%') OR :username = '')
        AND (:name IS NULL OR t.name LIKE CONCAT('%',:name,'%') OR :name = '')
        AND (:goodsStoreId IS NULL OR t.goodsStoreId = :goodsStoreId)
    """)
    Page<BusEmp> findByUsernameAndNameAndGoodsStoreId(String username, String name,
            Long goodsStoreId
            , Pageable pageable);

    /**
     * 根据userId查询
     */
    BusEmp getByUserId(Long userId);

}
