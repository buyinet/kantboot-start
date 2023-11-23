package com.kantboot.functional.template.service;

import com.kantboot.functional.template.domain.dto.GenerateModuleDTO;
import com.kantboot.functional.template.domain.dto.KantbootModuleDTO;

/**
 * 代码生成器服务
 * @author 方某方
 */
public interface IGenerateCodeService {

    /**
     * 创建模块
     */
    void generateModule(GenerateModuleDTO generateModuleDTO);

    /**
     * 创建pom文件
     */
    String generatePom(GenerateModuleDTO generateModuleDTO);

    /**
     * 生成kanboot结构的模块
     */
    void generateKantbootModule(KantbootModuleDTO dto);

}
