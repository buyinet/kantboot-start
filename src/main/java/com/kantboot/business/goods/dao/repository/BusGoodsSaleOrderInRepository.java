package com.kantboot.business.goods.dao.repository;

import com.kantboot.business.goods.domain.entity.BusGoodsSaleOrderIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BusGoodsSaleOrderInRepository extends JpaRepository<BusGoodsSaleOrderIn, Long> {

    /**
     * 根据采购单ID获取采购单明细
     * @param procureOrderId 采购单ID
     * @return 采购单明细
     */
    List<BusGoodsSaleOrderIn> findByOrderId(Long procureOrderId);

}
