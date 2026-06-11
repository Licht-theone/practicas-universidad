package pract09.tarjetas;

/**
 * Subclase de la clase tarjeta, tarjeta basica.
 * 
 * @author Aaron Alegria
 * @version may-2021
 */
public class TarjetaBasica extends Tarjeta {
	private static final double DESCUENTO = 0.95;
	
	/**
	 * constructor.
	 * @param dni dni del dueño
	 */
	public TarjetaBasica(String dni) {
		super(dni);
	}
	
	/**
	 * Realiza una compra con la tarjeta descontando su importe del saldo.
	 * 
	 * @param precioCompra precio correspondiente a la compra realizada.
	 * @throws SaldoInsuficiente si el saldo es insuficiente
	 */
	public void realizaCompra(double precioCompra) throws SaldoInsuficiente {
		if ((precioCompra) * DESCUENTO > saldo()) {
			throw new SaldoInsuficiente();
		}
		anhadeSaldo(-(precioCompra * DESCUENTO));
	}
}
