$(function () {
    //加载弹出层
    layui.use(['form','element'],
    function() {
        layer = layui.layer;
        element = layui.element;
    });
   $("tbody.x-cate tr[fid!='0']").hide();
    // 栏目多级显示效果
    $('.x-show').click(function () {
        if($(this).attr('status')=='true'){
            $(this).html('&#xe625;'); 
            $(this).attr('status','false');
            cateId = $(this).parents('tr').attr('cate-id');
            $("tbody tr[fid="+cateId+"]").show();
       }else{
            cateIds = [];
            $(this).html('&#xe623;');
            $(this).attr('status','true');
            cateId = $(this).parents('tr').attr('cate-id');
            getCateId(cateId);
            for (var i in cateIds) {
                $("tbody tr[cate-id="+cateIds[i]+"]").hide().find('.x-show').html('&#xe623;').attr('status','true');
            }
       }
    })
    
})
var cateIds = [];
function getCateId(cateId) {
    
    $("tbody tr[fid="+cateId+"]").each(function(index, el) {
        id = $(el).attr('cate-id');
        cateIds.push(id);
        getCateId(id);
    });
}
/**
 * layui弹出框共同方法
 * @param title 标题
 * @param url 请求的url
 * @param w 弹出层宽度（缺省调默认值）
 * @param h 弹出层高度（缺省调默认值）
 * @param type 弹出操作类型
 * @param upload 是否上传
 * @returns
 */
function Modalshow(title,url,w,h,type,upload){
	
    if (title == null || title == '') {
        title=false;
    };
    if (url == null || url == '') {
        url="404.html";
    };
    if (w == null || w == '') {
        w=($(window).width()*0.9);
    };
    if (h == null || h == '') {
        h=($(window).height() - 50);
    };
    var btn ="";
    if (type=='2') {
    	if(upload){
    		btn =['保存','取消','附件']
    	}else{
    		btn =['保存','取消']
    	}
	}else if (type=='3') {
		if(upload){
    		btn =['更新','取消','附件']
    	}else{
    		btn =['更新','取消']
    	}
	}
    if(type=='1'){
		layer.open({
	        type: 2,
	        area: [w+'px', h +'px'],
	        anim: 0,
	        fix: false, //不固定
	        maxmin: true,
	        shadeClose: false,
	        resize:false,//不允许拉伸
	        btnAlign: 'c',
	        shade:0.4,
	        title: title,
	        content: url,
	        skin: 'layer-myskin',
	        btn: ['确认']
	    });
	}else{layer.open({
        type: 2,
        area: [w+'px', h +'px'],
        anim: 4,
        fix: false, //不固定
        maxmin: true,
        shadeClose: false,
        resize:false,//不允许拉伸
        shade:0.4,
        title: title,
        content: url,
        btn: btn,
        skin: 'layer-myskin'
		,btn1: function(index, layero){
			$(layero).find("iframe")[0].contentWindow.$('#submitForm').click();
		  }
		,btn2: function(index, layero){
			layer.close(index);
		 }
		,btn3: function(index, layero){
			//获取父页面的iframe值
			var iframeName = $(layero).find("iframe")[0].id;
			var contextPath= getContextPath();
			var url = contextPath+'/uploadFilesForworrd';
			//打开子页面 传入iframe值是为了更好的指定传回值的页面
			var uploadFileIds = uploadShow("附件列表",url,900,600,iframeName);
			return false;
		 }
    });
	}
    
}
//文件上传modal
function uploadShow(title,url,w,h,iframeName){
	var uploadFileIds;
    var btn =['开始上传','保存','取消'];
    layer.open({
        type: 2,
        area: [w+'px', h +'px'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: false,
        shade:0.4,
        title: title,
        content: url,
        btn: btn,
        skin: 'layer-myskin',
        zIndex: layer.zIndex,
        success: function(layero){
            layer.setTop(layero);//置顶当前窗口
          }
		,btn1: function(index, layero){
			$(layero).find("iframe")[0].contentWindow.$('#uploadFileButton').click();
			return false;
		  }
		,btn2: function(index, layero){
			//子页面的元素值
			uploadFileIds = $(layero).find("iframe")[0].contentWindow.$('#uploadFileIds').val();
			uploadFileIds=uploadFileIds.substring(0,uploadFileIds.length-1);
			//data
			var dataArray=new Array();
			var dataMap={};
			dataMap.name = "FileIds";
			dataMap.value = uploadFileIds;
			dataArray.push(dataMap);
			//element
			var eleArray=new Array();
			eleArray.push("FileIds");
			//进行子父页面传值操作
			setChooseValues(eleArray,dataArray,iframeName,layer,"input","id");
		 }
		,btn3: function(index, layero){
			layer.close(index);
		  }
    });
    return uploadFileIds;
}
/*关闭弹出框口*/
function Modalclose(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
/**
 * 
 * @param _element 元素名称
 * @param _data 子页面元素名称数据
 * @param iframeName 父页面iframeName
 * @param layer 子页面layer
 * @param type 指定父页面元素dom类型
 * @param elementPro 指定父页面元素属性
 * @returns
 */
function setChooseValues(_element,_data,iframeName,layer,type,elementPro){
	var index = layer.getFrameIndex(iframeName);
	for (var i = 0; i < _element.length; i++) {
		var element = layer.getChildFrame(type+"["+elementPro+"='"+_element[i]+"']", index);
		for (var j = 0; j < _data.length; j++) {
			if(_element[i]==_data[j].name){
				element[0].value=_data[j].value;
			}
		}
	}
}
