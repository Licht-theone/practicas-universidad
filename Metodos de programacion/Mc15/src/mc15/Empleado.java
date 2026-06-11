package mc15;

public class Empleado extends Persona{
	private final double sueldo;
	/**
	 * @param nombre
	 * @param edad
	 * @param sueldo
	 */
	public Empleado(String nombre, int edad, double sueldo) {
		super(nombre, edad);
		this.sueldo = sueldo;
	}

	public double getSueldo() {
		return sueldo;
	}
}
