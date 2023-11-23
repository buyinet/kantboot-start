package com.kantboot.business.goods.service;

import com.kantboot.business.goods.domain.entity.BusGoodsBusiness;

public interface IBusGoodsBusinessService {

    /**
     * 根据编码获取业务
     * @param code 编码
     * @return 业务
     */
    BusGoodsBusiness getByCode(String code);

}
