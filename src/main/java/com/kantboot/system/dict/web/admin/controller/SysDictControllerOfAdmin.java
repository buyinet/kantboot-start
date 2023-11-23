package com.kantboot.system.dict.web.admin.controller;

import com.kantboot.system.dict.domain.entity.SysDict;
import com.kantboot.util.core.controller.BaseAdminController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system-dict-web/admin/dict")
public class SysDictControllerOfAdmin extends BaseAdminController<SysDict,Long> {
}
