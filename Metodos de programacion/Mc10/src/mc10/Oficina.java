package mc10;

public class Oficina {
	private final int numero;
	private int numVehiculos = 0;

	/**
	 * @param numero
	 */
	public Oficina(int numero) {
		this.numero = numero;
	}

	public int numero() {
		return numero;
	}

	public void meteVehiculo() {
		numVehiculos++;
	}

	public void sacaVehiculo() {
		numVehiculos--;
	}

	public int numVehiculos() {
		return numVehiculos;
	}
}
