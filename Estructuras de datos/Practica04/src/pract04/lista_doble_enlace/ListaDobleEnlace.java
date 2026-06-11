package pract04.lista_doble_enlace;

import java.util.NoSuchElementException;

import pract04.ilista.IIteradorSimple;
import pract04.ilista.ILista;

/**
 * Implementacion de una lista generica utilizando celdas doblemente enlazadas
 * con celdas de cabecera y cola y punteros a principio y fin.
 * 
 * @param <E> tipo de los elementos almacenados en la lista
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version sep-2023
 */
public class ListaDobleEnlace<E> implements ILista<E> {

	// clase privada que define la celda
	private static class Celda<E> {
		private E contenido;
		private Celda<E> siguiente;
		private Celda<E> anterior;

		public Celda(E cont) {
			contenido = cont;
		}
	}

	// referencia a la primera celda de la lista (celda de cabecera)
	private final Celda<E> principio;

	// referencia a la ultima celda de la lista (celda de cola)
	private final Celda<E> fin;

	// numero de elementos en la lista
	private int numEle;

	/**
	 * Construye una lista vacia.
	 */
	// Complejidad temporal: O(1) 
	public ListaDobleEnlace() {
		principio = new Celda<E>(null); // crea la celda de cabecera
		fin = new Celda<E>(null); // crea la celda de cola

		// coloca las celdas de cabecera y cola apuntandose entre si
		principio.siguiente = fin;
		fin.anterior = principio;

		// la lista comienza con 0 elementos
		numEle = 0;
	}

