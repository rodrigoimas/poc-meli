package ar.com.ml.solar.rest;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.halt;

import ar.com.ml.solar.dto.MessageDTO;
import ar.com.ml.solar.rest.exceptions.RestValidationException;
import ar.com.ml.solar.rest.utils.RestUtils;
import ar.com.ml.solar.service.exceptions.ServiceException;
import ar.com.ml.solar.services.ClimaServices;
import ar.com.ml.solar.services.impl.ClimaServicesImpl;

public class RestController {
    public static void main(String[] args) {

        port(obtenerPuerto());
    	
    	ClimaServices climaservice = new ClimaServicesImpl();
    	
		before("/clima", (req, res) -> {
			try	{
	    		RestValidator.validate(req,"dia");
	    	} catch (RestValidationException e) {
	    		RestUtils.responderJson(res, 400, new MessageDTO("Request sin el parametro \"dia\""));
	    		halt(400, res.body());
	    	}
		});
		
		before("/pronostico", (req, res) -> {
			try	{
	    		RestValidator.validate(req,"dias");
	    	} catch (RestValidationException e) {
	    		RestUtils.responderJson(res, 400, new MessageDTO("Request sin el parametro \"dias\""));
	    		halt(400, res.body());
	    	}
		});
		
    	get("/clima", (req, res) -> {
	    	try	{
	    		RestUtils.responderJson(res, 200, climaservice.buscarClima(Integer.parseInt(req.queryParamsValues("dia")[0])));
		    	return res.body();
	    	}
	    	catch (ServiceException e)	{
	    		RestUtils.responderJson(res, 400, new MessageDTO(e.getMessage()));
		    	return res.body();
	    	}
    	});
 	
        get("/pronostico", (req, res) -> {
        	RestUtils.responderJson(res, 200, climaservice.calcularPronostico(Integer.parseInt(req.queryParamsValues("dias")[0])));
        	return res.body();
        });
        
        get("/*", (req, res) -> {
        	RestUtils.responderJson(res, 404, new MessageDTO("Recurso no encontrado."));
        	return res.body();
    });
        
    }

	private static Integer obtenerPuerto() {
		ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
		return port;
	}
}
