package com.sparksys.inventory.basedata.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.entity.MstD003Warehouse;
import com.sparksys.common.entity.MstD004Shelf;
import com.sparksys.inventory.plant.service.IMstD001Service;
import com.sparksys.inventory.warehouse.service.IMstD003Service;
import com.sparksys.inventory.warehouse.service.IMstD004Service;

@Controller
public class BaseDataController extends CommonController{
	/**
	 * 注入单位接口
	 */
	@Resource
	private IMstD001Service mstD001Service;
	/**
	 * 注入仓库接口
	 */
	@Resource
	private IMstD003Service mstD003Service;
	/**
	 * 注入货架接口
	 */
	@Resource
	private IMstD004Service mstD004Service;
	
	@RequestMapping(value ="/queryCascadeTree")
	@ResponseBody
	public void queryGetPlantTree() throws Exception {
		List<Map<String, String>> list = new ArrayList<>();
		String plantCode = this.getRequest().getParameter("plantCode");
		String locationCode = this.getRequest().getParameter("locationCode");
		List<MstD003Warehouse> warehouses =new ArrayList<>();
		List<MstD004Shelf> shelfs =new ArrayList<>();
		if(plantCode!=null&&!"".equals(plantCode)) {
			warehouses = mstD003Service.findListByPlantCode(Long.valueOf(plantCode));
			for (MstD003Warehouse mstD003Warehouse : warehouses) {
				Map<String, String> map = new HashMap<String,String>();
				map.put("id", mstD003Warehouse.getLocationCode().toString());
				map.put("name", mstD003Warehouse.getWarehouseName());
				list.add(map);
			}
		}
		if(locationCode!=null&&!"".equals(locationCode)) {
			shelfs = mstD004Service.findListByLocationCode(Long.valueOf(locationCode));
			for (MstD004Shelf shelf : shelfs) {
				Map<String, String> map = new HashMap<String,String>();
				map.put("id", shelf.getShelfId().toString());
				map.put("name", shelf.getShelfName());
				list.add(map);
			}
		}
		this.success(null, list);
	}
}
