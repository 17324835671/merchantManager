package com.sparksys.system.role.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SysPermission;
import com.sparksys.common.entity.SysRole;
import com.sparksys.common.entity.SysUser;
import com.sparksys.common.utils.ChineseUtools;
import com.sparksys.system.permission.service.ISysPermissionService;
import com.sparksys.system.role.service.ISysRoleService;
import com.sparksys.system.user.service.ISysUserService;
@Controller
public class RoleController extends CommonController{
	@Resource
	private ISysRoleService roleService;
	@Resource
	private ISysPermissionService permissionService;
	
	@Resource
	private ISysUserService userService;
	
	@RequestMapping(value ="/queryRoleList")
	public String queryRoleList() throws Exception {
		int pageNum = 0;
		String currentNum = this.getRequest().getParameter("currentNum");
		String roleName = this.getRequest().getParameter("roleName");
		String available = this.getRequest().getParameter("available");
		Map<String, Object> map = new HashMap<>();
		if(roleName!=null&&!"".equals(roleName)) {
			roleName=ChineseUtools.toChinese(roleName);
			map.put("roleName", roleName);
		}
		if(available!=null&&!"".equals(available)) {
			map.put("available", available);
		}
		if (currentNum!=null&&!"".equals(currentNum)) {
			pageNum=Integer.valueOf(currentNum);
		}
		PageBean<SysRole> pageTag = roleService.findSysRolePageList(pageNum, 10,map);
		this.getRequest().setAttribute("roleList", pageTag.getItems());
		this.getRequest().setAttribute("pageTag", pageTag);
		return "sys/role/roleList";
		
	}
	@RequestMapping(value ="/queryRoleInfoSearch",method = RequestMethod.GET)
	public String queryRoleInfoSearch() throws Exception {
		return "sys/role/roleSearch";
		
	}
	/**
	 * 查看角色信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/viewRoleInfo")
	public String viewRoleInfo() throws Exception {
		String roleId= this.getRequest().getParameter("roleId");
		SysRole role =null;
		role = roleService.findSysRoleById(Long.valueOf(roleId));
		List<SysUser> userList = userService.findRoleUserList(Long.valueOf(roleId));
		List<SysPermission> permissionList = permissionService.findRolePermissionList(Long.valueOf(roleId));
		this.getRequest().setAttribute("role", role);
		this.getRequest().setAttribute("userList", userList);
		this.getRequest().setAttribute("permissionList", permissionList);
		return "sys/role/roleView";
	}
	/**
	 * 更新角色页面转发
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateRoleForword")
	public String updateRoleForworrd() throws Exception {
		String roleId= this.getRequest().getParameter("roleId");
		SysRole role =null;
		role = roleService.findSysRoleById(Long.valueOf(roleId));
		this.getRequest().setAttribute("role", role);
		return "sys/role/roleUpdate";
	}
	/**
	 * 增加角色页面转发
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/addRoleForworrd")
	public String addRoleForworrd() throws Exception {
		return "sys/role/roleAdd";
	}
	/**
	 * 删除角色
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteRole",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteRole() throws Exception {
		String roleId= this.getRequest().getParameter("roleId");
		try {
			SysRole role = new SysRole();
			role.setRoleId(Long.valueOf(roleId));
			roleService.deleteSysRoleById(role);
			this.success("删除角色成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除角色失败",null);
		}
	}
	/**
	 * 保存角色信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveRole",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void saveRole() throws Exception {
		String roleName= this.getRequest().getParameter("roleName");
		String roleCode= this.getRequest().getParameter("roleCode");
		String available= this.getRequest().getParameter("available");
		try {
			SysRole role = new SysRole();
			role.setRoleName(roleName);
			role.setRoleCode(roleCode);
			role.setUserId(this.getSessionUserBean().getUserId());
			role.setAvailable(Long.valueOf(available));
			roleService.saveSysRole(role);
			this.success("新增角色成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("新增角色失败",null);
		}
	}
	/**
	 * 更新角色信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateRole",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateRole() throws Exception {
		String roleId= this.getRequest().getParameter("roleId");
		String roleName= this.getRequest().getParameter("roleName");
		String roleCode= this.getRequest().getParameter("roleCode");
		String available= this.getRequest().getParameter("available");
		try {
			SysRole role = null;
			role= roleService.findSysRoleById(Long.valueOf(roleId));
			role.setRoleCode(roleCode);
			role.setRoleName(roleName);
			role.setUserId(this.getSessionUserBean().getUserId());
			role.setAvailable(Long.valueOf(available));
			roleService.updateSysRole(role);
			this.success("修改角色成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("修改角色失败",null);
		}
	}
	/**
	 * 查看角色下的用户
	 * @param response
	 * @param request
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/queryRoleAndUserList")
	public String queryRoleAndUserList() throws Exception {
		String roleId = this.getRequest().getParameter("roleId");
		List<SysRole> list = roleService.findysRoleByList();
		List<SysUser> userList = new ArrayList<>();
		if (roleId!=null&&!"".equals(roleId)) {
			userList=userService.findRoleUserList(Long.valueOf(roleId));
		}else {
			roleId=list.get(0).getRoleId().toString();
			userList=userService.findRoleUserList(list.get(0).getRoleId());
		}
		this.getRequest().setAttribute("roleList", list);
		this.getRequest().setAttribute("userList", userList);
		this.getRequest().setAttribute("roleId", roleId);
		return "sys/role/userRole";
	}
	/**
	 * 保存用户角色
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveUser_role",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void saveUser_role() throws Exception {
		String roleId = this.getRequest().getParameter("roleId");
		String[] userIds= this.getRequest().getParameterValues("userIds");
		try {
			List<String> userIdList = Arrays.asList(userIds);
			if(roleId!=null&&!"".equals(roleId)) {
				roleService.saveUserRole(Long.valueOf(roleId),userIdList);
			}
			this.success("新增用户角色成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("新增用户角色失败",null);
		}
	}
	/**
	 * AJAX查询某个角色下的用户信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/queryUserListByRoleId")
	@ResponseBody
	public void queryUserListByRoleId() {
		String roleId = this.getRequest().getParameter("roleId");
		List<Map<String, String>> list = new ArrayList<>();
		List<SysUser> userList = userService.findRoleUserList(Long.valueOf(roleId));
		for (SysUser user : userList) {
			Map<String, String> map = new HashMap<String,String>();
			map.put("userId", user.getUserId().toString());
			map.put("userName", user.getUserName());
			map.put("displayName", user.getDisplayName());
			map.put("isdel", user.getIsdel().toString());
			list.add(map);
		}
		this.success(null,list);
	}
	/**
	 * 删除角色用户
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteUser_role",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteUser_role() throws Exception {
		String userId= this.getRequest().getParameter("userId");
		String roleId= this.getRequest().getParameter("roleId");
		try {
			List<String> userIds = new ArrayList<>();
			userIds.add(userId);
			if(roleId!=null&&!"".equals(roleId)) {
				roleService.deleteUserRole(Long.valueOf(roleId),userIds);
			}
			this.success("删除用户角色成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除用户角色失败",null);
		}
	}
	/**
	 * 删除多个角色用户
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteUserRoleAll",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteUserRoleAll() throws Exception {
		String roleId= this.getRequest().getParameter("roleId");
		String getUserIds= this.getRequest().getParameter("userIds");
		try {
			List<String> userIds = Arrays.asList(getUserIds.split(","));
			if(roleId!=null&&!"".equals(roleId)) {
				roleService.deleteUserRole(Long.valueOf(roleId),userIds);
			}
			this.success("删除用户角色成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除用户角色失败",null);
		}
	}
	
}
