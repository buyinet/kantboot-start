package com.kantboot.functional.template.dao.repository;

import com.kantboot.functional.template.domain.entity.FunctionalTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FunctionalTemplateRepository extends JpaRepository<FunctionalTemplate,Long> {

    /**
     * 根据分组编码和编码查询
     * @param groupCode
     * @param code
     * @return FunctionalTemplate
     */
    FunctionalTemplate getByGroupCodeAndCode(String groupCode, String code);

    /**
     * 根据分组编码获取
     * @param groupCode
     * @return List<FunctionalTemplate>
     */
    List<FunctionalTemplate> getByGroupCode(String groupCode);

}
