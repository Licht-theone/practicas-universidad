package mc3;

import java.util.ArrayList;

public class Inmo {
	private Agente[] agentes;
	private int numAgentes = 0;
	private ArrayList<Piso> pisos;
	
	/**
	 * crea inmo.
	 * @param maxAgentes max agentes
	 */
	public Inmo(int maxAgentes) {
		agentes = new Agente[maxAgentes];
		pisos = new ArrayList<>();
	}
	
	/**
	 * a.
	 * @param piso piso
	 * @return id
	 */
	public String anhadePiso(Piso piso) {
		pisos.add(piso);
		return piso.getIdentificador();
	}
	
	/**
	 * añade agente.
	 * @param ag agente
	 * @return true si pudo false en caso contrario
	 */
	public boolean anhadeAgente(Agente ag) {
		if (buscaAgente(ag.getDni()) != null) {
			return false;
		}
		agentes[numAgentes] = ag;
		numAgentes++;
		return true;
	}
	
	/**
	 * a.
	 * @param dni dni
	 * @return agente o null
	 */
	public Agente buscaAgente(String dni) {
		for (int i = 0; i < numAgentes; i++) {
			if (dni.equals(agentes[i].getDni())) {
				return agentes[i];
			}
		}
		return null;
	}
	
	/**
	 * asigna pis.
	 * @param ref referencia
	 * @param dni dni
	 * @return true o false
	 */
	public boolean asignaPiso(String ref, String dni) {
		Piso p = buscaPiso(ref);
		Agente ag = buscaAgente(dni);
		if (ag == null || p == null || p.getAgente() != null) {
			return false;
		}
		p.setAgente(ag);
		return true;
	}

	public Piso buscaPiso(String ref) {
		for(int i = 0; i < pisos.size(); i++) {
			if (pisos.get(i).getIdentificador().equals(ref)) {
				return pisos.get(i);
			}
		}
		return null;
	}

	public boolean vendePiso(String ref) {
		Piso p = buscaPiso(ref);
		if (p == null || p.getAgente() == null) {
			return false;
		}
		Agente ag = p.getAgente();
		ag.addComision(p.getPrecio());
		pisos.remove(p);
		return true;
	}

}
