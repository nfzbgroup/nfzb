package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.LegislateConst;
import com.wonders.fzb.legislate.beans.FileRecord;
import com.wonders.fzb.legislate.beans.ModelFileRecord;
import com.wonders.fzb.legislate.dao.FileRecordDao;

@Repository("fileRecordDao")
public class FileRecordDaoImpl extends BaseSupportDao implements FileRecordDao{
	@Override
	public void save(Object object) {
		FileRecord info = (FileRecord) object;
		super.save(info);
	}

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		sortMap.put("createTime", CommonConst.ORDER_DESC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"FileRecord");
	}

	@Override
	public List<FileRecord> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "FileRecord");
	}

	@Override
	public void saveOrUpdate(FileRecord info) {
		// TODO Auto-generated method stub
		super.saveOrUpdate(info);
	}

	/**
	 *@see com.wonders.fzb.legislate.dao.FileRecordDao#findModelFileRecordByList(java.util.Map, java.util.Map)
	 */
	@Override
	public List<ModelFileRecord> findModelFileRecordByList(
			final Map<String, Object> condMap) {
		return getHibernateTemplate().execute(new HibernateCallback<List<ModelFileRecord>>() {

			@Override
			public List<ModelFileRecord> doInHibernate(Session session) throws HibernateException {
				StringBuffer sb = new StringBuffer();
				
				String modelType = (String) condMap.get("MODEL_TYPE");
				
				if(modelType == null){
					sb.append("SELECT m.MODEL_ID,m.MODEL_NAME, m.STATUS, m.MODEL_TYPE,m.instructions,m.FILE_RECORD_ID as has_file,m.is_unique,fr.*  FROM wegov_lf_model m LEFT JOIN (select * from wegov_lf_file_record ");
					sb.append(" where ACTIVITY_TYPE = ? and out_id = ?");
					sb.append(") fr  ON m.MODEL_TYPE = fr.BIZ_TYPE");
					sb.append(" where m.ACTIVITY_TYPE = ? and m.MODEL_TYPE ");
					sb.append(" != ? order by m.CREATE_TIME,fr.CREATE_TIME");
				}else{
					sb.append("select fr.*,fr.file_record_id MODEL_ID, '' MODEL_NAME, 1 STATUS,  '"+LegislateConst.Draft_Add_Model_key_07+"' MODEL_TYPE, '' instructions, '' as has_file, 0 is_unique from wegov_lf_file_record fr ");
					sb.append(" where ACTIVITY_TYPE = ? and out_id = ? and BIZ_TYPE= ?");
				}
				SQLQuery query = session.createSQLQuery(sb.toString());
				if(modelType == null){
					modelType = LegislateConst.Draft_Add_Model_key_07;
					query.setParameter(0,condMap.get("ACTIVITY_TYPE"));
					query.setParameter(1,condMap.get("out_id"));
					query.setParameter(2,condMap.get("ACTIVITY_TYPE"));
					query.setParameter(3,modelType);
				}else{
					query.setParameter(0,condMap.get("ACTIVITY_TYPE"));
					query.setParameter(1,condMap.get("out_id"));
					query.setParameter(2,modelType);
				}
				query.addEntity(ModelFileRecord.class);
				return query.list();
				
			}
		});
	}
}
