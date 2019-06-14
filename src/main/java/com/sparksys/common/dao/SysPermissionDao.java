package com.sparksys.common.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.SysPermission;
@Repository
public interface SysPermissionDao {
	
    int deleteSysPermissionById(Long permissionId);

    int insertSysPermission(SysPermission permission);

    SysPermission findSysPermissionById(Long permissionId);

    int updateSysPermission(SysPermission permission);
    /**
     * 	权限查询--父权限
     * @return
     */
    List<SysPermission> findParentPermissionList();
    /**
     *	权限查询--子权限 
     * @return
     */
    List<SysPermission> findNodePermissiontList();
    /**
     *	菜单查询--父菜单 
     * @return
     */
    List<SysPermission> findMenuParentList();
    /**
     *	菜单查询--子菜单 
     * @return
     */
    List<SysPermission> findMenuNodeList();
    
    int findysPermissionCount();
    
    List<SysPermission> findRolePermissionList(Long roleId);
    
    List<String> findRolePermissionIdList(Long roleId);
    
    List<SysPermission> findUserPermissions(Long userId);

    List<SysPermission> findMenuParentList2(Long userId);
}