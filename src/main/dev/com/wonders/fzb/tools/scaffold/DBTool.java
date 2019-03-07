package com.wonders.fzb.tools.scaffold;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;


/**
 * 根据数据库信息生成相关的Bean，Dao,Service,Action的scaffold代码。
 * @author lj
 *
 */
public class DBTool {
	static String DbDriver="oracle.jdbc.driver.OracleDriver";
	static String DbConnStr="jdbc:oracle:thin:@192.168.168.146:1521:fzb";
	static String DbUser="fzb";
	static String DbPassword="Abcde_12345";
	/**   
	 * 根据表名生成bean类
	 * @param tableName 数据库表名
	 * @param basePackage 所需要生成的类的包路径，如此bean类需要生成在src/com/wondersgroup/beans包下，那么只需要传入com/wondersgroup
	 */
	public static void generateBeanByTableName(String tableName, String basePackage, String sequence, String setBizIdMathodName, String prefixId){
		DBTable table = getTableInfo(tableName,sequence,setBizIdMathodName,prefixId);
		if(table == null){
			throw new NullPointerException("找不到名称为" + tableName + "的表");
		}
		generateBeanByTable(table, basePackage);
		System.out.println("数据库表" + tableName + "的bean.dao.service.action类已生成");
	}
	
	
	private static void generateBeanByTable(DBTable table, String basePackage){
		OutputStream os = null;
		try {
			if(table == null){
				throw new NullPointerException("table不能为null");
			}
			os = getOutputStream("src/"+ basePackage + "/beans/" + table.getTableTransName() + ".java");
			FreeMarker marker = new FreeMarker("src/main/com/wonders/fzb/tools/scaffold", "bean.ftl");
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("packageStr", basePackage.replace("/", "."));
			dataMap.put("table", table);
			marker.write(dataMap, os, "UTF-8");
			
//			os = getOutputStream("src/"+ basePackage + "/dao/" + table.getTableTransName() + "Dao.java");
//			marker = new FreeMarker("src/main/com/wonders/fzb/tools/scaffold", "beanDao.ftl");
//			marker.write(dataMap, os, "UTF-8");
//			
//			os = getOutputStream("src/"+ basePackage + "/dao/impl/" + table.getTableTransName() + "DaoImpl.java");
//			marker = new FreeMarker("src/main/com/wonders/fzb/tools/scaffold", "beanDaoImpl.ftl");
//			marker.write(dataMap, os, "UTF-8");
//			
//			os = getOutputStream("src/"+ basePackage + "/services/" + table.getTableTransName() + "Service.java");
//			marker = new FreeMarker("src/main/com/wonders/fzb/tools/scaffold", "beanService.ftl");
//			marker.write(dataMap, os, "UTF-8");
//			
//			os = getOutputStream("src/"+ basePackage + "/services/impl/" + table.getTableTransName() + "ServiceImpl.java");
//			marker = new FreeMarker("src/main/com/wonders/fzb/tools/scaffold", "beanServiceImpl.ftl");
//			marker.write(dataMap, os, "UTF-8");
//			
//			os = getOutputStream("src/"+ basePackage + "/web/" + table.getTableTransName() + "Action.java");
//			marker = new FreeMarker("src/main/com/wonders/fzb/tools/scaffold", "beanAction.ftl");
//			marker.write(dataMap, os, "UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			os = null;
		}
	}
	
	/**
	 * 获得可读取comment的JDBC数据库连接（for oracle）
	 * @param jdbc_driver
	 * @param jdbc_conn_str
	 * @param jdbc_user
	 * @param jdbc_password
	 * @return
	 * @throws Exception
	 */
	private static Connection getRemarksConnection(String jdbc_driver, String jdbc_conn_str, String jdbc_user, String jdbc_password) throws Exception{
    	Class.forName(jdbc_driver);
    	Properties props = new Properties();
    	props.put("remarksReporting","true");
    	props.put("user", jdbc_user);
    	props.put("password", jdbc_password);
    	return DriverManager.getConnection(jdbc_conn_str, props);
	}
	
