package com.sparksys.common.entity;

import java.util.Date;

public class MstD004Shelf extends CommonOutDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8728155827528588665L;

	private Long shelfId;

    private Date cdTime;

    private Long deleteFlag;

    private String shelfCode;

    private String shelfName;

    private Date upTime;

    private Long cdPerson;

    private Long locationCode;

    private Long shelfTypeId;
    
    private String shelfTypeName;

    private Long upPerson;

    private String warehouseName;
    
    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
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

    public String getShelfCode() {
        return shelfCode;
    }

    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode == null ? null : shelfCode.trim();
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName == null ? null : shelfName.trim();
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

    public Long getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(Long locationCode) {
        this.locationCode = locationCode;
    }

    public Long getShelfTypeId() {
        return shelfTypeId;
    }

    public void setShelfTypeId(Long shelfTypeId) {
        this.shelfTypeId = shelfTypeId;
    }

    public Long getUpPerson() {
        return upPerson;
    }

    public void setUpPerson(Long upPerson) {
        this.upPerson = upPerson;
    }

	public String getShelfTypeName() {
		return shelfTypeName;
	}

	public void setShelfTypeName(String shelfTypeName) {
		this.shelfTypeName = shelfTypeName;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
    
}