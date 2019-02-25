package ar.com.ml.solar.dto;

import java.util.Map;

public class ResultadoCalculoDTO {

	private Map<ClimaEnum,Long> totales;
	private DiaDTO diaConMayorLluvia;
	
	public ResultadoCalculoDTO(DiaDTO diaMayorPrecipitacion, Map<ClimaEnum,Long> totales) {
		this.setTotales(totales);
		this.setDiaConMayorLluvia(diaMayorPrecipitacion);
	}

	public Map<ClimaEnum, Long> getTotales() {
		return totales;
	}

	public void setTotales(Map<ClimaEnum, Long> totales) {
		this.totales = totales;
	}

	public DiaDTO getDiaConMayorLluvia() {
		return diaConMayorLluvia;
	}

	public void setDiaConMayorLluvia(DiaDTO diaConMayorLluvia) {
		this.diaConMayorLluvia = diaConMayorLluvia;
	}

}
