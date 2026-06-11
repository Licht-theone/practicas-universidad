package mc17;

public class Curso {
	private final double precio;
	private final String nombre;
	private final int maxSocios;
	private int numSocios = 0;
	/**
	 * @param precio
	 * @param nombre
	 * @param maxSocios
	 */
	public Curso(double precio, String nombre, int maxSocios) {
		this.precio = precio;
		this.nombre = nombre;
		this.maxSocios = maxSocios;
	}
	public double getPrecio() {
		return precio;
	}
	public String getNombre() {
		return nombre;
	}
	
	private boolean estaLleno() {
		if (numSocios >= maxSocios) {
			return true;
		}
		return false;
	}
	public boolean apuntaSocio() {
		if (estaLleno()) {
			return false;
		}
		numSocios++;
		return true;
	}
	public void eliminaSocio() {
		numSocios--;
	}
	@Override
	public String toString() {
		return "Curso [precio=" + precio + ", nombre=" + nombre + "]";
	}
}
