package com.sparksys.inventory.online.service;

import java.io.Serializable;
import java.util.List;

import com.sparksys.common.entity.SysUserOnline;

public interface UserOnlineService {
	
 public List<SysUserOnline> findSysUserOnlineList();
 
 public void kickout(Serializable sessionId);
 
}
