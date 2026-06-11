package pract09.pract07.modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import pract09.IMapa;
import pract09.MapaDispersionAbierta;

/**
 * Empresa con sus registros de productos y pedidos.
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version oct-2021
 */
public class Empresa {
	private Queue<Pedido> colaPedidos = new LinkedList<>();
	//private LinkedList<Producto> catalogo = new LinkedList<>();
	private IMapa<String, Producto> catalogo = 
			new MapaDispersionAbierta<String, Producto>();
	private ArrayList<Producto> descatalogados = new ArrayList<>();

	/**
	 * Lanzada cuando el codigo no se corresponde a ninguno de los productos en
	 * el catalogo.
	 */
	@SuppressWarnings("serial")
	public static class CodigoProductoIncorrecto extends RuntimeException {
	}

	/**
	 * Lanzada cuando no hay ningun pedido pendiente para procesar.
	 */
	@SuppressWarnings("serial")
	public static class NoHayPedidosPendientes extends RuntimeException {
	}

	/**
	 * Lanzada cuando no hay suficientes unidades del producto solicitado.
	 */
	@SuppressWarnings("serial")
	public static class CantidadInsuficiente extends RuntimeException {
	}

	/**
	 * Lanzada cuando la posicion indicada no corresponde a ningun producto descatalogado.
	 */
	@SuppressWarnings("serial")
	public static class PosicionIncorrecta extends RuntimeException {
	}

	/**
	 * Construye la empresa.
	 * Crea algunos productos para simular su lectura desde un fichero.
	 * @param nomFich nombre del fichero.
	 * @throws FileNotFoundException si no se puede abrir el fichero
	 */
	//O(n)
	public Empresa(String nomFich) throws FileNotFoundException {
		try (Scanner in = new Scanner(new FileReader(nomFich))) {
			while (in.hasNext()) {
				String codigo = in.next();
				Producto p = new Producto(codigo, in.nextInt());
				catalogo.anhade(codigo, p);
			}
		}
	}

	/**
	 * Registra un pedido.
	 * @param codProducto codigo del producto solicitado.
	 * @param numUnidades numero de unidades solicitadas.
	 * @throws CodigoProductoIncorrecto si no existe ningun producto con el codigo indicado.
	 * @throws CantidadInsuficiente si no hay suficientes unidades del producto solicitado.
	 */
	//O(1)
	public void registraPedido(String codProducto, int numUnidades)
			throws CodigoProductoIncorrecto, CantidadInsuficiente {
		Producto producto = catalogo.busca(codProducto);
		if (producto == null) {
			throw new CodigoProductoIncorrecto();
		}

		if (numUnidades > producto.numUnidadesDisponibles()) {
			throw new CantidadInsuficiente();
		}

		colaPedidos.add(new Pedido(producto, numUnidades));
	}

	/**
	 * Procesa el pedido pendiente mas antiguo.
	 * @return pedido que ha sido procesado.
	 * @throws NoHayPedidosPendientes si no hay ningún pedido pendiente
	 * @throws CantidadInsuficiente si no hay suficientes unidades del producto solicitado.
	 */
	//O(1)
	public Pedido procesaSiguientePedido() throws NoHayPedidosPendientes, CantidadInsuficiente {
		if (colaPedidos.isEmpty()) {
			throw new NoHayPedidosPendientes();
		}

		Pedido pedido = colaPedidos.poll();

		if (pedido.producto().numUnidadesDisponibles() < pedido.numUnidades()) {
			throw new CantidadInsuficiente();
		}
		pedido.producto().reduceUnidadesDisponibles(pedido.numUnidades());

		return pedido;
	}

	/**
	 * Descataloga los productos con un numero de unidades disponibles inferior
	 * al umbral.
	 * @param umbralUnidades umbral del numero de unidades por debajo del cual
	 * descatalogar los productos.
	 */
	//O(n)
	public void descatalogaProductos(int umbralUnidades) {
		List<Producto> lst = catalogo.valores();
		Iterator<Producto> iter = lst.listIterator();
		while (iter.hasNext()) {
			Producto producto = iter.next();
			if (producto.numUnidadesDisponibles() < umbralUnidades) {
				catalogo.elimina(producto.codigo());
				descatalogados.add(producto);
			}
		}
	}

	/**
	 * Retorna el producto que ocupa la posicion indicada en el catalogo de productos
	 * descatalogados.
	 * @param posicion posicion del producto en el registro de descatalogados.
	 * @return producto en la posicion indicada.
	 * @throws PosicionIncorrecta si la posicion no es valida.
	 */
	//O(1)
	public Producto productoDescatalogado(int posicion) 
			throws PosicionIncorrecta {
		if (posicion < 0 || posicion >= descatalogados.size()) {
			throw new PosicionIncorrecta();
		}

		return descatalogados.get(posicion);
	}
	
	/**
	 * Retorna el numero de productos descatalogados.
	 * @return el numero de productos descatalogados.
	 */
	public int numProductosDescatalogados() {
		return descatalogados.size();
	}

}
