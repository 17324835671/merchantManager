function queryAccountInfoList(pageNum) {
	console.log('queryAccountInfoList')
	var contextPath = getContextPath();
	var name = $("#name").val();
    var timeDesc = $("#timeDesc").val();
	var url = contextPath+"/findAccountList?currentNum="+pageNum+"&name="+name+"&timeDesc="+timeDesc;
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
		    getPage("queryAccountInfoList");
		    layer.close(index);
		 }
	 });
}
function queryAccountShopInfoList(pageNum) {
    console.log('queryAccountShopInfoList')
    var contextPath = getContextPath();
    var name = $("#name").val();
    var timeDesc = $("#timeDesc").val();
    var url = contextPath+"/findAccountShopList?currentNum="+pageNum+"&name="+name+"&timeDesc="+timeDesc;
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
            getPage("queryAccountShopInfoList");
            layer.close(index);
        }
    });
}