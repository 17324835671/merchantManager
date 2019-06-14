package com.sparksys.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.SSensorDeviceRecord;

@Repository
public interface SSensorDeviceRecordDao {
	
	public int deleteById(Long deviceecordid);

	public int insertSSensorDeviceRecord(SSensorDeviceRecord sensorDeviceRecord);

	public SSensorDeviceRecord findById(Long deviceecordid);

	public int updateSSensorDeviceRecord(SSensorDeviceRecord sensorDeviceRecord);
    
	public List<SSensorDeviceRecord> findSSensorDeviceRecordList(Map<String, Object> map);
	
	public int findSSensorDeviceRecordListCount(Map<String, Object> map);
}