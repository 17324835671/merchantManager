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
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SSensorDevice;
import com.sparksys.common.entity.SSensorDeviceRecord;
import com.sparksys.common.entity.SysUser;
import com.sparksys.inventory.sensor.service.ISSensorDeviceRecordService;
import com.sparksys.inventory.sensor.service.ISsensorDeviceService;
import com.sparksys.system.user.service.ISysUserService;
/**
 *  传感器设备数据管理
 *  @project: inventory
 *  @author: zhouxinlei
 *  @time：2018年8月31日
 *  @version：ver 1.1
 */
@Controller
public class SSensorDeviceRecordController extends CommonController{
	/**
	 * 注入传感器设备接口
	 */
	@Resource
	private ISsensorDeviceService deviceService;
	/**
	 * 注入传感器设备数据接口
	 */
	@Resource
	private ISSensorDeviceRecordService deviceRecordService;
	/**
	 * 注入登录用户接口
	 */
	@Resource
	private ISysUserService userService;
	
	/**
	 *  查询传感器设备数据列表
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/querySensorDeviceRecordList")
	public String querySensorDeviceRecordList() throws Exception {
		int pageNum = 0;
		String currentNum = this.getRequest().getParameter("currentNum");
		String devicename = this.getRequest().getParameter("devicename");
		String deviceno = this.getRequest().getParameter("deviceno");
		if (currentNum!=null&&!"".equals(currentNum)) {
			pageNum=Integer.valueOf(currentNum);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("deviceno", deviceno);
		map.put("devicename", devicename);
		PageBean<SSensorDeviceRecord> pageList = deviceRecordService.findSSensorDeviceRecordList(pageNum,10,map);
		this.getRequest().setAttribute("sensorDeviceRecordList", pageList.getItems());
		this.getRequest().setAttribute("pageTag", pageList);
		return "sensor/sensorDeviceRecordList";
	}
	
	@RequestMapping(value ="/querySensorDeviceRecordSearch",method = RequestMethod.GET)
	public String querySensorDeviceRecordSearch() throws Exception {
		return "sensor/sensorDeviceRecordSearch";
	}
	
	/**
	 *   新增传感器设备数据信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/addSSensorDeviceRecordForworrd")
	public String addSSensorDeviceRecordForworrd() throws Exception {
		List<SSensorDevice> sensorDevices = deviceService.findSSensorDeviceList();
		this.getRequest().setAttribute("sensorDevices",sensorDevices);
		return "sensor/sensorDeviceRecordAdd";
	}
	
	/**
	 * 查看传感器设备数据信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/viewSensorDeviceRecordInfo")
	public String viewSensorDeviceRecordInfo() throws Exception {
		String deviceecordid= this.getRequest().getParameter("deviceecordid");
		SSensorDeviceRecord sensorDeviceRecord  = deviceRecordService.findById(Long.valueOf(deviceecordid));
		this.getRequest().setAttribute("sensorDeviceRecord", sensorDeviceRecord);
		return "sensor/sensorDeviceRecordView";
		
	}
	/**
	 * 删除传感器设备数据
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteSensorDeviceRecord",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteSensorDeviceRecord() throws Exception {
		String deviceecordid= this.getRequest().getParameter("deviceecordid");
		try {
			SSensorDeviceRecord sensorDeviceRecord = new SSensorDeviceRecord();
			sensorDeviceRecord.setDeviceecordid(Long.valueOf(deviceecordid));
			deviceRecordService.deleteSSensorDeviceRecord(sensorDeviceRecord);
			this.success("删除传感器设备数据成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除传感器设备数据失败",null);
		}
	}
	/**
	 *  保存传感器设备保存
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveSensorDeviceRecord",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void saveSensorDeviceRecord() throws Exception {
		SysUser sessionUserBean = getSessionUserBean();
		String deviceid= this.getRequest().getParameter("deviceid");
		String temperature= this.getRequest().getParameter("temperature");
		String humidity= this.getRequest().getParameter("humidity");
		String pressure= this.getRequest().getParameter("pressure");
		String altitude= this.getRequest().getParameter("altitude");
		String fire= this.getRequest().getParameter("fire");
		String gas= this.getRequest().getParameter("gas");
		String longitude= this.getRequest().getParameter("longitude");
		String latitude= this.getRequest().getParameter("latitude");
		String deleteFlag= this.getRequest().getParameter("deleteFlag");
		try {
			SSensorDeviceRecord deviceRecord = new SSensorDeviceRecord();
			deviceRecord.setDeviceid(Long.valueOf(deviceid));
			//温度
			deviceRecord.setTemperature(temperature);
			//湿度
			deviceRecord.setHumidity(humidity);
			//压力
			deviceRecord.setPressure(pressure);
			//海拔高度
			deviceRecord.setAltitude(altitude);
			//火灾报警值
			deviceRecord.setFire(fire);
			//气体泄露报警值
			deviceRecord.setGas(gas);
			//经度
			deviceRecord.setLongitude(longitude);
			//纬度
			deviceRecord.setLatitude(latitude);
			//是否删除
			deviceRecord.setDeleteFlag(Long.valueOf(deleteFlag));
			//记录人
			deviceRecord.setRecorder(sessionUserBean.getUserId());
			deviceRecordService.saveSSensorDeviceRecord(deviceRecord);
			this.success("保存传感器设备数据成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("保存传感器设备数据失败",null);
		}
	}
}
