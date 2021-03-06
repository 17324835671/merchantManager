package com.sparksys.inventory.plant.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.MstD001PlantDao;
import com.sparksys.common.entity.MstD001Plant;
import com.sparksys.common.entity.MstD001PlantCount;
import com.sparksys.common.entity.PageBean;
import com.sparksys.inventory.plant.service.IMstD001Service;
@Service
public class MstD001PlantServiceImpl implements IMstD001Service {
	
	@Resource
	private MstD001PlantDao plantDao;
	
	@Override
	public void deleteById(MstD001Plant mstD001Plant) {
		plantDao.deleteMstD001PlantById(mstD001Plant.getPlantCode());
	}

	@Override
	public void saveMstD001Plant(MstD001Plant mstD001Plant) {
		plantDao.insertMstD001Plant(mstD001Plant);
	}

	@Override
	public MstD001Plant findById(Long plantCode) {
		MstD001Plant mstD001Plant = plantDao.findMstD001PlantById(plantCode);
		return mstD001Plant;
	}

	@Override
	public void updateMstD001Plant(MstD001Plant mstD001Plant) {
		plantDao.updateMstD001Plant(mstD001Plant);
	}

	@Override
	public List<MstD001PlantCount> findMstD001PlantCount() {
		List<MstD001PlantCount> list = plantDao.findMstD001PlantCount();
		return list;
	}

	@Override
	public PageBean<MstD001Plant> findMstD001PlantPageList(int currentPage, int pageSize,Map<String, Object> map) {
		PageHelper.startPage(currentPage, pageSize);
		List<MstD001Plant> list = plantDao.findMstD001PlantList(map);
		int totalCount = plantDao.findMstD001PlantListCount(map);
		PageBean<MstD001Plant> pageData = new PageBean<>(currentPage, pageSize, totalCount);
		pageData.setItems(list);
		return pageData;
	}

	@Override
	public List<MstD001Plant> findMstD001PlantList() {
		List<MstD001Plant> list = plantDao.findMstD001PlantList(null);
		return list;
	}

}
