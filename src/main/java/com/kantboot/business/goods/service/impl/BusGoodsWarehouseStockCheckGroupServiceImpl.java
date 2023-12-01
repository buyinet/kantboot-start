package com.kantboot.business.goods.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.kantboot.business.goods.dao.repository.BusGoodsWarehouseStockCheckGroupRepository;
import com.kantboot.business.goods.dao.repository.BusGoodsWarehouseStockCheckRepository;
import com.kantboot.business.goods.domain.dto.BusGoodsWarehouseChangeDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsWarehouseDetail;
import com.kantboot.business.goods.domain.entity.BusGoodsWarehouseStockCheck;
import com.kantboot.business.goods.domain.entity.BusGoodsWarehouseStockCheckGroup;
import com.kantboot.business.goods.service.IBusGoodsWarehouseStockCheckGroupService;
import com.kantboot.business.goods.service.IBusGoodsWarehouseStockService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BusGoodsWarehouseStockCheckGroupServiceImpl implements IBusGoodsWarehouseStockCheckGroupService
{

    @Resource
    private IBusGoodsWarehouseStockService stockService;

    @Resource
    private BusGoodsWarehouseStockCheckGroupRepository repository;

    @Resource
    private BusGoodsWarehouseStockCheckRepository checkRepository;

    /**
     * 生成盘点单
     */
    @Override
    public void save(BusGoodsWarehouseStockCheckGroup group) {
        BusGoodsWarehouseStockCheckGroup group1 = BeanUtil.copyProperties(group, BusGoodsWarehouseStockCheckGroup.class);
        List<BusGoodsWarehouseStockCheck> inList = group.getInList();
        group.setInList(null);
        // 生成盘点单号
        group.setBusinessOrder("check-" + UUID.randomUUID().toString());
        // 保存盘点单
        BusGoodsWarehouseStockCheckGroup save = repository.save(group1);
        // 保存盘点单明细
        List<BusGoodsWarehouseChangeDTO> list = new ArrayList<>();
        // 保存盘点单明细
        List<BusGoodsWarehouseStockCheck> inList1 = new ArrayList<>();
        for (BusGoodsWarehouseStockCheck busGoodsWarehouseStockCheck : inList) {
            busGoodsWarehouseStockCheck.setGroupId(save.getId());
            busGoodsWarehouseStockCheck.setBusinessOrder(save.getBusinessOrder());
            busGoodsWarehouseStockCheck.setWarehouseId(save.getWarehouseId());
            busGoodsWarehouseStockCheck.setRemark(save.getRemark());
            BusGoodsWarehouseChangeDTO busGoodsWarehouseChangeDTO = new BusGoodsWarehouseChangeDTO();
            busGoodsWarehouseChangeDTO.setGoodsId(busGoodsWarehouseStockCheck.getGoodsId());
            busGoodsWarehouseChangeDTO.setWarehouseId(busGoodsWarehouseStockCheck.getWarehouseId());
            busGoodsWarehouseChangeDTO.setStockChange(busGoodsWarehouseStockCheck.getStockChange());
            busGoodsWarehouseChangeDTO.setRemark(busGoodsWarehouseStockCheck.getRemark());
            busGoodsWarehouseChangeDTO.setBusinessCode("check");
            busGoodsWarehouseChangeDTO.setBusinessOrder(save.getBusinessOrder());
            inList1.add(busGoodsWarehouseStockCheck);
            list.add(busGoodsWarehouseChangeDTO);
        }
        List<BusGoodsWarehouseDetail> busGoodsWarehouseDetails = stockService.batchStockChange(list);
        for (BusGoodsWarehouseDetail busGoodsWarehouseDetail : busGoodsWarehouseDetails) {
            inList1.stream().filter(item -> item.getGoodsId().equals(busGoodsWarehouseDetail.getGoodsId())).findFirst().ifPresent(item -> {
                // 设置库存变化
                item.setStockChange(busGoodsWarehouseDetail.getStockChange());
                // 设置原库存
                item.setStockOld(busGoodsWarehouseDetail.getStockOld());
                // 设置新库存
                item.setStockNew(busGoodsWarehouseDetail.getStockNew());
            });
        }

        checkRepository.saveAll(inList1);
    }

}
