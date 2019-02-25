package ar.com.ml.solar.services.impl.utils;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ar.com.ml.solar.dto.DiaDTO;
import ar.com.ml.solar.dto.OrientacionEnum;
import ar.com.ml.solar.dto.PlanetaDTO;
import ar.com.ml.solar.dto.PlanetaConfiguracionDTO;
import ar.com.ml.solar.dto.PlanetaEnum;
import ar.com.ml.solar.dto.PosicionDTO;

public class UtilClimaTest {

	PosicionDTO sol;
	PosicionDTO planetaA90;
	PosicionDTO planetaA0;
	PosicionDTO planetaAMenos90;
	PosicionDTO planetaA180;
	Map<PlanetaEnum,PlanetaConfiguracionDTO> mapaConfig;
	
	@Before
	public void setup()	{
		sol = new PosicionDTO (new PlanetaDTO(PlanetaEnum.SOL,0),90);
		planetaA90 = new PosicionDTO (new PlanetaDTO(PlanetaEnum.FERENGI,1.0d),90);
		planetaA0 = new PosicionDTO (new PlanetaDTO(PlanetaEnum.FERENGI,1.0d),0);
		planetaAMenos90 = new PosicionDTO (new PlanetaDTO(PlanetaEnum.FERENGI,1.0d),-90);
		planetaA180 = new PosicionDTO (new PlanetaDTO(PlanetaEnum.FERENGI,1.0d),180);
		
		mapaConfig = new HashMap<PlanetaEnum, PlanetaConfiguracionDTO>();
		mapaConfig.put(PlanetaEnum.FERENGI, new PlanetaConfiguracionDTO(PlanetaEnum.FERENGI,1.0d,OrientacionEnum.HORARIA, 1));
		mapaConfig.put(PlanetaEnum.BETASOIDE, new PlanetaConfiguracionDTO(PlanetaEnum.BETASOIDE,2.0d,OrientacionEnum.HORARIA, 1));
		mapaConfig.put(PlanetaEnum.VULCANO, new PlanetaConfiguracionDTO(PlanetaEnum.VULCANO,4.0d,OrientacionEnum.HORARIA, 1));
	}
	
	  @Test
	  public void calcularGradosTest() {
	    assertTrue(UtilClima.calcularGrados(2, OrientacionEnum.HORARIA, 90)==88);
	    assertTrue(UtilClima.calcularGrados(4, OrientacionEnum.ANTIHORARIA, 90)==94);
	    assertTrue(UtilClima.calcularGrados(20, OrientacionEnum.HORARIA, 0)==-20);
	    assertTrue(UtilClima.calcularGrados(3, OrientacionEnum.HORARIA, 0)==-3);
	    assertTrue(UtilClima.calcularGrados(5, OrientacionEnum.HORARIA, -177)==178);
	    assertTrue(UtilClima.calcularGrados(5, OrientacionEnum.ANTIHORARIA, 178)==-177);
	  }
	  
	  @Test
	  public void esLluviaTest()	{
		  DiaDTO dia = new DiaDTO(1, mapaConfig);
		  dia.getPosicionPlaneta1().setGrados(90);
		  dia.getPosicionPlaneta2().setGrados(30);
		  dia.getPosicionPlaneta3().setGrados(75);
		  assertTrue(!UtilClima.esLluvia(dia,sol));
		  dia.getPosicionPlaneta1().setGrados(90);
		  dia.getPosicionPlaneta2().setGrados(157);
		  dia.getPosicionPlaneta3().setGrados(75);
		  assertTrue(!UtilClima.esLluvia(dia,sol));
		  dia.getPosicionPlaneta1().setGrados(-130);
		  dia.getPosicionPlaneta2().setGrados(157);
		  dia.getPosicionPlaneta3().setGrados(-177);
		  assertTrue(!UtilClima.esLluvia(dia,sol));
		  dia.getPosicionPlaneta1().setGrados(45);
		  dia.getPosicionPlaneta2().setGrados(-45);
		  dia.getPosicionPlaneta3().setGrados(-170);
		  assertTrue(UtilClima.esLluvia(dia,sol));
		  dia.getPosicionPlaneta1().setGrados(100);
		  dia.getPosicionPlaneta2().setGrados(175);
		  dia.getPosicionPlaneta3().setGrados(50);
		  assertTrue(!UtilClima.esLluvia(dia,sol));

	  }
	  
	  @Test
	  public void esOptimoTest()	{
		  DiaDTO dia = new DiaDTO(1, mapaConfig);
		  dia.getPosicionPlaneta1().setGrados(90);
		  dia.getPosicionPlaneta2().setGrados(157);
		  dia.getPosicionPlaneta3().setGrados(75);
		  assertTrue(UtilClima.esOptimo(dia,sol));
		  dia.getPosicionPlaneta1().setGrados(-60);
		  dia.getPosicionPlaneta2().setGrados(30);
		  dia.getPosicionPlaneta3().setGrados(120);
		  assertTrue(!UtilClima.esOptimo(dia,sol));
		  dia.getPosicionPlaneta1().setGrados(-60);
		  dia.getPosicionPlaneta2().setGrados(-120);
		  dia.getPosicionPlaneta3().setGrados(-60);
		  assertTrue(UtilClima.esOptimo(dia,sol));
	  }
	
	  @Test
	  public void esSequia()	{
		  DiaDTO dia = new DiaDTO(1, mapaConfig);
		  dia.getPosicionPlaneta1().setGrados(45);
		  dia.getPosicionPlaneta2().setGrados(-135);
		  dia.getPosicionPlaneta3().setGrados(45);
		  assertTrue(UtilClima.esSequia(dia));
		  dia.getPosicionPlaneta1().setGrados(45);
		  dia.getPosicionPlaneta2().setGrados(60);
		  dia.getPosicionPlaneta3().setGrados(120);
		  assertTrue(!UtilClima.esSequia(dia));
		  dia.getPosicionPlaneta1().setGrados(-13);
		  dia.getPosicionPlaneta2().setGrados(-13);
		  dia.getPosicionPlaneta3().setGrados(167);
		  assertTrue(UtilClima.esSequia(dia));
	  }
}