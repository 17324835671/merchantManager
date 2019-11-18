package com.sparksys.system.login;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sparksys.common.controller.CommonController;
import com.sparksys.common.entity.MstD001PlantCount;
import com.sparksys.common.entity.SysPermission;
import com.sparksys.common.entity.SysUser;
import com.sparksys.common.utils.CommonProperties;
import com.sparksys.inventory.plant.service.IMstD001Service;
import com.sparksys.system.permission.service.ISysPermissionService;
/**
 *	登录controller类
 *  @application name: 
 *  @author: zhouxinlei 
 *  @time：2018年6月27日
 *  @version：ver 1.1
 */
@Controller
public class LoginController extends CommonController{
	
	@Resource
	private ISysPermissionService permissionService;
	@Resource
	private IMstD001Service iMstD001Service;
	@RequestMapping(value = "/index")
	public String index(){
		SysUser sessionUserBean = getSessionUserBean();
		//List<SysPermission> permissionList = permissionService.findMenuList();
		List<SysPermission> permissionList = permissionService.findMenuList2(sessionUserBean);
		this.getRequest().setAttribute("permissionList", permissionList);
		this.getRequest().setAttribute("menuSize", permissionList.size());
		Map<String, String> system = this.SystemInfo();
		String data = "";
		List<MstD001PlantCount> list = iMstD001Service.findMstD001PlantCount();
		JSONArray jsonArray = new JSONArray();
		for (MstD001PlantCount plantCount : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", plantCount.getTypeName());
			jsonObject.put("value", plantCount.getTypeCount());
			jsonArray.add(jsonObject);
			data += plantCount.getTypeName() + ",";
		}
		data = data.substring(0, data.length() - 1);
		this.getRequest().setAttribute("system", system);
		this.getRequest().setAttribute("jsonData", jsonArray.toString());
		this.getRequest().setAttribute("data", data);
		this.getSession().setAttribute("userBean",sessionUserBean);
		return "index";
	}
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	/**
	 * ajax登录请求
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/ajaxLogin",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void submitLogin() {
		String userName =this.getRequest().getParameter("userName");
		String password =this.getRequest().getParameter("password");
		String rememberMe =this.getRequest().getParameter("rememberMe");
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password,rememberMe);
			SecurityUtils.getSubject().login(token);
			success("登录成功",null);
		} catch (Exception e) {
			String msg = "";
				if (UnknownAccountException.class.equals(e.getClass())|| AccountException.class.equals(e.getClass())) {
					msg = CommonProperties.UnknownAccountException;
				} else if (AccountException.class.equals(e.getClass())) {
					msg = CommonProperties.AccountException;
				} else if (IncorrectCredentialsException.class.equals(e.getClass())) {
					msg = CommonProperties.IncorrectCredentialsException;
				} else if ("kaptchaValidateFailed".equals(e.getClass())) {
					msg = CommonProperties.kaptchaValidateFailed;
				} else if (DisabledAccountException.class.equals(e.getClass())) {
					msg = CommonProperties.SHIRO_IS_LOCK;
				}
			error(msg,null);
		}
	}
	//踢出用户
	@RequestMapping(value="kickouting")
	@ResponseBody
	public String kickouting() {
		return "error/kickout";
	}
	//被踢出后跳转的页面
	@RequestMapping(value="kickout")
	public String kickout() {
		return "error/kickout";
	}
	@RequestMapping("/unauthorized")
	public String unauthorized() {

		return "error/unauthorized";
	}
}
