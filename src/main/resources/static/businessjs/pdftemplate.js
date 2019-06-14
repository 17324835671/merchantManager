function findPdfTemplateList(pageNum) {
	var contextPath = getContextPath();
	var templatename = $("#templatename").val();
	var url = contextPath+"/findPdfTemplateList?currentNum="+pageNum+"&templatename="+templatename;
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
		    getPage("findPdfTemplateList");
		    layer.close(index);
		 }
	 });
}
function AddTemplateForward() {
	var contextPath = getContextPath();
	var url = contextPath+"/AddTemplateForward";
	Modalshow('添加PDF模板',url,500,380,2);
	
}
function templateView(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/templateView?templateid=" + obj.id;
	Modalshow('查看PDF模板',url,500,380,1);
}
function pdfView(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/pdfView?templateid=" + obj.id;
	Modalshow('查看PDF文书',url,800,600,1);
}

function deletePdftemplate(obj){
	var data = "templateid="+obj.id;
	deleteDataCommon("/deletePdftemplate",data);
}
function updatePdftemplateForward(obj) {
	var contextPath = getContextPath();
	var url = contextPath+"/updatePdftemplateForward?templateid="+obj.id;
	Modalshow('修改PDF模板',url,500,380,3);
}
