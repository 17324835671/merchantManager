function queryUserInfoList(pageNum) {
	var contextPath = getContextPath();
	var sex=$('input:radio[name="sex"]:checked').val();
	var displayName = $("#displayName").val();
	var isdel = $("#isdel").val();
	var url = contextPath+"/queryUserInfoList?currentNum="+pageNum+"&sex="+sex+"&displayName="+displayName+"&isdel="+isdel;
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
		    getPage("queryUserInfoList");
		    layer.close(index);
		 }
	 });
}
//新增用户
function saveUserForward() {
	var contextPath = getContextPath();
	var url = contextPath+"/addUserForworrd";
	Modalshow('添加用户',url,700,500,2);
	
}
//查看用户信息
function viewUserInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/viewUserInfo?userId=" + obj.id;
	Modalshow('查看用户',url,700,500,1);
}
//删除用户
function deleteUser(obj){
	var data = "userId="+obj.id;
	deleteDataCommon("/deleteUser",data);
}
//修改用户信息
function updateUserForward(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/updataUserForworrd?userId="+obj.id;
	Modalshow('修改用户',url,700,500,3);
}
function exportUserInfo(obj){
	var contextPath = getContextPath();
	var sex=$('input:radio[name="sex"]:checked').val();
	var displayName = $("#displayName").val();
	var isdel = $("#isdel").val();
	var url = contextPath+"/queryUserExcelExport?currentNum="+obj.id+"&sex="+sex+"&displayName="+displayName+"&isdel="+isdel;
	ExcelExport(url);
}
