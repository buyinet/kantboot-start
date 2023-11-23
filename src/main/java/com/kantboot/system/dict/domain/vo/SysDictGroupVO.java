package com.kantboot.system.dict.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典分组VO
 * @author 方某方
 */
@Data
public class SysDictGroupVO implements Serializable {


    /**
     * 字典组编码
     */
    private String code;


    /**
     * 字典组名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;


}
