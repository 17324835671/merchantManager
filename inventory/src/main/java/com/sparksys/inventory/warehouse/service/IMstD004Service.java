package com.sparksys.inventory.warehouse.service;

import java.util.List;
import java.util.Map;

import com.sparksys.common.entity.MstD004Shelf;
import com.sparksys.common.entity.PageBean;

public interface IMstD004Service {

	public void deleteMstD004Shelf(MstD004Shelf shelf);
	
	public void saveMstD004Shelf(MstD004Shelf shelf);
	
	public void updateMstD004Shelf(MstD004Shelf shelf);
	
	public List<MstD004Shelf> findMstD004ShelfList();
	
	public List<MstD004Shelf> findListByLocationCode(Long locationCode);
	
	public MstD004Shelf findById(Long shelfId);
	/**
	 * 	查询仓库货架数据
	 * @param currentPage
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public PageBean<MstD004Shelf> findMstD004ShelfList(int currentPage,int pageSize,Map<String, Object> map);
	
}
