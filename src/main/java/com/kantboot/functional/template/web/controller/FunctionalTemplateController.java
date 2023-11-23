package com.kantboot.functional.template.web.controller;

import com.kantboot.functional.template.domain.dto.FunctionalTemplateGenerateDTO;
import com.kantboot.functional.template.service.IFunctionalTemplateService;
import com.kantboot.util.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/functional-template-web/template")
public class FunctionalTemplateController {

    @Resource
    private IFunctionalTemplateService service;

    /**
     * 根据分组编码和编码查询
     * @param groupCode 分组编码
     * @param code 编码
     * @return 模板字符
     */
    @RequestMapping("/getByGroupCodeAndCode")
    public RestResult<Object> getByGroupCodeAndCode(String groupCode, String code) {
        return RestResult.success(service.getByGroupCodeAndCode(groupCode, code), "getSuccess","获取成功");
    }

    /**
     * 生成模板
     */
    @RequestMapping("/generate")
    public RestResult<String> generate(@RequestBody FunctionalTemplateGenerateDTO dto) {
        return RestResult.success(service.generate(dto), "getSuccess","获取成功");
    }


}
