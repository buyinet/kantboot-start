package com.kantboot.system.dict.service.impl;

import com.kantboot.system.dict.dao.repository.SysDictRepository;
import com.kantboot.system.dict.domain.entity.SysDict;
import com.kantboot.system.dict.service.ISysDictService;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * 字典服务实现类
 * @author 方某方
 */
@Service
public class SysDictServiceImpl implements ISysDictService {


    @Resource
    private SysDictRepository repository;

    @Override
    public PageResult search(PageParam<SysDict> pageParam) {
        // 如果groupCode为空字符串，设置为null
        if ("".equals(pageParam.getData().getGroupCode())) {
            pageParam.getData().setGroupCode(null);
        }

        Page<SysDict> byGroupCodeAndCodeLike = repository.findByGroupCodeAndCodeLike(pageParam.getData().getGroupCode(), pageParam.getData().getCode(), pageParam.getPageable());
        return PageResult.of(byGroupCodeAndCodeLike);
    }
}
