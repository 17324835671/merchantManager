package com.sparksys.common.entity;

import java.util.Date;

public class SysRole extends CommonOutDto{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5288254934675731680L;

	private Long roleId;

    private Long available;

    private Date cdTime;

    private String roleCode;

    private String roleName;

    private Date upTime;

    private String cdPerson;

    private String upPerson;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public Date getCdTime() {
        return cdTime;
    }

    public void setCdTime(Date cdTime) {
        this.cdTime = cdTime;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

	public String getCdPerson() {
		return cdPerson;
	}

	public void setCdPerson(String cdPerson) {
		this.cdPerson = cdPerson;
	}

	public String getUpPerson() {
		return upPerson;
	}

	public void setUpPerson(String upPerson) {
		this.upPerson = upPerson;
	}

}