package examen.c1_busca_igual_a_suma_hijos;

/**
 * Arbol binario "padre-hijos" (nodos con punteros a
 * padre, hijo derecho e hijo izquierdo.
 * 
 * @param <E> tipo de los elementos almacenados en el arbol
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2020
 */
public class ArbolBinarioPH<E> implements IArbolBinario<E> {

	// raiz del arbol
	private Nodo raiz;
	
	// numero de nodos en el arbol
	private int numNodos;
	
	/**
	 * Crea un arbol vacio.
	 */
	// Complejidad temporal: O(1)
	public ArbolBinarioPH() {
		this.raiz = null;
		numNodos = 0;
	}

	/**
	 * Crea un arbol con con el contenido indicado en su nodo raiz.
	 * @param contenidoRaiz contenido del nodo raiz.
	 */
	// Complejidad temporal: O(1)
	public ArbolBinarioPH(E contenidoRaiz) {
		this.raiz = new Nodo(contenidoRaiz, null, null, null);
		numNodos = 1;
	}

	/**
	 * Retorna el elemento que ocupa la raiz del arbol.
	 * @return elemento que ocupa la raiz del arbol
	 */
	// Complejidad temporal: O(1)
	@Override
	public Nodo raiz() {
		return raiz;
	}

	/**
	 * Retorna el numero de elementos del arbol.
	 * @return numero de elementos del arbol
	 */
	// Complejidad temporal: O(numero de nodos del arbol)
	@Override
	public int tamanho() {
		return numNodos;
	}
	
	/**
	 * Vacia el arbol.
	 */
	// Complejidad temporal: O(1)
	@Override
	public void haceVacio() {
		raiz = null;
		numNodos = 0;
	}
	
	/**
	 * En un arbol vacio anhade el nodo raiz con el contenido indicado.
	 * @param contenidoRaiz contenido del nodo raiz
	 * @throws UnsupportedOperationException si el arbol no esta vacio.
	 */
	// Complejidad temporal: O(1)
	@Override
	public void anhadeRaiz(E contenidoRaiz)
			throws UnsupportedOperationException {
		if (raiz != null) {
			throw new UnsupportedOperationException();
		}

		raiz = new Nodo(contenidoRaiz, null, null, null);
		numNodos = 1;		
	}

	/**
	 * Clase que implementa un nodo del arbol.
	 */
	public class Nodo implements INodoArbolBinario<E> {
		private E contenido;
		private Nodo padre;
		private Nodo hijoIzq;
		private Nodo hijoDer;

		/**
		 * Construye un nodo.
		 * @param contenido contenido del nodo.
		 * @param padre nodo padre del nodo.
		 * @param hijoIzq nodo que es el hijo izquierdo del nodo.
		 * @param hijoDer nodo que es el hijo derecho del nodo.
		 */
		// Complejidad temporal: O(1)
		private Nodo(E contenido, Nodo padre, Nodo hijoIzq, Nodo hijoDer) {
			this.contenido = contenido;
			this.padre = padre;
			this.hijoIzq = hijoIzq;
			this.hijoDer = hijoDer;
		}
		
		/**
		 * Retorna el contenido del nodo.
		 * @return el contenido del nodo.
		 */
		// Complejidad temporal: O(1)
		@Override
		public E contenido() {
			return contenido;
		}
		
		/**
		 * Cambia el contenido del nodo.
		 * @param nuevoContenido nuevo contenido del nodo.
		 */
		// Complejidad temporal: O(1)
		@Override
		public void cambiaContenido(E nuevoContenido) {
			contenido = nuevoContenido;
		}
		
		/**
		 * Retorna el padre del nodo.
		 * @return el padre del nodo o null si el nodo actual es la raiz.
		 */
		// Complejidad temporal: O(1)
		@Override
		public Nodo padre() {
			return padre;
		}
		
		/**
		 * Retorna el hijo izquierdo del nodo.
		 * @return el hijo izquierdo del nodo o null si el nodo actual
		 * no tiene hijo izquierdo.
		 */
		// Complejidad temporal: O(1)
		@Override
		public Nodo hijoIzq() {
			return hijoIzq;
		}
		
		/**
		 * Retorna el hijo derecho del nodo.
		 * @return el hijo derecho del nodo o null si el nodo actual
		 * no tiene hijo derecho.
		 */
		// Complejidad temporal: O(1)
		@Override
		public Nodo hijoDer() {
			return hijoDer;
		}

		/**
		 * Anhade el hijo izquierdo al nodo.
		 * @param contenido contenido del hijo izquierdo.
		 * @throws UnsupportedOperationException si el nodo ya tiene
		 * hijo izquierdo.
		 */
		// Complejidad temporal: O(1)
		@Override
		public void anhadeHijoIzq(E contenido)
				throws UnsupportedOperationException {
			if (hijoIzq != null) {
				throw new UnsupportedOperationException();
			}
			
			hijoIzq = new Nodo(contenido, this, null, null);	
			ArbolBinarioPH.this.numNodos++;
		}
		
		/**
		 * Anhade el hijo derecho al nodo.
		 * @param contenido contenido del hijo derecho.
		 * @throws UnsupportedOperationException si el nodo ya tiene
		 * hijo derecho.
		 */
		// Complejidad temporal: O(1)
		@Override
		public void anhadeHijoDer(E contenido)
				throws UnsupportedOperationException {
			if (hijoDer != null) {
				throw new UnsupportedOperationException();
			}
			
			hijoDer = new Nodo(contenido, this, null, null);	
			ArbolBinarioPH.this.numNodos++;
		}
	}
}
