package pract09.tarjetas;

/**
 * Superclase de la jerarquia de tarjetas de compras.
 * 
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version may-2021
 */
public abstract class Tarjeta {
	protected final String dni;
	private double saldo;
	
	/**
	 * Lanzada si no hay saldo suficiente para realizar una compra.
	 */
	@SuppressWarnings("serial")
	public static class SaldoInsuficiente extends RuntimeException {
	}

	/**
	 * constructor.
	 * @param dni dni del dueño
	 */
	public Tarjeta(String dni) {
		this.dni = dni;
		saldo = 0;
	}

	/**
	 * Realiza una compra con la tarjeta descontando su importe del saldo.
	 * 
	 * @param precioCompra precio correspondiente a la compra realizada.
	 * @throws SaldoInsuficiente si el saldo es insuficiente
	 */
	public void realizaCompra(double precioCompra) throws SaldoInsuficiente {
		if (precioCompra > saldo) {
			throw new SaldoInsuficiente();
		}
		saldo -= precioCompra;
	}
	
	/**
	 * Incrementa el saldo de la tarjeta.
	 * @param incrementoSaldo cantidad en la que incrementar el saldo.
	 */
	public void anhadeSaldo(double incrementoSaldo) {
		saldo += incrementoSaldo;
	}

	/**
	 * Retorna el saldo de la tarjeta.
	 * @return el saldo de la tarjeta.
	 */
	public double saldo() {
		return saldo;
	}
	
	/**
	 * Retorna el DNI del propietario de la tarjeta.
	 * @return DNI del propietario de la tarjeta
	 */
	public String dni() {
		return dni;
	}

}
