package com.sparksys.system.role.service;

import java.util.List;
import java.util.Map;

import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SysRole;

public interface ISysRoleService {
	/**
	 * 	删除角色信息
	 * @param role
	 */
	void deleteSysRoleById(SysRole role);
	/**
	 *	 保存角色信息
	 * @param role
	 */
    void saveSysRole(SysRole role);
    /**
     * 	根据roleId查询角色信息
     * @param roleId
     * @return
     */
    SysRole findSysRoleById(Long roleId);
    /**
     *	 更新角色信息
     * @param role
     */
    void updateSysRole(SysRole role);
    /**
     * 	删除用户角色关联
     * @param map
     */
    void deleteUserRole(Long roleId,List<String> userIds);
    /**
     * 	保存用户角色关联
     * @param map
     */
    void saveUserRole(Long roleId,List<String> userIds);
    /**
     * 	删除角色权限关联
     * @param map
     */
    void deleteRolePermission(Long roleId,List<String> permissionIds);
    /**
     * 	保存角色权限关联
     * @param map
     */
    void saveRolePermission(Long roleId,List<String> permissionIds);
    /**
     * 	根据userId查询用户所拥有的角色
     * @param userId
     * @return
     */
    List<SysRole> findUserRoles(Long userId);
    /**
     * 	分页查询角色列表
     * @param currentPage
     * @param pageSize
     * @param map
     * @return
     */
    PageBean<SysRole> findSysRolePageList(int currentPage,int pageSize,Map<String, Object> map);
    /**
     * 	查询所有的角色信息
     * @return
     */
    List<SysRole> findysRoleByList();
}
