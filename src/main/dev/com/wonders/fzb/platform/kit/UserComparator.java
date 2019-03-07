package com.wonders.fzb.platform.kit;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;

import com.wonders.fzb.platform.beans.UserInfoOld;
import com.wonders.fzb.platform.services.AccessRightService;
import com.wonders.fzb.platform.services.OrgService;

public class UserComparator implements Comparator {
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private AccessRightService accessRightService;

	private String unitId = null;

	public UserComparator() {
	}

	public UserComparator(String unitId) {
		this.unitId = unitId;
	}
	
	public int compare(Object userA, Object userB) {
		int result = 0;
		try {
			String userId1 = ((UserInfoOld) userA).userId;
			String userId2 = ((UserInfoOld) userB).userId;
			if (unitId == null) {
				result = -accessRightService.compareLevel(orgService.getUserHighestLevel(userId1), orgService.getUserHighestLevel(userId2));
				if (result == 0)
					result = orgService.getUserHighestLevelNo(userId1) - orgService.getUserHighestLevelNo(userId2);
			} else {
				result = -accessRightService.compareLevel(orgService.getUserLevel(unitId, userId1), orgService.getUserLevel(unitId, userId2));
				if (result == 0)
					result = orgService.getUserLevelNo(unitId, userId1) - orgService.getUserLevelNo(unitId, userId2);
			}
		} catch (Exception e) {
			//LogHelper.debug("UserComparator.compare:err" + e.toString());
			System.out.println("UserComparator.compare:err" + e.toString());
		}
		return result;
	}
}
