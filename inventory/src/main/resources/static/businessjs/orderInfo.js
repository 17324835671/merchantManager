
function queryOrderDeatilList(pageNum) {
    console.log('queryOrderDeatilList')
    var contextPath = getContextPath();
    var name = $("#shopName").val();
    var timeDesc = $("#timeDesc").val();
    var url = contextPath+"/findOrderInfoList?currentNum="+pageNum+"&shopName="+name+"&timeDesc="+timeDesc;
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
            getPage("queryOrderDeatilList");
            layer.close(index);
        }
    });
}

