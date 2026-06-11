package c2_es_ascendiente_en_heap;

import java.util.Arrays;

/**
 * Implementación de una cola de prioridad generica utilizando un array.
 * Se trata de una estructura acotada, con una capacida maxima indicada
 * en el constructor
 *
 * @param <E> tipo de los elementos almacenados en la cola
 * 
 * @author Estructuras de Datos (UC) y <TODO: nombre alumno>
 * @version dic-2020
 */
public class ColaPrioMonticulo<E extends Comparable<E>> implements IColaPrioridad<E> {
	// Atributos de la clase
	private E[] elementos;
	private int numEle;
	
	/**
	 * Crea una cola con la capacidad maxima indicada.
	 * @param capacidad capacidad maxima de la cola.
	 */
	// Complejidad temporal: O(capacidad)
	@SuppressWarnings("unchecked")
	public ColaPrioMonticulo(int capacidad) {
		// crea un array de elementos comparables
		// (el compilador pone un "warning", pero esta bien)
		elementos = (E[]) new Comparable[capacidad + 1]; 
		numEle = 0;
	}
	
	/**
	 * Retorna si un elemento es ascendiente propio de otro.
	 * @param ele elemento buscado.
	 * @param ascendiente elemento que se quiere saber si es ascendiente.
	 * @return verdadero si 'ascendiente' es ascendidente propio de 'ele' y false en
	 * caso contrario (incluyendo el caso de que 'ele' no este en el monticulo).
	 */
	public boolean esAscendientePropio(E ele, E ascendiente) {
		// TODO
		return false;
	}
	
	/**
	 * Anhade el elemento dado a la cola en la posicion
	 * correspondiente a su prioridad.
	 * @param e elemento a anhadir.
	 * @throws UnsupportedOperationException si la cola esta llena.
	 */
	// Complejidad temporal: O(log numEle)
	@Override
	public void encolaConPrioridad(E e) throws UnsupportedOperationException {
		
		if (numEle == elementos.length - 1) {
			throw new UnsupportedOperationException(); // cola llena
		}
		
		// empezamos en la primera posicion libre del arbol
		int posHueco = numEle + 1;
		
		// reflota el hueco
		while (posHueco > 1 && e.compareTo(elementos[posPadre(posHueco)]) < 0) {
			elementos[posHueco] = elementos[posPadre(posHueco)];
			posHueco = posPadre(posHueco);		
		}
		
		numEle++;
		// copia el elemento en la posicion final del hueco
		elementos[posHueco] = e;
	}

	/**
	 * Elimina y retorna el elemento mas prioritario.
	 * @return elemento  mas prioritario o null si la cola
	 * esta vacia
	 */
	// Complejidad temporal: O(log numEle)
	@Override
	public E desencolaMasPrioritario() {
		if (numEle == 0) {
			return null;
		}
		
		E eleMaxPrio = elementos[1];
		E x = elementos[numEle];  // elemento a hundir
		numEle--;
		int posHueco = 1; // posición del hueco que vamos a ir hundiendo
		boolean verificaMinimalidad = false;
		
		// hunde el hueco
		while (!verificaMinimalidad && posHijoIzq(posHueco) <= numEle) {
			// tiene al menos un hijo
			
			// busca el hijo menor
			int posHijoMenor = posHijoIzq(posHueco); // supongo que el menor es el hijo izquierdo
			if (posHijoDer(posHueco) <= numEle &&
					elementos[posHijoDer(posHueco)].compareTo(elementos[posHijoMenor]) < 0) {
				// tiene dos hijos y el menor es el derecho
				posHijoMenor = posHijoDer(posHueco);
			}
			
			// se verifica la condición de minimalidad?
			if (x.compareTo(elementos[posHijoMenor]) > 0) {
				// no se verifica, hay que hundir el hueco
				elementos[posHueco] = elementos[posHijoMenor];
				posHueco = posHijoMenor;
			} else {
				// se verifica minimalidad
				verificaMinimalidad = true;
			}
		}
		
		elementos[posHueco] = x;    // escribimos elemento en el hueco
		elementos[numEle + 1] = null; // permitimos el acceso del recolector de basura
		return eleMaxPrio;
	}

	/**
	 * Retorna (pero no elimina) el elemento  mas prioritario.
	 * @return elemento  mas prioritario o null si la cola
	 * esta vacia
	 */
	// Complejidad temporal: O(1)
	@Override
	public E masPrioritario() {
		if (numEle == 0) {
			return null;
		}
		
		return elementos[1];
	}
	 
	/**
	 * Vacia la cola (pasa a tener tamanho 0).
	 */
	// Complejidad temporal: O(numEle)
	@Override
	public void haceVacia() {
		// Ponemos a null para que pueda entrar el recolector de basura
		for (int pos = 1; pos <= numEle; pos++) {
			elementos[pos] = null;
		}
		numEle = 0;
	}

	/**
	 * Retorna el tamaño de la cola (num. elementos)
	 * @return tamaño de la cola
	 */
	// Complejidad temporal: O(1)
	@Override
	public int tamanho() {
		return numEle;
	}
	
	/**
	 * Retorna la posicion del padre de la posicion dada.
	 * @param pos posicion
	 * @return posicion del padre
	 */
	// Complejidad temporal: O(1)
	private int posPadre(int pos) {
		return pos / 2;
	}
	
	/**
	 * Retorna la posicion del hijo izquierdo de la posicion dada.
	 * @param pos posicion
	 * @return posicion del hijo izquierdo
	 */
	// Complejidad temporal: O(1)
	private int posHijoIzq(int pos) {
		return pos * 2;
	}
	
	/**
	 * Retorna la posicion del hijo derecho de la posicion dada.
	 * @param pos posicion
	 * @return posicion del hijo derecho
	 */
	// Complejidad temporal: O(1)
	private int posHijoDer(int pos) {
		return (pos * 2) + 1;
	}

	// Complejidad temporal: O(numEle)
	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOfRange(elementos, 1, numEle + 1)) + " numEle:" + numEle;
	}
}
