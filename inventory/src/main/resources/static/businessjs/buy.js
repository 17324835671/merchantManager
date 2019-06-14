function queryBuyInfoList(pageNum) {
	console.log('queryBuyInfoList')
	var contextPath = getContextPath();
	var name = $("#name").val();
    var timeDesc = $("#timeDesc").val();
	var url = contextPath+"/findBuyList?currentNum="+pageNum+"&name="+name+"&timeDesc="+timeDesc;
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
		    getPage("queryBuyInfoList");
		    layer.close(index);
		 }
	 });
}
