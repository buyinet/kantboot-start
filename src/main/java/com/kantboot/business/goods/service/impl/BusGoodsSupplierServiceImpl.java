package com.kantboot.business.goods.service.impl;

import com.kantboot.business.goods.dao.repository.BusGoodsDueToSupplierRepository;
import com.kantboot.business.goods.dao.repository.BusGoodsSupplierRepository;
import com.kantboot.business.goods.domain.dto.BusGoodsSupplierSearchDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsDueToSupplier;
import com.kantboot.business.goods.domain.entity.BusGoodsSupplier;
import com.kantboot.business.goods.service.IBusGoodsSupplierService;
import com.kantboot.util.common.exception.BaseException;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.redis.RedisUtil;
import com.kantboot.util.core.result.PageResult;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * 商品供应商服务实现类
 * @author 方某方
 */
@Service
public class BusGoodsSupplierServiceImpl implements IBusGoodsSupplierService {

    @Resource
    private BusGoodsSupplierRepository repository;

    @Resource
    private BusGoodsDueToSupplierRepository dueToSupplierRepository;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public PageResult getBodyData(PageParam<BusGoodsSupplierSearchDTO> pageParam) {
        Page<BusGoodsSupplier> bodyData = repository.getBodyData(pageParam.getData(), pageParam.getPageable());
        return PageResult.of(bodyData);
    }

    @Override
    public void borrow(Long id, Double amount) {
        String redisKey = "supplier:" + id;
        if(redisUtil.lock(redisKey,50, TimeUnit.SECONDS)){
            // 第一个参数是驼峰式编码,提示重复操作：supperOperationRepeat
            throw BaseException.of("supperOperationRepeat", "有人正在操作该供应商，请稍后再试");
        }

        BusGoodsDueToSupplier bySupplierId = dueToSupplierRepository.findBySupplierId(id);
        if(bySupplierId == null){
            bySupplierId = new BusGoodsDueToSupplier();
            bySupplierId.setId(id);
            bySupplierId.setSupplierId(id);
            bySupplierId.setDue(0.0);
        }

        bySupplierId.setDue(new BigDecimal(bySupplierId.getDue()).add(new BigDecimal(amount)).doubleValue());

        dueToSupplierRepository.save(bySupplierId);

        redisUtil.unlock(redisKey);
    }

    @Override
    public void repay(Long id, Double amount) {
        String redisKey = "supplier:" + id;
        if(redisUtil.lock(redisKey,50, TimeUnit.SECONDS)){
            // 第一个参数是驼峰式编码,提示重复操作：supperOperationRepeat
            throw BaseException.of("supperOperationRepeat", "有人正在操作该供应商，请稍后再试");
        }

        BusGoodsDueToSupplier bySupplierId = dueToSupplierRepository.findBySupplierId(id);
        if(bySupplierId == null){
            bySupplierId = new BusGoodsDueToSupplier();
            bySupplierId.setId(id);
            bySupplierId.setSupplierId(id);
            bySupplierId.setDue(0.0);
        }

        BusGoodsDueToSupplier busGoodsDueToSupplier = bySupplierId.setDue(new BigDecimal(bySupplierId.getDue()).subtract(new BigDecimal(amount)).doubleValue());
        dueToSupplierRepository.save(busGoodsDueToSupplier);

        redisUtil.unlock(redisKey);
    }
}
