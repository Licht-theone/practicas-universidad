package mc23;

import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class Cliente {
	public class MaxImporteCompra extends RuntimeException{}
	public class CompraYaRealizada extends RuntimeException{}
	private final String id;
	protected ArrayList<Compra> compras;
	/**
	 * @param id
	 */
	public Cliente(String id) {
		super();
		this.id = id;
		compras = new ArrayList<Compra>();
	}

	public String getId() {
		return id;
	}

	public abstract void realizaCompra(Compra c) throws MaxImporteCompra, CompraYaRealizada;

	public abstract double pagaCompras();

	public Compra buscaCompra(String nombre) {
		for (Compra c: compras) {
			if (c.nombre().equals(nombre)) {
				return c;
			}
		}
		return null;
	}
	protected double sumaPrecios() {
		double total = 0;
		for (Compra c: compras) {
			total += c.precio();
		}
		return total;
	}

	public int numCompras() {
		return compras.size();
	}

	public abstract double descuento();
}