	protected static DBTable getTableInfo(String tableName, String tableSequence, String setBizIdMathodName, String prefixId){
		Connection conn = null;
		DBTable table = null;
		try {
			conn = getRemarksConnection(DbDriver, DbConnStr, DbUser, DbPassword);
			table = getTableInfoWithConn(conn, tableName,tableSequence, setBizIdMathodName, prefixId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			conn = null;
		}
		return table;
	}
	
	
	private static OutputStream getOutputStream(String filePath) throws Exception{
		OutputStream os = null;
		if(filePath != null && !"".equals(filePath)){
			//生成文件夹
			String dirStr = filePath.substring(0, filePath.lastIndexOf("/"));
			File file = new File(dirStr);
        	if(!file.exists()){
        		file.mkdirs();
        	}
			os = new FileOutputStream(filePath);
		}
		return os;
	}
	
	/**
	 * 根据表名得到表信息
	 * @param tableName
	 * 			表名
	 * @return 数据库表的实例
	 */
	private static DBTable getTableInfoWithConn(Connection conn, String tableName, String tableSequence, String setBizIdMathodName, String prefixId) throws Exception{
		tableName = tableName.toUpperCase();
		DBTable table = new DBTable();
		table.setTableName(tableName);
		String tableClass=transTableName(tableName);
		table.setTableTransName(tableClass);
		table.setTableTransNameLow(Character.toLowerCase(tableClass.charAt(0))+tableClass.substring(1));
		table.setTableSequence(tableSequence);
		table.setSetBizIdMathodName(setBizIdMathodName);
		table.setPrefixId(prefixId);
		//表基本信息
		setDatabaseInfo(conn, table);
		DatabaseMetaData dmd = conn.getMetaData();
		//得到数据库类型对应的适配器并构造表描述
		HashMap<String, String> commentsMap = getCommentsMap(dmd, tableName,setBizIdMathodName,prefixId);
		if(commentsMap.containsKey("") && commentsMap.get("") != null && !"".equals(commentsMap.get(""))){
			//表描述
			table.setTableComment(commentsMap.get(""));
		}
		else{
			table.setTableComment(table.getTableName());
		}
		//得到主键字段名
		String primaryKey = getPrimaryKey(conn, null, null, tableName);
		//构造字段信息
		setColumnList(conn, table, primaryKey, commentsMap);
		return table;
	}
	
	/**
	 * 得到数据库表和字段注释的Map
	 * <br>Map的key为字段名，value为注释，如果key为''，那么value就是表注释
	 * @param conn
	 * @param tableName
	 * @return
	 * @throws SQLException 
	 */
	protected static HashMap<String, String> getCommentsMap(DatabaseMetaData dmd, String tableName, String setBizIdMathodName, String prefixId) {
		HashMap<String, String> commentsMap = new HashMap<String, String>();
		ResultSet rs = null;
		try {
			rs = dmd.getColumns(null, "SHITEM_NEW", tableName, null);
			while(rs.next()){
				commentsMap.put(rs.getString("COLUMN_NAME"), rs.getString("REMARKS"));
			}
			while(rs.next()){
				commentsMap.put("", rs.getString("REMARKS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getClass().getName() + " : " + e.getMessage());
		}
		finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
		}
		return commentsMap;
	}
	
	/**
	 * 构造表的主键信息，返回字段名
	 * @param catalog 类别过滤，因为存储在数据库中，所以它必须匹配类别名称。
	 * 		该参数为 "" 则检索没有类别的描述，为 null 则表示该类别名称不应用于缩小搜索范围
	 * @param schemaPattern 模式名称过滤，因为存储在数据库中，所以它必须匹配模式名称。
	 * 		该参数为 "" 则检索那些没有模式的描述，为 null 则表示该模式名称不应用于缩小搜索范围
	 * @param tableName 表名
	 * @throws Exception 
	 */
	private static String getPrimaryKey(Connection conn, String catalog, String schemaPattern, String tableName) throws Exception{
		String primaryKey = null;
		DatabaseMetaData dmd = conn.getMetaData();
		ResultSet rs = null;
		try {
			//查询表主键信息 
			rs = dmd.getPrimaryKeys(catalog, schemaPattern, tableName);
			if(rs.next()){
				//COLUMN_NAME String => column name 
				//KEY_SEQ short => sequence number within primary key( a value of 1 represents the first column of the primary key, a value of 2 would represent the second column within the primary key). 
				//PK_NAME String => primary key name (may be null) 
				primaryKey = rs.getString("COLUMN_NAME");
			}
//			if(pkMap.isEmpty() || pkMap.size() == 0){
//				throw new NullPointerException("表" + table.getTableName() + "没有主键！！！");
//			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getClass().getName() + " : " + e.getMessage());
		}
		finally{
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return primaryKey;
	}
	
	/**
	 * 通过数据库驱动得到所有表（视图）名
	 * @param catalog 类别过滤，因为存储在数据库中，所以它必须匹配类别名称。
	 * 		该参数为 "" 则检索没有类别的描述，为 null 则表示该类别名称不应用于缩小搜索范围
	 * @param schemaPattern 模式名称过滤，因为存储在数据库中，所以它必须匹配模式名称。
	 * 		该参数为 "" 则检索那些没有模式的描述，为 null 则表示该模式名称不应用于缩小搜索范围
	 * @param tableNamePattern 表名称过滤
	 * @throws Exception
	 */
	private static List<String> getTableNameList(Connection conn, String catalog, String schemaPattern, String tableNamePattern){
		List<String> tableNameList = new ArrayList<String>();
		ResultSet rs = null;
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			rs = metaData.getTables(catalog, schemaPattern, tableNamePattern, new String[]{"TABLE"});
			while(rs.next()){
				tableNameList.add(rs.getString("TABLE_NAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getClass().getName() + " : " + e.getMessage());
		}
		finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
		}
		return tableNameList;
	}
	
	/**
	 * 将数据库表名转换为类名，用于代码生成（如SYS_USER变成SysUser）
	 * @param tableName
	 * @return 转换后的类名
	 */
	protected static String transTableName(String tableName){
		StringBuffer tableTransName = new StringBuffer();
		if(tableName!=null&&!"".equals(tableName)){
			//转换成全小写并去除所有空格
			tableName = tableName.toLowerCase().replace(" ", "");
			//以_分割
			String tableSplitNameArray[] = tableName.split("_");
			for(String tableSplitName : tableSplitNameArray){
				if(tableSplitName!=null&&!"".equals(tableSplitName)){
					//将_分割后的首字母大写并拼接
					tableTransName.append(Character.toUpperCase(tableSplitName.charAt(0))+tableSplitName.substring(1));
				}
			}
		}
		return tableTransName.toString();
	}
	
	/**
	 * 将数据库列名转换为类中的属性名，用于代码生成（如ST_USER_NAME变成stUserName）
	 * @param columnName
	 * @return 转换后的属性名
	 */
	protected static String transColumnName(String columnName){
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
		return columnTransName;
	}
	
	/**
	 * 构造数据库相关信息
	 * @param table
	 * @param connection
	 * @throws Exception
	 */
	protected static void setDatabaseInfo(Connection conn, DBTable table) throws Exception{
		DatabaseMetaData dmd = conn.getMetaData();
		if(conn.getCatalog() != null){
			//数据库名
			table.setDatabaseName(conn.getCatalog().toUpperCase());
		}
		//数据库驱动
		table.setDatabaseDriver(dmd.getDriverName());
		//数据库类型名
		table.setDatabaseProduct(dmd.getDatabaseProductName());
		//数据库版本号
		table.setDatabaseVersion(dmd.getDatabaseProductVersion());
	}
	
	/**
	 * 构造表字段信息
	 * @param primaryKey 传入表的主键字段，用于构造字段是否为主键
	 * @param indexList 传入表的索引信息，用于构造字段是否为索引
	 * @param commentsMap 传入表的注释信息，用于构造字段的注释
	 */
	private static void setColumnList(Connection conn, DBTable table, String primaryKey, HashMap<String,String> commentsMap){
		Map<String, DBColumn> columnMap = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			columnMap = new HashMap<String, DBColumn>();
			//查询一个空结果集
			ps = conn.prepareStatement("select * from "+table.getTableName()+" where 1=2");
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			HashMap<String, String> fieldTypeMap = getFieldTypeMap();
			//循环列信息
			for(int i=1;i<=rsmd.getColumnCount();i++){
				DBColumn column = new DBColumn();
				//得到此列在数据库中的类型
				String columnType = rsmd.getColumnTypeName(i).toUpperCase();
				String columnName = rsmd.getColumnLabel(i);
				
				//此列在数据库中的类型
				column.setColumnType(columnType);
				//此列在数据库中的类型对应java中的类型。设计字段类型不对，只能硬写。现修改名，改为_db来区分
				column.setColumnJavaType( (columnName.endsWith("_DB") )?"Double":fieldTypeMap.get(columnType));
				//列定义的长度
				column.setColumnSize(rsmd.getPrecision(i));
				//列名
				column.setColumnName(columnName);
				column.setColumnTransName(transColumnName(columnName));
				//列描述
				if(commentsMap.containsKey(columnName) && commentsMap.get(columnName) != null && !"".equals(commentsMap.get(columnName))){
					column.setColumnComment(commentsMap.get(columnName));
				}
				else{
					column.setColumnComment(columnName);
				}
				//是否自增字段
				column.setAutoIncrement(rsmd.isAutoIncrement(i));
				//是否可为空
				column.setNullable(rsmd.isNullable(i) == 1);
				//是否为主键
				if(primaryKey != null && primaryKey.equals(columnName)){
					column.setPK(true);
					table.setKeyFieldColumn(column);
				}
				else{
					column.setPK(false);
				}
				columnMap.put(columnName, column);
			}
			table.setColumnMap(columnMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ps = null;
			}
		}
	}
	
	/**
	 * 得到数据库字段类型和java类型的对应关系
	 * @return
	 */
	private static HashMap<String, String> getFieldTypeMap(){
		HashMap<String, String> fieldTypeMap = new HashMap<String, String>();
		fieldTypeMap.put("INT", "Integer");
		fieldTypeMap.put("INTEGER", "Integer");
		fieldTypeMap.put("TINYINT", "Integer");
		fieldTypeMap.put("SMALLINT", "Integer");
		fieldTypeMap.put("MEDIUMINT", "Integer");
		fieldTypeMap.put("NUMBER", "Integer");
		
		fieldTypeMap.put("ID", "Long");
		fieldTypeMap.put("BIGINT", "Long");

		fieldTypeMap.put("CHAR", "String");
		fieldTypeMap.put("NCHAR", "String");
		fieldTypeMap.put("VARCHAR", "String");
		fieldTypeMap.put("VARCHAR2", "String");
		fieldTypeMap.put("NVARCHAR", "String");
		fieldTypeMap.put("NVARCHAR2", "String");
		fieldTypeMap.put("TEXT", "String");
		fieldTypeMap.put("NTEXT", "String");
		fieldTypeMap.put("TINYTEXT", "String");
		fieldTypeMap.put("NTINYTEXT", "String");
		fieldTypeMap.put("MEDIUMTEXT", "String");
		fieldTypeMap.put("NMEDIUMTEXT", "String");
		fieldTypeMap.put("LONGTEXT", "String");
		fieldTypeMap.put("NLONGTEXT", "String");
		fieldTypeMap.put("CLOB", "String");

		fieldTypeMap.put("BIT", "Boolean");
		fieldTypeMap.put("BOOLEAN", "Boolean");

		fieldTypeMap.put("FLOAT", "Float");
		fieldTypeMap.put("DOUBLE", "Double");

		fieldTypeMap.put("DECIMAL", "BigDecimal");
		fieldTypeMap.put("MONEY", "BigDecimal");
		fieldTypeMap.put("SMALLMONEY", "BigDecimal");
		fieldTypeMap.put("NUMERIC", "BigDecimal");

		fieldTypeMap.put("REAL", "Float");

		fieldTypeMap.put("SMALLDATETIME", "Timestamp");
		fieldTypeMap.put("DATETIME", "Timestamp");
		fieldTypeMap.put("TIMESTAMP", "Timestamp");
		fieldTypeMap.put("YEAR", "Timestamp");
		//fieldTypeMap.put("DATE", "Timestamp");
		fieldTypeMap.put("DATE", "Date");
		fieldTypeMap.put("TIME", "Timestamp");

		fieldTypeMap.put("BINARY", "byte[]");
		fieldTypeMap.put("VARBINARY", "byte[]");
		fieldTypeMap.put("IMAGE", "byte[]");
		fieldTypeMap.put("BLOB", "byte[]");
		fieldTypeMap.put("TINYBLOB", "byte[]");
		fieldTypeMap.put("MEDIUMBLOB", "byte[]");
		fieldTypeMap.put("LONGBLOB", "byte[]");
		fieldTypeMap.put("LONG", "byte[]");
		return fieldTypeMap;
	}
	
	/**
	 * 
	 * 获得列备注
	 * @param 表名
	 * @return 数据库表的实例
	 */
	public static Map<String, DBColumn> getCommentsMap(String tableName,String tableSequence, String setBizIdMathodName, String prefixId) throws Exception{
		Connection conn = getRemarksConnection(DbDriver, DbConnStr, DbUser, DbPassword);
		DBTable table = getTableInfoWithConn(conn,tableName, tableSequence, setBizIdMathodName, prefixId);
		return table.getColumnMap();
	}	
	
	
	/**
	 * 请按示例逐个生成与单表相关的bean,dao,service,action.
	 * 目前的dao层，由于review的bean是直接配置的sequence的，所以模板与其它的可能不同。
	 * 
	 * 
	 * !!!!!!!!!!!!不同的模块注意 ID生成方式不同，需要改ftl模板，或者生成后修改。
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		//generateBeanByTableName(表名,包名,序列名,设置业务ID方法名,业务ID的前缀);
		//generateBeanByTableName("REVIEW_CASE_INFO","com/wonders/fzb/reviewsh/reviewcase","REVIEW_CASE_ID","setStCaseId","CAS_");
		//generateBeanByTableName("LITIGATION_CASE_INFO","com/wonders/fzb/reviewsh/litigation","LITIGATION_CASE_ID","setStCaseId","LIT_");
		//generateBeanByTableName("REVIEW_PORTAL_CODE","com/wonders/fzb/reviewsh/portal","REVIEW_CODE_ID","setStTid","COD_");
		
		//generateBeanByTableName("REVIEW_GEN_FILE","com/wonders/fzb/reviewsh/portal","REVIEW_FILE_ID","setStFileId","FIL_");

		
		//generateBeanByTableName("BEHAVIOR_INFO","com/wonders/fzb/behavior","SEQ_BEHAVIOR_INFO","setStInfoId","INF_");
		generateBeanByTableName("BEHAVIOR_STATISTIC","com/wonders/fzb/behavior","SEQ_BEHAVIOR_STATISTIC","setStStaticId","STA_");
	
//		DBTable table = getTableInfo("BEHAVIOR_STATISTIC","SEQ_BEHAVIOR_STATISTIC","setStStaticId","STA_");
//		Map<String, DBColumn> colMap= table.getColumnMap();
//		StringBuilder sb=new StringBuilder("");
//		StringBuilder sb2=new StringBuilder("");
//		  Set<String> set = colMap.keySet(); 
//		  for (String s:set) {
//			  sb.append("\""+s+"\",");
//			  sb2.append("\""+transColumnName(s)+"\",");
//		  }
//		  System.out.println(sb.toString());
//		  System.out.println(sb2.toString());
		
	}
}
