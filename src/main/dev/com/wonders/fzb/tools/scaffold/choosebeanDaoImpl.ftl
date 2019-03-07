package ${packageStr}.dao.impl;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import ${packageStr}.beans.*;
import ${packageStr}.dao.*;

/**
 * ${table.tableTransName} dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("${table.tableTransNameLow}Dao")
public class ${table.tableTransName}DaoImpl extends BaseSupportDao implements ${table.tableTransName}Dao {

	@Override
	public void save(Object object) {
		${table.tableTransName} info = (${table.tableTransName}) object;
		info.${table.setBizIdMathodName}(super.getId("${table.tableSequence}", "${table.prefixId}", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		${table.tableTransName} info = (${table.tableTransName}) object;
		String newId=super.getId("${table.tableSequence}", "${table.prefixId}", 16);
		info.${table.setBizIdMathodName}(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"${table.tableTransName}");
	}

	@Override
	public List<${table.tableTransName}> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "${table.tableTransName}");
	}

	@Override
	public void saveOrUpdate(${table.tableTransName} info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<${table.tableTransName}> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<${table.tableTransName}> result = query.list();
		session.flush();
		return result;
	}

}
