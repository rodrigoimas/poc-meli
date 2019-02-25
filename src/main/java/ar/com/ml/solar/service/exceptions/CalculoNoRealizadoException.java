package ar.com.ml.solar.service.exceptions;

public class CalculoNoRealizadoException extends ServiceException {

	private static final long serialVersionUID = 3921489116591517472L;

	public CalculoNoRealizadoException() {
		super ("Debe calcular el pronóstico antes de realizar cualquier otra operacion. Ejecute GET /pronostico con el parámetro (queryparam) dias");
	}
	
}
