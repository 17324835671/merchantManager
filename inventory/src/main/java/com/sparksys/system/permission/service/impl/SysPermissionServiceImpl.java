package com.sparksys.system.permission.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.sparksys.common.entity.SysUser;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.SysPermissionDao;
import com.sparksys.common.dao.SysUserDao;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SysPermission;
import com.sparksys.common.utils.SnowFlakeId;
import com.sparksys.system.permission.service.ISysPermissionService;

@Service
public class SysPermissionServiceImpl implements ISysPermissionService {
	
	@Resource
	private SysPermissionDao permissionDao;
	
	@Resource
	private SysUserDao userDao;
	
	@Override
	public void deleteSysPermission(SysPermission permission) {
		permissionDao.deleteSysPermissionById(permission.getPermissionId());

	}

	@Override
	public void saveSysPermission(SysPermission permission) {
		if(permission.getPermissionId()==null||"".equals(permission.getPermissionId())) {
			permission.setPermissionId(SnowFlakeId.getSnowFlakeId());
			permissionDao.insertSysPermission(permission);
		}
	}

	@Override
	public SysPermission findSysPermissionById(Long permissionId) {
		SysPermission permission= permissionDao.findSysPermissionById(permissionId);
		return permission;
	}

	public void updateSysPermission(SysPermission permission) {
		if(permission.getPermissionId()!=null&&!"".equals(permission.getPermissionId())) {
			permissionDao.updateSysPermission(permission);
		}
	}

	@Override
	public List<SysPermission> findSysPermissionList() {
		List<SysPermission> parentList = permissionDao.findParentPermissionList();
		List<SysPermission> nodePermissionList = permissionDao.findNodePermissiontList();
		for (SysPermission permission : parentList) {
			List<SysPermission> nodeList = new ArrayList<>();
			for (SysPermission nodePermission : nodePermissionList) {
				if(permission.getPermissionId().equals(nodePermission.getParentId())) {
					nodeList.add(nodePermission);
				}
			}
			permission.setPermissions(nodeList);
		}
		return parentList;
	}

	@Override
	public PageBean<SysPermission> findSysPermissionPageList(int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<SysPermission> list = findSysPermissionList();
		int totalCount = permissionDao.findysPermissionCount();
		PageBean<SysPermission> pageData = new PageBean<>(currentPage, pageSize, totalCount);
		pageData.setItems(list);
		return pageData;
	}

	@Override
	public List<SysPermission> findNodePermissiontList() {
		List<SysPermission> list = permissionDao.findNodePermissiontList();
		return list;
	}

	@Override
	public List<SysPermission> findUserPermissions(Long userId) {
		List<SysPermission> list = permissionDao.findUserPermissions(userId);
		return list;
	}

	@Override
	public List<SysPermission> findRolePermissionList(Long roleId) {
		List<SysPermission> list = permissionDao.findRolePermissionList(roleId);
		return list;
	}

	@Override
	public List<SysPermission> findMenuList() {
		List<SysPermission> parentList = permissionDao.findMenuParentList();
		List<SysPermission> nodePermissionList = permissionDao.findMenuNodeList();
		for (SysPermission permission : parentList) {
			List<SysPermission> nodeList = new ArrayList<>();
			for (SysPermission nodePermission : nodePermissionList) {
				if(permission.getPermissionId().equals(nodePermission.getParentId())) {
					nodeList.add(nodePermission);
				}
			}
			permission.setPermissions(nodeList);
		}
		return parentList;
	}
	@Override
	public List<SysPermission> findMenuList2(SysUser sessionUserBean) {
		Long userId=sessionUserBean.getUserId();
		List<SysPermission> parentList = permissionDao.findMenuParentList2(userId);
		List<SysPermission> nodePermissionList = permissionDao.findMenuNodeList();
		for (SysPermission permission : parentList) {
			List<SysPermission> nodeList = new ArrayList<>();
			for (SysPermission nodePermission : nodePermissionList) {
				if(permission.getPermissionId().equals(nodePermission.getParentId())) {
					nodeList.add(nodePermission);
				}
			}
			permission.setPermissions(nodeList);
		}
		return parentList;
	}

}
