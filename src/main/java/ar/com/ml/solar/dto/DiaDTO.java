package ar.com.ml.solar.dto;

import java.util.Map;

public class DiaDTO implements Comparable<DiaDTO> {

	ClimaEnum clima;
	PosicionDTO posicionPlaneta1;
	PosicionDTO posicionPlaneta2;
	PosicionDTO posicionPlaneta3;
	long numero;

	public DiaDTO (long numero, Map<PlanetaEnum, PlanetaConfiguracionDTO> mapaConfig)	{
		super();
		this.setNumero(numero);
		this.setClima(ClimaEnum.INDEFINIDO);
		this.posicionPlaneta1 = new PosicionDTO(new PlanetaDTO (PlanetaEnum.BETASOIDE, mapaConfig.get(PlanetaEnum.BETASOIDE).getRadio()), 90);
		this.posicionPlaneta2 = new PosicionDTO(new PlanetaDTO (PlanetaEnum.FERENGI, mapaConfig.get(PlanetaEnum.FERENGI).getRadio()), 90);
		this.posicionPlaneta3 = new PosicionDTO(new PlanetaDTO (PlanetaEnum.VULCANO, mapaConfig.get(PlanetaEnum.VULCANO).getRadio()), 90);
	}
	
	public PosicionDTO getPosicionPlaneta1() {
		return posicionPlaneta1;
	}

	public void setPosicionPlaneta1(PosicionDTO posicionPlaneta1) {
		this.posicionPlaneta1 = posicionPlaneta1;
	}

	public PosicionDTO getPosicionPlaneta2() {
		return posicionPlaneta2;
	}

	public void setPosicionPlaneta2(PosicionDTO posicionPlaneta2) {
		this.posicionPlaneta2 = posicionPlaneta2;
	}

	public PosicionDTO getPosicionPlaneta3() {
		return posicionPlaneta3;
	}

	public void setPosicionPlaneta3(PosicionDTO posicionPlaneta3) {
		this.posicionPlaneta3 = posicionPlaneta3;
	}

	public ClimaEnum getClima() {
		return clima;
	}

	public void setClima(ClimaEnum clima) {
		this.clima = clima;
	}

	public PosicionDTO getPosicion (PlanetaEnum planeta)	{
		switch (planeta)	{
		case BETASOIDE:
			return posicionPlaneta1;
		case FERENGI:
			return this.posicionPlaneta2;
		case VULCANO:
			return this.posicionPlaneta3;
		default:
			return null;
		}
	}
	
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	
	public String toString()	{
		return new StringBuilder().append("Dia{")
				.append("numero: ").append(this.getNumero())
				.append(",clima=").append(this.getClima().name())
				.append(",perimetro=").append(this.getPerimetro())
				.append(",posicion1=").append(this.getPosicion(PlanetaEnum.BETASOIDE))
				.append(",posicion2=").append(this.getPosicion(PlanetaEnum.FERENGI))
				.append(",posicion3=").append(this.getPosicion(PlanetaEnum.VULCANO))
				.append("}")
				.toString();
	}

	public double getPerimetro()	{
		return Math.hypot(this.getPosicionPlaneta1().getX()-this.getPosicionPlaneta2().getX(), this.getPosicionPlaneta1().getY()-this.getPosicionPlaneta2().getY()) + 
				Math.hypot(this.getPosicionPlaneta1().getX()-this.getPosicionPlaneta3().getX(), this.getPosicionPlaneta1().getY()-this.getPosicionPlaneta3().getY()) + 
				Math.hypot(this.getPosicionPlaneta2().getX()-this.getPosicionPlaneta3().getX(), this.getPosicionPlaneta2().getY()-this.getPosicionPlaneta3().getY()); 
	}

	@Override
	public int compareTo(DiaDTO dia1) {
		return this.getPerimetro()<dia1.getPerimetro() ? -1 : 1;
	}
	
}
