package pract08.c2_algoritmos_recursivos;

import pract08.c1_arbol_binario_ph.ArbolBinarioPH.Nodo;
import pract08.c1_arbol_binario_ph.IArbolBinario;

/**
 * Algoritmos recursivos sobre arboles binarios.
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version oct-2022
 */
@SuppressWarnings("rawtypes")
public class AlgoritmosRecursivos {

	/**
	 * Retorna el numero de veces que se encuentra el elemento
	 * buscado en el arbol.
	 * @param <E> tipo de los elementos almacenados en el arbol.
	 * @param arbol arbol en el que contar el numero de ocurrencias.
	 * @param buscado elemento buscado.
	 * @return numero de ocurrencias del elemento en el arbol.
	 */
	
	public static <E> int cuentaOcurrenciasElemento(IArbolBinario<E> arbol,
			E buscado) {

		return cuentaOcurrenciasElementoRec((Nodo) arbol.raiz(), buscado);
	}

	/**
	 * Busca el primer nodo del arbol siguiendo un recorrido en inorden
	 * que tiene sus dos hijos iguales.
	 * @param <E> tipo de los elementos almacenados en el arbol.
	 * @param arbol arbol en el que buscar.
	 * @return el contenido del primer nodo del arbol siguiendo un recorrido en inorden
	 * que tiene sus dos hijos iguales o null si no existe ningun nodo con sus dos hijos
	 * iguales.
	 */
	public static <E> E buscaElemConDosHijosIgualesInorden(IArbolBinario<E> arbol)	{
		if (arbol.raiz() == null) {
			return null;
		}
		return buscaElemConDosHijosIgualesInordenRec((Nodo) arbol.raiz());
	}
	
	@SuppressWarnings("unchecked")
	private static <E> E buscaElemConDosHijosIgualesInordenRec(Nodo nodo) {
		if (nodo == null) {
			return null;
		}
		E elem = buscaElemConDosHijosIgualesInordenRec(nodo.hijoIzq());
		if (elem != null) {
			return elem;
		}
		if (nodo.hijoIzq() != null && nodo.hijoDer() != null) {
			if (nodo.hijoIzq().contenido().equals(nodo.hijoDer().contenido())) {
				return (E) nodo.contenido();
			}
		}
		return buscaElemConDosHijosIgualesInordenRec(nodo.hijoDer());
	}
	
	private static <E> int cuentaOcurrenciasElementoRec(Nodo nodo, E elem) {
		if (nodo == null) {
			return 0;
		}
		int c = 0;
		if (nodo.contenido().equals(elem)) {
			c++;
		}
		c += cuentaOcurrenciasElementoRec(nodo.hijoDer(), elem);
		c += cuentaOcurrenciasElementoRec(nodo.hijoIzq(), elem);
		return c;
	}
}
