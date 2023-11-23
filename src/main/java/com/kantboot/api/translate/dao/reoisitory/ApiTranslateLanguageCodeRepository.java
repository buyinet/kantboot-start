package com.kantboot.api.translate.dao.reoisitory;

import com.kantboot.api.translate.domain.entity.ApiTranslateLanguageCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiTranslateLanguageCodeRepository extends JpaRepository<ApiTranslateLanguageCode, Long>
{

    /**
     * 根据国际标准语言编码查询
     * @param code 国际标准语言编码
     * @return 国际标准语言编码
     */
    ApiTranslateLanguageCode findByCode(String code);

}
