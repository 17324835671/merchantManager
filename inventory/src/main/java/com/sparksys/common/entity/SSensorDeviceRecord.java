package com.sparksys.common.entity;

import java.util.Date;
public class SSensorDeviceRecord extends CommonOutDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7593005241955435014L;

	private Long deviceecordid;

    private String altitude;

    private Date cdTime;

    private Long deleteFlag;

    private String fire;

    private String gas;

    private String humidity;

    private String latitude;

    private String longitude;

    private String pressure;

    private String temperature;

    private Date upTime;

    private Long recorder;

    private Long deviceid;
    
    private String devicename;

    private String deviceno;
    
    public Long getDeviceecordid() {
        return deviceecordid;
    }

    public void setDeviceecordid(Long deviceecordid) {
        this.deviceecordid = deviceecordid;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude == null ? null : altitude.trim();
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

    public String getFire() {
        return fire;
    }

    public void setFire(String fire) {
        this.fire = fire == null ? null : fire.trim();
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas == null ? null : gas.trim();
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity == null ? null : humidity.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure == null ? null : pressure.trim();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Long getRecorder() {
        return recorder;
    }

    public void setRecorder(Long recorder) {
        this.recorder = recorder;
    }

    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getDeviceno() {
		return deviceno;
	}

	public void setDeviceno(String deviceno) {
		this.deviceno = deviceno;
	}

}