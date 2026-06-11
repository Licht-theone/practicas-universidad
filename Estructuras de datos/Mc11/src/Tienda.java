import java.util.List;

/**
 * Clase que representa una tienda que oferta una serie de productos.
 * La clase gestiona el stock de cada producto y las reservas que los clientes
 * pueden realizar cuando el stock actual disponible de un producto no es 
 * suficiente para suplir su demanda. 
 * 
 * @author Estructuras de Datos (UC) y <TODO: nombre alumno>
 * @version nov-2018
 *
 */
public class Tienda {

	// TODO: atributos

	/**
	 * Excepcion utilizada para indicar que un cliente ya esta
	 * registrado en la tienda.
	 */
	@SuppressWarnings("serial")
	public class ClienteYaExiste extends RuntimeException {};

	/**
	 * Excepcion utilizada para indicar que un cliente no esta
	 * registrado en la tienda.
	 */
	@SuppressWarnings("serial")
	public class ClienteNoExiste extends RuntimeException {};

	/**
	 * Excepcion utilizada para indicar que un producto no es ofertado
	 * por la tienda.
	 */
	@SuppressWarnings("serial")
	public class ProductoNoExiste extends RuntimeException {};
	
	/**
	 * Anhade un nuevo producto a la tienda.
	 * @param producto Producto.
	 * @return Identificador del producto.
	 */
	public int anhadeProducto(Producto producto) {
		// TODO
		return 0;
	}

	/**
	 * Registra un nuevo cliente en la tienda.
	 * @param cliente Nuevo cliente.
	 * @throws ClienteYaExiste si ya existe un cliente con ese DNI.
	 */
	public void anhadeCliente(Cliente cliente) 
			throws ClienteYaExiste {
		// TODO
	}

	/**
	 * Actualiza el stock de un producto.
	 * @param idProducto Identificador del producto cuyo stock se actualiza.
	 * @param cantidad Numero de unidades en que se actualiza el stock.
	 * @return Lista de reservas del producto que se han suplido con el
	 * nuevo stock.
	 * @throws ProductoNoExiste Cuando idProducto no corresponde a ningun
	 * producto de la tienda.
	 */
	public List<Reserva> actualizaStock(int idProducto, int cantidad)
			throws ProductoNoExiste {
		// TODO
		return null;
	}

	/**
	 * Realiza una venta de un producto.
	 * @param idProducto Identificador del producto cuyo stock se actualiza.
	 * @param dniCliente DNI del cliente que realiza la compra.
	 * @param cantidad Numero de unidades en que se actualiza el stock.
	 * @return true si se ha realizado la venta (habia stock suficiente)
	 * 	       false si se ha generado una reserva (no habia stock suficiente).
	 * @throws ClienteNoExiste Cuando dniCliente no corresponde a ningun
	 * cliente registrado.
	 * @throws ProductoNoExiste Cuando idProducto no corresponde a ningun
	 * producto de la tienda.
	 */
	public boolean nuevaVenta(int idProducto, String dniCliente, int cantidad) 
			throws ClienteNoExiste, ProductoNoExiste {
		// TODO
		return false;
	}

	/**
	 * Retorna una lista con aquellos productos que tienen reservas pendientes.
	 * @return Lista de productos con reservas.
	 */
	public List<Producto> productosConReserva() {
		// TODO
		return null;
	}
}
