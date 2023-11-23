package com.kantboot.system.dict.service.impl;

import com.alibaba.fastjson2.JSON;
import com.kantboot.api.translate.service.IApiTranslateService;
import com.kantboot.system.dict.dao.repository.SysDictI18nRepository;
import com.kantboot.system.dict.dao.repository.SysDictRepository;
import com.kantboot.system.dict.dao.repository.SysLanguageLocalizedRepository;
import com.kantboot.system.dict.domain.dto.GlobalTranslateDTO;
import com.kantboot.system.dict.domain.dto.SysDictI18nDTO;
import com.kantboot.system.dict.domain.entity.SysDict;
import com.kantboot.system.dict.domain.entity.SysDictI18n;
import com.kantboot.system.dict.domain.entity.SysLanguageLocalized;
import com.kantboot.system.dict.domain.vo.SysLanguageVO;
import com.kantboot.system.dict.service.ISysDictI18nService;
import com.kantboot.system.dict.service.ISysLanguageService;
import com.kantboot.util.common.http.HttpRequestHeaderUtil;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典国际化服务实现类
 *
 * @author 方某方
 */
@Slf4j
@Service
public class ISysDictI18nServiceImpl implements ISysDictI18nService {

    /**
     * redis前缀
     * Redis Prefix
     */
    private static final String REDIS_KEY_PREFIX = "sysDictI18n";

    @Resource
    private SysDictI18nRepository repository;

    @Resource
    private SysDictRepository sysDictRepository;

    @Resource
    private ISysLanguageService languageService;

    @Resource
    private HttpRequestHeaderUtil httpRequestHeaderUtil;

    @Resource
    private IApiTranslateService apiTranslateService;

    @Resource
    private SysLanguageLocalizedRepository languageLocalizedRepository;


    /**
     * 获取字典国际化映射
     * Get a mapping of dictionary internationalization
     *
     * @param dictGroupCode 字典组代码 (Dictionary Group Code)
     * @param languageCode  语言代码 (Language Code)
     * @return 字典映射 (Dictionary Mapping)
     */
    @Override
//    @Cacheable(value = REDIS_KEY_PREFIX, key = "#dictGroupCode + ':' + #languageCode")
    public HashMap<String, String> getDictI18nMap(String dictGroupCode, String languageCode) {
        HashMap<String, String> result = new HashMap<>(100);
        SysLanguageLocalized byCode = languageLocalizedRepository.findByCode(languageCode);
        String languageCode1 = "en";
        if (byCode != null) {
            languageCode1 = byCode.getLanguageCode();
        }
        log.info("languageCode:{},dictGroupCode:{}", languageCode1, dictGroupCode);
        // 获取字典国际化列表
        List<SysDictI18n> byDictGroupCodeAndLanguageCode
                = repository.findByDictGroupCodeAndLanguageCode(dictGroupCode, languageCode1);

        // 将字典国际化列表转换为字典map
        for (SysDictI18n sysDictI18n : byDictGroupCodeAndLanguageCode) {
            result.put(sysDictI18n.getDictCode(), sysDictI18n.getValue());
        }
        return result;
    }

    @Override
    public Map<String, String> getDictI18nMapSelf(String dictGroupCode) {
        String languageCode = httpRequestHeaderUtil.getLanguageCode();
        return getDictI18nMap(dictGroupCode, languageCode);
    }

    /**
     * 获取字典值
     * Get dictionary value
     *
     * @param dictCode      字典代码 (Dictionary Code)
     * @param dictGroupCode 字典组代码 (Dictionary Group Code)
     * @param languageCode  语言代码 (Language Code)
     * @return 字典值 (Dictionary Value)
     */
    @Override
    @Cacheable(value = REDIS_KEY_PREFIX, key = "#dictCode + ':' + #dictGroupCode + ':' + #languageCode")
    public String getValue(String dictCode, String dictGroupCode, String languageCode) {
        return repository.findValue(dictCode, dictGroupCode, languageCode);
    }

