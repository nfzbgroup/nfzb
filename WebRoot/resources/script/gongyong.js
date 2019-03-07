//在简单查询的条件等于0或""时，隐藏输入查询条件的输入框
//condName: 选择的字段的名字，如：condForm.condField
//hideName: 要隐藏的输入框的名字，如：condForm.condValue
//condField: 上次查询时选择的查询字段的值，如："docTitle"
//condValue: 上次查询时输入的查询的值，如："批示"
function hideInputTag(condName,hideName,condField,condValue){
	if(condName!=null && hideName!=null){
		if(condName.value=="" || condName.value=="0")
			hideName.style.display = "none";
		else{
			hideName.style.display = "block";
			if(condName.value==condField){
				hideName.value = condValue;
			}else{
				hideName.value = "";
			}
		}
	}
}
//打开导航栏(top.htm)
function openSitlist()
{
	window.open("sitlist.htm","displayWindow","toolbar=no,width=500,height=500,direclories=no,status=no,scrollbars=yes,resize=no,menubar=no");
}

//打开系统帮助(top.htm)
function openhelp()
{
	window.open("help.htm","displayWindow","toolbar=no,width=600,height=400,direclories=no,status=no,scrollbars=no,resize=no,menubar=no");
}

//控制form的宽度(show.htm)
function changel()
{
       if(window.top.f2.cols != "0,*")
        {
                window.top.f2.cols = "0,*";
                this.document.show.src="../image/open.gif";
                this.document.show.alt="显示菜单";
        }
        else{
                window.top.f2.cols = "120,*";
                 this.document.show.src="../image/close.gif";
                 this.document.show.alt="隐藏菜单";
        }


}

//在页面上添加子系统
function addLeader() 
{
	tbox = this.document.form1.selSystem;
	fbox = this.document.form1.allSystem;
	 
	if(fbox.selectedIndex < 0)
      	{
     		alert("请选择要添加的子系统！");
      		return;
      	}
      	
      	//var systemName = fbox.options[fbox.selectedIndex].text
    	//alert("systemName="+systemName);
      	
      	if(tbox.length >= 4)
      	{
      		alert("您最多可以选择四个子系统！");
      		return;
      	}
    	
    	for (var i = 0; i < tbox.length; i++)
      	{
      		if (tbox.options[i].text == fbox.options[fbox.selectedIndex].text)
        	{
        		alert("此子系统已被选择，请重新选择！")
        		return;
        	}
     	}
     	

	
	for(var i=0; i<fbox.options.length; i++)  
	{
		if(fbox.options[i].selected && fbox.options[i].value != "")  
	   	{
			
			// 增加项目列表到右侧
			var no = new Option();
			no.value = fbox.options[i].value;
			no.text = fbox.options[i].text;
			//alert(no.value);
			//alert(no.text);
			tbox.options[tbox.options.length] = no;

	
			//  清空左侧的项目列表
			fbox.options[i].value = "";
			fbox.options[i].text = "";
	     	}
	}
		BumpUp(fbox);
}




//在页面上移去子系统
function delLeader() 
{
   	tbox = this.document.form1.allSystem;
	fbox = this.document.form1.selSystem;
   	
   	if(fbox.selectedIndex < 0)
      	{
     		alert("请选择要移去的子系统！");
      		return;
      	}
      	
  	//var systemValue = fbox.options[fbox.selectedIndex].value;
  	//alert("systemValue="+systemValue);
  	
	var err = 0;
	for (var i = 0; i < tbox.length; i++)
	{
		if (tbox.options[i].value == fbox.options[fbox.selectedIndex].value){err=1;}
	}
	//alert("err="+err);
    	
	for(var i=0; i<fbox.options.length; i++)  
	{
		if(fbox.options[i].selected && fbox.options[i].value != "")  
	     	{
		       if(err==0)
			{
			        // 增加项目列表到右侧
				var no = new Option();
				no.value = fbox.options[i].value;
			    	textTemp = fbox.options[i].text;
			   	no.text = textTemp;
			   	//alert(no.value);
				//alert(no.text);
				tbox.options[tbox.options.length] = no;
			}				
			
			//  清空左侧的项目列表
	        	fbox.options[i].value = "";
	        	fbox.options[i].text = "";
     		}
     		
   	}
   	BumpUp(fbox);
}

//清空列表
function BumpUp(box)  
{
  	for(var i=0; i<box.options.length; i++)  
  	{
     		if(box.options[i].value == "")  
     		{
       			for(var j=i; j<box.options.length-1; j++)  
       			{
				box.options[j].value = box.options[j+1].value
			       	box.options[j].text = box.options[j+1].text
       			}
       			var ln = i
       			break
     		}
   	}
   	if(ln < box.options.length)
   	{
     		box.options.length -= 1;
     		BumpUp(box);
   	}
}

//如窗口没有打开，则打开窗口；如已经打开，则使其获得焦点
function OpenWindow(url,name){
    openWindow=window.open(url,name,'width=795,height=595,left=0,top=0,scrollbars=yes,resizable=yes');
    openWindow.focus();
}
/* FUNCTION---------------------------------------------refurbish
功能：对页面进行刷新操作，去除New标识。
参数：flag ： true 已读； flase 未读。
      url：   查看object的url。
*/
function refurbish(url,flag){
  OpenWindow(url,'doc');
  var i = 0 ;
  if(flag == "false"){
  	while(i < 10000 ){i++;}
  	window.location.reload();
  	}
  }
 
function show_op(id){
	var obj = eval("this.op_"+id);
	if(obj.style.display=="none")
		obj.style.display = "inline";
	else
		obj.style.display = "none";		
}
