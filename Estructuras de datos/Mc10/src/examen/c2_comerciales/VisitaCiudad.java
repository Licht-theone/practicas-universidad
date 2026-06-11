package examen.c2_comerciales;

/**
 * Visita realizada por el comercial de una empresa.
 * 
 * @author Estructuras de Datos (UC)
 * @version dic-2020
 */
public class VisitaCiudad {
	private final String nombreCiudad;
	private String direccionUltimoCliente;
	
	/**
	 * Construye una visita.
	 * @param nombreCiudad nombre de la ciudad visitada.
	 * @param direccionUltimoCliente direccion del ultimo cliente visitado
	 * en esa ciudad.
	 */
	public VisitaCiudad(String nombreCiudad, String direccionUltimoCliente) {
		this.nombreCiudad = nombreCiudad;
		this.direccionUltimoCliente = direccionUltimoCliente;
	}

	/**
	 * Retorna el nombre de la ciudad visitada.
	 * @return el nombre de la ciudad visitada.
	 */
	public String nombreCiudad() {
		return nombreCiudad;
	}

	
	/**
	 * Retorna la direccion del ultimo cliente visitado en la ciudad.
	 * @return la direccion del ultimo cliente visitado en la ciudad.
	 */
	public String direccionUltimoCliente() {
		return direccionUltimoCliente;
	}
	
	/**
	 * Actualiza la direccion del ultimo cliente visitado en la ciudad.
	 * @param direccionUltimoCliente nueva direccion del ultimo cliente visitado
	 * en la ciudad.
	 */
	public void actualizaDireccionUltimoCliente(String direccionUltimoCliente) {
		this.direccionUltimoCliente = direccionUltimoCliente;
	}
	
}
