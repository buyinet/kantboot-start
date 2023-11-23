package com.kantboot.business.goods.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.kantboot.business.goods.dao.repository.BusGoodsSaleOrderInRepository;
import com.kantboot.business.goods.dao.repository.BusGoodsSaleOrderRepository;
import com.kantboot.business.goods.domain.dto.BusGoodsProcureOrderSearchDTO;
import com.kantboot.business.goods.domain.dto.BusGoodsWarehouseChangeDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsProcureOrder;
import com.kantboot.business.goods.domain.entity.BusGoodsSaleOrder;
import com.kantboot.business.goods.domain.entity.BusGoodsSaleOrderIn;
import com.kantboot.business.goods.service.IBusGoodsSaleOrderService;
import com.kantboot.business.goods.service.IBusGoodsSupplierService;
import com.kantboot.business.goods.service.IBusGoodsWarehouseDetailService;
import com.kantboot.business.goods.service.IBusGoodsWarehouseStockService;
import com.kantboot.util.common.exception.BaseException;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.redis.RedisUtil;
import com.kantboot.util.core.result.PageResult;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品采购单Service
 */
@Service
public class BusGoodsSaleOrderServiceImpl implements IBusGoodsSaleOrderService {

    @Resource
    private BusGoodsSaleOrderRepository repository;

    @Resource
    private IBusGoodsWarehouseStockService warehouseStockService;

    @Resource
    private IBusGoodsSupplierService supplierService;

    @Resource
    private IBusGoodsWarehouseDetailService warehouseDetailService;

    @Resource
    private BusGoodsSaleOrderInRepository orderInRepository;

    @Resource
    private RedisUtil redisUtil;


