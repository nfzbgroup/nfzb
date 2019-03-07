package com.wonders.fzb.legislate.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.LegislateConst;
import com.wonders.fzb.legislate.beans.Draft;
import com.wonders.fzb.legislate.beans.DraftTask;
import com.wonders.fzb.legislate.beans.ExchangePivot;
import com.wonders.fzb.legislate.beans.Fbd;
import com.wonders.fzb.legislate.beans.FileRecord;
import com.wonders.fzb.legislate.beans.Plan;
import com.wonders.fzb.legislate.beans.PlanTask;
import com.wonders.fzb.legislate.dao.DraftDao;
import com.wonders.fzb.legislate.dao.DraftDealDao;
import com.wonders.fzb.legislate.dao.DraftTaskDao;
import com.wonders.fzb.legislate.dao.ExchangePivotDao;
import com.wonders.fzb.legislate.dao.FbdDao;
import com.wonders.fzb.legislate.dao.FileRecordDao;
import com.wonders.fzb.legislate.services.DraftService;

/**
 * Draft service实现
 * 
 * @author scalffold created by lj
 */
@Service("draftService")
@Transactional
public class DraftServiceImpl implements DraftService{
	@Autowired
	DraftDao draftDao;
	@Autowired
	DraftTaskDao draftTaskDao;
	@Autowired
	DraftDealDao draftDealDao;
	@Autowired
	FbdDao fbdDao;
	@Autowired
	ExchangePivotDao exchangePivotDao;
	@Autowired
	FileRecordDao fileRecordDao;

	/**
	 * 添加实体对象
	 */
	@Override
	public void add(Draft info) {
		draftDao.save(info);
	}
	

