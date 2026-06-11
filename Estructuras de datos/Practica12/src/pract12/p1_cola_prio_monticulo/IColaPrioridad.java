package pract12.p1_cola_prio_monticulo;

/**
 * TDA Cola de Prioridad.
 * 
 * @param <E> tipo de los elementos almacenados en la cola
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2024
 */
public interface IColaPrioridad<E> {
	
	/**
	 * Anhade el elemento dado a la cola en la posicion
	 * correspondiente a su prioridad.
	 * La cola es acotada, por lo que no es posible encolar si se
	 * ha alcanzado la capacidad.
	 * @param e elemento a anhadir
	 * @throws UnsupportedOperationException si la cola esta llena.
	 */
	public void encolaConPrioridad(E e) throws UnsupportedOperationException;

	/**
	 * Elimina y retorna el elemento mas prioritario.
	 * @return elemento mas prioritario o null si la cola esta vacia.
	 */
	public E desencolaMasPrioritario();

	/**
	 * Retorna (pero no elimina) el elemento  mas prioritario.
	 * @return elemento  mas prioritario o null si la cola esta vacia.
	 */
	public E masPrioritario();
	 
	/**
	 * Vacia la cola (pasa a tener 0 elementos).
	 */
	public void haceVacia();

	/**
	 * Retorna el numero de elementos en la cola.
	 * @return el numero de elementos en la cola.
	 */
	public int tamanho();

}
