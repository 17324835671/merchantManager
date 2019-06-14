package com.sparksys.inventory.material.service;

import java.util.List;
import java.util.Map;

import com.sparksys.common.entity.MstD002Material;
import com.sparksys.common.entity.PageBean;

public interface IMstD002MaterialService {
	
	public void deleteMstD002Material(Long materialId);
	
	public void saveMstD002Material(MstD002Material mstD002Material);
	
	public void updateMstD002Material(MstD002Material mstD002Material);
	
	public List<MstD002Material> findMstD002MaterialList();
	
	public MstD002Material findById(Long materialId);
	
	public PageBean<MstD002Material> findMstD002MaterialList(int currentPage,int pageSize,Map<String,Object> map);

}
