package com.sparksys.common.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.sparksys.common.entity.SysUser;
import com.sparksys.common.utils.CommonProperties;
import com.sparksys.common.utils.SnowFlakeId;
/**
 *	 公共controller类
 *  @application name: 
 *  @author: zhouxinlei 
 *  @time：2018年6月27日
 *  @version：ver 1.1
 */
public class CommonController {
	/**
	 * 主键生成策略
	 * @return
	 */
	public Long  getSnowFlakeId() {
		return SnowFlakeId.getSnowFlakeId();
	} 
	
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	protected Session getSession() {
		return getSubject().getSession();
	}

	protected Session getSession(Boolean flag) {
		return getSubject().getSession(flag);
	}

	protected void login(AuthenticationToken token) {
		getSubject().login(token);
	}
	
	public SysUser getSessionUserBean() {
		return (SysUser) getSubject().getPrincipal();
	}

	public HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		return request;
	}
	public HttpServletResponse getResponse() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = requestAttributes.getResponse();
		return response;
	}
	
	public void writejson(String json) {
		getResponse().setContentType("application/json;charset=utf-8");
		if (json != null) {
			try {
				getResponse().getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Map<String, String> SystemInfo() {
		Map<String, String> sysMap = new HashMap<>();
		try {
            InetAddress addr = InetAddress.getLocalHost();
            Properties props = System.getProperties();
            sysMap.put("ip", addr.getHostAddress().toString());
            sysMap.put("name", addr.getHostName().toString());
            sysMap.put("systemName", props.getProperty("os.name"));
            sysMap.put("systemUserName", props.getProperty("user.name"));
            sysMap.put("JavaVersion", "Java "+props.getProperty("java.version"));
            sysMap.put("tomcat", getRequest().getServletContext().getServerInfo());
            sysMap.put("Redis", "3.2.1");
            sysMap.put("MongoDB", "3.4");
        } catch (Exception e) {
            e.printStackTrace();
        }
		return sysMap;
	}
	
	public void success(String msg,Object object) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", CommonProperties.SUCCESS);
		jsonObject.put("message", msg);
		jsonObject.put("data", object);
        writejson(jsonObject.toJSONString());
    }
	
	public void error(String msg,Object object) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", CommonProperties.FAIL);
		jsonObject.put("message", msg);
		jsonObject.put("data", object);
        writejson(jsonObject.toJSONString());
    }	
}
