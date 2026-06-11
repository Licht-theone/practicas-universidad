package mc5;

import java.util.ArrayList;

public class Empresa {
	private static final int ERR = 11;
	private static final int MAX_CAMIONES = 10;
	private Camion[] camiones;
	private ArrayList<Paquete> registro;
	
	/**
	 * @param camiones
	 */
	public Empresa() {
		camiones = new Camion[MAX_CAMIONES];
		registro = new ArrayList<>();
		for (int i = 0; i < MAX_CAMIONES; i++) {
			camiones[i] = new Camion(i);
		}
	}

	public boolean cargaPaqueteCamion(Paquete paq, int id) {
		if (!camiones[id].cargaPaquete(paq)) {
			return false;
		}
		registro.add(paq);
		return true;
	}

	public boolean entregaPaquete(String id) {
		int cam = buscaCamionConPaquete(id);
		if (cam == ERR) {
			return false;
		}
		camiones[cam].entregaPaquete(id);
		return true;
	}

	public int buscaCamionConPaquete(String id) {
		for (int i = 0; i < MAX_CAMIONES; i++) {
			if (camiones[i].buscaPaquete(id) != null) {
				return i;
			}
		}
		return ERR;
	}

	public String listaCamionesVacios() {
		String lista = "CAMIONES VACIOS:\n";
		for (int i = 0; i < MAX_CAMIONES; i++) {
			if (camiones[i].numPaquetes() == 0) {
				lista += camiones[i].toString() + "\n";
			}
		}
		return lista;
	}

}
