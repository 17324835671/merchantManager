package com.sparksys.common.entity;

import java.util.Date;

public class MstD005Owner extends CommonOutDto{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8025475681303108340L;

	private Long ownerUid;

    private String address;

    private Date cdTime;

    private Long deleteFlag;

    private String kanaName;

    private String ownerCode;

    private String ownerName;

    private Date upTime;

    private Long cdPerson;

    private Long upPerson;

    public Long getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(Long ownerUid) {
        this.ownerUid = ownerUid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public String getKanaName() {
        return kanaName;
    }

    public void setKanaName(String kanaName) {
        this.kanaName = kanaName == null ? null : kanaName.trim();
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode == null ? null : ownerCode.trim();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
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

    public Long getUpPerson() {
        return upPerson;
    }

    public void setUpPerson(Long upPerson) {
        this.upPerson = upPerson;
    }
}