package com.kantboot.business.goods.service.impl;

import com.kantboot.business.goods.dao.repository.BusGoodsBusinessRepository;
import com.kantboot.business.goods.domain.entity.BusGoodsBusiness;
import com.kantboot.business.goods.service.IBusGoodsBusinessService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class BusGoodsBusinessServiceImpl
implements IBusGoodsBusinessService
{

    @Resource
    private BusGoodsBusinessRepository repository;

    @Override
    public BusGoodsBusiness getByCode(String code) {
        return repository.findByCode(code);
    }
}
