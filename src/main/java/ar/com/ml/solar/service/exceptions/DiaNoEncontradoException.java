package ar.com.ml.solar.service.exceptions;

public class DiaNoEncontradoException extends ServiceException {

	private static final long serialVersionUID = -3159319604343398906L;

	public DiaNoEncontradoException() {
		super ("El dia buscado no fue pronosticado.");
	}
	
}
