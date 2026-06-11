package pract13.secuencia_ordenada_abb;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Secuencia Ordenada implementada utilizando un ABB.
 * 
 * @param <E> tipo de los elementos almacenados en la secuencia
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2024
 */
public class SecuenciaOrdenadaABB<E extends Comparable<E>> implements ISecuenciaOrdenada<E> {

	/**
	 * Nodo del ABB sobre el que se implementa la secuencia ordenada. 
	 * @param <E> tipo de los elementos almacenados en la secuencia
	 */
	private static class Nodo<E> {
		private E contenido;
		private Nodo<E> padre;
		private Nodo<E> hijoIzq;
		private Nodo<E> hijoDer;
		private int tamanhoSubarbol;

		/**
		 * Construye un nodo sin hijos (tamanhoSubarbol = 1).
		 * @param contenido elemento almacenado en el nodo.
		 * @param padre padre del nodo.
		 */
		public Nodo(E contenido, Nodo<E> padre) {
			this.contenido = contenido;
			this.padre = padre;
			tamanhoSubarbol = 1;
		}

		@Override
		public String toString() {
			return "(" + contenido + ", " + tamanhoSubarbol + ")";
		}
	}

	// raiz del ABB sobre el que se implementa la secuencia
	private Nodo<E> raiz = null;

	// numero de elementos de la secuencia
	private int numEle = 0;

	// Complejidad temporal: O(numEle)
	// (En un ABB balanceado (AVL o Rojinegro) la complejidad temporal
	// seria O(log numEle))
	@Override
	public void anhade(E e) {
		if (raiz == null) { // arbol vacio (caso especial)
			raiz = new Nodo<E>(e, null);
			numEle = 1;
			return;
		}

		Nodo<E> n = raiz;
		Nodo<E> nodoPrev = null;
		int comp;
		do {
			n.tamanhoSubarbol++;
			nodoPrev = n;
			comp = e.compareTo(n.contenido);

			if (comp < 0) { // e < n.contenido
				n = n.hijoIzq;

			} else { // e >= n.contenido
				n = n.hijoDer; 
			}
		} while (n != null);

		if (comp < 0) { // e < n.contenido
			nodoPrev.hijoIzq = new Nodo<E>(e, nodoPrev);

		} else { // e >= n.contenido
			nodoPrev.hijoDer = new Nodo<E>(e, nodoPrev);
		}
		numEle++;
	}

	// Complejidad temporal: O(numEle)
	// (En un ABB balanceado (AVL o Rojinegro) la complejidad temporal
	// seria O(log numEle))
	@Override
	public E elimina(E e) {
		final int pos = busca(e);
		if (pos == -1) {
			return null;
		}
		return elimina(pos);
	}

