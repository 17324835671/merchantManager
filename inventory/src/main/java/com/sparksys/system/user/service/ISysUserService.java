package com.sparksys.system.user.service;

import java.util.List;
import java.util.Map;

import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SysUser;

public interface ISysUserService {
	/**
	 * 	删除用户
	 * @param user
	 */
	void deleteSysUer(SysUser user);
	/**
	 *	 保存用户信息
	 * @param user
	 */
	void saveSysUer(SysUser user);
	/**
	 * 	查看用户信息
	 * @param userId
	 * @return
	 */
	SysUser findSysUerById(Long userId);
	/**
	 * 	根据用户名和密码查询用户信息
	 * @param map
	 * @return
	 */
    SysUser findSysUerByUserNamePwd(Map<String, Object> map);
    /**
     * 	更新用户信息
     * @param user
     */
    void updateSysUer(SysUser user);
    /**
     *	查询用户列表
     * @param map
     * @return
     */
    List<SysUser> findSysUserList(Map<String, Object> map);
    /**
     * 	分页查询用户列表
     * @param currentPage
     * @param pageSize
     * @param map
     * @return
     */
    PageBean<SysUser> findSysUserPageList(int currentPage,int pageSize,Map<String, Object> map);
    /**
     * 查询角色下的用户
     * @param roleId
     * @return
     */
    List<SysUser> findRoleUserList(Long roleId);
    
}
