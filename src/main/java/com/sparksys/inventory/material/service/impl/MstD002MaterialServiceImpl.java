package com.sparksys.inventory.material.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.MstD002MaterialDao;
import com.sparksys.common.entity.MstD002Material;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.utils.SnowFlakeId;
import com.sparksys.inventory.material.service.IMstD002MaterialService;
@Service
public class MstD002MaterialServiceImpl implements IMstD002MaterialService {
	
	@Resource
	private MstD002MaterialDao materialDao;
	
	@Override
	public void deleteMstD002Material(Long materialId) {
		materialDao.deleteById(materialId);

	}

	@Override
	public void saveMstD002Material(MstD002Material mstD002Material) {
		if(mstD002Material.getMaterialId()!=null&&!"".equals(mstD002Material.getMaterialId())) {
			updateMstD002Material(mstD002Material);
		}else {
			mstD002Material.setMaterialId(SnowFlakeId.getSnowFlakeId());
			materialDao.insertMstD002Material(mstD002Material);
		}

	}

	@Override
	public void updateMstD002Material(MstD002Material mstD002Material) {
		if(mstD002Material.getMaterialId()!=null&&!"".equals(mstD002Material.getMaterialId())) {
			materialDao.updateMstD002Material(mstD002Material);
		}

	}

	@Override
	public List<MstD002Material> findMstD002MaterialList() {
		List<MstD002Material> list = materialDao.findMstD002MaterialList(null);
		return list;
	}

	@Override
	public MstD002Material findById(Long materialId) {
		MstD002Material material = materialDao.findById(materialId);
		return material;
	}

	@Override
	public PageBean<MstD002Material> findMstD002MaterialList(int currentPage, int pageSize, Map<String, Object> map) {
		PageHelper.startPage(currentPage, pageSize);
		List<MstD002Material> list = materialDao.findMstD002MaterialList(map);
		int totalCount = materialDao.findMstD002MaterialListCount(map);
		PageBean<MstD002Material> pageData = new PageBean<>(currentPage, pageSize, totalCount);
		pageData.setItems(list);
		return pageData;
	}

}
