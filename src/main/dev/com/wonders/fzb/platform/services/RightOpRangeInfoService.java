package com.wonders.fzb.platform.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.platform.beans.RightOpRangeInfo;
import com.wonders.fzb.platform.beans.RightOpRangePK;

/**
 * WFC_F_RIGHT_OP_RANGE业务接口
 * 
 * @author scalffold
 * 
 */
public interface RightOpRangeInfoService {
	/**
	 * 添加实体对象
	 * 
	 * @param RightOpRangeInfo
	 */
	public void add(RightOpRangeInfo RightOpRangeInfo);

	/**
	 * 更新实体对象
	 * 
	 * @param RightOpRangeInfo
	 */
	public void update(RightOpRangeInfo RightOpRangeInfo);
	
	/**
	 * 删除实体对象
	 * 
	 * @param RightOpRangeInfo
	 */
	public void delete(RightOpRangePK RightOpRangeInfo);

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
	public List<RightOpRangeInfo> findByList(Map<String, Object> condMap,
			Map<String, String> sortMap);
	
	/**
	   * 根据用户操作权限获得操作范围标识列表
	   * @param opRightId
	   * @param type
	   * @return ArrayList of rangeIds
	   */
	public List<String> getOpRangeIdsByOpRightIdType(String opRightId, String type);
	
	/**
	   * 根据角色操作获得操作范围标识列表
	   * @param opId
	   * @param roleId
	   * @param rangeType
	   * @return ArrayList of range ids
	   */
	public List<String> getOpRangeIdsByRoleId(String opId, String roleId,String rangeType);
}
