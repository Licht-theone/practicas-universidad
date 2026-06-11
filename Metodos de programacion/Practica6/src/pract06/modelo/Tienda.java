package pract06.modelo;

import java.util.ArrayList;

/**
 * Tienda con vendedores.
 * 
 * @author  Aaron Alegria
 * @version mar-23
 */
public class Tienda {
	private ArrayList<Vendedor> vendedores = new ArrayList<>();
	
	/**
	 * metodo que añande un vendedor al array.
	 * @param v el vendedor a añadir
	 * @return false si ya existe true en caso contrario
	 */
	public boolean anhadeVendedor(Vendedor v) {
		if (buscaVendedor(v.nombre()) != null) {
			return false;
		}
		vendedores.add(v);
		return true;
	}
	
	/**
	 * metodo que añade la comision de una venta a un vendedor.
	 * @param nombreVendedor nombre del vendedor al que asignar la venta
	 * @param importe importe de la venta
	 * @return false si el vendedor no existe true si existe y se añade la venta
	 */
	public boolean nuevaVenta(String nombreVendedor, double importe) {
		Vendedor v = buscaVendedor(nombreVendedor);
		if (v == null) {
			return false;
		}
		v.anhadeComision(importe);
		return true;
	}

	/**
	 * metodo que elimina a un vendedor de la lista.
	 * @param nombre nombre del vendedor a eliminar
	 * @return false si el vendedor no existe true en caso contrario y se elimina
	 */
	public boolean eliminaVendedor(String nombre) {
		Vendedor v = buscaVendedor(nombre);
		if (v == null) {
			return false;
		}
		vendedores.remove(v);
		return true;
	}

	/**
	 * metodo que retorna la posicion en el ranking de vendedores.
	 * @param nombre nombre del vendedor del que sacar la posicion
	 * @return la posicion del vendedor en el ranking o -1 en caso de error
	 */
	public int rankingVendedor(String nombre) {
		Vendedor v = buscaVendedor(nombre);
		int rank = 1;
		if (v == null) {
			return -1;
		}
		for (int i = 0; i < vendedores.size(); i++) {
			if (vendedores.get(i).comisionAcumulada() > v.comisionAcumulada()) {
				rank++;
			}
		}
		return rank;
	}

	/**
	 * metodo que busca un vendedor en el arraylist.
	 * @param nombre el nombre del vendedor a buscar
	 * @return el vendedor encontrado o null en caso de que no exista
	 */
	public Vendedor buscaVendedor(String nombre) {
		for (Vendedor v: vendedores) {
			if (v.nombre().equals(nombre)) {
				return v;
			}
		}
		return null;
	}
}
