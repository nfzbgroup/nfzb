$(function () {
    var parentDom = $(window.parent.document);
    function t(t) {
        var e = 0;
        return $(t).each(function() {
            e += $(this).outerWidth(!0)
        }),
            e
    }
    function e(e) {
        var a = t($(e).prevAll())
            , i = t($(e).nextAll())
            , n = t(parentDom.find(".content-tabs").children().not(".J_menuTabs"))
            , s = parentDom.find(".content-tabs").outerWidth(!0) - n
            , r = 0;
        if (parentDom.find(".page-tabs-content").outerWidth() < s)
            r = 0;
        else if (i <= s - $(e).outerWidth(!0) - $(e).next().outerWidth(!0)) {
            if (s - $(e).next().outerWidth(!0) > i) {
                r = a;
                for (var o = e; r - $(o).outerWidth() > $(".page-tabs-content").outerWidth() - s; )
                    r -= $(o).prev().outerWidth(),
                        o = $(o).prev()
            }
        } else
            a > s - $(e).outerWidth(!0) - $(e).prev().outerWidth(!0) && (r = a - $(e).prev().outerWidth(!0));
        parentDom.find(".page-tabs-content").animate({
            marginLeft: 0 - r + "px"
        }, "fast")
    }
    function newtab() {
        var t = $(this).attr("href")
            , a = $(this).data("index")
            , i = $.trim($(this).text())
            , n = !0;
        if (void 0 == t || 0 == $.trim(t).length)
            return !1;
        if ($(".J_menuTab").each(function() {
                return $(this).data("id") == t ? ($(this).hasClass("active") || ($(this).addClass("active").siblings(".J_menuTab").removeClass("active"),
                    e(this),
                    $(".J_mainContent .J_iframe").each(function() {
                        return $(this).data("id") == t ? ($(this).show().siblings(".J_iframe").hide(),
                            !1) : void 0
                    })),
                    n = !1,
                    !1) : void 0
            }),
                n) {
            var s = '<a href="javascript:;" class="active J_menuTab" data-id="' + t + '">' + i + ' <i class="fa fa-times-circle"></i></a>';
            parentDom.find(".J_menuTab").removeClass("active");
            var r = '<iframe class="J_iframe" name="iframe' + a + '" width="100%" height="100%" src="' + t + '" frameborder="0" data-id="' + t + '" seamless></iframe>';
            parentDom.find(".J_mainContent").find("iframe.J_iframe").hide().parents(".J_mainContent").append(r);
            var o = layer.load();
            parentDom.find(".J_mainContent iframe:visible").load(function() {
                layer.close(o)
            }),
                parentDom.find(".J_menuTabs .page-tabs-content").append(s),
                e($(".J_menuTab.active"))
        }
        return !1
    }
    $(".J_menuItem").each(function(t) {
        $(this).attr("data-index") || $(this).attr("data-index", t)
    });
    $(".J_menuItem").on("click", newtab);
});
