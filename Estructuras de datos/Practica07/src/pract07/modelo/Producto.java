package pract07.modelo;

/**
 * Producto vendido por una empresa.
 * 
 * @author Estructuras de Datos (UC)
 * @version oct-2019
 */
public class Producto {
	private final String codigo;
	private int numUnidadesDisponibles;

	/**
	 * Construye un producto con los datos indicados.
	 * @param codigo codigo del producto.
	 * @param numUnidades numero de unidades disponibles del producto.
	 */
	public Producto(String codigo, int numUnidades) {
		this.codigo = codigo;
		this.numUnidadesDisponibles = numUnidades;
	}

	/**
	 * Retorna el codigo del producto.
	 * @return el codigo del producto.
	 */
	public String codigo() {
		return codigo;
	}
	
	/**
	 * Retorna el numero de unidades disponibles del producto.
	 * @return el numero de unidades disponibles del producto.
	 */
	public int numUnidadesDisponibles() {
		return numUnidadesDisponibles;
	}
	
	/**
	 * Reduce el numero de unidades disponibles del producto en la cantidad indicada.
	 * @param cantidad cantidad en la que reducir el numero de unidades disponibles.
	 */
	public void reduceUnidadesDisponibles(int cantidad) {
		numUnidadesDisponibles -= cantidad;
	}

}
