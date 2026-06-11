package mc10;

import java.util.ArrayList;

public class Empresa {
	private static final int MAX_OFI = 10;
	private ArrayList<Vehiculo> vehiculos;
	private Oficina[] oficinas = new Oficina[MAX_OFI];
	private static final int VEHICULO_NO_EXISTE = -1;
	private static final int VEHICULO_EN_REP = -2;
	private static final int VEHICULO_EN_OF = -3;

	public Empresa() {
		vehiculos = new ArrayList<Vehiculo>();
		for (int i = 0; i < MAX_OFI; i++) {
			oficinas[i] = new Oficina(i);
		}
	}

	public boolean anhadeVehiculo(String matricula, int num) {
		if (buscaVehiculo(matricula) != null || num < 0 || num >= MAX_OFI) {
			return false;
		}
		Vehiculo v = new Vehiculo(matricula, oficinas[num]);
		oficinas[num].meteVehiculo();
		vehiculos.add(v);
		return true;
	}

	public Vehiculo buscaVehiculo(String matricula){
		for (Vehiculo v: vehiculos) {
			if (v.matricula().equals(matricula)) {
				return v;
			}
		}
		return null;
	}

	public boolean eliminaVehiculo(String matricula) {
		Vehiculo v = buscaVehiculo(matricula);
		if (v == null) {
			return false;
		}
		Oficina o = v.getOficina();
		o.sacaVehiculo();
		vehiculos.remove(v);
		return true;
	}

	public boolean vehiculoComienzaReparto(String matricula) {
		Vehiculo v = buscaVehiculo(matricula);
		if (v == null || v.getOficina() == null) {
			return false;
		}
		Oficina o = v.getOficina();
		o.sacaVehiculo();
		v.setOficina(null);
		return true;
	}

	public int consultaLocalizacion(String matricula) {
		Vehiculo v = buscaVehiculo(matricula);
		if (v == null) {
			return VEHICULO_NO_EXISTE;
		}
		if (v.getOficina() == null) {
			return VEHICULO_EN_REP;
		}
		return VEHICULO_EN_OF;
	}

	public boolean estacionaVehiculoEnOfi(String matricula, int numOfi) {
		Vehiculo v = buscaVehiculo(matricula);
		if (v == null || v.getOficina() != null || numOfi < 0 || numOfi >= MAX_OFI) {
			return false;
		}
		oficinas[numOfi].meteVehiculo();
		v.setOficina(oficinas[numOfi]);
		return true;
	}

	public Oficina oficinaConMenosVehi() {
		int min = Integer.MAX_VALUE;
		int nuevoMin;
		Oficina o = null;
		for (int i = 0; i < MAX_OFI; i++) {
			nuevoMin = oficinas[i].numVehiculos();
			if (nuevoMin < min) {
				min = nuevoMin;
				o = oficinas[i];
			}
		}
		return o;
	}
}
