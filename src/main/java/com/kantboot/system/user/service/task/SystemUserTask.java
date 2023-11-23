package com.kantboot.system.user.service.task;

import com.kantboot.system.user.dao.repository.SysTokenRepository;
import com.kantboot.system.user.domain.entity.SysToken;
import com.kantboot.util.core.redis.RedisUtil;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 用户任务
 *
 * @author 方某方
 */
@Slf4j
@Component
public class SystemUserTask {

    @Resource
    private SysTokenRepository repository;
    @Resource
    private RedisUtil redisUtil;


    /**
     * 定时每1分钟清理一次过期的token
     */
    @SneakyThrows
    @Scheduled(cron = "0 0 0 */7 * ?")
    public void clearToken() {
        String redisKey = "sysToken::getUserId:";
        // 获取所有"sysToken::getUserId:"开头的key
        Set<String> keys = redisUtil.keysByPrefix(redisKey);

        // 获取所有的token
        for (String key : keys) {
            String token = key.substring(redisKey.length());
            Thread.sleep(100);
            Long expire = redisUtil.getExpire(key, TimeUnit.MILLISECONDS);
            Date gmtExpire = new Date(System.currentTimeMillis() + expire);
            repository.save(new SysToken()
                    .setUserId(Long.parseLong(redisUtil.get(key)))
                    .setToken(token)
                    .setGmtExpire(gmtExpire));
            repository.flush();
            log.info("重新保存token:{}，过期时间:{}", token, gmtExpire);
        }

        log.info("开始清理过期的token");
        // 清理掉数据库中所有过期的token
        repository.deleteAllByGmtExpireLessThanNow();
        repository.flush();
        log.info("清理过期的token完成");
    }

}
