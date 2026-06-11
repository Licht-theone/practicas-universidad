package pract09;

import java.util.List;

/**
 * TDA mapa.
 *
 * @param <K> clase de elementos usados como llaves del mapa
 * @param <V> clase de elementos usados como valores del mapa
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2020
 */
public interface IMapa<K, V> {

	/**
	 * Si NO existe la entrada para la llave, anhade la entrada (llave,valor) al mapa.
	 * Si existe la entrada para la llave, pone valor como nuevo valor asociado con la llave.
	 * 
	 * @param llave llave con la que asociar el valor.
	 * @param valor valor asociado a la llave.
	 */
	public void anhade(K llave, V valor);

	/**
	 * Si existe la entrada para la llave, la elimina.
	 * 
	 * @param llave llave de la entrada a eliminar.
	 */
	public void elimina(K llave);
	
	/**
	 * Retorna el valor asociado con la llave.
	 * 
	 * @param llave llave de la entrada buscada.
	 * @return valor asociado con la llave o null si no hay ninguna
	 * entrada para la llave indicada.
	 */
	public V busca(K llave);

	/**
	 * Vacia el mapa (pasa a tener 0 entradas).
	 */
	public void haceVacio();

	/**
	 * Retorna el numero de entradas en el mapa.
	 *	
	 * @return numero de entradas en el mapa.
	 */
	public int tamanho();

	/**
	 * Retorna una lista con todas las llaves de las
	 * entradas existentes en el mapa. 
	 * @return lista con todas las llaves de las
	 * entradas existentes en el mapa.
	 */
	public List<K> llaves();

	/**
	 * Retorna una lista con todos los valores de las
	 * entradas existentes en el mapa. 
	 * @return lista con todos los valores de las
	 * entradas existentes en el mapa.
	 */
	public List<V> valores();

	// XXX
	// Otra operacion de los mapas que no se incluye
	// para limitar la complejidad de la practica
	//
	// public List<Entrada> entradas();
}
