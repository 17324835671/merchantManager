package com.sparksys.common.entity;

import java.util.Date;

public class MstD006Department extends CommonOutDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6780188999901909521L;

	private Long deptUid;

    private Date cdTime;

    private Long deleteFlag;

    private String deptCode;

    private String deptHead;

    private String deptName;

    private String deptTelno;

    private Date upTime;

    private Long cdPerson;

    private Long ownerUid;

    private Long upPerson;

    private String ownerName;
    
    public Long getDeptUid() {
        return deptUid;
    }

    public void setDeptUid(Long deptUid) {
        this.deptUid = deptUid;
    }

    public Date getCdTime() {
        return cdTime;
    }

    public void setCdTime(Date cdTime) {
        this.cdTime = cdTime;
    }

    public Long getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Long deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    public String getDeptHead() {
        return deptHead;
    }

    public void setDeptHead(String deptHead) {
        this.deptHead = deptHead == null ? null : deptHead.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDeptTelno() {
        return deptTelno;
    }

    public void setDeptTelno(String deptTelno) {
        this.deptTelno = deptTelno == null ? null : deptTelno.trim();
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Long getCdPerson() {
        return cdPerson;
    }

    public void setCdPerson(Long cdPerson) {
        this.cdPerson = cdPerson;
    }

    public Long getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(Long ownerUid) {
        this.ownerUid = ownerUid;
    }

    public Long getUpPerson() {
        return upPerson;
    }

    public void setUpPerson(Long upPerson) {
        this.upPerson = upPerson;
    }

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

}