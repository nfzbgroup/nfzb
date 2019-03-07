'设置Excel中命名过的Range的值
'@param workSheet Excel中WorkSheet对象
'@param tagName Range的名字
'@param tagValue Range中的值
Sub replaceTagExcel( workSheet , tagName , tagValue )
   set tagRange = workSheet.Range( tagName ) 
   tagRange.Value = tagValue 
End Sub

'用页面中的表格替换Excel中命名过的Range
'@param workSheet Excel中WorkSheet对象
'@param tagName Range的名字
'@param table 页面中的Table对象
'@param startRow Table开始用于替换的行号（行号从0零开始）
'@param endRow Table开始用于替换的行号（最大为Table的行数-1）
'@param startCol Table开始用于替换的列号（列号从0零开始）
'@param endCol Table开始用于替换的列号（最大为Table的列数-1）
Sub replaceLoopTagExcel( workSheet , tagName , table , startRow , endRow , startCol , endCol )
  set tagRange = workSheet.Range( tagName ) 
  dim loopRange 
	set loopRange = tagRange
	for i= startRow to  endRow
	   for j=startCol to  endCol
		loopRange(1 , j ).value = table.rows(i).cells(j).innerText    	
	   next
        if ( i <> endRow ) then 
        	tagRange.copy
		loopRange.insert 2
	end if
        next
End Sub

'替换Word文件中的应用替换符
'@param document Word中的document对象
'@param tagName 替换符的名字
'@param tagValue 替换符的值
Sub replaceTag(document , tagName , tagValue )
    Dim replaceRange 
    
    Set replaceRange = document.Content
    replaceRange.Find.Execute tagName, , , , , , True

    If (replaceRange.Find.Found = True) Then
        replaceRange.Text = tagValue
    End If
End Sub


'循环替换Word中的应用替换符
'循环替换是指替换在【Start】和【End】两个循环控制符之间应用替换符的值
'@param document Word中的document对象
'@param startTagName 循环起始标识
'@param endTagName 循环结束标识
'@param tagNames 替换符的名字的数组
'@param tagValue 替换符的值的数组（个数需要保持和名字数组一致）
'@param isLastOne 是否是最后一组的循环替换，为布尔变量(True|False)
Sub replaceLoopTag(document , startTagName , endTagName , tagNames, tagValues, isLastOne )
    Dim copyRange 
    Dim startRange
    Dim endRange 
    Dim replaceRange 
    Dim pastePos 
    pastePos = 0

    Set startRange = document.Content
    startRange.Find.Execute startTagName, , , , , , True
    Set endRange = document.Content
    endRange.Find.Execute endTagName, , , , , , True
    
    If ((startRange.Find.Found = True) And (endRange.Find.Found = True)) Then
        Set copyRange = document.Range(startRange.Start, endRange.End)
        copyRange.Copy
        startRange.Text = ""
    End If
    
    For i = 0 To UBound(tagNames) - 1
        Set replaceRange = document.Content
        replaceRange.Find.Execute tagNames(i)
        If (replaceRange.Find.Found = True) Then
            replaceRange.Text = tagValues(i)
        End If
    Next
           Dim pasteRange 
        Set pasteRange = document.Content
        pasteRange.Find.Execute endTagName, , , , , , True
    
    If (pasteRange.Find.Found = True) Then
        If (isLastOne <> True) Then
                pasteRange.Paste
        Else
                pasteRange.Text = ""
        End If
    End If
End Sub

'将两个Tag之间的内容清空（包括StartTag和EndTag本身）
'@param document Word中的document对象
'@param startTagName 起始标识
'@param endTagName 结束标识
'@param mode 清除模式 
'            1 将两个Tag之间的内容清空（包括StartTag和EndTag本身）
'            2 将两个Tag清空（不包括StartTag和EndTag本身）
'            3 将两个Tag清空
Sub clearTags(document , startTagName , endTagName , mode)
    Dim startRange
    Dim endRange 
    Dim clearRange
    
    Set startRange = document.Content
    startRange.Find.Execute startTagName, , , , , , True
    Set endRange = document.Content
    endRange.Find.Execute endTagName, , , , , , True
    If ( mode = 1 ) Then 
    	Set clearRange = document.Range( startRange.Start , endRange.End)
    	clearRange.Text = "" 
    ElseIf ( mode = 2 ) Then 
    	Set clearRange = document.Range( startRange.End , endRange.Start)
    	clearRange.Text = "" 
    Else
    	startRange.Text = "" 
	endRange.Text = "" 
    End If	
End Sub 
