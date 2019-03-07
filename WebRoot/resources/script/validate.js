/* FUNCTION---------------------------------------------trim
功能：
	去除字符串两端的空格
参数：
	String	str 字符串
返回：
	String	去除两端空格的字符串
*/
function trim(str)
{
	var left = 0;
	var right = -1;
	for(i=0;i<str.length;i++)
		if(str.charAt(i)!=" ")
		{
			left = i;
			break;
		}
	for(i=str.length-1;i>=0;i--)
		if(str.charAt(i)!=" ")
		{
			right = i+1;
			break;
		}
	return str.substring(left,right);
}

/* FUNCTION---------------------------------------------isNotBlank
功能：
	判断输入值是否不为空
参数：
	Object	obj 标签对象
	boolean	是否先去除字符串两端的空格
返回：
	boolean	输入值是否不为空
*/
function isNotBlank(obj,isSpaceFilter)
{
	if(isSpaceFilter)
		obj.value = trim(obj.value);
	return obj.value!=""
}

/* FUNCTION---------------------------------------------isNotBlankEx
功能：
	判断输入值是否不为空（去除字符串两端的空格）
参数：
	Object	obj 标签对象
返回：
	boolean	输入值是否不为空
*/
function isNotBlankEx(obj)
{
	return isNotBlank(obj,true);
}

/* FUNCTION---------------------------------------------isNumber
功能：
	判断输入值是否为数值
参数：
	Object	obj 标签对象
返回：
	boolean	输入值是否为数值
*/
function isNumber(obj)
{
	obj.value = trim(obj.value);
	return !isNaN(obj.value)
}

/* FUNCTION---------------------------------------------isInteger
功能：
	判断输入值是否为整数
参数：
	Object	obj 标签对象
返回：
	boolean	输入值是否为整数
*/
function isInteger(obj)
{
	if(!isNumber(obj))
		return false;
	if(parseInt(obj.value,10)==parseFloat(obj.value))
		return true;
	else
		return false;
}

/* FUNCTION---------------------------------------------isBetween
功能：
	判断输入值是否在一区间内
参数：
	Object	obj 标签对象
	float	nMin 左边界取值
	boolean	isContainMin 是否左边界为闭区间
	float	nMax 右边界取值
	boolean isContainMax 是否右边界为闭区间
返回：
	boolean	输入值是否在一区间内
*/
function isBetween(obj,nMin,isContainMin,nMax,isContainMax)
{
	if(!isNumber(obj))
		return false;
	var result = parseFloat(obj.value);
	var condition1 = (result>nMin && !isContainMin) || (result>=nMin && isContainMin);
	var condition2 = (result<nMax && !isContainMax) || (result<=nMax && isContainMax);
	if(condition1 && condition2)
		return true;
	else
		return false;
}

/* FUNCTION---------------------------------------------isBetweenEx
功能：
	判断输入值是否在一区间内（左边界为闭区间，右边界为闭区间）
参数：
	Object	obj 标签对象
	float	nMin 左边界取值
	float	nMax 右边界取值
返回：
	boolean	输入值是否在一区间内
*/
function isBetweenEx(obj,nMin,nMax)
{
	return isBetween(obj,nMin,true,nMax,true);
}

/* FUNCTION---------------------------------------------isBelow
功能：
	判断输入值是否小于某一数值
参数：
	Object	obj 标签对象
	float	nMax 右边界取值
	boolean isContainMax 是否右边界为闭区间
返回：
	boolean	输入值是否小于某一数值
*/
function isBelow(obj,nMax,isContainMax)
{
	if(!isNumber(obj))
		return false;
	var result = parseFloat(obj.value);
	if(result<nMax && !isContainMax || result<=nMax && isContainMax)
		return true;
	else
		return false;
}

/* FUNCTION---------------------------------------------isBelowEx
功能：
	判断输入值是否小于某一数值（右边界为闭区间）
参数：
	Object	obj 标签对象
	float	nMax 右边界取值
	boolean isContainMax 是否右边界为闭区间
返回：
	boolean	输入值是否小于某一数值
*/
function isBelowEx(obj,nMax)
{
	return isBelow(obj,nMax,true);
}

