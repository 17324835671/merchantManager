package com.sparksys.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.MstD005Owner;

@Repository
public interface MstD005OwnerDao {
	
    public int deleteById(Long ownerUid);

    public int insertMstD005Owner(MstD005Owner owner);

    public MstD005Owner findById(Long ownerUid);

    public int updateMstD005Owner(MstD005Owner owner);
    
    public List<MstD005Owner> findList(Map<String, Object> map);

	public int findListCount(Map<String, Object> map);
}