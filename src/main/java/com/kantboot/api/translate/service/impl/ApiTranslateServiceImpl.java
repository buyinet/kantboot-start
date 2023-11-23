package com.kantboot.api.translate.service.impl;


import com.kantboot.api.translate.dao.reoisitory.ApiTranslateLanguageCodeRepository;
import com.kantboot.api.translate.domain.entity.ApiTranslateLanguageCode;
import com.kantboot.api.translate.service.IApiTranslateService;
import com.kantboot.api.translate.util.BaiduTranslateUtil;
import com.kantboot.system.setting.service.ISysSettingService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ApiTranslateServiceImpl implements IApiTranslateService {

    @Resource
    private ApiTranslateLanguageCodeRepository repository;

    @Resource
    private ISysSettingService settingService;

    @Override
    public String translate(String text, String from, String to) {
        String appid = settingService.getValue("baiduTranslate", "appid");
        String secret = settingService.getValue("baiduTranslate", "secret");
        ApiTranslateLanguageCode codeByFrom = repository.findByCode(from);
        ApiTranslateLanguageCode codeByTo = repository.findByCode(to);
        return BaiduTranslateUtil.translate(text,
                codeByFrom.getBaiduTranslateCode(),
                codeByTo.getBaiduTranslateCode(),
                appid,
                secret);
    }


}
