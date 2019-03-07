/** FUNCTION---------------------------------------------
功能：
	将html form的域值拷贝到Word文档的书签中
*/
function copyInputToBookMark(ocxObj,inputName,bookMarkName)
{
	try
	{	
		var inputValue="";
		var j,elObj,optionItem;
		var elObj = document.forms[0].elements(inputName);		 
		if (!elObj)
		{
			alert("HTML的FORM中没有此输入域："+ inputName);
			return;
		}
		switch(elObj.type)
		{
				case "select-one":
					inputValue = elObj.options[elObj.selectedIndex].text;
					break;
				case "select-multiple":
					var isFirst = true;
					for(j=0;j<elObj.options.length;j++)
					{
						optionItem = elObj.options[j];					
						if (optionItem.selected)
						{
							if(isFirst)
							{
								inputValue = optionItem.text;
								isFirst = false;
							}
							else
							{
								inputValue += "  " + optionItem.text;
							}
						}
					}
					
					break;
				default: // text,Areatext,selecte-one,password,submit,etc.
					inputValue = elObj.value;
					break;
		}
		//do copy
		//DEBUG
		//if(inputName=='Report_textContent')
		//	alert(inputName+"="+inputValue+" bookmarkname="+bookMarkName);
		var bkmkObj = ocxObj.ActiveDocument.BookMarks(bookMarkName);	
		if(!bkmkObj)
		{
			alert("Word 模板中不存在名称为：\""+bookMarkName+"\"的书签！");
		}
		var saverange = bkmkObj.Range
		inputValue = inputValue.replace(new RegExp("[\$][\$]","gm"),"\r\n");
		//if(inputName=='Report_textContent')
		//	alert(inputName+"="+inputValue);
		saverange.Text = inputValue;
		ocxObj.ActiveDocument.Bookmarks.Add(bookMarkName,saverange);
	}
	catch(err){
	}finally{
	}		
}

/** FUNCTION---------------------------------------------
功能：

	将Word文档的书签值拷贝到html form的域中
*/
function copyBookMarkToInput(ocxObj,inputName,bookMarkName)
{
	try
	{	
		var inputValue="";
		var j,elObj,optionItem;
		var elObj = document.forms[0].elements(inputName);	//alert(inputName);	 
		if (!elObj)
		{
			alert("HTML的FORM中没有此输入域："+ inputName);
			return;
		}
		var bkmkObj = ocxObj.ActiveDocument.BookMarks(bookMarkName);	
		if(!bkmkObj)
		{
			alert("Word 模板中不存在名称为：\""+bookMarkName+"\"的书签！");
		}
		var saverange = bkmkObj.Range
		inputValue = saverange.Text;
		inputValue = inputValue.replace(new RegExp("[\r]","gm"),"$$$$");
		inputValue = inputValue.replace(new RegExp("[\n]","gm"),"");
		//if(inputName=='Report_textContent')
		//	alert(inputValue);	 
		switch(elObj.type)
		{
				case "select-one":
					for(j=0;j<elObj.options.length;j++)
					{
						optionItem = elObj.options[j];
						if(optionItem.text==inputValue){
							optionItem.selected = true;
						}
					}
					break;
				case "select-multiple":
					for(j=0;j<elObj.options.length;j++)
					{
						optionItem = elObj.options[j];	
						if(inputValue.indexOf(optionItem.text)>=0){
							optionItem.selected = true;
						}
					}
					
					break;
				default: // text,Areatext,selecte-one,password,submit,etc.
					elObj.value = inputValue;
					break;
		}
		//alert(elObj.value);
		//do copy
		//DEBUG
		//alert(inputName+"="+inputValue+" bookmarkname="+bookMarkName);
	}
	catch(err){
	}finally{
	}		
}
