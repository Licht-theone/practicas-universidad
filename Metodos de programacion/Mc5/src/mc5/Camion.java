package mc5;

import java.util.ArrayList;

public class Camion {
	private final int id;
	private ArrayList <Paquete> paquetes;

	/**
	 * @param id
	 */
	public Camion(int id) {
		this.id = id;
		paquetes = new ArrayList<Paquete>();
	}

	public boolean cargaPaquete(Paquete paq) {
		if (paquetes.contains(paq)) {
			return false;
		}
		paquetes.add(paq);
		return true;
	}

	public boolean entregaPaquete(String id) {
		Paquete paq = buscaPaquete(id);
		if (paq == null) {
			return false;
		}
		paquetes.remove(paq);
		return true;
	}

	public Paquete buscaPaquete(String id) {
		for (Paquete paq: paquetes) {
			if (paq.getId().equals(id)) {
				return paq;
			}
		}

		return null;
	}

	public int numPaquetes() {
		return paquetes.size();
	}

	@Override
	public String toString() {
		return "Camion [id=" + id + ", paquetes=" + paquetes + "]";
	}

}
