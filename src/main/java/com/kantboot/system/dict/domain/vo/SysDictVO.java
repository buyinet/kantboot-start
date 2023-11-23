package com.kantboot.system.dict.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典VO
 * 用于前端展示的VO，不包含敏感信息
 * @author 方某方
 */
@Data
public class SysDictVO implements Serializable {

    private String code;

    private String groupCode;

    private String value;

    private String description;

}
