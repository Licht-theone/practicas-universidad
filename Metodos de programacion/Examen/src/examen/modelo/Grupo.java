package examen.modelo;

/**
 * Grupo que realiza una visita guiada a un museo.
 *  
 * @author Metodos de Programacion (UC) y Aaron Alegria Puente
 * @version mar-2024
 */
public class Grupo {
	private final int numVisitantes;
	private static int ultimoCodigo = 0;
	private final String codigo;

	/**
	 * constructor.
	 * @param numVisitantes visitantes del grupo
	 */
	public Grupo(int numVisitantes) {
		ultimoCodigo++;
		this.numVisitantes = numVisitantes;
		codigo = "GRP" + ultimoCodigo;
	}

	/**
	 * observador del numero de visitantes.
	 * @return el numero de visitantes
	 */
	public int numVisitantes() {
		return numVisitantes;
	}

	/**
	 * observador del codigo del grupo.
	 * @return el codigo del grupo
	 */
	public String codigo() {
		return codigo;
	}
}
