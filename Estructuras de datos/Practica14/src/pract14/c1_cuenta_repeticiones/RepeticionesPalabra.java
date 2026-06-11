package pract14.c1_cuenta_repeticiones;

/**
 * Repeticiones de una palabra.
 *  
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version ene-2021
 */
public class RepeticionesPalabra implements Comparable<RepeticionesPalabra> {
	
	private final String palabra;
	private int repeticiones;
	
	/**
	 * Crea un objeto para contabilizar las repeticiones
	 * de una palabra. La palabra comienza con una repeticion.
	 * @param palabra palabra a contabilizar.
	 */
	public RepeticionesPalabra(String palabra) {
		this.palabra = palabra;
		repeticiones = 1;
	}

	/**
	 * Repeticiones de la palabra.
	 * @return repeticiones de la palabra
	 */
	public int repeticiones() {
		return repeticiones;
	}

	/**
	 * Suma una repeticion a la palabra.
	 */
	public void sumaRepeticion() {
		repeticiones++;
	}

	/**
	 * La palabra de la que se contabilizan las repeticiones.
	 * @return la palabra de la que se contabilizan las repeticiones.
	 */
	public String palabra() {
		return palabra;
	}

	@Override
	public boolean equals(Object obj) {
		return palabra.equals(((RepeticionesPalabra) obj).palabra);
	}

	@Override
	public int compareTo(RepeticionesPalabra o) {
		return o.repeticiones - this.repeticiones;
	}
}
