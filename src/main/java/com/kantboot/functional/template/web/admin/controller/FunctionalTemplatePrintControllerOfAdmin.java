package com.kantboot.functional.template.web.admin.controller;

import com.kantboot.functional.template.domain.entity.FunctionalTemplatePrint;
import com.kantboot.functional.template.service.IFunctionalTemplatePrintService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.controller.BaseAdminController;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/functional-template-web/admin/print")
public class FunctionalTemplatePrintControllerOfAdmin extends BaseAdminController<FunctionalTemplatePrint,Long>
{

    @Resource
    private IFunctionalTemplatePrintService service;

    @RequestMapping("/save")
    public RestResult<FunctionalTemplatePrint> save(@RequestBody FunctionalTemplatePrint functionalTemplatePrint){
        return RestResult.success(service.save(functionalTemplatePrint),"saveSuccess","保存成功");
    }

}
