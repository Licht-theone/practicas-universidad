package mc17;

import java.util.ArrayList;

public abstract class Socio {
	private final String dni;
	private final String nombre;
	protected final static int CUOTA_NORMAL = 60;
	protected ArrayList<Curso> cursos = new ArrayList<Curso>();
	/**
	 * @param dni
	 * @param nombre
	 */
	public Socio(String dni, String nombre) {
		super();
		this.dni = dni;
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public String getNombre() {
		return nombre;
	}
	public int numCursos() {
		return cursos.size();
	}
	public void anhadeCurso(Curso c) {
		cursos.add(c);
	}
	protected double precioTotalCursos() {
		double total = 0;
		for (Curso c: cursos) {
			total += c.getPrecio();
		}
		return total;
	}
	public abstract double precioTotal();
	public abstract String toString();
}
