package com.kantboot.system.dict.domain.dto;

import com.kantboot.system.dict.domain.entity.SysDictI18n;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 字典国际化DTO
 * 用于接收前端传来的字典国际化数据
 * @author 方某方
 */
@Data
public class SysDictI18nDTO implements Serializable
{

    /**
     * 字典分组编码
     */
    private String dictGroupCode;

    /**
     * 语言编码
     */
    private String dictCode;

    /**
     * 字典编码
     */
    private String dictValue;

    /**
     * 字典描述
     */
    private String dictDescription;

    /**
     * 字典国际化列表
     */
    private List<SysDictI18n> dictI18nList;

}
