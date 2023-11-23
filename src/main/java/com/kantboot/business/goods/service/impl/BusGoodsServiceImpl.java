package com.kantboot.business.goods.service.impl;

import com.kantboot.business.goods.dao.repository.BusGoodsRepository;
import com.kantboot.business.goods.domain.dto.BusGoodsSearchDTO;
import com.kantboot.business.goods.domain.entity.BusGoods;
import com.kantboot.business.goods.service.IBusGoodsService;
import com.kantboot.business.goods.service.IBusGoodsTypeService;
import com.kantboot.util.common.exception.BaseException;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务实现类
 *
 * @author 方某方
 */
@Service
public class BusGoodsServiceImpl implements IBusGoodsService {

    @Resource
    private BusGoodsRepository repository;
    @Resource
    private IBusGoodsTypeService busGoodsTypeService;

    @Override
    public BusGoods save(BusGoods busGoods) {
        BusGoods busGoods1 =new BusGoods();
        if(busGoods.getId()!=null){
            busGoods1 = repository.findById(busGoods.getId()).orElse(new BusGoods());
        }

        if(!"".equals(busGoods.getNumberStr())&&busGoods.getNumberStr()!=null&&!busGoods.getNumberStr().equals(busGoods1.getNumberStr())){
            List<BusGoods> byNumberStr = repository.findByNumberStr(busGoods.getNumberStr());
            if(byNumberStr.size()>0){
                throw BaseException.of("goodsNumberStrExist","商品编号已存在");
            }
        }
        return repository.save(busGoods);
    }

    @Override
    public PageResult getBodyDataTop(PageParam<BusGoodsSearchDTO> pageParam) {
        Long typeId = pageParam.getData().getTypeId();
        if(typeId == null){
            return PageResult.of(repository.getBodyDataTop(pageParam.getData(),pageParam.getPageable()));
        }
        List<Long> allChildrenId = busGoodsTypeService.getAllChildrenId(pageParam.getData().getTypeId());
        pageParam.getData().setTypeIdList(allChildrenId);
        return PageResult.of(repository.getBodyDataTop(pageParam.getData(),pageParam.getPageable()));
    }

    @Override
    public PageResult getBodyData(PageParam<BusGoodsSearchDTO> pageParam) {
        Long typeId = pageParam.getData().getTypeId();
        if(typeId == null){
            return PageResult.of(repository.getBodyData(pageParam.getData(),pageParam.getPageable()));
        }
        List<Long> allChildrenId = busGoodsTypeService.getAllChildrenId(pageParam.getData().getTypeId());
        pageParam.getData().setTypeIdList(allChildrenId);
        return PageResult.of(repository.getBodyData(pageParam.getData(),pageParam.getPageable()));
    }

    @Override
    public List<BusGoods> getBySynthesis(String synthesis) {
        return repository.findBySynthesis(synthesis);
    }

}
