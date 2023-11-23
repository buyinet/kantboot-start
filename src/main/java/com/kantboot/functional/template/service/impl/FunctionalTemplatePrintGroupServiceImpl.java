package com.kantboot.functional.template.service.impl;

import com.kantboot.functional.template.dao.repository.FunctionalTemplatePrintGroupRepository;
import com.kantboot.functional.template.domain.entity.FunctionalTemplatePrintGroup;
import com.kantboot.functional.template.service.IFunctionalTemplatePrintGroupService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionalTemplatePrintGroupServiceImpl implements IFunctionalTemplatePrintGroupService {

    @Resource
    private FunctionalTemplatePrintGroupRepository repository;

    @Override
    public List<FunctionalTemplatePrintGroup> getAll() {
        return repository.findAll();
    }
}
