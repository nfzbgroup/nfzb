package com.wonders.fzb.legislate.services.impl;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.LegislateConst;
import com.wonders.fzb.legislate.beans.ExchangePivot;
import com.wonders.fzb.legislate.beans.Fbd;
import com.wonders.fzb.legislate.beans.FileRecord;
import com.wonders.fzb.legislate.beans.Plan;
import com.wonders.fzb.legislate.beans.PlanSummary;
import com.wonders.fzb.legislate.beans.PlanTask;
import com.wonders.fzb.legislate.dao.ExchangePivotDao;
import com.wonders.fzb.legislate.dao.FbdDao;
import com.wonders.fzb.legislate.dao.FileRecordDao;
import com.wonders.fzb.legislate.dao.PlanDao;
import com.wonders.fzb.legislate.dao.PlanSummaryDao;
import com.wonders.fzb.legislate.dao.PlanTaskDao;
import com.wonders.fzb.legislate.services.PlanService;

/**
 * plan service实现
 * 
 * @author scalffold created by lj
 */
@Service("planService")
@Transactional
public class PlanServiceImpl implements PlanService{
	@Autowired
	PlanDao planDao;
	@Autowired
	PlanTaskDao planTaskDao;
	@Autowired
	FbdDao fbdDao;
	@Autowired
	PlanSummaryDao planSummaryDao;
	@Autowired
	ExchangePivotDao exchangePivotDao;
	@Autowired
	FileRecordDao fileRecordDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(Plan info) {
		planDao.save(info);
	}
	

