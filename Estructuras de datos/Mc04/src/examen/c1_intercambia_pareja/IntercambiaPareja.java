package examen.c1_intercambia_pareja;

import java.util.List;
import java.util.ListIterator;

/**
 * Clase que contiene el metodo intercambiaPareja().
 * 
 * @author Estructuras de Datos (UC) 
 * @version oct-2021
 */
public class IntercambiaPareja {

	/**
	 * Intercambia las posiciones de los elementos de cada ocurrencia
	 * de la pareja formada por los elementos primero y segundo.
	 * @param <E> tipo de los elementos de la lista.
	 * @param lista lista en la intercambiar las parejas.
	 * @param primero primer elemento de la pareja.
	 * @param segundo segundo elemento de la pareja.
	 */
	public static <E> void intercambiaPareja(List<E> lista, E primero, E segundo) {
		ListIterator<E> iter = lista.listIterator();
		E aux, temp;
		while (iter.hasNext()) {
			aux = iter.next();
			if (aux.equals(primero) && iter.hasNext()) {
				temp = iter.next();
				if (temp.equals(segundo)) {
					iter.set(primero);
					iter.previous();
					iter.previous();
					iter.set(segundo);
					iter.next();
					iter.next();
				}
			}
		}
	}
}
