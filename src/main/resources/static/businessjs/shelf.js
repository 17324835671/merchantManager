function queryMstD004InfoList(pageNum) {
	var contextPath = getContextPath();
	var shelfCode = $("#shelfCode").val();
	var shelfName = $("#shelfName").val();
	var locationCode = $("#locationCode").val();
	var deleteFlag = $("#deleteFlag").val();
	var url = contextPath+"/queryMstD004InfoList?currentNum="+pageNum+"&shelfCode="+shelfCode
	+"&shelfName="+shelfName+"&locationCode="+locationCode+"&deleteFlag="+deleteFlag;
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
		    getPage("queryMstD004InfoList");
		    layer.close(index);
		 }
	 });
}
//新增仓库货架
function addMstD004ShelfForworrd() {
	var contextPath = getContextPath();
	var url = contextPath+"/addMstD004ShelfForworrd";
	Modalshow('添加仓库货架',url,600,430,2);
	
}
//查看仓库信息
function viewMstD004ShelfInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/viewMstD004ShelfInfo?shelfId=" + obj.id;
	Modalshow('查看仓库货架',url,600,430,1);
}
//删除仓库
function deleteMstD004Shelf(obj){
	var data = "shelfId="+obj.id;
	deleteDataCommon("/deleteMstD004Shelf",data);
}
//修改仓库信息
function updateMstD004ShelfForword(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/updateMstD004ShelfForword?shelfId="+obj.id;
	Modalshow('修改仓库货架',url,600,430,3);
}