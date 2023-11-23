package com.kantboot.business.goods.service;

import com.kantboot.business.goods.domain.dto.BusGoodsSupplierSearchDTO;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;

public interface IBusGoodsSupplierService {

    PageResult getBodyData(PageParam<BusGoodsSupplierSearchDTO> pageParam);

    /**
     * 借款
     */
    void borrow(Long id, Double amount);

    /**
     * 还款
     */
    void repay(Long id, Double amount);

}
