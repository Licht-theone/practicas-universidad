package pract07.modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Scanner;

/**
 * Empresa con sus registros de productos y pedidos.
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version oct-2019
 */
public class Empresa {
	//atributos
	private Queue<Pedido> pedidosPendientes = new ArrayDeque<Pedido>();
	private LinkedList<Producto> catalogo = new LinkedList<Producto>();
	private ArrayList<Producto> descatalogados = new ArrayList<Producto>();
	
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
	 * Lee del fichero los datos de los productos.
	 * @param nomFich nombre del fichero.
	 * @throws FileNotFoundException si no se puede abrir el fichero
	 */
	public Empresa(String nomFich) throws FileNotFoundException {
		try (Scanner in = new Scanner(new FileReader(nomFich))) {
			while (in.hasNext()) {
				Producto producto = new Producto(in.next(), in.nextInt());
				//anhade producto al catalogo
				catalogo.add(producto);
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
	public void registraPedido(String codProducto, int numUnidades)
			throws CodigoProductoIncorrecto, CantidadInsuficiente {
		Producto p = buscaProducto(codProducto);
		if (p == null) {
			throw new CodigoProductoIncorrecto();
		}
		if (numUnidades > p.numUnidadesDisponibles()) {
			throw new CantidadInsuficiente();
		}
		Pedido pedido = new Pedido(p, numUnidades);
		pedidosPendientes.add(pedido);
	}

	/**
	 * Procesa el pedido pendiente mas antiguo.
	 * @return pedido que ha sido procesado.
	 * @throws NoHayPedidosPendientes si no hay ningún pedido pendiente
	 * @throws CantidadInsuficiente si no hay suficientes unidades del producto solicitado.
	 */
	public Pedido procesaSiguientePedido() throws NoHayPedidosPendientes, CantidadInsuficiente {
		Pedido p = ((ArrayDeque<Pedido>) pedidosPendientes).peekFirst();
		if (p == null) {
			throw new NoHayPedidosPendientes();
		}
		if (p.numUnidades() > p.producto().numUnidadesDisponibles()) {
			((ArrayDeque<Pedido>) pedidosPendientes).removeFirst();
			throw new CantidadInsuficiente();
		}
		p.producto().reduceUnidadesDisponibles(p.numUnidades());
		((ArrayDeque<Pedido>) pedidosPendientes).removeFirst();
		return p;

	}

	/**
	 * Descataloga los productos con un numero de unidades disponibles inferior
	 * al umbral.
	 * @param umbralUnidades umbral del numero de unidades por debajo del cual
	 * descatalogar los productos.
	 */
	public void descatalogaProductos(int umbralUnidades) {
		Producto p;
		ListIterator<Producto> aux = catalogo.listIterator();
		while (aux.hasNext()) {
			p = aux.next();
			if (p.numUnidadesDisponibles() < umbralUnidades) {
				aux.remove();
				descatalogados.add(p);
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
	public Producto productoDescatalogado(int posicion) 
			throws PosicionIncorrecta {
		Producto p;
		if (posicion < 0 || posicion >= descatalogados.size()) {
			throw new PosicionIncorrecta();
		}
		p = descatalogados.get(posicion);
		return p;
	}
	
	/**
	 * Busca un producto por codigo.
	 * @param codigo del producto
	 * @return el producto o null si no existe
	 */
	public Producto buscaProducto(String codigo) {
		for (int i = 0; i < catalogo.size(); i++) {
			if (catalogo.get(i).codigo().equals(codigo)) {
				return catalogo.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Retorna el numero de productos descatalogados.
	 * @return el numero de productos descatalogados.
	 */
	public int numProductosDescatalogados() {
		return descatalogados.size();
	}
}
