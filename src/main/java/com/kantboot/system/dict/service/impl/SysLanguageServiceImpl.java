package com.kantboot.system.dict.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.kantboot.system.dict.dao.repository.SysLanguageRepository;
import com.kantboot.system.dict.domain.entity.SysLanguage;
import com.kantboot.system.dict.domain.vo.SysLanguageVO;
import com.kantboot.system.dict.service.ISysLanguageService;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典国际化服务类
 * @author 方某方
 */
@Service
public class SysLanguageServiceImpl implements ISysLanguageService {

    @Resource
    private SysLanguageRepository repository;

    @Override
    @Cacheable(value = "sysLanguage", key = "'getBySupport'")
    public List<SysLanguageVO> getBySupport() {
        List<SysLanguage> bySupportTrue = repository.findBySupportTrue();
        return BeanUtil.copyToList(bySupportTrue, SysLanguageVO.class);
    }

}
