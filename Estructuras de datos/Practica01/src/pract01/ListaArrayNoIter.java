package pract01;

/**
 * Implementacion de una lista basada en array que no implementa el iterador.
 * 
 * @param <E> tipo de los elementos almacenados en la lista.
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version sep-2021
 */
public class ListaArrayNoIter<E> implements IListaNoIter<E> {
	// haz que la clase implemente la interfaz IListaNoIter e implementa
	// las operaciones de la citada interfaz segun lo descrito en sus comentarios
	// de documentacion y en los pseudocodigos incluidos como comentarios en este
	// fichero.

	private E[] elementos;	 // array que almacena los elementos de la lista
	private int numEle = 0;	 // numero de elementos almacenados en la lista

	/**
	 * Construye una lista vacia con la capacidad incial indicada.
	 * 
	 * @param capacidad capacidad inicial de la lista 
	 */
	@SuppressWarnings("unchecked")
	public ListaArrayNoIter(int capacidad) { 
		elementos = (E[]) new Object[capacidad]; // el compilador pone un "warning", pero esta bien
	}

	/**
	 * Construye una lista vacia con una capacidad inicial de 8 elementos.
	 */
	@SuppressWarnings("unchecked")
	public ListaArrayNoIter() { 
		elementos = (E[]) new Object[8]; // el compilador pone un "warning", pero esta bien
	}

	/**
	 * Inserta el elemento en la posicion indicada.
	 * El elemento en la posicion de insercion (si
	 * existe) y sucesivos se desplazan a la derecha
	 * (su posicion se incrementa en 1).
	 * @param pos posicion en la que insertar el elemento
	 * @param e elemento a insertar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos > tamanho)
	 */

	@Override
	public void anhade(int pos, E e) throws IndexOutOfBoundsException {

		if (pos < 0 || pos > tamanho()) {
			throw new IndexOutOfBoundsException();
		}

		if (numEle < elementos.length) {
			for (int i = (numEle - 1); i >= pos; i--) {
				elementos[i + 1] = elementos[i]; 
			}
		} else {
			@SuppressWarnings("unchecked")
			E[] nuevoArray = (E[]) new Object[elementos.length * 2];
			for (int i = 0; i < pos; i++) {
				nuevoArray[i] = elementos[i]; 
			}

			for (int i = (numEle - 1); i >= pos; i--) {
				nuevoArray[i + 1] = elementos[i];
			}

			elementos = nuevoArray;
		}

		elementos[pos] = e;
		numEle++;
	}


	/**
	 * Elimina y retorna el elemento en la posicion indicada. El elemento
	 * siguiente al eliminado (si existe) y sucesivos se desplazan a la
	 * izquierda (su posicion se decrementa en 1).
	 * @param pos posicion del elemento a eliminar
	 * @return el elemento eliminado
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */

	@Override
	public E elimina(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= tamanho()) {
			throw new IndexOutOfBoundsException();
		}

		E temp = elementos[pos];

		for (int i = pos; i < numEle - 1; i++) {
			elementos[i] = elementos[i + 1];
		}

		elementos[numEle - 1] = null;
		numEle--;
		return temp;
	}

	/**
	 * Retorna el elemento que ocupa la posicion indicada.
	 * @param pos posicion del elemento a retornar
	 * @return el elemento que ocupa la posicion indicada.
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */

	@Override
	public E obtenElemento(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= tamanho()) {
			throw new IndexOutOfBoundsException();
		}

		return elementos[pos];
	}

	/**
	 * Retorna el tamanho de la lista (numero de elementos).
	 * @return numero de elementos de la lista
	 */

	@Override
	public int tamanho() {
		return numEle;
	}

	/**
	 * Retorna la posicion de la primera ocurrencia del elemento
	 * buscado en la lista. Utiliza el metodo equals del elemento.
	 * @param e elemento buscado
	 * @return posicion de la primera ocurrencia del elemento en la
	 * lista o -1 en caso de que el elemento no este en la lista
	 */

	@Override
	public int busca(E e) {
		for (int i = 0; i < numEle; i++) {
			if (elementos[i].equals(e)) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public void haceVacia() {
		for (int i = 0; i < numEle; i++) {
			elementos[i] = null;
		}
		numEle = 0;
	}

	@Override
	public String toString() {
		String str = "[";
		for (int i = 0; i < numEle; i++) {
			str += elementos[i];
			if (i < numEle - 1) {
				str = str + ", ";
			}
		}
		return str + "]";
	}

	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		ListaArrayNoIter<E> l2 = (ListaArrayNoIter<E>) obj;
		if (this.numEle != l2.numEle) {
			return false;
		}

		for (int i = 0; i < this.numEle; i++) {
			if (!this.elementos[i].equals(l2.elementos[i])) {
				return false;
			}
		}

		return true;
	}

}
