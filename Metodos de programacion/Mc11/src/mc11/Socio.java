package mc11;

public class Socio {
	private final String nombre;
	private final String dni;
	
	/**
	 * @param nombre
	 * @param dni
	 */
	public Socio(String nombre, String dni) {
		super();
		this.nombre = nombre;
		this.dni = dni;
	}
	public String nombre() {
		return nombre;
	}
	public String dni() {
		return dni;
	}
	@Override
	public String toString() {
		return "Socio [nombre=" + nombre + ", dni=" + dni + "]";
	}
	
}
