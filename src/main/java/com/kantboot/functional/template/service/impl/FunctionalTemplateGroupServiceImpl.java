package com.kantboot.functional.template.service.impl;

import com.kantboot.functional.template.dao.repository.FunctionalTemplateGroupRepository;
import com.kantboot.functional.template.domain.entity.FunctionalTemplateGroup;
import com.kantboot.functional.template.service.IFunctionalTemplateGroupService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionalTemplateGroupServiceImpl implements IFunctionalTemplateGroupService {

    @Resource
    private FunctionalTemplateGroupRepository repository;


    @Override
    public List<FunctionalTemplateGroup> getAll() {
        return repository.findAll();
    }
}
