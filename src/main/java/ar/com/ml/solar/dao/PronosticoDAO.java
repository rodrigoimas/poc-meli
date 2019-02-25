package ar.com.ml.solar.dao;

import ar.com.ml.solar.model.Pronostico;

public interface PronosticoDAO {
	
	public Pronostico buscarPronostico (long dia);
	public void grabarPronostico (Pronostico pronostico);
	public void borrarPronosticos();
}
