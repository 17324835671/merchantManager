function queryMstD003InfoList(pageNum) {
	var contextPath = getContextPath();
	var warehouseCode = $("#warehouseCode").val();
	var warehouseName = $("#warehouseName").val();
	var warehouseOwner = $("#warehouseOwner").val();
	var plantCode = $("#plantCode").val();
	var shelfMang = $("#shelfMang").val();
	var url = contextPath+"/queryMstD003InfoList?currentNum="+pageNum+"&warehouseCode="+warehouseCode
	+"&warehouseName="+warehouseName+"&warehouseOwner="+warehouseOwner+"&shelfMang="+shelfMang+"&plantCode="+plantCode;
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
		    getPage("queryMstD003InfoList");
		    layer.close(index);
		 }
	 });
}
function queryWarehouseMstD002List(pageNum) {
	var contextPath = getContextPath();
	var locationCode = $("#locationCode").val();
	var url = contextPath+"/queryWarehouseMstD002List?currentNum="+pageNum+"&locationCode="+locationCode;
	Ext.Ajax.request({
		 url: url,
		 method : 'post',
		 success: function(request){
		    Ext.getDom("returnList").innerHTML=request.responseText;
		    getPage("queryWarehouseMstD002List");
		 }
	 });
}
//新增仓库
function addMstD003WarehouseForworrd() {
	var contextPath = getContextPath();
	var url = contextPath+"/addMstD003WarehouseForworrd";
	Modalshow('添加仓库',url,800,580,2);
	
}
//查看仓库信息
function viewMstD003WarehouseInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/viewMstD003WarehouseInfo?locationCode=" + obj.id;
	Modalshow('查看仓库',url,900,700,1);
}
//删除仓库
function deleteMstD003Warehouse(obj){
	var data = "locationCode="+obj.id;
	deleteDataCommon("/deleteMstD003Warehouse",data);
}
//修改仓库信息
function updateMstD003WarehouseForward(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/updateMstD003WarehouseForword?locationCode="+obj.id;
	Modalshow('修改仓库',url,800,580,3);
}
function queryMstD003display() {
	var contextPath = getContextPath();
	var warehouseCode = $("#warehouseCode").val();
	var warehouseName = $("#warehouseName").val();
	var warehouseOwner = $("#warehouseOwner").val();
	var plantCode = $("#plantCode").val();
	var shelfMang = $("#shelfMang").val();
	var url = contextPath+"/queryMstD003InfoList?currentNum="+pageNum+"&warehouseCode="+warehouseCode
	+"&warehouseName="+warehouseName+"&warehouseOwner="+warehouseOwner+"&shelfMang="+shelfMang+"&plantCode="+plantCode;
	Ext.Ajax.request({
		 url: url,
		 method : 'post',
		 success: function(request){
		    Ext.getDom("returnList").innerHTML=request.responseText;
		    getPage("queryMstD003InfoList");
		 }
	 });
}