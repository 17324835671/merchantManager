package com.sparksys.inventory.reqprice.service;

import java.util.List;
import java.util.Map;

import com.sparksys.common.entity.MstD007ReqPriceH;
import com.sparksys.common.entity.PageBean;
/**
 *  @application name: 
 *  @author: zhouxinlei 
 *  @time：2018年9月26日
 *  @version：ver 1.1
 */
public interface IMstD007Service {

	public void deleteMstD007ReqPriceH(Long reqpUid);
	
	public void saveMstD007ReqPriceH(MstD007ReqPriceH reqPriceH);
	
	public void updateMstD007ReqPriceH(MstD007ReqPriceH reqPriceH);
	
	public List<MstD007ReqPriceH> findMstD007ReqPriceHList();
	
	public MstD007ReqPriceH findById(Long reqpUid);

	public PageBean<MstD007ReqPriceH> findMstD007ReqPriceHList(int currentPage,int pageSize,Map<String,Object> map);

}
