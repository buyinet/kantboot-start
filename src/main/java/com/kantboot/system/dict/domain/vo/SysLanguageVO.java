package com.kantboot.system.dict.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典VO
 * 不包含敏感信息
 * @author 方某方
 */
@Data
public class SysLanguageVO implements Serializable {

    private String code;

    private String description;

    private String name;

    private String baiduTranslateCode;

}
