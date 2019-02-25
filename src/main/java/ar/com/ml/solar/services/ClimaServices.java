package ar.com.ml.solar.services;

import ar.com.ml.solar.dto.DiaDTO;
import ar.com.ml.solar.dto.ResultadoCalculoDTO;
import ar.com.ml.solar.service.exceptions.ServiceException;

public interface ClimaServices {

	public ResultadoCalculoDTO calcularPronostico (long dias);
	
	public DiaDTO buscarClima (long dia) throws ServiceException;
	
}
