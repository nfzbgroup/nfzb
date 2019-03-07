package com.wonders.fzb.framework.exceptions;

public class ServiceException extends Exception {
	public Exception m_exception;

	public ServiceException(String msg, Exception e) {
		super(msg);
		m_exception = e;
	}

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(Exception e) {
		super(e.getMessage());
		m_exception = e;
	}
}
