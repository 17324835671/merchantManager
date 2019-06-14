package com.sparksys.inventory.owner.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.MstD006DepartmentDao;
import com.sparksys.common.entity.MstD006Department;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.utils.SnowFlakeId;
import com.sparksys.inventory.owner.service.IMstD006DepartmentService;
@Service
public class MstD006DepartmentServiceImpl implements IMstD006DepartmentService {

	@Resource
	private MstD006DepartmentDao departmentDao;
	@Override
	public void deleteMstD006Department(Long deptUid) {
		departmentDao.deleteById(deptUid);
	}

	@Override
	public void saveMstD006Department(MstD006Department department) {
		if(department.getDeptUid()!=null&&!"".equals(department.getDeptUid())) {
			updateMstD006Department(department);
		}else {
			department.setDeptUid(SnowFlakeId.getSnowFlakeId());
			departmentDao.insertMstD006Department(department);
		}

	}

	@Override
	public void updateMstD006Department(MstD006Department department) {
		if(department.getDeptUid()!=null&&!"".equals(department.getDeptUid())) {
			departmentDao.updateMstD006Department(department);
		}
	}

	@Override
	public List<MstD006Department> findMstD006DepartmentList() {
		return departmentDao.findList(null);
	}

	@Override
	public MstD006Department findById(Long deptUid) {
		return departmentDao.findById(deptUid);
	}

	@Override
	public PageBean<MstD006Department> findMstD006DepartmentList(int currentPage, int pageSize,Map<String, Object> map) {
		PageHelper.startPage(currentPage, pageSize);
		List<MstD006Department> list = departmentDao.findList(map);
		int totalCount = departmentDao.findListCount(map);
		PageBean<MstD006Department> pageData = new PageBean<>(currentPage, pageSize, totalCount);
		pageData.setItems(list);
		return pageData;
	}

	@Override
	public List<MstD006Department> findDepartmentListByOwnerUid(Long ownerUid) {
		// TODO Auto-generated method stub
		return departmentDao.findDepartmentListByOwnerUid(ownerUid);
	}

}
