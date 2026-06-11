package pract12.modelo;

import pract12.modelo.Actividad.ActividadMaxSocios;

/**
 * Subclase de la clase socio.
 * @author Aaron Alegria
 * @version may-2023
 */
public class SocioPlus extends Socio {
	private final String nombre;
	private static final int MAX_ACTIVIDADES = 10;
	
	/**
	 * constructor.
	 * @param dni dni del socio
	 * @param nombre nombre del socio
	 */
	public SocioPlus(String dni, String nombre) {
		super(dni);
		this.nombre = nombre;
	}
	
	/**
	 * observador del nombre.
	 * @return el nombre
	 */
	public String nombre() {
		return nombre;
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
		return precioTotalSociosPlus();
	}
	
}
