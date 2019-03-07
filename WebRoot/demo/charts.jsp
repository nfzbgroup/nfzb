<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/common/header.jsp" />
        <title>图表</title>
    </head>
    <body>
    		 <div id="main" style="width: 1400px;height:750px;"></div>
    </body>
    <jsp:include page="/common/scripts.jsp" />
<script language="javascript">
$.include(['echarts']);
</script>

<script language="javascript">
$(function(){
var myChart = echarts.init(document.getElementById('main'));

option = {
    title: {
        text: '卫计委2015年统计',
        subtext: '行政处罚 - 行政处罚种类统计'
    },
    tooltip : {
        trigger: 'axis',
        formatter:function (params, ticket, callback){
        	console.info(params);
        	var index = 0;
        	var result = "<b>" + params[index].name + "</b> <br /> ";
        	$.each(params,function(i){
        		var param = params[i];
        			
        		result += param.seriesName + ":" + param.value;
        		
        		if(index > 0 && index % 2 == 1){
        			result += "<br />";
        		}else{
        			result += "，";
        		}
        		index++;
        	});
        	return result;
        },
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    xAxis: {
        data: ['2015年上半年','2015年下半年'],
        type : 'category',
        axisLabel :{
    		interval:0   
		}  
    },
    yAxis : [
        {
            type : 'value'
        }
    ],
    legend: {
    	padding:10,
    	borderWidth:1,
        data:['案件总数（市级）','案件总数（区县）','警告（市级）','警告（区县）','罚款次数（市级）','罚款次数（区县）','\n','没收违法所得次数（市级）','没收违法所得次数（区县）','没收非法财物次数（市级）','没收非法财物次数（区县）']
    },
    series : [
    	{
    		name:'案件总数（市级）',
    		type:'bar',
    		stack:'案件总数',
    		label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
    		data:[145,182]
    	},
    	{
    		name:'案件总数（区县）',
    		type:'bar',
    		stack:'案件总数',
    		label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
    		data:[2858,3568]
    	},
    	{
    		name:'警告（市级）',
    		type:'bar',
    		stack:'警告',
    		label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
    		data:[93,109]
    	},
    	{
    		name:'警告（区县）',
    		type:'bar',
    		stack:'警告',
    		label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
    		data:[1511,1970]
    	},
    	{
    		name:'罚款次数（市级）',
    		type:'bar',
    		stack:'罚款次数',
    		label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
    		data:[109,141]
    	},
    	{
    		name:'罚款次数（区县）',
    		type:'bar',
    		stack:'罚款次数',
    		label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
    		data:[2275,2874]
    	},
    	{
    		name:'没收违法所得次数（市级）',
    		type:'bar',
    		stack:'没收违法所得次数',
    		label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
    		data:[5,7]
    	},
    	{
    		name:'没收违法所得次数（区县）',
    		type:'bar',
    		stack:'没收违法所得次数',
    		label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
    		data:[44,72]
    	},
    	{
    		name:'没收非法财物次数（市级）',
    		type:'bar',
    		stack:'没收非法财物次数',
    		label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
    		data:[0,0]
    	},
    	{
    		name:'没收非法财物次数（区县）',
    		type:'bar',
    		stack:'没收非法财物次数',
    		label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
    		data:[151,134]
    	}
    ]
};
	 // 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
});
	</script>
</html>