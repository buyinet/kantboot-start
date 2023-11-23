package com.kantboot.system.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.kantboot.system.user.dao.repository.SysAdminRouterRepository;
import com.kantboot.system.user.domain.entity.SysAdminRouter;
import com.kantboot.system.user.service.ISysAdminRouterService;
import com.kantboot.system.user.service.ISysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysAdminRouterServiceImpl implements ISysAdminRouterService
{

    @Resource
    private SysAdminRouterRepository repository;

    @Resource
    private ISysUserService userService;

    @Override
    public List<SysAdminRouter> getAll() {
        return repository.findAll();
    }

    @Override
    public List<SysAdminRouter> getTop() {
        return repository.getTop();
    }

    @Override
    public List<SysAdminRouter> getSelf() {
        Map<String, Object> self = userService.getSelf();
        List<String> permissionCodes = JSON.parseArray(JSON.toJSONString(self.get("permissionCodes")), String.class);
        List<SysAdminRouter> byPermissionCodeList = repository.getByPermissionCodeList(permissionCodes);
        List<SysAdminRouter> result = new ArrayList<>();
        for (SysAdminRouter router : byPermissionCodeList) {
            if(router.getChildren()==null){
                router.setChildren(new ArrayList<>());
            }
            List<SysAdminRouter> children = new ArrayList<>();
            for (SysAdminRouter child : router.getChildren()) {
                for (String permissionCode : permissionCodes) {
                    if(child.getPermissionCode()!=null && child.getPermissionCode().equals(permissionCode)){
                        children.add(BeanUtil.copyProperties(child, SysAdminRouter.class));
                    }
                }
            }
            SysAdminRouter sysAdminRouter = BeanUtil.copyProperties(router, SysAdminRouter.class);
            sysAdminRouter.setChildren(children);
            result.add(sysAdminRouter);
        }

        return result;
    }


}
