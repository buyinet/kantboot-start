package com.kantboot.business.goods.service;

import com.kantboot.business.goods.domain.entity.BusGoodsStore;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;

public interface IBusGoodsStoreService {

    PageResult getBodyData(PageParam<BusGoodsStore> pageParam);

}
