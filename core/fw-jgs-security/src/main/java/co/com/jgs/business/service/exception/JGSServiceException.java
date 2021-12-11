package co.com.jgs.business.service.exception;

import co.com.jgs.business.service.input.JGSInput;

public class JGSServiceException extends Exception{

	private static final long serialVersionUID = 1L;
	private JGSInput input;
	
	public JGSServiceException(String message) {
		super(message);
	}
	
	public JGSServiceException(String message, Exception exception) {
		super(message, exception);
	}
	
	public JGSServiceException(String message, Exception exception, JGSInput input) {
		super(message, exception);
		this.input = input;
	}

	public JGSInput getInput() {
		return input;
	}

	public void setInput(JGSInput input) {
		this.input = input;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
