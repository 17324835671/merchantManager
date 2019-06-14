function queryMstD002InfoList(pageNum) {
	var contextPath = getContextPath();
	var materialName = $("#materialName").val();
	var plantCode = $("#plantCode").val();
	var locationCode ="";
	if($("#locationCode").val()!=null&&$("#locationCode").val()!=""){
		locationCode=$("#locationCode").val();
	}
	var shelfId ="";
	if($("#shelfId").val()!=null&&$("#shelfId").val()!=""){
		shelfId=$("#shelfId").val();
	}
	var url = contextPath+"/queryMstD002InfoList?currentNum="+pageNum+"&materialName="+materialName+"&plantCode="+plantCode
	+"&locationCode="+locationCode+"&shelfId="+shelfId;
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
		    getPage("queryMstD002InfoList");
		    layer.close(index);
		 }
	 });
}
//新增商品
function addMstD002MaterialForworrd() {
	var contextPath = getContextPath();
	var url = contextPath+"/addMstD002MaterialForworrd";
	Modalshow('添加商品',url,850,680,2);
	
}
//查看商品信息
function viewMaterialInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/viewMaterialInfo?materialId=" + obj.id;
	Modalshow('查看商品',url,800,680,1);
}
//删除用户
function deleteMaterial(obj){
	var data = "materialId="+obj.id;
	deleteDataCommon("/deleteMaterial",data);
}
//修改商品信息
function updateMaterialForword(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/updateMaterialForword?materialId="+obj.id;
	Modalshow('修改商品',url,850,680,3);
}
function exportPlantInfo(obj){
	var contextPath = getContextPath();
	var plantName = $("#plantName").val();
	var kanaName = $("#kanaName").val();
	var plantType = $("#plantType").val();
	var url = contextPath+"/queryMstD001InfoList?currentNum="+obj.id+"&kanaName="+kanaName+"&plantName="+plantName+"&plantType="+plantType;
	ExcelExport(url);
}