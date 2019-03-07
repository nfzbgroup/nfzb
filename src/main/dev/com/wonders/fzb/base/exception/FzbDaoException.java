package com.wonders.fzb.base.exception;

public class FzbDaoException extends FZBBaseException {

	private static final long serialVersionUID = 7590602878185539084L;

	public FzbDaoException(String errorMsg) {
		super(errorMsg);
	}

	public FzbDaoException(Throwable cause) {
		super(cause);
	}
}