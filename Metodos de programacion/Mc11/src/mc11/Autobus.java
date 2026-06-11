package mc11;

public class Autobus {
	private static final int MAX_PLAZAS = 60;
	private final int id;
	private Socio[] pasajeros = new Socio[MAX_PLAZAS];
	private int numOcupantes;
	/**
	 * @param id
	 */
	public Autobus(int id) {
		this.id = id;
		numOcupantes = 0;
	}
	public int getNumOcupantes() {
		return numOcupantes;
	}
	public boolean anhadeOcupante(Socio s) {
		if (numOcupantes >= MAX_PLAZAS) {
			return false;
		}
		pasajeros[numOcupantes] = s;
		numOcupantes++;
		return true;
	}
	public int getId() {
		return id;
	}
	
	public boolean eliminaSocio(String dni) {
		for (int i = 0 ; i < numOcupantes; i++) {
			if (pasajeros[i].dni().equals(dni)) {
				pasajeros[i] = null;
				numOcupantes--;
				return true;
			}
		}
		return false;
	}
	
	public String sociosEnBus() {
		String s = "Lista:\n";
		for (int i = 0; i < numOcupantes; i++) {
			s += pasajeros[i].toString() + "\n";
		}
		return s;
	}
	
	public Socio busca(String dni) {
		for (int i = 0 ; i < numOcupantes; i++) {
			if (pasajeros[i].dni().equals(dni)) {
				return pasajeros[i];
			}
		}
		return null;
	}
}
