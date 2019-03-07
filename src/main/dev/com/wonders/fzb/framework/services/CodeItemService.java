package com.wonders.fzb.framework.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.framework.beans.CodeItem;
import com.wonders.fzb.framework.exceptions.CodeException;

/**
 * WFC_CODE_INFO业务接口
 * 
 * @author scalffold
 * 
 */
public interface CodeItemService {
	/**
	 * 添加实体对象
	 * 
	 * @param opInfo
	 */
	public void add(CodeItem codeItem);

	/**
	 * 更新实体对象
	 * 
	 * @param opInfo
	 */
	public void update(CodeItem codeItem);
	
	/**
	 * 删除实体对象
	 * 
	 * @param opInfo
	 */
	public void delete(String id);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id 主键
	 * @return
	 */
	public CodeItem findById(String id);

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
	public Page findByPage(Map<String, Object> condMap,
			Map<String, String> sortMap, int pageNo, int pageSize);


	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @return
	 */
	public List<CodeItem> findByList(Map<String, Object> condMap,
			Map<String, String> sortMap);

	public CodeItem getCodeItemByItemName(String pid, String name);
	
	/**数据字典的信息管理--查询某一代码的所有子代码
	 * @param  pid-—-需要获得代码项的父代码标识
	 * @return 返回该父代码标识的所有子代码信息的集合，没有的返回null
	 * @exception CodeException
	 */
	public List<CodeItem> getChildItems (String pid);
	
	/**
	 *数据字典的信息管理--获得某个代码项的父代码
	 * @param tid -- 代码标识
	 * @return 父代码的代码项信息，没有返回null
	 * @exception CodeException
	 */
	public CodeItem getParentItem(String tid);
}
