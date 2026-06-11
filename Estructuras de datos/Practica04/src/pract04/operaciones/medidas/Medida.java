package pract04.operaciones.medidas;

/**
 * Clase sencilla que permite almacenar una medida de tiempo: tamanho del
 * ejemplar y tiempo que ha tardado en realizarse el algoritmo medido para
 * ese tamanho.
 * 
 * @author Estructuras de Datos (UC)
 * @version sep-19
 */
public class Medida {
	private final int tamanho; // tamanho del ejemplar
	private final double ms;   // milisegundos de la medida
	
	/**
	 * Construye una medida con el tamanho y el tiempo pasados como 
	 * parametros.
	 * @param tamanho tamanho del ejemplar al que corresponde la medida
	 * @param ms tiempo en milisegundos de la medida
	 */
	public Medida(int tamanho, double ms) {
		this.tamanho = tamanho;
		this.ms = ms;
	}

	/**
	 * Retorna el tamaho del ejemplar al que corresponde la medida.
	 * @return el tamaho del ejemplar al que corresponde la medida.
	 */
	public int tamanho() {
		return tamanho;
	}

	/**
	 * Retorna el tiempo en milisegundos de la medida.
	 * @return el tiempo en milisegundos de la medida.
	 */
	public double ms() {
		return ms;
	}
}
