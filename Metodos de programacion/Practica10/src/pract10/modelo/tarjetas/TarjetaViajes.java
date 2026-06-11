package pract10.modelo.tarjetas;

/**
 * Subclase de la clase tarjeta pero superclase de las variante de viajes.
 * 
 * @author Aaron Alegria
 * @version may-2021
 */
public abstract class TarjetaViajes extends Tarjeta {
	protected final String direccion;
	private int puntosViajeAcumulados;
	private static final int MIN_PUNTOS = 50;
	
	/**
	 * Lanzada si no hay puntos suficientes.
	 */
	@SuppressWarnings("serial")
	public static class PuntosInsuficientes extends RuntimeException {
	}

	/**
	 * constructor.
	 * @param dni dni del dueño
	 * @param direccion direccion del dueño
	 */
	public TarjetaViajes(String dni, String direccion) {
		super(dni);
		this.direccion = direccion;
		puntosViajeAcumulados = 0;
	}
	
	/**
	 * observardor de los puntos.
	 * @return los puntos
	 */
	public int puntosViajeAcumulados() {
		return puntosViajeAcumulados;
	}
	
	/**
	 * observador direccion.
	 * @return direccion
	 */
	public String direccion() {
		return direccion;
	}
	
	/**
	 * añade puntos a la tarjeta normal.
	 */
	protected void anhadePuntosNormal() {
		puntosViajeAcumulados++;
	}
	
	/**
	 * añade puntos a la tarjeta familiar.
	 * @param importe importe de la compra
	 */
	protected void anhadePuntosFamiliar(double importe) {
		puntosViajeAcumulados += (int) (importe / MIN_PUNTOS);
	}
	
	/**
	 * gasta puntos de viaje.
	 * @param puntos puntos a gastar
	 * @throws PuntosInsuficientes excepcion si la tarjeta no tiene puntos suficientes
	 */
	public void gastaPuntosViaje(int puntos) throws PuntosInsuficientes {
		if (puntos > puntosViajeAcumulados) {
			throw new PuntosInsuficientes();
		}
		puntosViajeAcumulados -= puntos;
	}
}
