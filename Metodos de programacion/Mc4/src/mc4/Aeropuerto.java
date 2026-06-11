package mc4;

import java.util.ArrayList;

public class Aeropuerto {
	private final int numPistas;
	private ArrayList<Avion> registro;
	private Pista[] pistas;

	/**
	 * @param numPistas
	 */
	public Aeropuerto(int numPistas) {
		this.numPistas = numPistas;
		registro = new ArrayList<>();
		pistas = new Pista[numPistas];
		for (int i = 0; i < numPistas; i++) {
			pistas[i] = new Pista(i);
		}
	}

	public int asignaVueloPista(String id) {
		int min = Integer.MAX_VALUE;
		int nuevoMin;
		Pista ps = null;
		for (int i = 0; i < numPistas; i++) {
			nuevoMin = pistas[i].vuelosPendientes();
			if (nuevoMin < min) {
				min = nuevoMin;
				ps = pistas[i];
			}
		}
		Avion av = new Avion(id);
		if (registro.contains(av) == true) {
			return -1;
		}
		ps.asignaVuelo(av);
		return ps.getId();
	}

	public boolean aterrizaVueloPista(int pistaId, String vueloId) {
		Pista p;
		Avion v;
		p = buscaPista(pistaId);
		if (p == null) {
			return false;
		}
		v = p.buscaVuelo(vueloId);
		if(v == null) {
			return false;
		}
		if (registro.contains(v) == true) {
			return false;
		}
		else {
			p.aterrizaAvion(vueloId);
			registro.add(v);
			return true;
		}
	}

	public Pista buscaPista (int id) {
		for (int i = 0; i < numPistas; i++) {
			if (pistas[i].getId() == id) {
				return pistas[i];
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return registro.toString();
	}

}
