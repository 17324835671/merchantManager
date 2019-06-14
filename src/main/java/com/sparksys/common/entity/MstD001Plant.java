package com.sparksys.common.entity;

import java.util.Date;

public class MstD001Plant extends CommonOutDto{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3208864251591967532L;

	private Long plantCode;

    private String address;

    private String buDeptName;

    private String buDeptTel;

    private Date cdTime;

    private Long deleteFlag;

    private String kanaName;

    private String plantName;

    private Date upTime;

    private Long cdPerson;

    private Long plantTypeId; 
    
    private String plantTypeName;

    private Long upPerson;
    
    private Long ownerUid;

    private String ownerName;

	public MstD001Plant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(Long plantCode) {
		this.plantCode = plantCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBuDeptName() {
		return buDeptName;
	}

	public void setBuDeptName(String buDeptName) {
		this.buDeptName = buDeptName;
	}

	public String getBuDeptTel() {
		return buDeptTel;
	}

	public void setBuDeptTel(String buDeptTel) {
		this.buDeptTel = buDeptTel;
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
		this.kanaName = kanaName;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
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

	public String getPlantTypeName() {
		return plantTypeName;
	}

	public void setPlantTypeName(String plantTypeName) {
		this.plantTypeName = plantTypeName;
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

	public Long getOwnerUid() {
		return ownerUid;
	}

	public void setOwnerUid(Long ownerUid) {
		this.ownerUid = ownerUid;
	}

	public Long getPlantTypeId() {
		return plantTypeId;
	}

	public void setPlantTypeId(Long plantTypeId) {
		this.plantTypeId = plantTypeId;
	}

}