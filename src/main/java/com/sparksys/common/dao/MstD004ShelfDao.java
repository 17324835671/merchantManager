package com.sparksys.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.MstD004Shelf;

@Repository
public interface MstD004ShelfDao {
	
	public int deleteById(Long shelfId);

    public int insertMstD004Shelf(MstD004Shelf mstD004Shelf);

    public MstD004Shelf findById(Long shelfId);

    public int updateMstD004Shelf(MstD004Shelf mstD004Shelf);
    
    public List<MstD004Shelf> findMstD004ShelfList(Map<String, Object> map);
    
    public int findMstD004ShelfListCount(Map<String, Object> map);
    
    public List<MstD004Shelf> findListByLocationCode(Long warehouseId);
}