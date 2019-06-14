package com.sparksys.common.entity;

import java.io.Serializable;

public class CommonOutDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6113279863321569106L;

	private Long userId;
	
	private String displayName;
	
	private String userName;
	
	private String cdName;

    private String upName;
    
    
	public CommonOutDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getCdName() {
		return cdName;
	}

	public void setCdName(String cdName) {
		this.cdName = cdName;
	}

	public String getUpName() {
		return upName;
	}

	public void setUpName(String upName) {
		this.upName = upName;
	}

}
