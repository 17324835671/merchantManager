function queryTemperatureInfoList() {
	var contextPath = getContextPath();
	var locationCode = $("#locationCode").val();
	var url = contextPath+"/queryTemperatureInfoList?locationCode="+locationCode;
	var index;
	layui.use("layer",function(){
		var layer = layui.layer;
		index = layer.load(3);
	});
	Ext.Ajax.request({
		 url: url,
		 method : 'post',
		 success: function(request){
			var data = jQuery.parseJSON(request.responseText);
			data = data.data;
		    document.getElementById("temperatureSensorDevice").innerHTML="";
		    var html = ""
			if(data.length!=0){
				for (var i = 0; i < data.length; i++) {
					var deviceNo=data[i].deviceNo;
					var deviceName=data[i].deviceName;
					var slelfName=data[i].slelfName;
						$("#temperatureSensorDevice").append("<li style=\"height: 200px;\" ondblclick=\"zoomChart(\'temperatureChart\',\'"+deviceName+"\',\'"+deviceNo+"\',\'"+slelfName+"\');\">" 
								+"<div id=\"temperature_"+i+"\" style=\"width: 90%;height: 95%;\"></div>" 
								+"<input type=\"hidden\" id=\"warning_temperature_"+i+"\" value=\"0\"/></li>");
						temperatureChart(deviceName,deviceNo,slelfName,"temperature_"+i);
				}
			}
		    layer.close(index);
		}
	});
}
//温度传感器图表
function temperatureChart(deviceName,deviceNo,slelfName,id){
	var getele = document.getElementById(id);
	var myChart = echarts.init(document.getElementById(id));
	var option = {
	    title: {
	        text: slelfName
	    },
	    tooltip: {
	        trigger: 'axis',
	        formatter: "{b}<br/>{c} ℃"
	    },
	    grid: {
	        left: '3%',
	        right: '9%',
	        bottom: '3%',
	        top: '20%',
	        containLabel: true
	    },
	    xAxis: [
	        {
	            type: 'category',
	            boundaryGap: false,
	            data: (function (){
	                var now = new Date();
	                var res = [];
	                var len = 10;
	                while (len--) {
	                    res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
	                    now = new Date(now-5000);
	                }
	                return res;
	            })()
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            boundaryGap: [0, '100%'],
	            min: 0,
	            max: 50,
	            interval: 10,
	            axisLabel: {
		            formatter: '{value} ℃'
		        }
	        }
	    ],
	    series: [
	        {
	            name:slelfName,
	            type:'line',
	            markLine: {
	                data: [
	                    {type: 'average', name: '平均值'}
	                ]
	            },
	            data:[0,0,0,0,0,0,0,0,0,0]
	        }
	    ]
	};
	myChart.setOption(option, true);
	
	setInterval(function (){
		var ayisData = 0;
		var date = new Date().toLocaleTimeString();
		var axisData = date.substring(2,date.length);
		ayisData = randomNum(0,40);
		var data0 = option.series[0].data;
	    data0.shift();
	    data0.push(ayisData);
	    option.xAxis[0].data.shift();
	    option.xAxis[0].data.push(axisData);
	    myChart.setOption(option);
		
	},5000);
}
function queryHumidityInfoSearch() {
	var contextPath = getContextPath();
	var locationCode = $("#locationCode").val();
	var url = contextPath+"/queryHumidityInfoList?locationCode="+locationCode;
	var index;
	layui.use("layer",function(){
		var layer = layui.layer;
		index = layer.load(3);
	});
	Ext.Ajax.request({
		 url: url,
		 method : 'post',
		 success: function(request){
			var data = jQuery.parseJSON(request.responseText);
			data = data.data;
		    document.getElementById("humiditySensorDevice").innerHTML="";
		    var html = ""
			if(data.length!=0){
				for (var i = 0; i < data.length; i++) {
					var deviceNo=data[i].deviceNo;
					var deviceName=data[i].deviceName;
					var slelfName=data[i].slelfName;
						$("#humiditySensorDevice").append("<li style=\"height: 200px;\" ondblclick=\"zoomChart(\'humidityChart\',\'"+deviceName+"\',\'"+deviceNo+"\',\'"+slelfName+"\');\">" 
								+"<div id=\"humidity_"+i+"\" style=\"width: 90%;height: 95%;\"></div>" 
								+"<input type=\"hidden\" id=\"warning_humidity_"+i+"\" value=\"0\"/></li>");
						humidityChart(deviceName,deviceNo,slelfName,"humidity_"+i);
				}
			}
		    layer.close(index);
		}
	});
}
//湿度传感器图表
function humidityChart(deviceName,deviceNo,slelfName,id){
	var getele = document.getElementById(id);
	var myChart = echarts.init(document.getElementById(id));
	var option = {
	    title: {
	        text: slelfName
	    },
	    tooltip: {
	        trigger: 'axis',
	        formatter: "{b}<br/>{c} RH"
	    },
	    grid: {
	        left: '3%',
	        right: '9%',
	        bottom: '3%',
	        top: '20%',
	        containLabel: true
	    },
	    xAxis: [
	        {
	            type: 'category',
	            boundaryGap: false,
	            data: (function (){
	                var now = new Date();
	                var res = [];
	                var len = 10;
	                while (len--) {
	                    res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
	                    now = new Date(now-5000);
	                }
	                return res;
	            })()
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            boundaryGap: [0, '100%'],
	            axisLabel: {
		            formatter: '{value} RH'
		        }
	        }
	    ],
	    series: [
	        {
	            name:slelfName,
	            type:'line',
	            markLine: {
	                data: [
	                    {type: 'average', name: '平均值'}
	                ]
	            },
	            data:[0,0,0,0,0,0,0,0,0,0]
	        }
	    ]
	};
	myChart.setOption(option, true);
	
	setInterval(function (){
		var ayisData = 0;
		var date = new Date().toLocaleTimeString();
		var axisData = date.substring(2,date.length);
		ayisData = randomNum(0,40);
		var data0 = option.series[0].data;
	    data0.shift();
	    data0.push(ayisData);
	    option.xAxis[0].data.shift();
	    option.xAxis[0].data.push(axisData);
	    myChart.setOption(option);
		
	},5000);
}
function zoomChart(funnctionName,deviceName,deviceNo,slelfName){
	var width = $(window).width()-50+"px";
	var height = $(window).height()-50+"px";
	$("#tempChart").height(height);
	//页面层
	layer.open({
		type: 1,
	  	shadeClose: true, //开启遮罩关闭
	  	title: false, //不显示标题
		closeBtn: 1, //不显示关闭按钮
	  	area: [width,height], //宽高
	  	content: $("#tempChartDiv")
	});
	eval(funnctionName+"('"+deviceName+"','"+deviceNo+"','"+slelfName+"','tempChart')");
}