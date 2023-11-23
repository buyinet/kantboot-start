package com.kantboot.functional.template.web.admin.controller;

import com.kantboot.functional.template.domain.entity.FunctionalTemplatePrintGroup;
import com.kantboot.functional.template.service.IFunctionalTemplatePrintGroupService;
import com.kantboot.util.common.result.RestResult;
import com.kantboot.util.core.controller.BaseAdminController;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/functional-template-web/admin/printGroup")
public class FunctionalTemplatePrintGroupControllerOfAdmin extends BaseAdminController<FunctionalTemplatePrintGroup,Long>
{

    @Resource
    private IFunctionalTemplatePrintGroupService service;

    @RequestMapping("/getAll")
    public RestResult<List<FunctionalTemplatePrintGroup>> getAll(){
        return RestResult.success(service.getAll(),"getSuccess","获取成功");
    }

}
