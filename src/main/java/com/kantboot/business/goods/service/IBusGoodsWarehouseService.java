package com.kantboot.business.goods.service;

import com.kantboot.business.goods.domain.entity.BusGoodsWarehouse;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;

public interface IBusGoodsWarehouseService {

    PageResult getBodyData(PageParam<BusGoodsWarehouse> param);

}
