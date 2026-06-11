package pract06.modelo;

/**
 * Vendedor de una tienda.
 * 
 * @author  Aaron Alegria
 * @version mar-23
 */
public class Vendedor {
	private final String nombre;
	private double comisionAcumulada;
	private static final double COMISION = 0.05;

	/**
	 * metodo constructor.
	 * @param nombre nombre del vendedor
	 */
	public Vendedor(String nombre) {
		this.nombre = nombre;
		comisionAcumulada = 0;
	}

	/**
	 * metodo observador de la comision.
	 * @return la comision acumulada
	 */
	public double comisionAcumulada() {
		return comisionAcumulada;
	}

	/**
	 * metodo modificador de la comision.
	 * @param importeVenta importe de la venta de la que añadir la comision
	 */
	public void anhadeComision(double importeVenta) {
		comisionAcumulada += importeVenta * COMISION;
	}

	/**
	 * metodo observador del nombre.
	 * @return el nombre del vendedor
	 */
	public String nombre() {
		return nombre;
	}
}
