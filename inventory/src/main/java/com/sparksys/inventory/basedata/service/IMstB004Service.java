package com.sparksys.inventory.basedata.service;

import java.util.List;
import java.util.Map;

import com.sparksys.common.entity.MstB004Unit;
/**
 * 
 *  @project: inventory
 *  @author: zhouxinlei
 *  @time：2018年8月30日
 *  @version：ver 1.1
 */
public interface IMstB004Service {
	
	public List<MstB004Unit> findMstB004UnitList(Map<String, Object> map);
	
	public List<MstB004Unit> findMstB004UnitList01(Map<String, Object> map);
	
	public MstB004Unit findMstB004UnitInfo(Long Id);
	

}
