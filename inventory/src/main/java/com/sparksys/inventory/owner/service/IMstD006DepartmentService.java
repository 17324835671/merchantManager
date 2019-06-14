package com.sparksys.inventory.owner.service;

import java.util.List;
import java.util.Map;

import com.sparksys.common.entity.MstD006Department;
import com.sparksys.common.entity.PageBean;

public interface IMstD006DepartmentService {

	public void deleteMstD006Department(Long deptUid);
	
	public void saveMstD006Department(MstD006Department department);
	
	public void updateMstD006Department(MstD006Department department);
	
	public List<MstD006Department> findMstD006DepartmentList();
	
	public MstD006Department findById(Long deptUid);
	
	public PageBean<MstD006Department> findMstD006DepartmentList(int currentPage,int pageSize,Map<String,Object> map);
	
	public List<MstD006Department> findDepartmentListByOwnerUid(Long ownerUid);
	
}
