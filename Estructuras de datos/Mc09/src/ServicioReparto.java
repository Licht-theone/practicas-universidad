

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * Servicio de reparto de pedido para entrega a domicilio de un supermercado.
 * @author Estructuras de Datos (UC)
 * @version 2-dic-2021
 */
public class ServicioReparto {
	private Queue<Pedido> pendientes = new LinkedList<Pedido>();
	private SortedMap<String, Pedido> buscaPedido = new TreeMap<String, Pedido>();
	private Map<String, Camion> camiones = new HashMap<String, Camion>();

	/**
	 * Lanzada si se trata de anhadir un camion ya existente.
	 */
	@SuppressWarnings("serial")
	public static class CamionYaExistente extends RuntimeException {
	}

	/**
	 * Lanzada si no existe ningun camion con la matricula indicada.
	 */
	@SuppressWarnings("serial")
	public static class MatriculaIncorrecta extends RuntimeException {
	}

	/**
	 * Lanzada si se trata de anhadir un pedido con la misma direccion de
	 * que alguno de los pedidos pendientes de entrega.
	 */
	@SuppressWarnings("serial")
	public static class DireccionYaExistente extends RuntimeException {
	}

	/**
	 * Anhade el camion a los camiones disponibles para el reparto.
	 * @param camion camion a anhadir.
	 * @throws CamionYaExistente si ya existe otro camion con la misma matricula.
	 */
	// Complejidad temporal: 
	public void anhadeCamion(Camion camion) throws CamionYaExistente {
		if (camiones.containsKey(camion.matricula())) {
			throw new CamionYaExistente();
		}
		camiones.put(camion.matricula(), camion);
	}

	/**
	 * Registra el pedido y lo anhade a los pedidos pendientes para reparto.
	 * @param pedido pedido a anhadir.
	 * @throws DireccionYaExistente si ya existe otro pedido pendiente de reparto
	 * con la misma direccion.
	 */
	// Complejidad temporal:
	public void registraPedido(Pedido pedido) throws DireccionYaExistente {
		if (buscaPedido.containsKey(pedido.direccion())) {
			throw new DireccionYaExistente();
		}
	}

	/**
	 * Carga pedidos pendientes de reparto en el camion con la matricula indicada.
	 * Con el camion vacio carga los pedidos mas antiguos pendientes de entrega
	 * hasta llegar a un pedido con el que se superaria la carga maxima del camion o
	 * hasta que no queden mas pedidos pendientes.
	 * Los pedidos cargados en el camion dejan de estar pendientes de entrega.
	 * @param matricula matricula del camion en el que cargar los pedidos.
	 * @throws MatriculaIncorrecta si no existe ningun camion con la matricula
	 * indicada.
	 */
	// Complejidad temporal:
	public void cargaCamion(String matricula) throws MatriculaIncorrecta {
		if (!camiones.containsKey(matricula)) {
			throw new MatriculaIncorrecta();
		}
		camiones.get(matricula).vacia();
		boolean carga = true;
		while (carga) {
			carga = camiones.get(matricula).cargaCamion(pendientes.poll());
		}
	}

	/**
	 * Busca en un camion un pedido con la direccion indicada. 
	 * @param matricula matricula del camion en el que buscar el pedido.
	 * @param direccion direccion del pedido buscado.
	 * @return el pedido cargado en el camion con la direccion indicada o
	 * null en caso de que el camion no transporte ningun pedido para esa direccion.
	 * @throws MatriculaIncorrecta si no existe ningun camion con la matricula
	 * indicada.
	 */
	// Complejidad temporal: 
	public Pedido buscaPedidoEnCamion(String matricula, String direccion)
			throws MatriculaIncorrecta {
		if (!camiones.containsKey(matricula)) {
			throw new MatriculaIncorrecta();
		}
		return camiones.get(matricula).buscaPedido(direccion);
	}

	/**
	 * Retorna los pedidos pendientes de entrega ordenados alfabeticamente respecto
	 * a su direccion de entrega.
	 * @return los pedidos pendientes de entrega ordenados alfabeticamente respecto
	 * a su direccion de entrega.
	 */
	// Complejidad temporal: 
	public Collection<Pedido> pedidosPendientes() {
		return buscaPedido.values();
	}

}
