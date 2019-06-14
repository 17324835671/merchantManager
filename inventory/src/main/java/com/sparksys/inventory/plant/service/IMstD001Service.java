package com.sparksys.inventory.plant.service;

import java.util.List;
import java.util.Map;

import com.sparksys.common.entity.MstD001Plant;
import com.sparksys.common.entity.MstD001PlantCount;
import com.sparksys.common.entity.PageBean;

public interface IMstD001Service {

	public void deleteById(MstD001Plant mstD001Plant);

    public void saveMstD001Plant(MstD001Plant mstD001Plant);

    public MstD001Plant findById(Long plantCode);

    public void updateMstD001Plant(MstD001Plant mstD001Plant);
    
    public List<MstD001PlantCount> findMstD001PlantCount();
    
    PageBean<MstD001Plant> findMstD001PlantPageList(int currentPage,int pageSize,Map<String, Object> map);

	public List<MstD001Plant> findMstD001PlantList();
    
}
