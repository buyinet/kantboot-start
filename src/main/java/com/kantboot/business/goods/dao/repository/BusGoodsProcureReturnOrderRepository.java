package com.kantboot.business.goods.dao.repository;

import com.kantboot.business.goods.domain.dto.BusGoodsProcureOrderSearchDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsProcureReturnOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusGoodsProcureReturnOrderRepository extends JpaRepository<BusGoodsProcureReturnOrder, Long> {

    @Query("""
            select t from BusGoodsProcureReturnOrder t
            where 1=1
            and (:#{#param.gmtCreateStart} is null or t.gmtCreate >= :#{#param.gmtCreateStart})
            and (:#{#param.gmtCreateEnd} is null or t.gmtCreate <= :#{#param.gmtCreateEnd})
            and (:#{#param.statusCodeList} is null or t.statusCode IN :#{#param.statusCodeList})
            and (:#{#param.supplierId} is null or t.supplierId = :#{#param.supplierId})
            and (:#{#param.order} is null or :#{#param.order} = '' or t.order like concat('%', :#{#param.order}, '%'))
            order by t.id desc
    """)
    Page<BusGoodsProcureReturnOrder> getBodyData(
            @Param("param") BusGoodsProcureOrderSearchDTO param,
            Pageable pageable);

}
