package ar.com.ml.solar.dto;

public class PlanetaConfiguracionDTO  {

	private	PlanetaEnum nombre;
	private double radio;
	private OrientacionEnum orientacion;
	private int velocidad;
	
	public PlanetaConfiguracionDTO(PlanetaEnum nombre, double radio, OrientacionEnum orientacion, int velocidad) {
		this.nombre = nombre;
		this.radio = radio;
		this.orientacion = orientacion;
		this.velocidad = velocidad;
	}
	public PlanetaEnum getNombre() {
		return nombre;
	}
	public void setNombre(PlanetaEnum nombre) {
		this.nombre = nombre;
	}
	public double getRadio() {
		return radio;
	}
	public void setRadio(double radio) {
		this.radio = radio;
	}
	public OrientacionEnum getOrientacion() {
		return orientacion;
	}
	public void setOrientacion(OrientacionEnum orientacion) {
		this.orientacion = orientacion;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	@Override
	public String toString()	{
		return "Planeta: " + this.getNombre() + ", Radio: " + this.getRadio() + ", Orientacion: " + this.getOrientacion().toString() + ", Velocidad: " + this.getVelocidad();
	}
	
}
