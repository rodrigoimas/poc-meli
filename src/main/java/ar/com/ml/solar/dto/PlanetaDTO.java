package ar.com.ml.solar.dto;

public class PlanetaDTO {

	private PlanetaEnum nombre;
	private double radio;

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public PlanetaDTO (PlanetaEnum nombre, double radio)	{
		this.setNombre(nombre);
		this.setRadio(radio);
	}
	
	public PlanetaEnum getNombre() {
		return nombre;
	}
	public void setNombre(PlanetaEnum nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString()	{
		return new StringBuilder().append("Planeta{")
				.append("nombre=").append(this.getNombre())
				.append(",radio=").append(this.getRadio())
				.append("}")
				.toString();
	}

}
