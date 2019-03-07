/*
 1.封装的请求方法:httpRequest()
 2.请求数据(传参为json):httpGetData()
 3.页面自适应及导航收缩功能
 4.根据企业类型判断移除不应该显示的左边菜单导航项
 5.省市区
 6.列表页清空条件重新搜索:reSearch()
 7.table-input的增删行:delTr(obj)
 8.弹框的打开关闭:openModal():打开有列表的弹框，openModalOther()打开无列表的弹框、弹框里的单选框点击事件
 9.打开关闭全局/局部遮罩层:beginFullLoad(),endFullLoad()，beginSmallLoad(),endSmallLoad()
 10.通用的请求失败的回调函数:errorCallBack()
 11.浮点数相加:FloatAdd()
 12.数字金额大写转换(可以处理整数,小数,负数):smallToBig()
 13.保留几位有效数字:toDecimal2()
 14.数字格式转换成千分位,去除千分位：formatMoney(),delFormatMoney()
 */

var mobileReg= '^1(3[0-9]|4[57]|5[0-35-9]|7[0-9]|8[0-9]|9[9])\\d{8}$';



//页面自适应及导航收缩功能
(function ($) {
    //定义autoWidth类，在使用此方法的页面New autoWidth()使用此方法
    var autoWidth= function (navSetting) {
        var _this_       = this;
        this.parent      = $(".page-main");
        this.navBg       = $("#navBg");
        this.nav         = $("#nav");
        this.mainBox      = $("#main-box");
        this.main        = $("#main");
        this.button      = $(".nav-button");
        this.navigation  = $("#navigation");
        this.navigationThree  = $("#navigationThree");
        this.navSetting  = navSetting;
        this.setting     = {
            "pointWidth":1600,          //临界宽度
            "scrollWidth": 17,          //滚动条宽度
            "gap": 20,                  //主体左右间隙
            "navState":0                //左侧导航状态，0为展开，1为收起
        };
        this.hrefConfig  = {
            "n1":"n1href",              //集团管理url
            "n2":"n2href",              //采购管理url
            "n3":"n3href",              //保证金管理url
            "n4":"n4href",              //融资管理url
            "n5":"n5href"               //设置中心url
        };
        this.setWidth();
        // this.setPower(this.navSetting.powerSetting);
        this.setNav(this.navSetting.navigationSetting);
        //浏览器宽度改变使用设置宽度方法
        $(window).resize(function () {
            _this_.setWidth();
        });
        //浏览器宽度改变使用设置宽度方法
        this.button.click(function () {
            if(_this_.setting.navState==0){
                _this_.setting.navState=1;
                _this_.setWidth();
                $(".nav-list-1").hide();
                $(".nav-button").addClass("open");
              }else{
                _this_.setting.navState=0;
                _this_.setWidth();
                $(".current").parents(".nav-list-1").show();
                $(".nav-button").removeClass("open");
            }
        })
    };
    autoWidth.prototype={
        setWidth: function () {
            var _self       = this;
            var navState    = _self.setting.navState;
            var windowWidth = $(window).width();
            var parentWidth = _self.parent.width();
            var mainWidth;
            if(navState==1){
                //nav收起宽度设置
                mainWidth=parentWidth - 50 - _self.setting.gap*2;
                this.navBg.css({"width":"50px"});
				this.navBg.removeClass("overhidden");   // 左边菜单栏宽度最小时取消 overflow：hidden；
				this.nav.removeClass().addClass("nav-mini");
				this.button.css({"left":"50px"});      //  点击按钮 使左边菜单栏宽度变大变小,按钮的位置
				/*小尺寸专用 底部用户退出按钮*/
				$(".user-avatar").css({
					"display": "block",
					"margin-left": "10px",
				});
				$(".user-name").css("display","none");
				$(".logout").css("display", "none");
                this.mainBox.css({
                    "margin-left":"50px",
                    "margin-right":"40px"
                });
                this.main.css({
                    "width":"100%",
                    "float":"left"
                    // "width":mainWidth
                })
            }else if(windowWidth<=_self.setting.pointWidth){
                //nav展开窄屏
                mainWidth=parentWidth - 150 - _self.setting.gap*2;
                this.navBg.css({"width":"150px"});
				this.navBg.addClass("overhidden");  // 左边菜单栏宽度变大时 隐藏滚轮
				this.nav.removeClass("nav-big").removeClass("nav-mini").addClass("nav-normal").addClass("overflow_xy");  //添加菜单栏滚动overflow_xy,宽度nav-normal; 移除其他菜单栏宽度样式
				this.button.css({"left":"150px"});  // 点击按钮 使左边菜单栏宽度变大变小 按钮的位置
				/*中尺寸专用 底部用户退出按钮*/
				$(".user-avatar").css({
					"display": "block",
					"margin-left": "10px",
				});
				$(".user-name").css({
					"display": "block",
					"margin-left": "5px",
				});
				$(".logout").css({
					"display": "block",
					"margin-right": "6px",
				});
                this.mainBox.css({
                    "margin-left":"150px",
                    "margin-right":"40px"
                });
                this.main.css({
                    "width":"100%",
                    "float":"left"
                    // "width":mainWidth
                })
            }else {
                //nav展开宽屏
                mainWidth= parentWidth - 200 - _self.setting.gap*2;
                this.navBg.css({"width":"200px"});
				this.navBg.addClass("overhidden");  // 左边菜单栏宽度变大时 隐藏滚轮
				this.nav.removeClass("nav-normal").removeClass("nav-mini").addClass("nav-big").addClass("overflow_xy");  //添加菜单栏滚动overflow_xy，宽度nav-normal; 移除其他菜单栏宽度样式
				this.button.css({"left":"200px"}); // 点击按钮 使左边菜单栏宽度变大变小,按钮的位置
				/*大尺寸专用 底部用户退出按钮*/
				$(".user-avatar").css({
					"display": "block",
					"margin-left": "24px",
				});
				$(".user-name").css({
					"display": "block",
					"margin-left": "12px",
				});
				$(".logout").css({
					"display": "block",
					"margin-right": "6px",
				});
                this.mainBox.css({
                    "margin-left":"200px",
                    "margin-right":"40px"
				});
                this.main.css({
                    "width":"100%",
					"float":"left"
                    // "width":mainWidth
                })
            }
        },
        setPower: function (powerSetting) {
                // 移除没用的权限
                for(var i=0;i<powerSetting.length;i++){
                    $('#'+powerSetting[i]).remove();
                }
        },
        setNav: function (navSetting) {
            // 设置显示的导航
            var firstId  = navSetting.split('-')[0];
            $('#'+firstId).find(".nav-list-1").show();
            $('#'+firstId).find("b").addClass("up");
            $('#'+firstId).find("h3").addClass("cur");
            var secondId = navSetting.split('-')[0]+'-'+navSetting.split('-')[1];
            $('#'+secondId).addClass("current dark");
            $('#'+secondId).find(".nav-list-2").show();
            var secondIdNew = navSetting.split('-')[0]+'-'+navSetting.split('-')[1]; //没三级菜单
            $('#'+secondIdNew).addClass("current dark");
            $('#'+secondIdNew+'[class=\'current dark\']>a').css("color","#ff8d08");
            var thirdId;
            if(navSetting.split('-').length == 3){
                thirdId= navSetting;
                $('#'+thirdId).addClass("current");
                $('#'+thirdId).parent().parent("li").addClass("current");
            }
            //设置面包屑导航
            var firstUrl        = $('#manageHome').attr("href");
            var firstText       = $('#'+firstId).children("h3").children("span").text();
            var secoundUrl      = $('#'+secondId).find("a").eq(0).attr("href");
            var secoundText     = $('#'+secondId).children("span").text();
            var secoundUrlNew     = $('#'+secondIdNew).find("a").attr("href");//没三级菜单
            var secoundTextNew     = $('#'+secondIdNew).find("a").text();
            var navigationText = this.navigation.attr("data-text");
            var navigationHtml  = '<a href="'+firstUrl+'">管理首页</a>';
                navigationHtml += '<em></em><span>'+ firstText +'</span>';
                navigationHtml += '<em></em><a href=" '+secoundUrl+' ">'+ secoundText +'</a>';
                navigationHtml += '<em></em><span id="pageTitle">'+navigationText+'</span>';
            this.navigation.html(navigationHtml);
            //三段面包屑 改 四段
            var navigationThreeText = this.navigationThree.attr("data-text");
            var navigationThreeHtml  = '<a href="'+firstUrl+'">管理首页</a>';
            navigationThreeHtml += '<em></em><span>'+ firstText +'</span>';
            navigationThreeHtml += '<em></em><a href="'+secoundUrlNew+'">'+ secoundTextNew +'</a>';
            navigationThreeHtml += '<em></em><span id="pageTitle">'+navigationThreeText+'</span>';
            this.navigationThree.html(navigationThreeHtml);
            // 一级导航点击事件
            var _that_ = this;
            this.nav.find("h3").click(function () {
            	_that_.nav.find("h3 b").removeClass("up");
                var nextUl= $(this).next(".nav-list-1");
                if(nextUl.is(":hidden")){
                    $(this).parent("div").siblings("div").find(".nav-list-1").hide();
                    $(this).find("b").addClass("up");
                    nextUl.show();
                }else{
                    nextUl.hide();
                    $(this).find("b").removeClass("up");
                }
            });
            // 二级导航点击事件
            this.nav.find(".nav-list-1").children("li").children("span").click(function () {
                var nextUl= $(this).next(".nav-list-2");
                $(this).parent("li").siblings("li").removeClass("dark");
                $(this).parent("li").addClass("dark");
                if(nextUl.is(":hidden")){
                    $(this).parent("li").siblings("li").find(".nav-list-2").hide();
                    nextUl.show();
                }else{
                    nextUl.hide();
                }
            })
        }
    };
    // window对象注册此方法
    window['autoWidth']= autoWidth;
})(jQuery);

