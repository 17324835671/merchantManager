package com.sparksys.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.SSensorDevice;

@Repository
public interface SsensorDeviceDao {
    
	public int deleteById(Long deviceid);

	public int insertSsensorDevice(SSensorDevice ssensorDevice);

	public SSensorDevice findById(Long deviceid);

	public int updateSsensorDevice(SSensorDevice ssensorDevice);
	
	public List<SSensorDevice> findSsensorDeviceList(Map<String, Object> map);
    
	public int findSsensorDeviceListCount(Map<String, Object> map);
	
	public List<SSensorDevice> findListByLocationCode(Long locationCode);
	
}