package pract05.c3;

import java.util.NoSuchElementException;

import pract05.ilista.IIteradorSimple;
import pract05.ilista.ILista;

/**
 * Implementación de una lista generica utilizando celdas simplemente enlazadas
 * con celda de cabecera y sin puntero a fin.
 * 
 * @param <E> tipo de los elementos almacenados en la lista
 * 
 * @author Estructuras de Datos (UC) y Lara Hernández Marcote
 * @version oct-2021
 */
public class ListaSimpleEnlace<E> implements ILista<E> {

	// clase privada que define la celda
	private static class Celda<E> {
		private E contenido;
		private Celda<E> siguiente = null;

		public Celda(E cont) {
			contenido = cont;    
		}
	}

	// referencia a la primera celda de la lista (celda de cabecera)
	private Celda<E> principio;

	// numero de elementos en la lista
	private int numEle;

	/**
	 * Construye una lista vacia.
	 */ 
	public ListaSimpleEnlace() {
		principio = new Celda<E>(null); // crea la celda de cabecera

		// la lista comienza con 0 elementos
		numEle = 0;
	}

	/**
	 * Construye una lista vacia.
	 * @param capacidad capacidad inicial de la lista (parametro
	 * no usado).
	 * (Este constructor se incluye para que existan los mismos
	 * constructores que en el caso de ListaArray)
	 */
	public ListaSimpleEnlace(int capacidad) {
		this(); // llama al constructor sin parametros ListaSimpleEnlace()
	}

	/**
	 * Reemplaza cada ocurrencia de la pareja de elementos (primero, segundo)
	 * por el elemento de reemplazo.
	 * @param primero primer elemento de la pareja a reemplazar.
	 * @param segundo segundo elemento de la pareja a reemplazar.
	 * @param reemplazo elemento por el que reemplazar la pareja.
	 */
	// XXX cuestion 3
	public void reemplazaParejaElementos(E primero, E segundo,
			E reemplazo) {
		Celda<E> x = principio.siguiente;
		while (x != null && x.siguiente != null) {
			if (x.contenido.equals(primero)) {
				if (x.siguiente.contenido.equals(segundo)) {
					x.contenido = reemplazo;
					x.siguiente = x.siguiente.siguiente;
					numEle--;
				}
			}
			x = x.siguiente;
		}
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
		if (pos < 0 || pos > numEle) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		Celda<E> nuevaCelda = new Celda<E>(e);

		Celda<E> posAnt = celdaPos(pos - 1);
		nuevaCelda.siguiente = posAnt.siguiente;
		posAnt.siguiente = nuevaCelda;

		numEle++;
	}

	/**
	 * Retorna una referencia a la celda en la posicion pos.
	 * {Pre: pos >= -1 y pos < numEle}
	 * @param pos posicion de la celda buscada
	 * @return referencia a la celda en la posicion pos
	 */ 
	private Celda<E> celdaPos(int pos) {
		Celda<E> aux = principio;
		for (int i = 0; i <= pos; i++) {
			aux = aux.siguiente;
		}
		return aux;
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
		if (pos < 0 || pos >= numEle) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		Celda<E> posAnt = celdaPos(pos - 1);
		E tmp = posAnt.siguiente.contenido;
		posAnt.siguiente = posAnt.siguiente.siguiente;

		numEle--;

		return tmp;
	}

	/**
	 * Retorna el elemento que ocupa la posicion indicada.
	 * @param pos posicion del elemento a retornar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */
	@Override
	public E obtenElemento(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= tamanho()) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		return celdaPos(pos).contenido;
	}

	/**
	 * Retorna la posicion de la primera ocurrencia
	 * del elemento en la lista.
	 * Utiliza el método equals del elemento.
	 * @param e elemento buscado
	 * @return posicion de la primera ocurrencia
	 * del elemento en la lista
	 */
	@Override
	public int busca(E e) {
		Celda<E> aux = principio.siguiente;
		int pos = 0;
		while (aux != null) {
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
	@Override
	public void haceVacia() {
		principio.siguiente = null;
		numEle = 0;
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
	 * Clase iteradora.
	 * 
	 * @param <E> tipo de los elementos almacenados en la lista
	 */
	private static class IteradorLista<E> implements IIteradorSimple<E> {
		private ListaSimpleEnlace<E> lista; // lista a ser iterada
		private Celda<E> previo;            // celda anterior a la que retornar con siguiente

		/**
		 * Constructor del iterador. El iterador comienza al principio
		 * de la lista (justo antes del primer elemento)
		 * @param lista lista a ser iterada
		 */
		public IteradorLista(ListaSimpleEnlace<E> lista) {
			this.lista = lista;
			previo = lista.principio;
		}

		/**
		 * Indica si hay más elementos (no se ha llegado al final de la lista).
		 * @return true si todavia no se ha llegado al final de la lista
		 */
		@Override
		public boolean haySiguiente() {
			return previo.siguiente != null;
		}

		/**
		 * Retorna el siguiente elemento en la iteracion y avanza el iterador.
		 * @return el siguiente elemento
		 * @throws NoSuchElementException si se ha llegado al final de la lista
		 */
		@Override
		public E siguiente() {
			if (!haySiguiente()) {
				throw new NoSuchElementException("No hay siguiente elemento");
			}

			previo = previo.siguiente;
			return previo.contenido;
		}

		/**
		 * Inserta un elemento en la posición del iterador.
		 * @param e elemento a insertar
		 */
		@Override
		public void anhade(E e) {

			Celda<E> nuevaCelda = new Celda<E>(e);

			// caso habitual
			nuevaCelda.siguiente = previo.siguiente;
			previo.siguiente = nuevaCelda;

			// incrementa el contador de elementos
			lista.numEle++;

			// una llamada a siguiente despues de anhadir debe retornar
			// el mismo elemento que habria retornado si no se hubiera
			// llamado a anhade
			previo = nuevaCelda; 
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
		public void asigna(E e) {
			if (previo == lista.principio) {
				throw new NoSuchElementException("Elemento inexistente");
			}
			previo.contenido = e;
		}	
	}


	/**
	 * Retorna un iterador que permite recorrer los elementos en el orden que
	 * ocupan en la lista.
	 * @return iterador que permite recorrer los elementos de la lista
	 */
	@Override
	public IIteradorSimple<E> iterador() {
		return new IteradorLista<E>(this);
	}


}
