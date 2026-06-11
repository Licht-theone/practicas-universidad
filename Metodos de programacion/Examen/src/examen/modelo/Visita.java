package examen.modelo;

import java.util.ArrayList;

/**
 * Visita guiada de un museo.
 *  
 * @author Aaron Alegria Puente
 * @version mar-2024
 */
public class Visita {
	private final int id;
	private static final int MAX_VISITANTES = 15;
	private int visitantesTotales;
	private ArrayList<Grupo> gruposEnVisita = new ArrayList<Grupo>();

	/**
	 * constructor.
	 * @param id id de la visita
	 */
	public Visita(int id) {
		this.id = id;
		visitantesTotales = 0;
	}

	/**
	 * observador del id de la visita.
	 * @return el id de la visita
	 */
	public int id() {
		return id;
	}

	/**
	 * metodo que añade un grupo a la visita.
	 * @param grupo el grupo a añadir
	 */
	public void anhadeGrupo(Grupo grupo) {
		visitantesTotales += grupo.numVisitantes();
		gruposEnVisita.add(grupo);
	}

	/**
	 * metodo que busca si un grupo esta en la visita.
	 * @param codigo el codigo del grupo a buscar
	 * @return el grupo o null en caso de no encontrarle
	 */
	public Grupo buscaGrupo(String codigo) {
		for (Grupo g: gruposEnVisita) {
			if (g.codigo().equals(codigo)) {
				return g;
			}
		}
		return null;
	}

	/**
	 * metodo que comprueba si la visita tiene capacidad para esos visitantes dados.
	 * @param numVis el numero de visitantes a comprobar
	 * @return false si no tiene espacio true si tiene espacio
	 */
	public boolean tieneCapacidadSuficiente(int numVis) {
		int visTotales = visitantesTotales;
		if (visTotales + numVis > MAX_VISITANTES) {
			return false;
		}
		return true;
	}

	/**
	 * observador de los visitantes que hay en la visita.
	 * @return el numero de visitantes
	 */
	public int visitantesTotales() {
		return visitantesTotales;
	}

	/**
	 * elimina un grupo de la visita.
	 * @param grupo el grupo a eliminar
	 */
	public void eliminaGrupo(Grupo grupo) {
		visitantesTotales -= grupo.numVisitantes();
		gruposEnVisita.remove(grupo);
	}

	/**
	 * metodo que comprueba cuantos grupos con un tamaño igual o superior al numero de visitantes
	 * dado hay.
	 * @param visitantes el numero de visitantes minimo
	 * @return el numero de grupos con >= numero de visitantes
	 */
	public int gruposConTamanho(int visitantes) {
		int contador = 0;
		for (Grupo g: gruposEnVisita) {
			if (g.numVisitantes() >= visitantes) {
				contador++;
			}
		}
		return contador;
	}
}
