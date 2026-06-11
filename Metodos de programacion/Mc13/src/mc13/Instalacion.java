package mc13;

public class Instalacion {
	private final String codigo;

	/**
	 * @param codigo
	 */
	public Instalacion(String modelo, int tiempo) {
		codigo = modelo + tiempo;
	}

	public String codigo() {
		return codigo;
	}
	
}
