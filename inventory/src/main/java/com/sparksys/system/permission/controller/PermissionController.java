package com.sparksys.system.permission.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.entity.SysPermission;
import com.sparksys.common.entity.SysRole;
import com.sparksys.common.service.ShiroService;
import com.sparksys.system.permission.service.ISysPermissionService;
import com.sparksys.system.role.service.ISysRoleService;
@Controller
public class PermissionController extends CommonController{
	
	@Resource
	private ISysPermissionService permissionService;
	
	@Resource
	private ISysRoleService roleService;
	
	@Resource
	private ShiroService shiroService;
	/**
	 * 权限树列表查询
	 * @param response
	 * @param request
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/queryPermissionList")
	public String queryPermissionList(){
		List<SysPermission> permissionList = permissionService.findSysPermissionList();
		this.getRequest().setAttribute("permissionList", permissionList);
		return "sys/permission/permissionList";
		
	}
	/**
	 * 新增权限页面转发
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/addPermissionForworrd")
	public String addPermissionForworrd(){
		List<SysPermission> parentIds = permissionService.findSysPermissionList();
		this.getRequest().setAttribute("parentIds", parentIds);
		return "sys/permission/permissionAdd";
	}
	/**
	 * 查看权限详情
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/viewPermissionInfo")
	public String viewPermissionInfo(){
		String permissionId= this.getRequest().getParameter("permissionId");
		SysPermission permission =null;
		permission = permissionService.findSysPermissionById(Long.valueOf(permissionId));
		String parentPermissionName="";
		if(permission.getParentId()==0L) {
			parentPermissionName = "无";
		}else{
			parentPermissionName+=permissionService.findSysPermissionById(permission.getParentId()).getPermissionName();
		}
		this.getRequest().setAttribute("permission", permission);
		this.getRequest().setAttribute("parentPermissionName", parentPermissionName);
		return "sys/permission/permissionView";
	}
	/**
	 * 修改权限页面详情
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/updatePermissionForworrd")
	public String updatePermissionForworrd(){
		String permissionId= this.getRequest().getParameter("permissionId");
		SysPermission permission =null;
		permission = permissionService.findSysPermissionById(Long.valueOf(permissionId));
		String parentPermissionName="";
		if(permission.getParentId()==0L) {
			parentPermissionName = "无";
		}else{
			parentPermissionName+=permissionService.findSysPermissionById(permission.getParentId()).getPermissionName();
		}
		List<SysPermission> parentIds = permissionService.findSysPermissionList();
		this.getRequest().setAttribute("parentIds", parentIds);
		this.getRequest().setAttribute("parentPermissionName", parentPermissionName);
		this.getRequest().setAttribute("permission", permission);
		return "sys/permission/permissionUpdate";
	}
	/**
	 * 删除权限
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deletePermission",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deletePermission(){
		String permissionId= this.getRequest().getParameter("permissionId");
		try {
			SysPermission permission = new SysPermission();
			permission.setPermissionId(Long.valueOf(permissionId));
			permissionService.deleteSysPermission(permission);
			this.success("删除权限成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除权限失败",null);
		}
	}
	/**
	 * 保存权限 
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/savePermission",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void savePermission(){
		String isparend= this.getRequest().getParameter("isparend");
		String permissionCode= this.getRequest().getParameter("permissionCode");
		String permissionName= this.getRequest().getParameter("permissionName");
		String permissionUrl= this.getRequest().getParameter("permissionUrl");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		Long parentId=0L;
		try {
			//判断是否是父类权限 0：是 ，1：否
			if(isparend.equals("1")) {
				parentId=Long.valueOf(this.getRequest().getParameter("parentId"));
			}
			SysPermission permission = new SysPermission();
			permission.setParentId(parentId);
			permission.setPermissionCode(permissionCode);
			permission.setPermissionName(permissionName);
			permission.setPermissionUrl(permissionUrl);
			permission.setDeleteFlag(Long.valueOf(deleteFlag));
			permissionService.saveSysPermission(permission);
			this.success("新增权限成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("新增权限失败",null);
		}
	}
	/**
	 * 修改权限保存
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/updatePermission",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updatePermission(){
		String permissionId= this.getRequest().getParameter("permissionId");
		String permissionCode= this.getRequest().getParameter("permissionCode");
		String permissionName= this.getRequest().getParameter("permissionName");
		String permissionUrl= this.getRequest().getParameter("permissionUrl");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		try {
			SysPermission permission = null;
			if (permissionId!=null&&!"".equals(permissionId)) {
				permission= permissionService.findSysPermissionById(Long.valueOf(permissionId));
			}
			permission.setPermissionCode(permissionCode);
			permission.setPermissionName(permissionName);
			permission.setPermissionUrl(permissionUrl);
			permission.setDeleteFlag(Long.valueOf(deleteFlag));
			permissionService.updateSysPermission(permission);
			this.success("修改权限成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("修改权限失败",null);
		}
	}
	/**
	 * 查询角色下的权限
	 * @param response
	 * @param request
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/queryRoleAndPermissionList")
	public String queryRoleAndPermissionList(){
		String roleId = this.getRequest().getParameter("roleId");
		List<SysRole> list = roleService.findysRoleByList();
		List<SysPermission> permissionList = new ArrayList<>();
		if (roleId!=null&&!"".equals(roleId)) {
			permissionList=permissionService.findRolePermissionList(Long.valueOf(roleId));
		}else {
			roleId=list.get(0).getRoleId().toString();
			permissionList=permissionService.findRolePermissionList(list.get(0).getRoleId());;
		}
		this.getRequest().setAttribute("roleList", list);
		this.getRequest().setAttribute("permissionList", permissionList);
		this.getRequest().setAttribute("roleId", roleId);
		return "sys/permission/rolePermission";
	}
	/**
	 * 保存角色权限
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/savePermission_role",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void savePermission_role(){
		String roleId = this.getRequest().getParameter("roleId");
		StringBuffer sb = new StringBuffer(this.getRequest().getParameter("permissionIds"));
		String[] permissionIds = sb.toString().split(",");
		try {
			List<String> permissionIdList = Arrays.asList(permissionIds);
			if(roleId!=null&&!"".equals(roleId)) {
				roleService.saveRolePermission(Long.valueOf(roleId),permissionIdList);
			}
			//更新shiro过滤器链
			shiroService.updateFilterChainDefinition();
			this.success("新增角色权限成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("新增角色权限失败",null);
		}
	}
	/**
	 * AJAX查询某个角色下的所有权限
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/queryPermissionListByRoleId")
	@ResponseBody
	public void queryPermissionListByRoleId(){
		List<Map<String, String>> list = new ArrayList<>();
		String roleId = this.getRequest().getParameter("roleId");
		List<SysPermission> returnlist = permissionService.findRolePermissionList(Long.valueOf(roleId));
		for (SysPermission permission : returnlist) {
			Map<String, String> map = new HashMap<String,String>();
			map.put("parentId", permission.getParentId().toString());
			map.put("permissionId", permission.getPermissionId().toString());
			map.put("permissionName", permission.getPermissionName());
			map.put("permissionUrl", permission.getPermissionUrl());
			map.put("roleId", roleId);
			list.add(map);
		}
		this.success(null,list);
	}
	/**
	 * 删除单个角色权限
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deletePermission_role",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deletePermission_role(){
		String permissionId= this.getRequest().getParameter("permissionId");
		String roleId= this.getRequest().getParameter("roleId");
		try {
			List<String> permissionIds = new ArrayList<>();
			permissionIds.add(permissionId);
			if(roleId!=null&&!"".equals(roleId)) {
				roleService.deleteRolePermission(Long.valueOf(roleId),permissionIds);
			}
			//更新shiro过滤器链
			shiroService.updateFilterChainDefinition();
			this.success("删除角色权限成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除角色权限失败",null);
		}
	}
	/**
	 * 删除多个角色权限
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteRolePermissionAll",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteRolePermissionAll(){
		String roleId= this.getRequest().getParameter("roleId");
		String getPermissionIds= this.getRequest().getParameter("permissionIds");
		try {
			List<String> permissionIds = Arrays.asList(getPermissionIds.split(","));
			if(roleId!=null&&!"".equals(roleId)) {
				roleService.deleteRolePermission(Long.valueOf(roleId),permissionIds);
			}
			//更新shiro过滤器链
			shiroService.updateFilterChainDefinition();
			this.success("删除角色权限成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除角色权限失败",null);
		}
	}
	/**
	 * 新增角色权限详情
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/queryPermissionInfoList")
	public String queryPermissionInfoList(){
		String roleId = this.getRequest().getParameter("roleId");
		List<SysPermission> permissionList = permissionService.findSysPermissionList();
		this.getRequest().setAttribute("permissionList", permissionList);
		this.getRequest().setAttribute("roleId", roleId);
		return "sys/permission/permissionModal";
	}
}
