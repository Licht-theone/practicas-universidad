package pract14.c2_lista_con_elementos_comunes;

import java.util.ArrayList;

/**
 * Implementa el algoritmo que permite dejar en una lista los
 * productos que tambien se encuentran en la otra.
 * Se trata de una implementacion ineficiente con tiempo de ejecucion cuadratico.
 *  
 * @author Estructuras de Datos (UC)
 * @version ene-2021
 */
public class ProductosComunesIneficiente {

	/**
	 * Deja en la lista1 unicamente aquellos productos que tambien estan
	 * en la lista2.
	 * Los elementos en la lista resultante deben mantener el orden relativo
	 * y el numero de ocurrencias que tenian en la lisa original.
	 * @param lista1 lista en la que se dejan unicamente aquellos productos
	 * que tambien estan en la lista2.
	 * @param lista2 lista en la que esta los productos a conservar.
	 */
	//O(n^2)
	public static void dejaElementosComunes(ArrayList<Producto> lista1,
			ArrayList<Producto> lista2) {
		int i = 0;
		while (i < lista1.size()) {
			if (!estaEnLista(lista2, lista1.get(i))) {
				lista1.remove(i);
			} else {
				i++;
			}
		}
	}
	
	/**
	 * Indica si un producto esta en la lista.
	 * @param lista lista en la que buscar el producto.
	 * @param producto producto buscado
	 * @return verdadero si el producto esta en la lista.
	 */
	private static boolean estaEnLista(ArrayList<Producto> lista, Producto producto) {
		for (Producto p: lista) {
			if (p.codigo() == producto.codigo()) {
				return true;
			}
		}
		return false;
	}
}
