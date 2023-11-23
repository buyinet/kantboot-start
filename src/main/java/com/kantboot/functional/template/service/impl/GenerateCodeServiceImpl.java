package com.kantboot.functional.template.service.impl;

import cn.hutool.core.io.FileUtil;
import com.kantboot.functional.template.domain.dto.DependencyDTO;
import com.kantboot.functional.template.domain.dto.FunctionalTemplateGenerateDTO;
import com.kantboot.functional.template.domain.dto.GenerateModuleDTO;
import com.kantboot.functional.template.domain.dto.KantbootModuleDTO;
import com.kantboot.functional.template.service.IFunctionalTemplateService;
import com.kantboot.functional.template.service.IGenerateCodeService;
import jakarta.annotation.Resource;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class GenerateCodeServiceImpl implements IGenerateCodeService {

    @Resource
    private IFunctionalTemplateService templateService;

    @Override
    public void generateModule(GenerateModuleDTO dto) {
        File file = new File(dto.getPath() + "/" + dto.getModuleName());
        File file2 = new File(dto.getPath() + "/" + dto.getModuleName() + "/src/main/resources");
        File file3 = new File(dto.getPath() + "/" + dto.getModuleName() + "/src/test/java");

        // 如果文件的packging类型不是pom，且包名不为空，且包名不是空字符串，那么就创建包名文件夹
        if (!"pom".equals(dto.getPackagingType())
                && dto.getPackageName() != null
                && !dto.getPackageName().equals("")) {
            file = new File(dto.getPath() + "/" + dto.getModuleName() + "/src/main/java/" + dto.getPackageName().replaceAll("\\.", "/"));
            file3 = new File(dto.getPath() + "/" + dto.getModuleName() + "/src/test/java/" + dto.getPackageName().replaceAll("\\.", "/"));
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (!file3.exists()) {
            file3.mkdirs();
        }

    }

    @Override
    public String generatePom(GenerateModuleDTO dto) {
        File file = new File(dto.getPath() + "/" + dto.getModuleName());
        if (!file.exists()) {
            file.mkdirs();
        }

        FunctionalTemplateGenerateDTO templateGenerateDTO = new FunctionalTemplateGenerateDTO();
        templateGenerateDTO.setGroupCode("springBoot");
        templateGenerateDTO.setCode("pomXml");
        templateGenerateDTO.setData(dto);
        String generate = templateService.generate(templateGenerateDTO);
        // 写入pom.xml文件
        File pomFile = new File(dto.getPath() + "/" + dto.getModuleName() + "/pom.xml");
        // 如果文件不存在，那么就创建文件
        if (!pomFile.exists()) {
            try {
                pomFile.createNewFile();
                // 写入文件
                FileUtil.writeUtf8String(generate, pomFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return generate;
    }

    private GenerateModuleDTO createModuleDTO(String path, String baseName, String basePackage, String moduleSuffix, String packagingType, String description) {
        GenerateModuleDTO moduleDTO = new GenerateModuleDTO();
        moduleDTO.setPath(path);
        moduleDTO.setModuleName(baseName + moduleSuffix);
        moduleDTO.setPackageName(basePackage);
        moduleDTO.setParentDom(new DependencyDTO()
                .setGroupId(basePackage)
                .setArtifactId(baseName)
                .setVersion("0.0.1-SNAPSHOT")
                .setRelativePath("../pom.xml")
        );
        if (moduleSuffix.equals("")) {
            moduleDTO.setSelfDom(new DependencyDTO()
                    .setArtifactId(baseName)
                    .setDescription(description)
            );
        } else {
            moduleDTO.setSelfDom(new DependencyDTO()
                    .setArtifactId(baseName + moduleSuffix)
                    .setDescription(description + moduleSuffix)
            );
        }
        moduleDTO.setPackagingType(packagingType);
        return moduleDTO;
    }

    @Override
    @Synchronized
    public void generateKantbootModule(KantbootModuleDTO dto) {
        // 创建父模块
        GenerateModuleDTO parentDto = createModuleDTO(dto.getPath(), dto.getBaseName(), dto.getBasePackage(), "", dto.getPackagingType(), dto.getDescription());
        generateModule(parentDto);
        generatePom(parentDto);

        // 创建子模块-domain
        GenerateModuleDTO domainDTO = createModuleDTO(dto.getPath() + "/" + dto.getBaseName(), dto.getBaseName(), dto.getBasePackage() + ".domain", "-domain", "jar", dto.getDescription());
        generateModule(domainDTO);
        generatePom(domainDTO);
        // 在domain模块下创建entity文件夹
        File entityFile = new File(domainDTO.getPath() + "/" + domainDTO.getModuleName() + "/src/main/java/" + domainDTO.getPackageName().replaceAll("\\.", "/") + "/entity");
        if (!entityFile.exists()) {
            entityFile.mkdirs();
        }
        // 在domain模块下创建dto文件夹
        File dtoFile = new File(domainDTO.getPath() + "/" + domainDTO.getModuleName() + "/src/main/java/" + domainDTO.getPackageName().replaceAll("\\.", "/") + "/dto");
        if (!dtoFile.exists()) {
            dtoFile.mkdirs();
        }
        // 在domain模块下创建vo文件夹
        File voFile = new File(domainDTO.getPath() + "/" + domainDTO.getModuleName() + "/src/main/java/" + domainDTO.getPackageName().replaceAll("\\.", "/") + "/vo");
        if (!voFile.exists()) {
            voFile.mkdirs();
        }

        // 创建子模块-service
        GenerateModuleDTO serviceDTO = createModuleDTO(dto.getPath() + "/" + dto.getBaseName(), dto.getBaseName(), dto.getBasePackage() + ".service", "-service", "jar", dto.getDescription());
        generateModule(serviceDTO);
        generatePom(serviceDTO);

        // 创建子模块-dao
        GenerateModuleDTO daoDTO = createModuleDTO(dto.getPath() + "/" + dto.getBaseName(), dto.getBaseName(), dto.getBasePackage() + ".dao", "-dao", "jar", dto.getDescription());
        generateModule(daoDTO);
        generatePom(daoDTO);
        // 在dao模块下创建repository文件夹
        File file = new File(daoDTO.getPath() + "/" + daoDTO.getModuleName() + "/src/main/java/" + daoDTO.getPackageName().replaceAll("\\.", "/") + "/repository");
        if (!file.exists()) {
            file.mkdirs();
        }

        // 创建子模块-web
        GenerateModuleDTO rpcServiceDTO = createModuleDTO(dto.getPath() + "/" + dto.getBaseName(), dto.getBaseName(), dto.getBasePackage() + ".rpc.service", "-rpc-service-api", "", dto.getDescription());
        generateModule(rpcServiceDTO);
        generatePom(rpcServiceDTO);

        // 创建子模块-web
        GenerateModuleDTO webDTO = createModuleDTO(dto.getPath() + "/" + dto.getBaseName(), dto.getBaseName(), dto.getBasePackage() + ".web", "-web", "jar", dto.getDescription());
        generateModule(webDTO);
        generatePom(webDTO);

    }
}
