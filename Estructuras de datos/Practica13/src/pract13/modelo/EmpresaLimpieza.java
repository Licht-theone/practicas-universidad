package pract13.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;
import pract13.secuencia_ordenada_abb.ISecuenciaOrdenada;
import pract13.secuencia_ordenada_abb.SecuenciaOrdenadaABB;

/**
 * Empresa de servicios de limpieza.
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version nov-22
 */
public class EmpresaLimpieza {
	private PriorityQueue<EquipoLimpieza> equipos = new PriorityQueue<EquipoLimpieza>();
	private SortedMap<String, Cliente> clientes = new TreeMap<String, Cliente>();
	private Map<String, EquipoLimpieza> buscaEquipos = new HashMap<String, EquipoLimpieza>();
	private ISecuenciaOrdenada<Cliente> ranking = new SecuenciaOrdenadaABB<Cliente>();
	
	/**
	 * Lanzada al tratar de anhadir un equipo con un Id ya existente.
	 */
	@SuppressWarnings("serial")
	public static class IdEquipoYaExistente extends RuntimeException {
	}

	/**
	 * Lanzada si el Id no corresponde a ningun equipo.
	 */
	@SuppressWarnings("serial")
	public static class IdEquipoNoValido extends RuntimeException {
	}

	/**
	 * Lanzada si se trata de finalizar un servicio para un equipo que se 
	 * encuentra libre en ese momento.
	 */
	@SuppressWarnings("serial")
	public static class EquipoSinServicioAsignado extends RuntimeException {
	}

	/**
	 * Lanzada al tratar de anhadir un cliente con un nombre ya existente.
	 */
	@SuppressWarnings("serial")
	public static class NombreClienteYaExistente extends RuntimeException {
	}

	/**
	 * Lanzada si el nombre no corresponde a ningun cliente.
	 */
	@SuppressWarnings("serial")
	public static class NombreClienteNoValido extends RuntimeException {
	}

	/**
	 * Lanzada si se proporciona una posicion en el ranking incorrecta.
	 */
	@SuppressWarnings("serial")
	public static class PosicionRankingIncorrecta extends RuntimeException {
	}

	/**
	 * Construye una empresa de limpieza simulando la lectura de los
	 * equipos de un fichero.
	 */
	public EmpresaLimpieza() {
		// Ficheros "leidos" del fichero
		EquipoLimpieza[] equiposFich = {new EquipoLimpieza("E01"),
			new EquipoLimpieza("E02"),
			new EquipoLimpieza("E03")};

		// Registra en la empresa los equipos "leidos" del fichero (equiposFich)
		for (EquipoLimpieza e: equiposFich) {
			buscaEquipos.put(e.id(), e);
			equipos.add(e);
		}
	}

	/**
	 * Anhade un cliente a la empresa.
	 * @param cliente cliente a anhadir.
	 * @throws NombreClienteYaExistente si ya existe otro cliente
	 * con el mismo nombre.
	 */
	public void anhadeCliente(Cliente cliente) throws NombreClienteYaExistente {
		if (clientes.containsKey(cliente.nombre())) {
			throw new NombreClienteYaExistente();
		}
		clientes.put(cliente.nombre(), cliente);
		ranking.anhade(cliente);
	}

	/**
	 * Asigna un servicio para el cliente indicado al equipo disponible
	 * con menos horas trabajadas.
	 * @param nombreCliente nombre del cliente para el que se necesita
	 * realizar el trabajo.
	 * @return equipo de limpieza al que ha sido asignado el trabajo o
	 * null si no hay ningun equipo disponible en este momento.
	 * @throws NombreClienteNoValido si el nombre no corresponde a
	 * ninguno de los clientes registrados.
	 */
	public EquipoLimpieza asignaServicioLimpieza(String nombreCliente)
			throws NombreClienteNoValido {
		if (!clientes.containsKey(nombreCliente)) {
			throw new NombreClienteNoValido();
		}
		if (equipos.isEmpty()) {
			return null;
		}
		EquipoLimpieza e = equipos.poll();
		ranking.elimina(clientes.get(nombreCliente));
		clientes.get(nombreCliente).encargaLimpieza();
		e.empiezaTrabajo(clientes.get(nombreCliente));
		ranking.anhade(clientes.get(nombreCliente));
		return e;
	}

	/**
	 * Regista que el equipo ha finalizado el servicio que estaba realizado.
	 * @param idEquipo identificador del equipo que ha finalizado el trabajo.
	 * @param horasTrabajadas horas que ha durado el trabajo realizado.
	 * @throws IdEquipoNoValido si el identificador no corresponde a ninguno
	 * de los equipos de la empresa.
	 * @throws EquipoSinServicioAsignado si el identificador corresponde a un
	 * equipo que no esta realizando un servicio en este momento.
	 */
	public void equipoFinalizaServicio(
			String idEquipo,
			int horasTrabajadas) 
					throws IdEquipoNoValido, EquipoSinServicioAsignado {
		if (!buscaEquipos.containsKey(idEquipo)) {
			throw new IdEquipoNoValido();
		}
		if (buscaEquipos.get(idEquipo).estaDisponible()) {
			throw new EquipoSinServicioAsignado();
		}
		buscaEquipos.get(idEquipo).finalizaTrabajo(horasTrabajadas);
		equipos.add(buscaEquipo(idEquipo));
	}

	/**
	 * Busca y retorna el equipo con el identificador indicado.
	 * @param idEquipo identificador del equipo buscado.
	 * @return el equipo con el identificador indicado o null
	 * si no existe ningun equipo con ese identificador.
	 */
	public EquipoLimpieza buscaEquipo(String idEquipo) {
		return buscaEquipos.get(idEquipo);
	}

	/**
	 * Retorna los clientes ordenados en funcion de sus nombres.
	 * @return los clientes ordenados en funcion de sus nombres.
	 */
	public Collection<Cliente> clientes() {
		return clientes.values();
	}

	/**
	 * Retorna el cliente que ocupa la posicion indicada en el
	 * ranking de clientes por numero de trabajos contratados.
	 * Las posiciones comienzan por 1.
	 * @param posEnRanking posicion en ranking del cliente.
	 * @return cliente que ocupa la posicion indicada
	 * @throws PosicionRankingIncorrecta si la posicion no esta
	 * en el rango valido [1 .. numero de clientes]
	 */
	public Cliente clienteEnPosRanking(int posEnRanking)
			throws PosicionRankingIncorrecta {
		if (posEnRanking < 1 || posEnRanking > ranking.tamanho()) {
			throw new PosicionRankingIncorrecta();
		}
		return ranking.obtenElemento(posEnRanking - 1);
	}

	/**
	 * Retorna la posicion ocupada por el cliente con el nombre indicado en el ranking
	 * de clientes por numero de trabajos contratados. 
	 * Las posiciones comienzan por 1.
	 * @param nombreCliente nombre del cliente.
	 * @return la posicion ocupada por el cliente en el rango [1 .. numero de clientes].
	 * @throws NombreClienteNoValido si el nombre no corresponde a
	 * ninguno de los clientes registrados.
	 */
	public int posClienteEnRanking(String nombreCliente)
			throws NombreClienteNoValido  {
		if (!clientes.containsKey(nombreCliente)) {
			throw new NombreClienteNoValido();
		}
		return ranking.busca(clientes.get(nombreCliente)) + 1;
	}

}
