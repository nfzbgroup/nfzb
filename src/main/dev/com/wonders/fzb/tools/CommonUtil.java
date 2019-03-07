package com.wonders.fzb.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author midao
 */
public class CommonUtil {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // 定义将日期格式要换成的格式

	/**
	 * 日期操作通用方法
	 * @param date 需要操作时间   
	 * @param addType 添加类型 date month year
	 * @param number 添加天数  正数 增加 负数 减少
	 * @return
	 */
	public static Date addDate(Date date, String addType, int number) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if ("date".equals(addType)) {
			cal.add(Calendar.DATE, number);
		} else if ("month".equals(addType)) {
			cal.add(Calendar.MONTH, number);
		} else if ("year".equals(addType)) {
			cal.add(Calendar.YEAR, number);
		}
		return cal.getTime();
	}

	/**
	 * 判断时间是否在时间段内
	 * @param nowTime
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws ParseException 
	 */
	public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) throws ParseException {
		Date dtNowTime = new Date();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");// 设置日期格式
		dtNowTime = df.parse(df.format(nowTime));
		if (dtNowTime.after(beginTime) && dtNowTime.before(endTime)) {
			return true;
		} else {
			return false;
		}
	}

}
