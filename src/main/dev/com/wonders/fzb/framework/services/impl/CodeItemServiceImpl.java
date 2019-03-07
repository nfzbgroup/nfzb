package com.wonders.fzb.framework.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.framework.beans.CodeItem;
import com.wonders.fzb.framework.dao.CodeItemDao;
import com.wonders.fzb.framework.services.CodeItemService;

/**
 * WFC_CODE_INFO 业务实现
 * 
 * @author scalffold
 * 
 */
@Service("codeItemService")
@Transactional
public class CodeItemServiceImpl implements CodeItemService{

	@Autowired
	private CodeItemDao codeItemDao;
	
	/**
	 * 添加实体对象
	 * 
	 * @param opInfo
	 */
	@Override
	public void add(CodeItem opInfo){
		codeItemDao.save(opInfo);
	}

	/**
	 * 更新实体对象
	 * 
	 * @param opInfo
	 */
	@Override
	public void update(CodeItem opInfo){
		codeItemDao.update(opInfo);
	}
	
	/**
	 * 删除实体对象
	 * 
	 * @param opInfo
	 */
	@Override
	public void delete(String id){
		codeItemDao.deleteById(id);
	}

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id 主键
	 * @return
	 */
	@Override
	public CodeItem findById(String id){
		return (CodeItem)codeItemDao.load(id);
	}

	/**
	 * 根据分页参数进行分页查询.
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	@Override
	public Page findByPage(int pageNo, int pageSize){
//		return opInfoDao.findAllWithPage(pageNo, pageSize);
		return null;
	}

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
	@Override
	public Page findByPage(Map<String, Object> condMap,
			Map<String, String> sortMap, int pageNo, int pageSize){
		return codeItemDao.findByPage(condMap, sortMap, pageNo, pageSize);
	}

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @return
	 */
	@Override
	public List<CodeItem> findByList(Map<String, Object> condMap,
			Map<String, String> sortMap){
		return codeItemDao.findByList(condMap, sortMap);
	}

	@Override
	public CodeItem getCodeItemByItemName(String pid, String name) {
		// TODO Auto-generated method stub
		List<CodeItem> codeList = this.getChildItems(pid);
		for(int i=0;i<codeList.size();i++)
	    {
	      CodeItem item =(CodeItem)codeList.get(i);
	      if(item.stName.equals(name))
	        return item;
	    }
	    return new CodeItem();
	}

	@Override
	public List<CodeItem> getChildItems(String pid) {
		// TODO Auto-generated method stub
		List<CodeItem> codeList=new ArrayList<CodeItem>();
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		condMap.put("stPid", pid);
		sortMap.put("stTid", CommonConst.ORDER_ASC);
		codeList = codeItemDao.findByList(condMap, sortMap);
		return codeList;
	}

	@Override
	public CodeItem getParentItem(String tid) {
		// TODO Auto-generated method stub
		CodeItem code=this.findById(tid);
		CodeItem code2=this.findById(code.stPid);
		return code2;
	}


}
