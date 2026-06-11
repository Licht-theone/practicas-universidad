package pract01;

/**
 * TDA Lista generica sin iterador.
 * Version simplificada del TDA Lista que veremos en el tema 3 que no
 * incluye las operaciones del iterador.
 * 
 * @param <E> tipo de los elementos almacenados en la lista
 * 
 * @author Estructuras de Datos (UC)
 * @version sep-2022
 */
public interface IListaNoIter<E> {

	/**
	 * Inserta el elemento en la posicion indicada.
	 * El elemento en la posicion de insercion (si
	 * existe) y sucesivos se desplazan a la derecha
	 * (su posicion se incrementa en 1).
	 * @param pos posicion en la que insertar el elemento
	 * @param e elemento a insertar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos > tamanho)
	 */
	public void anhade(int pos, E e) throws IndexOutOfBoundsException;

	/**
	 * Elimina y retorna el elemento en la posicion
	 * indicada. El elemento siguiente al eliminado
	 * (si existe) y sucesivos se desplazan a la
	 * izquierda (su posicion se decrementa en 1).
	 * @param pos posicion del elemento a eliminar
	 * @return el elemento eliminado
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */
	public E elimina(int pos) throws IndexOutOfBoundsException;

	/**
	 * Retorna el elemento que ocupa la posicion indicada.
	 * @param pos posicion del elemento a retornar.
	 * @return el elemento que ocupa la posicion indicada.
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */
	public E obtenElemento(int pos) throws IndexOutOfBoundsException;

	/**
	 * Retorna el tamanho de la lista (numero de elementos).
	 * @return numero de elementos de la lista
	 */
	public int tamanho();

	/**
	 * Retorna la posicion de la primera ocurrencia
	 * del elemento buscado en la lista.
	 * Utiliza el metodo equals del elemento.
	 * @param e elemento buscado
	 * @return posicion de la primera ocurrencia
	 * del elemento en la lista o -1 en caso de que 
	 * el elemento no este en la lista
	 */
	public int busca(E e);

	/**
	 * Vacia la lista (pasa a tener tamanho 0).
	 */
	public void haceVacia();

}
