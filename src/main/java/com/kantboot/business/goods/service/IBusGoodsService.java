package com.kantboot.business.goods.service;

import com.kantboot.business.goods.domain.dto.BusGoodsSearchDTO;
import com.kantboot.business.goods.domain.entity.BusGoods;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;

import java.util.List;

public interface IBusGoodsService {

    /**
     * 查询所有
     */
    PageResult getBodyDataTop(PageParam<BusGoodsSearchDTO> pageParam);

    /**
     * 查询所有
     */
    PageResult getBodyData(PageParam<BusGoodsSearchDTO> pageParam);

    /**
     * 保存
     */
    BusGoods save(BusGoods busGoods);

    /**
     * 根据synthesis查询
     * @param synthesis
     * @return BusGoods集合
     */
    List<BusGoods> getBySynthesis(String synthesis);


}
