package mc8;

/**
 * Estancia realizada en una habitacion del hotel por dos huespedes.
 * 
 * @author  Metodos de Programacion (UC)
 * @version mar-22
 */
public class Estancia {
	private final String dniHuesped1;
	private final String dniHuesped2;
	
	/**
	 * Construye la estancia.
	 * @param dniHuesped1 DNI de uno de los huespedes.
	 * @param dniHuesped2 DNI de otro de los huespedes.
	 */
	public Estancia(String dniHuesped1, String dniHuesped2) {
		this.dniHuesped1 = dniHuesped1;
		this.dniHuesped2 = dniHuesped2;
	}

	/**
	 * Retorna el DNI de huesped 1 de la estancia.
	 * @return el DNI de huesped 1 de la estancia.
	 */
	public String dniHuesped1() {
		return dniHuesped1;
	}


	/**
	 * Retorna el DNI de huesped 2 de la estancia.
	 * @return el DNI de huesped 2 de la estancia.
	 */
	public String dniHuesped2() {
		return dniHuesped2;
	}

	//pueden anhadirse mas metodos si se considera conveniente.
	
}
