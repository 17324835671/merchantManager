function queryOrderInfoList(pageNum) {
	console.log('queryOrderInfoList')
	var contextPath = getContextPath();
	var name = $("#shopName").val();
    var timeDesc = $("#timeDesc").val();
	var url = contextPath+"/findOrderList?currentNum="+pageNum+"&shopName="+name+"&timeDesc="+timeDesc;
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
		    getPage("queryOrderInfoList");
		    layer.close(index);
		 }
	 });
}



//新增单位
function saveOrder() {
	var contextPath = getContextPath();
	var url = contextPath+"/addOrderForworrd";
	Modalshow('添加单位',url,1200,520,2,true);
	
}

//删除用户
function deleteOrder(obj){
	deleteDataCommon("/deleteOrder",obj);
}
//修改单位信息
function updateOrder(obj) {
	console.log("updateOrder",obj)
	var contextPath = getContextPath();
	var url = contextPath+"/updateOrderForword?id="+obj.id;
	Modalshow('修改单位',url,1200,580,3,true);
}

