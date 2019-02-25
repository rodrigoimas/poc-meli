package ar.com.ml.solar.services.impl.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.com.ml.solar.dao.PronosticoDAO;
import ar.com.ml.solar.dao.impl.PronosticoDAOImpl;
import ar.com.ml.solar.dto.ClimaEnum;
import ar.com.ml.solar.dto.ResultadoCalculoDTO;
import ar.com.ml.solar.service.exceptions.ServiceException;
import ar.com.ml.solar.services.ClimaServices;
import ar.com.ml.solar.services.impl.ClimaServicesImpl;

public class ClimaServicesImplTest {

	ClimaServices service;
	PronosticoDAO dao;
	
	@Before
	public void setup()	{
		service = new ClimaServicesImpl();
		dao = new PronosticoDAOImpl();
	}
	
	@After
	public void tearDown()	{
		System.out.println("Eliminando todos los pronosticos de la base...");
		dao.borrarPronosticos();
		System.out.println("Se eliminaron todos los pronosticos");
	}
	
	@Test
	public void calcularPronosticoTest()	{
		ResultadoCalculoDTO resultado = service.calcularPronostico(5);
		assertNull(resultado.getDiaConMayorLluvia());
		assertTrue(5==resultado.getTotales().get(ClimaEnum.INDEFINIDO));
		resultado = service.calcularPronostico(100);
		assertNotNull(resultado.getDiaConMayorLluvia());
		assertTrue(92==resultado.getTotales().get(ClimaEnum.INDEFINIDO));
		assertTrue(1==resultado.getTotales().get(ClimaEnum.SEQUIA));
		assertTrue(1==resultado.getTotales().get(ClimaEnum.OPTIMO));
		assertTrue(6==resultado.getTotales().get(ClimaEnum.LLUVIA));
	}
	
	@Test
	public void buscarDiaTest() throws ServiceException	{
		service.calcularPronostico(100);
		assertEquals(service.buscarClima(Long.valueOf(1)).getClima(),  ClimaEnum.INDEFINIDO);
		assertEquals(service.buscarClima(Long.valueOf(100)).getClima(),  ClimaEnum.LLUVIA);
		assertEquals(service.buscarClima(Long.valueOf(19)).getClima(),  ClimaEnum.OPTIMO);
		assertEquals(service.buscarClima(Long.valueOf(90)).getClima(),  ClimaEnum.SEQUIA);
	}
}
