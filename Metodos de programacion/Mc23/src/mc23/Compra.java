package mc23;

public class Compra {
	private final String nombre;
	private final double precio;
	/**
	 * @param nombre
	 * @param precio
	 */
	public Compra(String nombre, double precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}
	public String nombre() {
		return nombre;
	}
	public double precio() {
		return precio;
	}

}
