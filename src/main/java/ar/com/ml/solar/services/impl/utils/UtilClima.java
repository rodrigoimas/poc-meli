package ar.com.ml.solar.services.impl.utils;

import ar.com.ml.solar.dto.DiaDTO;
import ar.com.ml.solar.dto.OrientacionEnum;
import ar.com.ml.solar.dto.PosicionDTO;

public class UtilClima {

	public static boolean esLluvia(DiaDTO dia, PosicionDTO posicionSol) {
		return !estanEnMismoCuadrante(dia) && !estanEnMismaMitad(dia) && formanTrianguloDentroDelSol(dia, posicionSol); 
	}

	public static boolean esSequia(DiaDTO dia) {
		return dia.getPosicionPlaneta1().estanEnGradosSimetricosOpuestos(dia.getPosicionPlaneta2()) && dia.getPosicionPlaneta2().estanEnGradosSimetricosOpuestos(dia.getPosicionPlaneta3());
	}

	public static boolean esOptimo(DiaDTO dia, PosicionDTO posicionSol) {
		return estanAlineados (dia.getPosicionPlaneta1(), dia.getPosicionPlaneta2(), dia.getPosicionPlaneta3()) && 
			!estanAlineados (dia.getPosicionPlaneta1(), posicionSol, dia.getPosicionPlaneta2());
	}

	public static int calcularGrados(int velocidad, OrientacionEnum orientacion, int grados) {
		int resultado;
		if (orientacion.equals(OrientacionEnum.HORARIA))	{
			resultado = grados - velocidad;
		} else	{
			resultado = grados + velocidad;
		}
		if (resultado>180)	{
			resultado = -360 + Math.abs(resultado);
		}
		if (resultado<-180)	{
			resultado = 360 - Math.abs(resultado);
		}
		return resultado;
	}
	
	private static boolean estanEnMismoCuadrante(DiaDTO dia) {
		return dia.getPosicionPlaneta1().getCuadrante().equals(dia.getPosicionPlaneta2().getCuadrante()) &&
				dia.getPosicionPlaneta2().getCuadrante().equals(dia.getPosicionPlaneta3().getCuadrante());
	}
	
	private static boolean estanEnMismaMitad(DiaDTO dia) {
		return (dia.getPosicionPlaneta1().estaEnHemisferioNorte() && dia.getPosicionPlaneta2().estaEnHemisferioNorte() && dia.getPosicionPlaneta3().estaEnHemisferioNorte()) || 
				(dia.getPosicionPlaneta1().estaEnHemisferioSur() && dia.getPosicionPlaneta2().estaEnHemisferioSur() && dia.getPosicionPlaneta3().estaEnHemisferioSur()) || 
				(dia.getPosicionPlaneta1().estaEnHemisferioEste() && dia.getPosicionPlaneta2().estaEnHemisferioEste() && dia.getPosicionPlaneta3().estaEnHemisferioEste()) || 
				(dia.getPosicionPlaneta1().estaEnHemisferioOeste() && dia.getPosicionPlaneta2().estaEnHemisferioOeste() && dia.getPosicionPlaneta3().estaEnHemisferioOeste());
	}

	private static boolean formanTrianguloDentroDelSol (DiaDTO dia, PosicionDTO posicionSol) {
		if (dia.getPosicionPlaneta1().estanEnCuadrantesOpuestos(dia.getPosicionPlaneta2()) || dia.getPosicionPlaneta1().estanEnCuadrantesOpuestos(dia.getPosicionPlaneta3())) {
			return estaElSolEntrePendientes(dia.getPosicionPlaneta1(), dia.getPosicionPlaneta2(), dia.getPosicionPlaneta3(), posicionSol);
		}
		if (dia.getPosicionPlaneta2().estanEnCuadrantesOpuestos(dia.getPosicionPlaneta1()) || dia.getPosicionPlaneta2().estanEnCuadrantesOpuestos(dia.getPosicionPlaneta3())) {
			return estaElSolEntrePendientes(dia.getPosicionPlaneta2(), dia.getPosicionPlaneta1(), dia.getPosicionPlaneta3(), posicionSol);
		}
		if (dia.getPosicionPlaneta3().estanEnCuadrantesOpuestos(dia.getPosicionPlaneta1()) || dia.getPosicionPlaneta3().estanEnCuadrantesOpuestos(dia.getPosicionPlaneta2())) {
			return estaElSolEntrePendientes(dia.getPosicionPlaneta3(), dia.getPosicionPlaneta1(), dia.getPosicionPlaneta2(), posicionSol);
		}
		return false;
	}

	private static boolean estaElSolEntrePendientes (PosicionDTO p1, PosicionDTO p2, PosicionDTO p3, PosicionDTO posicionSol) {
		return p1.getPendienteAUnPunto(p2)>p1.getPendienteAUnPunto(posicionSol) && 
				p1.getPendienteAUnPunto(p3)<p1.getPendienteAUnPunto(posicionSol);
	}
	
	private static  boolean estanAlineados (PosicionDTO p1, PosicionDTO p2, PosicionDTO p3) {
//		System.out.println("p1=" + p1 + "\np2=" + p2 + "\np3=" + p3);
//		System.out.println("pendiente p1_p2=" + p1.getPendienteAUnPunto(p2));
//		System.out.println("pendiente p1_p3=" + p1.getPendienteAUnPunto(p3));
		return p1.getPendienteAUnPunto(p2) == p1.getPendienteAUnPunto(p3);
	}

	
}
