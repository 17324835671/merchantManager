//初始化layui
var form = null,layer = null;
layui.use(["layer","form"], function(){
	form = layui.form;
	layer = layui.layer;
});

function loadingAjaxHtml(url){
	var htmlStr="";
	var layer_load=null;
	$.ajax({
		type: "POST",
		dataType: "html",
		cache: false,
		timeout: 10000,
		url: url,
		async: false,
		success: function(data){
			htmlStr=data;
		},
		complete: function(request) {
			setTimeout(function () { 
				layer.close(layer_load);//关闭
		    }, 200);
		},
		error: function(data){
			layer.msg('访问服务器失败！', {icon: 5,area: ['100px', '60px']});
		},
		beforeSend: function(data){
			layer_load=layer.load(0,{shade:[0.7,'#F0F0EA']});
		}
	});
	return htmlStr;
}

function loadingAjaxJson(url){
	var jsonObj="";
	var layer_load=null;
	$.ajax({
		type: "POST",
		dataType: "text",
		cache: false,
		timeout: 10000,
		url: url,
		async: false,
		success: function(data){
			jsonObj=eval(data);
		},
		complete: function(request) {
			setTimeout(function () { 
				layer.close(layer_load);//关闭
		    }, 200);
		},
		error: function(data){
			layer.msg('访问服务器失败！', {icon: 5,area: ['100px', '60px']});
		},
		beforeSend: function(data){
			layer_load=layer.load(0,{shade:[0.7,'#F0F0EA']});
		}
	});
	return jsonObj;
}

function loadingAjaxPage(url){
	layer.load(0,{shade:[0.7,'#F0F0EA']});
	window.location.href=url;
}

function loadingAjaxJsonData(url,data){
	var jsonObj="";
	var layer_load=null;
    if(typeof(data) == "object" && Object.prototype.toString.call(data).toLowerCase() == "[object object]" && !data.length){
    	data = JSON.stringify(data)
    }else{
    	data = data || "{}";
    }
	$.ajax({
		type: "POST",
		cache: false,
		timeout: 10000,
		url: url,
		contentType: "application/json;charset=utf-8", 
		data: data,
		dataType: "json",
		async: false,
		success: function(data){
			jsonObj=data
		},
		complete: function(request) {
			setTimeout(function () { 
				layer.close(layer_load);//关闭
		    }, 200);
		},
		error: function(data){
			layer.msg('访问服务器失败！', {icon: 5,area: ['100px', '60px']});
		},
		beforeSend: function(data){
			layer_load=layer.load(0,{shade:[0.7,'#F0F0EA']});
		}
	});
	return jsonObj;
}

//跨域访问
var jsonCorsData="";
function loadingAjaxJsonNotLayerAndCors(callbackFunction,url){
	$.support.cors = true;
	var contentType ="application/x-www-form-urlencoded;charset=utf-8";
	if(window.XDomainRequest) //for IE8,IE9
	contentType = "text/plain";
	$.ajax({
		type:"post",
		dataType: "json",
		url:url,
		contentType:contentType,
		cache: false,
		timeout: 10000,
		success: function(data){
			jsonCorsData=data;
			eval(callbackFunction+"()");
		},
		error: function (data) {
           alert("readyState::"+data.readyState+"\nstatus::"+data.status+"\nstatusText::"+data.statusText +"\nresponseText::"+data.responseText);
        }
	});
}