//根据企业类型判断移除不应该显示的左边菜单导航项
function actionRemoveData(obj){
    for(var i=0;i<obj.length;i++){
        $('#'+obj[i]).remove();
    }
}








//地区选择三级联动插件 暂时不用
function area_up(element,level){
    var othLevel = 0;
    if(level==2){
        if($("#province").val()==""){
            return false;
        }
    }
    if(level==3){
        if($("#province").val()==""){
            return false;
        }
        if($("#city").val()==""){
            return false;
        }
    }
    $(".area-list:not('#"+element+"')").hide();
    $("ul[id='"+element+"']").toggle();
    $("ul[id='"+element+"'] li").click(function(){
        $(this).parent().prev().val($(this).html());
        $(this).parent().prev().attr("code",$(this).attr("code"));
        $(this).parent().hide();
        var code = $(this).attr("code");
        if(level==1){
            $("#city").val("");
            $("#city").attr("code","");
            $("#area").val("");
            $("#area").attr("code","");
            if(code==71 || code==81 || code==82){
                $("#cityList").html('');
            }else{
                othLevel=2;
                getCity(code,othLevel);
            }
        }else if(level==2){
            $("#area").val("");
            $("#area").attr("code","");
            othLevel=3;
            getArea(code,othLevel);
        }
    })
}





//列表页清空条件重新搜索
function reSearch(){
	$("#searchInput").val("");
	getList(1);
}

//隐藏table-input的一行
function hideTr(obj){
	$(obj).closest("tr").hide().find("input[name='itemDel']").val("1");
}
//删除table-input的一行
function delTr(obj){
	$(obj).closest("tr").remove();
	if(moneyCalcu&&typeof(moneyCalcu)=="function"){
        moneyCalcu();
	}
}
//增加table-input的一行
$(document).on("blur",".table-input #productTbody2 input",function(){
	var nullLength = $(this).closest("tr").nextAll("tr").length;
	if(($.trim($(this).val())!= "") && (nullLength == 0)){
		addTr();//新增一行（该方法写在自己页面中）
		$(this).closest("tr").find("td:last").append('<a href="javascript:void(0);" class="cbdbdbd" onclick="delTr(this);">删除</a>');
	}
});


//隐藏table-input的一行 合同录入
function hideTrC(obj){
    $(obj).closest("tr").hide().find("input[name='itemDel']").val("1");
    productTotal();
}

//删除table-input的一行
function delTrC(obj){
    $(obj).closest("tr").remove();
    productTotal();
}

//增加table-input的一行   合同录入
$(document).on("blur",".table-input #productTbody input",function(){
    var nullLength = $(this).closest("tr").nextAll("tr").length;
    if(($.trim($(this).val())!= "") && (nullLength == 0)){
        addTr();//新增一行（该方法写在自己页面中）
        $(this).closest("tr").find("td:last").append('<a href="javascript:void(0);" onclick="delTrC(this);">删除</a>');
    }
});

//隐藏table-input的一行
function delTrH(obj){
    $(obj).closest("tr").hide();
    $(obj).closest("tr").find("input").eq(1).val("1");
    productTotal();
}

