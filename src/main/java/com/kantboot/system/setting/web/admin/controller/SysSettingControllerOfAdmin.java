package com.kantboot.system.setting.web.admin.controller;

import com.kantboot.system.setting.domain.entity.SysSetting;
import com.kantboot.util.core.controller.BaseAdminController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system-setting-web/admin/setting")
public class SysSettingControllerOfAdmin extends BaseAdminController<SysSetting,Long> {
}
