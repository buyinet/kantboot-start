package com.kantboot.business.goods.dao.repository;

import com.kantboot.business.goods.domain.entity.BusGoodsProcureOrderIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BusGoodsProcureOrderInRepository extends JpaRepository<BusGoodsProcureOrderIn, Long> {

    /**
     * 根据采购单ID获取采购单明细
     * @param procureOrderId 采购单ID
     * @return 采购单明细
     */
    List<BusGoodsProcureOrderIn> findByProcureOrderId(Long procureOrderId);

}
