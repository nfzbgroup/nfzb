package com.wonders.fzb.legislation.services.impl;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.LegislationExample;
import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.dao.LegislationExampleDao;
import com.wonders.fzb.legislation.services.LegislationExampleService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * LegislationExample service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationExampleService")
@Transactional
public class LegislationExampleServiceImpl implements LegislationExampleService {

	@Autowired
	private LegislationExampleDao legislationExampleDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationExample info) {
		legislationExampleDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationExample info) {
		return legislationExampleDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationExample info) {
		legislationExampleDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationExample info) {
		legislationExampleDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationExample findById(String id) {
		return (LegislationExample) legislationExampleDao.load(id);
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
		return legislationExampleDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationExample> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationExampleDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationExample info) {
		legislationExampleDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationExample> findByHQL(String hql) {
		List<LegislationExample> legislationExampleList = legislationExampleDao.findByHQL(hql);
		return legislationExampleList;
	}

	@Override
	public List<Map> queryLegislationExampleFilesList(String stNodeId, List<LegislationFiles> legislationFilesList) {
		List<Map> legislationExampleFilesList =new ArrayList<>();
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stNode", stNodeId);
		condMap.put("stStatus", "USED");
		sortMap.put("stExampleId", "ASC");
		List<LegislationExample> legislationExampleList = findByList(condMap, sortMap);
		legislationExampleList.forEach((LegislationExample legislationExample)->{
			Map map=new HashMap();
			map.put("stExampleId",legislationExample.getStExampleId());
			map.put("stExampleName",legislationExample.getStExampleName());
			map.put("stFileNo",legislationExample.getStFileNo());
			map.put("stNeed",legislationExample.getStNeed());
			map.put("stNodeStatus",legislationExample.getStNodeStatus());
			if(legislationFilesList!=null&&legislationFilesList.size()>0){
				legislationFilesList.forEach((LegislationFiles legislationFiles)->{
					if(null!=legislationFiles.getStSampleId()&&
							legislationExample.getStExampleId().equals(legislationFiles.getStSampleId())){
						map.put("fileId",legislationFiles.getStFileId());
						map.put("fileName",legislationFiles.getStTitle());
						map.put("fileUrl",legislationFiles.getStFileUrl());
					}
				});
			}else{
				map.put("fileId",null);
				map.put("fileName",null);
				map.put("fileUrl",null);
			}
			legislationExampleFilesList.add(map);
		});
		return legislationExampleFilesList;
	}

	
	@Override
	public List<Map> queryLegislationExampleFilesListByNodeStatus(String stNodeId,String nodeStatus, List<LegislationFiles> legislationFilesList) {
		List<Map> legislationExampleFilesList =new ArrayList<>();
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stNode", stNodeId);
		condMap.put("stNodeStatus", nodeStatus);
		sortMap.put("stExampleId", "ASC");
		List<LegislationExample> legislationExampleList = findByList(condMap, sortMap);
		legislationExampleList.forEach((LegislationExample legislationExample)->{
			Map map=new HashMap();
			map.put("stExampleId",legislationExample.getStExampleId());
			map.put("stExampleName",legislationExample.getStExampleName());
			map.put("stNeed",legislationExample.getStNeed());
			map.put("stNodeStatus",legislationExample.getStNodeStatus());
			if(legislationFilesList!=null&&legislationFilesList.size()>0){
				legislationFilesList.forEach((LegislationFiles legislationFiles)->{
					if(null!=legislationFiles.getStSampleId()&&
							legislationExample.getStExampleId().equals(legislationFiles.getStSampleId())){
						map.put("fileId",legislationFiles.getStFileId());
						map.put("fileName",legislationFiles.getStTitle());
						map.put("fileUrl",legislationFiles.getStFileUrl());
					}
				});
			}else{
				map.put("fileId",null);
				map.put("fileName",null);
				map.put("fileUrl",null);
			}
			legislationExampleFilesList.add(map);
		});
		return legislationExampleFilesList;
	}
	
	@Override
	public void saveExampleFile(HttpServletRequest request, HttpSession session, File upload, String uploadFileName) throws IOException {
		String stExampleId=request.getParameter("stExampleId");
		String stExampleName=request.getParameter("stExampleName");
		String stNeed=request.getParameter("stNeed");
		String stNodeId=request.getParameter("stNodeId");
		String[] node=stNodeId.split("__");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userId=currentPerson.getUserId();
		String userName=currentPerson.getName();
		if(StringUtils.isEmpty(stExampleId)){
			LegislationExample legislationExample=new LegislationExample();
			legislationExample.setStExampleName(stExampleName);
			legislationExample.setStCreateId(userId);
			legislationExample.setStCreateName(userName);
			legislationExample.setDtCreateDate(new Date());
			legislationExample.setStStatus("USED");
			legislationExample.setStNeed(stNeed);
			if(null!=upload){
				legislationExample.setStFileNo(uploadFileName);
				legislationExample.setBlContent(FileUtils.readFileToByteArray(upload));
			}
			legislationExample.setStNode(node[0]);
			if(node.length>1){
				legislationExample.setStNodeStatus(node[1]);
			}
			add(legislationExample);
		}else{
			LegislationExample legislationExample=findById(stExampleId);
			legislationExample.setStExampleName(stExampleName);
			legislationExample.setStNeed(stNeed);
			if(null!=upload){
				legislationExample.setStFileNo(uploadFileName);
				legislationExample.setBlContent(FileUtils.readFileToByteArray(upload));
			}else {
				String stFileNo=request.getParameter("stFileNo");
				if(StringUtils.isEmpty(stFileNo)){
					legislationExample.setStFileNo(null);
					legislationExample.setBlContent(null);
				}
			}
			update(legislationExample);
		}
	}
}
