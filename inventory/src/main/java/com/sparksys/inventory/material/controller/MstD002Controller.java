package com.sparksys.inventory.material.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.entity.MstD001Plant;
import com.sparksys.common.entity.MstD002Material;
import com.sparksys.common.entity.MstD004Shelf;
import com.sparksys.common.entity.MstD005Owner;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SysUser;
import com.sparksys.inventory.material.service.IMstD002MaterialService;
import com.sparksys.inventory.owner.service.IMstD005OwnerService;
import com.sparksys.inventory.plant.service.IMstD001Service;
import com.sparksys.inventory.warehouse.service.IMstD003Service;
import com.sparksys.inventory.warehouse.service.IMstD004Service;
import com.sparksys.system.user.service.ISysUserService;

@Controller
public class MstD002Controller extends CommonController{

	@Resource
	private IMstD002MaterialService mstD002Service;
	/**
	 * 单位接口
	 */
	@Resource
	private IMstD001Service mstD001Service;
	/**
	 * 仓库接口
	 */
	@Resource
	private IMstD003Service mstD003Service;
	/**
	 * 货架接口
	 */
	@Resource
	private IMstD004Service mstD004Service;
	/**
	 * 供应商接口
	 */
	@Resource
	private IMstD005OwnerService mstD005OwnerService;
	/**
	 * 用户接口
	 */
	@Resource
	private ISysUserService userService;
	
