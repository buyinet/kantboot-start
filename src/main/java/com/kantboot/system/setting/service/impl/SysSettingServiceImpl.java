package com.kantboot.system.setting.service.impl;

import com.kantboot.system.setting.dao.repository.SysSettingRepository;
import com.kantboot.system.setting.domain.entity.SysSetting;
import com.kantboot.system.setting.service.ISysSettingService;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.redis.RedisUtil;
import com.kantboot.util.core.result.PageResult;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 系统设置服务层实现类
 * @author 方某方
 */
@Service
public class SysSettingServiceImpl implements ISysSettingService {

    /**
     * redis缓存前缀
     */
    private static final String REDIS_KEY_PREFIX = "sysSetting:";

    @Resource
    private SysSettingRepository repository;

    @Resource
    private RedisUtil redisUtil;

    @Override
    @Cacheable(value = REDIS_KEY_PREFIX,key = "#groupCode+':map'")
    public HashMap<String,String> getMapByGroupCode(String groupCode) {

        HashMap<String,String> result = new HashMap<>(100);
        List<SysSetting> byGroupCode = repository.findByGroupCode(groupCode);
        for (SysSetting sysSetting : byGroupCode) {
            result.put(sysSetting.getCode(),sysSetting.getValue());
        }

        return result;
    }

    @Override
    @Cacheable(value = REDIS_KEY_PREFIX,key = "#groupCode+':'+#code+':value'")
    public String getValue(String groupCode, String code) {
        SysSetting byGroupCodeAndCode = repository.findByGroupCodeAndCode(groupCode, code);
        if(byGroupCodeAndCode == null){
            return null;
        }
        return byGroupCodeAndCode.getValue();
    }

    @Override
    public PageResult getBodyData(PageParam<SysSetting> pageParam) {
        Page<SysSetting> bodyData = repository.getBodyData(pageParam.getData(), pageParam.getPageable());
        return PageResult.of(bodyData);
    }
}
