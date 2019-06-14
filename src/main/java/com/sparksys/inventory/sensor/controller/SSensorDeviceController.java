package com.sparksys.inventory.sensor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.entity.MstB006Type;
import com.sparksys.common.entity.MstD001Plant;
import com.sparksys.common.entity.MstD004Shelf;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SSensorDevice;
import com.sparksys.common.entity.SysUser;
import com.sparksys.common.utils.RedisConstant;
import com.sparksys.inventory.basedata.service.IMstB006Service;
import com.sparksys.inventory.plant.service.IMstD001Service;
import com.sparksys.inventory.sensor.service.ISsensorDeviceService;
import com.sparksys.inventory.warehouse.service.IMstD003Service;
import com.sparksys.inventory.warehouse.service.IMstD004Service;
import com.sparksys.system.user.service.ISysUserService;
/**
 * 传感器设备管理
 * @project inventory
 * @Author zhouxinlei
 * @Description： //TODO
 * @Date：2018年11月28日
 *
 */
@Controller
public class SSensorDeviceController extends CommonController{

	/**
	 * 注入传感器设备接口
	 */
	@Resource
	private ISsensorDeviceService deviceService;
	//类型接口
	@Resource
	private IMstB006Service mstB006Service;	
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
	 * 注入登录用户接口
	 */
	@Resource
	private ISysUserService userService;
	
