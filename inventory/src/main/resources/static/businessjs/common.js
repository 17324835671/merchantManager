function AjaxHttpRequest(url,data){
	var index = layer.load(3);
	var contextPath= getContextPath();
	var url = contextPath+url;
	Ext.Ajax.request({
		  url : url,
		  method : 'post',
		  params : data,
		  success : function(request) {
			  var data = jQuery.parseJSON(request.responseText);
			  var icon ="";
			  if(data.code=='0'){
				  icon=1
			  }else{
				  icon=2 
			  }
			  layer.close(index);
			  layer.alert(data.message, {icon: icon,skin:'layer-myskin',zIndex: layer.zIndex},function(index,layero) {
				  layer.setTop(layero);
				  layer.close(index);
	              window.parent.location.reload();
	           });
		  	},
		  failure : function(response) {
			  layer.alert('系统异常', {icon: 2,skin:'layer-myskin',zIndex: layer.zIndex},function (index,layero) {
				  layer.setTop(layero);
				  layer.close(index);
	              window.parent.location.reload();
	            });
		  	}
	});
}
function AjaxHttpRequestNotClosed(url,data){
	var index = layer.load(3);
	var contextPath= getContextPath();
	var url = contextPath+url;
	Ext.Ajax.request({
		  url : url,
		  method : 'post',
		  params : data,
		  success : function(request) {
			  var data = jQuery.parseJSON(request.responseText);
			  var icon ="";
			  if(data.code=='0'){
				  icon=1
			  }else{
				  icon=2 
			  }
			  layer.close(index);
			  layer.alert(data.message, {icon: icon,skin:'layer-myskin',zIndex: layer.zIndex},function(index,layero) {
				  layer.setTop(layero);
				  layer.close(index);
	              window.location.reload();
	           });
		  	},
		  failure : function(response) {
			  layer.alert('系统异常', {icon: 2,skin:'layer-myskin',zIndex: layer.zIndex},function (index,layero) {
				  layer.setTop(layero);
				  layer.close(index);
	              window.location.reload();
	            });
		  	}
	});
}
function deleteDataCommon(url,data){
	layer.confirm('确认删除吗？',{icon: 3,title:'警告',skin:'layer-myskin',},function(index){
    	AjaxHttpRequestNotClosed(url,data);
    });
}
function getPage(method){
    var count = document.getElementById("totalCount").value;
    var currentNum = document.getElementById("currentNum").value;
    var limitSize = document.getElementById("limitSize").value;
	layui.use(["laypage"], function(){
		var laypage = layui.laypage;
		laypage.render({
	  		elem: 'pageTag',
		    count: count,
		    curr: currentNum,
		    limit: limitSize,
		    theme: '#11998e',
		    layout: ['count', 'prev', 'page', 'next','skip'],
	    	jump: function(obj, first){
		    	if(!first){
		    		eval(method+"('"+obj.curr+"')");
				}
	    	}
	  	});
	});
}
function refreshPage(obj){
	var contextPath = getContextPath();
	var url = contextPath+obj;
	var temp = document.createElement("form"); 
	temp.action = url; 
	temp.method = "get"; 
	temp.style.display = "none"; 
	var paramters = urlParse(url);
	for (var name in paramters) { 
	    var opt = document.createElement("textarea"); 
	    opt.name = name; 
	    opt.value = paramters[name];
	    temp.appendChild(opt); 
	} 
	document.body.appendChild(temp); 
	temp.submit(); 
	return temp;
}
function urlParse(url){
    var obj = {};
    var arr = url.split("&");
    if (arr) {
        arr.forEach(function (item) {
            var tempArr = item.substring(0).split('=');
            var key = decodeURIComponent(tempArr[0]);
            var val = decodeURIComponent(tempArr[1]);
            obj[key] = val;
        });
    }
    return obj;
}
function getContextPath(){
	var contextPath = document.getElementById("contextPath").value;
	return contextPath;
}
function ExcelExport(url){
    var elemIF = document.createElement("iframe");
    elemIF.src = url;
    elemIF.style.display = "none";     
    document.body.appendChild(elemIF);
}
function downLoadFile(obj){
	var contextPath = getContextPath();
	var url = contextPath+"/findFileInfo?mongodbId="+obj.id;
    var elemIF = document.createElement("iframe");
    elemIF.src = url;
    elemIF.style.display = "none";     
    document.body.appendChild(elemIF);
}
//查看当前用户信息
function viewCurrentUserInfo() {
	var contextPath = getContextPath();
	var url = contextPath+"/viewUserInfo?currentUserId=0";
	Modalshow('查看当前用户',url,800,600,'1');
}
//修改当前用户信息
function updateCurrentUserInfo() {
	var contextPath = getContextPath();
	var url = contextPath+"/updataUserForworrd?currentUserId=0";
	Modalshow('修改当前用户',url,800,600,'3');
}
function changeMouse(obj){
	obj.style.cursor='pointer';
}
function recoveryMouse(obj){
	obj.style.cursor='black';
}
function strToArray(data){
	data = data.split(",");
	return data;
}
function strToJson(data){
	var data = eval('('+data+')');
	return data;
}
function randomNum(minNum,maxNum){ 
    switch(arguments.length){ 
        case 1: 
            return parseInt(Math.random()*minNum+1,10); 
        break; 
        case 2: 
            return parseInt(Math.random()*(maxNum-minNum+1)+minNum,10); 
        break; 
        default: 
            return 0; 
        break; 
    } 
} 