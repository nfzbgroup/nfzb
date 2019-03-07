package com.wonders.fzb.base.exception;

import java.util.ArrayList;
import java.util.List;

import com.wonders.fzb.base.kit.StringKit;

public class FZBBaseException extends Exception {
	private static final long serialVersionUID = 6451677312433022035L;

	public FZBBaseException(String errorMsg) {
		super(errorMsg);
	}

	public FZBBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public FZBBaseException(Throwable cause) {
		super(cause);
	}

//	@Override
//	public String getMessage() {
//		StackTraceElement ste = this.getStackTrace()[0];
//		List<String> msgList = new ArrayList<String>();
//		msgList.add("Class = " + ste.getClassName());
//		msgList.add("Line = " + ste.getLineNumber());
//		msgList.add("Method = " + ste.getMethodName());
//		String errorDetails = StringKit.join(msgList, "，");
//		return super.getMessage() + "\r\n\t" + errorDetails;
//	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}
}
