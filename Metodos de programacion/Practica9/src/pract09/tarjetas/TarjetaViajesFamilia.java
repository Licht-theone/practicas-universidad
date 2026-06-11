package pract09.tarjetas;

/**
 * Subclase de la tarjeta de viajes.
 * 
 * @author Aaron Alegria
 * @version may-2021
 */
public class TarjetaViajesFamilia extends TarjetaViajes {
	
	/**
	 * constructor.
	 * @param dni dni del dueño
	 * @param direccion direccion del dueño
	 */
	public TarjetaViajesFamilia(String dni, String direccion) {
		super(dni, direccion);
	}
	
	/**
	 * realiza una compra con la tarjeta.
	 * @param precioCompra precio de la compra
	 * @throws SaldoInsuficiente excepcion si no tiene saldo suficiente
	 */
	public void realizaCompra(double precioCompra) throws SaldoInsuficiente {
		if (precioCompra > saldo()) {
			throw new SaldoInsuficiente();
		}
		
		anhadeSaldo(-precioCompra);
		anhadePuntosFamiliar(precioCompra);
	}
}
