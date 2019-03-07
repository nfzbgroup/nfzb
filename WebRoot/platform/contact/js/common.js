/**
 * 清除checkbox的选中状态
 */
function clearCheckboxes(cbs) {
	try {
		if(typeof(cbs.length) != 'undefined') {
			for(var i = 0; i < cbs.length; i ++) {
				cbs[i].checked = false;
			}
		} else {
			cbs.checked = false;
		}
	} catch(ex) {
		alert(ex);
	}
}

function openAllscreenwin(winURL)
{
	var winSet = "height="+screen.availHeight+",width="+screen.availWidth+",top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no"
	
	var Allscreenwin = window.open(winURL,"",winSet);
	Allscreenwin.resizeTo(screen.availWidth, screen.availHeight);
}

function openWin(winURL)
{
	var newWin = window.open(winURL);

}
function openWindowInCenter(winURL,height,width)
{
	var top = (screen.availHeight - height)/2;
	var left = (screen.availWidth - width)/2;
	var InCenterwin = window.open(winURL,"","height=" + height + ",width=" + width + ",top=" + top + ",left=" + left + ",toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no");

}

function openwinNoScreen(winURL)
{
	var NoScreen = window.open(winURL,"","height=200,width=350,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no");	
}
//函数名：fucCheckNUM
//功能介绍：检查是否为数字
//参数说明：要检查的数字
//返回值：1为是数字，0为不是数字
function fucCheckNUM(NUM)
{
 var i,j,strTemp;
 strTemp="0123456789";
 if ( NUM.length== 0)
  return 0
 for (i=0;i<NUM.length;i++)
 {
  j=strTemp.indexOf(NUM.charAt(i)); 
  if (j==-1)
  {
   return 0;
  }
 }
 return 1;
}

//避免页面出现科学计数法

// 编辑页面替换查询并替换全部显示的input输入框显示内容
function init(){
	var objFloat = document.getElementsByTagName("input");
	for(var i=0;i<objFloat.length;i++){			
			var objValue = objFloat[i].value;
			if (objValue.indexOf('.0E-4') > -1 ){			
				var subValue=parseFloat(objValue.substring(0,objValue.indexOf('.0E-4')),10);					
				objFloat[i].value = accMul(subValue,0.0001);
			}		
	}	
}

// 列表显示页面查询并替换全部显示内容

function initSingleTable(){
	var objTable = document.getElementById("dataTable");	
	var objTr = objTable.rows;
	
	for( var i = 0 ; i < objTr.length && objTr.length > 0 ; i++){
		var objTd = objTr[i].cells;
		for( var j = 0 ; j < objTd.length && objTd.length > 0 ; j++){
			var tdValue = objTd[j].innerHTML;
			if (tdValue.indexOf('.0E-4') > -1){
				var newValue = parseFloat(tdValue.substring(0,tdValue.indexOf('.0E-4')),10);					
				objTd[j].innerHTML = accMul(newValue,0.0001);
			}
		}
	}	
}
// 页面多个table数据区替换
function initFloatData(){
	var objTable,objTr,objTd;	
	objTable = document.all("dataTable");		
    if (objTable != null) {
		if(objTable.length == undefined){			
		// 只有一张数据表
			objTr = objTable.rows;	
			for( var i = 0 ; i < objTr.length && objTr.length > 0 ; i++){
				objTd = objTr[i].cells;
				for( var j = 0 ; j < objTd.length && objTd.length > 0 ; j++){
					var tdValue = objTd[j].innerHTML;
					if (tdValue.indexOf('.0E-4') > -1){
						var newValue = parseFloat(tdValue.substring(0,tdValue.indexOf('.0E-4')),10);					
						objTd[j].innerHTML = accMul(newValue,0.0001);
					}
				}
			}	
			
		}else{
		// 多数据表		
			for(var k = 0; k <objTable.length ; k++){
				objTr = objTable[k].rows;				
				for( var i = 0 ; i < objTr.length && objTr.length > 0 ; i++){
					objTd = objTr[i].cells;
					for( var j = 0 ; j < objTd.length && objTd.length > 0 ; j++){
						var tdValue = objTd[j].innerHTML;
						if (tdValue.indexOf('.0E-4') > -1){
							var newValue = parseFloat(tdValue.substring(0,tdValue.indexOf('.0E-4')),10);					
							objTd[j].innerHTML = accMul(newValue,0.0001);
						}
					}
				}
			}		
		}    
	}
}	


