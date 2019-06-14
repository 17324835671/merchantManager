package com.sparksys.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.MstD006Department;

@Repository
public interface MstD006DepartmentDao {
	
	public int deleteById(Long deptUid);

    public int insertMstD006Department(MstD006Department department);

    public MstD006Department findById(Long deptUid);

    public int updateMstD006Department(MstD006Department department);
    
    public List<MstD006Department> findList(Map<String, Object> map);

	public int findListCount(Map<String, Object> map);

	public List<MstD006Department> findDepartmentListByOwnerUid(Long ownerUid);
}