/* FUNCTION---------------------------------------------isAbove
功能：
	判断输入值是否大于某一数值
参数：
	Object	obj 标签对象
	float	nMin 左边界取值
	boolean isContainMin 是否左边界为闭区间
返回：
	boolean	输入值是否小于某一数值
*/
function isAbove(obj,nMin,isContainMin)
{
	if(!isNumber(obj))
		return false;
	var result = parseFloat(obj.value);
	if(result>nMin && !isContainMin || result>=nMin && isContainMin)
		return true;
	else
		return false;
}

/* FUNCTION---------------------------------------------isAboveEx
功能：
	判断输入值是否大于某一数值（左边界为闭区间）
参数：
	Object	obj 标签对象
	float	nMin 左边界取值
	boolean isContainMin 是否左边界为闭区间
返回：
	boolean	输入值是否小于某一数值
*/
function isAboveEx(obj,nMin)
{
	return isAbove(obj,nMin,true);
}

/* FUNCTION---------------------------------------------isPositiveNumber
功能：
	判断输入值是否为正数
参数：
	Object	obj 标签对象
	boolean isContainZero 是否允许零为合法
返回：
	boolean	输入值是否为正数
*/
function isPositiveNumber(obj,isContainZero)
{
	return isAbove(obj,0,isContainZero);
}

/* FUNCTION---------------------------------------------isPositiveNumberEx
功能：
	判断输入值是否为正数（允许零为合法）
参数：
	Object	obj 标签对象
返回：
	boolean	输入值是否为正数
*/
function isPositiveNumberEx(obj)
{
	return isPositiveNumber(obj,true);
}

/* FUNCTION---------------------------------------------isValidString
功能：
	判断输入值是否为指定的合法字符串
参数：
	Object	obj 标签对象
	String	species 合法字符集
	boolean isSpaceFilter 是否先去除字符串两端的空格
返回：
	boolean	输入值是否为指定的合法字符串
*/
var VS_LETTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
var VS_NUMBER = "0123456789";
var VS_BASIC = VS_LETTER + VS_NUMBER;
var VS_COMMON = "@$()/.- _" + VS_BASIC;
function isValidString(obj,species,isSpaceFilter)
{
	if(isSpaceFilter)
		obj.value = trim(obj.value);
	for(i=0;i<obj.value.length;i++)
		if(species.indexOf(obj.value.charAt(i))<0)
			return false;
	return true;
}

/* FUNCTION---------------------------------------------isValidStringEx
功能：
	判断输入值是否为指定的合法字符串（以VS_COMMON为合法字符集，先去除字符串两端的空格）
参数：
	Object	obj 标签对象
返回：
	boolean	输入值是否为指定的合法字符串
*/
function isValidStringEx(obj)
{
	return isValidString(obj,VS_BASIC,true);
}

/* FUNCTION---------------------------------------------isValidChineseString
功能：
	判断输入值是否为指定的合法的中文字符串
参数：
	Object	obj 标签对象
	String	species 其它合法字符集
	boolean isSpaceFilter 是否先去除字符串两端的空格
返回：
	boolean	输入值是否为指定的合法中文字符串
*/
function isValidChineseString(obj,species,isSpaceFilter)
{
	if(isSpaceFilter)
		obj.value = trim(obj.value);
	for(i=0;i<obj.value.length;i++)
		if(species.indexOf(obj.value.charAt(i))<0 && obj.value.charCodeAt(i)<19968)
			return false;
	return true;
}

/* FUNCTION---------------------------------------------isValidChineseStringEx
功能：
	判断输入值是否为指定的合法的中文字符串（以VS_COMMON为其它合法字符集，先去除字符串两端的空格）
参数：
	Object	obj 标签对象
返回：
	boolean	输入值是否为指定的合法中文字符串
*/
function isValidChineseStringEx(obj)
{
	return isValidChineseString(obj,VS_BASIC,true);
}

