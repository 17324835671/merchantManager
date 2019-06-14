package com.sparksys.inventory.sensor.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.SsensorDeviceDao;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SSensorDevice;
import com.sparksys.common.utils.SnowFlakeId;
import com.sparksys.inventory.sensor.service.ISsensorDeviceService;

@Service
public class SsensorDeviceServiceImpl implements ISsensorDeviceService {

	@Resource
	private SsensorDeviceDao ssensorDeviceDao;
	@Override
	public void deleteSSensorDevice(SSensorDevice sensorDevice) {
		if(sensorDevice.getDeviceid()!=null&&!"".equals(sensorDevice.getDeviceid())) {
			ssensorDeviceDao.deleteById(sensorDevice.getDeviceid());
		}
	}

	@Override
	public void saveSSensorDevice(SSensorDevice sensorDevice) {
		if(sensorDevice.getDeviceid()!=null&&!"".equals(sensorDevice.getDeviceid())) {
			updateSSensorDevice(sensorDevice);
		}else {
			sensorDevice.setDeviceid(SnowFlakeId.getSnowFlakeId());
			ssensorDeviceDao.insertSsensorDevice(sensorDevice);
		}
	}

	@Override
	public void updateSSensorDevice(SSensorDevice sensorDevice) {
		if(sensorDevice.getDeviceid()!=null&&!"".equals(sensorDevice.getDeviceid())) {
			ssensorDeviceDao.updateSsensorDevice(sensorDevice);
		}
	}

	@Override
	public List<SSensorDevice> findSSensorDeviceList() {
		List<SSensorDevice> list = ssensorDeviceDao.findSsensorDeviceList(null);
		return list;
	}

	@Override
	public List<SSensorDevice> findListByLocationCode(Long locationCode) {
		List<SSensorDevice> list = ssensorDeviceDao.findListByLocationCode(locationCode);
		return list;
	}

	@Override
	public SSensorDevice findById(Long deviceid) {
		SSensorDevice device = ssensorDeviceDao.findById(deviceid);
		return device;
	}

	@Override
	public PageBean<SSensorDevice> findSSensorDeviceList(int currentPage, int pageSize, Map<String, Object> map) {
		PageHelper.startPage(currentPage, pageSize);
		List<SSensorDevice> list = ssensorDeviceDao.findSsensorDeviceList(map);
		int totalCount = ssensorDeviceDao.findSsensorDeviceListCount(map);
		PageBean<SSensorDevice> pageData = new PageBean<>(currentPage, pageSize, totalCount);
		pageData.setItems(list);
		return pageData;
	}

}
