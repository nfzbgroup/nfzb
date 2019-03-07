package com.wonders.fzb.framework.exceptions;

public class CodeException extends Exception {

	public CodeException() {
	}

	public CodeException(Exception e) {
		ex = e;
	}

	public String toString() {
		return ex.toString();
	}

	private Exception ex;
}
