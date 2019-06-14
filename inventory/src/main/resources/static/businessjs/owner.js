
function queryOwnerInfoList(pageNum) {
	var contextPath = getContextPath();
	var ownerName = $("#ownerName").val();
	var kanaName = $("#kanaName").val();
	var url = contextPath+"/queryMstD005InfoList?currentNum="+pageNum+"&ownerName="+ownerName
	+"&kanaName="+kanaName;
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
		    getPage("queryOwnerInfoList");
		    layer.close(index);
		 }
	 });
}
//新增供应商
function addOwnerForward() {
	var contextPath = getContextPath();
	var url = contextPath+"/addMstD005OwnerForworrd";
	Modalshow('添加供应商',url,700,480,2);
	
}
//查看供应商信息
function viewMstD005OwnerInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/viewMstD005OwnerInfo?ownerUid=" + obj.id;
	Modalshow('查看供应商',url,750,600,1);
}
//删除供应商
function deleteMstD005Owner(obj){
	var data = "ownerUid="+obj.id;
	deleteDataCommon("/deleteMstD005Owner",data);
}
//修改供应商信息
function updateMstD005OwnerForward(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/updateMstD005OwnerForword?ownerUid="+obj.id;
	Modalshow('修改供应商',url,700,480,3);
}