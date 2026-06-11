package pract11.modelo;

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
	 * @throws ActividadMaxSocios si esta llena
	 */
	public void apuntaSocio() throws ActividadMaxSocios {
		if (numSocios >= maxSocios) {
			throw new ActividadMaxSocios();
		}
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
}
