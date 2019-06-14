package com.sparksys.common.entity;

import java.util.Date;

public class MstD007ReqPriceH extends CommonOutDto{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -175210882389145426L;

	private Long reqpUid;

    private Date cdTime;

    private Date effMonthFrom;

    private Date effMonthTo;

    private Long stopFlag;

    private Date upTime;

    private Long cdPerson;

    private Long materialId;

    private Long upPerson;

    private String reqpName;

    private String materialName;
    
    private String color;
    
    private Float capacity;

	public Long getReqpUid() {
		return reqpUid;
	}

	public void setReqpUid(Long reqpUid) {
		this.reqpUid = reqpUid;
	}

	public Date getCdTime() {
		return cdTime;
	}

	public void setCdTime(Date cdTime) {
		this.cdTime = cdTime;
	}

	public Date getEffMonthFrom() {
		return effMonthFrom;
	}

	public void setEffMonthFrom(Date effMonthFrom) {
		this.effMonthFrom = effMonthFrom;
	}

	public Date getEffMonthTo() {
		return effMonthTo;
	}

	public void setEffMonthTo(Date effMonthTo) {
		this.effMonthTo = effMonthTo;
	}

	public Long getStopFlag() {
		return stopFlag;
	}

	public void setStopFlag(Long stopFlag) {
		this.stopFlag = stopFlag;
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

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public Long getUpPerson() {
		return upPerson;
	}

	public void setUpPerson(Long upPerson) {
		this.upPerson = upPerson;
	}

	public String getReqpName() {
		return reqpName;
	}

	public void setReqpName(String reqpName) {
		this.reqpName = reqpName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Float getCapacity() {
		return capacity;
	}

	public void setCapacity(Float capacity) {
		this.capacity = capacity;
	}
    
}