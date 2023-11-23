package com.kantboot.functional.template.domain.dto;

import lombok.Data;

/**
 * 模板生成DTO
 */
@Data
public class FunctionalTemplateGenerateDTO implements java.io.Serializable {

    /**
     * 模板分组编码
     */
    private String groupCode;

    /**
     * 模板编码
     */
    private String code;

    /**
     * 模板数据
     */
    private Object data;

}
