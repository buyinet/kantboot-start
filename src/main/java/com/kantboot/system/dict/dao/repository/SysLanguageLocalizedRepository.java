package com.kantboot.system.dict.dao.repository;

import com.kantboot.system.dict.domain.entity.SysLanguageLocalized;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysLanguageLocalizedRepository
extends JpaRepository<SysLanguageLocalized,Long>
{

    /**
     * 根据code查询
     */
    SysLanguageLocalized findByCode(String code);


}
