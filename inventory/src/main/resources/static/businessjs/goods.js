function queryGoodsInfoList(pageNum) {
	console.log('queryGoodsInfoList')
	var contextPath = getContextPath();
	var name = $("#name").val();
	var url = contextPath+"/findGoodsList?currentNum="+pageNum+"&name="+name;
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
		    getPage("queryGoodsInfoList");
		    layer.close(index);
		 }
	 });
}



//新增单位
function saveGoods() {
	var contextPath = getContextPath();
	var url = contextPath+"/addGoodsForworrd";
	Modalshow('添加单位',url,700,520,2,true);
	
}

//删除用户
function deleteGoods(obj){
	console.log("deleteGoods",obj)
	deleteDataCommon("/deleteGoods",obj);
}
//修改单位信息
function updateGoods(obj) {
	console.log("updateGoods",obj)
	var contextPath = getContextPath();
	var url = contextPath+"/updateGoodsForword?id="+obj.id;
	Modalshow('修改单位',url,700,580,3,true);
}

