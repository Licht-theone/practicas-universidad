package pract08.c1_arbol_binario_ph;

/**
 * Arbol binario "padre-hijos" (nodos con punteros a
 * padre, hijo derecho e hijo izquierdo.
 * 
 * @param <E> tipo de los elementos almacenados en el arbol
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version oct-2022
 */
public class ArbolBinarioPH<E> implements IArbolBinario<E> {

	//atributo con la raiz del arbol
	private Nodo raiz;
	private int numEle;

	/**
	 * Crea un arbol vacio.
	 */
	public ArbolBinarioPH() {
		raiz = null;
		numEle = 0;
	}
	//O(1)
	
	/**
	 * Crea un arbol con con el contenido indicado en su nodo raiz.
	 * @param contenidoRaiz contenido del nodo raiz.
	 */
	public ArbolBinarioPH(E contenidoRaiz) {
		raiz = new Nodo(contenidoRaiz, null, null, null);
		numEle = 1;
	}
	//O(1)

	/**
	 * Retorna el elemento que ocupa la raiz del arbol.
	 * @return elemento que ocupa la raiz del arbol
	 */
	@Override
	public Nodo raiz() {
		return raiz;
	}
	//O(1)

	/**
	 * Retorna el numero de elementos del arbol.
	 * @return numero de elementos del arbol
	 */
	@Override
	public int tamanho() {
		return numEle;
	}
	//O(1)

	/**
	 * Vacia el arbol.
	 */
	@Override
	public void haceVacio() {
		raiz = null;
		numEle = 0;
	}
	//O(1)

	/**
	 * En un arbol vacio anhade el nodo raiz con el contenido indicado.
	 * @param contenidoRaiz contenido del nodo raiz
	 * @throws UnsupportedOperationException si el arbol no esta vacio.
	 */
	@Override
	public void anhadeRaiz(E contenidoRaiz)
			throws UnsupportedOperationException {
		if (raiz != null) {
			throw new UnsupportedOperationException();
		}
		raiz = new Nodo(contenidoRaiz, null, null, null);
		numEle++;
	}
	//O(1)

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
		@Override
		public E contenido() {
			return contenido;
		}
		//O(1)

		/**
		 * Cambia el contenido del nodo.
		 * @param nuevoContenido nuevo contenido del nodo.
		 */
		@Override
		public void cambiaContenido(E nuevoContenido) {
			contenido = nuevoContenido;
		}
		//O(1)

		/**
		 * Retorna el padre del nodo.
		 * @return el padre del nodo o null si el nodo actual es la raiz.
		 */
		@Override
		public Nodo padre() {
			return padre;
		}
		//O(1)

		/**
		 * Retorna el hijo izquierdo del nodo.
		 * @return el hijo izquierdo del nodo o null si el nodo actual
		 * no tiene hijo izquierdo.
		 */
		@Override
		public Nodo hijoIzq() {
			return hijoIzq;
		}
		//O(1)

		/**
		 * Retorna el hijo derecho del nodo.
		 * @return el hijo derecho del nodo o null si el nodo actual
		 * no tiene hijo derecho.
		 */
		@Override
		public Nodo hijoDer() {
			return hijoDer;
		}
		//O(1)

		/**
		 * Anhade el hijo izquierdo al nodo.
		 * @param contenido contenido del hijo izquierdo.
		 * @throws UnsupportedOperationException si el nodo ya tiene
		 * hijo izquierdo.
		 */
		@Override
		public void anhadeHijoIzq(E contenido)
				throws UnsupportedOperationException {
			if (hijoIzq != null) {
				throw new UnsupportedOperationException();
			}
			hijoIzq = new Nodo(contenido, this, null, null);
			numEle++;

		}
		//O(1)

		/**
		 * Anhade el hijo derecho al nodo.
		 * @param contenido contenido del hijo derecho.
		 * @throws UnsupportedOperationException si el nodo ya tiene
		 * hijo derecho.
		 */
		@Override
		public void anhadeHijoDer(E contenido)
				throws UnsupportedOperationException {
			if (hijoDer != null) {
				throw new UnsupportedOperationException();
			}
			hijoDer = new Nodo(contenido, this, null, null);
			numEle++;
		}
		//O(1)

		/**
		 * Anhade la raiz de la rama como hijo izquierdo del nodo actual.
		 * Despues de esta operacion el arbol "rama" queda vacio.
		 * @param rama rama que anhadir como hijo izquierdo.
		 * @throws UnsupportedOperationException si el nodo ya tiene
		 * hijo izquierdo.
		 */
		@Override
		public void anhadeRamaIzq(IArbolBinario<E> rama)
				throws UnsupportedOperationException {
			// parte opcional
			if (hijoIzq != null) {
				throw new UnsupportedOperationException();
			}
			hijoIzq = (ArbolBinarioPH<E>.Nodo) rama.raiz();
			hijoIzq.padre = this;
			numEle += rama.tamanho();
			rama.haceVacio();
		}
		//O(1)

		/**
		 * Anhade la raiz de la rama como hijo derecho del nodo actual.
		 * Despues de esta operacion el arbol "rama" queda vacio.
		 * @param rama rama que anhadir como hijo derecho.
		 * @throws UnsupportedOperationException si el nodo ya tiene
		 * hijo derecho.
		 */
		@Override
		public void anhadeRamaDer(IArbolBinario<E> rama) throws UnsupportedOperationException {
			//  parte opcional
			if (hijoDer != null) {
				throw new UnsupportedOperationException();
			}
			hijoDer = (ArbolBinarioPH<E>.Nodo) rama.raiz();
			hijoDer.padre = this;
			numEle += rama.tamanho();
			rama.haceVacio();
		}
		//O(1)

		/**
		 * Elimina la rama del arbol cuya raiz es el nodo izquierdo del
		 * nodo actual.
		 * @return arbol cuya raiz es el nodo que era el hijo izquierdo del
		 * nodo actual.
		 */
		@Override
		public IArbolBinario<E> cortaRamaIzq() {
			IArbolBinario<E> rama = new ArbolBinarioPH<E>(hijoIzq.contenido);
			Nodo auxRaiz = (ArbolBinarioPH<E>.Nodo) rama.raiz();
			auxRaiz.hijoIzq = hijoIzq.hijoIzq;
			auxRaiz.hijoDer = hijoIzq.hijoDer;
			hijoIzq.hijoIzq.padre = auxRaiz;
			hijoIzq.hijoDer.padre = auxRaiz;
			hijoIzq = null;
			return rama;
		}

		/**
		 * Elimina la rama del arbol cuya raiz es el nodo derecho del
		 * nodo actual.
		 * @return arbol cuya raiz es el nodo que era el hijo derecho del
		 * nodo actual.
		 */
		@Override
		public IArbolBinario<E> cortaRamaDer() {
			IArbolBinario<E> rama = new ArbolBinarioPH<E>(hijoDer.contenido);
			Nodo auxRaiz = (ArbolBinarioPH<E>.Nodo) rama.raiz();
			auxRaiz.hijoIzq = hijoDer.hijoIzq;
			auxRaiz.hijoDer = hijoDer.hijoDer;
			hijoDer.hijoIzq.padre = auxRaiz;
			hijoDer.hijoDer.padre = auxRaiz;
			hijoDer = null;
			return rama;
		}
	}
}
