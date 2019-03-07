package com.wonders.fzb.framework.exceptions;

import com.wonders.fzb.base.exception.FZBBaseException;

public class InvalidUserException extends FZBBaseException {

	private static final long serialVersionUID = 8347640569047391237L;

	public InvalidUserException(String errorMsg) {
		super(errorMsg);
	}

}
