package com.kantboot.business.goods.service.impl;

import com.kantboot.business.goods.dao.repository.BusGoodsStoreRepository;
import com.kantboot.business.goods.domain.entity.BusGoodsStore;
import com.kantboot.business.goods.service.IBusGoodsStoreService;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class BusGoodsStoreServiceImpl
implements IBusGoodsStoreService
{

    @Resource
    private BusGoodsStoreRepository repository;

    @Override
    public PageResult getBodyData(PageParam<BusGoodsStore> pageParam) {
        Page<BusGoodsStore> byNameOrAddress = repository.findByNameOrAddress(pageParam.getData().getName(), pageParam.getData().getAddress(), pageParam.getPageable());
        return PageResult.of(byNameOrAddress);
    }
}
