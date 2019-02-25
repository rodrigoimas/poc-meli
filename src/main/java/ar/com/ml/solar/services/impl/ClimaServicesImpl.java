package ar.com.ml.solar.services.impl;

import java.util.HashMap;
import java.util.Map;

import ar.com.ml.solar.dao.PronosticoDAO;
import ar.com.ml.solar.dao.impl.PronosticoDAOImpl;
import ar.com.ml.solar.dto.ClimaEnum;
import ar.com.ml.solar.dto.DiaDTO;
import ar.com.ml.solar.dto.OrientacionEnum;
import ar.com.ml.solar.dto.PlanetaConfiguracionDTO;
import ar.com.ml.solar.dto.PlanetaDTO;
import ar.com.ml.solar.dto.PlanetaEnum;
import ar.com.ml.solar.dto.PosicionDTO;
import ar.com.ml.solar.dto.ResultadoCalculoDTO;
import ar.com.ml.solar.model.Pronostico;
import ar.com.ml.solar.service.exceptions.CalculoNoRealizadoException;
import ar.com.ml.solar.service.exceptions.DiaNoEncontradoException;
import ar.com.ml.solar.service.exceptions.ServiceException;
import ar.com.ml.solar.services.ClimaServices;
import ar.com.ml.solar.services.impl.utils.UtilClima;

public class ClimaServicesImpl implements ClimaServices {

	Map<PlanetaEnum, PlanetaConfiguracionDTO> mapaConfig;
	Map<Long,DiaDTO> dias;
	Map<ClimaEnum,Long> total;
	PosicionDTO posicionSol;
	PronosticoDAO dao = new PronosticoDAOImpl();
	
	@Override
	public ResultadoCalculoDTO calcularPronostico (long dias) {
		inicializarConfiguracion();
		borrarPronosticoPersistido();
		calcularPosiciones(dias);
		calcularClima();
		return new ResultadoCalculoDTO(obtenerDiaMayorLluvia(), this.total);
	}

	@Override
	public DiaDTO buscarClima(long dia) throws ServiceException {
		if (null==dias)	{
			throw new CalculoNoRealizadoException();
		}
		if (null==dias.get(Long.valueOf(dia)))	{
			throw new DiaNoEncontradoException();
		}
		return dias.get(Long.valueOf(dia));
	}

	private void inicializarConfiguracion() {
		mapaConfig = new HashMap<PlanetaEnum, PlanetaConfiguracionDTO>();
		mapaConfig.put(PlanetaEnum.FERENGI, new PlanetaConfiguracionDTO(PlanetaEnum.FERENGI,500.0d,OrientacionEnum.HORARIA, 1));
		mapaConfig.put(PlanetaEnum.BETASOIDE, new PlanetaConfiguracionDTO(PlanetaEnum.BETASOIDE,2000.0d,OrientacionEnum.HORARIA, 3));
		mapaConfig.put(PlanetaEnum.VULCANO, new PlanetaConfiguracionDTO(PlanetaEnum.VULCANO,1000.0d,OrientacionEnum.ANTIHORARIA, 5));
		dias = new HashMap<Long, DiaDTO>();
		total = new HashMap<ClimaEnum, Long>();
		posicionSol = new PosicionDTO (new PlanetaDTO(PlanetaEnum.SOL,0),90);
	}

	
	private DiaDTO obtenerDiaMayorLluvia() {
		Long diaEncontrado = Long.valueOf(-1);
		for (DiaDTO dia : dias.values()) {
			if (dia.getClima().equals(ClimaEnum.LLUVIA))	{
				if (diaEncontrado<0)	{
					diaEncontrado = dia.getNumero();
				}
				if (dia.compareTo(dias.get(diaEncontrado))>0)	{
					diaEncontrado = dia.getNumero();
				}
			}
		}
		if (diaEncontrado>=0)	{
			return dias.get(diaEncontrado);
		} else	{
			return null;
		}
	}

	private void calcularPosiciones(long cantdias) {
		for (long i = 0; i < cantdias; i++) {
			if (i==0)	{
				dias.put(i+1, calcularDia(i+1,null));
			} else
			{
				dias.put(i+1, calcularDia(i+1,dias.get(i)));
			}
		}
	}

	private DiaDTO calcularDia(long dianro, DiaDTO diaanterior) {
		DiaDTO dia = new DiaDTO (dianro, mapaConfig);
		mapaConfig.forEach((nombrePlaneta,config) -> { calcularPosicion(dia, config, diaanterior); });
		return dia;
	}

	public void calcularPosicion (DiaDTO dia, PlanetaConfiguracionDTO config, DiaDTO diaanterior)	{
		if (null==diaanterior)	{
			dia.getPosicion(config.getNombre()).setGrados(UtilClima.calcularGrados(config.getVelocidad(), config.getOrientacion(), dia.getPosicion(config.getNombre()).getGrados()));
		} else	{
			dia.getPosicion(config.getNombre()).setGrados(UtilClima.calcularGrados(config.getVelocidad(), config.getOrientacion(), diaanterior.getPosicion(config.getNombre()).getGrados()));
		}
	}
	
	private void calcularClima() {
		dias.forEach((dia,diaobj) -> {
			calcularClima(diaobj);
			persistirClima(diaobj);
			if (null==this.total.get(diaobj.getClima()))	{
				this.total.put(diaobj.getClima(),Long.valueOf(1));
			} else {
				this.total.put(diaobj.getClima(),Long.valueOf( this.total.get(diaobj.getClima())+1 ));
			}
			
		});
	}

	private void persistirClima(DiaDTO diaobj) {
		Pronostico pronostico = new Pronostico();
		pronostico.setDia(diaobj.getNumero());
		pronostico.setClima(diaobj.getClima().name());
		dao.grabarPronostico(pronostico);
	}

	private void borrarPronosticoPersistido() {
		dao.borrarPronosticos();
	}
	
	private void calcularClima(DiaDTO dia) {
		System.out.println("Dia nro.=" + dia.getNumero() + ", p1=" + dia.getPosicionPlaneta1().getGrados() + ", p2=" + dia.getPosicionPlaneta2().getGrados() + ", p3=" + dia.getPosicionPlaneta3().getGrados());
		if (UtilClima.esSequia(dia))	{
			dia.setClima(ClimaEnum.SEQUIA);
			System.out.println("\n-----------HAY SEQUIA EN EL DIA-------------");
			System.out.println(dia+"\n");
		} else if (UtilClima.esOptimo (dia, posicionSol)) {
			dia.setClima(ClimaEnum.OPTIMO);
			System.out.println("\n-----------HAY CLIMA ÓPTIMO EN EL DIA-------------");
			System.out.println(dia+"\n");
		} else if (UtilClima.esLluvia (dia, posicionSol)) {
			dia.setClima(ClimaEnum.LLUVIA);
			System.out.println("\n-----------HAY LLUVIA EN EL DIA-------------");
			System.out.println(dia + "\n");
		}
	}


}
