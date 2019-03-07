package com.wonders.fzb.platform.kit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>
 * Title: 市府核心系统
 * </p>
 * <p>
 * Description: 市府内网项目
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company: wonders
 * </p>
 * 
 * @author charis shi
 * @version 1.0
 */

public class CommonTools {
	public CommonTools() {
	}

	public static ArrayList sort(ArrayList list) {
		Object[] objs = list.toArray();
		Arrays.sort(objs);
		list.clear();
		for (int i = 0; i < objs.length; i++) {
			list.add(objs[i]);
		}
		return list;
	}

	public static ArrayList sort(ArrayList list, Comparator comparator) {
		Object[] objs = list.toArray();
		Arrays.sort(objs, comparator);
		list.clear();
		for (int i = 0; i < objs.length; i++) {
			list.add(objs[i]);
		}
		return list;
	}
}