//增加table-input的一行
$(document).on("blur","#bidProductTbody input",function(){
    var nullLength = $(this).closest("tr").nextAll("tr").length;
    if(($.trim($(this).val())!= "") && (nullLength == 0)){
        addTr();//新增一行（该方法写在自己页面中）
        $(this).closest("tr").find("td:last").append('<a href="javascript:void(0);" onclick="delTrH(this);">删除</a>');
    }
});


//弹框中的单选
$(document).on('click','.modal .radio',function(){
	$(this).removeClass("radio-no").addClass("radio-yes").parent().parent().siblings().find(".radio").removeClass("radio-yes").addClass("radio-no");
});

var modalFlag = 0;
//打开弹框（有列表）邀请供应商
function openModalSup(modalId){
    beginFullLoad("数据加载中");
    endFullLoad();
	$("body").append('<div id="modalCoverSup"></div>');
    setTimeout(function(){
		modalFlag = modalId;
		$("#modalCoverSup").show();
		$("#modalSup").show().css("top",$(document).scrollTop()+100);
		$("#"+modalId).show();
		getList(1);
    },400);

}
//关闭弹框  邀请供应商
function closeModalSup(id){
    $("#modalCoverSup").remove();
    $("#"+id).hide();
}

//打开弹框（有列表）
function openModal(modalId){
	modalFlag = modalId;
	$("#modalCover").show();
	$("#"+modalId).show();
	getList(1);
}
//打开弹框（无列表）
function openModalOther(modalId){
    modalFlag = modalId;
    $("#modalCover").show();
    $("#"+modalId).show();
}
//关闭弹框
function closeModal(id){
	$("#modalCover").hide();
	$("#"+id).hide();
}

//打开通用全屏加载中遮罩
function beginSmallLoad(text) {
    var h = $("html").height();
    if (document.body.scrollHeight > h) h = document.body.scrollHeight;
    if (text == "" || text == null) text = "数据加载中";
    $("body").append("<div style='height:" + h + "px' name='loading-mask' class='full-loading-mask'></div><div name='loading' class='full-loading'><div class='ball-spin-fade-loader'><div></div> <div></div> <div></div> <div></div> <div></div> <div></div> <div></div> <div></div> </div><div class='text'>"+text+"</div></div>");
    $("body").addClass("loading-body");
}
//关闭通用全屏加载中遮罩
function endSmallLoad() {
	setTimeout(function(){
		$("div[name='loading-mask']").remove();
	    $("div[name='loading']").remove();
	    $("body").removeClass("loading-body");
	},400);
}

//打开通用小块加载中遮罩
function beginFullLoad(text) {
    if (text == "" || text == null) text = "数据加载中";
    $("body").append("<div name='loading' class='bg-loading'> <div class='small-loading'><div class='ball-spin-fade-loader'><div></div> <div></div> <div></div> <div></div> <div></div> <div></div> <div></div> <div></div> </div><div class='small-text'>"+text+"</div></div> </div>");
    $("body").addClass("loading-body");
}
//关闭通用小块加载中遮罩
function endFullLoad() {
    setTimeout(function(){
    	$("div[name='loading']").remove();
    	$("body").removeClass("loading-body");
    },400);
}


//通用的请求失败的回调函数
function errorCallBack(name,err){
    endFullLoad();
    endSmallLoad();
    setTimeout(function(){
		new Dialog({
			"type":"fail",
			"title":"失败",
			"content":"网络异常",
			"buttons":["返回上一页","刷新页面"],
			"cancel":function(){
                history.go(-1);
			},
			"confirm":function(){
                location.reload();
			}
		});
    },400);
}

/*浮点数相加*/
function FloatAdd(arg1,arg2){
    var r1,r2,m;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2));
    return (arg1*m+arg2*m)/m;
}
// 两个浮点数相减  
function accSub(num1,num2){  
   var r1,r2,m;  
   try{r1 = num1.toString().split('.')[1].length;}catch(e){r1 = 0;}  
   try{r2=num2.toString().split(".")[1].length;}catch(e){r2=0;}  
   m=Math.pow(10,Math.max(r1,r2));  
   n=(r1>=r2)?r1:r2;  
   return (Math.round(num1*m-num2*m)/m).toFixed(n);  
}  
// 两数相乘
function accMul(arg1,arg2){  
    var m=0,s1=arg1.toString(),s2=arg2.toString();  
    try{m+=s1.split(".")[1].length}catch(e){}  
    try{m+=s2.split(".")[1].length}catch(e){}  
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)  
}
// 两数相除  
function accDiv(num1,num2){  
   var t1,t2,r1,r2;  
   try{t1 = num1.toString().split('.')[1].length;}catch(e){t1 = 0;}  
   try{t2=num2.toString().split(".")[1].length;}catch(e){t2=0;}  
   r1=Number(num1.toString().replace(".",""));  
   r2=Number(num2.toString().replace(".",""));  
   return (r1/r2)*Math.pow(10,t2-t1);  
}  

/** 数字金额大写转换(可以处理整数,小数,负数) */
function smallToBig(n)
{
    var fraction = ['角', '分'];
    var digit = ['零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'];
    var unit = [ ['元', '万', '亿'], ['', '拾', '佰', '仟']  ];
    var head = n < 0? '欠': '';
    n = Math.abs(n);
    var s = '';
    for (var i = 0; i < fraction.length; i++)
    {
        s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');
    }
    s = s || '整';
    n = Math.floor(n);

    for (var i = 0; i < unit[0].length && n > 0; i++)
    {
        var p = '';
        for (var j = 0; j < unit[1].length && n > 0; j++)
        {
            p = digit[n % 10] + unit[1][j] + p;
            n = Math.floor(n / 10);
        }
        s = p.replace(/(零.)*零$/, '').replace(/^$/, '零')  + unit[0][i] + s;
    }
    return head + s.replace(/(零.)*零元/, '元').replace(/(零.)+/g, '零').replace(/^整$/, '零元整');
}

//保留几位有效数字:x为传入的值，num为保留的小数位数
function toDecimal2(x,num) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return false;
    }
	//默认保留2位小数
	if(num != 0 ){
        num = (num > 0 && num <= 20) ? num : 2;
    }
    //var f = Math.round(x*100)/100;
	var f = Math.round(x*Math.pow(10,num))/Math.pow(10,num);
    var s = f.toString();
    var rs = s.indexOf('.');
    if (rs < 0) {
        rs = s.length;
        s += '.';
    }
    while (s.length <= rs + num) {
        s += '0';
    }
    return s;
}

//Math方法浮点数小数点后保留
function mathNum(x,num) {
    return Math.round(x*Math.pop(10,num))/Math.pop(10,num);
}

