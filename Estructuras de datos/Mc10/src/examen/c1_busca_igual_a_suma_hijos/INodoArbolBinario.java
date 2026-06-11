package examen.c1_busca_igual_a_suma_hijos;

/**
 * Nodo de un arbol binario (forma parte del TDA Arbol binario).
 * No incluye las operaciones de ramas.
 * 
 * @param <E> tipo de los elementos almacenados en el arbol.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2020
 */
public interface INodoArbolBinario<E> {

	/**
	 * Retorna el contenido del nodo.
	 * @return el contenido del nodo.
	 */
	public E contenido();

	/**
	 * Cambia el contenido del nodo.
	 * @param nuevoContenido nuevo contenido del nodo.
	 */
	public void cambiaContenido(E nuevoContenido);

	/**
	 * Retorna el padre del nodo.
	 * @return el padre del nodo o null si el nodo actual es la raiz.
	 */
	public INodoArbolBinario<E> padre();
	
	/**
	 * Retorna el hijo izquierdo del nodo.
	 * @return el hijo izquierdo del nodo o null si el nodo actual
	 * no tiene hijo izquierdo.
	 */
	public INodoArbolBinario<E> hijoIzq();
	
	/**
	 * Retorna el hijo derecho del nodo.
	 * @return el hijo derecho del nodo o null si el nodo actual
	 * no tiene hijo derecho.
	 */
	public INodoArbolBinario<E> hijoDer();

	/**
	 * Anhade el hijo izquierdo al nodo.
	 * @param contenido contenido del hijo izquierdo.
	 * @throws UnsupportedOperationException si el nodo ya tiene
	 * hijo izquierdo.
	 */
	public void anhadeHijoIzq(E contenido)
			throws UnsupportedOperationException;
	
	/**
	 * Anhade el hijo derecho al nodo.
	 * @param contenido contenido del hijo derecho.
	 * @throws UnsupportedOperationException si el nodo ya tiene
	 * hijo derecho.
	 */
	public void anhadeHijoDer(E contenido)
			throws UnsupportedOperationException;
	
	
	// XXX No se incluyen las operaciones de ramas para limitar la complejidad
	//     de la practica
	/**
	 * Anhade la raiz de la rama como hijo izquierdo del nodo actual.
	 * Despues de esta operacion el arbol "rama" queda vacio.
	 * @param rama rama que anhadir como hijo izquierdo.
	 * @throws UnsupportedOperationException si el nodo ya tiene
	 * hijo izquierdo.
	 */
	// public void anhadeRamaIzq(IArbolBinario<E> rama)
	// 		throws UnsupportedOperationException;
	
	/**
	 * Anhade la raiz de la rama como hijo derecho del nodo actual.
	 * Despues de esta operacion el arbol "rama" queda vacio.
	 * @param rama rama que anhadir como hijo derecho.
	 * @throws UnsupportedOperationException si el nodo ya tiene
	 * hijo derecho.
	 */
	// public void anhadeRamaDer(IArbolBinario<E> rama)
	// 		throws UnsupportedOperationException;

	/**
	 * Elimina la rama del arbol cuya raiz es el nodo izquierdo del
	 * nodo actual.
	 * @return arbol cuya raiz es el nodo que era el hijo izquierdo del
	 * nodo actual.
	 */
	// public IArbolBinario<E> cortaRamaIzq();
	
	/**
	 * Elimina la rama del arbol cuya raiz es el nodo derecho del
	 * nodo actual.
	 * @return arbol cuya raiz es el nodo que era el hijo derecho del
	 * nodo actual.
	 */
	// public IArbolBinario<E> cortaRamaDer();

}
