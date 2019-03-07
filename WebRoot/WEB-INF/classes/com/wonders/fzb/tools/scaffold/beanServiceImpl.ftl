package ${packageStr}.services.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import ${packageStr}.beans.*;
import ${packageStr}.dao.*;
import ${packageStr}.services.*;


/**
 * ${table.tableTransName} service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("${table.tableTransNameLow}Service")
@Transactional
public class ${table.tableTransName}ServiceImpl implements ${table.tableTransName}Service {

	@Autowired
	private ${table.tableTransName}Dao ${table.tableTransNameLow}Dao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(${table.tableTransName} info) {
		${table.tableTransNameLow}Dao.save(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(${table.tableTransName} info) {
		${table.tableTransNameLow}Dao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(${table.tableTransName} info) {
		${table.tableTransNameLow}Dao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public ${table.tableTransName} findById(String id) {
		return (${table.tableTransName}) ${table.tableTransNameLow}Dao.load(id);
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
	 * @throws FzbDaoException
	 */
	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException {
		return ${table.tableTransNameLow}Dao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<${table.tableTransName}> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return ${table.tableTransNameLow}Dao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(${table.tableTransName} info) {
		${table.tableTransNameLow}Dao.saveOrUpdate(info);
	}

	//////////////////////////add u personal mathod below like the example!////////////////////////
//	@Override
//	public List<${table.tableTransName}> findByPara(String para) {
//		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
//		Map<String, String> sortMap = new LinkedHashMap<String, String>();
//		condMap.put("idenNo", idenNo);
//		sortMap.put("inportTime", CommonConst.ORDER_DESC);
//		List<${table.tableTransName}> ${table.tableTransNameLow}List = ${table.tableTransNameLow}Dao.findByList(condMap, null);
//		return ${table.tableTransNameLow};
//	}


}
