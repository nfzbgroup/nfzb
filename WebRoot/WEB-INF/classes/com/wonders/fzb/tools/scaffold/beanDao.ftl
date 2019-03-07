package ${packageStr}.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import ${packageStr}.beans.${table.tableTransName};


/**
 * ${table.tableTransName} dao接口
 * @author scalffold created by lj
 */
public abstract interface ${table.tableTransName}Dao extends BaseDao {
	
	public void saveOrUpdate(${table.tableTransName} info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<${table.tableTransName}> findByList(Map<String, Object> condMap, Map<String, String> sortMap);


}
