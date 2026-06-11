package mc23;

public class ClientePremium extends Cliente{
	private final String nombre;
	private static final double DESCUENTO = 0.98;

	/**
	 * @param id
	 * @param nombre
	 */
	public ClientePremium(String id, String nombre) {
		super(id);
		this.nombre = nombre;
	}


	public String nombre() {
		return nombre;
	}


	@Override
	public void realizaCompra(Compra c) throws MaxImporteCompra, CompraYaRealizada {
		if (buscaCompra(c.nombre()) != null) {
			compras.add(c);
			throw new CompraYaRealizada();
		}
		compras.add(c);
	}

	@Override
	public double pagaCompras() {
		double total = sumaPrecios();
		total = total * DESCUENTO;
		return total;
	}


	@Override
	public double descuento() {
		return 1-DESCUENTO;
	}


}
