package com.sparksys.inventory.warehouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.entity.MstB006Type;
import com.sparksys.common.entity.MstD003Warehouse;
import com.sparksys.common.entity.MstD004Shelf;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SysUser;
import com.sparksys.common.utils.RedisConstant;
import com.sparksys.inventory.basedata.service.IMstB006Service;
import com.sparksys.inventory.warehouse.service.IMstD003Service;
import com.sparksys.inventory.warehouse.service.IMstD004Service;
import com.sparksys.system.user.service.ISysUserService;
/**
 *	仓库货架数据管理
 * @project inventory
 * @Author zhouxinlei
 * @Description： //TODO
 * @Date：2018年11月23日
 *
 */
@Controller
public class MstD004Controller extends CommonController{

	@Resource
	private IMstD004Service mstD004Service;
	@Resource
	private IMstD003Service mstD003Service;
	@Resource
	private ISysUserService userService;
	//类型接口
	@Resource
	private IMstB006Service mstB006Service;
	
	@RequestMapping(value ="/queryMstD004InfoList")
	public String queryMstD004InfoList(){
		int pageNum = 0;
		String currentNum = this.getRequest().getParameter("currentNum");
		String shelfCode = this.getRequest().getParameter("shelfCode");
		String shelfName = this.getRequest().getParameter("shelfName");
		String deleteFlag = this.getRequest().getParameter("deleteFlag");
		String locationCode = this.getRequest().getParameter("locationCode");
		if (currentNum!=null&&!"".equals(currentNum)) {
			pageNum=Integer.valueOf(currentNum);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("shelfCode", shelfCode);
		map.put("shelfName", shelfName);
		map.put("deleteFlag", deleteFlag);
		map.put("locationCode", locationCode);
		PageBean<MstD004Shelf> pageList = mstD004Service.findMstD004ShelfList(pageNum, 10, map);
		this.getRequest().setAttribute("mstD004List", pageList.getItems());
		this.getRequest().setAttribute("pageTag", pageList);
		return "warehouse/shelfList";
	}
	@RequestMapping(value ="/queryMstD004InfoSearch",method = RequestMethod.GET)
	public String queryMstD004InfoSearch(){
		List<MstD003Warehouse> warehouses = mstD003Service.findMstD003WarehouseList();
		this.getRequest().setAttribute("warehouseList", warehouses);
		return "warehouse/shelfSearch";
	}
	@RequestMapping(value ="/addMstD004ShelfForworrd")
	public String addMstD004ShelfForworrd(){
		List<MstD003Warehouse> warehouses = mstD003Service.findMstD003WarehouseList();
		List<MstB006Type> shelfTypes = mstB006Service.findMstB006TypeListByTypeCode(RedisConstant.MST_D004_SHELF);
		this.getRequest().setAttribute("warehouseList", warehouses);
		this.getRequest().setAttribute("shelfTypeList", shelfTypes);
		return "warehouse/shelfAdd";
	}
	/**
	 * 保存仓库信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveMstD004Shelf",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void saveMstD004Shelf() throws Exception {
		SysUser sessionUserBean= getSessionUserBean();
		String shelfCode= this.getRequest().getParameter("shelfCode");
		String shelfType= this.getRequest().getParameter("shelfType");
		String shelfName= this.getRequest().getParameter("shelfName");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		String locationCode= this.getRequest().getParameter("locationCode");
		try {
			MstD004Shelf mstD004Shelf = new MstD004Shelf();
			mstD004Shelf.setShelfCode(shelfCode);
			mstD004Shelf.setShelfName(shelfName);
			mstD004Shelf.setDeleteFlag(Long.valueOf(deleteFlag));
			mstD004Shelf.setLocationCode(Long.valueOf(locationCode));
			mstD004Shelf.setCdPerson(sessionUserBean.getUserId());
			mstD004Shelf.setUpPerson(sessionUserBean.getUserId());
			mstD004Shelf.setShelfTypeId(Long.valueOf(shelfType));
			mstD004Service.saveMstD004Shelf(mstD004Shelf);
			this.success("新增仓库货架成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("新增仓库货架失败",null);
		}
	}
	/**
	 * 修改仓库货架信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateMstD004Shelf",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateMstD004Shelf(){
		SysUser sessionUserBean= getSessionUserBean();
		String shelfId= this.getRequest().getParameter("shelfId");
		String shelfCode= this.getRequest().getParameter("shelfCode");
		String shelfName= this.getRequest().getParameter("shelfName");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		String shelfType= this.getRequest().getParameter("shelfType");
		try {
			MstD004Shelf mstD004Shelf = mstD004Service.findById(Long.valueOf(shelfId));
			mstD004Shelf.setShelfCode(shelfCode);
			mstD004Shelf.setShelfName(shelfName);
			mstD004Shelf.setDeleteFlag(Long.valueOf(deleteFlag));
			mstD004Shelf.setShelfTypeId(Long.valueOf(shelfType));
			mstD004Shelf.setUpPerson(sessionUserBean.getUserId());
			mstD004Service.updateMstD004Shelf(mstD004Shelf);
			this.success("修改仓库货架成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("修改仓库货架失败",null);
		}
	}
	/**
	 * 查看仓库货架信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/viewMstD004ShelfInfo",method = RequestMethod.GET)
	public String viewMstD004ShelfInfo() throws Exception {
		String shelfId= this.getRequest().getParameter("shelfId");
		MstD004Shelf mstD004Shelf = mstD004Service.findById(Long.valueOf(shelfId));
		this.getRequest().setAttribute("mstD004Shelf", mstD004Shelf);
		return "warehouse/shelfView";
	}
	/**
	 * 修改仓库货架信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateMstD004ShelfForword",method = RequestMethod.GET)
	public String updateMstD004ShelfForword() throws Exception {
		String shelfId= this.getRequest().getParameter("shelfId");
		MstD004Shelf mstD004Shelf = mstD004Service.findById(Long.valueOf(shelfId));
		List<MstD003Warehouse> warehouses = mstD003Service.findMstD003WarehouseList();
		List<MstB006Type> shelfTypes = mstB006Service.findMstB006TypeListByTypeCode(RedisConstant.MST_D004_SHELF);
		this.getRequest().setAttribute("mstD004Shelf", mstD004Shelf);
		this.getRequest().setAttribute("warehouses", warehouses);
		this.getRequest().setAttribute("shelfTypes", shelfTypes);
		return "warehouse/shelfUpdate";
	}
	/**
	 * 删除仓库货架
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteMstD004Shelf",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteMstD004Shelf() throws Exception {
		String shelfId= this.getRequest().getParameter("shelfId");
		try {
			MstD004Shelf shelf = new MstD004Shelf();
			shelf.setShelfId(Long.valueOf(shelfId));
			mstD004Service.deleteMstD004Shelf(shelf);
			this.success("删除仓库货架成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除仓库货架失败",null);
		}
	}
}
