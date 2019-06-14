function querySSensorDeviceList(pageNum) {
	var contextPath = getContextPath();
	var deviceno = $("#deviceno").val();
	var devicename = $("#devicename").val();
	var plantCode = $("#plantCode").val();
	var locationCode = $("#locationCode").val();
	if(locationCode==undefined){
		locationCode="";
	}
	var shelfId = $("#shelfId").val();
	if(shelfId==undefined){
		shelfId="";
	}
	var url = contextPath+"/querySSensorDeviceList?currentNum="+pageNum+"&deviceno="+deviceno+"&devicename="+devicename
	+"&plantCode="+plantCode+"&locationCode="+locationCode+"&shelfId="+shelfId;
	var index;
	layui.use("layer",function(){
		var layer = layui.layer;
		index = layer.load(3);
	});
	Ext.Ajax.request({
		 url: url,
		 method : 'post',
		 success: function(request){
		    Ext.getDom("returnList").innerHTML=request.responseText;
		    getPage("querySSensorDeviceList");
		    layer.close(index);
		 }
	 });
}
//新增设备传感器
function addSSensorDeviceForworrd() {
	var contextPath = getContextPath();
	var url = contextPath+"/addSSensorDeviceForworrd";
	Modalshow('添加设备传感器',url,700,500,2);
	
}
//查看设备传感器信息
function viewSensorDeviceInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/viewSensorDeviceInfo?deviceid=" + obj.id;
	Modalshow('查看设备传感器',url,800,500,1);
}
//删除设备传感器
function deleteSensorDevice(obj){
	var data = "deviceid="+obj.id;
	deleteDataCommon("/deleteSensorDevice",data);
}
//修改设备传感器信息
function updateSensorDeviceForword(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/updateSensorDeviceForword?deviceid="+obj.id;
	Modalshow('修改设备传感器',url,800,600,3);
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////传感器设备数据///////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function querySensorDeviceRecordList(pageNum) {
	var contextPath = getContextPath();
	var deviceno = $("#deviceno").val();
	var devicename = $("#devicename").val();
	var url = contextPath+"/querySensorDeviceRecordList?currentNum="+pageNum+"&deviceno="+deviceno+"&devicename="+devicename;
	var index;
	layui.use("layer",function(){
		var layer = layui.layer;
		index = layer.load(3);
	});
	Ext.Ajax.request({
		 url: url,
		 method : 'post',
		 success: function(request){
		    Ext.getDom("returnList").innerHTML=request.responseText;
		    getPage("querySensorDeviceRecordList");
		    layer.close(index);
		 }
	 });
}
//新增设备传感器数据信息
function addSSensorDeviceRecordForworrd() {
	var contextPath = getContextPath();
	var url = contextPath+"/addSSensorDeviceRecordForworrd";
	Modalshow('添加设备传感器数据',url,700,500,2);
	
}
//查看设备传感器数据信息
function viewSensorDeviceRecordInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/viewSensorDeviceRecordInfo?deviceecordid=" + obj.id;
	Modalshow('查看设备传感器数据',url,700,500,1);
}
//删除设备传感器数据
function deleteSensorDeviceRecord(obj){
	var data = "deviceecordid="+obj.id;
	deleteDataCommon("/deleteSensorDeviceRecord",data);
}