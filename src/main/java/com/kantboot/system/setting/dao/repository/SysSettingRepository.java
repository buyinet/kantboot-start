package com.kantboot.system.setting.dao.repository;

import com.kantboot.system.setting.domain.entity.SysSetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 系统设置数据访问层
 * @author 方某方
 */
public interface SysSettingRepository extends JpaRepository<SysSetting,Long>
{

    /**
     * 根据分组编码查询
     * @param groupCode 分组编码
     * @return 系统设置
     */
    List<SysSetting> findByGroupCode(String groupCode);

    /**
     * 根据分组编码和编码查询
     * @param groupCode 分组编码
     * @param code 编码
     * @return 系统设置
     */
    SysSetting findByGroupCodeAndCode(String groupCode, String code);

    /**
     * 根据条件查询
     */
    @Query("""
        FROM SysSetting r
        WHERE (:#{#param.groupCode} IS NULL OR :#{#param.groupCode} = '' OR r.groupCode = :#{#param.groupCode})
        AND (:#{#param.code} IS NULL OR :#{#param.code} = '' OR r.code = :#{#param.code})
        AND (:#{#param.name} IS NULL OR :#{#param.name} = '' OR r.name LIKE CONCAT('%', :#{#param.name}, '%'))
        AND (:#{#param.value} IS NULL OR :#{#param.value} = '' OR r.value LIKE CONCAT('%', :#{#param.value}, '%'))
        AND (:#{#param.priority} IS NULL OR :#{#param.priority} = '' OR r.priority = :#{#param.priority})
        AND (:#{#param.description} IS NULL OR :#{#param.description} = '' OR r.description LIKE CONCAT('%', :#{#param.description}, '%'))
        ORDER BY r.priority DESC
        """)
    Page<SysSetting> getBodyData(@Param("param") SysSetting param, Pageable pageable);

}
