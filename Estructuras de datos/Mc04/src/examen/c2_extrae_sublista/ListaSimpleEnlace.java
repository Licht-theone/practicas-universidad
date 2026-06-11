package examen.c2_extrae_sublista;

import java.util.NoSuchElementException;


/**
 * Implementación de una lista generica utilizando celdas simplemente enlazadas
 * con celda de cabecera y puntero a fin.
 * Implementa un iterador sencillo.
 * 
 * @param <E> tipo de los elementos almacenados en la lista
 * 
 * @author Estructuras de Datos (UC) 
 * @version sep-2021
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

	// referencia a la ultima celda de la lista
	private Celda<E> fin;

	// numero de elementos en la lista
	private int numEle;

	/**
	 * Construye una lista vacia.
	 */
	public ListaSimpleEnlace() {
		principio = new Celda<E>(null); // crea la celda de cabecera

		// la lista esta vacia, luego la celda de cabecera es, a la vez, la ultima
		fin = principio;

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
	public ListaSimpleEnlace(int capacidad) {
		this(); // llama al constructor sin parametros ListaSimpleEnlaceNoIter()
	}

	/**
	 * Extrae a una nueva lista los elementos comprendidos entre la
	 * primera ocurrencia del elemento corteIni y la primera ocurrencia
	 * del elemento corteFin (ambos elementos de corte incluidos).
	 * @param corteIni comienzo de la sublista.
	 * @param corteFin fin de la sublista
	 * @return nueva lista con la sublista entre corteIni y corteFin o una
	 * lista vacia si corteIni o corteFin no estan en la lista.
	 */
	public ListaSimpleEnlace<E> extraeSublista(E corteIni, E corteFin) {
		Celda<E> aux = principio;
		ListaSimpleEnlace<E> sublis = new ListaSimpleEnlace<E>();

		while (aux != fin && !aux.siguiente.contenido.equals(corteIni)) {
			aux = aux.siguiente;
		}

		if (aux != fin) {
			Celda<E> temp = aux.siguiente;
			int c = 2;
			while (temp != fin && !temp.siguiente.contenido.equals(corteFin)) {
				temp = temp.siguiente;
				c++;
			}

			if (temp != fin) {
				sublis.principio.siguiente = aux.siguiente;
				sublis.fin = temp.siguiente;
				sublis.numEle = c;
				aux.siguiente = temp.siguiente.siguiente;
				sublis.fin.siguiente = null;
				numEle -= c;
			}
		}

		return sublis;
		// Implementar manejando los punteros directamente (no debera
		// llamar a ninguno de los otros metodos de la lista ni del
		// iterador salvo el constructor).

	}

	/**
	 * menos elegante
	 * Celda<E> aux;
		ListaSimpleEnlace<E> sublis = new ListaSimpleEnlace<E>();
		int cini = busca(corteIni);
		int cfin = busca(corteFin);

		if (cini == -1 || cfin == -1) {
			return sublis;
		}

		if (cfin > cini) {
			return sublis;
		}

		aux = buscaPos(cini);

		for (int i = cini; i <= cfin; i++) {
			sublis.anhade(sublis.tamanho(), aux.contenido);
			aux = aux.siguiente;
		}

	 */

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

		if (pos == numEle) {
			// caso especial: anhadir en la ultima posicion de la lista
			fin.siguiente = nuevaCelda;
			fin = nuevaCelda;

		} else {
			// caso normal: anhadir una posicion de la lista que no es la ultima
			Celda<E> posAnt = buscaPos(pos - 1);
			nuevaCelda.siguiente = posAnt.siguiente;
			posAnt.siguiente = nuevaCelda;
		}

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
		if (pos < 0 || pos >= numEle) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		Celda<E> posAnt = buscaPos(pos - 1);
		E tmp = posAnt.siguiente.contenido;
		posAnt.siguiente = posAnt.siguiente.siguiente;

		if (pos == numEle - 1) {
			// si se ha eliminado el ultimo hay que cambiar fin
			fin = posAnt;
		}

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

		return buscaPos(pos).contenido;
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
		fin = principio;
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
	private Celda<E> buscaPos(int pos) {
		Celda<E> aux = principio;
		for (int i = 0; i <= pos; i++) {
			aux = aux.siguiente;
		}
		return aux;
	}

	/**
	 * Clase iteradora.
	 * 
	 * @param <E> tipo de los elementos almacenados en la lista
	 */
	private static class IteradorLista<E> implements IIteradorSimple<E> {
		private ListaSimpleEnlace<E> lista; // lista a ser iterada
		private Celda<E> ultRet; // celda del ultimo elemento retornado

		/**
		 * Constructor del iterador. El iterador comienza al principio
		 * de la lista (justo antes del primer elemento).
		 * @param lista lista a ser iterada
		 */
		// Complejidad temporal: O(1)
		public IteradorLista(ListaSimpleEnlace<E> lista) {
			this.lista = lista;
			ultRet = lista.principio;
		}

		/**
		 * Indica si hay más elementos (no se ha llegado al final de la lista).
		 * @return true si todavia no se ha llegado al final de la lista
		 */
		// Complejidad temporal: O(1)
		@Override
		public boolean haySiguiente() {
			return ultRet != lista.fin;
		}

		/**
		 * Retorna el siguiente elemento en la iteracion y avanza el iterador.
		 * @return el siguiente elemento
		 * @throws NoSuchElementException si se ha llegado al final de la lista
		 */
		// Complejidad temporal: O(1)
		@Override
		public E siguiente() {
			if (!haySiguiente()) {
				throw new NoSuchElementException("No hay siguiente elemento");
			}

			ultRet = ultRet.siguiente;
			return ultRet.contenido;
		}

		/**
		 * Inserta un elemento en la posición del iterador.
		 * @param e elemento a insertar
		 */
		// Complejidad temporal: O(1)
		@Override
		public void anhade(E e) {
			Celda<E> nuevaCelda = new Celda<E>(e);
			nuevaCelda.siguiente = ultRet.siguiente;
			ultRet.siguiente = nuevaCelda;

			// si se anhade en la ultima posicion, actualiza fin
			if (ultRet == lista.fin) {
				lista.fin = nuevaCelda;
			}

			// incrementa el contador de elementos
			lista.numEle++;

			// una llamada a siguiente despues de anhadir debe retornar
			// el mismo elemento que habria retornado si no se hubiera
			// llamado a anhade
			ultRet = nuevaCelda; 
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
		// Complejidad temporal: O(1)
		@Override
		public void asigna(E e) {
			if (ultRet == lista.principio) {
				throw new NoSuchElementException("Elemento inexistente");
			}
			ultRet.contenido = e;
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
		return new IteradorLista<E>(this);
	}

}
