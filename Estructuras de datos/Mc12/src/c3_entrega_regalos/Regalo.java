package c3_entrega_regalos;

/**
 * Regalo para ser entregado a los socios de una asociacion de vecinos.
 * 
 * @author  Metodos de Programacion (UC) y <TODO: nombre alumno>
 * @version ene-23
 */
public class Regalo {
	private final String descripcion;
	private final double valor;
	
	/**
	 * Construye el regalo.
	 * @param descripcion descripcion del regalo.
	 * @param valor valor en euros del regalo.
	 */
	public Regalo(String descripcion, double valor) {
		this.descripcion = descripcion;
		this.valor = valor;
	}

	/**
	 * Retorna la descripcion del regalo.
	 * @return la descripcion del regalo.
	 */
	public String descripcion() {
		return descripcion;
	}

	/**
	 * Retorna el valor del regalo.
	 * @return el valor del regalo.
	 */
	public double valor() {
		return valor;
	}
	
	// TODO: otros metodos?
	
}
