package com.wonders.fzb.tools.scaffold;

import java.io.Serializable;
/**
 * 根据数据库信息生成相关的Bean，Dao,Service,Action的scaffold代码。
 * @author lj
 *
 */
public class DBColumn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9069158507635258972L;

	//列名
	private String columnName;
	
	//列名转换后的名称（如ST_USER_ID变成stUserId,用于代码生成）
	private String columnTransName;
	
	//列定义的长度
	private int columnSize;
	
	//列注释
	private String columnComment;
	
	//列在数据库中的类型名称
	private String columnType;
	
	//列的类型对应在java中的类型
	private String columnJavaType;
	
	//是否自增
	private boolean isAutoIncrement;
	
	//是否主键
	private boolean isPK;
	
	//是否可为空
	private boolean isNullable;
	
	//是否为索引
	private boolean isIndex;

	public boolean isIndex() {
		return isIndex;
	}

	public void setIndex(boolean isIndex) {
		this.isIndex = isIndex;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnJavaType() {
		return columnJavaType;
	}

	public void setColumnJavaType(String columnJavaType) {
		this.columnJavaType = columnJavaType;
	}

	public boolean isAutoIncrement() {
		return isAutoIncrement;
	}

	public void setAutoIncrement(boolean isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}

	public boolean isPK() {
		return isPK;
	}

	public void setPK(boolean isPK) {
		this.isPK = isPK;
	}

	public boolean isNullable() {
		return isNullable;
	}

	public void setNullable(boolean isNullable) {
		this.isNullable = isNullable;
	}

	public String getColumnTransName() {
		return columnTransName;
	}

	public void setColumnTransName(String columnTransName) {
		this.columnTransName = columnTransName;
	}
}
