package com.wonders.fzb.base.beans;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wonders.fzb.base.consts.FastQuery.Symbol;
import com.wonders.fzb.base.kit.StringKit;

/**
 * 通用查询条件解析器
 * 
 * @author ZSW
 */
public class FastQueryConditionParser {

	public FastQueryConditionParser(@SuppressWarnings("rawtypes") Class beanClass) {
		queryBeans = beanClass;
	}

	@SuppressWarnings("rawtypes")
	private Class queryBeans;

	@SuppressWarnings("rawtypes")
	public Class getQueryBeans() {
		return queryBeans;
	}

	public void setQueryBeans(@SuppressWarnings("rawtypes") Class queryBeans) {
		this.queryBeans = queryBeans;
	}

	/**
	 * 快速查询解析器
	 * @return
	 */
	public String analysis() {
		String beanName = getQueryBeans().getSimpleName();
		String abbrName = beanName.substring(0, 1).toLowerCase();

		Field[] fieds = this.getClass().getDeclaredFields();
		String conditions = " ";

		Field[] queryBeanFileds = getQueryBeans().getDeclaredFields();
		try {
			for (Field parameter : queryBeanFileds) {
				// 拿到主键 WHERE UUID IS NOT NULL
				if (null != parameter.getAnnotation(javax.persistence.Id.class)) {
					conditions = MessageFormat.format(" WHERE {0}.{1} {2} ", abbrName, parameter.getName(), Symbol.IS_NOT_NULL);
					break;
				}
			}

			for (Field parameter : fieds) {
				parameter.setAccessible(true);
				Object value = parameter.get(this);

				if (null != value && value instanceof FastQueryCondition) {
					String propertyName = parameter.getName();
					FastQueryCondition fastQuery = (FastQueryCondition) value;
					
					Object propertyValue = (fastQuery.getValue().length > 0 ? fastQuery.getValue()[0] : "");
					
					if(propertyValue instanceof Date){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						propertyValue = "to_date('"+sdf.format(propertyValue)+"','yyyy-mm-dd hh24:mi:ss')";
					}
					
					if (fastQuery.getSymbol() == Symbol.LIKE  || fastQuery.getSymbol() == Symbol.N_LIKE) {
						conditions += MessageFormat.format(" {0} NVL({1}.{2},'' '') {3} ''%'' || REPLACE(''{4}'','' '','''')||''%''",fastQuery.getCondition(),abbrName,propertyName, fastQuery.getSymbol(), propertyValue);
					}else if(fastQuery.getSymbol() == Symbol.LIKE_F || fastQuery.getSymbol() == Symbol.N_LIKE_F){
						conditions += MessageFormat.format(" {0} {1}.{2} {3} ''%{4}'' ", fastQuery.getCondition(),abbrName,propertyName,fastQuery.getSymbol(), propertyValue);
					}else if(fastQuery.getSymbol() == Symbol.LIKE_L || fastQuery.getSymbol() == Symbol.N_LIKE_L){
						conditions += MessageFormat.format(" {0} {1}.{2} {3} ''{4}%'' ", fastQuery.getCondition(),abbrName,propertyName,fastQuery.getSymbol(), propertyValue);
					}else if(fastQuery.getSymbol() == Symbol.LT  ||
							   fastQuery.getSymbol() == Symbol.LE  ||
							   fastQuery.getSymbol() == Symbol.GT ||
						   	   fastQuery.getSymbol() == Symbol.GE ||
						   	   fastQuery.getSymbol() == Symbol.EQ ||
						   	   fastQuery.getSymbol() == Symbol.N_EQ ||
						   	   fastQuery.getSymbol() == Symbol.IS ||
						   	   fastQuery.getSymbol() == Symbol.IS_NOT){
						
						if(propertyValue instanceof String || propertyValue instanceof Enum)
							conditions += MessageFormat.format(" {0} {1}.{2} {3} ''{4}'' ", fastQuery.getCondition(),abbrName,propertyName,fastQuery.getSymbol(), propertyValue);
						else
							conditions += MessageFormat.format(" {0} {1}.{2} {3} {4} ", fastQuery.getCondition(),abbrName,propertyName,fastQuery.getSymbol(), propertyValue);
					}else if(fastQuery.getSymbol() == Symbol.IS_NULL || fastQuery.getSymbol() == Symbol.IS_NOT_NULL){
						conditions += MessageFormat.format(" {0} {1}.{2} {3} ", fastQuery.getCondition(),abbrName,propertyName,fastQuery.getSymbol());
					}else if(fastQuery.getSymbol() == Symbol.IN || fastQuery.getSymbol() == Symbol.NOT_IN){
						String [] valueArr = propertyValue.toString().split(",");
						if(valueArr.length > 0)
							valueArr[0] = "'"+valueArr[0]+"'";
						
						if(valueArr.length < 2)
							conditions += MessageFormat.format(" {0} {1}.{2} {3} ({4})", fastQuery.getCondition(),abbrName,propertyName,fastQuery.getSymbol(),"'"+propertyValue+"'");
						else
							conditions += MessageFormat.format(" {0} {1}.{2} {3} ({4})", fastQuery.getCondition(),abbrName,propertyName,fastQuery.getSymbol(),StringKit.join(valueArr, ",'", "'"));
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		String baseHql = MessageFormat.format("FROM {0} {1} {2}", beanName, abbrName, conditions);

		return baseHql;
	}
}
