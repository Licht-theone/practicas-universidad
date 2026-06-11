package mc8;

/**
 * Hotel con un numero fijo de habitaciones.
 * 
 * @author  Metodos de Programacion (UC)
 * @version mar-22
 */
public class Hotel {
	private Habitacion[] habitaciones;
	private static final int NUM_HAB = 8;
	// codigos de error
	public static final int NO_ERROR = 0;
	public static final int ERROR_HOTEL_COMPLETO = -1;
	public static final int ERROR_NUM_HABITACION_INCORRECTO = -2;
	public static final int ERROR_HABITACION_NO_OCUPADA = -3;
	
	/**
	 * Construye el hotel.
	 */
	public Hotel() {
		habitaciones = new Habitacion[NUM_HAB];
	}
	
	/**
	 * Aloja los huespedes con los DNIs indicados en una de las habitaciones
	 * libres del hotel.
	 * @param dniHuesped1 DNI de uno de los huespedes.
	 * @param dniHuesped2 DNI de otro de los huespedes.
	 * @return el numero de la habitacion asignada o ERROR_HOTEL_COMPLETO si
	 * todas las habitaciones del hotel estan ocupadas.
	 */
	public int alojaHuespedes(String dniHuesped1, String dniHuesped2) {
		Habitacion h = buscaVacia();
		if (h == null) {
			return ERROR_HOTEL_COMPLETO;
		}
		Estancia e = new Estancia(dniHuesped1, dniHuesped2);
		h.alojaHuespedes(e);
		return NO_ERROR;
	}
	
	/**
	 * Finaliza el alojamiento en la habitacion indicada. La habitacion pasa a
	 * estar libre.
	 * @param numHabitacion numero de la habitacion que finaliza el alojamiento.
	 * @return NO_ERROR si la operacion se ha realizado con exito,
	 * ERROR_NUM_HABITACION_INCORRECTO si el numero no corresponde a ninguna de
	 * las habitaciones del hotel o ERROR_HABITACION_NO_OCUPADA si la habitacion
	 * indicada no se encuentra ocupada en este momento.
	 */
	public int finalizaAlojamiento(int numHabitacion) {
		if (numHabitacion < 0 || numHabitacion >= NUM_HAB) {
			return ERROR_NUM_HABITACION_INCORRECTO;
		}
		if (habitaciones[numHabitacion].estaVacia()) {
			return ERROR_HABITACION_NO_OCUPADA;
		}
		habitaciones[numHabitacion].finalizaEstancia();
		return NO_ERROR;
	}
	
	/**
	 * Retorna la estancia que ocupa la posicion indicada en el historico de
	 * estancias de la habitacion.
	 * @param numHabitacion numero de la habitacion en la que buscar la estancia
	 * @param posEstacia posicion de la estancia en el historico de estancias de
	 * la habitacion.
	 * @return la estancia buscada o null si no existe ninguna habitacion con el
	 * numero indicado o si la posicion no corresponde a ninguna estancia.
	 */
	public Estancia estanciaEnHabitacion(int numHabitacion, int posEstacia) {
		if (numHabitacion < 0 || numHabitacion >= NUM_HAB || habitaciones[numHabitacion].buscaEstancia(posEstacia) == null) {
			return null;
		}
		return habitaciones[numHabitacion].buscaEstancia(posEstacia);
	}
	
	/**
	 * Retorna el numero de estancias realizadas por un huesped en el hotel.
	 * @param dniHuesped DNI del huesped del que se quiere conocer el numero
	 * de estancias.
	 * @return el numero de estancias realizadas por un huesped en el hotel.
	 */
	public int numEstanciasDeHuesped(String dniHuesped) {
		int c = 0;
		for (int i = 0; i < NUM_HAB; i++) {
			c += habitaciones[i].numEstancias(dniHuesped);
		}
		return c;
	}
	
	//pueden anhadirse mas metodos privados si se considera conveniente.
	
	private Habitacion buscaVacia() {
		for (int i = 0; i < NUM_HAB; i++) {
			if (habitaciones[i].estaVacia()) {
				return habitaciones[i];
			}
		}
		return null;
	}
	
}
