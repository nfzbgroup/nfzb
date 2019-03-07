package com.wonders.fzb.platform.kit;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;



/**
 * 
 * @author liujun
 *
 */
public class DbHelper {
	
	private static final Logger log = Logger.getLogger(DbHelper.class);
	
	//行政行为的，其它的也可以放下面。也可以用bean的注解获取这个值。
	public static Map<String,String> propetyBehaviorMap=new HashMap<String,String>();
	/**
	 * 将数据库列名转换为类中的属性名，用于代码生成（如ST_USER_NAME变成stUserName）
	 * @param columnName
	 * @return 转换后的属性名
	 */
	public static String transColumnName(String columnName){
		if(columnName==null || "".equals(columnName)) return "";
		String mapValue=propetyBehaviorMap.get(columnName);
		if(mapValue!=null && !"".equals(mapValue) && !"null".equals(mapValue)){
			log.debug("Get value from cahce Map...");
			//System.out.println("**************");System.out.println(mapValue);System.out.println("**************");
			return propetyBehaviorMap.get(columnName);
		}
		if("NM_ID".equals(columnName))
		{
			return "id";//PK固定为id
		}
		String columnTransName = "";
		StringBuffer columnTransNameBuffer = new StringBuffer();
		if(columnName!=null&&!"".equals(columnName)){
			columnName = columnName.toLowerCase().replace(" ", "");
			String columnSplitNameArray[] = columnName.split("_");
			for(int i=0;i<columnSplitNameArray.length;i++){
				if(columnSplitNameArray[i]!=null&&!"".equals(columnSplitNameArray[i])){
					//将_分割后的首字母大写并拼接
					columnTransNameBuffer.append(Character.toUpperCase(columnSplitNameArray[i].charAt(0))+columnSplitNameArray[i].substring(1));
				}
			}
			//将首字母小写
			columnTransName = Character.toLowerCase(columnTransNameBuffer.charAt(0))+columnTransNameBuffer.substring(1);
		}
		//System.out.println(columnName+" - "+columnTransName);
		propetyBehaviorMap.put(columnName, columnTransName);
		return columnTransName;
	}
}
