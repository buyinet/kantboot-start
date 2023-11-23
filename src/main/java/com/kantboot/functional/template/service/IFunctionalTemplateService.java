package com.kantboot.functional.template.service;

import com.kantboot.functional.template.domain.dto.FunctionalTemplateGenerateDTO;
import com.kantboot.functional.template.domain.entity.FunctionalTemplate;

import java.util.List;

public interface IFunctionalTemplateService {


    /**
     * 根据分组编码和编码查询
     * @param groupCode
     * @param code
     * @return FunctionalTemplate
     */
    FunctionalTemplate getByGroupCodeAndCode(String groupCode, String code);


    /**
     * 生成模板
     */
    String generate(FunctionalTemplateGenerateDTO dto);

    /**
     * 根据分组生成
     */
    List<FunctionalTemplate> getByGroupCode(String groupCode);

}
