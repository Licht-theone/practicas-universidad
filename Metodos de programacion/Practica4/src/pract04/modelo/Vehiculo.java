package pract04.modelo;

/**
 * Vehiculo que se carga en un barco transbordador.
 * 
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version mar-23
 */
public class Vehiculo {
	private final String matricula;
	private final double peso;
	private final int ocupantes;

	/**
	 * genera un vehiculo.
	 * @param matricula matricula del vehiculo
	 * @param peso peso del vehiculo
	 * @param ocupantes ocupantes en el vehiculo
	 */
	public Vehiculo(String matricula, double peso, int ocupantes) {
		this.matricula = matricula;
		this.peso = peso;
		this.ocupantes = ocupantes;
	}

	/**
	 * metodo que retorna la matricula.
	 * @return la matricula del coche
	 */
	public String matricula() {
		return matricula;
	}

	/**
	 * metodo que retorna el peso.
	 * @return el peso del vehiculo
	 */
	public double peso() {
		return peso;
	}

	/**
	 * metodo que retorna los ocupantes.
	 * @return el numero de ocupantes
	 */
	public int numOcupantes() {
		return ocupantes;
	}

}
