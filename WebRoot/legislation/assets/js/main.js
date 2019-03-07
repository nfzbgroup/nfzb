//顶部菜单切换
$('.nav-line li').click(function () {
    var t = $(this);
    if (t.hasClass('active')) {
        return false;
    }
    $('.nav-line li.active').removeClass('active');
    t.addClass('active');
    var cat = t.data('target');
    $('#side-menu>li').hide();
    $('#side-menu li[cat='+cat+']').show();
});

//顶部菜单可滑动
var navLineStart = 0;
$('.nav-line').on('touchstart', function (e) {
    navLineStart = e.originalEvent.targetTouches[0].pageX;
}).on('touchmove', function (e) {
    var x = e.originalEvent.targetTouches[0].pageX;
    var ml = $(this).css('margin-left');
    ml = ml.substr(0, ml.length - 2);
    ml = parseInt(ml) + (x - navLineStart);
    if (ml > 50 || ml < 5) return false;
    $(this).css('margin-left',ml);
    navLineStart = x;
});