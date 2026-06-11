package pract04.operaciones;

import pract04.ilista.IIteradorSimple;
import pract04.ilista.ILista;
import pract04.lista_array.ListaArray;

/**
 * Clase que implementa dos algoritmos sencillos sobre listas.
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version oct-2020
 */
public class OperacionesConListas {

	/**
	 * Duplica en la lista todas las ocurrencias del elemento indicado
	 * anhadiendo una copia de dicho elemento a continuacion de cada ocurrencia.
	 * @param lista lista en la que duplicar el elemento indicado
	 * @param eleDuplicar elemento a duplicar
	 */
	public static void duplicaOcurrenciasElemento(ILista<String> lista, String eleDuplicar) {
		IIteradorSimple<String> iter = lista.iterador();
		while (iter.haySiguiente()) {
			if (iter.siguiente().equals(eleDuplicar)) {
				iter.anhade(eleDuplicar);
			}
		}
	}
	//complejidad o(n) en ambas listas
	
	/**
	 * Retorna una nueva lista con los elementos de la lista original que ocupan las
	 * posiciones indicadas. La lista original no se modifica.
	 * @param lista lista original de la que copiar los elementos a la nueva lista
	 * @param posiciones posiciones en la lista original de los elementos a copiar
	 * en la nueva lista (las posiciones no estan ordenadas)
	 * @return una nueva lista con los elementos copiados en el orden en el que 
	 * aparecen en el array posiciones.
	 */
	public static ListaArray<String> sublistaPosiciones(ILista<String> lista, int[] posiciones) {
		ILista<String> nuevaLista = new ListaArray<String>(lista.tamanho());
		String aux;
		for (int i = 0; i < posiciones.length; i++) {
			aux = lista.obtenElemento(posiciones[i]);
			nuevaLista.anhade(i, aux);
		}
		return (ListaArray<String>) nuevaLista;
	}
	//complejidad o(n)
}
