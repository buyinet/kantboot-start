package com.kantboot.business.goods.dao.repository;

import com.kantboot.business.goods.domain.entity.BusGoodsBusiness;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusGoodsBusinessRepository extends JpaRepository<BusGoodsBusiness,Long>
{

    /**
     * 根据业务编码查询业务
     * @param code 业务编码
     * @return 业务
     */
    BusGoodsBusiness findByCode(String code);

}
