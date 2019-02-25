package ar.com.ml.solar.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PosicionTest {

	private PlanetaDTO planeta;
	private PosicionDTO p1;
	private PosicionDTO p2;
	private PosicionDTO p3;
	private PosicionDTO p4;
	
	@Before
	public void setup()	{
		planeta = new PlanetaDTO(PlanetaEnum.FERENGI, 100);
		p1 = new PosicionDTO(planeta,30);
		p2 = new PosicionDTO(planeta,120);
		p3 = new PosicionDTO(planeta,-60);
		p4 = new PosicionDTO(planeta,-120);
	}
	
	@Test
	public void posicionCuadranteTest()	{
		assertTrue(p1.getCuadrante().equals(CuadranteEnum.PRIMERO));
		assertTrue(p2.getCuadrante().equals(CuadranteEnum.SEGUNDO));
		assertTrue(p4.getCuadrante().equals(CuadranteEnum.TERCERO));
		assertTrue(p3.getCuadrante().equals(CuadranteEnum.CUARTO));
	}

	@Test
	public void posicionHemisferioTest()	{
		assertTrue(p1.estaEnHemisferioEste());
		assertTrue(p1.estaEnHemisferioNorte());
		assertTrue(p2.estaEnHemisferioOeste());
		assertTrue(p2.estaEnHemisferioNorte());
		assertTrue(p3.estaEnHemisferioEste());
		assertTrue(p3.estaEnHemisferioSur());
		assertTrue(p4.estaEnHemisferioOeste());
		assertTrue(p4.estaEnHemisferioSur());
	}

	@Test
	public void posicionOpuestosTest()	{
		assertTrue(p1.estanEnCuadrantesOpuestos(p4));
		assertTrue(p2.estanEnCuadrantesOpuestos(p3));
		assertFalse(p1.estanEnGradosSimetricosOpuestos(p4));
		assertTrue(p2.estanEnGradosSimetricosOpuestos(p3));
	}
	
	@Test
	public void posicionPuntosTest()	{
		assertTrue(50d==p1.getY());
		assertTrue(-50d==p2.getX());
		assertTrue(3.7d==p1.getPendienteAUnPunto(p3));
	}
	
}
