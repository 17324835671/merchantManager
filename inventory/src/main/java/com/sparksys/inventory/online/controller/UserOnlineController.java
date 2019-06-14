package com.sparksys.inventory.online.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.entity.SysUserOnline;
import com.sparksys.inventory.online.service.UserOnlineService;
/**
 * 在线用户管理
 * @project inventory
 * @Author zhouxinlei
 * @Description： //TODO
 * @Date：2018年11月30日
 *
 */
@Controller
public class UserOnlineController extends CommonController{
	
	@Resource
	private UserOnlineService onlineService;
	
	@RequestMapping(value = "queryOnlineUserList",method = RequestMethod.GET)
	public String OnlineUsers(){
		List<SysUserOnline> onlineList= onlineService.findSysUserOnlineList();
		this.getRequest().setAttribute("onlineList", onlineList);
		return "sys/user/onlineList";
	}
	@RequestMapping(value = "kickoutUser")
	@ResponseBody
	public void kickoutUser(){
		Serializable sessionId= this.getRequest().getParameter("sessionId");
		try {
			onlineService.kickout(sessionId);
			this.success("踢出用户成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("踢出用户失败",null);
		}
	}
}
