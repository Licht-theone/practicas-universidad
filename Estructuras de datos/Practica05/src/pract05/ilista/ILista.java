package pract05.ilista;

/**
 * TDA Lista con iterador.
 * 
 * @param <E> tipo de los elementos almacenados en la lista
 * 
 * @author Estructuras de Datos (UC)
 * @version oct-2020
 */
public interface ILista<E> {
	
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
	public void anhade(int pos, E e) throws IndexOutOfBoundsException;

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
	 * Utiliza el método equals del elemento.
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
	
	/**
	 * Retorna un iterador que permite recorrer los elementos en el orden que
	 * ocupan en la lista.  El iterador comienza situado al principio de la lista.
	 * @return iterador que permite recorrer los elementos de la lista
	 */
	public IIteradorSimple<E> iterador();

}
