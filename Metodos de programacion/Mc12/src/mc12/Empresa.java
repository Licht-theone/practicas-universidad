package mc12;

public class Empresa {
	private static final int NUM_EQUIPOS = 7;
	private Equipo[] equipos = new Equipo[NUM_EQUIPOS];
	
	public Empresa() {
		for (int i = 0; i < NUM_EQUIPOS; i++) {
			equipos[i] = new Equipo(i);
		}
	}
	
	public boolean anhadeOperario(int numEq, String nombre) {
		Operario o = new Operario(nombre);
		if (numEq < 0 || numEq > NUM_EQUIPOS || !equipos[numEq].anhadeOperario(o)) {
			return false;
		}
		return true;
	}
	
	public boolean asignaReparacion(int eq, String nombre) {
		if (eq < 0 || eq > NUM_EQUIPOS || equipos[eq].buscaOperario(nombre) == null) {
			return false;
		}
		equipos[eq].anhandeReparacion(nombre);
		return true;
	}
	
	public int reparacionesOperario(String nombre) {
		for (Equipo e: equipos) {
			Operario o = e.buscaOperario(nombre);
			if (o != null) {
				return o.numReparaciones();
			}
		}
		return -1;
	}
	
}
