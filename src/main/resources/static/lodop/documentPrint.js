		var totalHeight  = 1058;  //总高度
		var totalWidth   = 687;	  //总宽度
		var left         = 75;  //左边空白宽度
		var top          = 220;   //初始上边距，随着行数增长
		var currLeft     = 75;  //当前左边距，随着文本的追加而增长
		var fontSize     = 13;    //字体大小 改变字体大小必须改变fontWisth,numWidth
		var fontWidth    = 18;    //默认单个汉子的宽度，size 为14
		var numWidth     = 9;  //默认数字的宽度，size 为14
		var rowSpacing   = 30;    //默认行间距
		var reserveWidth = 0;   //预留宽度，保证右边对差距最小
		var currPage     = 1;
		var pageCount    = 1;
		var arrText      = [];
		var isFile       = false;
		var rowWidth     = 612; //一行可以于打印文本的宽度；
		
		//添加不带下划线文本且指定下划线长度 ,如果宽度不足缩小字体 str字符串 width 指定宽度 isCenter文本是否居中
    	function addAppointWidthText(str, width , isPreviewOnly, isCenter){
    		if(str == null || str == undefined)
    			str = "";
    		str = str.toString();
			printLineOne(str,width,0,isCenter,1, isPreviewOnly);
    	}
		
		//添加带下划线文本且指定下划线长度 ,如果宽度不足缩小字体 str字符串 width 指定宽度 isCenter文本是否居中
    	function addAppointWidthLineText(str, width , isPreviewOnly, isCenter){
    		if(str == null || str == undefined)
    			str = "";
    		str = str.toString();
			printLineOne(str,width,1,isCenter,1, isPreviewOnly);
    	}
    	
    	//根据不同的条件进行打印 str文本 width打印宽度 isCenter是否居中 0否1是 autoSize宽度不够时是否自动缩小字体0否1是
    	function printLineOne(str,width,isLine,isCenter,autoSize,isPreviewOnly){
    		if(str == null || str == undefined)
    			str = "";
    		str = str.toString();
    		if(top>1050){
    			LODOP.NewPage();
    			top=20;
    		}
    		//保存当前默认fontSize
    		var currFontSize = fontSize;
    		//保存当前默认数字文本宽度
    		var numW = numWidth;
    		//保存当前默认文本宽度
    		var fontW = fontWidth;
    		//保存当前左边距
    		var startLeft = currLeft;
    		//计算文本居中时要往右移动的宽度
    		var addLeftWidth = 0;
    		//单个字符的宽度 默认为文本宽度
    		var currFontWidth = fontWidth;
    		//缩小字体时文本显示时位置偏移高度
    		var addTop = 0;
    		
    		//要求自动大小
    		if(autoSize == 1){
    			var arr = runSize(str,width);
    			//当前宽度适合的fontSize
    			currFontSize = arr[0];
    			//当前文本偏移高度
    			addTop = arr[1];
    			//当前文本单个宽度
    			fontW = arr[2];
    			//当前数字文本单个宽度
    			numW = arr[3];
    		}
    		
    		//要求居中
    		if(isCenter == 1){
    			//求出文本居中要往中间偏移的宽度
    			addLeftWidth = textCenter(str, width ,fontW, numW);
    		}
    		
    		//文本单个打印
    		for(var i = 0; i < str.length; i++){
    			//定义字母正则
    			var reg1   = /^[A-Za-z]+$/;
    			//定义英文标点符号
    			//var reg =/[-._ ,:%'\;`~!@#$^&*()+=|\\\[\]\{\}]/;
    			var reg =/[^\x00-\xff]/;
    			//定义数值正则
    			var regNum = /^[0-9]+$/;
    			//求出不同类型所占宽度
    			if(!reg.test(str.charAt(i))){
    				currFontWidth = numW;
    			}else{
    				currFontWidth = fontW;
    			}
    			//打印
				LODOP.ADD_PRINT_TEXT(top+addTop,currLeft + addLeftWidth,currFontWidth+10,30,str.charAt(i));
    			LODOP.SET_PRINT_STYLEA(0,"FontName","仿宋");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",currFontSize);
				if(isPreviewOnly == 1){
					LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
				}
				currLeft += currFontWidth;
				
			}
			
			//要求添加下划线
			if(isLine == 1){
				LODOP.ADD_PRINT_LINE(top+17,startLeft,top+17,startLeft + width,0,1);
			}
			//左边距追加
			currLeft = startLeft + width;
			
    	}
    	
    	//添加有下划线的文本 str需要打印的字符串， minTextCount最小字数为0时自动，num末行是否为整行 0自动 1为整行，isCenter 是否居中 1居中 0 不居中
    	function addLineText(str, minTextCount, isFullLine, isCenter){
    		if(str == null || str == undefined)
    			str = "";
    		str = str.toString();
    		//有要求最小下划线长度的情况下
    		if(minTextCount != 0){
    			str = fullText(str,minTextCount*fontWidth);
    		}
    		//求出字符串所占宽度
			var width = getStrWidth(str, fontWidth, numWidth);
			
			//得到当前行剩余宽度
			var currWidth = totalWidth-currLeft;
			
			//判断是否要换行
			if(currWidth < width){
				//对文本进行分行
				var arrText = subText(currWidth, str);
				//打印
				printLineOne(arrText[0],currWidth,1,isCenter,0,0);
				//初始左边距默认值
				currLeft = left;
				//换行 上边距追加
				top += rowSpacing;
				//打印剩下的文本 
				addLineText(arrText[1],0, isFullLine,isCenter);
			}else{ //不换行的情况下
				if(isFullLine == 0){//不要求末行为整行
					//打印
					printLineOne(str,width,1,isCenter,0,0);
				}else{//要求末行为整行
					//添加字符串长度为整行长度，以全角空格填充
					str = fullText(str,currWidth);
					//打印
					printLineOne(str,currWidth,1,isCenter,0,0);
					//初始左边距默认值
					currLeft = left;
					//换行上边距追加
					top += rowSpacing;
				}
			}
    	}
    	
    	
    	//添加没有下划线的文本 
    	function addText(str){  
			if(str == null || str == undefined)
    			str = "";
			str = str.toString();
    		//求出字符串所占宽度
			var width = getStrWidth(str, fontWidth, numWidth);
			
			//得到当前行剩余宽度
			var currWidth = totalWidth-currLeft;
			
			//判断是否要换行
			if(currWidth < width){
				
				//对文本进行分行
				var arrText = subText(currWidth, str);
				
				//打印
				printLineOne(arrText[0],currWidth,0,0,0,0);
				
				//初始左边距默认值
				currLeft = left;
				
				//换行上边距追加
				top += rowSpacing;
				
				//打印剩下的文本
				addText(arrText[1]);
				
			}else{//不换行的情况下
				//打印
				printLineOne(str,width,0,0,0,0);
				
			}
    			
    	}
    	
    	//添加没有下划线的文本并且是否只是预览
    	function addTextisPreviewOnly(str,isPreviewOnly){  
			if(str == null || str == undefined)
    			str = "";
			str = str.toString();
    		//求出字符串所占宽度
			var width = getStrWidth(str, fontWidth, numWidth);
			
			//得到当前行剩余宽度
			var currWidth = totalWidth-currLeft;
			
			//判断是否要换行
			if(currWidth < width){
				
				//对文本进行分行
				var arrText = subText(currWidth, str);
				
				//打印
				printLineOne(arrText[0],currWidth,0,0,0,isPreviewOnly);
				
				//初始左边距默认值
				currLeft = left;
				
				//换行上边距追加
				top += rowSpacing;
				
				//打印剩下的文本
				addTextisPreviewOnly(arrText[1],isPreviewOnly);
				
			}else{//不换行的情况下
				//打印
				printLineOne(str,width,0,0,0,isPreviewOnly);
				
			}
    			
    	}
    	
    	//全角空格填充字符串为指定宽度 str字符串 width剩余宽度
    	function fullText(str,width){
    		
    		var cWidth = totalWidth - currLeft;
    		
    		//定义初始文本宽度
    		var subStringWidth = 0;
    		
    		//定义数值正则
    		var regNum = /^[0-9]+$/;
    		
    		//定义字母正则
    		var reg1   = /^[A-Za-z]+$/;
    		
    		//定义英文标点符号
			//var reg =/[-._ ,:%'\;`~!@#$^&*()+=|\\\[\]\{\}]/;
    		var reg =/[^\x00-\xff]/;
    		for(var i = 0; i < str.length; i ++){
    		
    			var c = str.charAt(i);
    			
    			//判断是否是英文数字或者空格 针对不同的类型追加宽度
				if(!reg.test(c)){
					subStringWidth += numWidth;
					cWidth = (cWidth-numWidth)== 0? rowWidth:(cWidth-numWidth);
				}else{
					
					if(cWidth < fontWidth){
						subStringWidth += numWidth;
						cWidth = rowWidth;
					}
					subStringWidth += fontWidth;
					cWidth = (cWidth-fontWidth)== 0? rowWidth:(cWidth-fontWidth);
				}
    		}
			
			var bool = false;
			
			//当字符串宽度不够时增加宽度
			while(subStringWidth <= width){
				str += "　";
				subStringWidth += fontWidth;
				bool = true;
			}
			
			var num1 = subStringWidth - width;
			if(bool && num1 >= 0){
				if(num1 > numWidth){
					str = str.substring(0,str.length-1);
				}else{
					str = str.substring(0,str.length-1)+" ";
				}
			}
    		return  str;
    	}
    	
    	
    	//计算文本居中应该移动的宽度
    	function textCenter(str, width ,fontW, numW ){
    		var reg = /^[ ]*|[ ]*$/g;
    		var reg1 = /^[　]*|[　]*$/g;
    		str = str.replace(reg,"");
    		str = str.replace(reg1,"");
			var w = getStrWidth(str, fontW, numW);
			width = width -w;
			
			if(width > 0){
				return width/2;
			}
			
			return 0;
    	}
    	
    	
    	//求字符串的宽度
    	function getStrWidth(str, fontW, numW){
    		//字符串默认宽度
    		var strWidth = 0;
    		
    			//定义数值正则
    		var regNum = /^[0-9]+$/;
    		
    		//定义字母正则
    		var reg1   = /^[A-Za-z]+$/;
			
			//定义英文标点符号
			//var reg =/[-._ ,:%'\;`~!@#$^&*()+=|\\\[\]\{\}]/;
			var reg =/[^\x00-\xff]/;
			for(var i = 0; i < str.length; i++){
			
				//获取对应下标的字符
				c = str.charAt(i);
				
				//判断是否是英文数字或者空格 针对不同的类型追加宽度
				if(!reg.test(c)){
					strWidth += numW;
				}else{
					strWidth += fontW;
				}
				
			}
    		return strWidth;
    	}
    	
    	//分行  width剩余宽度 str字符串
    	function subText(width,str){
    	
    		//定义分行的文本数组
    		var arr = new Array();
    		
    		//定义初始文本宽度
    		var subStringWidth = 0;
    		
    		//定义初始截取字符串
    		var subString      = "";
    		
    		//定义初始字符下标
    		var index  = 0;
    		
    		//定义数值正则
    		var regNum = /^[0-9]+$/;
    		
    		//定义字母正则
    		var reg1   = /^[A-Za-z]+$/;
			
			//定义英文标点符号
			//var reg =/[-._ ,:%'\;`~!@#$^&*()+=|\\\[\]\{\}]/;
			var reg =/[^\x00-\xff]/;
			//循环截取字符 直到宽度不够
    		while (subStringWidth <= width && str.length > index) 
			{ 
				//获取对应下标的字符
				c = str.charAt(index);
				
				//追加到截取字符串
				subString += c;
				
				//判断是否是英文数字或者空格 针对不同的类型追加宽度
				if(!reg.test(c)){
					subStringWidth += numWidth;
				}else{
					subStringWidth += fontWidth;
				}
				
				//截取下标往后挪
				index ++;
			} 
			//去掉截取字符超出的一位
			if(subStringWidth - width > reserveWidth){
				subString = subString.substring(0,subString.length-1);
			}
			
			//加入文本数组
			arr[0] = subString;
			
			//将剩余文本加入文本数组
			arr[1] = str.replace(subString,"");
			
			//如果有附件加一句话
			var reg=/^　　　　　+　　　　　$/;
			if(isFile && reg.test(arr[0])){
				arr[0] = "其他检查材料见附件" + arr[0].substring(9);
				isFile = false;
			}
			return arr;
    	}
    	
    	//打印时间
    	function printTime(year,month,day){
    		printLineOne(year,3*fontWidth,0,1,0,0);
    		addText("年");
    		printLineOne(month,2*fontWidth,0,1,0,0);
    		addText("月");
    		printLineOne(day,2*fontWidth,0,1,0,0);
    		addText("日");
    	}
    	
    	//换行
    	function wrap(){
    		if(currLeft != left){
				currLeft = left;
				top += rowSpacing;
			}
    	}
    	
    	//空行
    	function newLine(){
    		top += rowSpacing;
    	}
    	
    	//根据宽度调整字体大小和文本位置 str字符串 width 宽度
        function runSize(str,width){
            var fontSize   = [13,12,11,10,9,8,7,6];
            var topSpacing = [0,0,0,2.9,3,4,5,6];
            var fontWidth  = [18,17,16,14,13,12,10,8];
            var numWidth   = [9,8.5,8,7,6.5,6,5,4];
            var rowSpacing   = [28,26,22,18,16,12,8,4];
            var index = 0;
            while(true){
                var strWidth = getStrWidth(str,fontWidth[index],numWidth[index]);
                if(strWidth > width && index < 7)
                    index ++;
                else
                    break;
            }
            var arr = new Array();
            arr[0]  = fontSize[index];
            arr[1]  = topSpacing[index];
            arr[2]  = fontWidth[index];
            arr[3]  = numWidth[index];
            arr[4]  = rowSpacing[index];
            return arr ;
        }
    	
    	
    	//添加主标题
    	function addMainTitle(topSpacing, title, isPrint){
    		/***************文书主标题******************/
    		if(topSpacing > 0){
    			LODOP.ADD_PRINT_TEXT(topSpacing,left,612,40,title);
    		}else{
    			LODOP.ADD_PRINT_TEXT(130,left,612,40,title);
    		}
			LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
			LODOP.SET_PRINT_STYLEA(0,"FontName","宋体");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",20);
			LODOP.SET_PRINT_STYLEA(0,"Bold",1);
			if(isPrint == 0){
				LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
			}
			top = 260;
    	}
    	
    	function addSubTitle(topSpacing, title){
    		/***************文书副标题******************/
    		if(topSpacing > 0){
    			LODOP.ADD_PRINT_TEXT(topSpacing,left,612,40, title);
    		}else{
    			LODOP.ADD_PRINT_TEXT(180,left,612,40, title);
    		}
			LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
			LODOP.SET_PRINT_STYLEA(0,"FontName","仿宋");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
			top = 260;
    	}
    	
    	function addImg(topSpacing, leftSpacing, width, height, imgSrc){
    		/***************图章******************/
			LODOP.ADD_PRINT_IMAGE(topSpacing,leftSpacing,width,height,"<img  src='" + imgSrc + "'></img>");
			LODOP.SET_PRINT_STYLEA(0,"TransColor","#FFFFFF");
			LODOP.SET_PRINT_STYLEA(0,"Stretch",2);
            LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);  
    	}
    	
    	function addQRCode(src){
    		/***************文书二维码******************/
    		if(src == ""){
    			LODOP.ADD_PRINT_SHAPE(4, 30, 80,100,100,0,0,"#FFFFFF")
    		}else{
    			LODOP.ADD_PRINT_BARCODE(30, 80, "12.5%", "9%", "QRCode", src);
				LODOP.SET_PRINT_STYLEA(0,"QRCodeVersion",10);
				LODOP.SET_PRINT_STYLEA(0,"QRCodeErrorLevel","H");
    		}
			
    	}    
    	
    	function addBindingLine(){
    		/***************文书左侧内容(装订线)******************/
			LODOP.ADD_PRINT_LINE(220,43,382,43,3,0);
			LODOP.ADD_PRINT_TEXT(390,35,35,30,"装");
			LODOP.SET_PRINT_STYLEA(0,"FontName","仿宋");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",fontSize);
			LODOP.ADD_PRINT_LINE(410,43,532,43,3,0);
			LODOP.ADD_PRINT_TEXT(540,35,35,30,"订");
			LODOP.SET_PRINT_STYLEA(0,"FontName","仿宋");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",fontSize);
			LODOP.ADD_PRINT_LINE(560,43,682,43,3,0);
			LODOP.ADD_PRINT_TEXT(690,35,35,30,"线");
			LODOP.SET_PRINT_STYLEA(0,"FontName","仿宋");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",fontSize);
			LODOP.ADD_PRINT_LINE(710,43,872,43,3,0);
    	}	
    	
    	function addLeftBottomContent(lContent, bContent){
    	    /***************文书右侧内容(联名)******************/
    		var str = "";
    		for(var i=0 ; i<lContent.length; i++){
    			if(i!=lContent.length){
    				str +=lContent.charAt(i);
    				if(lContent.length > 7){
    					str += " ";
    				}else{
    					str +="　";
    				}
    				
    			}else{
    				str += lContent.charAt(i);
    			}
    		}
    		var topSpacing;
    		if(lContent.length > 7){
    			 topSpacing = (352-((lContent.length + (lContent.length-1))*12.6))/2;
    		}else{
    			 topSpacing = (352-((lContent.length + (lContent.length-1))*23.6))/2;
    		}
    		
    		topSpacing = topSpacing > 0 ? topSpacing:1;
    		if(lContent != "" && lContent != "　")
    		LODOP.ADD_PRINT_LINE(183,715,363,715,0,1);
			LODOP.ADD_PRINT_TEXT(363+topSpacing,706,30,352,str);
			LODOP.SET_PRINT_STYLEA(0,"FontName","仿宋");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",fontSize);
			if(lContent != ""　&& lContent != "　")
			LODOP.ADD_PRINT_LINE(726,715,906,715,0,1);
			
			/***************文书底部内容******************/
			LODOP.ADD_PRINT_TEXT(1050,80,650,30, bContent);
			LODOP.SET_PRINT_STYLEA(0,"FontName","仿宋");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",fontSize);
    	}
    	
    	function addLeftBottomContent1(top, left, lContent, bContent){
    	    /***************文书右侧内容(联名)******************/
    		var str = "";
    		for(var i=0 ; i<lContent.length; i++){
    			if(i!=lContent.length){
    				str +=lContent.charAt(i);
    				if(lContent.length > 7){
    					str += " ";
    				}else{
    					str +="　";
    				}
    				
    			}else{
    				str += lContent.charAt(i);
    			}
    		}
    		var topSpacing;
    		if(lContent.length > 7){
    			 topSpacing = (352-((lContent.length + (lContent.length-1))*12.6))/2;
    		}else{
    			 topSpacing = (352-((lContent.length + (lContent.length-1))*23.6))/2;
    		}
    		
    		topSpacing = topSpacing > 0 ? topSpacing:1;
    		if(lContent != "" && lContent != "　")
    		LODOP.ADD_PRINT_LINE(183,715,363,715,0,1);
			LODOP.ADD_PRINT_TEXT(363+topSpacing,706,30,352,str);
			LODOP.SET_PRINT_STYLEA(0,"FontName","仿宋");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",fontSize);
			if(lContent != ""　&& lContent != "　")
			LODOP.ADD_PRINT_LINE(726,715,906,715,0,1);
			
			/***************文书底部内容******************/
			LODOP.ADD_PRINT_TEXT(top,left,650,30, bContent);
			LODOP.SET_PRINT_STYLEA(0,"FontName","仿宋");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",fontSize);
    	}
    	function addLeftBottomContent2(lContent, bContent){
    	    /***************文书右侧内容(联名)******************/
    		var str = "";
    		for(var i=0 ; i<lContent.length; i++){
    			if(i!=lContent.length){
    				str +=lContent.charAt(i);
    				if(lContent.length > 7){
    					str += " ";
    				}else{
    					str +="　";
    				}
    				
    			}else{
    				str += lContent.charAt(i);
    			}
    		}
    		var topSpacing;
    		if(lContent.length > 7){
    			 topSpacing = (352-((lContent.length + (lContent.length-1))*12.6))/2;
    		}else{
    			 topSpacing = (352-((lContent.length + (lContent.length-1))*23.6))/2;
    		}
    		
    		topSpacing = topSpacing > 0 ? topSpacing:1;
    		if(lContent != "" && lContent != "　")
    		LODOP.ADD_PRINT_LINE(183,715,363,715,0,1);
			LODOP.ADD_PRINT_TEXT(363+topSpacing,706,30,352,str);
			LODOP.SET_PRINT_STYLEA(0,"FontName","仿宋");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",fontSize);
			if(lContent != ""　&& lContent != "　")
			LODOP.ADD_PRINT_LINE(726,715,906,715,0,1);
			
			/***************文书底部内容******************/
			if (bContent.length>35) {
				LODOP.ADD_PRINT_TEXT(1020,80,650,30, bContent);
			}else {
				LODOP.ADD_PRINT_TEXT(1050,80,650,30, bContent);
			}
			LODOP.SET_PRINT_STYLEA(0,"FontName","仿宋");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",fontSize);
    	}
    	function addPage(){
    		addText("注：本页不够，可以另附页。");
    		currLeft = 520;
    		addText("共");
    		printLineOne(pageCount,2*fontWidth,0,1,0,0);
    		addText("页　第");
    		printLineOne(currPage,2*fontWidth,0,1,0,0);
    		addText("页");
    	}
    	
    	
    	function attriDefault(){
    		totalHeight  = 1058; 
			totalWidth   = 687;	  
			left         = 75; 
			top          = 220;   
			currLeft     = 75; 
			fontSize     = 13;   
			fontWidth    = 18;  
			numWidth     = 9;  
			rowSpacing   = 30;   
			reserveWidth = 0;  
			isFile       = false;
    	}
    	
    	
    	function getPageCount(textCount,rows,str){
    		var w1 = (textCount*fontWidth)%rowWidth;
    		var rs = Math.floor((textCount*fontWidth)/rowWidth);
    		
    		var texts = subText(w1,str);
    		for(var i = 0; i < rs; i++){
    			var arr1= subText(rowWidth,texts[1]);
    			texts[0] +=arr1[0];
    			texts[1] = arr1[1];
    		}
    		arrText.push(texts[0]);
    		while(texts[1].length != 0){
    			pageCount ++;
    			texts[0] = "";
    			for(var i = 0; i < rows; i++){
    				var arr1= subText(rowWidth,texts[1]);
    				texts[0] +=arr1[0];
    				texts[1] = arr1[1];
    			}
    			//texts = subText(twoWidth,texts[1]);
    			arrText.push(texts[0]);
    		}
    	}
    	
    	
    	function addSeal(text){
    		if(text&&text.length>0){
    			LODOP.ADD_PRINT_TEXT(top,450,290,30,text);
    		}else{
    			LODOP.ADD_PRINT_TEXT(top,420,232,30,"安全生产监督管理局(印章)");
    		}
    		LODOP.SET_PRINT_STYLEA(0,"FontName","仿宋");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",fontSize);
			//LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
			currLeft += 144;
    	}
    	
    	//添加头部内容
    	function addHeadContent(){
    		LODOP.ADD_PRINT_TEXT(80,left,612,40,"安全生产行政执法文书");
    		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
			LODOP.SET_PRINT_STYLEA(0,"FontName","宋体");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",20);
			LODOP.SET_PRINT_STYLEA(0,"Bold",1);
			
		//	LODOP.ADD_PRINT_RECT(65, 75, 687, "1mm",0, 1);
			LODOP.ADD_PRINT_LINE(115,75,115, 687,0,1);
			LODOP.ADD_PRINT_LINE(118,75,118, 687,0,1);
			
    	}
    	//添加底部内容
    	function addFootContent(content, isPage){
    		LODOP.ADD_PRINT_LINE(1010,75,1010, 687,0, 2);
    		top = 1015;
    		addText(content);
    		if(currLeft > 520){
    			wrap();
    		}
    		currLeft = 520;
    		if(isPage == 1){
    			addText("共");
    			printLineOne(pageCount,2*fontWidth,0,1,0,0);
    			addText("页　第");
    			printLineOne(currPage,2*fontWidth,0,1,0,0);
    			addText("页");
    		}
    	}
    	//添加底部内容2
    	function addFootContent2(content, isPage){
    		LODOP.ADD_PRINT_LINE(1010,55,1010, 707,0, 2);
    		top = 1015;
    		addText(content);
    		if(currLeft > 520){
    			wrap();
    		}
    		currLeft = 520;
    		if(isPage == 1){
    			addText("共");
    			printLineOne(pageCount,2*fontWidth,0,1,0,0);
    			addText("页　第");
    			printLineOne(currPage,2*fontWidth,0,1,0,0);
    			addText("页");
    		}
    	} 
    	//添加附页头部样式
    	function addHeadStyle(){
    		LODOP.ADD_PRINT_TEXT(70,650,50,30,"续页");
    		LODOP.SET_PRINT_STYLEA(0,"FontName","仿宋");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",fontSize);
    		LODOP.ADD_PRINT_LINE(100,75,100, 687,0, 2);
    	}

        /**
         * text文本大小适配以及多余内容省略……
         * @param str
         * @param leftLine
         * @param rightLine
         * @param downLine
         */
        function autoAddTopText(str,leftLine,rightLine,downLine){
            if(str == null || str == undefined)
                str = "";
            str = str.toString();

            //求出字符串所占宽度
            var width = getStrWidth(str, fontWidth, numWidth);
            //得到当前行剩余宽度
            var currWidth = rightLine-leftLine;
            //判断是否要换行
            if(currWidth < width){
                //对文本进行分行
                var arrText = subText(currWidth, str);
                //打印
                printLineOne(arrText[0],currWidth,0,0,0,0);

                //初始左边距默认值
                currLeft = leftLine;
                var arr = runSize(str,width);
                var addTop = arr[4];
                //换行上边距追加
                top += addTop;
                var leftSpacing = downLine-top;
                //求出剩余字符串所占宽度
                var leftWidth = getStrWidth(arrText[1], fontWidth, numWidth);
                //打印剩下的文本
                if(leftSpacing>=2*addTop){
                    autoAddTopText(arrText[1],leftLine,rightLine,downLine);
                }else if(leftSpacing>=addTop&&currWidth<leftWidth){
                   var lastText = subText(currWidth, arrText[1]);
                   lastText[0]=lastText[0].substring(0,lastText[0].length-3);
                   lastText[0]+="……";
                   autoAddTopText(lastText[0],leftLine,rightLine,downLine);
                }else if(leftSpacing>=addTop&&currWidth>leftWidth){
                    autoAddTopText(arrText[1],leftLine,rightLine,downLine);
                }
            }else{//不换行的情况下
                //打印
                printLineOne(str,width,0,0,0,0);

            }
        }
		function getDate(date){
			var dateArray= new Array();
			if(typeof(date)!="undefined"){
			//截取年
			var year=date.substr(0,4);	
			//截取月
			var index1=date.indexOf("-"); 
			var index2=date.lastIndexOf("-"); 
			var cha=parseInt(index2)-(parseInt(index1)+1); 
			var month=date.substr((parseInt(index1)+1),cha); 
			//截取日
			var kg=date.indexOf(" "); 
			cha=parseInt(kg)-parseInt(index2); 
			var day=date.substr(parseInt(index2)+1,2);
			dateArray.push(year);
			dateArray.push(month);
			dateArray.push(day);
			}
			return dateArray;
		}