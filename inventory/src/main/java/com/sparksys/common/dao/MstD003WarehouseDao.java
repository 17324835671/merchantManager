package com.sparksys.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.MstD003Warehouse;

@Repository
public interface MstD003WarehouseDao {
	
	public int deleteById(Long locationCode);

    public int insertMstD003Warehouse(MstD003Warehouse warehouse);

    public MstD003Warehouse findById(Long locationCode);

    public int updateMstD003Warehouse(MstD003Warehouse warehouse);
    
    public List<MstD003Warehouse> findMstD003WarehouseList(Map<String, Object> map);
    
    public int findMstD003WarehouseListCount(Map<String, Object> map);
    
    public List<MstD003Warehouse> findListByPlantCode(Long plantCode);
    
    public Map<String,String> findMapByShelfId(Long shelfId);
}