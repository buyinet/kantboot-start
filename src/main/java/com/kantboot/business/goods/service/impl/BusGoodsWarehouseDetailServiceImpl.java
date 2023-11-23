package com.kantboot.business.goods.service.impl;

import com.kantboot.business.goods.dao.repository.BusGoodsWarehouseDetailRepository;
import com.kantboot.business.goods.domain.dto.BusGoodsWarehouseDetailSearchDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsWarehouseDetail;
import com.kantboot.business.goods.service.IBusGoodsWarehouseDetailService;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品仓库明细服务实现类
 * @author 方某方
 */
@Service
public class BusGoodsWarehouseDetailServiceImpl implements IBusGoodsWarehouseDetailService {

    @Resource
    private BusGoodsWarehouseDetailRepository repository;

    @Override
    public PageResult getBodyData(PageParam<BusGoodsWarehouseDetailSearchDTO> pageParam) {
        if (pageParam.getData().getWarehouseIds() != null && pageParam.getData().getWarehouseIds().size() == 0) {
            pageParam.getData().setWarehouseIds(null);
        }

        Page<BusGoodsWarehouseDetail> bodyData = repository.getBodyData(pageParam.getData(), pageParam.getPageable());
        return PageResult.of(bodyData);
    }

    @Override
    public void deleteByBusinessOrder(String businessOrder) {
        List<BusGoodsWarehouseDetail> allByBusinessOrder = repository.findAllByBusinessOrder(businessOrder);
        repository.deleteAll(allByBusinessOrder);
    }
}
