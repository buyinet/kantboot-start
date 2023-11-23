package com.kantboot.system.user.service.impl;

import cn.hutool.core.util.IdUtil;
import com.kantboot.system.user.dao.repository.SysTokenRepository;
import com.kantboot.system.user.domain.entity.SysToken;
import com.kantboot.system.user.service.ISysTokenService;
import com.kantboot.util.common.exception.BaseException;
import com.kantboot.util.common.http.HttpRequestHeaderUtil;
import com.kantboot.util.core.redis.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 用户token服务实现类
 * @author 方某方
 */
@Service
public class SysTokenServiceImpl implements ISysTokenService
{

    @Resource
    private SysTokenRepository repository;

//    @DubboReference
//    private ISysSettingRpcService settingRpcService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private HttpRequestHeaderUtil httpRequestHeaderUtil;

    @Override
    @Cacheable(value = "sysToken::getTokenExpireTime")
    public String getTokenExpireTime() {
//        return settingRpcService.getValue("user", "tokenExpireTime");
        return "3600000";
    }

    @Override
    public Long getUserIdByToken(String token) {
        String redisKey = "sysToken::getUserId:" + token;
        if(redisUtil.hasKey(redisKey)){
            return Long.parseLong(redisUtil.get(redisKey));
        }
        Long userIdByToken = repository.findUserIdByToken(token);
        if(userIdByToken == null){
            throw BaseException.of("notLogin", "未登录");
        }

        // 登录已过期
        if(repository.findByToken(token).getGmtExpire().before(new Date())){
            throw BaseException.of("loginExpired", "登录已过期");
        }

        redisUtil.setEx(redisKey,userIdByToken+"",Long.parseLong(getTokenExpireTime()), TimeUnit.MILLISECONDS);
        return userIdByToken;
    }

    @Override
    public String generateToken(Long userId) {

        return repository.save(new SysToken()
                .setUserId(userId)
                .setToken(IdUtil.fastSimpleUUID().replace("-",""))
                .setGmtExpire(new Date(System.currentTimeMillis() + Long.parseLong(getTokenExpireTime())))
        ).getToken();
    }

    @Override
    public String getSelfToken() {
        return httpRequestHeaderUtil.getToken();
    }
}
