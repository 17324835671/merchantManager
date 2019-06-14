package com.sparksys.inventory.basedata.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.sparksys.common.dao.MstB004UnitDao;
import com.sparksys.common.entity.MstB004Unit;
import com.sparksys.inventory.basedata.service.IMstB004Service;
@Service
public class MstB004ServiceImpl implements IMstB004Service {

	@Resource
	private MstB004UnitDao mstB004UnitDao;
	
	@Override
	public List<MstB004Unit> findMstB004UnitList(Map<String, Object> map) {
		List<MstB004Unit> List = mstB004UnitDao.findParentMstB004UnitList();
		List<MstB004Unit> nodeList = mstB004UnitDao.findNodeMstB004UnitList();
		for (MstB004Unit  parent: List) {
			List<MstB004Unit> b004NodeList = new ArrayList<>();
			for (MstB004Unit node : nodeList) {
				if(parent.getUnitId().equals(node.getParentId())) {
					b004NodeList.add(node);
				}
			}
			parent.setMstB004Units(b004NodeList);
		}
		return List;
	}
	@Override
	public List<MstB004Unit> findMstB004UnitList01(Map<String, Object> map) {
		List<MstB004Unit> treeList = mstB004UnitDao.findMstB004UnitList(map);
		return treeList;
	}
	@Override
	public MstB004Unit findMstB004UnitInfo(Long Id) {
		MstB004Unit mstB004Unit = mstB004UnitDao.findMstB004UnitInfo(Id);
		return mstB004Unit;
	}

}