	/**
	 * 更新实体对象
	 */
	@Override
	public void update(Draft info) {
		draftDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(Draft info) {
		draftDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public Draft findById(String id) {
		return (Draft) draftDao.load(id);
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
		return draftDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<Draft> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return draftDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(Draft info) {
		draftDao.saveOrUpdate(info);
	}


	@Override
	public void updateBySend(Draft draft) {
		
		String teamId = draft.getTeamId();
		boolean isLFC = teamId != null && (teamId.equals(LegislateConst.TEAM_ID_JJFGC)|| teamId.equals(LegislateConst.TEAM_ID_CJFGC) || teamId
				.equals(LegislateConst.TEAM_ID_SHFGC));
		if(isLFC){
			draft.setStatus(LegislateConst.STATUS_DISE);
		}
		//更新草案状态
		draftDao.update(draft);
		//产生报送任务
		DraftTask task = new DraftTask();
		task.setDraft(draft);
		//报送任务、已经结束
		task.setTaskType(LegislateConst.STATUS_SEND);
		task.setStatus(LegislateConst.TASK_STATUS_DONE);
		task.setInstructions("已报送");
		task.setProcessTime(new Date());
		task.setTaskTime(draft.getCreateTime());
		task.setUserId(draft.getUpdateId());
		task.setUserName(draft.getUpdateName());
		task.setTeamId(draft.getTeamId());
		task.setTeamName(draft.getTeamName());
		
		draftTaskDao.save(task );
		
		JSONObject data = new JSONObject();
		data.element("draft", draft);
		data.element("sendTask", task);
		
		if(isLFC){
			DraftTask diseTask = new DraftTask();
			diseTask.setDraft(draft);
			
			//产生分办任务
			diseTask.setTaskType(LegislateConst.STATUS_DISE);
			diseTask.setStatus(LegislateConst.TASK_STATUS_DONE);
			diseTask.setTaskTime(new Date());
			//综合处分办
			diseTask.setTeamId(LegislateConst.TEAM_ID_ZHC);
			diseTask.setTeamName(draft.getTeamName());
			diseTask.setProcessTime(new Date());
			diseTask.setUserId(draft.getCreatorId());
			diseTask.setInstructions("自动分办");
			diseTask.setUserName(draft.getCreatorName());
			
			Map<String, String> sortMap = new LinkedHashMap<String, String>();
			sortMap.put("seq",  CommonConst.ORDER_DESC);
			Map<String, Object> condMap = new LinkedHashMap<String, Object>();
			int year =  Calendar.getInstance().get(Calendar.YEAR);
			condMap.put("year", String.valueOf(year));
			
			int seq = 1;
			try {
				Page<?> pages=fbdDao.findByPage(condMap, sortMap, 1, 1);
				if(pages.getTotalSize() != 0){
				   Fbd fbd=(Fbd)pages.getResult().get(0);
				   seq = Integer.parseInt(fbd.getSeq()) + 1;
				}
			} catch (FzbDaoException e) {
				e.printStackTrace();
			}
			Fbd fbd = new Fbd();
			fbd.setFbdId("DF"+year+"规"+String.format("%05d",seq));
			fbd.setSeq(String.format("%05d",seq));
			fbd.setYear(String.valueOf(year));
			fbd.setTeamId(draft.getTeamId());
			fbd.setReceiveTime(new Date());
			fbd.setProcessTime(new Date());
			fbd.setUserId(draft.getCreatorId());
			fbd.setUserName(draft.getCreatorName());
			fbd.setTeamId(teamId);
			fbd.setTeamName(draft.getTeamName());
			fbd.setInstructions("自动分办");
			fbdDao.save(fbd);
			draft.setFbd(fbd);
			draftDao.update(draft);
			updateByDise(diseTask, fbd, teamId);
		}else{
			//产生待办的接收任务
			task = new DraftTask();
			task.setDraft(draft);
			task.setStatus(LegislateConst.TASK_STATUS_NEW);
			//接收任务
			task.setTaskType(LegislateConst.STATUS_RECEIVE);
			task.setTaskTime(new Date());
			//综合处
			task.setTeamId(LegislateConst.TEAM_ID_ZHC);
			task.setTeamName("综合处");
			
			draftTaskDao.save(task);
			data.element("receiveTask", task);
		}
		
		Map<String, String> sortMap = new HashMap<String, String>();
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		condMap.put("outId",  draft.getDraftId());
		condMap.put("activityType",  LegislateConst.ACTIVITY_TYPE_Draft_ADD);
		List<FileRecord> draftFiles = fileRecordDao.findByList(condMap, sortMap );
		data.element("draftFiles", draftFiles);
		ExchangePivot exchangePivot = new ExchangePivot();
		try {
			exchangePivot.setFileContent(data.toString().getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		exchangePivot.setInsertDate(new Date());
		exchangePivot.setSendUserId(draft.getUpdateId());
		exchangePivot.setSendUserName(draft.getUpdateName());
		exchangePivot.setModule("规则草案");
		exchangePivot.setType("上报");
		exchangePivot.setInfoId(draft.getDraftId());
		exchangePivot.setParser_class_name("draftService#updateBySend");
		exchangePivot.setRecvStatus("init");
		exchangePivotDao.saveOrUpdate(exchangePivot );
		
	}


	@Override
	public void updateByReceive(DraftTask task) {
		Draft draft = task.getDraft();
		draftDao.update(task.getDraft());
		draftTaskDao.saveOrUpdate(task );
		
		DraftTask diseTask = new DraftTask();
		diseTask.setDraft(draft);
		
		//产生分办任务
		diseTask.setTaskType(LegislateConst.STATUS_DISE);
		diseTask.setStatus(LegislateConst.TASK_STATUS_NEW);
		diseTask.setTaskTime(new Date());
		//综合处分办
		diseTask.setTeamId(LegislateConst.TEAM_ID_ZHC);
		
		draftTaskDao.save(diseTask );
	}


	@Override
	public void updateByDise(DraftTask task, Fbd fbd,String teamId) {
		Draft draft = task.getDraft();
		fbdDao.saveOrUpdate(fbd);
		draftDao.update(task.getDraft());
		draftTaskDao.saveOrUpdate(task );
		
		DraftTask diseTask = new DraftTask();
		diseTask.setDraft(draft);
		
		//产生分办任务
		diseTask.setTaskType(LegislateConst.STATUS_CLAIM);
		diseTask.setStatus(LegislateConst.TASK_STATUS_NEW);
		diseTask.setTaskTime(new Date());
		//综合处分办
		diseTask.setTeamId(teamId);
		draftTaskDao.save(diseTask );
	}


	@Override
	public void updateByClaim(DraftTask task) {
		Draft draft = task.getDraft();
		draftDao.update(task.getDraft());
		draftTaskDao.saveOrUpdate(task );
		DraftTask diseTask = new DraftTask();
		diseTask.setDraft(draft);
		//产生处理任务
		diseTask.setTaskType(LegislateConst.STATUS_PROCESS);
		diseTask.setStatus(LegislateConst.TASK_STATUS_NEW);
		diseTask.setTaskTime(new Date());
		diseTask.setTeamId(task.getTeamId());
		draftTaskDao.save(diseTask );
	}
	
	public void updateBySend(ExchangePivot exchangePivot) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(new String(exchangePivot.getFileContent(),"utf-8"));
			Draft draft = (Draft) JSONObject.toBean(jsonObject.optJSONObject("draft"), Plan.class);
			this.draftDao.save(draft);
			DraftTask receiceTask = (DraftTask) JSONObject.toBean(jsonObject.optJSONObject("receiceTask"), PlanTask.class);
			receiceTask.setDraft(draft);
			this.draftTaskDao.save(receiceTask);
			DraftTask sendTask = (DraftTask) JSONObject.toBean(jsonObject.optJSONObject("sendTask"), PlanTask.class);
			receiceTask.setDraft(draft);
			this.draftTaskDao.save(sendTask);
			
			JSONArray draftFiles = jsonObject.optJSONArray("draftFiles");
			for (int i = 0; i < draftFiles.size(); i++) {
				JSONObject file = draftFiles.optJSONObject(i);
				FileRecord fileRecord = (FileRecord) JSONObject.toBean(file, FileRecord.class);
				this.fileRecordDao.save(fileRecord);
			}
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
}

