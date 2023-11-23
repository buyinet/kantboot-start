package com.kantboot.business.goods.service.impl;

import com.kantboot.business.goods.dao.repository.BusGoodsWarehouseRepository;
import com.kantboot.business.goods.domain.entity.BusGoodsWarehouse;
import com.kantboot.business.goods.service.IBusGoodsWarehouseService;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class BusGoodsWarehouseImpl implements IBusGoodsWarehouseService
{

    @Resource
    private BusGoodsWarehouseRepository repository;

    @Override
    public PageResult getBodyData(PageParam<BusGoodsWarehouse> param) {
        Page<BusGoodsWarehouse> bodyData = repository.getBodyData(param.getData(), param.getPageable());
        return PageResult.of(bodyData);
    }
}
