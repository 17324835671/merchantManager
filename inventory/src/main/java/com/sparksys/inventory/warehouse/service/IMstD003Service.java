package com.sparksys.inventory.warehouse.service;

import java.util.List;
import java.util.Map;

import com.sparksys.common.entity.MstD003Warehouse;
import com.sparksys.common.entity.PageBean;

public interface IMstD003Service {

	public void deleteMstD003Warehouse(MstD003Warehouse warehouse);
	
	public void saveMstD003Warehouse(MstD003Warehouse warehouse);
	
	public void updateMstD003Warehouse(MstD003Warehouse warehouse);
	
	public List<MstD003Warehouse> findMstD003WarehouseList();
	
	public List<MstD003Warehouse> findListByPlantCode(Long plantCode);
	
	public MstD003Warehouse findById(Long locationCode);
	
	public Map<String,String> findMapByShelfId(Long shelfId);
	/**
	 * 	查询仓库数据
	 * @param currentPage
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public PageBean<MstD003Warehouse> findMstD003WarehouseList(int currentPage,int pageSize,Map<String, Object> map);
	
	
}
