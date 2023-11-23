package com.kantboot.business.goods.dao.repository;

import com.kantboot.business.goods.domain.entity.BusGoodsDueToSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusGoodsDueToSupplierRepository extends JpaRepository<BusGoodsDueToSupplier, Long> {

    /**
     * 根据供应商id查询欠款
     */
    BusGoodsDueToSupplier findBySupplierId(Long supplierId);

    /**
     * 借款
     */
    @Query("""
            update BusGoodsDueToSupplier t
            set t.due = t.due + :#{#amount}
            where t.supplierId = :#{#supplierId}
    """)
    void borrow(@Param("supplierId") Long supplierId,@Param("amount") Double amount);

    /**
     * 还款
     */
    @Query("""
            update BusGoodsDueToSupplier t
            set t.due = t.due - :#{#amount}
            where t.supplierId = :#{#supplierId}
    """)
    void repay(@Param("supplierId") Long supplierId,@Param("amount") Double amount);

}
