package pract12.p2_candidatos.modelo;

import java.util.HashMap;
import java.util.Map;

import pract12.p1_cola_prio_monticulo.ColaPrioMonticulo;

/**
 * Candidatos para un puesto de trabajo.
 *
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version dic-2020
 */
public class CandidatosPuesto {
	private ColaPrioMonticulo<Candidato> candidatos;
	private final int maxNumCandidatos;
	private Map<String, Candidato> nombres;
	
	/**
	 * Lanzada si ya existe otro candidato con el nombre indicado.
	 */
	@SuppressWarnings("serial")
	public static class NombreCandidatoYaExistente extends RuntimeException {
	}
	
	/**
	 * Lanzada si se ha alcanzado el numero maximo de candidatos para el puesto.
	 */
	@SuppressWarnings("serial")
	public static class AlcanzadoMaximoNumCandidatos extends RuntimeException {
	}
	
	/**
	 * Construye el registro de los candidatos para un puesto de trabajo.
	 * @param maxNumCandidatos numero maximo de candidatos para el puesto.
	 */
	public CandidatosPuesto(int maxNumCandidatos) {
		candidatos = new ColaPrioMonticulo<Candidato>(maxNumCandidatos);
		this.maxNumCandidatos = maxNumCandidatos;
		nombres = new HashMap<String, Candidato>();
	}

	/**
	 * Anhade un candidato para el puesto.
	 * @param candidato candidato a anhadir.
	 * @throws NombreCandidatoYaExistente si ya existe otro candidato con el
	 * nombre indicado.
	 * @throws AlcanzadoMaximoNumCandidatos si se ha alcanzado el numero maximo
	 * de candidatos para el puesto.
	 */
	public void anhadeCandidato(Candidato candidato) 
			throws NombreCandidatoYaExistente, AlcanzadoMaximoNumCandidatos {
		if (nombres.containsKey(candidato.nombre())) {
			throw new NombreCandidatoYaExistente();
		}
		if (candidatos.tamanho() >= maxNumCandidatos) {
			throw new AlcanzadoMaximoNumCandidatos();
		}
		candidatos.encolaConPrioridad(candidato);
		nombres.put(candidato.nombre(), candidato);
		
	}

	/**
	 * Retorna y elimina el candidato mejor valorado.
	 * @return el candidato mejor valorado o null si no hay ningun
	 * candidato en la cola.
	 */
	public Candidato mejorValorado() {
		Candidato c = candidatos.desencolaMasPrioritario();
		if (c == null) {
			return null;
		}
		nombres.remove(c.nombre());
		return c;
	}

}
