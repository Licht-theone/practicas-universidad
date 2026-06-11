package examen.ilista;

import java.util.NoSuchElementException;

/**
 * Iterador simple.
 * 
 * @param <E> tipo de los elementos sobre los que itera
 * 
 * @author Estructuras de Datos (UC)
 * @version oct-2020
 */
public interface IIteradorSimple<E> {
	
	/**
	 * Indica si hay mas elementos(todavía no se ha llegado al final de
	 * la lista).
	 * @return verdadero si hay mas elementos que recorrer y falso en caso
	 * contrario
	 */
	public boolean haySiguiente();
	
	/**
	 * Retorna el siguiente elemento y avanza el iterador.
	 * @return siguiente elemento.
	 * @throws NoSuchElementException si se invoca este metodo cuando el
	 * iterador se encuentra al final de la secuencia.
	 */
	public E siguiente() throws NoSuchElementException;
	
	/**
	 * Inserta un elemento en la posición del iterador.
	 * @param e elemento a insertar.
	 */
	public void anhade(E e);
	
	/**
	 * Reemplaza el ultimo elemento accedido por el iterador.
	 * Se considera ultimo elemento accedido a el ultimo retornado por
	 * siguiente() o el ultimo anhadido por anhade(), lo que haya
	 * ocurrido mas recientemente.
	 * @param e elemento utilizado para reemplazar
	 * @throws NoSuchElementException si el iterador no ha retornado ningún elemento
	 * previamente ni se ha llamado a anhade().
	 */
	public void asigna(E e);
	
}
