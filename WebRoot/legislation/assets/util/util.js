(function($) {
    $.fn.formToJson = function() {
        var serializeObj = {};
        var array = this.serializeArray();
        $(array).each(
            function() {
                if (serializeObj[this.name]) {
                    if ($.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.value);
                    } else {
                        serializeObj[this.name] = [
                            serializeObj[this.name], this.value ];
                    }
                } else {
                    serializeObj[this.name] = this.value;
                }
            });
        return serializeObj;
    };
})(jQuery);
function onlyNum() {
    if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
        if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
            event.returnValue=false;
}

/**
 * 汇合工具类
 */
Duang = (function() {
    var loadJSForSync = function(isDebug, url) {
        if (isDebug) {
            jQuery.ajax({
                type : "GET",
                async : false,
                contentType:"text/html; charset=utf-8",
                url : url,
                dataType : "script",
                cache : project.profile=='product' ,
                error : function (XMLHttpRequest, textStatus, errorThrown) {
                    //alert('failed->'+url) ;
                }
            }) ;
        } else {
            var id=url.replace(/\//gm, '').replace(/\./gm, '');
            if ($("#" + id).length <= 0) {
                jQuery.ajax({
                    type : "GET",
                    async : false,
                    contentType:"text/html; charset=utf-8",
                    url : url,
                    dataType : "script",
                    cache : project.profile=='product' ,
                    success : function(data) {
                        $('<input>',{
                            id:id,
                            type:'hidden'
                        }).appendTo(document.body);
                    }
                });
            }
        }
    };

    var loadCSSForSync = function(isDebug, url) {
        if (isDebug) {
            jQuery.ajax({
                type : "POST",
                async : false,
                contentType:"text/css; charset=utf-8",
                url : url,
                dataType : "text",
                cache : false
            });
        } else {
            var id=url.replace(/\//gm, '').replace(/\./gm, '');
            if ($("#" + id).length <= 0) {
                jQuery.ajax({
                    type : "POST",
                    async : false,
                    contentType:"text/css; charset=utf-8",
                    url : url,
                    dataType : "text",
                    cache : false,
                    success : function(data) {
                        $('<style>',{
                            id:id,
                            type:'text/css'
                        }).appendTo(document.head).html(data);
                    }
                });
            }

        }
    };


    var loadJSForAsync = function(url) {
        var id=url.replace(/\//gm, '').replace(/\./gm, '');
        if ($("#" + id).length <= 0) {
            // 取得<head> Dom元素
            var oHead = document.getElementsByTagName("head").item(0);
            // 创建<script>元素，并设置个项属性
            var oScript = document.createElement("script");
            oScript.id = id;
            oScript.type = "text/javascript";
            oScript.src = url;

            // 将<script>元素追加到<head>元素中
            oHead.appendChild(oScript);
        }
    };

    var loadCSSForAsync = function(url) {
        var id=url.replace(/\//gm, '').replace(/\./gm, '');
        if ($("#" + id).length <= 0) {
            // 取得<head> Dom元素
            var oHead = document.getElementsByTagName("head").item(0);
            // 创建<script>元素，并设置个项属性
            var oScript = document.createElement("link");
            oScript.id = id;
            oScript.type = "text/css";
            oScript.rel='stylesheet';
            oScript.href = url;

            // 将<script>元素追加到<head>元素中
            oHead.appendChild(oScript);
        }
    };


    return {

        /**
         * 获取随机数
         */
        getRandom : function() {
            if (arguments[0]) {
                return Math.floor(Math.random() * arguments[0] + 1);
            }
            if (arguments[1]) {
                return parseInt(Math.random() * (arguments[0] - arguments[1])
                    + arguments[0]);
            }
        },

        /**
         * 设置Cookie
         *
         * @param name
         *            cookie名字
         * @param value
         *            cookie值
         *
         */
        setCookie : function(name, value) {
            var Days = 30; // 此 cookie 将被保存 30 天
            var exp = new Date(); // new Date("December 31, 9998");
            exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);

            document.cookie = name + "=" + escape(value) + ";expires="
                + exp.toGMTString()+";path=/";
        },

        /**
         * 获取Cookie
         *
         * @param name
         *            cookie名字
         * @returns cookie值
         */
        getCookie : function(name) {
            var arrStr = document.cookie.split("; ");
            for (var i = 0; i < arrStr.length; i++) {
                var temp = arrStr[i].split("=");
                if (temp[0] == name)
                    return unescape(temp[1]);
            }
        },

        /**
         * 删除cookie
         *
         * @param name
         *            cookie名字
         */
        removeCookie : function(name) {
            var expires = new Date();
            expires.setTime(expires.getTime() - 1000 * 60);
            this.setCookie(name, "", expires);
        },

        /**
         * 加载JS
         */
        loadJS : function(url,isDebug,isSync) {
            if (isSync==undefined||isSync==true) {
                loadJSForSync(isDebug, url);
            } else {
                loadJSForAsync(url);
            }
        },
        /**
         * 加载CSS
         */
        loadCSS : function(url,isDebug,isSync){
            if (isSync==undefined||isSync==true) {
                loadCSSForSync(isDebug, url);
            } else {
                alert("==");
                loadCSSForAsync(url);
            }
        },

        /**
         *创建命名空间
         */
        namespace:function(){
            var o, d;
            arguments.each(function(v) {
                d = v.split(".");
                o = window[d[0]] = window[d[0]] || {};
                Ext.each(d.slice(1), function(v2){
                    o = o[v2] = o[v2] || {};
                });
            });
            return o;
        },

        /**
         * 获取URL参数
         */
        getQueryString:function(name){
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        },
        registerService:function (name,src,debug){
            if(!project.wrapper){
                project.wrapper=new project.Wrapper();
            }
            project.wrapper.registerService(name,src, debug);
        },
        getService: function (name){
            if(!project.wrapper){
                project.wrapper=new project.Wrapper();
            }
            var service= project.wrapper.getService(name);
            return service;
        },
        /**
         * 获取唯一编码
         */
        getUniqueCode:function(){
            var id = setTimeout('0');
            clearTimeout(id);
            return id;
        },
        clone:function(obj) {
            var o;
            if (typeof obj == "object") {
                if (obj === null) {
                    o = null;
                } else {
                    if (obj instanceof Array) {
                        o = [];
                        for (var i = 0, len = obj.length; i < len; i++) {
                            o.push(this.clone(obj[i]));
                        }
                    } else {
                        o = {};
                        for (var j in obj) {
                            o[j] = this.clone(obj[j]);
                        }
                    }
                }
            } else {
                o = obj;
            }
            return o;
        },
        /**
         * post提交参数,页面跳转
         * $.sendPostArgs('url/path/req',{arg0:'arg0',arg1:'arg1'});
         */
        sendPostArgs:function(url,args){
            var formId= Duang.getUniqueCode();
            var body = $(document.body),
                form = $("<form id ='"+formId+"' method='post'></form>"),
                input;
            form.attr({"action":url});
            $.each(args,function(key,value){
                input = $("<input type='hidden'>");
                input.attr({"name":key});
                input.val(value);
                form.append(input);
            });
            form.appendTo(document.body);
            form.submit();
            $('#'+formId).remove();
        },
        copyProperties : function(destination, source) {
            for (var property in source) {
                destination[property] = source[property];
            }
            return destination;
        },
        inputFormat : function(self, digit){
            if(digit > 0){
                $(self).val(parseFloat($(self).val()).toFixed(digit));
            }else{
                $(self).val(parseInt($(self).val()).toFixed(digit));
            }
        },
        showMask : function(dom) {
            dom = dom || $("body");
            dom.append('<div id="maskDiv" style="width:100%; height:100%;background:#000;background:rgba(0,0,0,0.3); filter:Alpha(opacity=60);*zoom:1;top: 0px;left: 0px;z-index: 9999;position: fixed;">' +
                '<div class="chat-effect-container" style="position: fixed;top: 49%;left: 49%;"></div></div>');
            var $effectContainer=$(".chat-effect-container");
            var $dots=$("<div/>").addClass('chat-effect-dots').css({top : 20, left : 10}).appendTo($effectContainer);
            for (var i = 0; i < 3; i++) {
                var $dot=$("<div/>").addClass("chat-effect-dot").css({left : i * 20}).appendTo($dots);
                $dot.css({
                    'background' : "#32a8e6",
                    'width' : '15px',
                    'height' : '15px',
                    'border-radius': '100%',
                    'margin': '0 5px',
                    'float' : 'left'
                });

                TweenMax.to($dot,0.3,{
                    delay : -i * 0.1,
                    y : 30,
                    yoyo : true,
                    repeat : -1,
                    ease : Quad.easeInOut
                });
            }
        },
        removeMask : function(dom) {
            dom = dom || $("body");
            dom.find("#maskDiv").remove();
        },
        redirect: function(url, target){
            var $this = this;
            return {
                active: function(menuCode){
                    $this.setCookie('menuCode', menuCode);
                    return this;
                },
                execute: function(){
                    window.open(url, target || '_blank', '');
                }
            };
        },
        /**
         * 各种提示
         * @param titles
         * @param texts
         */
        error : function(title, content) {
            //错误提示信息
            toastr.options = {
                closeButton: true,
                progressBar: true,
                showMethod: 'slideDown',
                timeOut: 4000
            };
            toastr.error(content, title);
        },
        success : function(titles, texts) {
            //成功提示信息
            setTimeout(function() {
                toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    timeOut: 4000
                };
                toastr.success(texts,titles);

            }, 300);
        },
        info : function(titles, texts) {
            //正常提示信息
            setTimeout(function() {
                toastr.options = {
                    closeButton: true,  //关闭按钮
                    progressBar: true,	//下面进度提示信息
                    showMethod: 'slideDown', //显示方式
                    timeOut: 4000  //时间
                };
                toastr.info(texts,titles);

            }, 300);
        },
        /**
         * 弹出对话框基本类
         *
         * @param setting
         */
        getDefaultSetting: function () {
            return {
                title: '提示信息',
                content: [
                ],
                type: 'info',
                init: function () {
                }.bind(this),
                onclose: function () {
                    Duang.removeMask();
                    this.remove();
                },
                background: '#aaaaaa',
                icon: false,
                lock: true,
                top: 'goldenRatio',
                esc: false,
                zIndex:10000
            }
        },
        /**
         * setting 设置参数：
         * url: 提交地址
         * width: 提示宽度
         * height: 提示高度
         * title: 提示标题
         * content: 提示内容
         * type: 提交类型
         * data: 提交内容
         */
        showDialog: function (setting) {
            Duang.showMask();
            var $this = this;
            var dia = dialog(Object.merge(Duang.clone(this.getDefaultSetting()), setting));
            dia.show();
            if (setting && setting.url) {
                var html = [
                    '<div class="spinner">',
                    '<div class="double-bounce1"></div>',
                    '<div class="double-bounce2"></div>',
                    '</div>'
                ].join('\n');
                dia.content(html);
                dia.title(setting.title);
                dia.width(setting.width);
                dia.height(setting.height);
                $.ajax({
                    url: setting.url,
                    type: setting.type || 'post',
                    async: true,
                    data: setting.data || {
                    },
                    success: function (data) {
                        dia.content(data);
                        if (setting.success) {
                            setting.success.call(this, data);
                        }
                    },
                    error: function (data) {
                        if (setting.error) {
                            setting.error.call(this, data);
                        } else {
                            $this.error('sorry', '当前请求出现错误，请稍后再试');
                        }
                    }
                });
            } else {
                dia.content(setting.content);
                dia.title(setting.title || '提示信息');
                dia.width(setting.width || $(window).width() - 240);
                dia.height(setting.height || $(window).height() - 440);
            }
            return dia;
        }
    };
})();