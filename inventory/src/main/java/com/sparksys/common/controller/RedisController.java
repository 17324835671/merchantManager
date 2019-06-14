package com.sparksys.common.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sparksys.common.entity.OperateLog;
import com.sparksys.common.entity.RedisInfoDetail;
import com.sparksys.common.service.RedisService;
@Controller
public class RedisController extends CommonController{
	
	@Resource
	RedisService redisService;
	
	//跳转到监控页面
	@RequestMapping(value="redisMonitor",method = RequestMethod.GET)
	public String redisMonitor() {
		//获取redis的info
		List<RedisInfoDetail> ridList = redisService.getRedisInfo();
		//获取redis的日志记录
		List<OperateLog> logList = redisService.getLogs(1000);
		//获取日志总数
		long logLen = redisService.getLogLen();
		this.getRequest().setAttribute("infoList", ridList);
		this.getRequest().setAttribute("logList", logList);
		this.getRequest().setAttribute("logLen", logLen);
		return "redis/redisMonitor";
	}
	
	//清空日志按钮
	@RequestMapping(value ="logEmpty",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void logEmpty(){
		try {
			redisService.logEmpty();
			success("日志清空成功！",null);
		} catch (Exception e) {
			error("日志清空失败！",null);
		}
	}
	
	//获取当前数据库中key的数量
	@RequestMapping(value="getKeysSize",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getKeysSize(){
		return JSON.toJSONString(redisService.getKeysSize());
	}
	
	//获取当前数据库内存使用大小情况
	@RequestMapping(value="getMemeryInfo",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getMemeryInfo(){
		return JSON.toJSONString(redisService.getMemeryInfo());
	}
}
