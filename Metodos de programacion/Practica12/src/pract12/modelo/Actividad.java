package pract12.modelo;

import java.util.ArrayList;

/**
 * Actividad de un gimnasio.
 * @author Aaron Alegria
 * @version may-2023
 */
public class Actividad {
	/**
	 * excepcion para cuando la actividad esta llena.
	 */
	@SuppressWarnings("serial")
	public class ActividadMaxSocios extends RuntimeException {
	}
	
	private final String nombre;
	private final double precio;
	private final int maxSocios;
	private int numSocios;
	private ArrayList<Socio> socios = new ArrayList<Socio>();
	
	/**
	 * constructor.
	 * @param nombre nombre de la actividad
	 * @param precio precio de la actividad
	 * @param maxSocios plazas maximas
	 */
	public Actividad(String nombre, double precio, int maxSocios) {
		this.nombre = nombre;
		this.precio = precio;
		this.maxSocios = maxSocios;
		numSocios = 0;
	}
	
	/**
	 * observador de los socios apuntados.
	 * @return los socios apuntados
	 */
	public int numSocios() {
		return numSocios;
	}
	
	/**
	 * apunta a un socio a la actividad.
	 * @param s socio para el informe
	 * @throws ActividadMaxSocios si esta llena
	 */
	public void apuntaSocio(Socio s) throws ActividadMaxSocios {
		if (numSocios >= maxSocios) {
			throw new ActividadMaxSocios();
		}
		socios.add(s);
		numSocios++;
	}
	
	/**
	 * observador del nombre de la actividad.
	 * @return el nombre de la actividad
	 */
	public String nombre() {
		return nombre;
	}
	
	/**
	 * observador del precio.
	 * @return el precio
	 */
	public double precio() {
		return precio;
	}
	
	/**
	 * retorna el arraylist como array para el informe.
	 * @return el arraylist de socios
	 */
	public Socio[] socios() {
		return socios.toArray(new Socio[socios.size()]);
	}
}
