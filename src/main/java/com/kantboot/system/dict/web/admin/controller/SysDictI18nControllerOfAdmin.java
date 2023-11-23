package com.kantboot.system.dict.web.admin.controller;

import com.kantboot.system.dict.domain.entity.SysDictI18n;
import com.kantboot.util.core.controller.BaseAdminController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system-dict-web/admin/dictI18n")
public class SysDictI18nControllerOfAdmin extends BaseAdminController<SysDictI18n,Long>
{
}
