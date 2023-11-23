package com.kantboot.business.goods.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BusEmpSaveDTO implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 员工性别编码，驼峰式
     * 男：male
     * 女：female
     * 未知：unknown
     * 保密：secret
     */
    private String genderCode;

    /**
     * 员工生日
     */
    private Date birthday;

    /**
     * 员工手机号
     */
    private String phone;

    /**
     * 员工邮箱
     */
    private String email;

    /**
     * 员工住址
     */
    private String address;

    /**
     * 员工所属门店
     */
    private Long goodsStoreId;

    /**
     * 创建时间
     * @return 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     * @return 修改时间
     */
    private Date gmtModified;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 账号
     * 非用户账号，而是员工账号
     * 用于员工登录，但是员工账号和用户账号基本是同一个
     */
    private String username;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 密码
     */
    private String password;

}
