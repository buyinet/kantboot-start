package com.kantboot.system.setting.web.admin.controller;

import com.kantboot.system.setting.domain.entity.SysSettingGroup;
import com.kantboot.util.core.controller.BaseAdminController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system-setting-web/admin/settingGroup")
public class SysSettingGroupControllerOfAdmin extends BaseAdminController<SysSettingGroup,Long> {
}