/* FUNCTION---------------------------------------------isValidExtendedString
功能：
	判断输入值是否为指定的合法的扩展（含中文）字符串
参数：
	Object	obj 标签对象
	String	species 其它合法字符集
	boolean isSpaceFilter 是否先去除字符串两端的空格
返回：
	boolean	输入值是否为指定的合法扩展（含中文）字符串
*/
function isValidExtendedString(obj,species,isSpaceFilter)
{
	if(isSpaceFilter)
		obj.value = trim(obj.value);
	for(i=0;i<obj.value.length;i++)
		if(species.indexOf(obj.value.charAt(i))<0 && obj.value.charCodeAt(i)<128)
			return false;
	return true;
}

/* FUNCTION---------------------------------------------isValidExtendedStringEx
功能：
	判断输入值是否为指定的合法的扩展（含中文）字符串（以VS_COMMON为其它合法字符集，先去除字符串两端的空格）
参数：
	Object	obj 标签对象
返回：
	boolean	输入值是否为指定的合法扩展（含中文）字符串
*/
function isValidExtendedStringEx(obj)
{
	return isValidExtendedString(obj,VS_COMMON,true);
}

/* FUNCTION---------------------------------------------isStandardDate
功能：
	判断输入值是否为合法的日期类型（格式为yyyy?mm?dd其中年份必须为[1000,9999]）
参数：
	Object	obj 标签对象
	String	conjunction 为年月日的连接符（长度为1）
返回：
	boolean	输入值是否为合法的日期类型
*/
function isStandardDate(obj,str) {

	var sDate = trim(obj.value);
	if(sDate==null || sDate=="")
		return true;
	var iYear, iMonth, iDay, iIndex

	var	reg
	reg = new RegExp('[^0-9-]','')
	if (sDate.search(reg) >= 0)
		return false;
	
	iIndex = sDate.indexOf('-');
	if ( iIndex == -1 )
		return false;
	else {
		iYear = parseFloat(sDate.substr(0, iIndex));
		if ( isNaN(iYear) || iYear < 1900 || iYear > 2099 )
			return false;
		else
			sDate = sDate.substring(iIndex + 1, sDate.length);
	}
	
	iIndex = sDate.indexOf('-');
	if ( iIndex == -1 )
		return false;
	else {
		iMonth = parseFloat(sDate.substr(0, iIndex));
		if ( isNaN(iMonth) || iMonth < 1 || iMonth > 12 )
			return false;
		else
			sDate = sDate.substring(iIndex + 1, sDate.length);
	}
	
	iIndex = sDate.indexOf('-');
	if ( iIndex >= 0 )
		return false;
	else {
		iDay = parseFloat(sDate);
		if ( isNaN(iDay) || iDay < 1 || iDay > 31 )
			return false;
	}
	
	
	switch(iMonth) {
		case 4:
		case 6:
		case 9:
		case 11:
			if ( iDay > 30 )
				return false;
			else
				break;
		case 2:
			if ( ( ( iYear % 4 == 0 && iYear % 100 != 0 ) || iYear % 400 == 0 ) && iDay > 29 )
				return false;
			else if ( (iYear % 4 != 0 || (iYear % 100 == 0 && iYear % 400 != 0)) && iDay > 28 )
				return false;
			else
				break;
		default:
	}
	return true;
}

/* FUNCTION---------------------------------------------isStandardDateEx
功能：
	判断输入值是否为合法的日期类型（格式为yyyy?mm?dd其中年份必须为[1000,9999]，年月日连接符为“/”）
参数：
	Object	obj 标签对象
返回：
	boolean	输入值是否为合法的日期类型
*/
function isStandardDateEx(obj)
{
	return isStandardDate(obj,"/");
}

/* FUNCTION---------------------------------------------isLengthBetween
功能：
	判断输入值长度是否在一闭区间内
参数：
	Object	obj 标签对象
	int		nMin 长度左边界
	int		nMax 长度右边界
返回：
	boolean	输入值长度是否在一闭区间内
*/
function isLengthBetween(obj,nMin,nMax)
{
	obj.value = trim(obj.value);
	if(obj.value.length>=nMin && obj.value.length<=nMax)
		return true;
	else
		return false;
}

