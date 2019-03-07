package com.wonders.fzb.platform.kit;

/**
 * 单位大小比较类
 * <p>Title: 市府核心系统——业务监管平台</p>
 * <p>Description: 单位大小比较</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Wonders</p>
 * @author Moyunzhe
 * @version 1.0
 */
import java.util.Comparator;

import com.wonders.fzb.platform.beans.TeamInfoOld;

public class UnitComparator implements Comparator {

	public UnitComparator() {
	}

	@Override
	public int compare(Object paramT1, Object paramT2) {
		// TODO Auto-generated method stub
		int paramT1No = ((TeamInfoOld) paramT1).getTeamOrder();
		int paramT2No = ((TeamInfoOld) paramT2).getTeamOrder();
		return paramT1No - paramT2No;
	}

}
