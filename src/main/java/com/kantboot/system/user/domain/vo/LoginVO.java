package com.kantboot.system.user.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * 登录VO
 * @author: 方某方
 */
@Data
@Accessors(chain = true)
public class LoginVO
implements Serializable
{

    private String Token;

    private Map<String,Object> userInfo;

}
