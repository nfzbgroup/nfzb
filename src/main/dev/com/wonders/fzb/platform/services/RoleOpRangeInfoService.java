package com.wonders.fzb.platform.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.platform.beans.RoleOpRangeInfo;
import com.wonders.fzb.platform.beans.RoleOpRangePK;

/**
 * WFC_F_ROLE_OP_RELATION业务接口
 * 
 * @author scalffold
 * 
 */
public interface RoleOpRangeInfoService {
	/**
	 * 添加实体对象
	 * 
	 * @param RoleOpRelationInfo
	 */
	public void add(RoleOpRangeInfo roleOpRangeInfo);

	/**
	 * 更新实体对象
	 * 
	 * @param RoleOpRelationInfo
	 */
	public void update(RoleOpRangeInfo roleOpRangeInfo);
	
	/**
	 * 删除实体对象
	 * 
	 * @param pk
	 */
	public void delete(RoleOpRangePK pk);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param pk 主键
	 * @return
	 */
	RoleOpRangeInfo findByPK(RoleOpRangePK pk);

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
	public List<RoleOpRangeInfo> findByList(Map<String, Object> condMap,
			Map<String, String> sortMap);
}
