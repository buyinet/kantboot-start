package com.kantboot.system.setting.service;

import com.kantboot.system.setting.domain.entity.SysSetting;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;

import java.util.HashMap;

/**
 * 系统设置分组服务层接口
 * @author 方某方
 */
public interface ISysSettingService{

    /**
     * 根据分组获取所有设置，以map形式返回
     * @param groupCode 分组编码
     * @return 所有设置
     */
   HashMap<String,String> getMapByGroupCode(String groupCode);

    /**
     * 根据分组和编码获取值
     * @param groupCode 分组编码
     * @param code 编码
     * @return 设置
     */
    String getValue(String groupCode, String code);

    /**
     * 获取分组
     */
    PageResult getBodyData(PageParam<SysSetting> pageParam);


}
