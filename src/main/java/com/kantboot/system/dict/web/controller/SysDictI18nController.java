package com.kantboot.system.dict.web.controller;

import com.kantboot.system.dict.domain.dto.GlobalTranslateDTO;
import com.kantboot.system.dict.domain.dto.SysDictI18nDTO;
import com.kantboot.system.dict.domain.entity.SysDictI18n;
import com.kantboot.system.dict.service.ISysDictI18nService;
import com.kantboot.util.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典国际化控制器类
 * @author 方某方
 */
@RestController
@RequestMapping("/system-dict-web/dictI18n")
public class SysDictI18nController {

    @Resource
    private ISysDictI18nService service;


    /**
     * 获取字典国际化Map
     * @param dictGroupCode 字典分组编码
     * @param languageCode 语言编码
     * @return 字典国际化Map
     */
    @RequestMapping(value = "/getDictI18nMap",method = RequestMethod.POST)
    public RestResult getDictI18nMap(@RequestParam("dictGroupCode") String dictGroupCode,
                                     @RequestParam("languageCode") String languageCode) {
        return RestResult.success(service.getDictI18nMap(dictGroupCode, languageCode),"getSuccess","获取成功");
    }

    /**
     * 获取自己的字典国际化Map
     * @param dictGroupCode 字典分组编码
     * @return 字典国际化Map
     */
    @RequestMapping(value = "/getDictI18nMapSelf",method = RequestMethod.POST)
    public RestResult getDictI18nMapSelf(@RequestParam("dictGroupCode") String dictGroupCode) {
        return RestResult.success(service.getDictI18nMapSelf(dictGroupCode),"getSuccess","获取成功");
    }

    /**
     * 根据字典编码、字典分组编码、语言编码获取字典值
     * @param dictCode 字典编码
     * @param dictGroupCode 字典分组编码
     * @param languageCode 语言编码
     * @return 字典值
     */
    @RequestMapping(value="/getValue",method = RequestMethod.POST)
    public RestResult getValue(@RequestParam("dictCode") String dictCode,
                               @RequestParam("dictGroupCode") String dictGroupCode,
                               @RequestParam("languageCode") String languageCode) {
        return RestResult.success(service.getValue(dictCode, dictGroupCode, languageCode),"getSuccess","获取成功");
    }

    /**
     * 批量添加字典国际化
     * @param list 字典国际化列表
     * @return 添加结果
     */
    @RequestMapping(value="/addList",method = RequestMethod.POST)
    public RestResult addList(@RequestBody List<SysDictI18n> list) {
        service.addList(list);
        return RestResult.success(null, "addSuccess", "添加成功");
    }

    /**
     * 保存字典和字典国际化
     * @param dictI18n 字典国际化
     * @return 保存结果
     */
    @RequestMapping(value="/saveDictAndI18n",method = RequestMethod.POST)
    public RestResult saveDictAndI18n(@RequestBody SysDictI18nDTO dictI18n) {
        service.saveDictAndDictI18n(dictI18n);
        return RestResult.success(null, "saveSuccess", "保存成功");
    }

    /**
     * 全局翻译
     */
    @RequestMapping(value="/globalTranslate",method = RequestMethod.POST)
    public RestResult globalTranslate(@RequestBody GlobalTranslateDTO dto){
        service.globalTranslate(dto);
        return RestResult.success(null, "translateSuccess", "翻译成功");
    }

}
