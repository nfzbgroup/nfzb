package com.wonders.fzb.base.logging.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.logging.beans.LogInfo;
import com.wonders.fzb.base.logging.dao.LoggingDao;

@Repository("loggingDao")
public class LoggingDaoImpl extends BaseSupportDao implements LoggingDao {

	@Override
	public void save(LogInfo loginInfo) {
		super.save(loginInfo);
	}

	@Override
	public Page<LogInfo> findPageByHQL(int page,String hql) {
		int count = findTotal(hql);
		int pageSize = 15;
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(Page.getStartOfAnyPage(page, pageSize) - 1);
		query.setMaxResults(pageSize);
		List<LogInfo> result = query.list();
		session.flush();
		
		Page<LogInfo> pages = new Page<LogInfo>(Page.getStartOfAnyPage(page, 15), result.size(), count, pageSize, result);
		return pages;
	}
	
	private int findTotal(String hql){
		 return Integer.parseInt(getHibernateTemplate().find("SELECT COUNT(*) " + hql).listIterator().next()+"");
	}
	
	 
}
