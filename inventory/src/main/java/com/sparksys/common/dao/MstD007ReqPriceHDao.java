package com.sparksys.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.MstD007ReqPriceH;
@Repository
public interface MstD007ReqPriceHDao {
	
	public int deleteById(Long reqpUid);

	public int insertMstD007ReqPriceH(MstD007ReqPriceH reqPriceH);

	public MstD007ReqPriceH findById(Long reqpUid);

	public int updateMstD007ReqPriceH(MstD007ReqPriceH reqPriceH);
	
	public List<MstD007ReqPriceH> findMstD007ReqPriceHList(Map<String, Object> map);
	
	public int findMstD007ReqPriceHListCount(Map<String, Object> map);
	
}