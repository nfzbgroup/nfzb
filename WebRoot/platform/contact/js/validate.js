/** FUNCTION---------------------------------------------trim
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

/** FUNCTION---------------------------------------------isNotBlank
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

/** FUNCTION---------------------------------------------isNotBlankEx
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

/** FUNCTION---------------------------------------------isNumber
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

/** FUNCTION---------------------------------------------isInteger
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

/** FUNCTION---------------------------------------------isBetween
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

/** FUNCTION---------------------------------------------isBetweenEx
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

/** FUNCTION---------------------------------------------isAbove
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

/** FUNCTION---------------------------------------------isAboveEx
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

/** FUNCTION---------------------------------------------isLengthBetween
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

/** FUNCTION---------------------------------------------isLengthBelow
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

/* FUNCTION---------------------------------------------isStandardDate
功能：
	判断输入值是否为合法的日期类型（格式为yyyy?mm?dd其中年份必须为[1000,9999]）
参数：
	Object	obj 标签对象
	String	conjunction 为年月日的连接符（长度为1）
返回：
	boolean	输入值是否为合法的日期类型
*/
function isStandardDate(obj,conjunction)
{
	obj.value = trim(obj.value);
	if(obj.value=="")
	    return true;
	if(obj.value.charAt(4)!=conjunction || obj.value.charAt(7)!=conjunction)
		return false;
	var year = obj.value.substring(0,4);
	var month = obj.value.substring(5,7);
	var day = obj.value.substring(8,10);
	var condition1 = isNaN(year) || isNaN(month) || isNaN(day);
	var condition2 = parseInt(year,10)!=parseFloat(year) || parseInt(month,10)!=parseFloat(month) || parseInt(day,10)!=parseFloat(day)
	if(condition1 || condition2)
		return false;
	var d = new Date();
	d.setFullYear(parseInt(year,10));
	d.setMonth(parseInt(month,10)-1);
	d.setDate(parseInt(day,10));
	year = d.getFullYear();
	month = d.getMonth()+1;
	day = d.getDate();
	var temp = year+conjunction;
	if(month<10)
		temp+="0";
	temp+=month+conjunction;
	if(day<10)
		temp+="0";
	temp+=day;
	if(obj.value==temp)
		return true;
	else
		return false;
}

/* FUNCTION---------------------------------------------isStandardDate
功能：
	判断输入值是否为合法的日期类型（格式为yyyy?mm其中年份必须为[1000,9999]）
参数：
	Object	obj 标签对象
	String	conjunction 为年月的连接符（长度为1）
返回：
	boolean	输入值是否为合法的日期类型
*/
function isStandardYM(obj,conjunction)
{
	obj.value = trim(obj.value);
	if(obj.value=="")
	    return false;
	if(obj.value.charAt(4)!=conjunction)
		return false;
	var year = obj.value.substring(0,4);
	var month = obj.value.substring(5,7);
	var condition1 = isNaN(year) || isNaN(month);
	var condition2 = parseInt(year,10)!=parseFloat(year) || parseInt(month,10)!=parseFloat(month)
	if(condition1 || condition2)
		return false;

}

/* FUNCTION---------------------------------------------preview
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
		//alert("aa");
		//alert("theform.condField="+theform.condField);
		//alert("theform.condField.selectedIndex="+theform.condField.selectedIndex);
		theform.condValue[theform.condField.selectedIndex].style.display = "";
		//alert("bb");
		theform.condValue[theform.condField.selectedIndex].disabled = false;
	if(flag==1)
		theform.condValue[theform.condField.selectedIndex].value = "";
}

//计算表单元素的实际位置
function getIEPosX(elt) { return getIEPos(elt,"Left"); }
function getIEPosY(elt) { return getIEPos(elt,"Top"); }
function getIEPos(elt,which) {
	iPos = 0
	while (elt!=null) {
		iPos += elt["offset" + which]
		elt = elt.offsetParent
	}
	return iPos
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