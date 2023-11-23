package com.kantboot.system.dict.service;


import com.kantboot.system.dict.domain.vo.SysLanguageVO;

import java.util.List;

/**
 * 字典国际化服务类
 * @author 方某方
 */
public interface ISysLanguageService {

    /**
     * 查询所有支持的语言
     * @return 语言列表
     */
    List<SysLanguageVO> getBySupport();

}
