import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase que representa un producto ofertado por la tienda
 * Todo producto se identifica por un codigo numerico que se asigna
 * automaticamente en su creacion.
 * Un producto puede tener una serie de reservas, de manera, que cuando
 * se actualice el stock del producto, sea utilizado para suplir dichas 
 * reservas (en orden de antiguedad).
 * 
 * @author Estructuras de Datos (UC) y <TODO: nombre alumno>
 * @version nov-2018
 *
 */
public class Producto {

	private final int id;
	private final String descripcion;
	private int unidadesStock = 0;
	private Queue<Reserva> reservas = new LinkedList<Reserva>();

	// para generar un codigo diferente para cada producto
	private static int contadorProductos = 0;
	
	// : otros atributos

	/**
	 * Constructor del producto. Asigna un identificador unico
	 * a cada producto.
	 * @param descripcion descripcion del producto
	 * @param stockInicial numero de unidades disponibles del producto
	 */
	public Producto(String descripcion, int stockInicial) {
		this.unidadesStock = stockInicial;
		this.descripcion = descripcion;
		id = generaCodigoProducto();
	}

	/**
	 * Simula la generacion de un codigo diferente para cada producto.
	 * @return codigo generado.
	 */
	private int generaCodigoProducto() {
		contadorProductos++;
		return contadorProductos * 100 + (int)(Math.random() * 50);
	}

	/**
	 * Retorna el identificador del producto.
	 * @return identificador del producto.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Retorna la descripcion del producto.
	 * @return descripcion del producto.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Retorna el numero de unidades disponibles del producto.
	 * @return numero de unidades disponibles del producto.
	 */
	public int getUnidadesStock() {
		return unidadesStock;
	}
	
	/**
	 * Retorna el total de unidades reservadas.
	 * @return numero total de unidades del producto reservadas.
	 */
	public int unidadesReservadas() {
		// TODO
		return 0;
	}
	
	// TODO: otros metodos

}
