package mc15;

public class Alumno extends Persona {
	private final double nota;
	/**
	 * @param nombre
	 * @param edad
	 * @param nota
	 */
	public Alumno(String nombre, int edad, double nota) {
		super(nombre, edad);
		this.nota = nota;
	}
	public double getNota() {
		return nota;
	}
}
