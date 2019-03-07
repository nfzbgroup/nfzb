package com.wonders.fzb.base.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.logging.beans.LogInfo;

/**
 * 翻页自定义标签
 */
public class PagingTag extends TagSupport {

	private static final long serialVersionUID = 2313013371880308393L;
	
	private String attrName;
	
	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int doEndTag() throws JspException {
		StringBuffer htmlBuffer = new StringBuffer("");
		
		if(null != pageContext.getRequest().getAttribute(attrName)){
			Page<LogInfo> page = (Page<LogInfo>)pageContext.getRequest().getAttribute(attrName);
			int pageStart = ((page.getCurrentPageNo() - 4 <= 1) ? 1 : (page.getCurrentPageNo() - 4));
			
			htmlBuffer.append("<div class='text-center'><ul class='pagination'>");
			
			if(page.getCurrentPageNo() == 1)
				htmlBuffer.append("<li class='disabled'>");
			else
				htmlBuffer.append("<li>");
			htmlBuffer.append("<a href='javascript:void(0)' pageNo='"+(page.getCurrentPageNo() - 1)+"'>&laquo;</a></li>");
			
			if(page.getCurrentPageNo() - 1 > 4){
				htmlBuffer.append("<li><a href='javascript:void(0)'  pageNo='1'>1</a></li>");
				if(page.getCurrentPageNo() - 1 > 5)
					htmlBuffer.append("<li class='disabled'><a href='#'>...</a></li>");
			}
			
			for(int i=0;i<10;i++){
				int index = pageStart + i;
				if(page.getCurrentPageNo() == index)
					htmlBuffer.append("<li class='active'>");
				else
					htmlBuffer.append("<li>");
				htmlBuffer.append("<a href='javascript:void(0);' pageNo='"+index+"'>"+index+"</a></li>");
				if(index == page.getTotalPageCount())
					break;
			}
			
			if(page.getTotalPageCount() - page.getCurrentPageNo() > 5){
				htmlBuffer.append("<li class='disabled'><a href='#'>...</a></li>");
				htmlBuffer.append("<li><a href='javascript:void(0)'  pageNo='"+page.getTotalPageCount()+"'>"+page.getTotalPageCount()+"</a></li>");
			}
			
			if(page.getTotalPageCount() == page.getCurrentPageNo())
				htmlBuffer.append("<li class='disabled'>");
			else
				htmlBuffer.append("<li>");
			htmlBuffer.append("<a href='javascript:void(0)' pageNo='"+(page.getCurrentPageNo() + 1)+"'>&raquo;</a></li>");			
			
			htmlBuffer.append("</ul></div>");
		}
		JspWriter out = pageContext.getOut();
		try {
			out.print(htmlBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doEndTag();
	}
}