// 数字格式转换成千分位
function formatMoney(num){
	if($.trim(num + "") == ""){
		return "";
	}
	if(isNaN(num)){
		return "";
	}
	num = num+"";
	if(/^.*\..*$/.test(num)){
		var pointIndex =num.lastIndexOf(".");
		var intPart = num.substring(0,pointIndex);
		var pointPart =num.substring(pointIndex+1,num.length);
		intPart = intPart +"";
		var re = /(-?\d+)(\d{3})/;
		while(re.test(intPart)){
			intPart = intPart.replace(re,"$1,$2")
		}
		num = intPart + "." + pointPart;
	}else{
		num = num + "";
		var re = /(-?\d+)(\d{3})/;
		while(re.test(num)){
			num = num.replace(re,"$1,$2")
		}
	}
	return num;
}
function formatMoney2(number,n){
    if(n != 0 ){
        n = (n > 0 && n <= 20) ? n : 2;
    }
    number = parseFloat((number + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var sub_val = number.split(".")[0].split("").reverse();
    var sub_xs = number.split(".")[1];

    var n_html = "";
    for (i = 0; i < sub_val.length; i++){
        n_html += sub_val[i] + ((i + 1) % 3 == 0 && (i + 1) != sub_val.length ? "," : "");
    }
    if(n == 0){
        return n_html.split("").reverse().join("");
    }else{
        return n_html.split("").reverse().join("") +"."+ "<i style='font-size: 18px;color:#7b8fa6;'>" + sub_xs +"</i>";
    }
}
//去除千分位
function delFormatMoney(num){
	if($.trim(num + "") == ""){
		return "";
	}
	num = num.replace(/,/gi,'');
	return num;
}

//小数并确定小数点后位数
(function($){
	$.fn.extend({	
		decimalSet:function(n){  //小数 n小数点后面位数
			$(this).on('keyup', function (event) {
				//响应鼠标事件，允许左右方向键移动
			    event = window.event || event;
			    if (event.keyCode == 37 | event.keyCode == 39) {
			        return;
			    }
			    if(n==2){
				    //先把非数字的都替换掉，除了数字和. 
				    $(this).val($(this).val().replace(/[^\d.]/g, "").
				        //只允许一个小数点              
				        replace(/^\./g, "").replace(/\.{2,}/g, ".").
				        //只能输入小数点后两位
				        replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'));
				}else if(n==3){
					//先把非数字的都替换掉，除了数字和. 
				    $(this).val($(this).val().replace(/[^\d.]/g, "").
				        //只允许一个小数点              
				        replace(/^\./g, "").replace(/\.{2,}/g, ".").
				        //只能输入小数点后两位
				        replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d\d).*$/, '$1$2.$3'));
				}else if(n==4){
					//先把非数字的都替换掉，除了数字和. 
				    $(this).val($(this).val().replace(/[^\d.]/g, "").
				        //只允许一个小数点              
				        replace(/^\./g, "").replace(/\.{2,}/g, ".").
				        //只能输入小数点后两位
				        replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3'));
				}
				else if(n==7){
					//先把非数字的都替换掉，除了数字和. 
				    $(this).val($(this).val().replace(/[^\d.]/g, "").
				        //只允许一个小数点              
				        replace(/^\./g, "").replace(/\.{2,}/g, ".").
				        //只能输入小数点后两位
				        replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d\d\d\d\d\d).*$/, '$1$2.$3'));
				}
                //当位数大于1位时，如果第一位是0，那么移除第一位
                if($(this).val().length>1){
					if($(this).val().charAt(1)!="."){
                        var nf = $(this).val().charAt(0);
                        if(nf==0){
                            $(this).val($(this).val().substring(1));
                        }
					}
                }
			});
			$(this).on('paste',function(event){
				if(n==2){
				    setTimeout(function() {
					    //响应鼠标事件，允许左右方向键移动 
					    event = window.event || event;
					    if (event.keyCode == 37 | event.keyCode == 39) {
					        return;
					    }
					    //先把非数字的都替换掉，除了数字和. 
					    $(this).val($(this).val().replace(/[^\d.]/g, "").
					        //只允许一个小数点              
					        replace(/^\./g, "").replace(/\.{2,}/g, ".").
					        //只能输入小数点后两位
					        replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'));
				    }, 100);
			    }else if(n==3){
			    	setTimeout(function() {
					    //响应鼠标事件，允许左右方向键移动 
					    event = window.event || event;
					    if (event.keyCode == 37 | event.keyCode == 39) {
					        return;
					    }
					    //先把非数字的都替换掉，除了数字和. 
					    $(this).val($(this).val().replace(/[^\d.]/g, "").
					        //只允许一个小数点              
					        replace(/^\./g, "").replace(/\.{2,}/g, ".").
					        //只能输入小数点后两位
					        replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d\d).*$/, '$1$2.$3'));
				    }, 100);
			    }else if(n==4){
			    	setTimeout(function() {
					    //响应鼠标事件，允许左右方向键移动 
					    event = window.event || event;
					    if (event.keyCode == 37 | event.keyCode == 39) {
					        return;
					    }
					    //先把非数字的都替换掉，除了数字和. 
					    $(this).val($(this).val().replace(/[^\d.]/g, "").
					        //只允许一个小数点              
					        replace(/^\./g, "").replace(/\.{2,}/g, ".").
					        //只能输入小数点后两位
					        replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3'));
				    }, 100);
			    }else if(n==7){
			    	setTimeout(function() {
					    //响应鼠标事件，允许左右方向键移动 
					    event = window.event || event;
					    if (event.keyCode == 37 | event.keyCode == 39) {
					        return;
					    }
					    //先把非数字的都替换掉，除了数字和. 
					    $(this).val($(this).val().replace(/[^\d.]/g, "").
					        //只允许一个小数点              
					        replace(/^\./g, "").replace(/\.{2,}/g, ".").
					        //只能输入小数点后两位
					        replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d\d\d\d\d\d).*$/, '$1$2.$3'));
				    }, 100);
			    }
                //当位数大于1位时，如果第一位是0，那么移除第一位
                if($(this).val().length>1){
                    if($(this).val().charAt(1)!="."){
                        var nf = $(this).val().charAt(0);
                        if(nf==0){
                            $(this).val($(this).val().substring(1));
                        }
                    }
                }
			});
			$(this).on('blur', function () {
			    //最后一位是小数点的话，移除
			    $(this).val(($(this).val().replace(/\.$/g, "")));
			    //判断如果小数点移除了以后只剩一个0的话，移除
			    if($(this).val()==0){
			    	$(this).val('');
			    }
			});
		},
		positiveSet:function(){   //正整数可以等于0,但是如果是多位，但是第一位不能是0
			$(this).on('keyup', function (event) {
				//响应鼠标事件，允许左右方向键移动
				event = window.event || event;
				if (event.keyCode == 37 | event.keyCode == 39) {
					return;
				}
				//先把非数字的都替换掉，除了数字
				$(this).val($(this).val().replace(/[^\d]/g, ""));

				//当位数大于1位时，如果第一位是0，那么移除第一位
				if($(this).val().length>1){
					var nf = $(this).val().charAt(0);
					if(nf==0){
						$(this).val($(this).val().substring(1));
					}
				}
			});
			$(this).on('paste',function(event){
			    setTimeout(function() {
				    //响应鼠标事件，允许左右方向键移动 
				    event = window.event || event;
				    if (event.keyCode == 37 | event.keyCode == 39) {
				        return;
				    }
				    //先把非数字的都替换掉，除了数字
				    $(this).val($(this).val().replace(/[^\d]/g, ""));
				    
				    //当位数大于1位时，如果第一位是0，那么移除第一位
				    if($(this).val().length>1){
						var nf = $(this).val().charAt(0);
						if(nf===0){
							$(this).val($(this).val().substring(1));
						}
					}
			    }, 100);
			});
			$(this).on('blur', function () {
			    //判断如果连续两个0的话，移除
			    if($(this).val()=="00" || $(this).val()=="000" || $(this).val()=="0000" || $(this).val()=="00000"){
			    	$(this).val('');
			    }
			});
		},
        integerSet:function(){  //正整数大于0
            $(this).on('keyup', function (event) {
                //响应鼠标事件，允许左右方向键移动
                event = window.event || event;
                if (event.keyCode == 37 | event.keyCode == 39) {
                    return;
                }
                //先把非数字的都替换掉，除了数字
                $(this).val($(this).val().replace(/[^\d]/g, ""));

                //当位数大于1位时，如果第一位是0，那么移除第一位
                if($(this).val().length>1){
                    var nf = $(this).val().charAt(0);
                    if(nf==0){
                        $(this).val($(this).val().substring(1));
                    }
                }
                //如果等于0,直接移除
                if($(this).val()==0){
                    $(this).val('');
                }
            });
            $(this).on('paste',function(event){
                setTimeout(function() {
                    //响应鼠标事件，允许左右方向键移动
                    event = window.event || event;
                    if (event.keyCode == 37 | event.keyCode == 39) {
                        return;
                    }
                    //先把非数字的都替换掉，除了数字
                    $(this).val($(this).val().replace(/[^\d]/g, ""));

                    //当位数大于1位时，如果第一位是0，那么移除第一位
                    if($(this).val().length>1){
                        var nf = $(this).val().charAt(0);
                        if(nf===0){
                            $(this).val($(this).val().substring(1));
                        }
                    }
                    //如果等于0,直接移除
                    if($(this).val()==0){
                        $(this).val('');
                    }
                }, 100);
            });
            $(this).on('blur', function () {
                //判断如果只剩一个0的话，移除
                if($(this).val()=="0" || $(this).val()=="00" || $(this).val()=="000" || $(this).val()=="0000" || $(this).val()=="00000"){
                    $(this).val('');
                }
            });
        },
        telSet:function(){  //固话格式，可以输入中线 -
            $(this).on('keyup', function (event) {
                //响应鼠标事件，允许左右方向键移动
                event = window.event || event;
                if (event.keyCode == 37 | event.keyCode == 39) {
                    return;
                }
                //先把非数字的都替换掉，除了数字
                $(this).val($(this).val().replace(/[^\d-]/g, ""));
            });
            $(this).on('paste',function(event){
                setTimeout(function() {
                    //响应鼠标事件，允许左右方向键移动
                    event = window.event || event;
                    if (event.keyCode == 37 | event.keyCode == 39) {
                        return;
                    }
                    //先把非数字的都替换掉，除了数字
                    $(this).val($(this).val().replace(/[^\d-]/g, ""));
                }, 100);
            });
            $(this).on('blur', function () {
                //判断如果输入两个0以上，移除
                if($(this).val()=="00" || $(this).val()=="000" || $(this).val()=="0000" || $(this).val()=="00000"){
                    $(this).val('');
                }
            });
        },
        mobSet:function(){  //手机格式
            $(this).on('keyup', function (event) {
                //响应鼠标事件，允许左右方向键移动
                event = window.event || event;
                if (event.keyCode == 37 | event.keyCode == 39) {
                    return;
                }
                //先把非数字的都替换掉，除了数字
                $(this).val($(this).val().replace(/[^\d]/g, ""));

                //当位数大于1位时，如果第一位是0，那么移除第一位
                if($(this).val().length>1){
                    var nf = $(this).val().charAt(0);
                    if(nf==0){
                        $(this).val($(this).val().substring(1));
                    }
                }
            });
            $(this).on('paste',function(event){
                setTimeout(function() {
                    //响应鼠标事件，允许左右方向键移动
                    event = window.event || event;
                    if (event.keyCode == 37 | event.keyCode == 39) {
                        return;
                    }
                    //先把非数字的都替换掉，除了数字
                    $(this).val($(this).val().replace(/[^\d]/g, ""));

                    //当位数大于1位时，如果第一位是0，那么移除第一位
                    if($(this).val().length>1){
                        var nf = $(this).val().charAt(0);
                        if(nf==0){
                            $(this).val($(this).val().substring(1));
                        }
                    }
                }, 100);
            });
            $(this).on('blur', function () {
                //判断如果输入0以上，移除
                if($(this).val()=="0" || $(this).val()=="00" || $(this).val()=="000" || $(this).val()=="0000" || $(this).val()=="00000"){
                    $(this).val('');
                }
            });
        },
        telCode:function(){  //区号 021
            $(this).on('keyup', function (event) {
                //响应鼠标事件，允许左右方向键移动
                event = window.event || event;
                if (event.keyCode == 37 | event.keyCode == 39) {
                    return;
                }
                //先把非数字的都替换掉，除了数字
                $(this).val($(this).val().replace(/[^\d]/g, ""));
            });
            $(this).on('paste',function(event){
                setTimeout(function() {
                    //响应鼠标事件，允许左右方向键移动
                    event = window.event || event;
                    if (event.keyCode == 37 | event.keyCode == 39) {
                        return;
                    }
                    //先把非数字的都替换掉，除了数字
                    $(this).val($(this).val().replace(/[^\d]/g, ""));
                }, 100);
            });
            $(this).on('blur', function () {
                //判断如果输入两个0以上，移除
                if($(this).val()=="00" || $(this).val()=="000" || $(this).val()=="0000" || $(this).val()=="00000"){
                    $(this).val('');
                }
            });
        }

    });
})(jQuery);