	// Complejidad temporal: O(numEle)
	// (En un ABB balanceado (AVL o Rojinegro) la complejidad temporal
	// seria O(log numEle))
	@Override
	public E elimina(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= numEle) {
			throw new IndexOutOfBoundsException();
		}
		Nodo<E> nodoEliminado = new Nodo<E>(null, null);
		raiz = eliminaRec(raiz, pos, nodoEliminado);
		return nodoEliminado.contenido;
	}

	/**
	 * Metodo recursivo que elimina el nodo en la posicion indicada en
	 * el subarbol que tiene como raíz al nodo n.
	 * @param n nodo actual
	 * @param pos posicion del nodo a eliminar
	 * @param nodoEliminado nodo en el que se almacena el contenido del
	 * nodo a eliminado
	 * @return la nueva raíz del subárbol, que podrá ser
	 * el mismo nodo n o uno distinto en el caso de que
	 * n fuera precisamente el nodo a eliminar
	 */
	// Complejidad temporal: O(numEle)
	// (En un ABB balanceado (AVL o Rojinegro) la complejidad temporal
	// seria O(log numEle))
	private Nodo<E> eliminaRec(Nodo<E> n, int pos, Nodo<E> nodoEliminado) {
		int posNodo = tamanhoSubarbol(n.hijoIzq);

		if (pos == posNodo) {
			// encontrado el nodo en la posicion buscada
			numEle--;
			nodoEliminado.contenido = n.contenido;

			if (n.hijoIzq != null && n.hijoDer != null) {
				// tiene los dos hijos
				E retornar = n.contenido;

				// elimina el menor del subarbol derecho
				//Nodo<E> menor = buscaMenor(n.hijoDer);
				n.hijoDer = eliminaRec(n.hijoDer, 0, nodoEliminado);
				if (n.hijoDer != null) {
					n.hijoDer.padre = n;
				}

				// sustituye el nodo por el menor eliminado del subarbol derecho
				n.contenido = nodoEliminado.contenido;
				nodoEliminado.contenido = retornar;
				n.tamanhoSubarbol--;				
				numEle++; // la anterior llamada a eliminaRec ha restado 1

			} else if (n.hijoIzq != null) {
				// tiene sólo el hijo izquierdo: se sustituye por él
				n = n.hijoIzq;

			} else {
				// o sólo tiene hijo derecho o no tiene nigún hijo: se sustituye
				// por el hijo derecho o se elimina directametente
				n = n.hijoDer;
			}

		} else {
			// sigue buscado

			n.tamanhoSubarbol--;

			if (pos < posNodo) {
				n.hijoIzq = eliminaRec(n.hijoIzq, pos, nodoEliminado);
				if (n.hijoIzq != null) {
					n.hijoIzq.padre = n;
				}
			} else {
				n.hijoDer = eliminaRec(n.hijoDer, pos - posNodo - 1, nodoEliminado);
				if (n.hijoDer != null) {
					n.hijoDer.padre = n;
				}
			}
		}

		return n;
	}

	// Complejidad temporal: O(numEle)
	// (En un ABB balanceado (AVL o Rojinegro) la complejidad temporal
	// seria O(log numEle))
	@Override
	public int busca(E e) {
		Nodo<E> n = buscaNodo(e);
		if (n == null) {
			return -1;
		}

		int posNodo = tamanhoSubarbol(n.hijoIzq) + 1;

		while (n != raiz) {
			if (n == n.padre.hijoDer) {
				// es hijo derecho
				posNodo += tamanhoSubarbol(n.padre.hijoIzq) + 1;
			}
			n = n.padre;
		}

		return posNodo - 1;
	}

	// Complejidad temporal: O(numEle)
	// (En un ABB balanceado (AVL o Rojinegro) la complejidad temporal
	// seria O(log numEle))
	@Override
	public E obtenElemento(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= numEle) {
			throw new IndexOutOfBoundsException();
		}

		return buscaNodoEnPos(raiz, pos).contenido;
	}

	/**
	 * Retorna el nodo que ocupa la posicion indicada en el subarbol que
	 * tiene como raiz el nodo pasado como parametro.
	 * {Precondicion: la posicion es valida}
	 * @param raiz raiz del subarbol en el que buscar.
	 * @param pos posicion en el subarbol del nodo buscado.
	 * @return el nodo que ocupa la posicion indicada en el subarbol.
	 */
	// Complejidad temporal: O(numEle)
	// (En un ABB balanceado (AVL o Rojinegro) la complejidad temporal
	// seria O(log numEle))
	private Nodo<E> buscaNodoEnPos(Nodo<E> raiz, int pos) {
		int posNodo = tamanhoSubarbol(raiz.hijoIzq);

		if (pos == posNodo) {
			return raiz;
		}

		if (pos < posNodo) {
			return buscaNodoEnPos(raiz.hijoIzq, pos);
		} else {
			return buscaNodoEnPos(raiz.hijoDer, pos - posNodo - 1);
		}
	}

	// Complejidad temporal: O(1) + recoleccion de basura
	@Override
	public void haceVacia() {
		raiz = null;
		numEle = 0;
	}

	// Complejidad temporal: O(1)
	@Override
	public int tamanho() {
		return numEle;
	}

	/**
	 * Retorna un nodo con el contenido pasado como parametro.
	 * @param e contenido del nodo a buscar.
	 * @return un nodo con el contenido pasado como parametro.
	 */
	// Complejidad temporal: O(numEle)
	// (En un ABB balanceado (AVL o Rojinegro) la complejidad temporal
	// seria O(log numEle))
	private Nodo<E> buscaNodo(E e) {
		Nodo<E> n = raiz;
		boolean encontrado = false;

		while (n != null && !encontrado) {
			if (e.equals(n.contenido)) { // encontrado
				encontrado = true;
				return n;

			} else if (e.compareTo(n.contenido) < 0) { // e < n.contenido
				n = n.hijoIzq;

			} else { // e >= n.contenido
				n = n.hijoDer; 
			}
		}
		return null;
	}

	/**
	 * Retorna el tamanho del subarbol cuya raiz el el nodo pasado como
	 * parametro.
	 * @param n nodo raiz del subarbol.
	 * @return el tamanho del subarbol cuya raiz el el nodo o 0 si el
	 * nodo es null.
	 */
	private int tamanhoSubarbol(Nodo<E> n) {
		if (n == null) {
			return 0;
		}
		return n.tamanhoSubarbol;
	}

	@Override
	public String toString() {
		if (raiz == null) {
			return "";
		}
		dibuja(raiz);
		return dibuja(raiz) + "\n Inorden:[" + inorden(raiz) + "]";
	}

	/**
	 * Metodo auxiliar que genera un string con los nodos
	 * del arbol en inorden.
	 * @param raiz raiz del arbol a mostrar.
	 * @return string con los nodos del arbol en inorden.
	 */
	private String inorden(Nodo<E> raiz) {
		String str = "";

		// recorre el subarbol del hijo izquierdo (si le hay)
		if (raiz.hijoIzq != null) {
			str += inorden(raiz.hijoIzq);
		}

		// muestra el contenido del nodo actual
		str += raiz;

		// muestra el subarbol del hijo derecho (si le hay)
		if (raiz.hijoDer != null) {
			str += inorden(raiz.hijoDer);
		}

		return str;
	}

	/**
	 * Metodo auxiliar que genera un string con un dibujo del arbol.
	 * @param raiz raiz del arbol a mostrar.
	 * @return string con un dibujo del arbol.
	 */
	private String dibuja(Nodo<E> raiz) {
		String str = "";
		Queue<Nodo<E>> cola = new LinkedList<Nodo<E>>();
		cola.add(raiz);

		final int ELE_MEDIO_ANCHO;
		if (raiz != null) {
			// calcula el ancho de los datos a pintar en cada nodo
			ELE_MEDIO_ANCHO =
					(new String(raiz.contenido + "," +
							raiz.tamanhoSubarbol).length() - 1) / 2;
		} else {
			ELE_MEDIO_ANCHO = 0;
		}

		int nivel = 0;
		int contNodos = 1;

		while (!cola.isEmpty() && !todosNulosEnCola(cola)) {
			Nodo<E> nodo = cola.poll();

			str += printNodo(contNodos, nivel, nodo, ELE_MEDIO_ANCHO);

			contNodos++;
			if (contNodos == Math.pow(2, (nivel + 1))) {
				nivel++;
			}

			if (nodo != null) {
				if (nodo.hijoIzq == null) {
					cola.add(null);
				} else {
					cola.add(nodo.hijoIzq);
				}			
				if (nodo.hijoDer == null) {
					cola.add(null);
				} else {
					cola.add(nodo.hijoDer);
				}
			} else {
				cola.add(null);
				cola.add(null);
			}
		}
		return str;
	}

	/**
	 * Retorna un string con el nodo y los espacios para que aparezca
	 * con la posicion que le corresponde en el dibujo del arbol.
	 * @param contNodos posicion que ocupa el nodo en el arbol.
	 * @param nivel nivel que ocupa el nodo en el arbol.
	 * @param nodo nodo a escribir.
	 * @param eleMedioAncho mitad del ancho requerido para escribir los nodos.
	 * @return string con el nodo y los espacios para que aparezca
	 * con la posicion que le corresponde en el dibujo del arbol.
	 */
	private String printNodo(int contNodos, int nivel, Nodo<E> nodo,
			int eleMedioAncho) {	
		final int MITAD_ANCHO = 64;
		String str = "";
		final boolean primeroDelNivel = (contNodos ==  Math.pow(2, nivel));
		final boolean ultimoDelNivel = (contNodos == Math.pow(2, (nivel + 1)) - 1);
		int numEspacios = 0;

		if (primeroDelNivel) {
			numEspacios = MITAD_ANCHO / (int) Math.pow(2, (nivel + 1)) - eleMedioAncho;
		} else {
			numEspacios = MITAD_ANCHO / (int) Math.pow(2, nivel) - eleMedioAncho;
		}

		for (int i = 0; i < numEspacios; i++) {
			str += ' ';
		}

		if (nodo == null) {
			for (int i = 0; i < eleMedioAncho * 2; i++) {
				str += ' ';
			}
		} else {
			str += nodo.contenido + "," + nodo.tamanhoSubarbol;
		}

		if (ultimoDelNivel) {
			str += "\n\n";
		}

		return str;
	}

	/**
	 * Retorna si todos los nodos de la cola son null.
	 * @param cola cola a analizar
	 * @return verdadero si todos los nodos de la cola son null.
	 */
	private boolean todosNulosEnCola(Queue<Nodo<E>> cola) {
		for (Nodo<E> nodo: cola) {
			if (nodo != null) {
				return false;
			}
		}
		return true;
	}

}
