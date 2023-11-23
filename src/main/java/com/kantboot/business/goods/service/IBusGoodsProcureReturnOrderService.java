package com.kantboot.business.goods.service;

import com.kantboot.business.goods.domain.dto.BusGoodsProcureOrderSearchDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsProcureReturnOrder;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;

/**
 * 商品采购单Service
 * @author 方某方
 */
public interface IBusGoodsProcureReturnOrderService {

    /**
     * 获取商品采购单
     * @param pageParam 分页参数
     * @return 商品采购单
     */
    PageResult getBodyData(PageParam<BusGoodsProcureOrderSearchDTO> pageParam);

    /**
     * 撤销
     */
    void revoke(Long id);

    /**
     * 保存
     * @param entity 实体
     * @return 添加后的实体
     */
    BusGoodsProcureReturnOrder save(BusGoodsProcureReturnOrder entity);

}
