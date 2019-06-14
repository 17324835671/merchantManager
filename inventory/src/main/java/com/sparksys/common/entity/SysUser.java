package com.sparksys.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.sparksys.common.utils.UtilTools;

public class SysUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7727349075210738876L;

	private Long userId;

    private Date birthDay;

    private Date cdTime;

    private String displayName;

    private String email;

    private Long isdel;

    private String password;

    private Long sex;

    private Long state;

    private String telPhone;

    private Date upTime;

    private String userName;

    private String wechat;

    private Date lastLoginTime;

    private Long version;
    
	public SysUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Date getCdTime() {
		return cdTime;
	}

	public void setCdTime(Date cdTime) {
		this.cdTime = cdTime;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIsdel() {
		return isdel;
	}

	public void setIsdel(Long isdel) {
		this.isdel = isdel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = UtilTools.SM3(password);
	}

	public Long getSex() {
		return sex;
	}

	public void setSex(Long sex) {
		this.sex = sex;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public Date getUpTime() {
		return upTime;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
    
}