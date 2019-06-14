function queryPlantInfoList(pageNum) {
	var contextPath = getContextPath();
	var plantName = $("#plantName").val();
	var kanaName = $("#kanaName").val();
	var plantType = $("#plantType").val();
	var url = contextPath+"/queryMstD001InfoList?currentNum="+pageNum+"&kanaName="+kanaName+"&plantName="+plantName+"&plantType="+plantType;
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
		    getPage("queryPlantInfoList");
		    layer.close(index);
		 }
	 });
}
function queryMstD002ByPlantInfoList(pageNum) {
	var contextPath = getContextPath();
	var plantCode = $("#plantCode").val();
	var url = contextPath+"/queryMstD002ByPlantInfoList?currentNum="+pageNum+"&plantCode="+plantCode;
	Ext.Ajax.request({
		 url: url,
		 method : 'post',
		 success: function(request){
		    Ext.getDom("returnList").innerHTML=request.responseText;
		    getPage("queryMstD002ByPlantInfoList");
		 }
	 });
}

//新增单位
function savePlantForward() {
	var contextPath = getContextPath();
	var url = contextPath+"/addMstD001PlantForworrd";
	Modalshow('添加单位',url,700,520,2,true);
	
}
//查看单位信息
function viewPlantInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/viewPlantInfo?plantCode=" + obj.id;
	Modalshow('查看单位',url,800,680,1);
}
//删除用户
function deletePlant(obj){
	var data = "plantCode="+obj.id;
	deleteDataCommon("/deletePlant",data);
}
//修改单位信息
function updatePlantForward(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/updatePlantForword?plantCode="+obj.id;
	Modalshow('修改单位',url,700,580,3,true);
}
function exportPlantInfo(obj){
	var contextPath = getContextPath();
	var plantName = $("#plantName").val();
	var kanaName = $("#kanaName").val();
	var plantType = $("#plantType").val();
	var url = contextPath+"/queryMstD001InfoList?currentNum="+obj.id+"&kanaName="+kanaName+"&plantName="+plantName+"&plantType="+plantType;
	ExcelExport(url);
}