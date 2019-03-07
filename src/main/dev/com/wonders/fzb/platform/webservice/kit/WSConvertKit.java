package com.wonders.fzb.platform.webservice.kit;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.sun.org.apache.bcel.internal.generic.NEW;

public class WSConvertKit {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object convert(Object source, Object target) throws Exception {
		Class sourceClass = source.getClass();
		Class targetClass = target.getClass();

		Field[] sourceFields = sourceClass.getDeclaredFields();
		Field[] targetFields = targetClass.getDeclaredFields();

		for (Field sourceField : sourceFields) {
			String fieldName = sourceField.getName();
			Class fieldType = sourceField.getType();
			
			if("com.wondersgroup.wegov.missive.common.LeadCommentInfo".equals(sourceClass.getName()) && ("showSeq".equals(fieldName) || "leadRank".equals(fieldName))){
				continue;
			}

			String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method getMethod = sourceClass.getMethod("get" + methodName);

			Object sourceValue = getMethod.invoke(source); // 执行get方法
			if (sourceValue == null) {
				if (fieldType.getName().contains("String")) {
					sourceValue = "";
				} else if (fieldType.getName().contains("Date")) {
					sourceValue = new Date();
				} else if (fieldType.getName().contains("Integer")) {
					sourceValue = "0";
				}
			}
			for (Field targetField : targetFields) {
				String targetName = targetField.getName();
				if (targetName.equals(fieldName)) {
					Method setMethod = targetClass.getMethod("set" + methodName, fieldType);
					setMethod.invoke(target, sourceValue);
				}
			}
		}
		return target;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String convertXML(Object source) throws Exception {
		Class sourceClass = source.getClass();
		String className = sourceClass.getSimpleName();
		Field[] sourceFields = sourceClass.getDeclaredFields();
		StringBuffer buffer = new StringBuffer("<" + className + ">");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (Field sourceField : sourceFields) {
			String fieldName = sourceField.getName();
			if (fieldName.equals("serialVersionUID"))
				continue;
			Class fieldType = sourceField.getType();
			String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method getMethod = sourceClass.getMethod("get" + methodName);
			Object sourceValue = getMethod.invoke(source); // 执行get方法
			// buffer.append("<"+fieldName+" type=\""+fieldType.getSimpleName()+"\">");
			buffer.append("<" + fieldName + ">");
			String fieldTypeName = fieldType.getSimpleName();
			if ("Date".equals(fieldTypeName)) {
				buffer.append(sdf.format((Date) sourceValue));
			} else if ("Long".equals(fieldTypeName)) {
				buffer.append(sourceValue.toString());
			} else {
				buffer.append("<![CDATA[" + sourceValue + "]]>");
			}
			buffer.append("</" + fieldName + ">");
		}
		buffer.append("</" + className + ">");
		return buffer.toString();
	}
}
