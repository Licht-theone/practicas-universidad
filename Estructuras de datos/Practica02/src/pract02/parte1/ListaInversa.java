package pract02.parte1;

import pract02.lista_array_noiter.IListaNoIter;
import pract02.lista_array_noiter.ListaArrayNoIter;

/**
 * Distintas versiones de la operacion listaInversa para el estudio de sus
 * tiempos de ejecucion.
 * Utiliza el la implementación ListaArrayNoIter del TDA "Lista sin iterador".
 * 
 * @author Estructuras de Datos y Aaron Alegria
 * @version sep-2024
 */
public class ListaInversa {

	/**
	 * Retorna una lista con los mismos elementos de la lista original pero en orden inverso.
	 * @param lista lista original.
	 * @return lista lista inversa de la lista original.
	 */
	public static IListaNoIter<Integer> listaInversaIneficiente(IListaNoIter<Integer> lista) {
		IListaNoIter<Integer> inversa = new ListaArrayNoIter<Integer>(lista.tamanho()); //o(1)

		for (int i = 0; i < lista.tamanho(); i++) { //o(n)
			inversa.anhade(0, lista.obtenElemento(i)); //o(1) en obten elemento y anhade o(n)
		}

		return inversa;
		/* Respuesta a la cuestion 1.a
		 * en la creacion de la inversa lista.tamanho es o(1) y la creacion tambien es o(1)
		 * en el for lista.tamanho es o(1) y se ejecuta o(n) veces
		 * obten elemento es o(1) y anhade es o(n)
		 * entonces al estar uno dentro de otro en el for el tiempo es o(n^2) (regla de producto)
		 */
	}

	/**
	 * Version mas eficiente del metodo listaInversa() (Parte 1.b).
	 * Retorna una lista con los mismos elementos de la lista original pero en orden inverso.
	 * @param lista lista original.
	 * @return lista lista inversa de la lista original.
	 */
	public static IListaNoIter<Integer> listaInversaEficiente(IListaNoIter<Integer> lista) {
		IListaNoIter<Integer> inversa = new ListaArrayNoIter<Integer>(lista.tamanho()); //o(1)

		for (int i = (lista.tamanho() - 1)/*o(1)*/; i >= 0; i--) { //o(n)
			inversa.anhade(inversa.tamanho(), lista.obtenElemento(i)/*o(1)*/);
		}

		return inversa;
	}
	//Respuesta a la cuestion 1.b tiempo total es o(n*1)=o(n)

}
