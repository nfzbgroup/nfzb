package com.wonders.fzb.platform.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.platform.beans.WorkdayInfo;

/**
 * 工作日业务接口
 * 
 * @author scalffold
 * 
 */
public interface WorkdayInfoService {

	/**
	 * 添加或修改实体对象
	
	 * 
	 * @param appConfig
	 */
	public void saveOrUpdate(WorkdayInfo workdayInfo);

	/**
	 * 添加实体对象
	 * 
	 * @param appConfig
	 */
	public void add(WorkdayInfo workdayInfo);

	/**
	 * 添加实体对象
	 * 
	 * @param appConfig
	 * @throws ParseException 
	 */
	public boolean saveChange(WorkdayInfo workdayInfo);

	/**
	 * 更新实体对象
	 * 
	 * @param appConfig
	 */
	public void update(WorkdayInfo workdayInfo);

	/**
	 * 删除实体对象
	 * 
	 * @param appConfig
	 */
	public void delete(WorkdayInfo workdayInfo);

	/**
	 * 删除实体对象
	 * 
	 * @param appConfig
	 */
	public void deleteById(String st_day);

	/**
	 * 根据分页参数进行分页查询.
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findByPage(int pageNo, int pageSize);

	/**
	 * 根据Map中过滤条件、排序条件和分页参数进行分页查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize);

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @return
	 */
	public List<WorkdayInfo> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	/**
	 * 根据开始日期，计算几个工作日后的日期
	 * @param stStartDate 开始日期
	 * @param day 工作天数
	 * @return
	 * @throws ParseException 
	 */
	public String findWorkday(String stStartDate, int day) throws ParseException;

	/**
	 * 如果时间是休息日或者是国定休息日 获得下一个工作日 如果是工作日返回工作日
	 * @param stStartDate
	 * @return
	 * @throws ParseException 
	 */
	public String getNextWorkDay(String stStartDate) throws ParseException;

	/**
	 * 判断是否是工作日	 "09:00" 到 "18:00"
	 * 如果不是工作时间 日期向后加一天
	 * @param dtStartDate
	 * @return
	 * @throws ParseException
	 */
	public Date isWorkTime(Date dtStartDate) throws ParseException;

	
	/**
	 * 是否是规定时间
	 * 两个条件必须都满足  一、必须是9点到18点 二必须是工作日 如果满足 返回true 如果不满足 返回false
	 * 
	 * @param dtStartDate
	 * @return
	 * @throws ParseException
	 */
	public boolean isTrueDay(Date dtStartDate) throws ParseException;

}
