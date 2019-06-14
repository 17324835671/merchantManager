package com.sparksys.inventory.owner.service;

import java.util.List;
import java.util.Map;

import com.sparksys.common.entity.MstD005Owner;
import com.sparksys.common.entity.PageBean;

public interface IMstD005OwnerService {

	public void deleteById(Long ownerUid);

    public void saveMstD005Owner(MstD005Owner owner);

    public MstD005Owner findById(Long ownerUid);

    public void updateMstD005Owner(MstD005Owner owner);
    
    public List<MstD005Owner> findMstD005OwnerList();
    
    public PageBean<MstD005Owner> findMstD005OwnerList(int currentPage,int pageSize,Map<String,Object> map);
    
}
