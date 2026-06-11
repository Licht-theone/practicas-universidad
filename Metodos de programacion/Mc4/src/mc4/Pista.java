package mc4;

import java.util.ArrayList;
import java.util.Collections;

public class Pista {
	private final int id;
	private ArrayList<Avion> pendientes;



	/**
	 * @param id
	 * @param pendientes
	 */
	public Pista(int id) {
		this.id = id;
		pendientes = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public int vuelosPendientes() {
		return pendientes.size();
	}

	public void asignaVuelo(Avion av) {
		pendientes.add(av);
	}

	public Avion buscaVuelo(String id) {
		for (int i = 0 ; i < pendientes.size(); i++) {
			if (pendientes.get(i).getId().equals(id)) {
				return pendientes.get(i);
			}
		}
		return null;
	}

	public boolean aterrizaAvion(String id) {
		Avion av = buscaVuelo(id);
		if (av == null) {
			return false;
		}
		pendientes.remove(av);
		return true;
	}

	public void ordenaVuelo() {
		Collections.sort(pendientes);
	}

	@Override
	public String toString() {
		return pendientes.toString();
	}

}
