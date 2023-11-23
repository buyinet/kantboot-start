package com.kantboot.system.dict.dao.repository;

import com.kantboot.system.dict.domain.entity.SysDictI18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 字典国际化数据访问层接口
 * @author 方某方
 */
public interface SysDictI18nRepository extends JpaRepository<SysDictI18n, Long>
{

    /**
     * 根据分组code和语言code查询字典国际化列表
     */
    List<SysDictI18n> findByDictGroupCodeAndLanguageCode(String dictGroupCode, String languageCode);


    /**
     * 根据字典分组和语言编码查询字典国际化列表
     * @param dictGroupCode 字典分组编码
     * @param languageLocalizedCode 语言编码
     *                              这个编码对应着SysLanguageLocalized表中的code字段
     *                              再通过SysLanguageLocalized表中的languageCode字段查询
     * @return 字典国际化列表
     */
    @Query(value = """
    SELECT dictI18n FROM SysDictI18n dictI18n
        LEFT JOIN SysLanguageLocalized languageLocalized 
        ON dictI18n.languageCode = languageLocalized.languageCode
    WHERE dictI18n.dictGroupCode = :dictGroupCode
        AND languageLocalized.code = :languageLocalizedCode
    """)
    List<SysDictI18n> findByDictGroupCodeAndLanguageLocalizedCode(
            @Param("dictGroupCode") String dictGroupCode,
            @Param("languageLocalizedCode") String languageLocalizedCode);

    /**
     * 根据字典编码、字典分组编码和语言编码查询值
     * @param dictCode 字典编码
     * @param dictGroupCode 字典分组编码
     * @param languageCode 语言编码
     * @return 值（一个字段）
     */
    @Query(value = """
        select dictI18n.value from SysDictI18n dictI18n 
        where dictI18n.dictCode = :dictCode 
        and dictI18n.dictGroupCode = :dictGroupCode
        and dictI18n.languageCode = :languageCode
        """)
    String findValue(
            @Param("dictCode") String dictCode,
            @Param("dictGroupCode") String dictGroupCode,
            @Param("languageCode") String languageCode);

    /**
     * 根据dictCode、dictGroupCode和languageCode删除字典国际化
     * @param dictCodes       字典代码列表
     * @param dictGroupCodes  字典组代码列表
     * @param languageCodes   语言代码列表
     */
    @Modifying
    @Query("DELETE FROM SysDictI18n dictI18n WHERE dictI18n.dictCode IN :dictCodes AND dictI18n.dictGroupCode IN :dictGroupCodes AND dictI18n.languageCode IN :languageCodes")
    int deleteByDictCodesAndDictGroupCodesAndLanguageCodes(@Param("dictCodes") List<String> dictCodes, @Param("dictGroupCodes") List<String> dictGroupCodes, @Param("languageCodes") List<String> languageCodes);





}
