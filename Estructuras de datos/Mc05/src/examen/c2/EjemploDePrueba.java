package examen.c2;

import java.util.List;
import java.util.ListIterator;

/**
 * Ejemplos mostrados por pantalla para ver su correcto funcionamiento.
 * @author javier sierra
 *@version octubre-2023
 * @param <E>
 */

public class EjemploDePrueba<E> {


	/**
	 * es el main de la clase.
	 * @param args argumentos
	 */

	public static void main(String[] args) {

		ListaDobleEnlace<String> lista = new ListaDobleEnlace<String>();
		//anhado elementos a la lista de String
		lista.anhade(0, "A");
		lista.anhade(1, "B");
		lista.anhade(2, "C");
		lista.anhade(3, "D");
		lista.anhade(4, "E");

		ListaDobleEnlace<String> lista2 = new ListaDobleEnlace<String>();
		//anhado elementos a la lista de String
		lista2.anhade(0, "a");
		lista2.anhade(1, "x");
		lista2.anhade(2, "b");
		lista2.anhade(3, "c");
		lista2.anhade(4, "x");
		lista2.anhade(5, "d");
		lista2.anhade(6, "x");

		System.out.println("Lista Inicial antes del método: " + lista.toString());
		System.out.println("Otra lista antes del método: " + lista2.toString());
		ListaDobleEnlace.eliminaRepetidos(lista2);
		System.out.println("Lista Inicial después del método: " + lista.toString());
		System.out.println("Otra lista después del método" + lista2.toString());
		
	}
	public void separaSeguidosIguales(E sep, List<E> lista) {
		ListIterator<E> iter = lista.listIterator();
		while (iter.hasNext()) {
			E aux = iter.next();
			if (iter.hasNext()) {
				E temp = iter.next();
				if (aux.equals(temp)) {
					iter.previous(); //si es despues se quita si es antes hacer 2 previous y 2 next
					iter.add(sep);
				} else {
					iter.previous();
				}
			}
		}
	}
}