	/**
	 * 更新实体对象
	 */
	@Override
	public void update(Plan info) {
		planDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(Plan info) {
		planDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public Plan findById(String id) {
		return (Plan) planDao.load(id);
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
		return planDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<Plan> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return planDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(Plan info) {
		planDao.saveOrUpdate(info);
	}
	

	@Override
	public void updateBySend(Plan plan) {
		
		JSONObject data = new JSONObject();
		
		//更新草案状态
		planDao.update(plan);
		
		data.element("plan", plan);
		
		//产生报送任务
		PlanTask task = new PlanTask();
		task.setPlan(plan);
		//报送任务、已经结束
		task.setTaskType(LegislateConst.STATUS_SEND);
		task.setStatus(LegislateConst.TASK_STATUS_DONE);
		task.setInstructions("已报送");
		task.setProcessTime(new Date());
		task.setTaskTime(plan.getCreateTime());
		task.setUserId(plan.getUpdateId());
		task.setUserName(plan.getUpdateName());
		task.setTeamId(plan.getTeamId());
		task.setTeamName(plan.getTeamName());
		
		planTaskDao.save(task);
		
		data.element("sendTask", task);
		
		//产生待办的接收任务
		PlanTask newtask = new PlanTask();
		newtask.setPlan(plan);
		newtask.setStatus(LegislateConst.TASK_STATUS_NEW);
		//接收任务
		newtask.setTaskType(LegislateConst.STATUS_RECEIVE);
		newtask.setTaskTime(new Date());
		//综合处
		newtask.setTeamId(LegislateConst.TEAM_ID_ZHC);
		newtask.setTeamName("立法处 ");
		planTaskDao.save(newtask);
		
		data.element("receiceTask", newtask);
		
		Map<String, String> sortMap = new HashMap<String, String>();
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		condMap.put("outId",  plan.getPlanId());
		condMap.put("activityType",  LegislateConst.ACTIVITY_TYPE_Plan_ADD);
		List<FileRecord> planFiles = fileRecordDao.findByList(condMap, sortMap );
		data.element("planFiles", planFiles);
		
		ExchangePivot exchangePivot = new ExchangePivot();
		try {
			exchangePivot.setFileContent(data.toString().getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		exchangePivot.setInsertDate(new Date());
		exchangePivot.setSendUserId(plan.getUpdateId());
		exchangePivot.setSendUserName(plan.getUpdateName());
		exchangePivot.setModule("立法计划");
		exchangePivot.setType("上报");
		exchangePivot.setInfoId(plan.getPlanId());
		exchangePivot.setParser_class_name("planService#updateBySend");
		exchangePivot.setRecvStatus("init");
		exchangePivotDao.saveOrUpdate(exchangePivot );
	}
	
	@Override
	public void updateByClaim(PlanTask planTask) {
		Plan plan = planTask.getPlan();
		planDao.update(plan);
		planTaskDao.saveOrUpdate(planTask );
		PlanTask task = new PlanTask();
		task.setPlan(plan);
		//产生处理任务
		task.setTaskType(LegislateConst.STATUS_PROCESS);
		task.setStatus(LegislateConst.TASK_STATUS_NEW);
		task.setTaskTime(new Date());
		task.setTeamId(task.getTeamId());
		planTaskDao.save(task );
	}


	@Override
	public void updateByaudit(PlanTask planTask) {
		Plan plan = planTask.getPlan();
		planDao.saveOrUpdate(plan);
		
		
		planTaskDao.saveOrUpdate(planTask );
	}
	
	@Override
	public void updateByReceive(PlanTask task) {
		Plan plan = task.getPlan();
		planDao.update(task.getPlan());
		planTaskDao.saveOrUpdate(task );
		
		PlanTask diseTask = new PlanTask();
		diseTask.setPlan(plan);
		
		//产生分办任务
		diseTask.setTaskType(LegislateConst.STATUS_DISE);
		diseTask.setStatus(LegislateConst.TASK_STATUS_NEW);
		diseTask.setTaskTime(new Date());
		//综合处分办
		diseTask.setTeamId(LegislateConst.TEAM_ID_ZHC);
		
		planTaskDao.save(diseTask );
	} 
	
	@Override
	public void updateByDise(PlanTask task, Fbd fbd,String teamId) {
		Plan plan = task.getPlan();
		fbdDao.saveOrUpdate(fbd);
		planDao.update(task.getPlan());
		planTaskDao.saveOrUpdate(task );
		
		PlanTask diseTask = new PlanTask();
		diseTask.setPlan(plan);
		
		//产生分办任务
		diseTask.setTaskType(LegislateConst.STATUS_CLAIM);
		diseTask.setStatus(LegislateConst.TASK_STATUS_NEW);
		diseTask.setTaskTime(new Date());
		//综合处分办
		diseTask.setTeamId(teamId);
		planTaskDao.save(diseTask );
	}


	@Override
	public void updateBySummary(PlanTask task) {
		Plan plan = task.getPlan();
		planDao.saveOrUpdate(plan);
		String year = Calendar.getInstance().get(Calendar.YEAR) + "";
		Map<String, Object> condMap = new HashMap<String, Object>();
		condMap .put("teamId", task.getTeamId());
		condMap.put("year", year);
		PlanSummary planSummary = new PlanSummary();
		Map<String, String> sortMap = new HashMap<String, String>();
		List<PlanSummary> planSummaryList = planSummaryDao.findByList(condMap, sortMap );
		if(!planSummaryList.isEmpty()){
			planSummary = planSummaryList.get(0);
		}else{
			planSummary.setFirstTime(new Date());
			planSummary.setYear(year);
			planSummary.setTeamId(task.getTeamId());
			planSummary.setTeamName(task.getTeamName());
			planSummary.setFirstTime(new Date());
		}
		planSummary.setUserId(plan.getUpdateId());
		planSummary.setUserName(plan.getUpdateName());
		planSummary.setLastTime(new Date());
		planSummaryDao.saveOrUpdate(planSummary);
		plan.setPlanSummary(planSummary);
		planDao.saveOrUpdate(plan);
	}


	@Override
	public void updateBySend(ExchangePivot exchangePivot) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(new String(exchangePivot.getFileContent(),"utf-8"));
			Plan plan = (Plan) JSONObject.toBean(jsonObject.optJSONObject("plan"), Plan.class);
			this.planDao.save(plan);
			PlanTask receiceTask = (PlanTask) JSONObject.toBean(jsonObject.optJSONObject("receiceTask"), PlanTask.class);
			receiceTask.setPlan(plan);
			this.planTaskDao.save(receiceTask);
			PlanTask sendTask = (PlanTask) JSONObject.toBean(jsonObject.optJSONObject("sendTask"), PlanTask.class);
			sendTask.setPlan(plan);
			this.planTaskDao.save(sendTask);
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
}
