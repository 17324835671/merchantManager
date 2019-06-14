package com.sparksys.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.MstB006Type;
@Repository
public interface MstB006TypeDao {

    public MstB006Type findMstB006TypeInfo(Long typeId);
    
    public List<MstB006Type> findMstB006TypeListByTypeCode(String typeCode);
    
    public List<MstB006Type> findMstB006TypeList();

}