package ar.com.ml.solar.rest;

import ar.com.ml.solar.rest.exceptions.RestValidationException;
import spark.Request;

public class RestValidator {
    public static void validate (Request request, String paramName) throws RestValidationException {
    		if (null==request.queryParamsValues(paramName))
	        	{
	        		throw new RestValidationException (paramName + " no recibido.");
	        	};
	        for (String valor : request.queryParamsValues(paramName)) {
				System.out.println("Valor del parametro \"" + paramName + "\": " + valor);
			}
        	
    };
}
