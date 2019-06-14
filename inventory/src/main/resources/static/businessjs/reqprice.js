function queryMstD007InfoList(pageNum) {
	var contextPath = getContextPath();
	var reqpName = $("#reqpName").val();
	var effMonthFrom = $("#effMonthFrom").val();
	var effMonthTo =$("#effMonthTo").val();
	var url = contextPath+"/queryMstD007InfoList?currentNum="+pageNum+"&reqpName="+reqpName+"&effMonthFrom="+effMonthFrom
	+"&effMonthTo="+effMonthTo;
	var index = layer.load(3);
	Ext.Ajax.request({
		 url: url,
		 method : 'post',
		 success: function(request){
		    Ext.getDom("returnList").innerHTML=request.responseText;
		    getPage("queryMstD007InfoList");
		    layer.close(index);
		 }
	 });
}
//新增商品定价组
function addMstD007ReqPriceHForworrd() {
	var contextPath = getContextPath();
	var url = contextPath+"/addMstD007ReqPriceHForworrd";
	Modalshow('添加商品定价组',url,680,500,2);
	
}
//查看商品定价组信息
function viewMstD007ReqPriceHInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/viewMstD007ReqPriceHInfo?reqpUid=" + obj.id;
	Modalshow('查看商品定价组信息',url,680,500,1);
}
//删除商品定价组
function deleteMstD007ReqPriceH(obj){
	var data = "reqpUid="+obj.id;
	deleteDataCommon("/deleteMstD007ReqPriceH",data);
}
//修改商品定价组信息
function updateMstD007ReqPriceHForword(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/updateMstD007ReqPriceHForword?reqpUid="+obj.id;
	Modalshow('修改商品定价组',url,680,500,3);
}
//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////商品定价/////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
function queryMstD008InfoList(pageNum) {
	var contextPath = getContextPath();
	var effMonthFrom = $("#effMonthFrom").val();
	var effMonthTo =$("#effMonthTo").val();
	var url = contextPath+"/queryMstD008InfoList?currentNum="+pageNum+"&effMonthFrom="+effMonthFrom+"&effMonthTo="+effMonthTo;
	Ext.Ajax.request({
		 url: url,
		 method : 'post',
		 success: function(request){
		    Ext.getDom("returnList").innerHTML=request.responseText;
		    getPage("queryMstD008InfoList");
		 }
	 });
}
//新增商品定价
function addMstD008ReqPriceDForworrd() {
	var contextPath = getContextPath();
	var url = contextPath+"/addMstD008ReqPriceDForworrd";
	Modalshow('添加商品定价',url,680,500,2);
	
}
//查看商品定价信息
function viewMstD008ReqPriceDInfo(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/viewMstD008ReqPriceDInfo?reqpDuid=" + obj.id;
	Modalshow('查看商品定价信息',url,680,500,1);
}
//删除商品定价
function deleteMstD008ReqPriceD(obj){
	var data = "reqpDuid="+obj.id;
	deleteDataCommon("/deleteMstD008ReqPriceD",data);
}
//修改商品定价信息
function updateMstD008ReqPriceDForword(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/updateMstD008ReqPriceDForword?reqpDuid="+obj.id;
	Modalshow('修改商品定价',url,680,500,3);
}