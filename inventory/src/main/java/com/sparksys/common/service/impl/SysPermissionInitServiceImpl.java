package com.sparksys.common.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sparksys.common.dao.SysPermissionDao;
import com.sparksys.common.dao.SysPermissionInitDao;
import com.sparksys.common.entity.SysPermission;
import com.sparksys.common.entity.SysPermissionInit;
import com.sparksys.common.service.SysPermissionInitService;


@Service
public class SysPermissionInitServiceImpl implements SysPermissionInitService {
	
	@Resource
	private SysPermissionInitDao permissionInitDao;

	@Resource
	private SysPermissionDao permissionDao;
	
	@Override
	public List<SysPermissionInit> findSysPermissionInitList() {
		List<SysPermissionInit> permissionInitList = permissionInitDao.findSysPermissionInitList();
		return permissionInitList;
	}

	@Override
	public Map<String, String> loadFilterChainDefinitions() {
		List<SysPermission> permissionList = permissionDao.findNodePermissiontList();
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		List<SysPermissionInit> sysPermissionInits = permissionInitDao.findSysPermissionInitList();
		//公共资源配置
		for (SysPermissionInit sysPermissionInit : sysPermissionInits) {
			filterChainDefinitionMap.put(sysPermissionInit.getPermissionInitUrl(), sysPermissionInit.getPermissionInitCode());
		}
		if (permissionList.size() > 0) {
			for (SysPermission permission : permissionList) {
				filterChainDefinitionMap.put(permission.getPermissionUrl(),"perms["+permission.getPermissionCode()+"],kickout");
			}
        }
        //拦截所有请求
        filterChainDefinitionMap.put("/**", "kickout,authc");
		return filterChainDefinitionMap;
	}

}
