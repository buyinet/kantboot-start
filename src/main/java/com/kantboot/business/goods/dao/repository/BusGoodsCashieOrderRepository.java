package com.kantboot.business.goods.dao.repository;

import com.kantboot.business.goods.domain.dto.BusGoodsCashieOrderSearchDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsCashieOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusGoodsCashieOrderRepository extends JpaRepository<BusGoodsCashieOrder,Long> {


    @Query("""
            from BusGoodsCashieOrder t
            where 1=1
            and (:#{#param.gmtCreateStart} is null or t.gmtCreate >= :#{#param.gmtCreateStart})
            and (:#{#param.gmtCreateEnd} is null or t.gmtCreate <= :#{#param.gmtCreateEnd})
            and (:#{#param.statusCode} is null or :#{#param.statusCode} = '' or t.statusCode = :#{#param.statusCode})
            and (:#{#param.order} is null or :#{#param.order} = '' or t.order like concat('%', :#{#param.order}, '%'))
            and (:#{#param.remark} is null or :#{#param.remark} = '' or t.remark like concat('%', :#{#param.remark}, '%'))
            and (:#{#param.warehouseId} is null or t.warehouseId = :#{#param.warehouseId})
            and (:#{#param.empId} is null or t.empId = :#{#param.empId})
            order by t.id desc
    """)
    Page<BusGoodsCashieOrder> getBodyData(
            @Param("param") BusGoodsCashieOrderSearchDTO param,
            Pageable pageable);

}
