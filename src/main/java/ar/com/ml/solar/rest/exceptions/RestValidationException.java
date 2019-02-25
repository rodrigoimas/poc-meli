package ar.com.ml.solar.rest.exceptions;

public class RestValidationException extends Exception {
	
	private static final long serialVersionUID = -3379879454741697513L;

	public RestValidationException() {
		super();
	}

	public RestValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestValidationException(String message) {
		super(message);
	}

	public RestValidationException(Throwable cause) {
		super(cause);
	}
	
}
