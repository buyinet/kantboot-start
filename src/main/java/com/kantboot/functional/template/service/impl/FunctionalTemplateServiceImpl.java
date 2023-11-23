package com.kantboot.functional.template.service.impl;

import com.kantboot.functional.template.dao.repository.FunctionalTemplateRepository;
import com.kantboot.functional.template.domain.dto.FunctionalTemplateGenerateDTO;
import com.kantboot.functional.template.domain.entity.FunctionalTemplate;
import com.kantboot.functional.template.service.IFunctionalTemplateService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;

@Service
public class FunctionalTemplateServiceImpl implements IFunctionalTemplateService {

    @Resource
    private FunctionalTemplateRepository repository;

    @Override
    public FunctionalTemplate getByGroupCodeAndCode(String groupCode, String code) {
        return repository.getByGroupCodeAndCode(groupCode,code);
    }

    @SneakyThrows
    @Override
    public String generate(FunctionalTemplateGenerateDTO dto) {
        FunctionalTemplate byGroupCodeAndCode = getByGroupCodeAndCode(dto.getGroupCode(), dto.getCode());
        // 获取模板内容
        String content = byGroupCodeAndCode.getContent();

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        // 使用 freemarker
        Template template = new Template("content", content, configuration);
        // 生成模板
        StringWriter stringWriter = new StringWriter();
        template.process(dto.getData(), stringWriter);
        return stringWriter.toString();
    }

    @Override
    public List<FunctionalTemplate> getByGroupCode(String groupCode) {
        return repository.getByGroupCode(groupCode);
    }
}
