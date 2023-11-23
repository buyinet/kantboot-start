package com.kantboot.business.goods.service;

import com.kantboot.business.goods.domain.dto.BusGoodsCashieOrderSearchDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsCashieOrder;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;

/**
 * 商品采购单Service
 * @author 方某方
 */
public interface IBusGoodsCashieOrderService {

    /**
     * 保存
     */
    BusGoodsCashieOrder save(BusGoodsCashieOrder entity);

    /**
     * 撤销
     */
    void revoke(Long id);

    PageResult getBodyData(PageParam<BusGoodsCashieOrderSearchDTO> pageParam);

}
