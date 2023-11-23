package com.kantboot.system.dict.service;


import com.kantboot.system.dict.domain.entity.SysDict;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;

/**
 * 字典服务类
 * @author 方某方
 */
public interface ISysDictService {

    /**
     * 根据分组编码和语言编码模糊查询字典
     * @param pageParam 分页参数
     * @return 字典列表
     */
    PageResult search(PageParam<SysDict> pageParam);


}
