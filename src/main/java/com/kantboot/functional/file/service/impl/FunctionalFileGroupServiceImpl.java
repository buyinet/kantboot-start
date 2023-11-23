package com.kantboot.functional.file.service.impl;


import com.kantboot.functional.file.dao.repository.FunctionalFileGroupRepository;
import com.kantboot.functional.file.domain.entity.FunctionalFileGroup;
import com.kantboot.functional.file.service.IFunctionalFileGroupService;
import com.kantboot.util.core.redis.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 文件组管理的Service接口实现类
 * @author 方某方
 */
@Service
public class FunctionalFileGroupServiceImpl implements IFunctionalFileGroupService {

    @Resource
    FunctionalFileGroupRepository repository;

    @Resource
    RedisUtil redisUtil;

    @Override
    public FunctionalFileGroup getByCode(String code) {
        FunctionalFileGroup byCode = repository.findByCode(code);
        return byCode;
    }

    @Override
    public String getPathByCode(String code) {
        String redisKey = "fileGroupId:" + code + ":path";
        String s = redisUtil.get(redisKey);
        if (s != null) {
            return s;
        }
        FunctionalFileGroup byCode = repository.findByCode(code);
        if (byCode != null) {
            redisUtil.set(redisKey, byCode.getPath());
            return byCode.getPath();
        }
        return null;
    }
}
