package mc23;


public class ClienteNormal extends Cliente{
	private static final double DESCUENTO = 0.99;
	private static final int MIN_DESCUENTO = 100;
	private final double maxImporteTotal;
	/**
	 * @param id
	 * @param maxImporteTotal
	 */
	public ClienteNormal(String id, double maxImporteTotal) {
		super(id);
		this.maxImporteTotal = maxImporteTotal;
	}
	public double maxImporteTotal() {
		return maxImporteTotal;
	}
	
	@Override
	public void realizaCompra(Compra c) throws MaxImporteCompra, CompraYaRealizada {
		if (sumaPrecios() + c.precio() > maxImporteTotal) {
			throw new MaxImporteCompra();
		}
		if (buscaCompra(c.nombre()) != null) {
			compras.add(c);
			throw new CompraYaRealizada();
		}
		compras.add(c);

	}
	
	@Override
	public double pagaCompras() {
		double total = sumaPrecios();
		if (total > MIN_DESCUENTO) {
			total = total * DESCUENTO;
		}
		return total;
	}
	@Override
	public double descuento() {
		return 1-DESCUENTO;
	}

}
