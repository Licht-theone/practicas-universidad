package mc11;

import java.util.ArrayList;

public class Asociacion {
	private Autobus[] autobuses;
	private ArrayList<Socio> socios = new ArrayList<Socio>();
	private final int numBuses;
	/**
	 * @param numBuses
	 */
	public Asociacion(int numBuses) {
		this.numBuses = numBuses;
		autobuses = new Autobus[numBuses];
		for (int i = 0; i < numBuses; i++) {
			autobuses[i] = new Autobus(i);
		}
	}
	
	public Socio buscaSocio(String dni) {
		for (Socio s: socios) {
			if (s.dni().equals(dni)) {
				return s;
			}
		}
		return null;
	}
	
	public boolean anhadeSocio(String nombre, String dni) {
		if (buscaSocio(dni) != null) {
			return false;
		}
		Socio a = new Socio(nombre, dni);
		socios.add(a);
		int min = Integer.MAX_VALUE;
		int nuevoMin;
		Autobus b = null;
		for (int i = 0; i < numBuses; i++) {
			nuevoMin = autobuses[i].getNumOcupantes();
			if (nuevoMin < min) {
				min = nuevoMin;
				b = autobuses[i];
			}
		}
		b.anhadeOcupante(a);
		return true;
	}
	
	public String sociosEnBus(int id) {
		if (id < 0 || id > numBuses) {
			return null;
		}
		return autobuses[id].sociosEnBus();
	}
	
	public boolean cambiasociobus(String dni, int id) {
		if (buscaSocio(dni) == null || id < 0 || id > numBuses) {
			return false;
		}
		for (int i = 0; i < numBuses; i++) {
			if (autobuses[i].busca(dni) != null) {
				autobuses[id].anhadeOcupante(buscaSocio(dni));
				autobuses[i].eliminaSocio(dni);
			}
		}
		return true;
	}
	
}
