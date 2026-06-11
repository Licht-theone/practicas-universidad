package pract08.c1_arbol_binario_ph;

/**
 * TDA Arbol binario.
 * 
 * @param <E> tipo de los elementos almacenados en el arbol.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2020
 */
public interface IArbolBinario<E> {
	
	/**
	 * Retorna el nodo en la raiz del arbol.
	 * @return el nodo en la raiz del arbol.
	 */
	public INodoArbolBinario<E> raiz();
	
	/**
	 * Retorna el numero de nodos del arbol.
	 * @return numero de nodos del arbol
	 */
	public int tamanho();
	
	/**
	 * Vacia el arbol.
	 */
	public void haceVacio();
	
	/**
	 * En un arbol vacio anhade el nodo raiz con el contenido indicado.
	 * @param contenidoRaiz contenido del nodo raiz
	 * @throws UnsupportedOperationException si el arbol no esta vacio.
	 */
	public void anhadeRaiz(E contenidoRaiz)
			throws UnsupportedOperationException;

}
