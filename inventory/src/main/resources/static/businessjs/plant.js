function queryPlantInfoList(pageNum) {
	console.log('queryPlantInfoList')
	var contextPath = getContextPath();
	var name = $("#name").val();
	var url = contextPath+"/findPlantList?currentNum="+pageNum+"&name="+name;
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



//新增单位
function savePlant() {
	var contextPath = getContextPath();
	var url = contextPath+"/addPlantForworrd";
	Modalshow('添加单位',url,700,520,2,true);
	
}

//删除用户
function deletePlant(obj){
	console.log("deleteShop",obj)
	deleteDataCommon("/deletePlant",obj);
}
//修改单位信息
function updatePlant(obj) {
	console.log("updatePlant",obj)
	var contextPath = getContextPath();
	var url = contextPath+"/updatePlantForword?id="+obj.id;
	Modalshow('修改单位',url,700,580,3,true);
}

