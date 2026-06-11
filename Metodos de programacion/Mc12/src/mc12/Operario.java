package mc12;

public class Operario {
	private final String nombre;
	private int numReparaciones;
	/**
	 * @param nombre
	 * @param numReparaciones
	 */
	public Operario(String nombre) {
		this.nombre = nombre;
		numReparaciones = 0;
	}
	public int numReparaciones() {
		return numReparaciones;
	}
	public void anhadereparacion() {
		numReparaciones++;
	}
	public String getNombre() {
		return nombre;
	}
	
}
