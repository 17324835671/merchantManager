package com.sparksys.common.entity;

import java.io.Serializable;

public class SysUserOnline implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6844303724099319231L;

	private String sessionId;

	private String host;

	private String startTime;

	private String lastAccess;

	private long timeout;

	private boolean sessionStatus = Boolean.TRUE;
	
	private String displayName;
	
	private String userName;
	public SysUserOnline() {
		super();
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getLastAccess() {
		return lastAccess;
	}
	public void setLastAccess(String lastAccess) {
		this.lastAccess = lastAccess;
	}
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	public boolean isSessionStatus() {
		return sessionStatus;
	}
	public void setSessionStatus(boolean sessionStatus) {
		this.sessionStatus = sessionStatus;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
