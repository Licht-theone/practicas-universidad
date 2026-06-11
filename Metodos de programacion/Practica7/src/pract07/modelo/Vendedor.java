package pract07.modelo;


/**
 * Vendedor de la tienda, con su nombre y comisiones.
 * 
 * @author Metodos de Programacion (UC)
 * @version abr-22
 */
public class Vendedor {
	
	private final String nombre;
	private double comisionAcumulada = 0;
	private static final double TANTO_POR_UNO_COMISION = 0.05;
	
	/**
	 * Constructor un vendedor con el nombre indicados al que se le asigna
	 * de forma automatica un identificador unico.
	 * El vendedor se crea con 0 euros en comisiones acumuladas.
	 * @param nombre numbre del vendedor a crear
	 */
	public Vendedor(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Retorna el nombre del vendedor.
	 * @return nombre del vendedor
	 */
	public String nombre() {
		return nombre;
	}

	/**
	 * Retorna la comision acumulada por el vendedor.
	 * @return comision acumulada por el vendedor
	 */
	public double comisionAcumulada() {
		return comisionAcumulada;
	}

	/**
	 * Actualiza la comision por ventas del vendedor.
	 * @param importeVenta importe de la venta
	 */
	public void anhadeVenta(double importeVenta) {
		comisionAcumulada = comisionAcumulada + importeVenta * TANTO_POR_UNO_COMISION;
	}
	
}
