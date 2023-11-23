package com.kantboot.business.goods.service;

import com.kantboot.business.goods.domain.dto.BusEmpSaveDTO;
import com.kantboot.business.goods.domain.entity.BusEmp;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;

/**
 * 商品属性服务
 * @author 方某方
 */
public interface IBusEmpService {

    PageResult getBodyData(PageParam<BusEmp> pageParam);

    BusEmp getSelf();

    /**
     * 保存
     * @param entity
     * @return
     */
    BusEmp save(BusEmpSaveDTO entity);

}
