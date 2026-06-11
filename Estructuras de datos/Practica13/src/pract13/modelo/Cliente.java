package pract13.modelo;

/**
 * Cliente de una empresa de limpieza.
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version dic-20
 */
public class Cliente implements Comparable<Cliente> {
	private final String nombre;
	private final String direccion;
	private int numLimpiezasEncargadas = 0;
	
	/**
	 * Crea un cliente con el nombre y la direccion indicados.
	 * @param nombre nombre del cliente.
	 * @param direccion direccion del cliente.
	 */
	public Cliente(String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
	}

	/**
	 * Retorna el nombre del cliente.
	 * @return el nombre del cliente.
	 */
	public String nombre() {
		return nombre;
	}

	/**
	 * Retorna la direccion del cliente.
	 * @return la direccion del cliente.
	 */
	public String direccion() {
		return direccion;
	}
	
	/**
	 * Registra que el cliente ha encargado un servicio de limpieza.
	 */
	public void encargaLimpieza() {
		numLimpiezasEncargadas++;
	}

	@Override
	public String toString() {
		return "(" + nombre + ",n=" + numLimpiezasEncargadas + ")";
	}

	@Override
	public int compareTo(Cliente o) {
		return o.numLimpiezasEncargadas - this.numLimpiezasEncargadas;
	}

}
