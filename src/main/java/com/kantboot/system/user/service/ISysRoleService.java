package com.kantboot.system.user.service;

import com.kantboot.system.user.domain.dto.SysRoleSaveDTO;
import com.kantboot.system.user.domain.entity.SysRole;
import com.kantboot.system.user.domain.vo.SysRoleVO;

import java.util.List;
import java.util.Map;

/**
 * 角色服务接口
 * @author 方某方
 */
public interface ISysRoleService {

    /**
     * 查询所有角色编码
     * @return 角色编码列表
     */
    List<String> getCodes();

    /**
     * 获取所有角色
     * @return 角色列表
     */
    List<SysRole> getAll();

    /**
     * 获取所有角色VO
     * @return 角色VO列表
     */
    List<SysRoleVO> getAllVO();

    /**
     * 获取角色map
     * @return 角色map
     */
    Map<String,String> getMap();

    /**
     * 根据角色编码获取角色
     * @param code 角色编码
     * @return 角色
     */
    SysRole getByCode(String code);

    /**
     * 保存vo
     * @param vo 角色vo
     * @return 角色
     */
    SysRoleVO saveVO(SysRoleSaveDTO vo);

}
