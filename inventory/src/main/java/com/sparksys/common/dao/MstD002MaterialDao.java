package com.sparksys.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.MstD002Material;
@Repository
public interface MstD002MaterialDao {
	
	public int deleteById(Long materialId);

	public int insertMstD002Material(MstD002Material material);

	public MstD002Material findById(Long materialId);

	public int updateMstD002Material(MstD002Material record);
	
	public List<MstD002Material> findMstD002MaterialList(Map<String,Object> map);
	
	public int findMstD002MaterialListCount(Map<String,Object> map);
	
}