    /**
     * 保存字典国际化
     * Save dictionary internationalization
     * 执行完成后，会清除所有dictI18n的缓存
     * After execution, it will clear the cache for all dictI18n
     *
     * @param dictI18nList 字典国际化列表 (Dictionary Internationalization List)
     */
    @Transactional(rollbackOn = Exception.class)
    @CacheEvict(value = REDIS_KEY_PREFIX, allEntries = true)
    @Override
    public void addList(List<SysDictI18n> dictI18nList) {
        int size = dictI18nList.size();
        List<String> dictCodeList = new ArrayList<>(size);
        List<String> dictGroupCodeList = new ArrayList<>(size);
        List<String> languageCodeList = new ArrayList<>(size);
        for (SysDictI18n sysDictI18n : dictI18nList) {
            dictCodeList.add(sysDictI18n.getDictCode());
            dictGroupCodeList.add(sysDictI18n.getDictGroupCode());
            languageCodeList.add(sysDictI18n.getLanguageCode());
        }
        int rowNumByDel = repository.deleteByDictCodesAndDictGroupCodesAndLanguageCodes(dictCodeList, dictGroupCodeList, languageCodeList);
        log.info("删除了{}条字典国际化数据", rowNumByDel);
        repository.saveAll(dictI18nList);
    }

    /**
     * 保存字典和字典国际化
     * Save dictionary and dictionary internationalization
     *
     * @param dictI18nDTO 字典国际化DTO (Dictionary Internationalization DTO)
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public void saveDictAndDictI18n(SysDictI18nDTO dictI18nDTO) {
        SysDict byGroupCodeAndCode = sysDictRepository.findByGroupCodeAndCode(
                dictI18nDTO.getDictGroupCode(),
                dictI18nDTO.getDictCode());

        if (byGroupCodeAndCode == null) {
            SysDict sysDict = new SysDict();
            sysDict.setGroupCode(dictI18nDTO.getDictGroupCode());
            sysDict.setCode(dictI18nDTO.getDictCode());
            sysDict.setValue(dictI18nDTO.getDictValue());
            sysDict.setDescription(dictI18nDTO.getDictDescription());
            sysDictRepository.save(sysDict);
        }

        List<SysDictI18n> dictI18nList = dictI18nDTO.getDictI18nList();
        for (SysDictI18n sysDictI18n : dictI18nList) {
            sysDictI18n.setDictGroupCode(dictI18nDTO.getDictGroupCode());
            sysDictI18n.setDictCode(dictI18nDTO.getDictCode());
        }
        addList(dictI18nList);
    }

    @SneakyThrows
    @Override
    public void globalTranslate(GlobalTranslateDTO dto) {
        List<SysDict> dictList = dto.getDictList();
        // 翻译的语言编码
        String translateLanguageCodeOfFrom = dto.getTranslateLanguageCodeOfFrom();
        // 获取所有支持的语言
        List<SysLanguageVO> bySupport = languageService.getBySupport();
        boolean isExist = true;

        for (SysLanguageVO sysLanguageVO : bySupport) {
            for (SysDict dict : dictList) {
                long start = System.currentTimeMillis();
                long end = System.currentTimeMillis();
                String value = repository.findValue(dict.getCode(), dto.getDictGroupCode(), sysLanguageVO.getCode());
                if (value == null) {
                    isExist = false;
                }
                log.info(dict.getCode() + "," + dto.getDictGroupCode() + "," + sysLanguageVO.getCode() + "," + value);
                if (value != null) {
                    log.info("数据库已存在，跳过翻译");
                    continue;
                }

                log.info(translateLanguageCodeOfFrom + " -> " + sysLanguageVO.getCode());
                String translate = null;
                try {
                    translate = apiTranslateService.translate(dict.getValue(), translateLanguageCodeOfFrom, sysLanguageVO.getCode());
                    end = System.currentTimeMillis();
                    System.out.println("时间：" + (end - start));
                } catch (Exception e) {
                    log.error("翻译失败", e);
                    end = System.currentTimeMillis();
                    log.info("时间：" + (end - start));
                    long allTime = end - start;
                    if (allTime < 10) {
                        Thread.sleep(10 - allTime);
                    }
                    translate = apiTranslateService.translate(dict.getValue(), translateLanguageCodeOfFrom, "en");
                }
                SysDictI18n sysDictI18n = new SysDictI18n();
                sysDictI18n.setDictCode(dict.getCode());
                sysDictI18n.setDictGroupCode(dto.getDictGroupCode());
                sysDictI18n.setLanguageCode(sysLanguageVO.getCode());
                sysDictI18n.setValue(translate);
                repository.save(sysDictI18n);
                end = System.currentTimeMillis();
                log.info("时间：" + (end - start));
                long allTime = end - start;
                if (allTime < 10) {
                    Thread.sleep(10 - allTime);
                }
                log.info(JSON.toJSONString(sysDictI18n));
            }
        }
    }
}
