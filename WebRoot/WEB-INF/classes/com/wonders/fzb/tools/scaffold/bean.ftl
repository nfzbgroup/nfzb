package ${packageStr}.beans;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * ${table.tableComment} Bean (操作业务实体) autoCreated by lj
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "${table.tableName}")
public class ${table.tableTransName} implements Serializable {

  public static final String ${table.tableTransName} = "${table.tableName}";
	
  public ${table.tableTransName}()
  {
  }

<#list table.columnMap?keys as key>
	<#assign column = table.columnMap[key]>
	<#assign fieldNameUp = column.columnTransName?cap_first>	
	/**
	 * ${column.columnComment}
	 */
	<#if column.PK>
	@Id
	//@SequenceGenerator(name="sequenceGenerator",sequenceName="${table.tableSequence}")
	//@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
	</#if>
	<#if column.autoIncrement>
	@javax.persistence.GeneratedValue
	</#if>
	@Column(name = "${column.columnName}")
	private ${column.columnJavaType} ${column.columnTransName};

	/**
	 * ${column.columnComment}
	 */
	public ${column.columnJavaType} get${fieldNameUp}(){
		return ${column.columnTransName};
	}

	/**
	 * ${column.columnComment}
	 */
	public void set${fieldNameUp} (${column.columnJavaType} ${column.columnTransName}){
		this.${column.columnTransName} = ${column.columnTransName};
	}

</#list>
}