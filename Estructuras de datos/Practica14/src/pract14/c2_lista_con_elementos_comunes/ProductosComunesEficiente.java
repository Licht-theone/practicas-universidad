package pract14.c2_lista_con_elementos_comunes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementa el algoritmo que permite dejar en una lista los
 * productos que tambien se encuentran en la otra.
 *  
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version dic-2021
 */
public class ProductosComunesEficiente {

	/**
	 * Deja en la lista1 unicamente aquellos productos que tambien estan
	 * en la lista2.
	 * Los elementos en la lista resultante deben mantener el orden relativo
	 * y el numero de ocurrencias que tenian en la lisa original.
	 * @param lista1 lista en la que se dejan unicamente aquellos productos
	 * que tambien estan en la lista2.
	 * @param lista2 lista en la que esta los productos a conservar.
	 */
	//O(n)
	//DA ERROR DE QUE NO COINCIDEN POR EL ORDEN DE LAS LISTAS
	public static void dejaElementosComunes(ArrayList<Producto> lista1,
			ArrayList<Producto> lista2) {
		Set<Producto> listaAux = new HashSet<Producto>();
		Set<Producto> listaAux2 = new HashSet<Producto>();
		listaAux2.addAll(lista2);
		listaAux.addAll(lista1);
		listaAux.retainAll(listaAux2);
		lista1.clear();
		lista1.addAll(listaAux);
	}
	
	/**
	 * Indica si un producto esta en la lista.
	 * @param lista lista en la que buscar el producto.
	 * @param producto producto buscado
	 * @return verdadero si el producto esta en la lista.
	 */
	// no va a ser necesario en la solucion eficiente
	@SuppressWarnings("unused")
	private static boolean estaEnLista(ArrayList<Producto> lista, Producto producto) {
		for (Producto p: lista) {
			if (p.codigo() == producto.codigo()) {
				return true;
			}
		}
		return false;
	}
}
