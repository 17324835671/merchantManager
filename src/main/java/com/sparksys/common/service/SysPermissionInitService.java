package com.sparksys.common.service;

import java.util.List;
import java.util.Map;

import com.sparksys.common.entity.SysPermissionInit;

public interface SysPermissionInitService {
    
	public List<SysPermissionInit> findSysPermissionInitList();
	
	public Map<String, String> loadFilterChainDefinitions();
}
