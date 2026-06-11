package mc14;

import java.util.ArrayList;

/**
 * Tarjeta de descuento.
 * 
 * @author  Metodos de Programacion (UC) y Aaron Alegria
 * @version oct-22
 */
public class Tarjeta {
	private final String dni;
	private final String codigo;
	private int puntosAcumulados;
	private ArrayList<Compra> historico;
	private static int ultimoCodigo = 0;

	/**
	 * Construye una tarjeta.
	 * @param dni DNI de la tarjeta a crear.
	 */
	public Tarjeta(String dni) {
		this.dni = dni;
		ultimoCodigo++;
		codigo = "TAR" + ultimoCodigo;
		historico = new ArrayList<Compra>();
		puntosAcumulados = 0;
	}

	/**
	 * Retorna el DNI del propietario de la tarjeta.
	 * @return el DNI del propietario de la tarjeta.
	 */
	public String dni() {
		return dni;
	}

	/**
	 * Retorna el codigo de la tarjeta.
	 * @return el codigo de la tarjeta.
	 */
	public String codigo() {
		return codigo;
	}

	/**
	 * Retorna los puntos de descuento acumulados.
	 * @return los puntos de descuento acumulados.
	 */
	public int puntosAcumulados() {
		return puntosAcumulados;
	}

	/**
	 * Registra una compra en la tarjeta acumulando los puntos que
	 * correspondan en funcion de su importe.
	 * @param compra compra a registrar.
	 */
	public void registraCompra(Compra compra) {
		historico.add(compra);
		puntosAcumulados += compra.calculaPuntos();
	}

	/**
	 * Retorna la compra que ocupa en el historico la posicion indicada.
	 * @param posCompra posicion en el historico de la compra buscada.
	 * @return la compra buscada o null si la posicion no corresponde a ninguna
	 * compra
	 */
	public Compra compraEnPos(int posCompra) {
		if (posCompra < 0 || posCompra >= historico.size()) {
			return null;
		}
		Compra c = historico.get(posCompra);
		if (c != null) {
			return c;
		}
		return null;
	}
	
	public Compra compraMayorImporte() {
		double max = -1;
		double nuevoMax = 0;
		Compra c = null;
		for (Compra b: historico) {
			nuevoMax = b.importe();
			if (nuevoMax > max) {
				c = b;
			}
		}
		return c;
	}
}
