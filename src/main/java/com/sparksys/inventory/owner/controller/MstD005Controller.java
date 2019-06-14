package com.sparksys.inventory.owner.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.entity.MstD005Owner;
import com.sparksys.common.entity.MstD006Department;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SysUser;
import com.sparksys.common.utils.UtilTools;
import com.sparksys.inventory.owner.service.IMstD005OwnerService;
import com.sparksys.inventory.owner.service.IMstD006DepartmentService;

@Controller
public class MstD005Controller extends CommonController{
	@Resource
	private IMstD005OwnerService mstD005OwnerService;
	@Resource
	private IMstD006DepartmentService mstD006DepartmentService;
	
	@RequestMapping(value ="/queryMstD005InfoList")
	public String queryMstD005InfoList() throws Exception {
		int pageNum = 0;
		String currentNum = this.getRequest().getParameter("currentNum");
		String ownerName = this.getRequest().getParameter("ownerName");
		String kanaName = this.getRequest().getParameter("kanaName");
		if (currentNum!=null&&!"".equals(currentNum)) {
			pageNum=Integer.valueOf(currentNum);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("ownerName", ownerName);
		map.put("kanaName", kanaName);
		PageBean<MstD005Owner> pageList = mstD005OwnerService.findMstD005OwnerList(pageNum, 10, map);
		this.getRequest().setAttribute("mstD005List", pageList.getItems());
		this.getRequest().setAttribute("pageTag", pageList);
		return "owner/ownerList";
	}
	@RequestMapping(value ="/queryMstD005InfoSearch",method = RequestMethod.GET)
	public String queryMstD005InfoSearch() throws Exception {
		return "owner/ownerSearch";
	}
	@RequestMapping(value ="/addMstD005OwnerForworrd")
	public String addMstD005OwnerForworrd() throws Exception {
		return "owner/ownerAdd";
	}
	/**
	 * 保存供应商信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveMstD005Owner",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void saveMstD005Owner() throws Exception {
		SysUser sessionBean = this.getSessionUserBean();
		String ownerName= this.getRequest().getParameter("ownerName");
		String kanaName= this.getRequest().getParameter("kanaName");
		String address= this.getRequest().getParameter("address");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		try {
			MstD005Owner mstD005Owner = new MstD005Owner();
			mstD005Owner.setOwnerName(ownerName);
			mstD005Owner.setKanaName(kanaName);
			mstD005Owner.setAddress(address);
			mstD005Owner.setOwnerCode(UtilTools.GeneratedValue("OW"));
			mstD005Owner.setCdPerson(sessionBean.getUserId());
			mstD005Owner.setUpPerson(sessionBean.getUserId());
			mstD005Owner.setDeleteFlag(Long.valueOf(deleteFlag));
			mstD005OwnerService.saveMstD005Owner(mstD005Owner);
			this.success("新增供应商成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("新增供应商失败",null);
		}
	}
	
	/**
	 * 修改供应商信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateMstD005Owner",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateMstD005Owner() throws Exception {
		SysUser sessionBean = this.getSessionUserBean();
		String ownerUid= this.getRequest().getParameter("ownerUid");
		String ownerName= this.getRequest().getParameter("ownerName");
		String kanaName= this.getRequest().getParameter("kanaName");
		String address= this.getRequest().getParameter("address");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		try {
			MstD005Owner mstD005Owner = mstD005OwnerService.findById(Long.valueOf(ownerUid));
			mstD005Owner.setOwnerName(ownerName);
			mstD005Owner.setKanaName(kanaName);
			mstD005Owner.setAddress(address);
			mstD005Owner.setUpPerson(sessionBean.getUserId());
			mstD005Owner.setDeleteFlag(Long.valueOf(deleteFlag));
			mstD005OwnerService.saveMstD005Owner(mstD005Owner);
			this.success("修改供应商成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("修改供应商失败",null);
		}
	}
	/**
	 * 查看供应商信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/viewMstD005OwnerInfo")
	public String viewMstD005OwnerInfo() throws Exception {
		String ownerUid= this.getRequest().getParameter("ownerUid");
		MstD005Owner mstD005Owner = mstD005OwnerService.findById(Long.valueOf(ownerUid));
		List<MstD006Department> d006Departments = mstD006DepartmentService.findMstD006DepartmentList();
		this.getRequest().setAttribute("mstD005", mstD005Owner);
		this.getRequest().setAttribute("departmentList", d006Departments);
		return "owner/ownerView";
		
	}
	/**
	 * 修改供应商信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateMstD005OwnerForword")
	public String updateMstD005OwnerForword() throws Exception {
		String ownerUid= this.getRequest().getParameter("ownerUid");
		MstD005Owner mstD005Owner = mstD005OwnerService.findById(Long.valueOf(ownerUid));
		this.getRequest().setAttribute("mstD005", mstD005Owner);
		return "owner/ownerUpdate";
		
	}
	/**
	 * 删除供应商
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteMstD005Owner",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteMstD005Owner() throws Exception {
		String ownerUid= this.getRequest().getParameter("ownerUid");
		try {
			mstD005OwnerService.deleteById(Long.valueOf(ownerUid));
			this.success("删除供应商成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除供应商失败",null);
		}
	}
}
