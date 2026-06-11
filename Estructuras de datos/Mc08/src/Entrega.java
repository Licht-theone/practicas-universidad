

/**
 * Entrega realizada por los camiones de una empresa de distribucion.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2023
 */
public class Entrega {
	private final Cliente cliente;
	private final String descripcion;
	
	/**
	 * Construye una entrega.
	 * @param cliente cliente al que va destinada la entrega.
	 * @param descripcion descripcion de la entrega.
	 */
	public Entrega(Cliente cliente, String descripcion) {
		this.cliente = cliente;
		this.descripcion = descripcion;
	}

	/**
	 * Retorna el cliente al que va destinada la entrega.
	 * @return el cliente al que va destinada la entrega.
	 */
	public Cliente cliente() {
		return cliente;
	}

	/**
	 * Retorna la descripcion de la entrega.
	 * @return la descripcion de la entrega.
	 */
	public String descripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return "(Cli:" + cliente + ", Des:" + descripcion + ")";
	}
}
