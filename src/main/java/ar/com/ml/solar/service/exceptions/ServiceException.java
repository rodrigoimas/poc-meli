package ar.com.ml.solar.service.exceptions;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = -3379879454741697513L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
	
}
