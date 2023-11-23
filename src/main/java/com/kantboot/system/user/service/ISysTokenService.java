package com.kantboot.system.user.service;

/**
 * 用户token服务接口
 */
public interface ISysTokenService {

    /**
     * 根据token获取用户id
     * @param token token
     * @return 用户id
     */
    Long getUserIdByToken(String token);

    /**
     * 根据用户id生成token
     * @param userId 用户id
     * @return token
     */
    String generateToken(Long userId);

    /**
     * 获取token过期时间
     * @return 过期时间
     */
    String getTokenExpireTime();

    /**
     * 获取自身的token
     * @return token
     */
    String getSelfToken();
}
