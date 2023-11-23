package com.kantboot.business.goods.service;

import com.kantboot.business.goods.domain.dto.BusGoodsWarehouseChangeDTO;
import com.kantboot.business.goods.domain.dto.BusGoodsWarehouseStockDTO;
import com.kantboot.business.goods.domain.entity.BusGoodsWarehouseStock;
import com.kantboot.business.goods.domain.vo.BusGoodsWarehouseStockStatisticsVO;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;

import java.util.List;

public interface IBusGoodsWarehouseStockService {

    PageResult getBodyData(PageParam<BusGoodsWarehouseStockDTO> pageParam);

    /**
     * 盘点
     * @param id id
     * @param newStock 新库存
     * @param oldStock 旧库存，用于比较，防止并发
     * @param remark 备注
     */
    void check(Long id, Long newStock, Long oldStock,String remark);


    /**
     * 修改
     * @param entity 实体
     * @return 添加后的实体
     */
    BusGoodsWarehouseStock stockChange(BusGoodsWarehouseChangeDTO entity);

    /**
     * 批量修改
     */
    void batchStockChange(List<BusGoodsWarehouseChangeDTO> list);

    /**
     * 根据id查询
     * @param id id
     * @return 实体
     */
    BusGoodsWarehouseStock getById(Long id);

    /**
     * 计算成本
     */
    BusGoodsWarehouseStockStatisticsVO calculateCost();

}
