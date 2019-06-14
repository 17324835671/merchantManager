package com.sparksys.inventory.warehouse.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.MstD003WarehouseDao;
import com.sparksys.common.entity.MstD003Warehouse;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.utils.SnowFlakeId;
import com.sparksys.inventory.warehouse.service.IMstD003Service;
@Service
public class MstD003ServiceImpl implements IMstD003Service {

	@Resource
	private MstD003WarehouseDao warehouseDao;
	
	@Override
	public void deleteMstD003Warehouse(MstD003Warehouse warehouse) {
		if(warehouse.getLocationCode()!=null&&!"".equals(warehouse.getLocationCode())) {
			warehouseDao.deleteById(warehouse.getLocationCode());
		}
	}

	@Override
	public void saveMstD003Warehouse(MstD003Warehouse warehouse) {
		if(warehouse.getLocationCode()!=null&&!"".equals(warehouse.getLocationCode())) {
			updateMstD003Warehouse(warehouse);
		}else {
			warehouse.setLocationCode(SnowFlakeId.getSnowFlakeId());
			warehouseDao.insertMstD003Warehouse(warehouse);
		}
		
	}

	@Override
	public void updateMstD003Warehouse(MstD003Warehouse warehouse) {
		if(warehouse.getLocationCode()!=null&&!"".equals(warehouse.getLocationCode())) {
			warehouseDao.updateMstD003Warehouse(warehouse);
		}
	}

	@Override
	public List<MstD003Warehouse> findMstD003WarehouseList() {
		List<MstD003Warehouse> list = warehouseDao.findMstD003WarehouseList(null);
		return list;
	}

	@Override
	public List<MstD003Warehouse> findListByPlantCode(Long plantCode) {
		List<MstD003Warehouse> list = warehouseDao.findListByPlantCode(plantCode);
		return list;
	}

	@Override
	public MstD003Warehouse findById(Long locationCode) {
		MstD003Warehouse warehouse = warehouseDao.findById(locationCode);
		return warehouse;
	}

	@Override
	public PageBean<MstD003Warehouse> findMstD003WarehouseList(int currentPage,int pageSize,Map<String, Object> map) {
		PageHelper.startPage(currentPage, pageSize);
		List<MstD003Warehouse> list = warehouseDao.findMstD003WarehouseList(map);
		int totalCount = warehouseDao.findMstD003WarehouseListCount(map);
		PageBean<MstD003Warehouse> pageData = new PageBean<>(currentPage, pageSize, totalCount);
		pageData.setItems(list);
		return pageData;
	}

	@Override
	public Map<String, String> findMapByShelfId(Long shelfId) {
		// TODO Auto-generated method stub
		return warehouseDao.findMapByShelfId(shelfId);
	}

	
}
