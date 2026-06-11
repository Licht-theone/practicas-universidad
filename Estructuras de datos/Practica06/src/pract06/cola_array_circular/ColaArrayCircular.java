package pract06.cola_array_circular;



/**
 * Clase que implementa una cola circular.
 * 
 * @param <E> tipo de los elementos almacenados en la cola
 * 
 * @author Aaron Alegria Puente
 * @version nov-2020
 */
public class ColaArrayCircular<E> implements ICola<E> {
	private int numEle;
	private int frente;
	private int fin;
	private E[] elementos;
	private static final int TAMANHO_PREDET = 10;
	
	/**
	 * constructor.
	 * @param capacidad de la cola
	 */
	@SuppressWarnings("unchecked")
	public ColaArrayCircular(int capacidad) {
		elementos = (E[]) new Object[capacidad];
		frente = 0;
		fin = elementos.length - 1;
		numEle = 0;
	}

	/**
	 * constructor generico 10 elementos.
	 */
	public ColaArrayCircular() {
		this(TAMANHO_PREDET);
	}

	@Override
	public void encola(E e) { //O(1) todos los metodos menos hace vacia
		if (numEle >= elementos.length) {
			throw new UnsupportedOperationException();
		}
		fin = (fin + 1) % elementos.length;
		elementos[fin] = e;
		numEle++;
	}

	@Override
	public E desencola() {
		if (numEle == 0) {
			return null;
		}
		E elem = elementos[frente];
		elementos[frente] = null;
		frente = (frente + 1) % elementos.length;
		numEle--;
		return elem;
	}

	@Override
	public E frente() {

		return elementos[frente];
	}

	@Override
	public void haceVacia() {
		int pos = frente; 
		for (int i = 0; i < numEle; i++) { //O(n)
			elementos[pos] = null;
			pos = (pos + 1) % elementos.length;
		}
		frente = 0;
		fin = elementos.length - 1;
		numEle = 0;
	}

	@Override
	public int tamanho() {

		return numEle;
	}


	/**
	 * metodo to string.
	 * @return string de elementos
	 */
	public String toString() {
		int pos = frente; 
		String s = "";
		for (int i = 0; i < numEle; i++) { //O(n)
			s += elementos[pos] + " ";
			pos = (pos + 1) % elementos.length;
		}

		return s;
	}

}
