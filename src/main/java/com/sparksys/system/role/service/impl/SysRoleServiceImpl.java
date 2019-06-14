package com.sparksys.system.role.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.SysPermissionDao;
import com.sparksys.common.dao.SysRoleDao;
import com.sparksys.common.dao.SysUserDao;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SysRole;
import com.sparksys.common.utils.SnowFlakeId;
import com.sparksys.system.role.service.ISysRoleService;
@Service
public class SysRoleServiceImpl implements ISysRoleService {
	@Resource
	private SysRoleDao roleDao;
	
	@Resource
	private SysUserDao userDao;
	
	@Resource
	private SysPermissionDao permissionDao;
	
	@Override
	public void deleteSysRoleById(SysRole role) {
		if(role.getRoleId()!=null&&!"".equals(role.getRoleId())) {
			roleDao.deleteSysRoleById(role.getRoleId());
		}
	}

	@Override
	public void saveSysRole(SysRole role) {
		if(role.getRoleId()==null||"".equals(role.getRoleId())) {
			role.setRoleId(SnowFlakeId.getSnowFlakeId());
			roleDao.insertSysRole(role);
		}
	}

	@Override
	public SysRole findSysRoleById(Long roleId) {
		SysRole role = roleDao.findSysRoleById(roleId);
		return role;
	}

	@Override
	public void updateSysRole(SysRole role) {
		if(role.getRoleId()!=null&&!"".equals(role.getRoleId())) {
			roleDao.updateSysRole(role);
		}

	}

	@Override
	public void deleteUserRole(Long roleId,List<String> userIds) {
		Map<String, Object> map = new HashMap<>();
		map.put("roleId", roleId);
		for (String userId : userIds) {
			map.put("userId", userId);
			roleDao.deleteUserRole(map);
			map.remove("userId");
		}
	}

	public void deleteRolePermission(Long roleId,List<String> permissionIds) {
		Map<String, Object> map = new HashMap<>();
		map.put("roleId", roleId);
		for (String permissionId : permissionIds) {
			map.put("permissionId", permissionId);
			roleDao.deleteRolePermission(map);
			map.remove("permissionId");
		}
	}

	@Override
	public List<SysRole> findUserRoles(Long userId) {
		List<SysRole> list = roleDao.findUserRoles(userId);
		return list;
	}

	@Override
	public PageBean<SysRole> findSysRolePageList(int currentPage, int pageSize,Map<String, Object> map) {
		PageHelper.startPage(currentPage, pageSize);
		List<SysRole> list = roleDao.findSysRoleList(map);
		int totalCount = roleDao.findSysRoleCount(map);
		PageBean<SysRole> pageData = new PageBean<>(currentPage, pageSize, totalCount);
		pageData.setItems(list);
		return pageData;
	}
	
	@Override
	public List<SysRole> findysRoleByList() {
		List<SysRole> list = roleDao.findSysRoleList(null);
		return list;
	}
	
	@Override
	public void saveUserRole(Long roleId,List<String> userIds) {
		Map<String, Object> map = new HashMap<>();
		map.put("roleId", roleId);
		List<Long> userIdList = userDao.findRoleUserIdList(roleId);
		for (int i = 0; i < userIds.size(); i++) {
			if(userIdList.size()>0) {
				for (int j = 0; j < userIdList.size(); j++) {
					if(userIds.get(i).equals(userIdList.get(j))) {
						break;
					}else if (j==userIdList.size()-1) {
						map.put("userId", userIds.get(i));
						roleDao.saveUserRole(map);
						map.remove("userId");
					}
				}
			}else {
				map.put("userId", userIds.get(i));
				roleDao.saveUserRole(map);
				map.remove("userId");
			}
		}
	}
	
	public void saveRolePermission(Long roleId,List<String> permissionIds) {
		Map<String, Object> map = new HashMap<>();
		map.put("roleId", roleId);
		List<String> permissionIdList = permissionDao.findRolePermissionIdList(roleId);
		for (int i = 0; i < permissionIds.size(); i++) {
			if(permissionIdList.size()>0) {
				for (int j = 0; j < permissionIdList.size(); j++) {
					if(permissionIds.get(i).equals(permissionIdList.get(j))) {
						break;
					}else if (j==permissionIdList.size()-1) {
						map.put("permissionId", permissionIds.get(i));
						roleDao.saveRolePermission(map);
						map.remove("permissionId");
					}
				}
			}else {
				map.put("permissionId", permissionIds.get(i));
				roleDao.saveRolePermission(map);
				map.remove("permissionId");
			}
		}
	}

}
