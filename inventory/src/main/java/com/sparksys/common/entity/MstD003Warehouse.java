package com.sparksys.common.entity;

import java.util.Date;

public class MstD003Warehouse extends CommonOutDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2835135689731748286L;

	private Long locationCode;

    private Date cdTime;

    private Long enableFlag;

    private String postcode;

    private Long shelfLeftNum;

    private Long shelfMang;

    private Long shelfNum;

    private Date upTime;

    private String warehouseAddress;

    private String warehouseArea;

    private String warehouseCode;

    private String warehouseName;

    private String warehouseOwner;

    private String warehouseTelNo;

    private Long cdPerson;

    private Long plantCode;

    private Long upPerson;

    private String plantName;
    
    public Long getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(Long locationCode) {
        this.locationCode = locationCode;
    }

    public Date getCdTime() {
        return cdTime;
    }

    public void setCdTime(Date cdTime) {
        this.cdTime = cdTime;
    }

    public Long getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Long enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public Long getShelfLeftNum() {
        return shelfLeftNum;
    }

    public void setShelfLeftNum(Long shelfLeftNum) {
        this.shelfLeftNum = shelfLeftNum;
    }

    public Long getShelfMang() {
        return shelfMang;
    }

    public void setShelfMang(Long shelfMang) {
        this.shelfMang = shelfMang;
    }

    public Long getShelfNum() {
        return shelfNum;
    }

    public void setShelfNum(Long shelfNum) {
        this.shelfNum = shelfNum;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress == null ? null : warehouseAddress.trim();
    }

    public String getWarehouseArea() {
        return warehouseArea;
    }

    public void setWarehouseArea(String warehouseArea) {
        this.warehouseArea = warehouseArea == null ? null : warehouseArea.trim();
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode == null ? null : warehouseCode.trim();
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
    }

    public String getWarehouseOwner() {
        return warehouseOwner;
    }

    public void setWarehouseOwner(String warehouseOwner) {
        this.warehouseOwner = warehouseOwner == null ? null : warehouseOwner.trim();
    }

    public String getWarehouseTelNo() {
        return warehouseTelNo;
    }

    public void setWarehouseTelNo(String warehouseTelNo) {
        this.warehouseTelNo = warehouseTelNo == null ? null : warehouseTelNo.trim();
    }

    public Long getCdPerson() {
        return cdPerson;
    }

    public void setCdPerson(Long cdPerson) {
        this.cdPerson = cdPerson;
    }

    public Long getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(Long plantCode) {
        this.plantCode = plantCode;
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
    
}