    @Override
    public PageResult getBodyData(PageParam<BusGoodsProcureOrderSearchDTO> pageParam) {
        BusGoodsProcureOrderSearchDTO param = pageParam.getData();
        if (param.getStatusCode() != null && !"".equals(param.getStatusCode())) {
            param.setStatusCodeList(List.of(param.getStatusCode()));
        } else {
            param.setStatusCodeList(List.of("sold", "draft"));
        }

        Page<BusGoodsSaleOrder> bodyData = repository.getBodyData(pageParam.getData(), pageParam.getPageable());
        return PageResult.of(bodyData);

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void revoke(Long id) {
        BusGoodsSaleOrder busGoodsProcureOrder = repository.getById(id);
        String order = busGoodsProcureOrder.getOrder();

        // 将状态改为已撤销
        busGoodsProcureOrder.setStatusCode(BusGoodsProcureOrder.STATUS_CODE_REVOKED);
        // 获取仓库id
        Long warehouseId = busGoodsProcureOrder.getWarehouseId();
        // 获取所有商品
        List<BusGoodsSaleOrderIn> inList = busGoodsProcureOrder.getInList();
        // 遍历商品
        for (BusGoodsSaleOrderIn busGoodsProcureOrderIn : inList) {
            // 获取商品仓库库存
            BusGoodsWarehouseChangeDTO busGoodsWarehouseChangeDTO = new BusGoodsWarehouseChangeDTO();
            busGoodsWarehouseChangeDTO.setGoodsId(busGoodsProcureOrderIn.getGoodsId());
            busGoodsWarehouseChangeDTO.setWarehouseId(warehouseId);
            busGoodsWarehouseChangeDTO.setRemark("采购单撤销");
            busGoodsWarehouseChangeDTO.setStockChange(-busGoodsProcureOrderIn.getNumber());
            busGoodsWarehouseChangeDTO.setBusinessCode("sale-order");
            busGoodsWarehouseChangeDTO.setBusinessOrder(order);
            busGoodsWarehouseChangeDTO.setMode(BusGoodsWarehouseChangeDTO.MODE_DELETE);
            // 减少库存
            warehouseStockService.stockChange(busGoodsWarehouseChangeDTO);
        }

        // 删除采购单
        warehouseDetailService.deleteByBusinessOrder(order);

//        // 获取欠供应商的钱
//        Double dueToSupplier = busGoodsProcureOrder.getSupplier().getDue();
//
//        // 获取采购单的欠款
//        Double due = busGoodsProcureOrder.getDue();
//        if(due==null){
//            due = 0D;
//        }
//        // 获取供应商id
//        Long supplierId = busGoodsProcureOrder.getSupplierId();
//
//
//        // 还款
//        supplierService.repay(supplierId, due);

        // 保存采购单
        repository.save(busGoodsProcureOrder);
    }


    @Override
    public BusGoodsSaleOrder save(BusGoodsSaleOrder entity) {

        if(entity.getId()!=null){
            // 如果为空，报异常
            BusGoodsSaleOrder byId = repository.findById(entity.getId()).orElseThrow(
                    () -> BaseException.of("busGoodsProcureOrder", "采购单不存在")
            );

            if (byId.getInList()!=null){
                // 删除采购单商品
                orderInRepository.deleteAll(byId.getInList());
            }

        }

        // 将result的inList里的id设置为null
        List<BusGoodsSaleOrderIn> inList = BeanUtil.copyToList(entity.getInList(), BusGoodsSaleOrderIn.class);

        entity.setInList(null);

        // 保存采购单
        BusGoodsSaleOrder result = repository.save(entity);

        // start:生成采购单号
        Long id = result.getId();
        result.setOrder("sale-order-" + id);
        // end:生成采购单号


        for (BusGoodsSaleOrderIn busGoodsProcureOrderIn : inList) {
            busGoodsProcureOrderIn.setId(null);
            busGoodsProcureOrderIn.setOrderId(entity.getId());
        }
        List<BusGoodsSaleOrderIn> inList2 = JSON.parseArray(JSON.toJSONString(inList),BusGoodsSaleOrderIn.class);
        result.setInList(null);;

        // 计算采购单总价
        BigDecimal total = new BigDecimal(0);
        // 计算采购单总数量
        Long totalNumber = 0L;

        for (BusGoodsSaleOrderIn busGoodsProcureOrderIn : inList) {
            // 如果采购价为空，则跳过
            if(busGoodsProcureOrderIn.getGoods().getPurchasePrice()==null){
                continue;
            }
            // 采购价是采购价*数量*折扣
            // 使用BigDecimal计算
            BigDecimal purchasePrice = new BigDecimal(busGoodsProcureOrderIn.getGoods().getPurchasePrice());
            BigDecimal number = new BigDecimal(busGoodsProcureOrderIn.getNumber());
            BigDecimal discount = new BigDecimal(busGoodsProcureOrderIn.getDiscount());
            total = total.add(purchasePrice.multiply(number).multiply(discount));
        }

        for (BusGoodsSaleOrderIn busGoodsProcureOrderIn : inList) {
            if(busGoodsProcureOrderIn.getGoods().getPurchasePrice()==null){
                continue;
            }
            totalNumber += busGoodsProcureOrderIn.getNumber();
        }

        // 再加上费用金额，减去抹零
        total = total.add(new BigDecimal(entity.getFeeAmount())).subtract(new BigDecimal(entity.getZeroAmount()));
        result.setTotalAmount(total.doubleValue());
        result.setTotalNumber(totalNumber);
        // 计算欠款
        Double due1 = total.doubleValue() - entity.getActualAmount();
        result.setDue(due1);

        result = repository.save(result);
        orderInRepository.saveAll(inList);

        if(BusGoodsProcureOrder.STATUS_CODE_PURCHASED.equals(entity.getStatusCode())){
            // 如果实付金额小于总金额，则进行欠款
            if(entity.getActualAmount()<total.doubleValue()){
                // 欠款
                Double due = total.doubleValue() - entity.getActualAmount();
                supplierService.borrow(entity.getSupplierId(), due);
            }

            // 如果实付金额大于总金额，则报错
            if(entity.getActualAmount()>total.doubleValue()){
                throw BaseException.of("actualAmount", "实付金额不能大于总金额");
            }

            // 添加商品库存
            for (BusGoodsSaleOrderIn busGoodsProcureOrderIn : inList) {
                BusGoodsWarehouseChangeDTO busGoodsWarehouseChangeDTO = new BusGoodsWarehouseChangeDTO();
                busGoodsWarehouseChangeDTO.setGoodsId(busGoodsProcureOrderIn.getGoodsId());
                busGoodsWarehouseChangeDTO.setWarehouseId(entity.getWarehouseId());
                busGoodsWarehouseChangeDTO.setRemark("销售单入库");
                busGoodsWarehouseChangeDTO.setStockChange(busGoodsProcureOrderIn.getNumber());
                busGoodsWarehouseChangeDTO.setBusinessCode("sale-order");
                busGoodsWarehouseChangeDTO.setBusinessOrder(entity.getOrder());
                busGoodsWarehouseChangeDTO.setMode(BusGoodsWarehouseChangeDTO.MODE_SAVE);
                warehouseStockService.stockChange(busGoodsWarehouseChangeDTO);
            }
        }


        return result;
    }
}
