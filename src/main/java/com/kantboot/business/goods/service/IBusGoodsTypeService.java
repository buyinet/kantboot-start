package com.kantboot.business.goods.service;

import com.kantboot.business.goods.domain.entity.BusGoodsType;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;

import java.util.List;

public interface IBusGoodsTypeService {

    /**
     * 查询所有
     */
    PageResult getBodyData(PageParam<BusGoodsType> pageParam);

    /**
     * 获取parent_id为null的数据
     */
    PageResult getBodyDataTop(PageParam<BusGoodsType> pageParam);

    /**
     * 获取所有子类的id，包括子类下的子类
     * @param id id
     * @return list
     */
    List<Long> getAllChildrenId(Long id);

}
