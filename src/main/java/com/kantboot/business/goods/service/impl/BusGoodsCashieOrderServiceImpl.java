package com.kantboot.business.goods.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.thread.ExecutorBuilder;
import com.kantboot.business.goods.dao.repository.BusGoodsCashieOrderInRepository;
import com.kantboot.business.goods.dao.repository.BusGoodsCashieOrderRepository;
import com.kantboot.business.goods.domain.dto.BusGoodsCashieOrderSearchDTO;
import com.kantboot.business.goods.domain.dto.BusGoodsWarehouseChangeDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsCashieOrder;
import com.kantboot.business.goods.domain.entity.BusGoodsCashieOrderIn;
import com.kantboot.business.goods.service.IBusGoodsCashieOrderService;
import com.kantboot.business.goods.service.IBusGoodsWarehouseDetailService;
import com.kantboot.business.goods.service.IBusGoodsWarehouseStockService;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 商品采购单Service实现类
 *
 * @author 方某方
 */
@Service
public class BusGoodsCashieOrderServiceImpl implements IBusGoodsCashieOrderService {

    @Resource
    private BusGoodsCashieOrderRepository repository;
    @Resource
    private BusGoodsCashieOrderInRepository inRepository;

    @Resource
    private IBusGoodsWarehouseStockService warehouseStockService;

    @Resource
    private IBusGoodsWarehouseDetailService warehouseDetailService;

    /**
     * huTool线程池
     */
    ExecutorService executor = ExecutorBuilder.create()
            .setCorePoolSize(5)
            .setMaxPoolSize(10)
            .setWorkQueue(new LinkedBlockingQueue<>(100))
            .build();

    /**
     * 保存
     *
     * @param entity 实体
     * @return 保存后的实体
     */
    @Override
    public BusGoodsCashieOrder save(BusGoodsCashieOrder entity) {
        // 取出inList
        List<BusGoodsCashieOrderIn> inList = BeanUtil.copyToList(entity.getInList(), BusGoodsCashieOrderIn.class);
        // 删除原有的inList
        entity.getInList().clear();
        // 设置状态为已完成
        entity.setStatusCode(BusGoodsCashieOrder.STATUS_CODE_COMPLETED);
        // 保存主表
        BusGoodsCashieOrder save = repository.save(entity);
        // 主表总价
        BigDecimal totalAmount = new BigDecimal(0);
        // 主表总数量
        Long totalNumber = 0L;

        String order = "cashie-order-" + save.getId();
        List<BusGoodsWarehouseChangeDTO> dtoList = new ArrayList<>();
        // 减少库存数量
        for (BusGoodsCashieOrderIn in : inList) {
            BusGoodsWarehouseChangeDTO dto = new BusGoodsWarehouseChangeDTO();
            dto.setGoodsId(in.getGoodsId());
            dto.setBusinessCode("cashie-order");
            dto.setWarehouseId(entity.getWarehouseId());
            dto.setStockChange(-in.getNumber());
            dto.setRemark("商品销售-前台收银");
            dto.setBusinessOrder(order);
            dtoList.add(dto);
        }
        warehouseStockService.batchStockChange(dtoList);

        // 保存子表
        for (BusGoodsCashieOrderIn in : inList) {
            in.setCashieOrderId(save.getId());
            // 零售价
            BigDecimal retailPrice = new BigDecimal(in.getGoods().getRetailPrice());
            // 总价
            BigDecimal multiply = new BigDecimal(in.getNumber()).multiply(retailPrice);
            // 设置总价
            in.setAmount(multiply.doubleValue());
            // 累加总价
            totalAmount = totalAmount.add(multiply);
            // 累加总数量
            totalNumber += in.getNumber();
        }
        inRepository.saveAll(inList);
        // 设置主表实收金额
        save.setRealAmount(entity.getAmount());

        // 设置主表总价
        // 打折
        totalAmount = totalAmount.multiply(new BigDecimal(entity.getDiscount()));
        // 抹零
        totalAmount = totalAmount.subtract(new BigDecimal(entity.getZeroAmount()));
        // 四舍五入，保持两位小数
        totalAmount = totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
        save.setAmount(totalAmount.doubleValue());

        // 设置主表总数量
        save.setNumber(totalNumber.longValue());

        // 设置主表订单号
        save.setOrder(order);


        // 保存主表
        return repository.save(save);
    }


    /**
     * 撤销
     *
     * @param id 主键
     */
    @Override
    public void revoke(Long id) {
        // 将主表状态改为撤销
        BusGoodsCashieOrder order = repository.getById(id);
        order.setStatusCode(BusGoodsCashieOrder.STATUS_CODE_REVOKED);
        // 增加库存数量
        for (BusGoodsCashieOrderIn in : order.getInList()) {
            BusGoodsWarehouseChangeDTO dto = new BusGoodsWarehouseChangeDTO();
            dto.setGoodsId(in.getGoodsId());
            dto.setBusinessCode("cashie-order");
            dto.setWarehouseId(order.getWarehouseId());
            dto.setStockChange(in.getNumber());
            dto.setBusinessCode("cashie-order");
            dto.setRemark("商品销售-前台撤销");
            dto.setBusinessOrder("cashie-order-" + order.getId());
            warehouseStockService.stockChange(dto);
        }
        warehouseDetailService.deleteByBusinessOrder("cashie-order-" + order.getOrder());
        repository.save(order);
    }

    @Override
    public PageResult getBodyData(PageParam<BusGoodsCashieOrderSearchDTO> pageParam) {
        return PageResult.of(repository.getBodyData(pageParam.getData(), pageParam.getPageable()));
    }
}