	/**
	 *  查询传感器设备信息列表
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/querySSensorDeviceList")
	public String querySSensorDeviceList() throws Exception {
		int pageNum = 0;
		String currentNum = this.getRequest().getParameter("currentNum");
		String devicename = this.getRequest().getParameter("devicename");
		String deviceno = this.getRequest().getParameter("deviceno");
		String locationCode = this.getRequest().getParameter("locationCode");
		String plantCode = this.getRequest().getParameter("plantCode");
		String shelfId = this.getRequest().getParameter("shelfId");
		Map<String,Object> map = new HashMap<String, Object>();
		if (currentNum!=null&&!"".equals(currentNum)) {
			pageNum=Integer.valueOf(currentNum);
		}
		map.put("locationCode", locationCode);
		map.put("devicename", devicename);
		map.put("deviceno", deviceno);
		map.put("plantCode", plantCode);
		map.put("shelfId", shelfId);
		PageBean<SSensorDevice> pageList = deviceService.findSSensorDeviceList(pageNum, 10, map);
		this.getRequest().setAttribute("sensorDeviceList", pageList.getItems());
		this.getRequest().setAttribute("pageTag", pageList);
		return "sensor/sensorDeviceList";
	}
	@RequestMapping(value ="/querySSensorDeviceSearch",method = RequestMethod.GET)
	public String querySSensorDeviceSearch() throws Exception {
		List<MstD001Plant> plantList = mstD001Service.findMstD001PlantList();
		this.getRequest().setAttribute("plantList", plantList);
		return "sensor/sensorDeviceSearch";
	}
	/**
	 *   新增传感器设备信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/addSSensorDeviceForworrd")
	public String addSSensorDeviceForworrd() throws Exception {
		List<MstD004Shelf> shelfList = mstD004Service.findMstD004ShelfList();
		//设备类型
		List<MstB006Type> deviceTypes = mstB006Service.findMstB006TypeListByTypeCode(RedisConstant.S_SENSOR_DEVICE);
		this.getRequest().setAttribute("shelfList", shelfList);
		this.getRequest().setAttribute("deviceTypeList", deviceTypes);
		return "sensor/sensorDeviceAdd";
	}
	@RequestMapping(value ="/queryPlantAndWarehouseData")
	@ResponseBody
	public void queryGetPlantTree(){
		String shelfId = this.getRequest().getParameter("shelfId");
		Map<String,String> map = mstD003Service.findMapByShelfId(Long.valueOf(shelfId));
		this.success(null, map);
	}
	/**
	 * 查看传感器设备信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/viewSensorDeviceInfo")
	public String viewSensorDeviceInfo() throws Exception {
		String deviceid= this.getRequest().getParameter("deviceid");
		SSensorDevice sensorDevice = deviceService.findById(Long.valueOf(deviceid));
		this.getRequest().setAttribute("sensorDevice", sensorDevice);
		return "sensor/sensorDeviceView";
	}
	/**
	 * 修改传感器设备信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateSensorDeviceForword")
	public String updateSensorDeviceForword() throws Exception {
		String deviceid= this.getRequest().getParameter("deviceid");
		SSensorDevice sensorDevice = deviceService.findById(Long.valueOf(deviceid));
		List<MstD004Shelf> shelfList = mstD004Service.findMstD004ShelfList();
		//设备类型
		List<MstB006Type> deviceTypes = mstB006Service.findMstB006TypeListByTypeCode(RedisConstant.S_SENSOR_DEVICE);
		this.getRequest().setAttribute("shelfList", shelfList);
		this.getRequest().setAttribute("deviceTypeList", deviceTypes);
		this.getRequest().setAttribute("sensorDevice", sensorDevice);
		return "sensor/sensorDeviceUpdate";
		
	}
	/**
	 * 删除传感器设备
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteSensorDevice",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteSensorDevice() throws Exception {
		String deviceid= this.getRequest().getParameter("deviceid");
		try {
			SSensorDevice sensorDevice = new SSensorDevice();
			sensorDevice.setDeviceid(Long.valueOf(deviceid));
			deviceService.deleteSSensorDevice(sensorDevice);
			this.success("删除传感器设备成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除传感器设备失败",null);
		}
	}
	/**
	 *  保存传感器设备保存
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveSensorDevice",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void saveSensorDevice() throws Exception {
		SysUser sessionUserBean = getSessionUserBean();
		String devicename= this.getRequest().getParameter("devicename");
		String deviceno= this.getRequest().getParameter("deviceno");
		String devicetype= this.getRequest().getParameter("devicetype");
		String shelfId= this.getRequest().getParameter("shelfId");
		String deviceproperty= this.getRequest().getParameter("deviceproperty");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		try {
			SSensorDevice sensorDevice = new SSensorDevice();
			sensorDevice.setDevicename(devicename);
			sensorDevice.setDeviceno(deviceno);
			sensorDevice.setDeviceproperty(deviceproperty);
			sensorDevice.setDeleteFlag(Long.valueOf(deleteFlag));
			sensorDevice.setShelfId(Long.valueOf(shelfId));
			sensorDevice.setDevicetype(Long.valueOf(devicetype));
			sensorDevice.setUpPerson(sessionUserBean.getUserId());
			sensorDevice.setCdPerson(sessionUserBean.getUserId());
			deviceService.saveSSensorDevice(sensorDevice);
			this.success("新增传感器设备成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("新增传感器设备失败",null);
		}
	}
	/**
	 * 修改传感器设备保存
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateSensorDevice",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateSensorDevice() throws Exception {
		SysUser sessionUserBean = getSessionUserBean();
		String deviceid= this.getRequest().getParameter("deviceid");
		String devicename= this.getRequest().getParameter("devicename");
		String deviceno= this.getRequest().getParameter("deviceno");
		String devicetype= this.getRequest().getParameter("devicetype");
		String shelfId= this.getRequest().getParameter("shelfId");
		String deviceproperty= this.getRequest().getParameter("deviceproperty");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		try {
			SSensorDevice sensorDevice = deviceService.findById(Long.valueOf(deviceid));
			sensorDevice.setDevicename(devicename);
			sensorDevice.setDeviceno(deviceno);
			sensorDevice.setDeviceproperty(deviceproperty);
			sensorDevice.setDeleteFlag(Long.valueOf(deleteFlag));
			sensorDevice.setShelfId(Long.valueOf(shelfId));
			sensorDevice.setDevicetype(Long.valueOf(devicetype));
			sensorDevice.setUpPerson(sessionUserBean.getUserId());
			sensorDevice.setCdPerson(sessionUserBean.getUserId());
			deviceService.saveSSensorDevice(sensorDevice);
			this.success("修改传感器设备成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("修改传感器设备失败",null);
		}
	
	}
}
