package com.wonders.fzb.tools.scaffold;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * 根据数据库信息生成相关的Bean，Dao,Service,Action的scaffold代码。
 * @author lj
 *
 */
public class FreeMarker {
	
	private Template template;
	
	/**
	 * 构造方法 读取模板
	 * @param templateDir 模板文件存放路径
	 * @param templateFile 模板文件名
	 * @throws IOException
	 */
	public FreeMarker(String templateDir, String templateName) throws IOException{
		Configuration cfg;
		File file = new File(templateDir);
		if(file.exists()){
			cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(file);
			cfg.setDefaultEncoding("UTF-8");
			template = cfg.getTemplate(templateName);
		}
	}
	
	/**
	 * 根据模板生成代码文件
	 * @param dataMap
	 * @param os
	 * @param encoding 字符编码
	 * @throws Exception
	 */
	public void write(Map<String, Object> dataMap, OutputStream os, String encoding){
		OutputStreamWriter osw = null;
		try {
			if(template != null){
				template.setEncoding(encoding);
				osw = new OutputStreamWriter(os, encoding);
				template.process(dataMap, osw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(osw != null){
				try {
					osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			osw = null;
		}
	}

}
