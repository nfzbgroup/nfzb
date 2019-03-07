/**
 * 
 */
package com.wonders.fzb.platform.kit;

import java.util.Comparator;

import com.wonders.fzb.platform.beans.OpInfo;

/**
 * @author Administrator
 *
 */
public class OpInfoComparator implements Comparator {
	public OpInfoComparator() {
	}

	public int compare(Object parm1, Object parm2) {
		/** @todo Implement this java.util.Comparator method */
		OpInfo op1 = (OpInfo) parm1;
		OpInfo op2 = (OpInfo) parm2;
		try {
			return op1.getOpId().compareTo(op2.getOpId());
		} catch (Exception e) {
			return -1;
		}
	}
}
