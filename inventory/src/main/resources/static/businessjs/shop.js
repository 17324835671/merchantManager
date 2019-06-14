function queryShopInfoList(pageNum) {
	console.log('queryShopInfoList')
	var contextPath = getContextPath();
	var name = $("#name").val();
	var url = contextPath+"/findShopList?currentNum="+pageNum+"&name="+name;
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
		    getPage("queryShopInfoList");
		    layer.close(index);
		 }
	 });
}



//新增单位
function saveShop() {
	var contextPath = getContextPath();
	var url = contextPath+"/addShopForworrd";
	Modalshow('添加单位',url,700,520,2,true);
	
}

//删除用户
function deleteShop(obj){
	console.log("deleteShop",obj)
	deleteDataCommon("/deleteShop",obj);
}
//修改单位信息
function updateShop(obj) {
	console.log("updateShop",obj)
	var contextPath = getContextPath();
	var url = contextPath+"/updateShopForword?id="+obj.id;
	Modalshow('修改单位',url,700,580,3,true);
}

