package org.ares.app.demo.common.exception;

@SuppressWarnings("serial")
public class SignException extends RuntimeException {

	public SignException() {
		super(ERR_MESSAGE);
	}

	public SignException(String message, Throwable cause) {
		super(message, cause);
	}

	public SignException(String message) {
		super(message);
	}

	public SignException(Throwable cause) {
		super(ERR_MESSAGE,cause);
	}
	
	static final String ERR_MESSAGE = "username or password is invalid.";  
    public static final String ERR_CODE = "108";  

}
