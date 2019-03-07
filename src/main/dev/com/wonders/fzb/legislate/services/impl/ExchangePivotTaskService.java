package com.wonders.fzb.legislate.services.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.fzb.base.kit.SpringKit;
import com.wonders.fzb.legislate.beans.ExchangePivot;
import com.wonders.fzb.legislate.services.DraftService;
import com.wonders.fzb.legislate.services.ExchangePivotService;
import com.wonders.fzb.legislate.services.PlanService;

@Service("exchangePivotTaskService")
public class ExchangePivotTaskService implements Runnable,InitializingBean{
	@Autowired
	ExchangePivotService exchangePivotService;
	@Autowired
	PlanService planService;
	@Autowired
	DraftService draftService;
	
	Thread workThread;
	
	Map<String,Object> servicesMap = new HashMap<String, Object>();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		servicesMap.put("planService", planService);
		servicesMap.put("draftService", draftService);
		
		workThread = new Thread(this, "ExchangePivotTaskService");
//		workThread.start();
	}
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(2000);
				
				Map<String, Object> condMap = new HashMap<String, Object>();
				Map<String, String> sortMap = new HashMap<String, String>();
				
				condMap.put("recvStatus", "init");
				
				List<ExchangePivot> exchangePivots = exchangePivotService.findByList(condMap , sortMap );
				for (ExchangePivot exchangePivot : exchangePivots) {
					exchangePivot.setRecvStatus("done");
					exchangePivotService.update(exchangePivot);
					
					String parserClassName  = exchangePivot.getParser_class_name();
					if(parserClassName != null){
						String className = parserClassName.split("#")[0];
						String methodName = parserClassName.split("#")[1];
						
						Object service = servicesMap.get(className);
						if(service != null){
							Method method = service.getClass().getMethod(methodName, ExchangePivot.class);
							method.invoke(service, exchangePivot);
						}
					}
					
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
