package pract02.lista_array_noiter;

/**
 * Implementación de una lista basada en array que no implementa el iterador.
 * 
 * @param <E> tipo de los elementos almacenados en la lista.
 * 
 * @author Estructuras de Datos (UC)
 * @version sep-2021
 */
public class ListaArrayNoIter<E> implements IListaNoIter<E> {

	private E[] elementos;	 // array que almacena los elementos de la lista
	private int numEle = 0;	 // numero de elementos almacenados en la lista
	private static final int TAMANHO_POR_DEFECTO = 8;

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
		elementos = (E[]) new Object[TAMANHO_POR_DEFECTO];
		// el compilador pone un "warning", pero esta bien (no hay otra forma de hacerlo)
	}

	/**
	 * Inserta el elemento en la posición indicada.
	 * El elemento en la posición de inserción (si
	 * existe) y sucesivos se desplazan a la derecha
	 * (su posición se incrementa en 1).
	 * @param pos posicion en la que insertar el elemento
	 * @param e elemento a insertar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos > tamanho)
	 */
	@Override
	public void anhade(int pos, E e) throws IndexOutOfBoundsException {
		if (pos < 0 || pos > tamanho()) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		if (numEle < elementos.length) {
			// el elemento cabe en el array

			// desplaza elementos hacia delante para hacer hueco
			for (int i = numEle - 1; i >= pos; i--) {
				elementos[i + 1] = elementos[i];
			}

		} else {
			// se ha alcanzado el numero maximo de elementos, luego hay que redimensionar el array.
			// Crea un nuevo array con la nueva capacidad
			@SuppressWarnings("unchecked")
			E[] nuevo = (E[]) new Object[elementos.length * 2];
			// el compilador pone un "warning", pero esta bien

			// copia los elementos hasta la posicion a anhadir al array nuevo en la misma
			// posicion que ocupaban en el viejo
			for (int i = 0; i < pos; i++) {
				nuevo[i] = elementos[i];
			}

			// copia los elementos a partir de la posicion a anhadir al array nuevo
			// en la posicion siguiente a la que ocupaban en el viejo
			for (int i = pos; i < numEle; i++) {
				nuevo[i + 1] = elementos[i];
			}

			// sustituye el array viejo por el nuevo
			elementos = nuevo;
		} 

		// añade el elemento en la posicion deseada
		elementos[pos] = e;
		numEle++;
	}

	/**
	 * Elimina y retorna el elemento en la posición
	 * indicada. El elemento siguiente al eliminado
	 * (si existe) y sucesivos se desplazan a la
	 * izquierda (su posición se decrementa en 1).
	 * @param pos posicion del elemento a eliminar
	 * @return el elemento eliminado
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */
	@Override
	public E elimina(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= tamanho()) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		// guarda el elemento a eliminar
		E temp = elementos[pos];

		// desplaza elementos hacia detrás para cubrir el hueco del
		// elemento eliminado
		for (int i = pos; i < numEle - 1; i++) {
			elementos[i] = elementos[i + 1];
		}

		// posicion no usada: la pone a null para permitir liberacion de memoria
		// por parte del "recolector de basura"
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
			throw new IndexOutOfBoundsException(); // posicion incorrecta
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
	 * Retorna la posicion de la primera ocurrencia
	 * del elemento buscado en la lista.
	 * Utiliza el método equals del elemento.
	 * @param e elemento buscado
	 * @return posicion de la primera ocurrencia
	 * del elemento en la lista o -1 en caso de que 
	 * el elemento no este en la lista
	 */
	@Override
	public int busca(E e) {
		for (int i = 0; i < numEle; i++) {
			if (e.equals(elementos[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Vacia la lista (pasa a tener tamanho 0).
	 */
	@Override
	public void haceVacia() {
		// pone los punteros a null para permitir liberacion de memoria
		// por parte del "recolector de basura"
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
