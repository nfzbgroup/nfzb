(function($) {
    $.fn.extend({
        IMGDEMO:function(opt) {

            var opt 	= arguments[0] ? arguments[0] :false;
            var $button = $(this).children("li")	;			//容器;
            var $sec 	= 5000						; 			//自动播放默认时间;
            var $min 	= $button.last().width() 	;			//最小宽度;
            var $max 	= $button.first().width() 	;			//最大宽度;
            var $index 	= 2   						;			//轮播开始索引号;

            $default = {								        //默认参数;todo 网上找的插件，此处变量未声明会污染全局
                speed	:	opt.speed 	? 	opt.speed 						:	"slow"	,
                by		:	opt.by 		? 	opt.by 							:	"click"	,
                auto	:	opt.auto 	? 	opt.auto						:	false	,
                sec		:	opt.sec 	? 	opt.sec < 1000 ? 1000 : opt.sec	:	$sec	,
                maxWidth:	opt.maxWidth?   opt.maxWidth                    :   $max	,
                minWidth:	opt.minWidth()?   opt.minWidth()                    :   $min	,
                index	:	opt.index   ?   opt.index                       :   $index
            };
            $button.bind($default.by, this.run).autoPlay();		//绑定事件;
        },
        run:function() {										//运行方法;
            var $obj = $(this);
            if ($obj.width() == $default.minWidth) {
                var timeDo = window.setTimeout(function() {
                    $default.index = $obj.index();
                    $obj.anim();
                }, 100);
                $obj.mouseout(function() {
                    window.clearTimeout(timeDo);
                });
            }
        },
        autoPlay:function() {									//自动播放;
            if ($default.auto) {
                var $this = $(this);
                $this.autoDo();
                $this.mouseover(function() {
                    window.clearInterval(timeL);
                });
                $this.mouseout(function() {
                    $this.autoDo();
                });
            }
        },
        autoDo:function() {										//播放方法;
            var $len 	= 	$(this).length - 1;
            var $this 	= 	$(this);
            timeL 	= 	window.setInterval(function() {
                $this.eq($default.index).anim();
                $default.index < $len ? $default.index++ :$default.index = 0;
            }, $default.sec);
        },
        anim:function() {										//动画方法;
            var $fx = function() {
                $(this).siblings("li").removeClass('active').animate({
                    width	:	$default.minWidth,
                    opacity	:	1
                }, $default.speed).css("cursor", "pointer");
                $(this).addClass('active').animate({
                    width	:	$default.maxWidth,
                    opacity	:	1
                }, $default.speed).css("cursor", "default");

                $(this).siblings("li").children(".customAccordion-title,.customAccordion-btn").fadeOut();
                $(this).children("div").fadeTo($default.speed,1);
                $(this).dequeue();
            };
            $(this).queue($fx);
        }
    });
})(jQuery);