package mc03;

import java.util.NoSuchElementException;


/**
 * Implementacion de una lista generica utilizando un array.
 * Implementa un iterador sencillo.
 * 
 * @param <E> tipo de los elementos almacenados en la lista.
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version sep-2023
 */
public class ListaArray<E> implements ILista<E> {

	private E[] elementos;	 // array que almacena los elementos de la lista
	private int numEle = 0;	 // numero de elementos almacenados en la lista

	/**
	 * Construye una lista vacia con la capacidad incial indicada.
	 * 
	 * @param capacidad capacidad inicial de la lista 
	 */
	@SuppressWarnings("unchecked")
	public ListaArray(int capacidad) { 
		elementos = (E[]) new Object[capacidad]; // el compilador pone un "warning", pero esta bien
	}

	/**
	 * Construye una lista vacia con una capacidad inicial de 8 elementos.
	 */
	@SuppressWarnings("unchecked")
	public ListaArray() {
		final int capacidadPorDefecto = 8;
		elementos = (E[]) new Object[capacidadPorDefecto];
		// el compilador pone un "warning", pero esta bien
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
	 * Elimina y retorna el elemento en la posicion
	 * indicada. El elemento siguiente al eliminado
	 * (si existe) y sucesivos se desplazan a la
	 * izquierda (su posicion se decrementa en 1).
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

		// desplaza elementos hacia detras para cubrir el hueco del
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
	 * Utiliza el metodo equals del elemento.
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

	/**
	 * Clase iteradora.
	 * 
	 * @param <E> tipo de los elementos almacenados en la lista
	 */
	private static class IteradorLista<E> implements IIteradorSimple<E> {
		private ListaArray<E> lista; // lista a ser iterada
		private int ultRet; // indice del ultimo elemento retornado
		// TODO: implementa la clase iteradora tomando como referencia
		// las diapositivas 50 y 51 de los apuntes.

		/**
		 * Constructor del iterador. El iterador comienza al principio
		 * de la lista (justo antes del primer elemento)
		 * @param lista lista a ser iterada
		 */
		public IteradorLista(ListaArray<E> lista) {
			this.lista = lista;
			ultRet = -1;
		}

		/**
		 * Indica si hay más elementos (no se ha llegado al final de la lista).
		 * @return true si todavia no se ha llegado al final de la lista
		 */
		@Override
		public boolean haySiguiente() {
			if (ultRet < lista.tamanho() - 1) {
				return true;
			}
			return false;
		}

		/**
		 * Retorna el siguiente elemento en la iteracion y avanza el iterador.
		 * @return el siguiente elemento
		 * @throws NoSuchElementException si se ha llegado al final de la lista
		 */
		@Override
		public E siguiente() throws NoSuchElementException {
			if (!haySiguiente()) {
				throw new NoSuchElementException();
			}
			ultRet++;
			return lista.elementos[ultRet];
		}

		/**
		 * Inserta un elemento en la posición del iterador. El iterador se situa despues
		 * del elemento anhadido.
		 * @param e elemento a insertar
		 */
		@Override
		public void anhade(E e) {
			lista.anhade(ultRet + 1, e);
			ultRet++;
		}

		/**
		 * Reemplaza el ultimo elemento accedido por el iterador.
		 * Se considera ultimo elemento accedido a el ultimo retornado por
		 * siguiente() o el ultimo anhadido por anhade(), lo que haya
		 * ocurrido mas recientemente.
		 * @param e elemento utilizado para reemplazar
		 * @throws NoSuchElementException si el iterador no ha retornado ningún elemento
		 * previamente ni se ha llamado a anhade().
		 */
		@Override
		public void asigna(E e) throws NoSuchElementException {
			 if (ultRet == -1) {
				 throw new NoSuchElementException();
			 }
			 lista.elementos[ultRet] = e;
		}			
	}

	/**
	 * Retorna un iterador que permite recorrer los elementos en el orden que
	 * ocupan en la lista.
	 * @return iterador que permite recorrer los elementos de la lista
	 */
	// Complejidad temporal: O(1)
	@Override
	public IIteradorSimple<E> iterador() {
		// XXX: nuevo metodo incluido para dar soporte al iterador
		return new IteradorLista<E>(this);
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
		ListaArray<E> l2 = (ListaArray<E>) obj;
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
