package com.kantboot.system.dict.dao.repository;

import com.kantboot.system.dict.domain.entity.SysLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 字典国际化数据访问层接口
 * @author 方某方
 */
public interface SysLanguageRepository extends JpaRepository<SysLanguage, Long> {

    /**
     * 查询所有支持的语言
     * @return 语言列表
     */
    List<SysLanguage> findBySupportTrue();

}
