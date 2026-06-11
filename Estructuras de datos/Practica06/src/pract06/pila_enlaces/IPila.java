package pract06.pila_enlaces;

/**
 * TDA Pila.
 * 
 * @param <E> tipo de los elementos almacenados en la pila
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2020
 */
public interface IPila<E> {
	
	/**
	 * Anhade el elemento en la cima de la pila.
	 *
	 * @param e elemento a anhadir.
	 */
	public void apila(E e);
	
	/**
	 * Elimina y retorna el elemento que ocupaba la
	 * la cima. La cima pasa al siguiente elemento
	 * @return elemento que ocupaba la cima o null si la
	 * pila estaba vacia
	 */
	public E desapila();
	
	/**
	 * Retorna (pero no elimina) el elemento que
	 * ocupa la cima. La cima no cambia.
	 * @return elemento que ocupa la cima o null si la
	 * pila esta vacia
	 */
	public E cima();
	
	/**
	 * Vacia la pila (pasa a tener tamanho 0).
	 */
	public void haceVacia();
	
	/**
	 * Retorna el tamanho de la pila (num. elementos)
	 * @return tamanho de la pila
	 */
	public int tamanho();
	
}
