package examen.c1;

import java.util.LinkedList;
import java.util.List;

import java.util.ListIterator;

import examen.c2.ListaDobleEnlace;


/**
 * Clase que contiene el metodo mueveElementos(). 
 * 
 * @author Estructuras de Datos (UC)
 * @version oct-2023
 * @param <E>
 */
public class Cuestion1<E> {

	/**
	 * Mueve los elementos de 'lstOrig' a partir de la primera ocurrencia de 'eleCorte'
	 * a continuacion de la primera ocurrencia de 'eleCorte' en 'lstDest'.
	 * Si 'eleCorte' no está en alguna de las listas, las listas no se modifican.
	 * @param <E> tipo de los elementos de las listas
	 * @param lstOrig lista origen desde la que se mueven los elementos.
	 * @param lstDest lista destino a la que se mueven los elementos.
	 * @param eleCorte elemento que marca las posiciones de los elementos a mover.
	 */
	public static <E> void mueveElementos(List<E> lstOrig, List<E> lstDest, E eleCorte) {
		ListIterator<E> iter1 = lstOrig.listIterator();
		ListIterator<E> iter2 = lstDest.listIterator();
		boolean enc1 = false;
		while (iter1.hasNext()) {
			E aux = iter1.next();
			if (aux.equals(eleCorte)) {
				enc1 = true;
			}
		}

		while (enc1 && iter2.hasNext()) {
			iter2.add(iter1.next());
		}
	}

	public static <E> boolean esCapicua(List<E> lista) {
		ListIterator<E> iterDr = lista.listIterator();
		while (iterDr.hasNext()) {
			iterDr.next();
		}
		ListIterator<E> iterIz = lista.listIterator();
		for (int i = 0; i < lista.size() / 2; i++) {
			E iz = iterIz.next();
			E dr = iterDr.previous();
			if (!iz.equals(dr)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Mueve al final de la lista actual cada uno los elementos de la 'otraLista'
	 * que estan justo despues de un elemento igual a 'eleAntes'. 
	 * @param eleAntes elemento anterior a los elementos a mover.
	 * @param otraLista lista desde la que se mueven los elementos.
	 */
	public void insertaLista(List<E> lista, List<E> otra, E elem) {
		ListIterator<E> iter1 = lista.listIterator();
		ListIterator<E> iter2 = otra.listIterator();
		boolean enc = false;
		while (iter1.hasNext() && !enc) {
			E aux = iter1.next();
			if (aux.equals(elem)) {
				enc = true;
			}
		}

		while (enc && iter2.hasNext()) {
			iter2.add(iter1.next());
		}
	}

	public List<E> extraeLista(List<E> lista, List<E> otra, E ini, E fin) {
		List<E> listEx = new LinkedList<E>();
		ListIterator<E> iter = lista.listIterator();
		boolean enc1 = false;
		while (!enc1 && iter.hasNext()) {
			E elem = iter.next();
			if (elem.equals(ini)) {
				listEx.add(elem);
				enc1 = true;
			}
		}
		if (!enc1) {
			return null;
		} else {
			while (iter.hasNext()) {
				E elem = iter.next();
				listEx.add(elem);
				if (elem.equals(fin)) {
					return listEx;
				}
			}
		}
		return null;
	}
}
