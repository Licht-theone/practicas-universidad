package examen.c2_extrae_sublista;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;



/**
 * Clase de prueba.
 * @author Pablo
 * @version 21/10
 *
 */
public class ClasePrueba {
	
	/**
	 * Clase de prueba.
	 * @param args argumentos
	 */
	public static void main(String[] args) {
		List<String> lista = new LinkedList<String>();
		lista.add("A");
		lista.add("B");
		lista.add("C");
		lista.add("A");
		lista.add("B");
		lista.add("D");
		lista.add("A");
		System.out.println("Lista:" + lista);
		intercambiaPareja(lista, "A", "B");
		System.out.println("Lista cambio:" + lista);
	}

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
