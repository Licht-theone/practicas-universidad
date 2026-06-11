package pract06.pila_enlaces;

/**
 * Clase que implementa una pila simple enlace.
 * 
 * @param <E> tipo de los elementos almacenados en la pila
 * 
 * @author Aaron Alegria Puente
 * @version nov-2020
 */
public class PilaSimpleEnlace<E> implements IPila<E> {
	private Celda<E> cima;
	private int numEle;
	
	@SuppressWarnings("unused")
	/**
	 * Clase celda para cada elemento de la pila.
	 * @param <E>
	 */
	private static class Celda<E> {
		private E contenido;
		private Celda<E> siguiente;
		
		public Celda(E cont) {
			contenido = cont;
			siguiente = null;
		}
		
		
		public E contenido() {
			return contenido;
		}
		
		public Celda<E> siguiente() {
			return siguiente;
		}
	}
	
	/**
	 * Constructor de la pila.
	 */
	public PilaSimpleEnlace() {
		cima = null; //O(1)
		numEle = 0;
	}

	@Override
	public void apila(E elem) {
		Celda<E> nuevaCima = new Celda<E>(elem); //O(1)
		nuevaCima.siguiente = cima;
		cima = nuevaCima;
		numEle++;
		
	}

	@Override
	public E desapila() {
		if (cima == null) { //O(1)
			return null;
		}
		E elem = cima.contenido;
		cima = cima.siguiente;
		numEle--;
		return elem;
	}

	@Override
	public E cima() {
		if (cima == null) {
			return null;
		}
		return cima.contenido;
	}

	@Override
	public void haceVacia() { //O(1)
		cima = null;
		numEle = 0;
		
	}

	@Override
	public int tamanho() {
		return numEle;
	}
	
	/**
	 * metodo to string de la pila.
	 * @return el string de elementos
	 */
	public String toString() {
		Celda<E> aux = cima;
		String s = "";
		while (aux != null) {
			s += aux.contenido + " ";
			aux = aux.siguiente;
		}
		return s;
	}

}
