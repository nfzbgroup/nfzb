package com.wonders.fzb.legislate.services.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.Assessment;
import com.wonders.fzb.legislate.dao.AssessmentDao;
import com.wonders.fzb.legislate.services.AssessmentService;
@Service("assessmentService")
@Transactional
public class AssessmentServiceImpl implements AssessmentService{

	@Autowired
	private AssessmentDao daoHelper;
	
	@Override
	public void saveOrUpdate(Assessment info) {
		daoHelper.saveOrUpdate(info);
	}

	@Override
	public void add(Assessment info) {
		daoHelper.add(info);
	}

	@Override
	public void delete(Assessment info) {
		daoHelper.delete(info);
	}

	@Override
	public List<Assessment> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return daoHelper.findByList(condMap, sortMap);
	}

	@Override
	public Page<Assessment> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize) throws FzbDaoException {
		return daoHelper.findByPage(condMap, sortMap, pageNo, pageSize);
	}

	@Override
	public Assessment findById(String assessmentId) {
		return daoHelper.findById(assessmentId);
	}
	
	/**
	 *  Word Export (poi-scratchpad-3.14.jar)
	 * 
	 * @param paramMap
	 *            所需替换模板的参数  <propertyName,properyValue>
	 * @param docName
	 *            所生成.doc文件Name 
	 * @param templateId
	 *            所需模板id 
	 * @return inputStream
	 *            
	 */
	@Override
	public InputStream exportWord(Map<String,String> paramMap,String docName,String realPath) throws Exception {
		if(paramMap == null ){
			  paramMap = new HashMap<String, String>();
		}
		String templatePath = realPath;
		File file = new File(templatePath);
	    //导出word
		InputStream is = new FileInputStream(file);  
		HWPFDocument doc = new HWPFDocument(is);  
			Range range = doc.getRange();  
		//替换标记位
		for(Map.Entry<String,String> entry : paramMap.entrySet()){
		   range.replaceText(entry.getKey(), entry.getValue());
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		doc.write(baos);          
		InputStream bais = new ByteArrayInputStream(baos.toByteArray());     
		if(is != null){
		  is.close();
		}
		return bais;	      
	}

}
