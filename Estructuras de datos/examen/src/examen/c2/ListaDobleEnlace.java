package examen.c2;

import java.util.NoSuchElementException;

import examen.ilista.IIteradorSimple;
import examen.ilista.ILista;

/**
 * Implementación de una lista generica utilizando celdas doblemente enlazadas
 * con celdas de cabecera y cola y punteros a principio y fin.
 * Implementa un iterador sencillo.
 * 
 * @param <E> tipo de los elementos almacenados en la lista
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version oct-2020
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
	private Celda<E> principio;

	// referencia a la ultima celda de la lista (celda de cola)
	private Celda<E> fin;

	// numero de elementos en la lista
	private int numEle;

	/**
	 * Construye una lista vacia.
	 */
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
	public ListaDobleEnlace(int capacidad) {
		this(); // llama al constructor sin parametros ListaDobleEnlaceNoIter()
	}

	/**
	 * Mueve al final de la 'listaDestino' la seccion de la lista actual que finaliza en
	 * la primera ocurrencia de 'eleFinSeccion' y que esta formada por 'largoSeccion'
	 * elementos.
	 * @param eleFinSeccion elemento final de la seccion a mover
	 * @param largoSeccion numero de elementos que forman la seccion.
	 * @param listaDestino lista a cuyo final hay que mover la seccion.
	 */
	public void mueveSeccion(E eleFinSeccion, int largoSeccion,
			ListaDobleEnlace<E> listaDestino) {
		Celda<E> finSeccion = principio;
		Celda<E> inicioSeccion;
		boolean encontrado = false;
		while (finSeccion != fin && !encontrado) {
			finSeccion = finSeccion.siguiente;
			if (finSeccion.contenido.equals(eleFinSeccion)) {
				encontrado = true;
			}
		}

		inicioSeccion = finSeccion;

		for (int i = 0; i < largoSeccion - 1; i++) {
			inicioSeccion = inicioSeccion.anterior;
		}

		finSeccion.siguiente.anterior = inicioSeccion.anterior;
		inicioSeccion.anterior.siguiente = fin.siguiente;

		inicioSeccion.anterior = listaDestino.fin.anterior;
		listaDestino.fin.anterior.siguiente = inicioSeccion;

		finSeccion.siguiente = listaDestino.fin;
		listaDestino.fin.anterior = finSeccion;

		numEle -= largoSeccion;
		listaDestino.numEle += largoSeccion;
	}
	//En el peor caso la complejidad temporal es O(n)

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

		Celda<E> nuevaCelda = new Celda<E>(e);
		Celda<E> posAnt = celdaPos(pos - 1);
		nuevaCelda.siguiente = posAnt.siguiente;
		nuevaCelda.anterior = posAnt;
		posAnt.siguiente.anterior = nuevaCelda;
		posAnt.siguiente = nuevaCelda;
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

		Celda<E> posElim = celdaPos(pos);
		posElim.anterior.siguiente = posElim.siguiente;
		posElim.siguiente.anterior = posElim.anterior;
		numEle--;

		return posElim.contenido;
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
	 * del elemento buscado en la lista.
	 * Utiliza el método equals del elemento.
	 * @param e elemento buscado
	 * @return posicion de la primera ocurrencia
	 * del elemento en la lista o -1 en caso de que 
	 * el elemento no este en la lista
	 */
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
	private Celda<E> celdaPos(int pos) {
		Celda<E> aux = principio;
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
		private Celda<E> previo; // celda anterior a la que debe retornar siguiente()

		/**
		 * Constructor del iterador. El iterador comienza al principio
		 * de la lista (justo antes del primer elemento).
		 * @param lista lista a ser iterada
		 */
		public IteradorLista(ListaDobleEnlace<E> lista) {
			this.lista = lista;
			previo = lista.principio;
		}

		/**
		 * Indica si hay más elementos (no se ha llegado al final de la lista).
		 * @return true si todavia no se ha llegado al final de la lista
		 */
		@Override
		public boolean haySiguiente() {
			return previo.siguiente != lista.fin;
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
			nuevaCelda.siguiente = previo.siguiente;
			nuevaCelda.anterior = previo;
			previo.siguiente.anterior = nuevaCelda;
			previo.siguiente = nuevaCelda;

			// incrementa el contador de elementos
			lista.numEle++;

			// una llamada a siguiente despues de anhadir debe retornar
			// el mismo elemento que habria retornado si no se hubiera
			// llamado a anhade
			previo = nuevaCelda; 
		}

		/**
		 * Reemplaza el último elemento retornado por el iterador.
		 * @param e elemento utilizado para reemplazar
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

	@Override
	public String toString() {
		String str = "[";
		for (int i = 0; i < tamanho(); i++) {
			try {
				str += obtenElemento(i);
			} catch (Exception e) {
				// obtenElemento ha lanzado una excepcion
				str += "error";
			}
			if (i < tamanho() - 1) {
				str = str + ", ";
			}
		}
		return str + "]";
	}

}
