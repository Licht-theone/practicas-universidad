package pract02.parte2;

import pract02.lista_array_noiter.IListaNoIter;
import pract02.lista_array_noiter.ListaArrayNoIter;

/**
 * Distintas versiones de la operacion eliminaOcurrenciasElemento para el estudio de sus
 * tiempos de ejecucion.
 * Utiliza el la implementación ListaArraySimple del TDA "Lista simple".
 * 
 * @author Estructuras de Datos y Aaron Alegria
 * @version sep-2024
 */
public class EliminaOcurrencias {

	/**
	 * Elimina de la lista todas las ocurrencias del elemento a eliminar.
	 * @param lista lista en la que eliminar los elementos.
	 * @param eleEliminar elemento a eliminar.
	 */
	public static void eliminaOcurrenciasLento(
			IListaNoIter<Integer> lista, Integer eleEliminar) {
		for (int i = 0; i < lista.tamanho()/*o(1)*/; i++) { //o(n)
			if (/*o(1)*/lista.obtenElemento(i).equals(eleEliminar)/*o(1)*/) {
				lista.elimina(i)/*o(n)*/;
				i--;
			}
		}
		//Respuesta a la cuestion 2.a: este metodo tarda o(n*n*1*1*1)= o(n^2)
	}

	/**
	 * Version mas rapida del metodo eliminaOcurrenciasLento() (Parte 2.b).
	 * Elimina de la lista todas las ocurrencias del elemento a eliminar.
	 * @param lista lista en la que eliminar los elementos.
	 * @param eleEliminar elemento a eliminar.
	 */
	public static void eliminaOcurrenciasRapido(
			IListaNoIter<Integer> lista, Integer eleEliminar) {
		for (int i = lista.tamanho() - 1/*o(1)*/; i >= 0; i--) { //o(n)
			if (/*o(1)*/lista.obtenElemento(i).equals(eleEliminar)/*o(1)*/) {
				lista.elimina(i); //o(n)
			}
		}
		//Respuesta a la cuestion 2.b: tiempo total o(n*1*1*1*n)=o(n^2)
	}


	/**
	 * Version mas eficiente del metodo eliminaOcurrenciasLento() (Parte 2.c).
	 * Elimina de la lista todas las ocurrencias del elemento a eliminar.
	 * @param lista lista en la que eliminar los elementos.
	 * @param eleEliminar elemento a eliminar.
	 */
	public static void eliminaOcurrenciasEficiente(
			IListaNoIter<Integer> lista, Integer eleEliminar) {
		IListaNoIter<Integer> auxiliar = new ListaArrayNoIter<Integer>(lista.tamanho()); //o(1)

		for (int i = 0; i < lista.tamanho(); i++) { //o(n)
			if (!(lista.obtenElemento(i).equals(eleEliminar))) { //o(1)
				auxiliar.anhade(auxiliar.tamanho(), lista.obtenElemento(i)); //o(1)
			}
		}
		lista.haceVacia(); //o(n)

		for (int i = 0; i < auxiliar.tamanho(); i++) { //o(n)
			lista.anhade(lista.tamanho(), auxiliar.obtenElemento(i));
		}

		//Respuesta a la cuestion 2.c: tiempo total o(n*1*1) + o(n) + o(n)=o(n)
	}

}
