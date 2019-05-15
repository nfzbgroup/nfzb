//fullPageList(128,1,10,'listPage');

function fullPageList(count,nowPage,pageSize,container) {
    var countPage = Math.ceil(count / pageSize);
    var nowPage = parseInt(nowPage);
    if (nowPage > countPage) {
        nowPage = countPage;
    }
    if (nowPage < 1) {
        nowPage = 1;
    }
    var prePage = nowPage - 1;
    var nextPage = nowPage + 1;
    var allCount = count;

    var pageHtml = '';
    pageHtml += '<div class="page-one fl">';
    if (nowPage == 1) {
        pageHtml += '<div class="fl page-one-left" onclick="javascript:void(0)"></div>';
    } else {
        pageHtml +='<div class="fl page-one-left" onclick="submitForm('+prePage+')"></div>';
    }
    pageHtml +='<div class="fl page-one-mid">';
    if (nowPage>1){
        pageHtml+='<a href="javascript:submitForm('+prePage+')">'+prePage+'</a>';
    }
    pageHtml += '<a class="current">' + nowPage + '</a>';
    if ((countPage - nextPage) >= 1) {
        pageHtml +='<a href="javascript:submitForm('+nextPage+')">' + nextPage + '</a>';
    }
    if ((countPage - nextPage) >= 2) {
        pageHtml +='<span class="page-ellipse">... </span>';
    }
    if (nowPage<countPage){
        pageHtml+='<a href="javascript:submitForm('+countPage+')">'+countPage+'</a>';
    }
    pageHtml += '</div>';
    if (nowPage == countPage) {
        pageHtml += '<div class="fl page-one-right" onclick="javascript:void(0)"></div>';
    } else {
        pageHtml += '<div class="fl page-one-right" onclick="submitForm('+nextPage+')"></div>';
    }
    pageHtml += '</div>';
    pageHtml += '<div class="page-two fr">';
    pageHtml += '<span>共</span> <i>'+ allCount +'</i> </span>条 , 跳转第</span>';
    pageHtml += '<input type="text" name="jumpPage" class="only-num" autocomplete="off" />';
    pageHtml += '<span class="mr10 ml10">页</span>';
    pageHtml += '<input type="button" onclick="javascript:jumpPage(this,'+countPage+','+nowPage+')" value="确定"/>';
    pageHtml += '</div>';

    $('#'+container).html(pageHtml);
    $("input[name='jumpPage']").positiveSet();
}

function jumpPage(obj,countPage,nowPage) {
    var toPage = $(obj).siblings("input[name='jumpPage']").val();
    if(toPage>countPage){
        toPage=countPage
    }
    if(toPage==0){
    	toPage=1;
    }
    if(toPage!=nowPage){
        submitForm(toPage)
    }

}

function submitForm(pageNo) {
    var currentWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var position=currentWwwPath.indexOf(pathName);
    var localhostPath=currentWwwPath.substring(0,position);

    $.post(localhostPath+$('#requestUrl').val()+"?stNodeId="+$('#stNodeId').val()+"&method=queryTable",{
        pageNo:pageNo,
        taskStatus:$('#taskStatus').val(),
        startTime:$('#startTime').val(),
        endTime:$('#endTime').val(),
        stDocName:$('#stDocName').val(),
        stUserName:$('#stUserName').val(),
        title:$('#title').val(),
        address:$('#address').val(),
        opinionType:$('#opinionType').val(),
        stPlanName:$('#stPlanName').val()
    },function(data){
        $('#legislationProcessTaskTable').html(data);
    });
}