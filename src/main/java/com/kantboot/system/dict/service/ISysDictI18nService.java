package com.kantboot.system.dict.service;

import com.kantboot.system.dict.domain.dto.GlobalTranslateDTO;
import com.kantboot.system.dict.domain.dto.SysDictI18nDTO;
import com.kantboot.system.dict.domain.entity.SysDictI18n;

import java.util.List;
import java.util.Map;

/**
 * 字典国际化服务类
 * @author 方某方
 */
public interface ISysDictI18nService {

    /**
     * 根据字典分组和语言编码查询字典国际化Map
     * @param dictGroupCode 字典分组编码
     * @param languageCode 语言编码
     * @return 字典国际化
     */
    Map<String,String> getDictI18nMap(String dictGroupCode, String languageCode);

    /**
     * 根据字典分组查询字典国际化Map
     * @param dictGroupCode 字典分组编码
     * @return 字典国际化
     */
    Map<String,String> getDictI18nMapSelf(String dictGroupCode);

    /**
     * 根据字典编码、字典分组编码和语言编码查询值
     * @param dictCode 字典编码
     * @param dictGroupCode 字典分组编码
     * @param languageCode 语言编码
     * @return 值
     */
    String getValue(String dictCode, String dictGroupCode, String languageCode);


    /**
     * 添加字典国际化
     * @param dictI18nList 字典国际化列表
     * @return 字典国际化列表
     */
    void addList(List<SysDictI18n> dictI18nList);

    /**
     * 保存字典和字典国际化
     * @param dictI18nDTO 字典国际化DTO
     */
    void saveDictAndDictI18n(SysDictI18nDTO dictI18nDTO);

    /**
     * 添加全局字典国际化
     * @return 字典国际化列表
     */
    void globalTranslate(GlobalTranslateDTO dto);


}