//附件删除
function delThis(n) {
    n.parents("p").remove();
}

//普通常用下拉列表
$(document).on("click",".common-select",function(e){
	$(".common-select").toggleClass("brt5px");
    $("ul[name='selectUl']").not($(this).next("ul")).hide();
    $(this).next("ul").toggle();
    e.stopPropagation();
});
$(document).click(function(e){
    $(".common-select").removeClass("brt5px");
    $(".common-select").next("ul").hide();
});


//标书进度条
var bidProgress = '<ul class="clearfix num8" action="0">';
bidProgress += '<li class="nav1"> <span></span> <p>发布标书</p> </li>';
bidProgress += '<li class="nav2"> <span></span> <p>平台客服审核中</p> </li>';
bidProgress += '<li class="nav3"> <span></span> <p>管理单位授权</p> </li>';
bidProgress += '<li class="nav4"> <span></span> <p>集团授权</p> </li>';
bidProgress += '<li class="nav5"> <span></span> <p>保证金待支付</p> </li>';
bidProgress += '<li class="nav6"> <span></span> <p>正在招标</p> </li>';
bidProgress += '<li class="nav7"> <span></span> <p>开标议标</p> </li>';
bidProgress += '<li class="nav8 last"> <span></span> <p>定标</p> </li> </ul>';

