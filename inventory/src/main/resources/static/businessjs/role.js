function queryRoleInfoList(pageNum) {
	var contextPath = getContextPath();
	var roleName = $("#roleName").val();
	var available = $("#available").val();
	var url = contextPath+"/queryRoleList?currentNum="+pageNum+"&roleName="+roleName+"&available="+available;
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
		    getPage("queryRoleInfoList");
		    layer.close(index);
		 }
	 });
}
//新增角色弹出框
function saveRoleForward() {
	var contextPath = getContextPath();
	var url = contextPath + "/addRoleForworrd";
	Modalshow('添加角色', url, 600, 380,2);
}
// 查看角色信息
function viewRoleInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath + "/viewRoleInfo?roleId=" + obj.id;
	Modalshow('查看角色', url, 800, 700, 1);
}
// 删除角色
function deleteRole(obj) {
	var data = "roleId=" + obj.id;
	deleteDataCommon("/deleteRole", data);
}
// 修改角色信息
function updateRoleForward(obj) {
	var contextPath = getContextPath();
	var url = contextPath + "/updateRoleForword?roleId=" + obj.id;
	Modalshow('修改角色', url, 600, 380,3);
}
//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////用户角色/////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
function getUserList(obj) {
	document.getElementById("user_roleId").value=obj.roleId;
	var contextPath = getContextPath();
	var url = contextPath + "/queryUserListByRoleId?roleId="+ obj.roleId;
	var html ="";
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
			data=data.data;
			document.getElementById("returnList").innerHTML = "";
			for (var i = 0; i < data.length; i++) {
				html += "<tr><td><input type='checkbox' class='checkUser' name='checkUser' lay-filter='checkOne' lay-skin='primary' value="+ data[i].userId+">" 
						+"<div class='layui-unselect layui-form-checkbox' lay-skin='primary'><i class='layui-icon layui-icon-ok'></i></div></td>" 
						+"<td>"+ data[i].userName+ "</td>"
						+ "<td>"+ data[i].displayName+ "</td>"
						+ "<td  align='center'>";
						if(data[i].isdel==0){
							html +="<span class=\"layui-btn layui-btn-xs\">已启用</span>";
						}else{
							html +="<span class=\"layui-btn layui-btn-primary layui-btn-xs layui-btn-disabled\">已停用</span>";
						}
						html += "</td><td  align='center'>"
						+ "<button class=\"layui-btn-danger layui-btn layui-btn-xs\" id='"+ data[i].userId+ "' onclick=\"deleteUser_role(this);\">删除</button>"
						+ "</td></tr>"
			}
			document.getElementById("returnList").innerHTML = html;
			layer.close(index);
		}
	});
}
// 删除用户角色
function deleteUser_role(obj) {
	var roleId = document.getElementById("user_roleId").value;
	var contextPath = getContextPath();
	var data = "userId=" + obj.id + "&roleId=" + roleId;
	deleteDataCommon("/deleteUser_role",data);
}
//删除全部用户角色
function deleteUser_roleAll() {
	var roleId = document.getElementById("user_roleId").value;
	var contextPath = getContextPath();
	
	var checkUsers = document.getElementsByName("checkUser");
	  var userIds="";
	  for(var i =0;i<checkUsers.length;i++){
		if(checkUsers[i].checked){
			userIds+=checkUsers[i].value+",";
		}
		if(i+1==checkUsers.length){
			userIds=userIds.substring(0,userIds.length-1);
		}
	  }
	  if(userIds.length==0){
    	layer.msg('请选择用户',{icon:5,anim: 6});
    	return false;
    }
	  
	var data = "userIds="+userIds+"&roleId=" + roleId;
	deleteDataCommon("/deleteUserRoleAll",data);
}
// 查询用户信息
function queryUserBasicInfo(obj) {
	var roleId = document.getElementById("user_roleId").value;
	var contextPath = getContextPath();
	var url = contextPath + "/queryUserList?roleId=" +roleId;
	Modalshow('添加角色用户', url, 900, 700,2);
}