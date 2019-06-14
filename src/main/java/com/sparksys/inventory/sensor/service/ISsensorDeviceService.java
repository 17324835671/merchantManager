package com.sparksys.inventory.sensor.service;

import java.util.List;
import java.util.Map;

import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SSensorDevice;

public interface ISsensorDeviceService {

public void deleteSSensorDevice(SSensorDevice sensorDevice);
	
	public void saveSSensorDevice(SSensorDevice sensorDevice);
	
	public void updateSSensorDevice(SSensorDevice sensorDevice);
	
	public List<SSensorDevice> findSSensorDeviceList();
	
	public List<SSensorDevice> findListByLocationCode(Long locationCode);
	
	public SSensorDevice findById(Long deviceid);
	
	public PageBean<SSensorDevice> findSSensorDeviceList(int currentPage,int pageSize,Map<String, Object> map);
	
}
