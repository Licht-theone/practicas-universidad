package pract04.modelo;

/**
 * Barco transbordador de vehiculos.
 * 
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version mar-21
 */
public class Transbordador {
	private final int maxVehiculos;
	private final double pesoMax;
	private double pesoAcumul;
	private int numVehiculos;
	private Vehiculo[] vehiculos;
	private static final double PRECIO_OCUPANTE = 1.2;
	private static final double PRECIO_KILO = 0.003;
	// codigos de error retornados por el metodo cargaVehiculo()
	public static final double SUPERA_PESO_MAX = -1;
	public static final double SUPERA_NUM_MAX_VEHICULOS = -2;
	public static final double VEHICULO_YA_EN_TRANSBORDADOR = -3;

	/**
	 * Construye un transbordador que es capaz de transportar el peso maximo y el
	 * numero de vehiculos indicado.
	 * El transbordador comienza vacio.
	 * @param pesoMaxSoportado peso maximo soportado por el transbordador
	 * @param maxCapacidadVehiculos maximo numero de vehiculos que es capaz de
	 * transportar el transbordador
	 */
	public Transbordador(double pesoMaxSoportado, int maxCapacidadVehiculos) {
		vehiculos = new Vehiculo[maxCapacidadVehiculos];
		pesoAcumul = 0;
		numVehiculos = 0;
		pesoMax = pesoMaxSoportado;
		maxVehiculos = maxCapacidadVehiculos;
	}

	/**
	 * Carga un vehiculo en el transbordador (siempre que no se supere el
	 * peso maximo o el numero maximo de vehiculos).
	 * @param vehiculo vehiculo a cargar
	 * @return precio a cobrar al vehiculo o SUPERA_PESO_MAX si se supera el
	 * peso maximo, SUPERA_NUM_MAX_VEHICULOS si se supera el numero maximo de
	 * vehiculos o VEHICULO_YA_EN_TRANSBORDADOR si ya hay en el transbordador
	 * otro vehiculo con la misma matricula que el que se pretende anhadir.
	 */
	public double cargaVehiculo(Vehiculo vehiculo) {
		Vehiculo v;
		double precio;
		double peso = pesoAcumul;
		v = buscaVehiculo(vehiculo.matricula());
		if (v != null) {
			return VEHICULO_YA_EN_TRANSBORDADOR;
		}
		if (numVehiculos >= maxVehiculos) {
			return SUPERA_NUM_MAX_VEHICULOS;
		}
		if ((peso + vehiculo.peso()) > pesoMax) {
			return SUPERA_PESO_MAX;
		}
		vehiculos[numVehiculos] = vehiculo;
		numVehiculos++;
		pesoAcumul += vehiculo.peso();
		precio = (vehiculo.peso() * PRECIO_KILO) + (vehiculo.numOcupantes() * PRECIO_OCUPANTE);
		return precio;
	}

	/**
	 * Busca un vehiculo con el numero de ocupantes indicado y con un peso igual
	 * o superior al peso minimo indicado.
	 * @param numOcupantes numero de ocupantes del vehiculo buscado
	 * @param pesoMinimo peso minimo del vehiculo buscado
	 * @return un vehiculo cargado en el transbordador que tenga el numero de ocupantes
	 * indicado y un peso igual o mayor que el peso minimo indicado. Retorna null si no
	 * hay ningun vehiculo que cumpla las condiciones indicadas
	 */
	public Vehiculo buscaVehiculoConCaracteristicas(int numOcupantes, double pesoMinimo) {
		for (int i = 0; i < numVehiculos; i++) {
			if (vehiculos[i].numOcupantes() == numOcupantes && vehiculos[i].peso() >= pesoMinimo) {
				return vehiculos[i];
			}
		}

		return null;
	}

	/**
	 * Vacia el transbordador de los vehiculos que transportaba.
	 */
	public void vaciaTransbordador() {
		for (int i = 0; i < maxVehiculos; i++) {
			vehiculos[i] = null;
		}
		numVehiculos = 0;
		pesoAcumul = 0;
	}

	/**
	 * Busca el vehiculo con la matricula indicada.
	 * @param matricula matricula del vehiculo a buscar.
	 * @return vehiculo con la matricula indicada o null en el caso de que
	 * no haya ningun vehiculo con esa matricula.
	 */
	private Vehiculo buscaVehiculo(String matricula) {
		for (int i = 0; i < numVehiculos; i++) {
			if (vehiculos[i].matricula().equals(matricula)) {
				return vehiculos[i];
			}
		}

		return null;
	}
}
