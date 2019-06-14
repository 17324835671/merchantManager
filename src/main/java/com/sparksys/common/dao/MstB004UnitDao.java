package com.sparksys.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.MstB004Unit;
@Repository
public interface MstB004UnitDao {
    
    public List<MstB004Unit> findParentMstB004UnitList();
    
    public List<MstB004Unit> findNodeMstB004UnitList();
    
    public List<MstB004Unit> findMstB004UnitList(Map<String, Object> map);
    
    public MstB004Unit findMstB004UnitInfo(Long Id);
}