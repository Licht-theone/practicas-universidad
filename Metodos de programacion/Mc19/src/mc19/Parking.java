package mc19;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Parking {
	public class Completo extends RuntimeException{}
	public class NoExiste extends RuntimeException{}
	public class YaExiste extends RuntimeException{}
	private static final int MAX_PLAZAS = 40;
	private ArrayList<Estacionamiento> estacionamientos = new ArrayList<Estacionamiento>();
	private ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	
	public void anhade(Vehiculo v) throws YaExiste{
		if (buscaVehi(v.getMatricula()) != null) {
			throw new YaExiste();
		}
		vehiculos.add(v);
	}

	public Vehiculo buscaVehi(String m) {
		for (Vehiculo v: vehiculos) {
			if (v.getMatricula().equals(m)) {
				return v;
			}
		}
		return null;
	}
	
	public Estacionamiento buscaEs(String m, int min) {
		for (Estacionamiento e: estacionamientos) {
			if (e.getEstacionado().getMatricula().equals(e)) {
				if (e.minutos() < min) {
					return e;
				}
			}
		}
		return null;
	}
}
