package pract13.secuencia_ordenada_abb;


/**
 * TDA Secuencia Ordenada.
 * 
 * @param <E> tipo de los elementos almacenados en la secuencia
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2024
 */
public interface ISecuenciaOrdenada<E extends Comparable<E>> {

	/**
	 * Anhade (inserta) el elemento en la posicion
	 * correspondiente a su orden natural.
	 * @param e elemento a insertar
	 */
	public void anhade(E e);

	/**
	 * Elimina y retorna el elemento en la posicion
	 * ‘pos’. La secuencia pasa a tener un elemento menos.
	 * Las posiciones son numeros en el rango [0 .. tamanho()-1].
	 * @param pos posicion del elemento a eliminar
	 * @return elemento eliminado
	 * @throws IndexOutOfBoundsException si la posicion
	 * no es valida (pos < 0 o pos >= tamanho)
	 */
	public E elimina(int pos) throws IndexOutOfBoundsException;

	/**
	 * Elimina la primera ocurrencia del elemento en la secuencia.
	 * @param e elemento a eliminar.
	 * @return elemento eliminado
	 */
	public E elimina(E e);

	/**
	 * Retorna la posicion de la primera ocurrencia del elemento en la secuencia.
	 * Las posiciones son numeros en el rango [0 .. tamanho()-1].
	 * @param e elemento buscado
	 * @return posicion de la primera ocurrencia del elemento en la secuencia o -1 si el
	 * elemento no se encuentra en la secuencia.
	 */
	public int busca(E e);

	/**
	 * Retorna el elemento que ocupa la posicion
	 * indicada.
	 * Las posiciones son numeros en el rango [0 .. tamanho()-1].
	 * @param pos posicion del elemento a retornar
	 * @return elemento que ocupa la posicion
	 * indicada
	 * @throws IndexOutOfBoundsException si la posicion
	 * no es valida (pos < 0 o pos >= tamanho)
	 */
	public E obtenElemento(int pos) throws IndexOutOfBoundsException;

	/**
	 * Vacia la secuencia (pasa a tener tamanho 0).
	 */
	public void haceVacia();

	/**
	 * Retorna el tamanho de la secuencia (numero de elementos).
	 * @return numero de elementos de la secuencia
	 */
	public int tamanho();

}
