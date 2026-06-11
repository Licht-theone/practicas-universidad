package pract06.torres_hanoi.modelo;

/**
 * Disco que puede moverse entre la torres. Se caracteriza por su longitud
 * representada por un numero entero igual o mayor que 1.
 * 
 * @author Metodos de Programacion (UC)
 * @version oct-2023
 */
public class Disco {
	private final int longitud; // Valores 1, 2, 3, ...

	/**
	 * Construye un disco.
	 * @param longitud longitud del disco (representada por un numero entero
	 * igual o mayor que 1).
	 */
	public Disco(int longitud) {
		this.longitud = longitud;
	}
	
	/**
	 * Retorna la longitud del disco.
	 * @return la longitud del disco.
	 */
	public int longitud() {
		return longitud;
	}

	@Override
	public String toString() {
		return "(" + longitud + ")";
	}
	
	

}
