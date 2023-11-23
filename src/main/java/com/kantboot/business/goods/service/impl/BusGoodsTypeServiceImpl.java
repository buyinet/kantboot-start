package com.kantboot.business.goods.service.impl;

import com.kantboot.business.goods.dao.repository.BusGoodsTypeRepository;
import com.kantboot.business.goods.domain.entity.BusGoodsType;
import com.kantboot.business.goods.service.IBusGoodsTypeService;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class

BusGoodsTypeServiceImpl implements IBusGoodsTypeService {

    @Resource
    private BusGoodsTypeRepository repository;

    @Override
    public PageResult getBodyData(PageParam<BusGoodsType> pageParam) {
        Page<BusGoodsType> byIdAndName = repository.findByIdAndNameAndCode(pageParam.getData().getId()
                , pageParam.getData().getName(),pageParam.getData().getCode(),
                pageParam.getPageable());
        return PageResult.of(byIdAndName);
    }
    

    @Override
    public PageResult getBodyDataTop(PageParam<BusGoodsType> pageParam) {
        Page<BusGoodsType> byIdAndNameAndParentIdIsNull = repository.findByIdAndNameAndCodeAndParentIdIsNull(pageParam.getData().getId()
                , pageParam.getData().getName(),pageParam.getData().getCode(),
                pageParam.getPageable());
        return PageResult.of(byIdAndNameAndParentIdIsNull);
    }

    /**
     * 获取所有子类的id，包括子类下的子类
     * @param id id
     * @return list
     */
    @Override
    public List<Long> getAllChildrenId(Long id) {
        BusGoodsType goodsType = repository.findById(id).orElseThrow(() -> new RuntimeException("id不存在"));
        List<Long> result = new ArrayList<>();
        goodsType.getChildren().forEach(item -> {
            Boolean flag = true;
            // 检查是否result是否已包含
            for (Long l : result) {
                if (l.equals(item.getId())) {
                    flag = false;
                }
            }
            if(flag){
                result.add(item.getId());
                result.addAll(getAllChildrenId(item.getId()));
            }
        });
        return result;
    }
}
