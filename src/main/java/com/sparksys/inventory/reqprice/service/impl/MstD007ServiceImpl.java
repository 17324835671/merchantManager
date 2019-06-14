package com.sparksys.inventory.reqprice.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.MstD007ReqPriceHDao;
import com.sparksys.common.entity.MstD007ReqPriceH;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.utils.SnowFlakeId;
import com.sparksys.inventory.reqprice.service.IMstD007Service;
@Service
public class MstD007ServiceImpl implements IMstD007Service {

	@Resource
	private MstD007ReqPriceHDao reqPriceHDao;
	
	@Override
	public void deleteMstD007ReqPriceH(Long reqpUid) {
		reqPriceHDao.deleteById(reqpUid);
	}

	@Override
	public void saveMstD007ReqPriceH(MstD007ReqPriceH reqPriceH) {
		if(reqPriceH.getReqpUid()!=null&&!"".equals(reqPriceH.getReqpUid())) {
			updateMstD007ReqPriceH(reqPriceH);
		}else {
			reqPriceH.setReqpUid(SnowFlakeId.getSnowFlakeId());
			reqPriceHDao.insertMstD007ReqPriceH(reqPriceH);
		}
	}

	@Override
	public void updateMstD007ReqPriceH(MstD007ReqPriceH reqPriceH) {
		if(reqPriceH.getReqpUid()!=null&&!"".equals(reqPriceH.getReqpUid())) {
			reqPriceHDao.updateMstD007ReqPriceH(reqPriceH);
		}

	}

	@Override
	public List<MstD007ReqPriceH> findMstD007ReqPriceHList() {
		List<MstD007ReqPriceH> list = reqPriceHDao.findMstD007ReqPriceHList(null);
		return list;
	}

	@Override
	public MstD007ReqPriceH findById(Long reqpUid) {
		// TODO Auto-generated method stub
		return reqPriceHDao.findById(reqpUid);
	}

	@Override
	public PageBean<MstD007ReqPriceH> findMstD007ReqPriceHList(int currentPage, int pageSize, Map<String, Object> map) {
		PageHelper.startPage(currentPage, pageSize);
		List<MstD007ReqPriceH> list = reqPriceHDao.findMstD007ReqPriceHList(map);
		int totalCount = reqPriceHDao.findMstD007ReqPriceHListCount(map);
		PageBean<MstD007ReqPriceH> pageData = new PageBean<>(currentPage, pageSize, totalCount);
		pageData.setItems(list);
		return pageData;
	}

}
