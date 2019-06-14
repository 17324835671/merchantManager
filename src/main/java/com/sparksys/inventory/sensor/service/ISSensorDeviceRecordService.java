package com.sparksys.inventory.sensor.service;

import java.util.List;
import java.util.Map;

import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SSensorDeviceRecord;

public interface ISSensorDeviceRecordService {

public void deleteSSensorDeviceRecord(SSensorDeviceRecord sensorDeviceRecord);
	
	public void saveSSensorDeviceRecord(SSensorDeviceRecord sensorDeviceRecord);
	
	public void updateSSensorDeviceRecord(SSensorDeviceRecord sensorDeviceRecord);
	
	public List<SSensorDeviceRecord> findSSensorDeviceRecordList();
	
	public SSensorDeviceRecord findById(Long deviceecordid);
	
	public PageBean<SSensorDeviceRecord> findSSensorDeviceRecordList(int currentPage, int pageSize, Map<String, Object> map);
	
}
