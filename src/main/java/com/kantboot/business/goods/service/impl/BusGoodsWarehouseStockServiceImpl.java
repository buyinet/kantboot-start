package com.kantboot.business.goods.service.impl;

import com.kantboot.business.goods.dao.repository.BusGoodsRepository;
import com.kantboot.business.goods.dao.repository.BusGoodsWarehouseDetailRepository;
import com.kantboot.business.goods.dao.repository.BusGoodsWarehouseRepository;
import com.kantboot.business.goods.dao.repository.BusGoodsWarehouseStockRepository;
import com.kantboot.business.goods.domain.dto.BusGoodsWarehouseChangeDTO;
import com.kantboot.business.goods.domain.dto.BusGoodsWarehouseStockDTO;
import com.kantboot.business.goods.domain.entity.BusGoods;
import com.kantboot.business.goods.domain.entity.BusGoodsWarehouse;
import com.kantboot.business.goods.domain.entity.BusGoodsWarehouseDetail;
import com.kantboot.business.goods.domain.entity.BusGoodsWarehouseStock;
import com.kantboot.business.goods.domain.vo.BusGoodsWarehouseStockStatisticsInVO;
import com.kantboot.business.goods.domain.vo.BusGoodsWarehouseStockStatisticsVO;
import com.kantboot.business.goods.service.IBusGoodsWarehouseDetailService;
import com.kantboot.business.goods.service.IBusGoodsWarehouseStockService;
import com.kantboot.util.common.exception.BaseException;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.redis.RedisUtil;
import com.kantboot.util.core.result.PageResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class BusGoodsWarehouseStockServiceImpl implements IBusGoodsWarehouseStockService {

    @Resource
    private BusGoodsWarehouseStockRepository repository;

    @Resource
    private BusGoodsRepository goodsRepository;

    @Resource
    private BusGoodsWarehouseRepository warehouseRepository;

    @Resource
    private IBusGoodsWarehouseDetailService detailService;

    @Resource
    private RedisUtil redisUtil;


    public PageResult getBodyData2(PageParam<BusGoodsWarehouseStockDTO> pageParam) {
        Page<Object[]> totalStockPerProduct = repository.getTotalStockPerProduct2(pageParam.getData(), pageParam.getPageable());
        List<BusGoodsWarehouseStock> list = new ArrayList<>();
        List<Long> goodsIds = new ArrayList<>();
        for (Object[] objects : totalStockPerProduct.getContent()) {
            goodsIds.add((Long) objects[0]);
        }
        BusGoodsWarehouse busGoodsWarehouse = new BusGoodsWarehouse();
        busGoodsWarehouse.setName("合并仓库");


        List<BusGoodsWarehouseStock> byGoodsIds = repository.findByGoodsIds(goodsIds);
        for (Object[] objects : totalStockPerProduct.getContent()) {
            BusGoodsWarehouseStock busGoodsWarehouseStock = new BusGoodsWarehouseStock();
            busGoodsWarehouseStock.setGoods(new BusGoods());
            busGoodsWarehouseStock.getGoods().setId((Long) objects[0]);
            busGoodsWarehouseStock.setStock((Long) objects[1]);
            busGoodsWarehouseStock.setWarehouse(busGoodsWarehouse);

            for (BusGoodsWarehouseStock byGoodsId : byGoodsIds) {
                if (byGoodsId.getGoods().getId().equals(busGoodsWarehouseStock.getGoods().getId())) {
                    busGoodsWarehouseStock.setGoods(byGoodsId.getGoods());
                    break;
                }
            }
            list.add(busGoodsWarehouseStock);
        }

        PageResult pageResult = PageResult.of(totalStockPerProduct);
        pageResult.setContent(list);
        return pageResult;
    }


    public PageResult getBodyData3(PageParam<BusGoodsWarehouseStockDTO> pageParam) {
        Page<Object[]> totalStockPerProduct = repository.getTotalStockPerProduct3(pageParam.getData(), pageParam.getPageable());
        List<BusGoodsWarehouseStock> list = new ArrayList<>();
        List<Long> goodsIds = new ArrayList<>();
        for (Object[] objects : totalStockPerProduct.getContent()) {
            Long goodsId = (Long) objects[0];
            goodsIds.add(goodsId);
        }
        BusGoodsWarehouse busGoodsWarehouse = new BusGoodsWarehouse();
        busGoodsWarehouse.setName("合并仓库");


        List<BusGoods> byGoodsIds = goodsRepository.findByIds(goodsIds);
        for (Object[] objects : totalStockPerProduct.getContent()) {
            BusGoodsWarehouseStock busGoodsWarehouseStock = new BusGoodsWarehouseStock();
            busGoodsWarehouseStock.setGoods(new BusGoods());
            busGoodsWarehouseStock.getGoods().setId((Long) objects[0]);
            busGoodsWarehouseStock.setStock((Long) objects[1]);
            busGoodsWarehouseStock.setWarehouse(busGoodsWarehouse);

            for (BusGoods byGoodsId : byGoodsIds) {
                if (byGoodsId.getId().equals(busGoodsWarehouseStock.getGoods().getId())) {
                    busGoodsWarehouseStock.setGoods(byGoodsId);
                    break;
                }
            }
            list.add(busGoodsWarehouseStock);
        }

        PageResult pageResult = PageResult.of(totalStockPerProduct);
        pageResult.setContent(list);
        return pageResult;

    }


    public PageResult getBodyData4(PageParam<BusGoodsWarehouseStockDTO> pageParam) {
        Page<Object[]> totalStockPerProduct = repository.getTotalStockPerProduct4(pageParam.getData(), pageParam.getPageable());
        List<BusGoodsWarehouseStock> list = new ArrayList<>();
        List<Long> goodsIds = new ArrayList<>();
        for (Object[] objects : totalStockPerProduct.getContent()) {
            Long goodsId = (Long) objects[0];
            goodsIds.add(goodsId);
        }


        List<BusGoods> byGoodsIds = goodsRepository.findByIds(goodsIds);
        for (Object[] objects : totalStockPerProduct.getContent()) {
            BusGoodsWarehouseStock busGoodsWarehouseStock = new BusGoodsWarehouseStock();
            busGoodsWarehouseStock.setGoods(new BusGoods());
            busGoodsWarehouseStock.getGoods().setId((Long) objects[0]);
            busGoodsWarehouseStock.setStock((Long) objects[1]);
            busGoodsWarehouseStock.setWarehouse(objects[2] == null ?
                    new BusGoodsWarehouse()
                    : (BusGoodsWarehouse) objects[2]);

            for (BusGoods byGoodsId : byGoodsIds) {
                if (byGoodsId.getId().equals(busGoodsWarehouseStock.getGoods().getId())) {
                    busGoodsWarehouseStock.setGoods(byGoodsId);
                    break;
                }
            }
            list.add(busGoodsWarehouseStock);
        }

        PageResult pageResult = PageResult.of(totalStockPerProduct);
        pageResult.setContent(list);
        return pageResult;

    }


    @Override
    public PageResult getBodyData(PageParam<BusGoodsWarehouseStockDTO> pageParam) {
        if (pageParam.getData().getWarehouseIds() != null && pageParam.getData().getWarehouseIds().size() == 0) {
            pageParam.getData().setWarehouseIds(null);
        }

        if (pageParam.getData().getMergeWarehouse() != null && pageParam.getData().getMergeWarehouse()
                && (pageParam.getData().getSingleProduct() != null && pageParam.getData().getSingleProduct())
        ) {
            // 合并仓库的单品查询
            return getBodyData2(pageParam);
        }

        if (pageParam.getData().getMergeWarehouse() != null && pageParam.getData().getMergeWarehouse()
                && (pageParam.getData().getSingleProduct() == null || !pageParam.getData().getSingleProduct())
        ) {
            // 合并仓库的非单品查询
            return getBodyData3(pageParam);
        }

        // 非合并仓库的非单品查询
        if ((pageParam.getData().getMergeWarehouse() == null || !pageParam.getData().getMergeWarehouse())
                && ((pageParam.getData().getSingleProduct() == null || !pageParam.getData().getSingleProduct()))
        ) {
            return getBodyData4(pageParam);
        }

        Page<BusGoodsWarehouseStock> bodyData
                = repository.getBodyData(pageParam.getData(), pageParam.getPageable());
        return PageResult.of(bodyData);
    }


    @Override
    public void check(Long id, Long newStock, Long oldStock, String remark) {
        Optional<BusGoodsWarehouseStock> byId = repository.findById(id);
        if (!byId.isPresent()) {
            throw new RuntimeException("库存不存在");
        }
        BusGoodsWarehouseStock busGoodsWarehouseStock = byId.get();

        if (!busGoodsWarehouseStock.getStock().equals(oldStock)) {
            throw BaseException.of("stockNotMatch", "库存已变更");
        }

        // 获取库存改变了多少
        Long stockChange = newStock - oldStock;

        BusGoodsWarehouseChangeDTO busGoodsWarehouseChangeDTO = new BusGoodsWarehouseChangeDTO();
        busGoodsWarehouseChangeDTO.setGoodsId(busGoodsWarehouseStock.getGoods().getId());
        busGoodsWarehouseChangeDTO.setWarehouseId(busGoodsWarehouseStock.getWarehouse().getId());
        busGoodsWarehouseChangeDTO.setStockChange(stockChange);
        busGoodsWarehouseChangeDTO.setRemark(remark);
        busGoodsWarehouseChangeDTO.setBusinessCode("check");
        busGoodsWarehouseChangeDTO.setBusinessCode("check-"+ UUID.randomUUID().toString().replaceAll("-",""));
        stockChange(busGoodsWarehouseChangeDTO);
    }

    @Resource
    private BusGoodsWarehouseDetailRepository detailRepository;




    @Override
    public BusGoodsWarehouseStock stockChange(BusGoodsWarehouseChangeDTO entity) {
        String redisKey = "goodsWarehouse:lock:" + entity.getGoodsId()+":"+entity.getWarehouseId();

        if (redisUtil.lock(redisKey,50, TimeUnit.SECONDS)) {
            throw BaseException.of("goodsWarehouseLock", "商品仓库明细正在操作中，请稍后再试");
        }

        String redisKey2 = "goodsWarehouse:lock:" + entity.getWarehouseId();
        // 加锁
        if (redisUtil.lock(redisKey2,50, TimeUnit.SECONDS)) {
            throw BaseException.of("goodsWarehouseLock", "商品仓库明细正在操作中，请稍后再试");
        }

        BusGoodsWarehouseDetail busGoodsWarehouseDetail = new BusGoodsWarehouseDetail();

        BusGoodsWarehouseStock busGoodsWarehouseStock = repository.findByGoodsIdAndWarehouseId(entity.getGoodsId(), entity.getWarehouseId());
        if (busGoodsWarehouseStock == null) {
            busGoodsWarehouseStock = new BusGoodsWarehouseStock();
            busGoodsWarehouseStock.setGoodsId(entity.getGoodsId());
            busGoodsWarehouseStock.setWarehouseId(entity.getWarehouseId());
            busGoodsWarehouseStock.setStock(0L);
        }
        // 获取原库存
        busGoodsWarehouseDetail.setStockOld(busGoodsWarehouseStock.getStock());
        // 库存变更
        busGoodsWarehouseStock.setStock(busGoodsWarehouseStock.getStock() + entity.getStockChange());
        BusGoodsWarehouseStock result = repository.save(busGoodsWarehouseStock);
        busGoodsWarehouseDetail.setRemark(entity.getRemark());
        busGoodsWarehouseDetail.setGoodsId(entity.getGoodsId());
        busGoodsWarehouseDetail.setStockChange(entity.getStockChange());
        busGoodsWarehouseDetail.setStockNew(result.getStock());
        busGoodsWarehouseDetail.setWarehouseId(entity.getWarehouseId());
        busGoodsWarehouseDetail.setBusinessCode(entity.getBusinessCode());
        busGoodsWarehouseDetail.setBusinessCode(entity.getBusinessCode());

        if (entity.getGmtOperate() == null) {
            busGoodsWarehouseDetail.setGmtOperate(new Date());
        } else {
            busGoodsWarehouseDetail.setGmtOperate(entity.getGmtOperate());
        }
        detailRepository.save(busGoodsWarehouseDetail);

        redisUtil.unlock(redisKey);
        redisUtil.unlock(redisKey2);
        return result;
    }

    @Override
    public void batchStockChange(List<BusGoodsWarehouseChangeDTO> list) {
        String redisKey = "goodsWarehouse:lock:" + list.get(0).getWarehouseId();
        // 加锁
        if (redisUtil.lock(redisKey,50, TimeUnit.SECONDS)) {
            throw BaseException.of("goodsWarehouseLock", "商品仓库明细正在操作中，请稍后再试");
        }

        // 商品id的list
        List<Long> goodsIds = new ArrayList<>();
        // 仓库id的list
        List<Long> warehouseIds = new ArrayList<>();
        for (BusGoodsWarehouseChangeDTO busGoodsWarehouseChangeDTO : list) {
            goodsIds.add(busGoodsWarehouseChangeDTO.getGoodsId());
            warehouseIds.add(busGoodsWarehouseChangeDTO.getWarehouseId());
        }
        List<BusGoodsWarehouseStock> byGoodsIds = repository.findByGoodsIds(goodsIds);
        // 明细list
        List<BusGoodsWarehouseDetail> detailList = new ArrayList<>();
        // 根据商品id和仓库id查询库存
        List<BusGoodsWarehouseStock> byGoodsIdsAndWarehouseIds = repository.findByGoodsIdsAndWarehouseIds(goodsIds, warehouseIds);
        // 开始时间
        long start = System.currentTimeMillis();
        for (BusGoodsWarehouseChangeDTO busGoodsWarehouseChangeDTO : list) {
            BusGoodsWarehouseStock busGoodsWarehouseStock = null;

            for (BusGoodsWarehouseStock byGoodsIdsAndWarehouseId : byGoodsIdsAndWarehouseIds) {
                if (byGoodsIdsAndWarehouseId.getGoods().getId().equals(busGoodsWarehouseChangeDTO.getGoodsId())
                        && byGoodsIdsAndWarehouseId.getWarehouse().getId().equals(busGoodsWarehouseChangeDTO.getWarehouseId())
                ) {
                    busGoodsWarehouseStock = byGoodsIdsAndWarehouseId;
                    break;
                }
            }

            BusGoodsWarehouseDetail busGoodsWarehouseDetail = new BusGoodsWarehouseDetail();
            // 添加原库存
            if (busGoodsWarehouseStock == null) {
                busGoodsWarehouseDetail.setStockOld(0L);
            } else {
                busGoodsWarehouseDetail.setStockOld(busGoodsWarehouseStock.getStock());
            }
            // 添加新库存
            if (busGoodsWarehouseStock == null) {
                busGoodsWarehouseDetail.setStockNew(busGoodsWarehouseChangeDTO.getStockChange());
            } else {
                busGoodsWarehouseDetail.setStockNew(busGoodsWarehouseStock.getStock() + busGoodsWarehouseChangeDTO.getStockChange());
            }
            busGoodsWarehouseDetail.setGoodsId(busGoodsWarehouseChangeDTO.getGoodsId());
            busGoodsWarehouseDetail.setWarehouseId(busGoodsWarehouseChangeDTO.getWarehouseId());
            busGoodsWarehouseDetail.setStockChange(busGoodsWarehouseChangeDTO.getStockChange());
            busGoodsWarehouseDetail.setRemark(busGoodsWarehouseChangeDTO.getRemark());
            busGoodsWarehouseDetail.setBusinessCode(busGoodsWarehouseChangeDTO.getBusinessCode());
            busGoodsWarehouseDetail.setBusinessOrder(busGoodsWarehouseChangeDTO.getBusinessOrder());
            busGoodsWarehouseDetail.setGmtOperate(busGoodsWarehouseChangeDTO.getGmtOperate());
            detailList.add(busGoodsWarehouseDetail);


            if (busGoodsWarehouseStock == null) {
                busGoodsWarehouseStock = new BusGoodsWarehouseStock();
                busGoodsWarehouseStock.setGoodsId(busGoodsWarehouseChangeDTO.getGoodsId());
                busGoodsWarehouseStock.setWarehouseId(busGoodsWarehouseChangeDTO.getWarehouseId());
                busGoodsWarehouseStock.setStock(0L);
            }
            // 库存变更
            busGoodsWarehouseStock.setStock(busGoodsWarehouseStock.getStock() + busGoodsWarehouseChangeDTO.getStockChange());
            byGoodsIds.add(busGoodsWarehouseStock);
        }
        // 结束时间
        long end = System.currentTimeMillis();
        log.info("循环时间：{}", end - start);

        start = System.currentTimeMillis();
        repository.saveAll(byGoodsIds);
        detailRepository.saveAll(detailList);
        redisUtil.unlock(redisKey);
        end = System.currentTimeMillis();

        log.info("保存时间：{}", end - start);

    }

    @Override
    public BusGoodsWarehouseStock getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                BaseException.of("warehouseStockNotFound", "库存不存在")
        );
    }

    @Override
    public BusGoodsWarehouseStockStatisticsVO calculateCost() {
        // 算出所有仓库
        List<BusGoodsWarehouse> all = warehouseRepository.findAll();
        // 便利所有仓库的所有商品
        List<BusGoodsWarehouseStockStatisticsInVO> list = new ArrayList<>();
        for (BusGoodsWarehouse busGoodsWarehouse : all) {
            List<BusGoodsWarehouseStock> byWarehouseId = repository.findByWarehouseId(busGoodsWarehouse.getId());
            BusGoodsWarehouseStockStatisticsInVO busGoodsWarehouseStockStatisticsVO = new BusGoodsWarehouseStockStatisticsInVO();
            busGoodsWarehouseStockStatisticsVO.setWarehouse(busGoodsWarehouse);
            // 库存总数量
            Long stockCount = 0L;
            // 库存总金额
            BigDecimal stockAmount = BigDecimal.ZERO;

            for (BusGoodsWarehouseStock item : byWarehouseId) {
                // 库存
                Long stock = item.getStock();
                if (stock == null) {
                    stock = 0L;
                }

                // 采购价
                Double purchasePrice = item.getGoods().getPurchasePrice();
                if (purchasePrice == null) {
                    purchasePrice = 0D;
                }

                // 库存总数量
                stockCount+=stock;

                // 库存总金额,加采购价
                stockAmount = stockAmount.add(BigDecimal.valueOf(purchasePrice).multiply(BigDecimal.valueOf(stock)));
            }
            busGoodsWarehouseStockStatisticsVO.setStockCount(stockCount);
            busGoodsWarehouseStockStatisticsVO.setStockAmount(stockAmount.doubleValue());
            list.add(busGoodsWarehouseStockStatisticsVO);
        }

        BusGoodsWarehouseStockStatisticsVO result = new BusGoodsWarehouseStockStatisticsVO();
        result.setList(list);
        // 库存总数量
        Long stockCount = 0L;
        // 库存总金额
        BigDecimal stockAmount = BigDecimal.ZERO;
        for (BusGoodsWarehouseStockStatisticsInVO busGoodsWarehouseStockStatisticsInVO : list) {
            stockCount+=busGoodsWarehouseStockStatisticsInVO.getStockCount();
            stockAmount = stockAmount.add(BigDecimal.valueOf(busGoodsWarehouseStockStatisticsInVO.getStockAmount()));
        }
        result.setStockCount(stockCount);
        result.setStockAmount(stockAmount.doubleValue());

        return result;
    }
}
