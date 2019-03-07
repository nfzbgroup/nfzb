/*
 * 模块化管理JS组件，需要的页面自行添加，不混乱页面
 * 2016年6月22日 17:21:05
 * ZSW
 */


/**
 * 模块
 * tree     是树状图
 * treeEX 是树状图的扩展包
 * my97   是日期选择控件
 */
var modules = {
		"expandable":["expandable/css/bootstrap-table-expandable.css","expandable/js/bootstrap-table-expandable.js"],
		"datepicker":["datepicker/bootstrap-datetimepicker.min.css","datepicker/bootstrap-datetimepicker.min.js","datepicker/bootstrap-datetimepicker.zh-CN.js"],
		"easysearch":["easysearch/jquery.easysearch.min.js"],
		"highlight":["highlight/css/highlight.css","highlight/scripts/highlight.js"],
		"tree":["tree/jquery.ztree.core-3.5.min.js","tree/zTreeStyle.css"],
		"treeEX":["tree/jquery.ztree.excheck-3.5.min.js","tree/jquery.ztree.exedit-3.5.min.js"],
		"my97":["My97DatePicker/WdatePicker.js"],
		"webcam":["webcam/jquery.webcam.min.js"],
		"echarts":["echarts/echarts.js"]
};

$.extend({
     includePath: '',
     include: function(module) {
    	 if(null == module || '' == module || module.length == 0) return;
    	 
    	 var includeModule = (typeof module == "string") ? [module] : module;
    	 for(var z = 0; z < includeModule.length; z++){
    		 var file = modules[includeModule[z]];
        	 if(file == null || file == "") return;
        	 var files = (typeof file == "string") ? [file] : file;
        	 for (var i = 0; i < files.length; i++) {
                var name = files[i].replace(/^\s|\s$/g, "");
                var att = name.split('.');
                var ext = att[att.length - 1].toLowerCase();
                var isCSS = ext == "css";
                var tag = isCSS ? "link" : "script";
                var link = (isCSS ? "href" : "src") + "='" + $.includePath + name + "'";
                
                if ($(tag + "[" + link + "]").length == 0) {
                	if(!isCSS){
                		document.write("<script  language='javascript' src='"+$.includePath + name+"'></script>");
                	}else{
                		var element = document.createElement("link");
                		element.type = "text/css";
                		element.rel = 'stylesheet';
                		element.href = $.includePath + name;
                		document.getElementsByTagName("head")[0].appendChild(element);
                	}
                }
        	 }
    	 }
     }
});