	@RequestMapping(value ="/queryMstD002InfoList")
	public String queryMaterialInfoList() throws Exception {
		int pageNum = 0;
		String currentNum = this.getRequest().getParameter("currentNum");
		String plantCode = this.getRequest().getParameter("plantCode");
		String materialName = this.getRequest().getParameter("materialName");
		String locationCode = this.getRequest().getParameter("locationCode");
		String shelfId = this.getRequest().getParameter("shelfId");
		if (currentNum!=null&&!"".equals(currentNum)) {
			pageNum=Integer.valueOf(currentNum);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("materialName", materialName);
		map.put("plantCode", plantCode);
		map.put("locationCode", locationCode);
		map.put("shelfId", shelfId);
		PageBean<MstD002Material> list = mstD002Service.findMstD002MaterialList(pageNum, 10, map);
		this.getRequest().setAttribute("mstD002List", list.getItems());
		this.getRequest().setAttribute("pageTag", list);
		return "material/materialList";
	}
	@RequestMapping(value ="/queryMstD002InfoSearch",method = RequestMethod.GET)
	public String queryMstD002InfoSearch() throws Exception {
		List<MstD001Plant> plantList = mstD001Service.findMstD001PlantList();
		List<MstD005Owner> ownerList = mstD005OwnerService.findMstD005OwnerList();
		this.getRequest().setAttribute("plantList", plantList);
		this.getRequest().setAttribute("ownerList", ownerList);
		return "material/materialSearch";
	}
	@RequestMapping(value ="/addMstD002MaterialForworrd")
	public String addMstD002MaterialForworrd() throws Exception {
		List<MstD004Shelf> shelfs =mstD004Service.findMstD004ShelfList();
		this.getRequest().setAttribute("shelfs", shelfs);
		return "material/materialAdd";
	}
	/**
	 * 保存商品信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveMaterial",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void saveMaterial() throws Exception {
		String shelfId= this.getRequest().getParameter("shelfId");
		String materialName= this.getRequest().getParameter("materialName");
		String unitCode= this.getRequest().getParameter("unitCode");
		String lotMangFlag= this.getRequest().getParameter("lotMangFlag");
		String capacity= this.getRequest().getParameter("capacity");
		String color= this.getRequest().getParameter("color");
		String materialSize= this.getRequest().getParameter("materialSize");
		String colorFlag= this.getRequest().getParameter("colorFlag");
		String sizeFlag= this.getRequest().getParameter("sizeFlag");
		String printNoTax= this.getRequest().getParameter("printNoTax");
		String specWidth= this.getRequest().getParameter("specWidth");
		String widthUnit= this.getRequest().getParameter("widthUnit");
		String specLength= this.getRequest().getParameter("specLength");
		String lengthUnit= this.getRequest().getParameter("lengthUnit");
		String weight= this.getRequest().getParameter("weight");
		String enName= this.getRequest().getParameter("enName");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		try {
			MstD002Material mstD002Material = new MstD002Material();
			mstD002Material.setShelfId(Long.valueOf(shelfId));
			SysUser sessionUserBean = getSessionUserBean();
			mstD002Material.setCdPerson(sessionUserBean.getUserId());
			mstD002Material.setUpPerson(sessionUserBean.getUserId());
			mstD002Material.setMaterialName(materialName);
			mstD002Material.setUnitCode(unitCode);
			mstD002Material.setLotMangFlag(Long.valueOf(lotMangFlag));
			mstD002Material.setCapacity(Float.parseFloat(capacity));
			mstD002Material.setColor(color);
			mstD002Material.setMaterialSize(materialSize);
			mstD002Material.setColorFlag(colorFlag);
			mstD002Material.setSizeFlag(sizeFlag);
			mstD002Material.setPrintNoTax(Float.parseFloat(printNoTax));
			mstD002Material.setSpecWidth(specWidth);
			mstD002Material.setWidthUnit(widthUnit);
			mstD002Material.setSpecLength(specLength);
			mstD002Material.setLengthUnit(lengthUnit);
			mstD002Material.setWeight(weight);
			mstD002Material.setEnName(enName);
			mstD002Material.setDeleteFlag(Long.valueOf(deleteFlag));
			mstD002Material.setShelfId(Long.valueOf(shelfId));
			mstD002Service.saveMstD002Material(mstD002Material);
			this.success("新增商品成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("新增商品失败",null);
		}
	}
	
	/**
	 * 修改商品信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateMaterial",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateMaterial() throws Exception {
		String materialId= this.getRequest().getParameter("materialId");
		String shelfId= this.getRequest().getParameter("shelfId");
		String materialName= this.getRequest().getParameter("materialName");
		String unitCode= this.getRequest().getParameter("unitCode");
		String lotMangFlag= this.getRequest().getParameter("lotMangFlag");
		String capacity= this.getRequest().getParameter("capacity");
		String color= this.getRequest().getParameter("color");
		String materialSize= this.getRequest().getParameter("materialSize");
		String colorFlag= this.getRequest().getParameter("colorFlag");
		String sizeFlag= this.getRequest().getParameter("sizeFlag");
		String printNoTax= this.getRequest().getParameter("printNoTax");
		String specWidth= this.getRequest().getParameter("specWidth");
		String widthUnit= this.getRequest().getParameter("widthUnit");
		String specLength= this.getRequest().getParameter("specLength");
		String lengthUnit= this.getRequest().getParameter("lengthUnit");
		String weight= this.getRequest().getParameter("weight");
		String enName= this.getRequest().getParameter("enName");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		try {
			MstD002Material mstD002Material = mstD002Service.findById(Long.valueOf(materialId));
			mstD002Material.setShelfId(Long.valueOf(shelfId));
			SysUser sessionUserBean = getSessionUserBean();
			mstD002Material.setCdPerson(sessionUserBean.getUserId());
			mstD002Material.setUpPerson(sessionUserBean.getUserId());
			mstD002Material.setMaterialName(materialName);
			mstD002Material.setUnitCode(unitCode);
			mstD002Material.setLotMangFlag(Long.valueOf(lotMangFlag));
			mstD002Material.setCapacity(Float.parseFloat(capacity));
			mstD002Material.setColor(color);
			mstD002Material.setMaterialSize(materialSize);
			mstD002Material.setColorFlag(colorFlag);
			mstD002Material.setSizeFlag(sizeFlag);
			mstD002Material.setPrintNoTax(Float.parseFloat(printNoTax));
			mstD002Material.setSpecWidth(specWidth);
			mstD002Material.setWidthUnit(widthUnit);
			mstD002Material.setSpecLength(specLength);
			mstD002Material.setLengthUnit(lengthUnit);
			mstD002Material.setWeight(weight);
			mstD002Material.setEnName(enName);
			mstD002Material.setDeleteFlag(Long.valueOf(deleteFlag));
			mstD002Material.setShelfId(Long.valueOf(shelfId));
			mstD002Service.updateMstD002Material(mstD002Material);
			this.success("修改商品成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("修改商品失败",null);
		}
	}
	/**
	 * 查看商品信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/viewMaterialInfo")
	public String viewMaterialInfo() throws Exception {
		String materialId= this.getRequest().getParameter("materialId");
		MstD002Material material = mstD002Service.findById(Long.valueOf(materialId));
		this.getRequest().setAttribute("material", material);
		return "material/materialView";
		
	}
	/**
	 * 修改商品信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateMaterialForword")
	public String updateMaterialForword() throws Exception {
		String materialId= this.getRequest().getParameter("materialId");
		MstD002Material material = mstD002Service.findById(Long.valueOf(materialId));
		List<MstD004Shelf> shelfs =mstD004Service.findMstD004ShelfList();
		this.getRequest().setAttribute("shelfs", shelfs);
		this.getRequest().setAttribute("material", material);
		return "material/materialUpdate";
		
	}
	/**
	 * 删除商品
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteMaterial",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteMaterial() throws Exception {
		String materialId= this.getRequest().getParameter("materialId");
		try {
			mstD002Service.deleteMstD002Material(Long.valueOf(materialId));
			this.success("删除商品成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除商品失败",null);
		}
	}
}
