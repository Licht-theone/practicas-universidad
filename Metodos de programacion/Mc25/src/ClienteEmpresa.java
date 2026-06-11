
public class ClienteEmpresa extends Cliente{
	private final double descuento;

	/**
	 * @param dni
	 * @param descuento en %
	 */
	public ClienteEmpresa(String dni, double descuento) {
		super(dni);
		
		this.descuento = 1 - (descuento / 100);
	}

	public double getDescuento() {
		return descuento;
	}
	
}
