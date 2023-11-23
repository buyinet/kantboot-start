package com.kantboot.system.user.dao.repository;

import com.kantboot.system.user.domain.entity.SysToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SysTokenRepository extends JpaRepository<SysToken,Long>
{

    /**
     * 根据token查询
     * @param token token
     * @return token
     */
    SysToken findByToken(String token);

    /**
     * 根据token查询userId
     * @param token token
     * @return userId
     */
    @Query("SELECT userId FROM SysToken WHERE token = :token")
    Long findUserIdByToken(String token);

    /**
     * 删除所有过期时间小于当前时间的token
     * @return token
     */
    @Query("DELETE FROM SysToken WHERE gmtExpire < CURRENT_TIMESTAMP")
    void deleteAllByGmtExpireLessThanNow();

}