var bidFinanceProgress = '<ul class="clearfix num10" action="0">';
bidFinanceProgress += '<li class="nav1"> <span></span> <p>发布标书</p> </li>';
bidFinanceProgress += '<li class="nav2"> <span></span> <p>平台客服审核中</p> </li>';
bidFinanceProgress += '<li class="nav3"> <span></span> <p>管理单位授权</p> </li>';
bidFinanceProgress += '<li class="nav3"> <span></span> <p>管理单位复核</p> </li>';
bidFinanceProgress += '<li class="nav5"> <span></span> <p>集团授权</p> </li>';
bidFinanceProgress += '<li class="nav6"> <span></span> <p>集团复核</p> </li>';
bidFinanceProgress += '<li class="nav7"> <span></span> <p>保证金待支付</p> </li>';
bidFinanceProgress += '<li class="nav8"> <span></span> <p>正在招标</p> </li>';
bidFinanceProgress += '<li class="nav9"> <span></span> <p>开标议标</p> </li>';
bidFinanceProgress += '<li class="nav10 last"> <span></span> <p>定标</p> </li> </ul>';

//进度条函数
function progressShow(state,a0,a1,a2,a3,a4,paid,iscash,isfinancing,bidstate,bidId){
    var param = {};
    param.interfaceName = "Bid_getAuthorMan";
    param.requestSource = "1";
    param.token = $("#tokenstr").val();
    param.bid = bidId;
    httpGetData(param,bigManSuccCallback,errorCallBack);
    function bigManSuccCallback(data) {
        if (data.author_1) {
            if (isfinancing == 1) {
                $(".bid-process ul").find("li").eq(2).addClass("tips").attr("title", "<b>授权人员</b><br>" + data.author_1.listname + "<br>" + data.author_1.phone);
            } else {
                $(".bid-process ul").find("li").eq(2).addClass("tips").attr("title", "<b>授权人员</b><br>" + data.author_1.listname + "<br>" + data.author_1.phone);
            }
        }

        if (data.author_3) {
            if (isfinancing == 1) {
                $(".bid-process ul").find("li").eq(3).addClass("tips").attr("title", "<b>复核人员</b><br>" + data.author_3.listname + "<br>" + data.author_3.phone);
            }
        }

        if (data.author_2) {
            if (isfinancing == 1) {
                $(".bid-process ul").find("li").eq(4).addClass("tips").attr("title", "<b>授权人员</b><br>" + data.author_2.listname + "<br>" + data.author_2.phone);
            } else {
                $(".bid-process ul").find("li").eq(3).addClass("tips").attr("title", "<b>授权人员</b><br>" + data.author_2.listname + "<br>" + data.author_2.phone);
            }
        }

        if (data.author_4) {
            if (isfinancing == 1) {
                $(".bid-process ul").find("li").eq(5).addClass("tips").attr("title", "<b>复核人员</b><br>" + data.author_4.listname + "<br>" + data.author_4.phone);
            }
        }
        if (isfinancing == 1){
            $('.tips').poshytip({
                className: 'tip-yellowsimple',
                showOn: 'hover',
                alignTo: 'target',
                alignX: 'right',
                alignY: 'bottom',
                offsetX: -120,
                offsetY: -55
            });
    	}else{
			$('.tips').poshytip({
				className: 'tip-yellowsimple',
				showOn: 'hover',
				alignTo: 'target',
				alignX: 'right',
				alignY: 'bottom',
				offsetX: -160,
				offsetY: -55
			});
		}
    }
	if(isfinancing==1) {  //金融标书进度条
        if (state == '正在审核' || state == '2') {
            var wend = 0;
            while (wend != 1) {
                if (a0 == '0') {
                    $(".bid-process ul").attr("action", "2");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核中");
                    break;
                }

                if (a0 == '2') {
                    $(".bid-process ul").attr("action", "2");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台审核不通过");
                    break;
                }

                if (a1 == '0') {
                    $(".bid-process ul").attr("action", "3");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
                    $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权中");
                    break;
                }

                if (a1 == '2') {
                    $(".bid-process ul").attr("action", "3");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
                    $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权不通过");
                    break;
                }

                if (a3 == '0') {
                    $(".bid-process ul").attr("action", "4");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
                    $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
                    $(".bid-process ul").find("li").eq(3).find("p").text("管理单位复核中");
                    break;
                }

                if (a3 == '2') {
                    $(".bid-process ul").attr("action", "4");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
                    $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
                    $(".bid-process ul").find("li").eq(3).find("p").text("管理单位复核不通过");
                    break;
                }

                if (a2 == '0') {
                    $(".bid-process ul").attr("action", "5");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
                    $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
                    $(".bid-process ul").find("li").eq(3).find("p").text("管理单位复核通过");
                    $(".bid-process ul").find("li").eq(4).find("p").text("集团授权中");
                    break;
                }

                if (a2 == '2') {
                    $(".bid-process ul").attr("action", "5");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
                    $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
                    $(".bid-process ul").find("li").eq(3).find("p").text("管理单位复核通过");
                    $(".bid-process ul").find("li").eq(4).find("p").text("集团授权不通过");
                    break;
                }

                if (a4 == '0') {
                    $(".bid-process ul").attr("action", "6");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
                    $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
                    $(".bid-process ul").find("li").eq(3).find("p").text("管理单位复核通过");
                    $(".bid-process ul").find("li").eq(4).find("p").text("集团授权通过");
                    $(".bid-process ul").find("li").eq(5).find("p").text("集团复核中");
                    break;
                }

                if (a4 == '2') {
                    $(".bid-process ul").attr("action", "6");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
                    $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
                    $(".bid-process ul").find("li").eq(3).find("p").text("管理单位复核通过");
                    $(".bid-process ul").find("li").eq(4).find("p").text("集团授权通过");
                    $(".bid-process ul").find("li").eq(5).find("p").text("集团复核不通过");
                    break;
                }

                if (paid == '2' && iscash == '1') {
                    $(".bid-process ul").attr("action", "7");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
                    $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
                    $(".bid-process ul").find("li").eq(3).find("p").text("管理单位复核通过");
                    $(".bid-process ul").find("li").eq(4).find("p").text("集团授权通过");
                    $(".bid-process ul").find("li").eq(5).find("p").text("集团复核通过");
                    $(".bid-process ul").find("li").eq(6).find("p").text("保证金待支付");
                    break;
                }
                wend = 1;
                break;
            }
        }
        else if (state == '审核不通过' || state == '6') {
            $(".bid-process ul").attr("action", "2");
            $(".bid-process ul").find("li").eq(1).find("p").text("平台审核不通过");
        }
        else if (state == '正在招标' || state == '3' ) {
            $(".bid-process ul").attr("action", "8");
            $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
            $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
            $(".bid-process ul").find("li").eq(3).find("p").text("管理单位复核通过");
            $(".bid-process ul").find("li").eq(4).find("p").text("集团授权通过");
            $(".bid-process ul").find("li").eq(5).find("p").text("集团复核通过");
            $(".bid-process ul").find("li").eq(6).find("p").text("保证金支付成功");
            $(".bid-process ul").find("li").eq(7).find("p").text("正在招标");
        }
        else if (state == '开标议标' || state == '4' ) {
            $(".bid-process ul").attr("action", "9");
            $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
            $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
            $(".bid-process ul").find("li").eq(3).find("p").text("管理单位复核通过");
            $(".bid-process ul").find("li").eq(4).find("p").text("集团授权通过");
            $(".bid-process ul").find("li").eq(5).find("p").text("集团复核通过");
            $(".bid-process ul").find("li").eq(6).find("p").text("保证金支付成功");
            $(".bid-process ul").find("li").eq(7).find("p").text("正在招标");
            $(".bid-process ul").find("li").eq(8).find("p").text("开标议标");
        }
        else if (state == '招标结束' || state == '5' ) {
            $(".bid-process ul").attr("action", "10");
            $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
            $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
            $(".bid-process ul").find("li").eq(3).find("p").text("管理单位复核通过");
            $(".bid-process ul").find("li").eq(4).find("p").text("集团授权通过");
            $(".bid-process ul").find("li").eq(5).find("p").text("集团复核通过");
            $(".bid-process ul").find("li").eq(6).find("p").text("保证金支付成功");
            $(".bid-process ul").find("li").eq(7).find("p").text("正在招标");
            $(".bid-process ul").find("li").eq(8).find("p").text("开标议标");
            if(bidstate=='1'){
                $(".bid-process ul").find("li").eq(9).find("p").text("中标");
            }else if(bidstate=='2'){
                $(".bid-process ul").find("li").eq(9).find("p").text("流标");
            }else if(bidstate=='3'){
                $(".bid-process ul").find("li").eq(9).find("p").text("流标待审核");
                $(".bid-process ul").find("li").eq(9).find("p").css({"width":"90px","left":"-12px"});
            }else if(bidstate=='4'){
                $(".bid-process ul").find("li").eq(9).find("p").text("流标审核不通过");
                $(".bid-process ul").find("li").eq(9).find("p").css({"width":"90px","left":"-25px"});
            }else if(bidstate=='5'){
                $(".bid-process ul").find("li").eq(9).find("p").text("二次招标");
                $(".bid-process ul").find("li").eq(9).find("p").css({"width":"90px","left":"-8px"});
            }

        }
    }else{  //普通标书进度条
        if (state == '正在审核' || state == '2') {
            var wend = 0;
            while (wend != 1) {
                if (a0 == '0') {
                    $(".bid-process ul").attr("action", "2");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核中");
                    break;
                }

                if (a0 == '2') {
                    $(".bid-process ul").attr("action", "2");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台审核不通过");
                    break;
                }

                if (a1 == '0') {
                    $(".bid-process ul").attr("action", "3");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
                    $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权中");
                    break;
                }

                if (a1 == '2') {
                    $(".bid-process ul").attr("action", "3");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
                    $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权不通过");
                    break;
                }

                if (a2 == '0') {
                    $(".bid-process ul").attr("action", "4");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
                    $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
                    $(".bid-process ul").find("li").eq(3).find("p").text("集团授权中");
                    break;
                }

                if (a2 == '2') {
                    $(".bid-process ul").attr("action", "4");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
                    $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
                    $(".bid-process ul").find("li").eq(3).find("p").text("集团授权不通过");
                    break;
                }

                if (paid == '2' && iscash == '1') {
                    $(".bid-process ul").attr("action", "5");
                    $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
                    $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
                    $(".bid-process ul").find("li").eq(3).find("p").text("集团授权通过");
                    $(".bid-process ul").find("li").eq(4).find("p").text("保证金待支付");
                    break;
                }
                wend = 1;
                break;
            }
        }
        else if (state == '审核不通过' || state == '6') {
            $(".bid-process ul").attr("action", "2");
            $(".bid-process ul").find("li").eq(1).find("p").text("平台审核不通过");
        }
        else if (state == '正在招标' || state == '3' ) {
            $(".bid-process ul").attr("action", "6");
            $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
            $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
            $(".bid-process ul").find("li").eq(3).find("p").text("集团授权通过");
            $(".bid-process ul").find("li").eq(4).find("p").text("保证金支付成功");
            $(".bid-process ul").find("li").eq(5).find("p").text("正在招标");
        }
        else if (state == '开标议标' || state == '4' ) {
            $(".bid-process ul").attr("action", "7");
            $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
            $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
            $(".bid-process ul").find("li").eq(3).find("p").text("集团授权通过");
            $(".bid-process ul").find("li").eq(4).find("p").text("保证金支付成功");
            $(".bid-process ul").find("li").eq(5).find("p").text("正在招标");
            $(".bid-process ul").find("li").eq(6).find("p").text("开标议标");
        }
        else if (state == '招标结束' || state == '5' ) {
            $(".bid-process ul").attr("action", "8");
            $(".bid-process ul").find("li").eq(1).find("p").text("平台客服审核通过");
            $(".bid-process ul").find("li").eq(2).find("p").text("管理单位授权通过");
            $(".bid-process ul").find("li").eq(3).find("p").text("集团授权通过");
            $(".bid-process ul").find("li").eq(4).find("p").text("保证金支付成功");
            $(".bid-process ul").find("li").eq(5).find("p").text("正在招标");
            $(".bid-process ul").find("li").eq(6).find("p").text("开标议标");
        	if(bidstate=='1'){
                $(".bid-process ul").find("li").eq(7).find("p").text("中标");
			}else if(bidstate=='2'){
                $(".bid-process ul").find("li").eq(7).find("p").text("流标");
			}else if(bidstate=='3'){
                $(".bid-process ul").find("li").eq(7).find("p").text("流标待审核");
                $(".bid-process ul").find("li").eq(7).find("p").css({"width":"90px","left":"-12px"});
            }else if(bidstate=='4'){
                $(".bid-process ul").find("li").eq(7).find("p").text("流标审核不通过");
                $(".bid-process ul").find("li").eq(7).find("p").css({"width":"90px","left":"-25px"});
            }else if(bidstate=='5'){
                $(".bid-process ul").find("li").eq(7).find("p").text("二次招标");
            }

        }
	}
}

