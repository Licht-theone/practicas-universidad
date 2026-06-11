package pract09.pract07.modelo;

/**
 * Pedido realizado por el cliente de una empresa.
 * 
 * @author Estructuras de Datos (UC)
 * @version oct-2019
 */
public class Pedido {
	private final Producto producto;
	private final int numUnidades;

	/**
	 * Construye un pedido con los datos indicados.
	 * @param producto producto solicitado.
	 * @param numUnidades numero de unidades solicitadas.
	 */
	public Pedido(Producto producto, int numUnidades) {
		this.producto = producto;
		this.numUnidades = numUnidades;
	}

	/**
	 * Retorna el producto solicitado en el pedido.
	 * @return el producto solicitado en el pedido.
	 */
	public Producto producto() {
		return producto;
	}
	
	/**
	 * Retorna el numero de unidades solicitadas en el pedido.
	 * @return el numero de unidades solicitadas en el pedido.
	 */
	public int numUnidades() {
		return numUnidades;
	}

}
