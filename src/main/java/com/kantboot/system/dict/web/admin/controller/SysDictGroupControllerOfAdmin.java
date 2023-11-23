package com.kantboot.system.dict.web.admin.controller;


import com.kantboot.system.dict.domain.entity.SysDictGroup;
import com.kantboot.util.core.controller.BaseAdminController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system-dict-web/admin/dictGroup")
public class SysDictGroupControllerOfAdmin extends BaseAdminController<SysDictGroup,Long> {
}
