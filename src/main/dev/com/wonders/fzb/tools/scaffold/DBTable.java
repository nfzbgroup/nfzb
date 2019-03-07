package com.wonders.fzb.tools.scaffold;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 根据数据库信息生成相关的Bean，Dao,Service,Action的scaffold代码。
 * @author lj
 *
 */
public class DBTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4920627054427495150L;

	//表名
	private String tableName;
	
	//表转换后的名称（如SYS_USER_TALE变成SysUserTable,用于代码生成）
	private String tableTransName;
	//表转换后的名称首字母小写（如SYS_USER_TALE变成sysUserTable,用于代码生成）
	private String tableTransNameLow;
	//表所使用的sequence
	private String tableSequence;
	//业务ID的设置方法名，用在DaoImpl
	private String setBizIdMathodName;
	//业务ID的前缀
	private String prefixId;
	
	//表描述
	private String tableComment;
	
	//表主键字段
	private DBColumn keyFieldColumn;
	
	//表索引字段名
	private List<String> indexList;
	
	//表字段
	private Map<String, DBColumn> columnMap;
	
	//所属数据库类型
	private String databaseProduct;
	
	//所属数据库版本
	private String databaseVersion;
	
	//所属数据库名
	private String databaseName;
	
	//所属数据库的驱动
	private String databaseDriver;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableComment() {
		return tableComment;
	}
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	public String getDatabaseProduct() {
		return databaseProduct;
	}
	public void setDatabaseProduct(String databaseProduct) {
		this.databaseProduct = databaseProduct;
	}
	public String getDatabaseVersion() {
		return databaseVersion;
	}
	public void setDatabaseVersion(String databaseVersion) {
		this.databaseVersion = databaseVersion;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public String getDatabaseDriver() {
		return databaseDriver;
	}
	public void setDatabaseDriver(String databaseDriver) {
		this.databaseDriver = databaseDriver;
	}
	public Map<String, DBColumn> getColumnMap() {
		return columnMap;
	}
	public void setColumnMap(Map<String, DBColumn> columnMap) {
		this.columnMap = columnMap;
	}
	public String getTableTransName() {
		return tableTransName;
	}
	public void setTableTransName(String tableTransName) {
		this.tableTransName = tableTransName;
	}
	
	public String getTableTransNameLow() {
		return tableTransNameLow;
	}
	public void setTableTransNameLow(String tableTransNameLow) {
		this.tableTransNameLow = tableTransNameLow;
	}
	
	public String getTableSequence() {
		return tableSequence;
	}
	public void setTableSequence(String tableSequence) {
		this.tableSequence = tableSequence;
	}
	
	public String getSetBizIdMathodName() {
		return setBizIdMathodName;
	}
	public void setSetBizIdMathodName(String setBizIdMathodName) {
		this.setBizIdMathodName = setBizIdMathodName;
	}
	public String getPrefixId() {
		return prefixId;
	}
	public void setPrefixId(String prefixId) {
		this.prefixId = prefixId;
	}
	public List<String> getIndexList() {
		return indexList;
	}
	public void setIndexList(List<String> indexList) {
		this.indexList = indexList;
	}
	public DBColumn getKeyFieldColumn() {
		return keyFieldColumn;
	}
	public void setKeyFieldColumn(DBColumn keyFieldColumn) {
		this.keyFieldColumn = keyFieldColumn;
	}
}
