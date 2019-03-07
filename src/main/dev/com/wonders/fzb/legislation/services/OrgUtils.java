package com.wonders.fzb.legislation.services;

import java.util.ArrayList;
import java.util.List;

import com.wonders.fzb.framework.beans.MOR;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.legislate.LegislateConst;

public class OrgUtils {
	/**
	 * 获取法制办处室人员
	 * @param teamInfoService
	 * @return
	 */
	public static List<MOR> getFzbMors(TeamInfoService teamInfoService){
		List<MOR> morList = new ArrayList<MOR>();
		List<MOR> ls = teamInfoService.findMorSubList(LegislateConst.SYSTEM_MODULE, "U0");
		for (MOR mor : ls) {
			if(!"U_1_0".equals(mor.getTeamCid()) && "市法制办处室".equals( mor.getOrgType())){
				morList.add(mor);
			}
		}
		return morList;
	}
	/**
	 * 是否为法制办处室
	 * @param teamInfoService
	 * @param teamId
	 * @return
	 */
	public static boolean isFzb(TeamInfoService teamInfoService,String teamId){
		List<MOR> morList = getFzbMors(teamInfoService);
		for (MOR mor : morList) {
			if(mor.getTeamCid().equals(teamId)){
				return true;
			}
		}
		return false;
	}
}
