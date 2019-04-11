package com.wonders.fzb.simpleflow.services.impl;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.dao.WegovSimpleNodeDao;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import dm.jdbc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WegovSimpleNode service实现
 * 
 * @author scalffold created by lj
 */

@Service("wegovSimpleNodeService")
@Transactional
public class WegovSimpleNodeServiceImpl implements WegovSimpleNodeService {

	@Autowired
	private WegovSimpleNodeDao wegovSimpleNodeDao;

	/**
	 * 添加实体对象
	 */
	@Override
	public void add(WegovSimpleNode info) {
		wegovSimpleNodeDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(WegovSimpleNode info) {
		return wegovSimpleNodeDao.saveObj(info);
	}

	/**
	 * 更新实体对象
	 */
	@Override
	public void update(WegovSimpleNode info) {
		wegovSimpleNodeDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(WegovSimpleNode info) {
		wegovSimpleNodeDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public WegovSimpleNode findById(String id) {
		return (WegovSimpleNode) wegovSimpleNodeDao.load(id);
	}

	/**
	 * 根据Map中过滤条件、排序条件和分页参数进行分页查询.
	 * 
	 * @param condMap  过滤条件<propertyName,properyValue>
	 * @param sortMap  排序条件<propertyName,properyValue>
	 * @param pageNo   当前页码
	 * @param pageSize 每页显示记录数.
	 * @return
	 * @throws FzbDaoException
	 */
	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException {
		return wegovSimpleNodeDao.findByPage(condMap, sortMap, pageNo, pageSize);
	}

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap 过滤条件<propertyName,properyValue>
	 * @param sortMap 排序条件<propertyName,properyValue>
	 * @return
	 */
	@Override
	public List<WegovSimpleNode> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return wegovSimpleNodeDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(WegovSimpleNode info) {
		wegovSimpleNodeDao.saveOrUpdate(info);
	}

	@Override
	public List<WegovSimpleNode> findByHQL(String hql) {
		List<WegovSimpleNode> wegovSimpleNodeList = wegovSimpleNodeDao.findByHQL(hql);
		return wegovSimpleNodeList;
	}

	/**
	 * 查询列表页tab信息
	 *
	 * @param stNodeId
	 * @return
	 */
	@Override
	public List<Object> queryButtonInfo(String stNodeId) {
		List<Object> buttonNameList = new ArrayList<>();
		HashMap<String, String> nameMap = null;
		List<WegovSimpleNode> nodeList = findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + stNodeId + "'");
		if (nodeList != null && nodeList.size() > 0) {
			WegovSimpleNode wegovSimpleNode = nodeList.get(0);
			if (StringUtil.isNotEmpty(wegovSimpleNode.getStTodoName())) {
				String[] stTodoNameArray = wegovSimpleNode.getStTodoName().split("#");
				String[] stDoneNameArray = wegovSimpleNode.getStDoneName().split("#");
				for (int i = 0; i < stTodoNameArray.length; i++) {
					nameMap = new HashMap<>();
					nameMap.put("buttonName", stTodoNameArray[i]);
					nameMap.put("buttonId", stDoneNameArray[i]);
					if (i == 0) {
						nameMap.put("buttonClass", "btn btn-w-m btn-success");
					} else {
						nameMap.put("buttonClass", "btn btn-w-m btn-default");
					}
					buttonNameList.add(nameMap);
				}
			}
		}
		return buttonNameList;
	}

	@Override
	public WegovSimpleNode findByNodeId(String stNodeId) {
		List<WegovSimpleNode> wegovSimpleNodeList = new ArrayList<WegovSimpleNode>();
		WegovSimpleNode wegovSimpleNode = new WegovSimpleNode();
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		if (!"".equals(stNodeId)) {
			condMap.put("stNodeId", stNodeId);
		}
		sortMap.put("id", "desc");
		wegovSimpleNodeList = wegovSimpleNodeDao.findByList(condMap, sortMap);
		if (wegovSimpleNodeList.size() > 0) {
			wegovSimpleNode = wegovSimpleNodeList.get(0);
		}
		return wegovSimpleNode;
	}

	@Override
	public HashMap<String, String> getDoneNameNext(String stNodeId, String stDoneName) {
		List<WegovSimpleNode> wegovSimpleNodeList = new ArrayList<WegovSimpleNode>();
		HashMap<String, String> nextMap = new HashMap<String, String>();
		WegovSimpleNode wegovSimpleNode = new WegovSimpleNode();
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		String stRtDoneName = "";
		String stRtTodoName = "";
		if (!"".equals(stNodeId)) {
			condMap.put("stNodeId", stNodeId);
		}
		sortMap.put("id", "desc");
		wegovSimpleNodeList = wegovSimpleNodeDao.findByList(condMap, sortMap);
		if (wegovSimpleNodeList.size() > 0) {
			wegovSimpleNode = wegovSimpleNodeList.get(0);
		}
		String[] stTodoNameArray = wegovSimpleNode.getStTodoName().split("#");
		String[] stDoneNameArray = wegovSimpleNode.getStDoneName().split("#");
		//不需要查询最后位置 如果是最后一位直接返回空
		for (int i = 0; i < stDoneNameArray.length - 1; i++) {
			String stDoneNameCode = stDoneNameArray[i];
			if (stDoneNameCode.equals(stDoneName)) {
				stRtDoneName = stDoneNameArray[i + 1];
				stRtTodoName = stTodoNameArray[i + 1];
			}
		}
		nextMap.put(stRtDoneName, stRtTodoName);
		return nextMap;
	}
}
