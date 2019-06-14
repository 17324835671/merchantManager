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
public class MstD006Controller extends CommonController{
	@Resource
	private IMstD006DepartmentService mstD006DepartmentService;
	@Resource
	private IMstD005OwnerService mstD005OwnerService;
	
	@RequestMapping(value ="/queryMstD006InfoList")
	public String queryMstD006InfoList() throws Exception {
		int pageNum = 0;
		String currentNum = this.getRequest().getParameter("currentNum");
		String deptName = this.getRequest().getParameter("deptName");
		String ownerUid = this.getRequest().getParameter("ownerUid");
		if (currentNum!=null&&!"".equals(currentNum)) {
			pageNum=Integer.valueOf(currentNum);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("deptName", deptName);
		map.put("ownerUid", ownerUid);
		PageBean<MstD006Department> pageList = mstD006DepartmentService.findMstD006DepartmentList(pageNum, 10, map);
		this.getRequest().setAttribute("MstD006List", pageList.getItems());
		this.getRequest().setAttribute("pageTag", pageList);
		return "owner/deptList";
	}
	@RequestMapping(value ="/queryMstD006InfoSearch",method = RequestMethod.GET)
	public String queryMstD006InfoSearch() throws Exception {
		List<MstD005Owner> owners = mstD005OwnerService.findMstD005OwnerList();
		this.getRequest().setAttribute("owners", owners);
		return "owner/deptSearch";
	}
	@RequestMapping(value ="/addMstD006DepartmentForworrd")
	public String addMstD006DepartmentForworrd() throws Exception {
		List<MstD005Owner> owners = mstD005OwnerService.findMstD005OwnerList();
		this.getRequest().setAttribute("owners", owners);
		return "owner/deptAdd";
	}
	/**
	 * 保存供应商部门信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveMstD006Department",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void saveMstD006Department() throws Exception {
		SysUser sessionBean = this.getSessionUserBean();
		String deptName= this.getRequest().getParameter("deptName");
		String deptHead= this.getRequest().getParameter("deptHead");
		String deptTelNo= this.getRequest().getParameter("deptTelNo");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		String ownerUid= this.getRequest().getParameter("ownerUid");
		try {
			MstD006Department department  = new MstD006Department();
			department.setOwnerUid(Long.valueOf(ownerUid));
			department.setDeptName(deptName);
			department.setDeptHead(deptHead);
			department.setDeptTelno(deptTelNo);
			department.setDeptCode(UtilTools.GeneratedValue1());
			department.setCdPerson(sessionBean.getUserId());
			department.setUpPerson(sessionBean.getUserId());
			department.setDeleteFlag(Long.valueOf(deleteFlag));
			mstD006DepartmentService.saveMstD006Department(department);
			this.success("新增供应商部门成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("新增供应商部门失败",null);
		}
	}
	
	/**
	 * 修改供应商部门信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateMstD006Department",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateMstD006Department() throws Exception {
		SysUser sessionBean = this.getSessionUserBean();
		String deptUid= this.getRequest().getParameter("deptUid");
		String deptName= this.getRequest().getParameter("deptName");
		String deptHead= this.getRequest().getParameter("deptHead");
		String deptTelNo= this.getRequest().getParameter("deptTelNo");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		String ownerUid= this.getRequest().getParameter("ownerUid");
		try {
			MstD006Department department  = mstD006DepartmentService.findById(Long.valueOf(deptUid));
			department.setOwnerUid(Long.valueOf(ownerUid));
			department.setDeptName(deptName);
			department.setDeptHead(deptHead);
			department.setDeptTelno(deptTelNo);
			department.setDeptCode(UtilTools.GeneratedValue1());
			department.setCdPerson(sessionBean.getUserId());
			department.setUpPerson(sessionBean.getUserId());
			department.setDeleteFlag(Long.valueOf(deleteFlag));
			mstD006DepartmentService.updateMstD006Department(department);
			this.success("修改供应商部门成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("修改供应商部门失败",null);
		}
	}
	/**
	 * 查看供应商部门信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/viewMstD006DepartmentInfo")
	public String viewMstD006DepartmentInfo() throws Exception {
		String deptUid= this.getRequest().getParameter("deptUid");
		MstD006Department mstD006Department  = mstD006DepartmentService.findById(Long.valueOf(deptUid));
		this.getRequest().setAttribute("mstD006", mstD006Department);
		return "owner/deptView";
		
	}
	/**
	 * 修改供应商部门信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateMstD006DepartmentForword")
	public String updateMstD006DepartmentForword() throws Exception {
		String deptUid= this.getRequest().getParameter("deptUid");
		MstD006Department mstD006Department  = mstD006DepartmentService.findById(Long.valueOf(deptUid));
		List<MstD005Owner> owners = mstD005OwnerService.findMstD005OwnerList();
		this.getRequest().setAttribute("owners", owners);
		this.getRequest().setAttribute("mstD006", mstD006Department);
		return "owner/deptUpdate";
		
	}
	/**
	 * 删除供应商
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteMstD006Department",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteMstD006Department() throws Exception {
		String deptUid= this.getRequest().getParameter("deptUid");
		try {
			mstD006DepartmentService.deleteMstD006Department(Long.valueOf(deptUid));
			this.success("删除供应商部门成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除供应商部门失败",null);
		}
	}
}
