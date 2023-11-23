package com.kantboot.business.goods.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.kantboot.business.goods.dao.repository.BusEmpRepository;
import com.kantboot.business.goods.domain.dto.BusEmpSaveDTO;
import com.kantboot.business.goods.domain.entity.BusEmp;
import com.kantboot.business.goods.service.IBusEmpService;
import com.kantboot.system.user.domain.vo.LoginVO;
import com.kantboot.system.user.service.ISysUserService;
import com.kantboot.util.common.exception.BaseException;
import com.kantboot.util.common.http.HttpRequestHeaderUtil;
import com.kantboot.util.core.param.PageParam;
import com.kantboot.util.core.result.PageResult;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * 商品属性服务实现类
 *
 * @author 方某方
 */
@Service
public class BusEmpServiceImpl implements IBusEmpService {

    @Resource
    private BusEmpRepository repository;

    @Resource
    private ISysUserService userService;

    @Resource
    private HttpRequestHeaderUtil httpRequestHeaderUtil;

    @Override
    public PageResult getBodyData(PageParam<BusEmp> pageParam) {
        Page<BusEmp> byUsernameAndName = repository.findByUsernameAndNameAndGoodsStoreId(pageParam.getData().getUsername(), pageParam.getData().getName(),
                pageParam.getData().getGoodsStoreId(),
                pageParam.getPageable());
        return PageResult.of(byUsernameAndName);
    }

    @Override
    public BusEmp getSelf() {
        Long userId = userService.getSelfId();
        BusEmp byUserId = repository.getByUserId(userId);
        return byUserId;
    }

    @Override
    public BusEmp save(BusEmpSaveDTO entity) {
        Boolean usernameExist = userService.isUsernameExist(entity.getUsername());
        if (usernameExist) {
            throw BaseException.of("usernameRepeat", "用户名已存在");
        }
        LoginVO register = userService.register(entity.getUsername(), entity.getPassword());

        // 将BusEmpSaveVO转换为BusEmp
        BusEmp busEmp = BeanUtil.toBean(entity, BusEmp.class);
        busEmp.setUserId((Long) register.getUserInfo().get("id"));

        return repository.save(busEmp);
    }
}
