package pract07.modelo;

import java.util.ArrayList;

/**
 * Tienda con sus vendedores.
 * 
 * @author  Metodos de Programacion (UC)
 * @version abr-22
 */
@SuppressWarnings("serial")
public class Tienda extends RuntimeException {
	/**
	 * excepcion para añadir un vendedor con nombre repetido.
	 */
	public static class NombreYaExistente extends Exception {
	}

	/**
	 * excepcion para datos erroneos.
	 */
	public static class NombreVendedorIncorrecto extends Exception {
	}

	// vendedores de la tienda
	private ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();

	/**
	 * Anhade un nuevo vendedor a la tienda.
	 * @param nuevoVendedor vendedor a anhadir
	 * @throws NombreYaExistente cuando ya existe el vendedor
	 */
	public void anhadeVendedor(Vendedor nuevoVendedor) throws NombreYaExistente	{
		if (buscaVendedor(nuevoVendedor.nombre()) != null) {
			throw new NombreYaExistente();
		}
		vendedores.add(nuevoVendedor);
	}

	/**
	 * Anhade una venta a un vendedor.
	 * @param nombre nombre del vendedor
	 * @param importe importe de la venta
	 * @throws NombreVendedorIncorrecto cuando el nombre es incorrecto
	 */
	public void anhadeVenta(String nombre, double importe) throws NombreVendedorIncorrecto {
		Vendedor vendedor = buscaVendedor(nombre);
		if (vendedor == null) {
			throw new NombreVendedorIncorrecto();
		}
		vendedor.anhadeVenta(importe);
	}

	/**
	 * Retorna la posicion en el ranking del vendedor con el ID indicado.
	 * @param nombre nombre del vendedor cuya posicion en el ranking se quiere conocer
	 * @return la posicion en el ranking del vendedor indicado
	 * @throws NombreVendedorIncorrecto cuando los datos son erroneos
	 */
	public int posRankingVendedor(String nombre) throws NombreVendedorIncorrecto {
		Vendedor vendedor = buscaVendedor(nombre);
		if (vendedor == null) {
			throw new NombreVendedorIncorrecto();
		}

		// cuenta cuantos vendedores tienen una comision mayor que el vendedor
		// en cuestion.
		int numVendedoresConMasComision = 0;
		for (Vendedor v: vendedores) {
			if (v.comisionAcumulada() > vendedor.comisionAcumulada()) {
				numVendedoresConMasComision++;
			}
		}

		// la posicion del vendedor en el ranking es el numero de vendedores con
		// mas comision que el mas uno (p.e. si hay 0 vendedores con mas comision
		// que el, eso significa que el vendedor ocupa la posicion 1 del ranking)
		return numVendedoresConMasComision + 1;
	}

	/**
	 * Retorna el vendedor con el nombre indicado.
	 * @param nombre nombre del vendedor
	 * @return vendedor con ese nombre o null en caso de no existir ninguno
	 */
	public Vendedor buscaVendedor(String nombre) {
		for (Vendedor v: vendedores) {
			if (v.nombre().equals(nombre)) {
				return v;
			}
		}
		return null;
	}

	/**
	 * Elimina el vendedor con el nombre indicado.
	 * @param nombre nombre del vendedor a eliminar.
	 * @return el vendedor eliminado o null si no existe ningun vendedor
	 * con el nombre indicado.
	 * @throws NombreVendedorIncorrecto cuando el nombre es incorrecto;
	 */
	public Vendedor eliminaVendedor(String nombre) throws NombreVendedorIncorrecto {
		Vendedor vendedor = buscaVendedor(nombre);
		if (vendedor == null) {
			throw new NombreVendedorIncorrecto();
		}
		vendedores.remove(vendedor);
		return vendedor;
	}

}
