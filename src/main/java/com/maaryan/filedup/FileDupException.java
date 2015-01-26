package com.maaryan.filedup;

public class FileDupException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileDupException() {
		super();

	}

	public FileDupException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public FileDupException(String message, Throwable cause) {
		super(message, cause);

	}

	public FileDupException(String message) {
		super(message);

	}

	public FileDupException(Throwable cause) {
		super(cause);

	}

}
