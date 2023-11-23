package com.kantboot.functional.template.service;

import com.kantboot.functional.template.domain.entity.FunctionalTemplatePrintGroup;

import java.util.List;

public interface IFunctionalTemplatePrintGroupService {

    /**
     * 获取所有的打印组
     * @return 所有的打印组
     */
    List<FunctionalTemplatePrintGroup> getAll();




}
