package ar.com.ml.solar.dto;

import org.apache.commons.math3.util.Precision;

public class PosicionDTO {

	private int grados;
	private PlanetaDTO planeta;
	
	public PosicionDTO (PlanetaDTO planeta, int grados) {
		this.setGrados(grados);
		this.setPlaneta(planeta);
	}
	
	public PlanetaDTO getPlaneta() {
		return planeta;
	}

	public void setPlaneta(PlanetaDTO planeta) {
		this.planeta = planeta;
	}

	public int getGrados() {
		if (grados==-180)	{
			return 180;
		} else	{
			return grados;
		}
	}
	public void setGrados(int grados) {
		this.grados = grados;
	}
	public double getX() {
		return Precision.round(Math.cos(Math.toRadians(this.getGrados()))*this.getPlaneta().getRadio(),0);
	}
	public double getY() {
		return Precision.round(Math.sin(Math.toRadians(this.getGrados()))*this.getPlaneta().getRadio(),0);
	}
	
	public double getPendienteAUnPunto(PosicionDTO p1)	{
		return Precision.round((this.getY()-p1.getY())/(this.getX()-p1.getX()),1);
	}

	public CuadranteEnum getCuadrante()	{
		if (this.getGrados()>0 && this.getGrados()<90)	{
			return CuadranteEnum.PRIMERO;
		} else if (this.getGrados()>90 && this.getGrados()<180)	{
			return CuadranteEnum.SEGUNDO;
		} else if ((this.getGrados()>-180 && this.getGrados()<-90) || this.getGrados()==180)	{
			return CuadranteEnum.TERCERO;
		} else if (this.getGrados()>-90 && this.getGrados()<0)	{
			return CuadranteEnum.CUARTO;
		} else	{
			return CuadranteEnum.SOBREEJES;
		}
	}
	
	public boolean estanEnGradosSimetricosOpuestos (PosicionDTO p1)	{
		return (this.getGrados()==p1.getGrados()) ||
				(this.getGrados()==0 && p1.getGrados()==180) ||
				(this.getGrados()>0 && p1.getGrados()==-180+this.getGrados()) || 
				(this.getGrados()<0 && p1.getGrados()==180+this.getGrados());
	}
	
	public boolean estaEnHemisferioNorte()	{
		return this.getCuadrante().equals(CuadranteEnum.PRIMERO) || this.getCuadrante().equals(CuadranteEnum.SEGUNDO);
	}

	public boolean estaEnHemisferioSur()	{
		return this.getCuadrante().equals(CuadranteEnum.TERCERO) || this.getCuadrante().equals(CuadranteEnum.CUARTO);
	}
	
	public boolean estaEnHemisferioEste()	{
		return this.getCuadrante().equals(CuadranteEnum.PRIMERO) || this.getCuadrante().equals(CuadranteEnum.CUARTO);
	}

	public boolean estaEnHemisferioOeste()	{
		return this.getCuadrante().equals(CuadranteEnum.SEGUNDO) || this.getCuadrante().equals(CuadranteEnum.TERCERO);
	}
	
	public boolean estanEnCuadrantesOpuestos (PosicionDTO p1) {
		return this.getCuadrante().equals(CuadranteEnum.PRIMERO) && p1.getCuadrante().equals(CuadranteEnum.TERCERO) || 
				this.getCuadrante().equals(CuadranteEnum.SEGUNDO) && p1.getCuadrante().equals(CuadranteEnum.CUARTO) || 
				this.getCuadrante().equals(CuadranteEnum.TERCERO) && p1.getCuadrante().equals(CuadranteEnum.PRIMERO) ||
				this.getCuadrante().equals(CuadranteEnum.CUARTO) && p1.getCuadrante().equals(CuadranteEnum.SEGUNDO); 
	}

	public String toString()	{
		return new StringBuilder().append("Posicion{")
				.append(",planeta=").append(this.getPlaneta())
				.append(",grados=").append(this.getGrados())
				.append(",cuadrante=").append(this.getCuadrante().name())
				.append(",x=").append(this.getX())
				.append(",y=").append(this.getY())
				.append("}")
				.toString();
	}
	
}
