<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
	var message = '${message}';
	if(message != ''){
		alert(message);
		parent.callBack();

	}
	
	var lock =  null;
	function checkLock(){
		if(lock != null){
			alert('操作太快休息一下！');
			return true;
		}
		lock = "1";
		setInterval(function (){
			lock =  null;
		}, 3000);
		return false;
	}
</script>