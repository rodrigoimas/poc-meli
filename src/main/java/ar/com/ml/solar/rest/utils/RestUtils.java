package ar.com.ml.solar.rest.utils;

import static spark.Spark.halt;

import com.google.gson.Gson;

import spark.Response;

public class RestUtils {

	public static String responderJson (Response res, int httpCode, Object obj)	
		{
	    Gson gson = new Gson();
		res.status(httpCode);
		res.type("application/json");
	    res.body(gson.toJson(obj));
	    System.out.println("Devolviendo respuesta con codigo " + httpCode + " y mensaje \"" + res.body() + "\".");
	    return res.body();
		}
}