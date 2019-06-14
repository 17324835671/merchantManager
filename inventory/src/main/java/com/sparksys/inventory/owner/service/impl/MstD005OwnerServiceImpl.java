package com.sparksys.inventory.owner.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.MstD005OwnerDao;
import com.sparksys.common.entity.MstD005Owner;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.utils.SnowFlakeId;
import com.sparksys.inventory.owner.service.IMstD005OwnerService;
@Service
public class MstD005OwnerServiceImpl implements IMstD005OwnerService {

	@Resource
	private MstD005OwnerDao ownerDao;
	
	@Override
	public void deleteById(Long ownerUid) {
		ownerDao.deleteById(ownerUid);
	}

	@Override
	public void saveMstD005Owner(MstD005Owner owner) {
		if(owner.getOwnerUid()!=null&&!"".equals(owner.getOwnerUid())) {
			updateMstD005Owner(owner);
		}else {
			owner.setOwnerUid(SnowFlakeId.getSnowFlakeId());
			ownerDao.insertMstD005Owner(owner);
		}
	}

	@Override
	public MstD005Owner findById(Long ownerUid) {
		return ownerDao.findById(ownerUid);
	}

	@Override
	public void updateMstD005Owner(MstD005Owner owner) {
		if(owner.getOwnerUid()!=null&&!"".equals(owner.getOwnerUid())) {
			ownerDao.updateMstD005Owner(owner);
		}else {
			ownerDao.insertMstD005Owner(owner);
		}
	}

	@Override
	public List<MstD005Owner> findMstD005OwnerList() {
		return ownerDao.findList(null);
	}

	@Override
	public PageBean<MstD005Owner> findMstD005OwnerList(int currentPage, int pageSize, Map<String, Object> map) {
		PageHelper.startPage(currentPage, pageSize);
		List<MstD005Owner> list = ownerDao.findList(map);
		int totalCount = ownerDao.findListCount(map);
		PageBean<MstD005Owner> pageData = new PageBean<>(currentPage, pageSize, totalCount);
		pageData.setItems(list);
		return pageData;
	}

}
