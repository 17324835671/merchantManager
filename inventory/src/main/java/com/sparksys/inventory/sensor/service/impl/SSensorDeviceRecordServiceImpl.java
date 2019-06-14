package com.sparksys.inventory.sensor.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.SSensorDeviceRecordDao;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SSensorDeviceRecord;
import com.sparksys.common.utils.SnowFlakeId;
import com.sparksys.inventory.sensor.service.ISSensorDeviceRecordService;
@Service
public class SSensorDeviceRecordServiceImpl implements ISSensorDeviceRecordService {

	@Resource
	private SSensorDeviceRecordDao sensorDeviceRecordDao;
	@Override
	public void deleteSSensorDeviceRecord(SSensorDeviceRecord sensorDeviceRecord) {
		if(sensorDeviceRecord.getDeviceecordid()!=null&&!"".equals(sensorDeviceRecord.getDeviceecordid())) {
			sensorDeviceRecordDao.deleteById(sensorDeviceRecord.getDeviceecordid());
		}
	}

	@Override
	public void saveSSensorDeviceRecord(SSensorDeviceRecord sensorDeviceRecord) {
		if(sensorDeviceRecord.getDeviceecordid()!=null&&!"".equals(sensorDeviceRecord.getDeviceecordid())) {
			updateSSensorDeviceRecord(sensorDeviceRecord);
		}else {
			sensorDeviceRecord.setDeviceecordid(SnowFlakeId.getSnowFlakeId());
			sensorDeviceRecordDao.insertSSensorDeviceRecord(sensorDeviceRecord);
		}
	}

	@Override
	public void updateSSensorDeviceRecord(SSensorDeviceRecord sensorDeviceRecord) {
		if(sensorDeviceRecord.getDeviceecordid()!=null&&!"".equals(sensorDeviceRecord.getDeviceecordid())) {
			sensorDeviceRecordDao.updateSSensorDeviceRecord(sensorDeviceRecord);
		}
	}

	@Override
	public List<SSensorDeviceRecord> findSSensorDeviceRecordList() {
		List<SSensorDeviceRecord> deviceRecords = sensorDeviceRecordDao.findSSensorDeviceRecordList(null);
		return deviceRecords;
	}

	@Override
	public SSensorDeviceRecord findById(Long deviceecordid) {
		return sensorDeviceRecordDao.findById(deviceecordid);
	}

	@Override
	public PageBean<SSensorDeviceRecord> findSSensorDeviceRecordList(int currentPage, int pageSize, Map<String, Object> map) {
		PageHelper.startPage(currentPage, pageSize);
		List<SSensorDeviceRecord> list = sensorDeviceRecordDao.findSSensorDeviceRecordList(map);
		int totalCount = sensorDeviceRecordDao.findSSensorDeviceRecordListCount(map);
		PageBean<SSensorDeviceRecord> pageData = new PageBean<>(currentPage, pageSize, totalCount);
		pageData.setItems(list);
		return pageData;
	}

}