//乘法函数，用来得到精确的乘法结果
//说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
//调用：accMul(arg1,arg2)
//返回值：arg1乘以arg2的精确结果
function accMul(arg1,arg2)
{
	if(!isNaN(arg1) && !isNaN(arg2)){
		arg1=String(arg1);var i=arg1.length-arg1.indexOf(".")-1;i=(i>=arg1.length)?0:i
		arg2=String(arg2);var j=arg2.length-arg2.indexOf(".")-1;j=(j>=arg2.length)?0:j
		return (arg1.replace(".","")*arg2.replace(".","")/Math.pow(10,i+j)).toFixed(4)
	}else return 0
}

//加法函数，用来得到精确的加法结果
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
//调用：accAdd(arg1,arg2)
//返回值：arg1加上arg2的精确结果
function accAdd(arg1,arg2,num){
	if(accAdd.arguments.length < 3) num = 4;
	if(!isNaN(arg1) && !isNaN(arg2)){
		var r1,r2,m;
		try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
		try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
		m=Math.pow(10,Math.max(r1,r2))
		return ((arg1*m+arg2*m)/m) .toFixed(num)
	}else return 0
}

//减法函数，用来得到精确的加法结果
//说明：javascript的减法结果会有误差，在两个浮点数相减的时候会比较明显。这个函数返回较为精确的减法结果。
//调用：accSub(arg1,arg2)
//返回值：arg1加上arg2的精确结果
function accSub(arg1,arg2){
	if(!isNaN(arg1) && !isNaN(arg2)){
		var r1,r2,m;
		try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
		try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
		m=Math.pow(10,Math.max(r1,r2))
		return ((arg1*m-arg2*m)/m).toFixed(4)
	}else return 0
}

//除法函数，用来得到精确的除法结果
//说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
//调用：accDiv(arg1,arg2)
//返回值：arg1除以arg2的精确结果
function accDiv(arg1,arg2){
	if(!isNaN(arg1) && !isNaN(arg2)){
		var t1=0,t2=0,r1,r2;
		try{t1=arg1.toString().split(".")[1].length}catch(e){}
		try{t2=arg2.toString().split(".")[1].length}catch(e){}
		with(Math){
			r1=Number(arg1.toString().replace(".",""))
			r2=Number(arg2.toString().replace(".",""))
			return ((r1/r2)*pow(10,t2-t1)).toFixed(4);
		}
	}else return 0;
}


/** tab hidden or show*/
function initTable (){
		for(var i=0;i<6;i++){
			var imgObj = eval("img"+i);
			var trObj  = eval("tr"+i);
			if(trObj.style.display == 'none'){
				imgObj.src = "../image/item_down.gif";
				imgObj.alt = "点击显示详细信息";
			} else{
				imgObj.src = "../image/item_plus.gif";
				imgObj.alt = "点击隐藏详细信息";
			}
		
		}	
	}
	
function swapTab(obj){
		var trObj = eval("tr"+obj);	
		var imgObj = eval("img"+obj);		
		if (trObj.style.display == 'none'){
			trObj.style.display = 'block';	
			imgObj.src = "../image/item_plus.gif";	
			imgObj.alt = "点击隐藏详细信息";		
		
		}else{
			trObj.style.display = 'none';
			imgObj.src = "../image/item_down.gif";
			imgObj.alt = "点击显示详细信息";	
		} 			
}


function viewHistoryApprove(){
		var tableObj = document.all("tableTVewHistory");	
		var tabObj = document.all("tabViewHistory");
		if (tableObj.style.display == 'none'){
			tableObj.style.display = 'block';	
			tabObj.value = "点击隐藏信息";	
			tabObj.alt = "点击隐藏信息";		
		
		}else{
			tableObj.style.display = 'none';
			tabObj.value =  "点击显示信息";	
			tabObj.alt = "点击隐藏信息";
		} 			
}

function flowTypeSel(flowType){
	var tableObj_0 = eval("table_0");	
	var tableObj_1 = eval("table_1");
	var tagObj_0 = eval("tag_0");	
	var tagObj_1 = eval("tag_1");

	if(flowType == '0'){
		tableObj_0.style.display = 'block';	
		tableObj_1.style.display = 'none';	
		tagObj_0.className = "yjzx_tag_1";
		tagObj_1.className = "yjzx_tag_2";
	}

	if(flowType == '1'){
		tableObj_0.style.display = 'none';	
		tableObj_1.style.display = 'block';	
		tagObj_0.className = "yjzx_tag_2";
		tagObj_1.className = "yjzx_tag_1";
	}

}

//将录音文件路径名转换为HTTP访问方式的URL
function getRecordFileUrl(filePath) {
	//return filePath;
	
	//var filePath = "d:\\audio\\20080422\\12345678.wav";	
	var str1 = filePath.replace(/\\/g,"/");
	var sIndex = str1.indexOf("/",3);
	var str2 = str1.substr(sIndex);
	return str2;
}
 
