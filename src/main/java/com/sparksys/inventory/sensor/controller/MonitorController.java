package com.sparksys.inventory.sensor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.entity.MstD003Warehouse;
import com.sparksys.common.entity.SSensorDevice;
import com.sparksys.inventory.plant.service.IMstD001Service;
import com.sparksys.inventory.sensor.service.ISsensorDeviceService;
import com.sparksys.inventory.warehouse.service.IMstD003Service;
import com.sparksys.inventory.warehouse.service.IMstD004Service;
/**
 *   物联网监管
 *  @application name:
 *  @author: zhouxinlei 
 *  @time：2018年9月1日
 *  @version：ver 1.1
 */
@Controller
public class MonitorController extends CommonController{
	/**
	 * 注入单位接口
	 */
	@Resource
	private IMstD001Service mstD001Service;
	/**
	 * 注入仓库接口
	 */
	@Resource
	private IMstD003Service mstD003Service;
	/**
	 * 注入货架接口
	 */
	@Resource
	private IMstD004Service mstD004Service;
	/**
	 * 注入传感器设备接口
	 */
	@Resource
	private ISsensorDeviceService deviceService;
	
	@RequestMapping(value ="/queryTemperatureInfoSearch",method = RequestMethod.GET)
	public String queryTemperatureInfoSearch() throws Exception {
		List<MstD003Warehouse> warehouses = mstD003Service.findMstD003WarehouseList();
		this.getRequest().setAttribute("warehouseList", warehouses);
		return "monitor/temperatureMonitorSearch";
	}
	
	@RequestMapping(value ="/queryTemperatureInfoList")
	@ResponseBody
	public void queryTemperatureInfoList() throws Exception {
		List<Map<String, String>> list = new ArrayList<>();
		String locationCode = this.getRequest().getParameter("locationCode");
		List<SSensorDevice> sensorDevices = deviceService.findListByLocationCode(Long.valueOf(locationCode));
		Long deviceType= 1035713681354735617L;
		for (SSensorDevice sSensorDevice : sensorDevices) {
			if(sSensorDevice.getDevicetype().equals(deviceType)) {
				Map<String, String> map = new HashMap<String,String>();
				map.put("deviceNo", sSensorDevice.getDeviceno());
				map.put("deviceName", sSensorDevice.getDevicename());
				map.put("slelfName", sSensorDevice.getShelfName());
				list.add(map);
			}
		}
		this.success(null, list);
	}
	
	@RequestMapping(value ="/queryHumidityInfoSearch",method = RequestMethod.GET)
	public String queryHumidityInfoSearch() throws Exception {
		List<MstD003Warehouse> warehouses = mstD003Service.findMstD003WarehouseList();
		this.getRequest().setAttribute("warehouseList", warehouses);
		return "monitor/humidityInfoSearch";
	}
	
	@RequestMapping(value ="/queryHumidityInfoList")
	@ResponseBody
	public void queryHumidityInfoList() throws Exception {
		List<Map<String, String>> list = new ArrayList<>();
		String locationCode = this.getRequest().getParameter("locationCode");
		List<SSensorDevice> sensorDevices = deviceService.findListByLocationCode(Long.valueOf(locationCode));
		Long deviceType= 1035713681354735617L;
		for (SSensorDevice sSensorDevice : sensorDevices) {
			if(sSensorDevice.getDevicetype().equals(deviceType)) {
				Map<String, String> map = new HashMap<String,String>();
				map.put("deviceNo", sSensorDevice.getDeviceno());
				map.put("deviceName", sSensorDevice.getDevicename());
				map.put("slelfName", sSensorDevice.getShelfName());
				list.add(map);
			}
		}
		this.success(null, list);
	}
}
