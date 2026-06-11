package examen.c1;

import java.util.List;
import java.util.ListIterator;

/**
 * Clase que contiene el metodo mueveElementos(). 
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version oct-2024
 */
public class Cuestion1 {
	
	/**
	 * Para cada ocurrencia de 'eleBuscado' en la lista, copia su elemento siguiente a
	 * la posicion anterior a la ocurrencia (de forma que la ocurrencia queda entre
	 * dos elementos iguales).
	 * @param <E> tipo de los elementos de la lista.
	 * @param lst lista en la que buscar los elementos.
	 * @param eleBuscado elemento buscado.
	 */
	public static <E> void poneEntreIguales(List<E> lst, E eleBuscado) {
		ListIterator<E> iter = lst.listIterator();
		
		while (iter.hasNext()) { //Complejidad temporal: O(n)
			E elem = iter.next();
			if (elem.equals(eleBuscado) && iter.hasNext()) {
				E eleInsertar = iter.next();
				iter.previous();
				iter.previous();
				iter.add(eleInsertar);
				iter.next();
			}
		}
	}
	/*
	 * C.1 en una lista doblemente enlazada su complejidad es O(n) ya que al 
	 * añadir se hace sin bucle entre las celdas por lo que no se convierte en n^2.
	 * C.2 en una lista simplemente enlazada tambien es O(n) 
	 * ya que se añade sin bucle entre las celdas.
	 * C.3 En un array en el peor caso es O(n^2) ya que hay que desplazar todos los elementos hacia
	 * la izquierda al añadir.
	 */

}
