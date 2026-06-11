package pract12.modelo;

import pract12.modelo.Actividad.ActividadMaxSocios;

/**
 * Subclase de la clase socio.
 * @author Aaron Alegria
 * @version may-2023
 */
public class SocioBasico extends Socio {
	private static final int MAX_ACTIVIDADES = 4;
	private static final double CUOTA = 40;
	
	/**
	 * constructor.
	 * @param dni dni del socio
	 */
	public SocioBasico(String dni) {
		super(dni);
	}
	
	@Override
	public void apuntaActividad(Actividad a, Socio s) 
			throws SocioYaInscrito, SocioMaxActividades, ActividadMaxSocios {
		if (buscaActividad(a.nombre()) != null) {
			throw new SocioYaInscrito();
		}
		if (numActividades() >= MAX_ACTIVIDADES) {
			throw new SocioMaxActividades();
		}
		a.apuntaSocio(s);
		anhadeActividad(a);
	}
	
	@Override
	public double cuotaMensual() {
		return CUOTA;
	}
}
