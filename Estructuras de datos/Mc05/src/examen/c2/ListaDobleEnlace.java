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
 * @author Estructuras de Datos (UC) y Javier Sierra
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

	public int cuantasParejas(E a, E b) {
		int cont = 0;
		Celda <E> aux = principio.siguiente;
		while (aux != fin) {
			if (aux.contenido != null && aux.contenido.equals(a)) {
				if (aux.siguiente != null && aux.siguiente.contenido.equals(b)) {
					cont++;
				}
			}
			aux = aux.siguiente;
		}
		return cont;
	}



	/**
	 * Mueve al final de la lista actual cada uno los elementos de la 'otraLista'
	 * que estan justo despues de un elemento igual a 'eleAntes'. 
	 * @param eleAntes elemento anterior a los elementos a mover.
	 * @param otraLista lista desde la que se mueven los elementos.
	 */
	public void mueveSiguientes(E eleAntes, ListaDobleEnlace<E> otraLista) {
		Celda <E> aux = otraLista.principio.siguiente;
		while (aux.siguiente != otraLista.fin) {
			if (aux.contenido.equals(eleAntes)) {
				Celda <E> temp = aux.siguiente;
				temp.siguiente.anterior = aux;
				aux.siguiente = temp.siguiente;
				otraLista.numEle--;
				numEle++;
				temp.anterior = fin.anterior;
				fin.anterior.siguiente = temp;
				temp.siguiente = fin;
				fin.anterior = temp;
			}
			aux = aux.siguiente;
		}

	}
	
	public void sustituyeSeccion(int ini, int fin, ListaDobleEnlace<E> listasust) {
		Celda<E> aux = principio.siguiente;
		for (int i = 0; i < ini; i++) {
			aux = aux.siguiente;
		}
		Celda<E> tmp = principio.siguiente;
		//igual hay q hacer un for inverso en ambos casos
		for (int i = ini; i <= fin; i++) {
			tmp = tmp.siguiente;
		}
		numEle = listasust.numEle - (fin + 1 - ini);
		listasust.numEle = 0;
		listasust.principio.siguiente.anterior = aux.anterior;
		listasust.fin.anterior.siguiente = tmp;
		aux.anterior.siguiente = listasust.principio.siguiente;
		tmp.anterior = listasust.fin.anterior;
		listasust.principio.siguiente = listasust.fin;
		listasust.fin.anterior = listasust.principio;
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

	public static <E> void eliminaRepetidos(ListaDobleEnlace<E> lista) {
		ListaDobleEnlace<E> aux = new ListaDobleEnlace<E>();
		IIteradorSimple<E> iter = lista.iterador();
		while (iter.haySiguiente()) {
			boolean encontrado = false;
			E elem = iter.siguiente();
			IIteradorSimple<E> iteraux = aux.iterador();
			while (iteraux.haySiguiente() && !encontrado) {
				E elem2 = iteraux.siguiente();
				if (elem.equals(elem2)) {
					encontrado = true;
				}
			}
			if (!encontrado) {
				aux.anhade(aux.numEle, elem);
			}
		}
		lista.haceVacia();
		for (int i = 0; i < aux.tamanho(); i++) {
			lista.anhade(i, aux.obtenElemento(i));
		}
	}
	/* sin iteradores
	 * ListaDobleEnlace<E> aux = new ListaDobleEnlace<E>();
	 * Celda <E> temp = lista.principio.sig
	 * while (temp != lista.fin) {
	 * 	Celda <E> temp2 = aux.principio.siguiente
	 * 	encontado = false
	 * 		while (temp2 != aux.fin) {
	 * 			if (temp.contenido.equals(temp2.contenido) && !encontrado) {
	 * 				encontrado = true
	 * 				}
	 *          temp2 = temp2.siguiente;
	 * 		}
	 * 	
	 *  if(!encontrado){
	 *  	aux.anhade(aux.numEle, temp.contenido);
	 *  }
	 *  temp = temp.siguiente;
	 * }
	 * lista = aux
	 */

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
