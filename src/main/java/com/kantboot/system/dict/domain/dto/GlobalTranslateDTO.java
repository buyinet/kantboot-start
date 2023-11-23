package com.kantboot.system.dict.domain.dto;

import com.kantboot.system.dict.domain.entity.SysDict;
import lombok.Data;

import java.util.List;

@Data
public class GlobalTranslateDTO implements java.io.Serializable {

    /**
     * 文字
     */
    private List<SysDict> dictList;

    /**
     * 语言编码
     */
    private String languageCode;

    /**
     * 字典分组编码
     */
    private String dictGroupCode;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 翻译的语言编码
     */
    private String translateLanguageCodeOfFrom;

    /**
     * 被翻译的语言编码
     */
    private String translateLanguageCodeOfTo;


}