//密码等级验证
var pwdNum_regular = "^.{8,20}$";  //密码的正则，8-20位
var pwdTs_regular = "^[a-z|A-Z|0-9|_|\-|#|;|@|?|<|>|(|)|^|%|&|$|*|\.|,|!|~]{1,90}$";  //密码的正则 只能包含大小写字母、数字以及标点符号（除空格）
var pwdTt_regular = "^(?![0-9]+$)(?![A-Z]+$)(?![a-z]+$)(?![_|\-|#|;|@|?|<|>|(|)|^|%|&|$|*|\.|,|!|~]+$)[0-9A-Za-z|_|\-|#|;|@|?|<|>|(|)|^|%|&|$|*|\.|,|!|~]{1,90}$";
var pwd_einfo1 = 0;
var pwd_einfo2 = 0;
var pwd_einfo3 = 0;
$(".password").keyup(function(){
    var regular_val = $(this).val();
    if(regular_val.match(pwdNum_regular)){
        $(".pwd-einfo1").addClass("pwd-einfo-s");
        pwd_einfo1=1;
    }else{
        $(".pwd-einfo1").removeClass("pwd-einfo-s");
        pwd_einfo1=0;
    }
    if(regular_val.match(pwdTs_regular)){
        $(".pwd-einfo2").addClass("pwd-einfo-s");
        pwd_einfo2=1;
    }else{
        $(".pwd-einfo2").removeClass("pwd-einfo-s");
        pwd_einfo2=0;
    }
    if(regular_val.match(pwdTt_regular)){
        $(".pwd-einfo3").addClass("pwd-einfo-s");
        pwd_einfo3=1;
    }else{
        $(".pwd-einfo3").removeClass("pwd-einfo-s");
        pwd_einfo3=0;
    }

    if(pwd_einfo1==1 && pwd_einfo2==1  && pwd_einfo3==1){
        // var strongRegex = new RegExp("^(?=.{12,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
        // var mediumRegex = new RegExp("^(?=.{10,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
        // var enoughRegex = new RegExp("(?=.{8,}).*", "g");
        var strongRegex = new RegExp("(?=.{12,}).*", "g");
        var mediumRegex = new RegExp("(?=.{10,}).*", "g");
        var enoughRegex = new RegExp("(?=.{8,}).*", "g");

        if (false == enoughRegex.test($(this).val())) {
            $(".pwd-einfo-sta").find("span").removeClass();
            $(".pwd-sta").html("");
        }else if (strongRegex.test($(this).val())) {
            $(".pwd-einfo-sta").find("span").removeClass();
            $("#pwd-sta1,#pwd-sta1s").addClass("high");
            $("#pwd-sta2,#pwd-sta2s").addClass("high");
            $("#pwd-sta3,#pwd-sta2s").addClass("high");
            $(".pwd-sta").html("高");
            /*密码为八位及以上并且字母数字特殊字符三项都包括,强度最强 */
        }else if (mediumRegex.test($(this).val())) {
            $(".pwd-einfo-sta").find("span").removeClass();
            $("#pwd-sta1,#pwd-sta1s").addClass("medium");
            $("#pwd-sta2,#pwd-sta2s").addClass("medium");
            $(".pwd-sta").html("中");
            /*密码为七位及以上并且字母、数字、特殊字符三项中有两项，强度是中等 */
        }else {
            $(".pwd-einfo-sta").find("span").removeClass();
            $("#pwd-sta1,#pwd-sta1s").addClass("low");
            $(".pwd-sta").html("低");
            /*如果密码为6为及以下，就算字母、数字、特殊字符三项都包括，强度也是弱的 */
        }
    }else{
        $(".pwd-einfo-sta").find("span").removeClass();
        $(".pwd-sta").html("");
    }
});

