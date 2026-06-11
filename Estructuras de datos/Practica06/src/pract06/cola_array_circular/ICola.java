package pract06.cola_array_circular;

/**
 * TDA Cola.
 * 
 * @param <E> tipo de los elementos almacenados en la cola
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2020
 */
public interface ICola<E> {
	/**
	 * Anhade el elemento al final de la cola.
	 * @param e elemento a enconlar.
	 */
	public void encola(E e);
	
	/**
	 * Elimina y retorna el elemento que ocupaba el
	 * frente de la cola.
	 * El siguiente elemento pasa al frente.
	 * @return elemento desencolado o null si la cola se encuentra vacia.
	 */
	public E desencola();
	
	/**
	 * Retorna (pero no elimina) el elemento que
	 * se encuentra al frente de la cola.
	 * @return elemento que ocupa el frente de la cola o null si la
	 * cola se encuentra vacia.
	 */
	public E frente();
	
	/**
	 * Vacia la cola (pasa a tener tamanho 0).
	 */
	public void haceVacia();
	
	/**
	 * Retorna el tamanho de la cola (num. elementos).
	 * @return el tamanho de la cola.
	 */
	public int tamanho();

}
