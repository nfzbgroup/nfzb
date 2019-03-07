	function addOnclick() 
	{
		tbox = this.document.form1.selectedFile;
		fbox = this.document.form1.allFile;
		
		 
		if(fbox.selectedIndex < 0)
	  {
	   	alert("请选择要添加项！");
	  	return;
	  }
	  var selectValue = fbox.options[fbox.selectedIndex].value;
	  for (var i = 0; i < tbox.length; i++)
	  {
	    if (tbox.options[i].value == selectValue)
	    {
	      alert("该组中已存此参数在，请重新选择参数！");
	      //alert("i="+i);
	      fbox.options[fbox.selectedIndex].value = "";
				fbox.options[fbox.selectedIndex].text = "";
				BumpUp(fbox);
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
			  //alert(no.selected);
				//alert(no.text);
				tbox.options[tbox.options.length] = no;
		
				//  清空左侧的项目列表
				fbox.options[i].value = "";
				fbox.options[i].text = "";
			}
		}
		BumpUp(fbox);	   	
	}
	
	
	//在页面上移去关联参数
	function delOnclick() 
	{
		tbox = this.document.form1.allFile;
		fbox = this.document.form1.selectedFile;
		
		if(fbox.selectedIndex < 0)
	  {
	    alert("请选择要移去项！");
	    return;
	  }
	      	
	  var selectValue = fbox.options[fbox.selectedIndex].value;
		for (var i = 0; i < tbox.length; i++)
	  {
	    if (tbox.options[i].value == selectValue)
	    {
	      //alert("该组中已存此参数在，请重新选择参数！");
	      //alert("i="+i);
	      fbox.options[fbox.selectedIndex].value = "";
				fbox.options[fbox.selectedIndex].text = "";
				BumpUp(fbox);
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
			  tbox.options[tbox.options.length] = no;
					
			  //清空左侧的项目列表
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
	
	//上移
	function upOnclick()
	{
		//alert("bbb");
		box = this.document.form1.selectedFile;
		if(box.selectedIndex < 0)
	  {
	    alert("请选择要移动的项！");
	    return;
	  }		
		if(box.selectedIndex==0)
		{
			alert("此选项的优先级已经最高！");
	   	return;
		}
		else
		{
			var i = box.selectedIndex;
			var selText = box.options[i].text;
			var selValue = box.options[i].value;
			box.options[i].text = box.options[i-1].text;
			box.options[i].value = box.options[i-1].value;
			
			box.options[i-1].text = selText;
			box.options[i-1].value = selValue;
			box.options[i-1].selected = true;
		}
	}
	
	//下移
	function downOnclick()
	{
		box = this.document.form1.selectedFile;
		if(box.selectedIndex < 0)
	  {
	    alert("请选择要移动的项！");
	    return;
	  }
	  if(box.selectedIndex == box.options.length-1)
		{
			alert("此选项的优先级已经最低！");
	    return;
		}
		else
		{
			var i = box.selectedIndex;
			var selText = box.options[i].text;
			var selValue = box.options[i].value;
			box.options[i].text = box.options[i+1].text;
			box.options[i].value = box.options[i+1].value;
			
			box.options[i+1].text = selText;
			box.options[i+1].value = selValue;
			box.options[i+1].selected = true;
			box.selectedIndex++;
		}		
	}