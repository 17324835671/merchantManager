package com.sparksys.common.entity;

import java.util.Date;

public class SSensorDevice extends CommonOutDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -51058562241729189L;

	private Long deviceid;

    private Date cdTime;

    private Long deleteFlag;

    private String devicename;

    private String deviceno;

    private String deviceproperty;

    private Date upTime;

    private Long cdPerson;

    private Long devicetype;

    private Long shelfId;

    private Long upPerson;

    private String plantName;
    
    private String warehouseName;
    
    private String shelfName;
    
    private String devicetypeName;
    
    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
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

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename == null ? null : devicename.trim();
    }

    public String getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(String deviceno) {
        this.deviceno = deviceno == null ? null : deviceno.trim();
    }

    public String getDeviceproperty() {
        return deviceproperty;
    }

    public void setDeviceproperty(String deviceproperty) {
        this.deviceproperty = deviceproperty == null ? null : deviceproperty.trim();
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

    public Long getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(Long devicetype) {
        this.devicetype = devicetype;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public Long getUpPerson() {
        return upPerson;
    }

    public void setUpPerson(Long upPerson) {
        this.upPerson = upPerson;
    }

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public String getDevicetypeName() {
		return devicetypeName;
	}

	public void setDevicetypeName(String devicetypeName) {
		this.devicetypeName = devicetypeName;
	}

}