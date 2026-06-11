package pract03;

/**
 * Implementación de una lista generica utilizando celdas doblemente enlazadas
 * con celdas de cabecera y cola y punteros a principio y fin.
 * No implementa el iterador.
 * 
 * @param <E> tipo de los elementos almacenados en la lista
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version oct-2020
 */
public class ListaDobleEnlaceNoIter<E> implements IListaNoIter<E> {

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
	public ListaDobleEnlaceNoIter() {
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
	public ListaDobleEnlaceNoIter(int capacidad) {
		this(); // llama al constructor sin parametros ListaDobleEnlaceNoIter()
	}

	@Override
	public void anhade(int pos, E e) throws IndexOutOfBoundsException {
		if (pos < 0 || pos > numEle) {
			throw new IndexOutOfBoundsException();
		}
		Celda<E> aux = celdaPos(pos);
		Celda<E> nueva = new Celda<E>(e);
		nueva.siguiente = aux;
		nueva.anterior = aux.anterior;
		aux.anterior.siguiente = nueva;
		aux.anterior = nueva;
		numEle++;
	}
	//complejidad: o(1) en la primera y ultima posicion y o(n) en cualquier otra

	@Override
	public E elimina(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= numEle) {
			throw new IndexOutOfBoundsException();
		}
		Celda<E> aux = celdaPos(pos);
		Celda<E> anterior;
		Celda<E> siguiente;
		anterior = aux.anterior;
		siguiente = aux.siguiente;
		anterior.siguiente = siguiente;
		siguiente.anterior = anterior;
		numEle--;
		return aux.contenido;
	}
	//complejidad: o(1) en la primera y ultima posicion y o(n) en cualquier otra

	@Override
	public E obtenElemento(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= numEle) {
			throw new IndexOutOfBoundsException();
		}

		Celda<E> aux = celdaPos(pos);

		return aux.contenido;
	}
	//complejidad: o(1) en la primera y ultima posicion y o(n) en cualquier otra

	@Override
	public int tamanho() {
		return numEle;
	}
	//complejidad: o(1)

	@Override
	public int busca(E e) {
		Celda<E> aux = principio.siguiente;
		for (int i = 0; i < numEle; i++) {
			if (aux.contenido != null && aux.contenido.equals(e)) {
				return i;
			}
			aux = aux.siguiente;
		}
		return -1;
	}
	//complejidad: o(n)

	@Override
	public void haceVacia() {
		principio.siguiente = fin;
		fin.anterior = principio;
		numEle = 0;
	}
	//complejidad: o(1)

	/**
	 * Metodo auxiliar para obtener la celda de una posicion.
	 * @param pos la posicion de la celda
	 * @return la celda de la posicion
	 */
	private Celda<E> celdaPos(int pos) {
		Celda<E> aux;

		if (pos < numEle / 2) {
			aux = principio;
			for (int i = 0; i <= pos; i++) {
				aux = aux.siguiente;
			}
		} else {
			aux = fin;
			for (int i = numEle - 1; i >= pos; i--) {
				aux = aux.anterior;
			}
		}

		return aux;
	}
	//complejidad: o(1) en la primera y ultima posicion y o(n) en cualquier otra
}
