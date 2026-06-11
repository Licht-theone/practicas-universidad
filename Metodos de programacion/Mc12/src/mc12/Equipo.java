package mc12;

import java.util.ArrayList;

public class Equipo {
	private final int id;
	private ArrayList<Operario> operarios;
	/**
	 * @param id
	 * @param operarios
	 */
	public Equipo(int id) {
		this.id = id;
		operarios = new ArrayList<Operario>();
	}
	
	public int id() {
		return id;
	}
	
	public boolean anhadeOperario(Operario o) {
		if (buscaOperario(o.getNombre()) != null) {
			return false;
		}
		operarios.add(o);
		return true;
	}
	
	public Operario buscaOperario(String nombre) {
		for (Operario o: operarios) {
			if (o.getNombre().equals(nombre)) {
				return o;
			}
		}
		return null;
	}
	
	public void anhandeReparacion(String nombre) {
		Operario o = buscaOperario(nombre);
		o.anhadereparacion();
	}
	
}