	/**
	 * Construye una lista vacia.
	 * @param capacidad capacidad inicial de la lista (parametro
	 * no usado).
	 * (Este constructor se incluye para que existan los mismos
	 * constructores que en el caso de ListaArrayNoIter)
	 */
	// Complejidad temporal: O(1) 
	public ListaDobleEnlace(int capacidad) {
		this(); // llama al constructor sin parametros ListaDobleEnlaceNoIter()
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
	// Complejidad temporal: O(numEle) 
	//   Caso especial: anhadir el primero y el ultimo es O(1)
	@Override
	public void anhade(int pos, E e) throws IndexOutOfBoundsException {
		if (pos < 0 || pos > numEle) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		Celda<E> nuevaCelda = new Celda<E>(e);
		Celda<E> celdaAnt = celdaPos(pos - 1);
		nuevaCelda.siguiente = celdaAnt.siguiente;
		nuevaCelda.anterior = celdaAnt;
		celdaAnt.siguiente.anterior = nuevaCelda;
		celdaAnt.siguiente = nuevaCelda;
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
	// Complejidad temporal: O(numEle)
	//   Caso especial: eliminar el primero y el ultimo es O(1) 
	@Override
	public E elimina(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= numEle) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		Celda<E> celdaElim = celdaPos(pos);
		celdaElim.anterior.siguiente = celdaElim.siguiente;
		celdaElim.siguiente.anterior = celdaElim.anterior;
		numEle--;

		return celdaElim.contenido;
	}

	/**
	 * Retorna el elemento que ocupa la posicion indicada.
	 * @param pos posicion del elemento a retornar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */
	// Complejidad temporal: O(numEle)
	//   Caso especial: obtener el primero y el ultimo es O(1) 
	@Override
	public E obtenElemento(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= tamanho()) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		return celdaPos(pos).contenido;
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
	// Complejidad temporal: O(numEle)
	@Override
	public int busca(E e) {
		Celda<E> aux = principio.siguiente;
		int pos = 0;
		while (aux != fin) {
			if (aux.contenido.equals(e)) {
				return pos;  // encontrado
			}
			aux = aux.siguiente;
			pos++;
		}
		return -1; // no encontrado
	}

	/**
	 * Vacia la lista (pasa a tener tamanho 0).
	 */
	// Complejidad temporal: O(1)
	@Override
	public void haceVacia() {
		principio.siguiente = fin;
		fin.anterior = principio;
		numEle = 0;
	}

	/**
	 * Retorna el tamanho de la lista (numero de elementos).
	 * @return numero de elementos de la lista
	 */
	// Complejidad temporal: O(1)
	@Override
	public int tamanho() {
		return numEle;
	}

	/**
	 * Retorna una referencia a la celda en la posicion pos.
	 * {Pre: pos >= -1 y pos < numEle}
	 * @param pos posicion de la celda buscada
	 * @return referencia a la celda en la posicion pos
	 */
	// Complejidad temporal: O(numEle) 
	private Celda<E> celdaPos(int pos) {
		Celda<E> aux;
		if (pos < tamanho() / 2) {
			aux = principio;
			for (int i = 0; i <= pos; i++) {
				aux = aux.siguiente;
			}
		} else {
			aux = fin;
			for (int i = tamanho() - 1; i >= pos; i--) {
				aux = aux.anterior;
			}
		}
		return aux;
	}

	/**
	 * Clase iteradora.
	 * 
	 * @param <E> tipo de los elementos almacenados en la lista
	 */
	private static class IteradorLista<E> implements IIteradorSimple<E> {
		private ListaDobleEnlace<E> lista; // lista a ser iterada
		private Celda<E> ultRet; // celda del ultimo elemento retornado
		// implementa la clase iteradora tomando como referencia
		// la diapositiva 60 de los apuntes. Las diapositivas 54 a 59 pueden servir
		// de ayuda aunque estan referidas a listas simplemente enlazadas.

		/**
		 * Constructor del iterador. El iterador comienza al principio
		 * de la lista (justo antes del primer elemento).
		 * @param lista lista a ser iterada
		 */
		public IteradorLista(ListaDobleEnlace<E> lista) {
			this.lista = lista;
			ultRet = lista.principio;
		}

		/**
		 * Indica si hay mas elementos (no se ha llegado al final de la lista).
		 * @return true si todavia no se ha llegado al final de la lista
		 */
		@Override
		public boolean haySiguiente() {
			if (ultRet.siguiente != lista.fin) {
				return true;
			}
			return false;
		}
		//complejidad o(1)

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
			ultRet = ultRet.siguiente;
			return ultRet.contenido;
		}
		//complejidad o(1)

		/**
		 * Inserta un elemento en la posicion del iterador. El iterador se situa despues
		 * del elemento anhadido.
		 * @param e elemento a insertar
		 */
		@Override
		public void anhade(E e) {
			Celda<E> nueva = new Celda<E>(e);
			nueva.anterior = ultRet;
			nueva.siguiente = ultRet.siguiente;
			ultRet.siguiente.anterior = nueva;
			ultRet.siguiente = nueva;
			lista.numEle++;
			ultRet = ultRet.siguiente;
		}
		//complejidad o(1)

		/**
		 * Reemplaza el ultimo elemento accedido por el iterador.
		 * Se considera ultimo elemento accedido a el ultimo retornado por
		 * siguiente() o el ultimo anhadido por anhade(), lo que haya
		 * ocurrido mas recientemente.
		 * @param e elemento utilizado para reemplazar
		 * @throws NoSuchElementException si el iterador no ha retornado ningun elemento
		 * previamente ni se ha llamado a anhade().
		 */
		@Override
		public void asigna(E e) throws NoSuchElementException {
			if (ultRet == lista.principio || ultRet == lista.fin) {
				throw new NoSuchElementException();
			}
			ultRet.contenido = e;
		}
	}
	//complejidad o(1)

	/**
	 * Retorna un iterador que permite recorrer los elementos en el orden que
	 * ocupan en la lista.  El iterador comienza situado al principio de la lista.
	 * @return iterador que permite recorrer los elementos de la lista
	 */
	// Complejidad temporal: O(1)
	@Override
	public IIteradorSimple<E> iterador() {
		return new IteradorLista<E>(this);
	}

}
