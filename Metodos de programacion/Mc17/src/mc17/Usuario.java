package mc17;

public abstract class Usuario {
	private final String dni;
	protected final static int PRECIO_SOCIO = 10;
	protected final static double PRECIO_POR_LIBRO = 1.5;
	/**
	 * @param dni
	 */
	public Usuario(String dni) {
		this.dni = dni;
	}
	public String getDni() {
		return dni;
	}
}
