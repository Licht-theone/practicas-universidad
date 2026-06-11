package pract12.p2_candidatos.modelo;

/**
 * Clase que representa a un candidato que se presenta a un
 * puesto de trabajo.
 * El orden natural esta basado en la ordenación en función del número de puntos
 * (a mayor número de puntos, mayor prioridad para obtener el puesto).
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version dic-2020
 *
 */
public class Candidato implements Comparable<Candidato> {

	private final String nombre;
	private final int puntos;
	
	/**
	 * Constructor de la clase Cliente.
	 * @param nombre nombre del cliente.
	 * @param puntos puntos del cliente.
	 */
	public Candidato(String nombre, int puntos) {
		this.nombre = nombre;
		this.puntos = puntos; 
	}

	/**
	 * Retorna el nombre del cliente.
	 * @return el nombre del cliente.
	 */
	public String nombre() {
		return nombre;
	}

	/**
	 * Retorna los puntos del cliente.
	 * @return puntos del cliente.
	 */
	public int puntos() {
		return puntos;
	}
	
	
	@Override
	public String toString() {
		return "(" + nombre + ", Puntos:" + puntos + ")";
	}

	@Override
	public int compareTo(Candidato o) {
		return o.puntos - this.puntos;
	}



}