/* FUNCTION---------------------------------------------isLengthBelow
功能：
	判断输入值长度是否小于某一值
参数：
	Object	obj 标签对象
	int		nMax 长度右边界
返回：
	boolean	输入值长度是否小于某一值
*/
function isLengthBelow(obj,nMax)
{
	return isLengthBetween(obj,0,nMax);
}

/* FUNCTION---------------------------------------------isLengthEqual
功能：
	判断输入值长度是否为某一值
参数：
	Object	obj 标签对象
	int		n 字符串必须长度
返回：
	boolean	输入值长度是否为某一值
*/
function isLengthEqual(obj,n)
{
	return isLengthBetween(obj,n,n);
}

/* FUNCTION---------------------------------------------isNotContain
功能：
	判断输入值是否不含有某些字符
参数：
	Object	obj 标签对象
	String	species 不合法字符集
	boolean isSpaceFilter 是否先去除字符串两端的空格
返回：
	boolean	输入值是否不含有某些字符
*/
var NC_BASIC = "<>";
var NC_COMMON = NC_BASIC+"\"\'";
function isNotContain(obj,species,isSpaceFilter)
{
	if(isSpaceFilter)
		obj.value = trim(obj.value);
	for(i=0;i<species.length;i++)
		if(obj.value.indexOf(species.charAt(i))>=0)
			return false;
	return true;
}

/* FUNCTION---------------------------------------------isNotContain
功能：
	判断输入值是否不含有某些字符（以NC_COMMON为不合法字符集，先去除字符串两端的空格）
参数：
	Object	obj 标签对象
返回：
	boolean	输入值是否不含有某些字符
*/
function isNotcontainEx(obj)
{
	return isNotContain(obj,NC_COMMON,true);
}

/* FUNCTION---------------------------------------------isContain
功能：
	判断输入值是否含有某些字符（不去除字符串两端的空格）
参数：
	Object	obj 标签对象
	String	species 必须含有的字符集
返回：
	boolean	输入值是否含有某些字符
*/
function isContain(obj,species)
{
	for(i=0;i<species.length;i++)
		if(obj.value.indexOf(species.charAt(i))<0)
			return false;
	return true;
}

/* FUNCTION---------------------------------------------hideSelectLayer
功能：
	如果有需要隐藏而未隐藏的层时，将该层隐藏。
	（目前用于按页面任意处关闭日历窗口。）
参数：
	无
返回：
	无
*/
function hideSelectLayer(){
try{
	//Layer_item2.style.visibility='hidden';
	//Layer_base.style.visibility='hidden';
	BaseLayer1.style.visibility='hidden';
	daysOfMonth.style.visibility='hidden';
	daysOfMonth2.style.visibility='hidden';
}catch(e){
 //do nothing;
}
}

/* FUNCTION---------------------------------------------submitConfirm
功能：
	在确认操作前弹出提示信息，以确认是否确定要执行操作。
参数：
	String msg 提示信息
	String url 所要联结的地方或要递交的form名称
	int urlType 0:url   1:form name	2:window.open url
返回：无
*/
function submitConfirm(msg,url,urlType){
	var agree;
	agree = confirm(msg);
	if(agree == true && urlType==0) //选择确定
		window.location = url;
	if(agree == true && urlType==1) //选择确定
		eval(url).submit();
	if(agree == true && urlType==2) //选择确定
		window.open(url);
}

/* FUNCTION---------------------------------------------openChildWindow
功能：
	弹出子窗口。
参数：
	String windowLocation  子窗口地址
	String windowName 子窗口名称，如为空时则使用默认窗口名
	String windowStyle 子窗口样式，如为空时则使用默认样式

返回：
	无
*/
function openChildWindow(windowLocation,windowName,windowStyle){
    var childwin
    if(!windowName){
    	windowName = 'smallWindow1';
    }
    if(!windowStyle){
    	windowStyle = 'width=660 height=500 top=50 left=100 scrollbars resizable=yes';
    }
    childwin = open(windowLocation,windowName,windowStyle)
    childwin.focus();
}


