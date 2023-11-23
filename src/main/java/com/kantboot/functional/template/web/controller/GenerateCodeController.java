package com.kantboot.functional.template.web.controller;

import com.kantboot.functional.template.domain.dto.GenerateModuleDTO;
import com.kantboot.functional.template.domain.dto.KantbootModuleDTO;
import com.kantboot.functional.template.service.IGenerateCodeService;
import com.kantboot.util.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/functional-template-web/generateCode")
public class GenerateCodeController {

    @Resource
    private IGenerateCodeService service;

    /**
     * 创建模块
     */
    @RequestMapping("/generateModule")
    public RestResult generateModule(@RequestBody GenerateModuleDTO generateModuleDTO){
        service.generateModule(generateModuleDTO);
        return RestResult.success("success","getSuccess","生成成功");
    }

    /**
     * 创建pom文件
     */
    @RequestMapping("/generatePom")
    public RestResult generatePom(@RequestBody GenerateModuleDTO generateModuleDTO){
        return RestResult.success(service.generatePom(generateModuleDTO),"getSuccess","生成成功");
    }

    /**
     * 生成kanboot结构的模块
     */
    @RequestMapping("/generateKantbootModule")
    public RestResult generateKantbootModule(@RequestBody KantbootModuleDTO param){
        service.generateKantbootModule(param);
        return RestResult.success("success","generateSuccess","生成成功");
    }


}
