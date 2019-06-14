package com.sparksys.inventory.basedata.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.sparksys.common.dao.MstB006TypeDao;
import com.sparksys.common.entity.MstB006Type;
import com.sparksys.inventory.basedata.service.IMstB006Service;

@Service
public class MstB006ServiceImpl implements IMstB006Service {

	@Resource
	private MstB006TypeDao b006TypeDao;
	@Override
	public List<MstB006Type> findMstB006TypeListByTypeCode(String typeCode) {
		List<MstB006Type> list=b006TypeDao.findMstB006TypeListByTypeCode(typeCode);
		return list;
	}
	@Override
	public List<MstB006Type> findMstB006TypeList() {
		List<MstB006Type> list=b006TypeDao.findMstB006TypeList();
		return list;
	}
	@Override
	public MstB006Type findMstB006TypeInfo(Long Id) {
		return b006TypeDao.findMstB006TypeInfo(Id);
	}

}
