package com.kantboot.functional.template.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class KantbootModuleDTO implements Serializable {

    /**
     * 路径
     */
    private String path;

    /**
     * 基础名称
     */
    private String baseName;

    /**
     * 基础包名
     */
    private String basePackage;

    /**
     * 描述
     */
    private String description;

    /**
     * 父节点
     */
    private DependencyDTO parentDom;


    /**
     * PackagingType
     */
    private String packagingType;

}
