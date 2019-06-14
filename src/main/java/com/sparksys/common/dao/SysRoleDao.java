package com.sparksys.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.SysRole;
@Repository
public interface SysRoleDao {
	
    int deleteSysRoleById(Long roleId);

    int insertSysRole(SysRole role);

    SysRole findSysRoleById(Long roleId);

    int updateSysRole(SysRole role);
    
    int deleteUserRole(Map<String, Object> map);
    
    int saveUserRole(Map<String, Object> map);
    
    int deleteRolePermission(Map<String, Object> map);
    
    int saveRolePermission(Map<String, Object> map);
    
    List<SysRole> findUserRoles(Long userId);
    
    List<SysRole> findSysRoleList(Map<String, Object> map);

	int findSysRoleCount(Map<String, Object> map);
}