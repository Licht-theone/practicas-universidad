package mc14;

/**
 * Compra realizada en una cadena de tiendas.
 * 
 * @author  Metodos de Programacion (UC) y Aaron Alegria
 * @version mar-23
 */
public class Compra {
	private final String producto;
	private final double importe;
	private static final int MIN_PUNTOS = 10;

	/**metodo constructor.
	 * @param producto nombre del producto
	 * @param importe importe del producto
	 */
	public Compra(String producto, double importe) {
		this.producto = producto;
		this.importe = importe;
	}

	/**
	 * retorna el nombre.
	 * @return nombre del prod
	 */
	public String producto() {
		return producto;
	}

	/**
	 * retorna el importe.
	 * @return importe
	 */
	public double importe() {
		return importe;
	}

	/**
	 * retorna los puntos por la compra.
	 * @return puntos
	 */
	public int calculaPuntos() {
		if (importe < MIN_PUNTOS) {
			return 0;
		}
		return (int) (importe / MIN_PUNTOS);
	}
}