/* FUNCTION---------------------------------------------changeNumber
功能：
	修改数量（用于修改份数。）
参数：
	String inputName  输入框名称
	String step 改变数量

返回：
	无
*/
function changeNumber(inputName,step){
	if(inputName.value!=""){
		if(parseInt(inputName.value)+parseInt(step)>=0){
			inputName.value=parseInt(inputName.value)+parseInt(step);
		}else{
			inputName.value="0";
		}
	}else{
		inputName.value="0";
	}
}

/* FUNCTION---------------------------------------------preview
功能：打印预览
参数：无
返回：无
备注：打印页中所有想隐藏的对象都起名为"btprint",
  并添加<OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 id=WB width=0></OBJECT>
*/
function preview(bts){
//	var WB;
//	WB = new ActiveXObject("Shell.Explorer.1");
	if(navigator.appVersion.indexOf("MSIE 5.0")<0){
	    var i=0;
		var btArr = bts.split("$");
		var obj;
		for(i;i<btArr.length;i++){
			obj = eval("document.all." + btArr[i]);
			obj.style.display = "none";
		}
		WB.ExecWB(7,1);
		window.location.reload();
	}else{
		alert("IE 5.5以下版本浏览器不支持打印预览功能。\n建议升级浏览器版本。")
	}
}

/* FUNCTION---------------------------------------------print
功能：打印
参数：无
返回：无
备注：打印页中所有想隐藏的对象都起名为"btprint"
  并添加<OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 id=WB width=0></OBJECT>
*/
function print(bts){
	var i=0;
	var btArr = bts.split("$");
	var obj;
	for(i;i<btArr.length;i++){
		obj = eval("document.all." + btArr[i]);
		obj.style.display = "none";
	}
    WB.ExecWB(6,2);
    window.location.reload();
}
/* FUNCTION---------------------------------------------print
功能：打印(直接打印)
参数：无
返回：无
备注：打印页中所有想隐藏的对象都起名为"btprint"
  并添加<OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 id=WB width=0></OBJECT>
*/
function printDirect(bts){
	var i=0;
	var btArr = bts.split("$");
	var obj;
	for(i;i<btArr.length;i++){
		obj = eval("document.all." + btArr[i]);
		obj.style.display = "none";
	}
    WB.ExecWB(6,2);
}

/* FUNCTION---------------------------------------------changesel
功能：简单查询输入匡选择
参数：fname form名称
	  flag   1:晴空value 0:不清空value
返回：无
备注：select contField的顺序和contValue顺序一直
*/
function changesel(fname,flag){
	var i=0;
	theform = eval(fname);
	for(i;i < theform.condValue.length;i++){
	 	theform.condValue[i].style.display = "none";
	 	theform.condValue[i].disabled = true;
	}
		theform.condValue[theform.condField.selectedIndex].style.display = "";
		theform.condValue[theform.condField.selectedIndex].disabled = false;
	if(flag==1)
		theform.condValue[theform.condField.selectedIndex].value = "";
}
/* FUNCTION---------------------------------------------addToPwindow
功能：字窗口form内容复制到夫窗口form
参数：	pinput: 父窗口输入框name: form name.input name
		phinput：父窗口hidden输入框name: form name.input name
 		sinput: 子窗口输入框name: form name.input name
返回：无
备注：子窗口输入 内容的 name 和 id 用"$"分割
	父窗口window.open时 要将pinput和phinput的名称以get的形式传给子窗口，再由子窗口request.getParameter得到他们
*/

