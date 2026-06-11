package mc9;

public class Consumicion {
	private final String descripcion;
	private final double precio;
	
	/**
	 * @param descripcion
	 * @param precio
	 */
	public Consumicion(String descripcion, double precio) {
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public String descripcion() {
		return descripcion;
	}

	public double precio() {
		return precio;
	}
	
}
