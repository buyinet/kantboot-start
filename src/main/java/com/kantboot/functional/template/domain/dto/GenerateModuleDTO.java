package com.kantboot.functional.template.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class GenerateModuleDTO implements Serializable {

    /**
     * 路径
     */
    private String path;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * packaging类型
     */
    private String packagingType;


    /**
     * 父节点
     */
    private DependencyDTO parentDom;

    /**
     * 自身节点
     */
    private DependencyDTO selfDom;

    /**
     * 依赖
     */
    private List<DependencyDTO> dependencies;

    /**
     * Plugins
     */
    private List<DependencyDTO> plugins;


}
