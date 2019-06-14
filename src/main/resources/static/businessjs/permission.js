//新增权限
function savePermissionForward() {
	var contextPath = getContextPath();
	var url = contextPath+"/addPermissionForworrd";
	Modalshow('添加权限',url,700,500,2);
	
}
//查看权限信息
function viewPermissionInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/viewPermissionInfo?permissionId=" + obj.id;
	Modalshow('查看权限',url,500,430,1);
}
//删除权限
function deletePermission(obj) {
	var data = "permissionId=" + obj.id;
	deleteDataCommon("/deletePermission", data);
}
//查看角色信息
function viewRoleInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath + "/viewRoleInfo?roleId=" + obj.id;
	Modalshow('查看角色', url, 800,680, 1);
}
//修改权限信息
function updatePermissionForward(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/updatePermissionForworrd?permissionId="+obj.id;
	Modalshow('修改权限',url,700,500,'3');
}
//修改权限信息
function getUserPageInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/updataUserForworrd?userId="+obj.id;
	Modalshow('修改用户',url,800,680);
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////角色权限/////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//查询用户信息
function queryPermissionList(obj){
	var roleId = document.getElementById("permission_roleId").value;
	var contextPath = getContextPath();
	var url = contextPath + "/queryPermissionInfoList?roleId=" +roleId;
	Modalshow('添加角色权限', url, 900, 680,2);
}
//查询角色下的权限
function getPermissionListByRoleId(obj){
	document.getElementById("permission_roleId").value=obj.roleId;
	var contextPath = getContextPath();
	var url = contextPath + "/queryPermissionListByRoleId?roleId="+obj.roleId;
	var html="";
	var index;
	layui.use("layer",function(){
		var layer = layui.layer;
		index = layer.load(3);
	});
	Ext.Ajax.request({
		url : url,
		method : 'post',
		success : function(request) {
			var data = jQuery.parseJSON(request.responseText);
			data = data.data;
			document.getElementById("returnList").innerHTML="";
			for (var i = 0; i < data.length; i++) {
				if(data[i].parentId=='0'){
					html+="<tr><td><input type='checkbox' class='checkPermission' name='checkPermission' lay-filter='checkOne' lay-skin='primary' value="+data[i].permissionId+">"
						+"<div class='layui-unselect layui-form-checkbox' lay-skin='primary'><i class='layui-icon layui-icon-ok'></i></div></td>" 
						+"<td>"+data[i].permissionName+"</td>"
						+"<td>"+data[i].permissionUrl+"</td>"
						+"<td align='center'><button id="+data[i].permissionId+" class='layui-btn-danger layui-btn layui-btn-xs' onclick='deleteRole_permission(this)'><i class='layui-icon'>&#xe640;</i>删除</button></td>" 
						+"</tr>"
				}else{
					html+="<tr><td><input type='checkbox' class='checkPermission' name='checkPermission' lay-filter='checkOne' lay-skin='primary' value="+data[i].permissionId+">"
						+"<div class='layui-unselect layui-form-checkbox' lay-skin='primary'><i class='layui-icon layui-icon-ok'></i></div></td>" 
						+"<td><span style='margin-left: 40px;'>"+data[i].permissionName+"</span></td>"
						+"<td>"+data[i].permissionUrl+"</td>"
						+"<td align='center'><button id="+data[i].permissionId+" class='layui-btn-danger layui-btn layui-btn-xs' onclick='deleteRole_permission(this)'><i class='layui-icon'>&#xe640;</i>删除</button></td>" 
						+"</tr>"
				}
			}
			document.getElementById("returnList").innerHTML=html;
			layer.close(index);
		}
	});
}
//删除角色权限
function deleteRole_permission(obj) {
	var roleId = document.getElementById("permission_roleId").value;
	var data = "permissionId="+obj.id+"&roleId="+roleId;
	var contextPath = getContextPath();
	deleteDataCommon("/deletePermission_role",data);
}
//删除全部用户角色
function deleteRolePermissionAll() {
	var roleId = document.getElementById("permission_roleId").value;
	var contextPath = getContextPath();
	
	var checkPermissions = document.getElementsByName("checkPermission");
	  var permissionIds="";
	  for(var i =0;i<checkPermissions.length;i++){
		if(checkPermissions[i].checked){
			permissionIds+=checkPermissions[i].value+",";
		}
		if(i+1==checkPermissions.length){
			permissionIds=permissionIds.substring(0,permissionIds.length-1);
		}
	  }
	  if(permissionIds.length==0){
    	layer.msg('请选择权限',{icon:5,anim: 6});
    	return false;
    }
	var data = "permissionIds="+permissionIds+"&roleId=" + roleId;
	deleteDataCommon("/deleteRolePermissionAll",data);
}
