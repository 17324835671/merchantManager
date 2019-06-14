package com.sparksys.inventory.plant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.sparksys.common.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.utils.RedisConstant;
import com.sparksys.inventory.basedata.service.IMstB006Service;
import com.sparksys.inventory.material.service.IMstD002MaterialService;
import com.sparksys.inventory.owner.service.IMstD005OwnerService;
import com.sparksys.inventory.plant.service.IMstD001Service;
import com.sparksys.inventory.warehouse.service.IMstD003Service;
import com.sparksys.system.user.service.ISysUserService;
/**
 * 
 * @project inventory
 * @Author zhouxinlei
 * @Description： //TODO 单位管理
 * @Date：2018年11月20日
 *
 */
@Controller
public class MstD001PlantController extends CommonController{
	
	//企业接口
	@Resource
	private IMstD001Service d001Service;
	//仓库接口
	@Resource
	private IMstD003Service d003Service;
	//类型接口
	@Resource
	private IMstB006Service mstB006Service;
	//用户接口
	@Resource
	private ISysUserService userService;
	//商品接口
	@Resource
	private IMstD002MaterialService mstD002Service;
	//供应商
	@Resource
	private IMstD005OwnerService ownerService;
	
	@RequestMapping(value ="/queryMstD001InfoList")
	public String queryMstD001InfoList() throws Exception {
		int pageNum = 0;
		String currentNum = this.getRequest().getParameter("currentNum");
		String plantName = this.getRequest().getParameter("plantName");
		String kanaName = this.getRequest().getParameter("kanaName");
		String plantType = this.getRequest().getParameter("plantType");
		if (currentNum!=null&&!"".equals(currentNum)) {
			pageNum=Integer.valueOf(currentNum);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("plantName", plantName);
		map.put("kanaName", kanaName);
		map.put("plantType", plantType);
		PageBean<MstD001Plant> pageList = d001Service.findMstD001PlantPageList(pageNum, 10, map);
		this.getRequest().setAttribute("mstD001List", pageList.getItems());
		this.getRequest().setAttribute("pageTag", pageList);
		return "plant/plantList";
	}
	@RequestMapping(value ="/queryMstD001InfoSearch",method = RequestMethod.GET)
	public String queryMstD001InfoSearch() throws Exception {
		List<MstB006Type> plantTypes = mstB006Service.findMstB006TypeListByTypeCode(RedisConstant.MST_D001_PLANT);
		this.getRequest().setAttribute("plantTypes", plantTypes);
		return "plant/plantSearch";
	}
	
	@RequestMapping(value ="/addMstD001PlantForworrd")
	public String addMstD001PlantForworrd() throws Exception {
		List<MstB006Type> plantTypes = mstB006Service.findMstB006TypeListByTypeCode(RedisConstant.MST_D001_PLANT);
		List<MstD005Owner> owners = ownerService.findMstD005OwnerList();
		this.getRequest().setAttribute("owners", owners);
		this.getRequest().setAttribute("plantTypes", plantTypes);
		return "plant/plantAdd";
	}
	/**
	 * 保存单位信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/savePlant",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void savePlant() throws Exception {
		SysUser sessionBean = this.getSessionUserBean();
		String plantName= this.getRequest().getParameter("plantName");
		String kanaName= this.getRequest().getParameter("kanaName");
		String plantTypeId= this.getRequest().getParameter("plantType");
		String buDeptName= this.getRequest().getParameter("buDeptName");
		String buDeptTel= this.getRequest().getParameter("buDeptTel");
		String address= this.getRequest().getParameter("address");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		String ownerUId= this.getRequest().getParameter("ownerUId");
		try {
			MstD001Plant mstD001Plant = new MstD001Plant();
			mstD001Plant.setPlantName(plantName);
			mstD001Plant.setKanaName(kanaName);
			mstD001Plant.setBuDeptName(buDeptName);
			mstD001Plant.setBuDeptTel(buDeptTel);
			mstD001Plant.setAddress(address);
			mstD001Plant.setCdPerson(sessionBean.getUserId());
			mstD001Plant.setUpPerson(sessionBean.getUserId());
			mstD001Plant.setOwnerUid(Long.valueOf(ownerUId));
			mstD001Plant.setDeleteFlag(Long.valueOf(deleteFlag));
			mstD001Plant.setPlantTypeId(Long.valueOf(plantTypeId));
			mstD001Plant.setCdPerson(sessionBean.getUserId());
			mstD001Plant.setUpPerson(sessionBean.getUserId());
			d001Service.saveMstD001Plant(mstD001Plant);
			this.success("保存单位成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("保存单位失败",null);
		}
	}
	/**
	 * 修改单位信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/updatePlant",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updatePlant() throws Exception {
		String plantCode= this.getRequest().getParameter("plantCode");
		SysUser sessionBean = this.getSessionUserBean();
		String plantName= this.getRequest().getParameter("plantName");
		String kanaName= this.getRequest().getParameter("kanaName");
		String plantTypeId= this.getRequest().getParameter("plantType");
		String buDeptName= this.getRequest().getParameter("buDeptName");
		String buDeptTel= this.getRequest().getParameter("buDeptTel");
		String address= this.getRequest().getParameter("address");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		String ownerUId= this.getRequest().getParameter("ownerUId");
		try {
			MstD001Plant mstD001Plant = d001Service.findById(Long.valueOf(plantCode));
			mstD001Plant.setPlantName(plantName);
			mstD001Plant.setKanaName(kanaName);
			mstD001Plant.setBuDeptName(buDeptName);
			mstD001Plant.setBuDeptTel(buDeptTel);
			mstD001Plant.setAddress(address);
			mstD001Plant.setCdPerson(sessionBean.getUserId());
			mstD001Plant.setUpPerson(sessionBean.getUserId());
			mstD001Plant.setOwnerUid(Long.valueOf(ownerUId));
			mstD001Plant.setDeleteFlag(Long.valueOf(deleteFlag));
			mstD001Plant.setPlantTypeId(Long.valueOf(plantTypeId));
			mstD001Plant.setUpPerson(sessionBean.getUserId());
			d001Service.updateMstD001Plant(mstD001Plant);
			this.success("修改单位成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("修改单位失败",null);
		}
	}
	/**
	 * 查看单位信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/viewPlantInfo")
	public String viewPlantInfo() throws Exception {
		String plantCode= this.getRequest().getParameter("plantCode");
		MstD001Plant mstD001Plant = d001Service.findById(Long.valueOf(plantCode));
		List<MstD003Warehouse> warehouseList = d003Service.findListByPlantCode(Long.valueOf(plantCode));
		this.getRequest().setAttribute("mstD001Plant", mstD001Plant);
		this.getRequest().setAttribute("warehouseList", warehouseList);
		return "plant/plantView";
		
	}
	/**
	 * 修改单位信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/updatePlantForword")
	public String updatePlantForword() throws Exception {
		String plantCode= this.getRequest().getParameter("plantCode");
		MstD001Plant mstD001Plant = d001Service.findById(Long.valueOf(plantCode));
		List<MstB006Type> plantTypes = mstB006Service.findMstB006TypeListByTypeCode(RedisConstant.MST_D001_PLANT);
		List<MstD005Owner> owners = ownerService.findMstD005OwnerList();
		this.getRequest().setAttribute("owners", owners);
		this.getRequest().setAttribute("plantTypes", plantTypes);
		this.getRequest().setAttribute("mstD001Plant", mstD001Plant);
		return "plant/plantUpdate";
	}
	/**
	 * 删除单位
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deletePlant",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deletePlant() throws Exception {
		String plantCode= this.getRequest().getParameter("plantCode");
		try {
			MstD001Plant mstD001Plant = new MstD001Plant();
			mstD001Plant.setPlantCode(Long.valueOf(plantCode));
			d001Service.deleteById(mstD001Plant);
			this.success("删除单位成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除单位失败",null);
		}
	}
	/**
	 * 单位商品统计
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/queryMstD002ByPlantInfoSearch",method = RequestMethod.GET)
	public String queryMstD002ByPlantInfoSearch() throws Exception {
		List<MstD001Plant> plantList = d001Service.findMstD001PlantList();
		this.getRequest().setAttribute("plantList", plantList);
		return "plant/plantMstD002Search";
	}
	@RequestMapping(value ="/queryMstD002ByPlantInfoList")
	public String queryMstD002ByPlantInfoList() throws Exception {
		int pageNum = 0;
		String currentNum = this.getRequest().getParameter("currentNum");
		String plantCode = this.getRequest().getParameter("plantCode");
		if (currentNum!=null&&!"".equals(currentNum)) {
			pageNum=Integer.valueOf(currentNum);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("plantCode", plantCode);
		PageBean<MstD002Material> list = mstD002Service.findMstD002MaterialList(pageNum, 10, map);
		this.getRequest().setAttribute("mstD002List", list.getItems());
		this.getRequest().setAttribute("pageTag", list);
		return "plant/plantMstD002List";
	}




}