function addToPwindow(pinput,phinput,sinput,flag){
	var pobj = eval("window.opener.document." + pinput);
	var phobj = eval("window.opener.document." + phinput);
	var sobj = eval("window.document." + sinput);
	var i = 0;
	var strlist;
	var str = "";
	var hstr = "";
	if(sinput.length == "undefined"){
		str = sobj.value;
		hstr = sobj.value;
	}
	else{
		for(i;i<sobj.length;i++){
			if(sobj[i].checked == true){
				strlist = sobj[i].value.split("$");
				str = str + strlist[0] + " ";
				hstr = hstr + strlist[1] + " ";
			}
		}
	}
	phobj.value = hstr;
	pobj.value = str;
	if(flag == 1)
	window.close();
}
/* FUNCTION---------------------------------------------addToPwindowBySplit
功能：字窗口form内容复制到夫窗口form
参数：	pinput: 父窗口输入框name: form name.input name
                phinput：父窗口hidden输入框name: form name.input name
                 sinput: 子窗口输入框name: form name.input name
                split :字窗口输入内容间的分隔符
返回：无
备注：子窗口输入 内容的 name 和 id 用split指定的分割符分割
        父窗口window.open时 要将pinput和phinput的名称以get的形式传给子窗口，再由子窗口request.getParameter得到他们
*/

function addToPwindowBySplit(pinput,phinput,sinput,flag,split){
        var pobj = eval("window.opener.document." + pinput);
        var phobj = eval("window.opener.document." + phinput);
        var sobj = eval("window.document." + sinput);
        var i = 0;
        var strlist;
        var str = "";
        var hstr = "";
        if(sinput.length == "undefined"){
                str = sobj.value;
                hstr = sobj.value;
        }
        else{
                for(i;i<sobj.length;i++){
                        if(sobj[i].checked == true){
                                strlist = sobj[i].value.split(split);
                                str = str + strlist[0] ;
                                hstr = hstr + strlist[1] ;
                        }
                }
        }
        phobj.value = hstr;
        pobj.value = str;
        if(flag == 1)
        window.close();
}

/* FUNCTION---------------------------------------------SA
功能：
    对一组同名复选框进行全选或者取消全选操作
参数：
    checkboxname    复选框的名称（包括form的名称，如：form1.checkbox1）

逻辑：
       1、当该组复选框全部都被选中时，函数对所有复选框进行清除选中（checked=false）操作
       2、否则（只要组中有一个复选框被选中），该函数对所有复选框进行选中（checked=true）操作
*/
function SA(checkboxname){
  var flag = true;
  var obj = eval("document."+checkboxname);
  for(var i=0;obj!=null && obj.length!=null && i<obj.length;i++){
     var e = obj[i];
     if(e.checked == false){
         flag = false;
     break;
    }
  }
  for(var i=0;obj!=null && obj.length!=null && i<obj.length;i++){
     var e = obj[i];
     e.checked = (!flag);
  }
  if(obj!=null && obj.length==null){
      obj.checked = !obj.checked;
  }
}

/* FUNCTION---------------------------------------------isSelected
功能：
    判断是否一组同名复选框中至少有一个被选中操作
参数：
    checkboxname    复选框的名称（包括form的名称，如：form1.checkbox1）

返回值：该组复选框都没被选中时返回false,否则返回true

*/
function isSelected(checkboxname){
  var flag=false;
  var obj = eval("document."+checkboxname);
  for(var i=0;obj!=null && obj.length!=null && i<obj.length;i++){
     var e = obj[i];
     if(e.checked==true){
        flag=true;
        break;
     }
  }
  if (obj!=null && obj.length==null){
     flag=obj.checked;
  }
   return flag;
}

//刷新父窗口，避免“重试〉某鱿直
function OpenerReload(){
   if(window.opener && !window.opener.closed){
	if(window.opener.document.condForm!=null)
		window.opener.document.condForm.submit();
	else if(window.opener.document.form1!=null)
		window.opener.document.form1.submit();
	else if(window.opener.document.conditionForm!=null)
		window.opener.document.conditionForm.submit();
	else if(window.opener.document.title!=null)
		window.opener.document.title.submit();
	else
		window.opener.location.reload();
    }
}
//只刷新父窗口
function OpenerJustReload(){
   if(window.opener && !window.opener.closed){
		window.opener.location = window.opener.location;
    }
}
