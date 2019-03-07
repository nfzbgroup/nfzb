function ToXlsFile(r,c)
	dim rowno
	dim colno

	if r=0 then
		rowno=window.XLSTbl.rows.length
	else
		rowno=r
	end if

	if c=0 then
		colno=window.XLSTbl.rows(0).cells.length
	else
		colno=c
	end if

	Dim table(1000,100)

	For i = 1 To rowno
		For j = 1 To colno
			table(i-1,j-1)=window.XLSTbl.rows(i-1).cells(j-1).innerText
		Next
	Next

	'创建Excel对象
	Set objExcelDoc = CreateObject("Excel.Application")
	Set workbook = objExcelDoc.Workbooks.Add
	objExcelDoc.Application.Visible = true 
	For i = 1 To rowno
		
		For j = 1 To colno
			Set cellRange = workbook.ActiveSheet.Cells(i, j)
			//cellRange.Select
			//Selection.NumberFormatLocal = "@"
			cellRange.Value = table(i - 1, j - 1)

			'设置单元格格式
			cellRange.HorizontalAlignment=3
			cellRange.VerticalAlignment=2
			cellRange.Font.Size = 10
		
			
			'cellRange.WrapText = True
			
			
			
		Next
	Next

	'调整列宽到最适合列宽
	Set c1 = workbook.ActiveSheet.Cells(1, 1)
	Set c2 = workbook.ActiveSheet.Cells(5, 8)
	workbook.ActiveSheet.Range(c1, c2).Columns.AutoFit

end function