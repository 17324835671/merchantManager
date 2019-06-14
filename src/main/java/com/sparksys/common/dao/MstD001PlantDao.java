package com.sparksys.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.MstD001Plant;
import com.sparksys.common.entity.MstD001PlantCount;
@Repository
public interface MstD001PlantDao {
	
	public int deleteMstD001PlantById(Long plantCode);

    public int insertMstD001Plant(MstD001Plant mstD001Plant);

    public MstD001Plant findMstD001PlantById(Long plantCode);

    public int updateMstD001Plant(MstD001Plant mstD001Plant);
    
    public List<MstD001PlantCount> findMstD001PlantCount();
    
    int findMstD001PlantListCount(Map<String, Object> map);
    
    List<MstD001Plant> findMstD001PlantList(Map<String, Object> map);
}