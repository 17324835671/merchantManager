function queryVegetablesInfoList(pageNum) {
	console.log('queryVegetablesInfoList')
	var contextPath = getContextPath();
	var name = $("#name").val();
	var url = contextPath+"/findVegetablesList?currentNum="+pageNum+"&name="+name;
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
		    getPage("queryVegetablesInfoList");
		    layer.close(index);
		 }
	 });
}



//新增单位
function saveVegetables() {
	var contextPath = getContextPath();
	var url = contextPath+"/addVegetablesForworrd";
	Modalshow('添加单位',url,700,520,2,true);
	
}

//删除用户
function deleteVegetables(obj){
	console.log("deleteVegetables",obj)
	deleteDataCommon("/deleteVegetables",obj);
}
//修改单位信息
function updateVegetables(obj) {
	console.log("updateVegetables",obj)
	var contextPath = getContextPath();
	var url = contextPath+"/updateVegetablesForword?id="+obj.id;
	Modalshow('修改单位',url,700,580,3,true);
}

