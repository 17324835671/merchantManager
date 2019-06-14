package com.sparksys.inventory.warehouse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sparksys.common.controller.CommonController;
import com.sparksys.common.entity.MstD001Plant;
import com.sparksys.common.entity.MstD002Material;
import com.sparksys.common.entity.MstD003Warehouse;
import com.sparksys.common.entity.MstD004Shelf;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SysUser;
import com.sparksys.common.utils.UtilTools;
import com.sparksys.inventory.material.service.IMstD002MaterialService;
import com.sparksys.inventory.plant.service.IMstD001Service;
import com.sparksys.inventory.warehouse.service.IMstD003Service;
import com.sparksys.inventory.warehouse.service.IMstD004Service;
import com.sparksys.system.user.service.ISysUserService;
@Controller
public class MstD003Controller extends CommonController{
	
	@Resource
	private IMstD003Service d003Service;
	@Resource
	private IMstD001Service d001Service;
	@Resource
	private ISysUserService userService;
	@Resource
	private IMstD004Service d004Service;
	@Resource
	private IMstD002MaterialService mstD002Service;
	@RequestMapping(value ="/queryMstD003InfoList")
	public String queryMstD003InfoList(){
		int pageNum = 0;
		String currentNum = this.getRequest().getParameter("currentNum");
		String warehouseCode = this.getRequest().getParameter("warehouseCode");
		String warehouseName = this.getRequest().getParameter("warehouseName");
		String warehouseOwner = this.getRequest().getParameter("warehouseOwner");
		String shelfMang = this.getRequest().getParameter("shelfMang");
		String plantCode = this.getRequest().getParameter("plantCode");
		if (currentNum!=null&&!"".equals(currentNum)) {
			pageNum=Integer.valueOf(currentNum);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("warehouseCode", warehouseCode);
		map.put("warehouseName", warehouseName);
		map.put("warehouseOwner", warehouseOwner);
		map.put("shelfMang", shelfMang);
		map.put("plantCode", plantCode);
		PageBean<MstD003Warehouse> pageList = d003Service.findMstD003WarehouseList(pageNum, 10, map);
		this.getRequest().setAttribute("mstD003List", pageList.getItems());
		this.getRequest().setAttribute("pageTag", pageList);
		return "warehouse/warehouseList";
	}
	@RequestMapping(value ="/queryMstD003InfoSearch",method = RequestMethod.GET)
	public String queryMstD003InfoSearch() throws Exception {
		List<MstD001Plant> plants = d001Service.findMstD001PlantList();
		this.getRequest().setAttribute("plantList", plants);
		return "warehouse/warehouseSearch";
	}
	@RequestMapping(value ="/addMstD003WarehouseForworrd")
	public String addMstD003WarehouseForworrd() throws Exception {
		List<MstD001Plant> plantList = d001Service.findMstD001PlantList();
		this.getRequest().setAttribute("plantList", plantList);
		return "warehouse/warehouseAdd";
	}
	/**
	 * 保存仓库信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveMstD003Warehouse",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void saveMstD003Warehouse(){
		SysUser sessionUserBean= getSessionUserBean();
		String warehouseName= this.getRequest().getParameter("warehouseName");
		String warehouseOwner= this.getRequest().getParameter("warehouseOwner");
		String warehouseArea= this.getRequest().getParameter("warehouseArea");
		String warehouseAddress= this.getRequest().getParameter("warehouseAddress");
		String postcode= this.getRequest().getParameter("postcode");
		String warehouseTelNo= this.getRequest().getParameter("warehouseTelNo");
		String shelfMang= this.getRequest().getParameter("shelfMang");
		String shelfNum= this.getRequest().getParameter("shelfNum");
		String enableFlag= this.getRequest().getParameter("enableFlag");
		String plantCode= this.getRequest().getParameter("plantCode");
		try {
			MstD003Warehouse mstD003Warehouse = new MstD003Warehouse();
			mstD003Warehouse.setWarehouseCode(UtilTools.GeneratedValue("W"));
			mstD003Warehouse.setWarehouseName(warehouseName);
			mstD003Warehouse.setWarehouseOwner(warehouseOwner);
			mstD003Warehouse.setWarehouseArea(warehouseArea);
			mstD003Warehouse.setWarehouseAddress(warehouseAddress);
			mstD003Warehouse.setPostcode(postcode);
			mstD003Warehouse.setWarehouseTelNo(warehouseTelNo);
			mstD003Warehouse.setShelfMang(Long.valueOf(shelfMang));
			mstD003Warehouse.setShelfNum(Long.valueOf(shelfNum));
			mstD003Warehouse.setEnableFlag(Long.valueOf(enableFlag));
			mstD003Warehouse.setShelfLeftNum(0L);
			mstD003Warehouse.setPlantCode(Long.valueOf(plantCode));
			mstD003Warehouse.setCdPerson(sessionUserBean.getUserId());
			mstD003Warehouse.setUpPerson(sessionUserBean.getUserId());
			d003Service.saveMstD003Warehouse(mstD003Warehouse);
			this.success("新增仓库成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("新增仓库失败",null);
		}
	}
	/**
	 * 修改仓库信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateMstD003Warehouse",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateMstD003Warehouse(){
		SysUser sessionUserBean= getSessionUserBean();
		String locationCode= this.getRequest().getParameter("locationCode");
		String warehouseName= this.getRequest().getParameter("warehouseName");
		String warehouseOwner= this.getRequest().getParameter("warehouseOwner");
		String warehouseArea= this.getRequest().getParameter("warehouseArea");
		String warehouseAddress= this.getRequest().getParameter("warehouseAddress");
		String postcode= this.getRequest().getParameter("postcode");
		String warehouseTelNo= this.getRequest().getParameter("warehouseTelNo");
		String shelfMang= this.getRequest().getParameter("shelfMang");
		String shelfNum= this.getRequest().getParameter("shelfNum");
		String enableFlag= this.getRequest().getParameter("enableFlag");
		String plantCode= this.getRequest().getParameter("plantCode");
		try {
			MstD003Warehouse mstD003Warehouse = d003Service.findById(Long.valueOf(locationCode));
			mstD003Warehouse.setWarehouseName(warehouseName);
			mstD003Warehouse.setWarehouseOwner(warehouseOwner);
			mstD003Warehouse.setWarehouseArea(warehouseArea);
			mstD003Warehouse.setWarehouseAddress(warehouseAddress);
			mstD003Warehouse.setPostcode(postcode);
			mstD003Warehouse.setWarehouseTelNo(warehouseTelNo);
			mstD003Warehouse.setShelfMang(Long.valueOf(shelfMang));
			mstD003Warehouse.setShelfNum(Long.valueOf(shelfNum));
			mstD003Warehouse.setEnableFlag(Long.valueOf(enableFlag));
			mstD003Warehouse.setShelfLeftNum(0L);
			mstD003Warehouse.setPlantCode(Long.valueOf(plantCode));
			mstD003Warehouse.setUpPerson(sessionUserBean.getUserId());
			d003Service.updateMstD003Warehouse(mstD003Warehouse);
			this.success("修改仓库成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("修改仓库失败",null);
		}
	}
	/**
	 * 查看仓库信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/viewMstD003WarehouseInfo")
	public String viewMstD003WarehouseInfo(){
		String locationCode= this.getRequest().getParameter("locationCode");
		MstD003Warehouse mstD003Warehouse = d003Service.findById(Long.valueOf(locationCode));
		List<MstD004Shelf> shelfList = d004Service.findListByLocationCode(Long.valueOf(locationCode));
		this.getRequest().setAttribute("mstD003Warehouse", mstD003Warehouse);
		this.getRequest().setAttribute("shelfList", shelfList);
		return "warehouse/warehouseView";
		
	}
	
	/**
	 * 修改仓库信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateMstD003WarehouseForword")
	public String updateMstD003WarehouseForword(){
		String locationCode= this.getRequest().getParameter("locationCode");
		MstD003Warehouse mstD003Warehouse = d003Service.findById(Long.valueOf(locationCode));
		List<MstD001Plant> plantList = d001Service.findMstD001PlantList();
		this.getRequest().setAttribute("plantList", plantList);
		this.getRequest().setAttribute("mstD003Warehouse", mstD003Warehouse);
		return "warehouse/warehouseUpdate";
	}
	/**
	 * 删除仓库
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteMstD003Warehouse",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteMstD003Warehouse(){
		String locationCode= this.getRequest().getParameter("locationCode");
		try {
			MstD003Warehouse mstD003Warehouse = new MstD003Warehouse();
			mstD003Warehouse.setLocationCode(Long.valueOf(locationCode));
			d003Service.deleteMstD003Warehouse(mstD003Warehouse);
			this.success("删除仓库成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除仓库失败",null);
		}
	}
	/**
	 *	仓库平面分布 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/queryWarehouseDisplaySearch",method = RequestMethod.GET)
	public String queryWarehouseDisplaySearch(){
		List<MstD001Plant> plants = d001Service.findMstD001PlantList();
		this.getRequest().setAttribute("plantList", plants);
		return "warehouse/wrehouseDisplaySearch";
	}
	@RequestMapping(value ="/queryWarehouseDisplay")
	public String queryWarehouseDisplay(){
		String plantCode= this.getRequest().getParameter("plantCode");
		MstD001Plant mstD001Plant = d001Service.findById(Long.valueOf(plantCode));
		List<MstD003Warehouse>  warehouses =  d003Service.findListByPlantCode(Long.valueOf(plantCode));
		List<Map<String, String>> list = new ArrayList<>();
		if (warehouses.size()>0) {
			for (int i = 0; i < warehouses.size(); i++) {
				Map<String, String> map = new HashMap<String,String>();
				map.put("name", warehouses.get(i).getWarehouseName());
				map.put("value", String.valueOf(i));
				list.add(map);
			}
		}
		this.getRequest().setAttribute("jsonData", JSONObject.toJSONString(list));
		this.getRequest().setAttribute("mstD001Plant",mstD001Plant);
		return "warehouse/wrehouseDisplayList";
	}
	/**
	 * 仓库商品统计
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/queryWarehouseMstD002Search",method = RequestMethod.GET)
	public String queryWarehouseMstD002Search() throws Exception {
		List<MstD003Warehouse> warehouseList = d003Service.findMstD003WarehouseList();
		this.getRequest().setAttribute("warehouseList", warehouseList);
		return "warehouse/warehouseMstD002Search";
	}
	@RequestMapping(value ="/queryWarehouseMstD002List")
	public String queryWarehouseMstD002List() throws Exception {
		int pageNum = 0;
		String currentNum = this.getRequest().getParameter("currentNum");
		String locationCode = this.getRequest().getParameter("locationCode");
		if (currentNum!=null&&!"".equals(currentNum)) {
			pageNum=Integer.valueOf(currentNum);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("locationCode", locationCode);
		PageBean<MstD002Material> pageList = mstD002Service.findMstD002MaterialList(pageNum, 10, map);
		this.getRequest().setAttribute("mstD002List", pageList.getItems());
		this.getRequest().setAttribute("pageTag", pageList);
		return "warehouse/warehouseMstD002List";
	}
}
