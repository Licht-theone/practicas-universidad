package c3_entrega_regalos;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Gestion del reparto de regalos entre los socios de una asociacion.
 * 
 * @author  Metodos de Programacion (UC)
 * @version ene-23
 */
public class RepartoRegalos {
	private PriorityQueue<Regalo> regalos = new PriorityQueue<Regalo>();
	private Queue<Socio> socios = new LinkedList<Socio>();
	private Map<Integer, Socio> sociosBus = new HashMap<>();
	private SortedMap<Integer, Socio> sociosSinRegalo = new TreeMap<Integer, Socio>();
	
	/**
	 * Lanzada si no hay regalos que entregar.
	 */
	@SuppressWarnings("serial")
	public static class NoHayRegalos extends RuntimeException {
	}
	
	/**
	 * Lanzada si no hay socios inscritos que aun no hayan recibido un regalo.
	 */
	@SuppressWarnings("serial")
	public static class NoHaySocios extends RuntimeException {
	}
	
	/**
	 * Lanzada si se trata de inscribir un socio ya escrito.
	 */
	@SuppressWarnings("serial")
	public static class SocioYaInscrito extends RuntimeException {
	}
	
	/**
	 * Lanzada si se pregunta por el regalo de un socio no inscrito.
	 */
	@SuppressWarnings("serial")
	public static class SocioNoInscrito extends RuntimeException {
	}

	/**
	 * Inscribe un socio para la entrega.
	 * @param socio socio a inscribir.
	 * @throws SocioYaInscrito si el socio ya esta inscrito.
	 */
	public void inscribeSocio(Socio socio) throws SocioYaInscrito {
		if (sociosBus.containsKey(socio.numSocio())) {
			throw new SocioYaInscrito();
		}
		socios.offer(socio);
		sociosBus.put(socio.numSocio(), socio);
		sociosSinRegalo.put(socio.numSocio(), socio);
	}
	
	/**
	 * Registra un regalo para entregar a los socios.
	 * @param regalo regalo a entregar.
	 */
	public void registraRegalo(Regalo regalo) {
		regalos.add(regalo);
	}
	
	/**
	 * Asigna el regalo de mas valor por entregar al socio sin regalo que lleva mas
	 * tiempo inscrito.
	 * @return socio al que se ha asignado regalo.
	 * @throws NoHayRegalos si no hay regalos que entregar.
	 * @throws NoHaySocios si no hay socios inscritos sin regalo aun.
	 */
	public Socio asignaRegalo() throws NoHayRegalos, NoHaySocios {
		if (regalos.isEmpty()) {
			throw new NoHayRegalos();
		}
		if (socios.isEmpty() || sociosSinRegalo.isEmpty()) {
			throw new NoHaySocios();
		}
		Socio socio = socios.poll();
		sociosSinRegalo.remove(socio.numSocio());
		socio.registraRegalo(regalos.poll());
		return socio;
	}
	
	/**
	 * Retorna los socios que no tienen regalo por ahora ordenados de menor a mayor
	 * numero de socio.
	 * @return los socios que no tienen regalo por ahora ordenados de menor a mayor
	 * numero de socio.
	 */
	public Collection<Socio> sociosSinRegalo() {
		return sociosSinRegalo.values();
	}
	
	/**
	 * Retorna el regalo asignado al socio con el numero indicado.
	 * @param numSocio numero del socio.
	 * @return el regalo asignado al socio o null si el socio con el numero indicado
	 * no tiene ningun regalo asignado por ahora.
	 * @throws SocioNoInscrito si el numero de socio no corresponde a ninguno de los 
	 * socios inscritos.
	 */
	public Regalo regaloDeSocio(int numSocio) throws SocioNoInscrito {
		if (!sociosBus.containsKey(numSocio)) {
			throw new SocioNoInscrito();
		}
		return sociosBus.get(numSocio).regalo();
	}
}
