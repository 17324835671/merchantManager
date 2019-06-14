package com.sparksys.system.permission.service;

import java.util.List;

import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SysPermission;
import com.sparksys.common.entity.SysUser;

public interface ISysPermissionService {
	/**
	 * 删除权限信息
	 * @param permission
	 */
	void deleteSysPermission(SysPermission permission);
	/**
	 * 保存权限信息
	 * @param permission
	 */
	void saveSysPermission(SysPermission permission);
	/**
	 * 根据permissionId查看权限信息
	 * @param permissionId
	 * @return
	 */
	SysPermission findSysPermissionById(Long permissionId);
	/**
	 * 更新权限信息
	 * @param permission
	 */
    void updateSysPermission(SysPermission permission);
    /**
     *	 查询所有权限
     * @return
     */
    List<SysPermission> findSysPermissionList();
    /**
     * 	查询菜单
     * @return
     */
    List<SysPermission> findMenuList();
	List<SysPermission> findMenuList2(SysUser sessionUserBean);
    /**
     * 分页查询角色
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<SysPermission> findSysPermissionPageList(int currentPage,int pageSize);
    /**
     * 子权限列表
     * @return
     */
    List<SysPermission> findNodePermissiontList();
    /**
     * 查看角色下的权限
     * @param roleId
     * @return
     */
    List<SysPermission> findRolePermissionList(Long roleId);
    /**
     * 查看用户权限
     * @param userId
     * @return
     */
    List<SysPermission> findUserPermissions(Long userId);
}