//通用点击单选框后面文字也触发单选框事件
$(".radio").next("span").click(function(){
    $(this).prev(".radio").removeClass("radio-no").addClass("radio-yes").siblings(".radio").removeClass("radio-yes").addClass("radio-no");
    $(this).prev(".radio").click();
});

//新增单独验证手机号码的
(function($){
    $.fn.extend({
        mobilePhone:function(){  //小数 n小数点后面位数
            $(this).on('keyup', function (event) {
                event = window.event || event;
                if (event.keyCode == 37 | event.keyCode == 39) {
                    return;
                }
                var thisVal = $(this).val();
                var mbRegExp = mobileReg;
                var mobile_regular = new RegExp(mbRegExp);
                if (mobile_regular.test(thisVal) == false) {
                    $(this).css({"border-color":"red","color":"red"});
                    $(this).attr("data-phone","0");
                } else {
                    $(this).css({"border-color":"#ddd","color":"#333"});
                    $(this).attr("data-phone","1");
                }
            });
            $(this).on('paste', function (event) {
                event = window.event || event;
                if (event.keyCode == 37 | event.keyCode == 39) {
                    return;
                }
                var thisVal = $(this).val();
                var mbRegExp = mobileReg;
                var mobile_regular = new RegExp(mbRegExp);
                if (mobile_regular.test(thisVal) == false) {
                    $(this).css({"border-color":"red","color":"red"});
                    $(this).attr("data-phone","0");
                } else {
                    $(this).css({"border-color":"#ddd","color":"#333"});
                    $(this).attr("data-phone","1");
                }
            });
        }
	})
})(jQuery);