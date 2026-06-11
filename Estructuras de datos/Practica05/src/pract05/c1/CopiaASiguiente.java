package pract05.c1;

import java.util.List;
import java.util.ListIterator;

/**
 * Clase que contiene el metodo copiaASiguiente.
 * 
 * @author Estructuras de Datos (UC) y Lara Hernández Marcote
 * @version oct-2021
 */
public class CopiaASiguiente {
	
	/**
	 * Copia cada ocurrencia del elemento buscado sobre el elemento
	 * siguiente.
	 * @param <E> tipo de los elementos de la lista.
	 * @param lista lista en la que buscar los elementos.
	 * @param buscado elemento buscado.
	 */
	// XXX cuestion 1
	public static <E> void copiaASiguiente(List<E> lista, E buscado) {
		ListIterator<E> iter = lista.listIterator();
		E ele;
		while (iter.hasNext()) {
			ele  = iter.next();
			if (ele.equals(buscado) && iter.hasNext()) {
				iter.next();
				iter.set(buscado);
			}
		}
	}

}
