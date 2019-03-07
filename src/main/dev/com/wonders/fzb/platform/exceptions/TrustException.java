package com.wonders.fzb.platform.exceptions;

public class TrustException extends Exception {
	protected Exception _nestException;
	protected String trustId;
	protected String reason;

	/**
	 * @param reason
	 * @roseuid 3F44206503C6
	 */
	public TrustException(String reason) {
		this.reason = reason;
	}

	/**
	 * @param trustId
	 * @param reason
	 * @roseuid 3F4420630373
	 */
	public TrustException(String trustId, String reason) {
		this.trustId = trustId;
		this.reason = reason;

	}

	/**
	 * @param e
	 * @roseuid 3F4420450352
	 */
	public TrustException(Exception e) {
		_nestException = e;
		if (e != null)
			e.printStackTrace();

	}

	/**
	 * @roseuid 3F44203D0260
	 */
	public TrustException() {

	}

	/**
	 * @return String
	 * @roseuid 3F4420840136
	 */
	public String toString() {
		if (_nestException != null)
			return _nestException.toString();
		return super.toString();
	}

	/**
	 * @return String
	 * @roseuid 3F4420870112
	 */
	public String getMessage() {
		return (reason == null) ? "" : reason;
	}
}
