package examen.modelo;

/**
 * Museo que realiza visitas guiadas a grupos.
 *  
 * @author Aaron Alegria Puente
 * @version mar-2024
 */
public class Museo {
	private static final int VISITAS_DIARIAS = 4;
	private Visita[] visitas = new Visita[VISITAS_DIARIAS];
	private static final int ERROR = -1;

	/**
	 * constructor.
	 */
	public Museo() {
		for (int i = 0; i < VISITAS_DIARIAS; i++) {
			visitas[i] = new Visita(i);
		}
	}

	/**
	 * realiza la reserva de un grupo para una visita.
	 * @param id id de la visita a reservar
	 * @param grupo el grupo que quiere reservar
	 * @return false en caso de que no se pueda y true si se realiza correctamente
	 */
	public boolean realizaReservaGrupo(int id, Grupo grupo) {
		if (id < 0 || id >= VISITAS_DIARIAS 
				|| !visitas[id].tieneCapacidadSuficiente(grupo.numVisitantes())) {
			return false;
		}
		visitas[id].anhadeGrupo(grupo);
		return true;
	}

	/**
	 * anula la reserva de un grupo para una visita.
	 * @param id el id de la visita
	 * @param codigo el codigo del grupo
	 * @return true si se anula false en caso de error
	 */
	public boolean anulaReserva(int id, String codigo) {
		if (id < 0 || id >= VISITAS_DIARIAS) {
			return false;
		}
		Grupo g = visitas[id].buscaGrupo(codigo);
		if (g == null) {
			return false;
		}
		visitas[id].eliminaGrupo(g);
		return true;
	}

	/**
	 * metodo que comprueba cuantos grupos de una visita superan el numero de visitantes dado.
	 * @param id identificador de la visita
	 * @param visitantes el numero de visitantes minimo
	 * @return el numero de grupos con ese numero de visitantes o mas o -1 en caso de error
	 */
	public int gruposConTamanho(int id, int visitantes) {
		if (id < 0 || id >= VISITAS_DIARIAS) {
			return ERROR;
		}
		return visitas[id].gruposConTamanho(visitantes);
	}

	/**
	 * metodo que retorna una visita con capacidad suficiente para un numero de visitantes.
	 * @param visitantes el numero de visitantes a comprobar
	 * @return la visita o null en caso de que no haya
	 */
	public Visita buscaVisitaConCapaciad(int visitantes) {
		for (Visita v: visitas) {
			if (v.tieneCapacidadSuficiente(visitantes)) {
				return v;
			}
		}
		return null;
	}
}
