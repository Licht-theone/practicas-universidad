package mc3;

public class Agente {
	private static final double prctg = 0.0001;
	private final String nombre;
	private final String dni;
	private double comision = 0;
	/**
	 * @param piso
	 * @param nombre
	 * @param dni
	 */
	public Agente(String nombre, String dni) {
		this.nombre = nombre;
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public double getComision() {
		return comision;
	}

	public void addComision(int precio) {
		comision += precio * prctg;
	}

	@Override
	public String toString() {
		return "Agente [nombre=" + nombre + ", dni=" + dni + ", comision=" + comision + "€]";
	}

}
