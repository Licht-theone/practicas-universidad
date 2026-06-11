package macaya2;

public class Avion {
	//atributos
	private final String id;
	private final String modelo;
	private int pasajeros;

	//atributo static para controlar avion 1,2,3, y q no se repitan
	//ni gestionarlo nosotros
	private static int aut = 1;
	//metodos
	//constructor

	/**
	 * @param modelo
	 * @param pasajeros
	 */
	public Avion(String modelo, int pasajeros) {
		this.modelo = modelo;
		this.pasajeros = pasajeros;
		this.id = "Avion"+aut;
		aut++;
	}

	//getters & setters
	public int getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(int pasajeros) {
		this.pasajeros = pasajeros;
	}

	public String getId() {
		return id;
	}

	public String getModelo() {
		return modelo;
	}

	public void incrPasajeros(int pasajeros) {
		this.pasajeros += pasajeros;
	}

	@Override
	public String toString() {
		return "Avion [id=" + id + ", modelo=" + modelo + ", pasajeros=" + pasajeros + "]\n";
	}

}
