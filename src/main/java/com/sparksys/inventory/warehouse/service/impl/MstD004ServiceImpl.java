package com.sparksys.inventory.warehouse.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.MstD004ShelfDao;
import com.sparksys.common.entity.MstD004Shelf;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.utils.SnowFlakeId;
import com.sparksys.inventory.warehouse.service.IMstD004Service;

@Service
public class MstD004ServiceImpl implements IMstD004Service {

	@Resource
	private MstD004ShelfDao shelfDao;
	
	@Override
	public void deleteMstD004Shelf(MstD004Shelf shelf) {
		if(shelf.getShelfId()!=null&&!"".equals(shelf.getShelfId())) {
			shelfDao.deleteById(shelf.getShelfId());
		}
	}

	@Override
	public void saveMstD004Shelf(MstD004Shelf shelf) {
		if(shelf.getShelfId()!=null&&!"".equals(shelf.getShelfId())) {
			updateMstD004Shelf(shelf);
		}else {
			shelf.setShelfId(SnowFlakeId.getSnowFlakeId());
			shelfDao.insertMstD004Shelf(shelf);
		}
	}

	@Override
	public void updateMstD004Shelf(MstD004Shelf shelf) {
		if(shelf.getShelfId()!=null&&!"".equals(shelf.getShelfId())) {
			shelfDao.updateMstD004Shelf(shelf);
		}
	}

	@Override
	public List<MstD004Shelf> findMstD004ShelfList() {
		List<MstD004Shelf> list = shelfDao.findMstD004ShelfList(null);
		return list;
	}

	@Override
	public List<MstD004Shelf> findListByLocationCode(Long locationCode) {
		List<MstD004Shelf> list = shelfDao.findListByLocationCode(locationCode);
		return list;
	}

	@Override
	public MstD004Shelf findById(Long shelfId) {
		MstD004Shelf mstD004Shelf = shelfDao.findById(shelfId);
		return mstD004Shelf;
	}

	@Override
	public PageBean<MstD004Shelf> findMstD004ShelfList(int currentPage, int pageSize, Map<String, Object> map) {
		PageHelper.startPage(currentPage, pageSize);
		List<MstD004Shelf> list = shelfDao.findMstD004ShelfList(map);
		int totalCount = shelfDao.findMstD004ShelfListCount(map);
		PageBean<MstD004Shelf> pageData = new PageBean<>(currentPage, pageSize, totalCount);
		pageData.setItems(list);
		return pageData;
	}

}
