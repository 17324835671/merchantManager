package com.sparksys.inventory.basedata.service;

import java.util.List;

import com.sparksys.common.entity.MstB006Type;
/**
 * 
 *  @project: inventory
 *  @author: zhouxinlei
 *  @time：2018年8月30日
 *  @version：ver 1.1
 */
public interface IMstB006Service {
	
	public List<MstB006Type> findMstB006TypeListByTypeCode(String typeCode);
	
	public List<MstB006Type> findMstB006TypeList();
	
	public MstB006Type findMstB006TypeInfo(Long Id);
	

}
