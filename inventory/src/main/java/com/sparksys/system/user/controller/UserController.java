package com.sparksys.system.user.controller;

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
import com.sparksys.common.entity.SysUser;
import com.sparksys.common.utils.DateUtil;
import com.sparksys.system.user.service.ISysUserService;
@Controller
public class UserController extends CommonController{
	
	@Resource
	private ISysUserService userService;
	
	@RequestMapping(value ="/queryUserInfoList")
	public String queryUserInfoList(){
		int pageNum =  0;
		String currentNum = this.getRequest().getParameter("currentNum");
		String displayName = this.getRequest().getParameter("displayName");
		String isdel = this.getRequest().getParameter("isdel");
		String sex = this.getRequest().getParameter("sex");
		if (currentNum!=null&&!"".equals(currentNum)) {
			pageNum=Integer.valueOf(currentNum);
		}
		Map<String, Object> map = new HashMap<>();
		if(displayName!=null&&!"".equals(displayName)) {
			map.put("displayName", displayName);
		}
		if(isdel!=null&&!"".equals(isdel)) {
			map.put("isdel", isdel);
		}
		if(sex!=null&&!"".equals(sex)) {
			map.put("sex", sex);
		}
		PageBean<SysUser> page = userService.findSysUserPageList(pageNum, 10,map);
		this.getRequest().setAttribute("userInfoList", page.getItems());
		this.getRequest().setAttribute("pageTag", page);
		return "sys/user/userList";
	}
	@RequestMapping(value ="/userInfoSearch",method = RequestMethod.GET)
	public String UserInfoSearch(){
		return "sys/user/userSearch";
	}
	/**
	 * 查看用户信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/viewUserInfo")
	public String viewUserInfo(){
		String userid= this.getRequest().getParameter("userId");
		String currentUserId= this.getRequest().getParameter("currentUserId");
		SysUser user =null;
		if(currentUserId!=null&&!currentUserId.equals("")&&currentUserId.equals("0")) {
			user=this.getSessionUserBean();
		}else {
			user=userService.findSysUerById(Long.valueOf(userid));
		}
		this.getRequest().setAttribute("user", user);
		return "sys/user/userView";
		
	}
	/**
	 * 新增用户转发
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/addUserForworrd")
	public String addUserForworrd(){
		return "sys/user/userAdd";
	}
	/**
	 * 更新用户转发
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/updataUserForworrd")
	public String updataUserForworrd(){
		String userid= this.getRequest().getParameter("userId");
		String currentUserId= this.getRequest().getParameter("currentUserId");
		SysUser user =null;
		if(currentUserId!=null&&!currentUserId.equals("")&&currentUserId.equals("0")) {
			user=this.getSessionUserBean();
		}else {
			user=userService.findSysUerById(Long.valueOf(userid));
		}
		this.getRequest().setAttribute("user", user);
		return "sys/user/userUpdate";
	}
	/**
	 * 保存用户信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveUser")
	@ResponseBody
	public void saveUser(){
		String userName= this.getRequest().getParameter("userName");
		String displayName= this.getRequest().getParameter("displayName");
		String password= this.getRequest().getParameter("password");
		String birthDay= this.getRequest().getParameter("birthDay");
		String telPhone= this.getRequest().getParameter("telPhone");
		String wechat= this.getRequest().getParameter("wechat");
		String email= this.getRequest().getParameter("email");
		String sex= this.getRequest().getParameter("sex");
		String isdel= this.getRequest().getParameter("isdel");
		try {
			SysUser user=new SysUser();
			user.setUserName(userName);
			user.setDisplayName(displayName);
			user.setTelPhone(telPhone);
			user.setWechat(wechat);
			user.setBirthDay(DateUtil.stringToDate(birthDay));
			user.setEmail(email);
			user.setSex(Long.valueOf(sex));
			user.setPassword(password);
			user.setIsdel(Long.valueOf(isdel));
			user.setState(1L);
			userService.saveSysUer(user);
			this.success("新增用户成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("新增用户失败",null);
		}
	}
	@RequestMapping(value ="/updateUser")
	@ResponseBody
	public void updateUser(){
		String userId= this.getRequest().getParameter("userId");
		String userName= this.getRequest().getParameter("userName");
		String displayName= this.getRequest().getParameter("displayName");
		String birthDay= this.getRequest().getParameter("birthDay");
		String telPhone= this.getRequest().getParameter("telPhone");
		String wechat= this.getRequest().getParameter("wechat");
		String email= this.getRequest().getParameter("email");
		String sex= this.getRequest().getParameter("sex");
		String isdel= this.getRequest().getParameter("isdel");
		try {
			SysUser user = null;
			if (userId!=null&&!"".equals(userId)) {
				user=userService.findSysUerById(Long.valueOf(userId));
			}
			user.setUserName(userName);
			user.setDisplayName(displayName);
			user.setBirthDay(DateUtil.stringToDate(birthDay));
			user.setTelPhone(telPhone);
			user.setWechat(wechat);
			user.setEmail(email);
			user.setSex(Long.valueOf(sex));
			user.setIsdel(Long.valueOf(isdel));
			userService.updateSysUer(user);
			this.success("修改用户成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("修改用户失败",null);
		}
	}
	/**
	 * 删除用户
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteUser")
	@ResponseBody
	public void deleteUser(){
		String userId= this.getRequest().getParameter("userId");
		try {
			SysUser user = new SysUser();
			user.setUserId(Long.valueOf(userId));
			userService.deleteSysUer(user);
			this.success("删除用户成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除用户失败",null);
		}
	}
	/**
	 * 查询所有用户
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/queryUserList")
	public String queryUserList(){
		String roleId = this.getRequest().getParameter("roleId");
		List<SysUser> list = userService.findSysUserList(null);
		this.getRequest().setAttribute("roleId", roleId);
		this.getRequest().setAttribute("userList", list);
		return "sys/role/userModal";
	}
}
