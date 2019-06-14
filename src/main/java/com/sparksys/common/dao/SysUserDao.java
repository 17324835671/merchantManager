package com.sparksys.common.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.SysUser;
@Repository
public interface SysUserDao {
    
	public int deleteSysUserById(Long userId);

	public int insertSysUser(SysUser uer);

	public SysUser findSysUserById(Long userId);
    
	public SysUser findSysUserByUserNamePwd(Map<String, Object> map);

	public int updateSysUser(SysUser uer);
    
    public int findSysUserListCount(Map<String, Object> map);
    
    public List<SysUser> findSysUserList(Map<String, Object> map);
    
    public List<SysUser> findRoleUserList(Long roleId);
    
    public List<Long> findRoleUserIdList(Long roleId);
    
    public List<Long> findPermissionUserIdList(Long permissionId);
    
}