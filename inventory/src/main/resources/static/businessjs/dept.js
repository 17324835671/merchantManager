function queryDeptInfoList(pageNum) {
	var contextPath = getContextPath();
	var deptName = $("#deptName").val();
	var ownerUid = $("#ownerUid").val();
	var url = contextPath+"/queryMstD006InfoList?currentNum="+pageNum+"&deptName="+deptName+"&ownerUid="+ownerUid;
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
		    getPage("queryDeptInfoList");
		    layer.close(index);
		 }
	 });
}
//新增供应商部门
function addDeptForward() {
	var contextPath = getContextPath();
	var url = contextPath+"/addMstD006DepartmentForworrd";
	Modalshow('添加供应商部门',url,600,480,2);
	
}
//查看供应商部门信息
function viewMstD006DepartmentInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/viewMstD006DepartmentInfo?deptUid=" + obj.id;
	Modalshow('查看供应商部门',url,600,400,1);
}
//删除供应商部门
function deleteMstD006Department(obj){
	var data = "deptUid="+obj.id;
	deleteDataCommon("/deleteMstD006Department",data);
}
//修改供应商部门
function updateMstD006DepartmentForward(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/updateMstD006DepartmentForword?deptUid="+obj.id;
	Modalshow('修改供应商部门',url,600,480,3);
}