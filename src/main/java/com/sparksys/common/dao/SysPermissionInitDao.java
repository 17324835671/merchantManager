package com.sparksys.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.SysPermissionInit;
@Repository
public interface SysPermissionInitDao {
	
	public List<SysPermissionInit> findSysPermissionInitList();
}