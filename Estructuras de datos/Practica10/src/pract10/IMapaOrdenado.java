package pract10;

import java.util.List;

/**
 * TDA mapa ordenado.
 *
 * @param <K> clase de elementos usados como llaves del mapa
 * @param <V> clase de elementos usados como valores del mapa
 * 
 * @author Estructuras de Datos (UC)
 * @version dic-2020
 */
public interface IMapaOrdenado<K extends Comparable<K>, V> {
	
	/**
	 * Entrada del mapa.
	 *
	 * @param <K> clase de elementos usados como llaves del mapa
	 * @param <V> clase de elementos usados como valores del mapa
	 */
	public interface IEntrada<K, V> {
		
		/**
		 * Retorna la llave de la entrada.
		 * @return la llave de la entra
		 */
		public K llave();	
		
		/**
		 * Retorna el valor de la entrada.
		 * @return el valor de la entra
		 */
		public V valor();
	}

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
	 * Retorna la entrada del mapa con la menor llave.
	 * @return la entrada del mapa con la menor llave o null
	 * si el mapa se encuentra vacio
	 */
	public IEntrada<K, V> primeraEntrada();
	
	/**
	 * Retorna la entrada del mapa con la mayor llave.
	 * @return la entrada del mapa con la mayor llave o null
	 * si el mapa se encuentra vacio
	 */
	public IEntrada<K, V> ultimaEntrada();

    /**
     * Retorna todas las entradas existentes en el mapa
     * (ordenadas en función de sus llaves).
     * @return todas las entradas existentes en el mapa
     * (ordenadas en función de sus llaves)
     */
    public List<IEntrada<K, V>> entradas();
    
    /**
     * Retorna todas las entradas del mapa que tienen una
     * llave menor que la llave indicada (ordenadas en
     * función de sus llaves).
     * @param llave las entradas retornadas tienen una llave menor
     * que este valor 
     * @return todas las entradas del mapa que tienen una
     * llave menor que la llave indicada (ordenadas en
     * función de sus llaves)
     */
    public List<IEntrada<K, V>> predecesores(K llave);
    
    /**
     * Retorna una lista con todos los valores de las
	 * entradas existentes en el mapa.
     * (ordenados en funcion de sus llaves).
     * @return lista con todos los valores del mapa
     * (ordenados en funcion de sus llaves)
     */
    public List<V> valores();
    
    // XXX
    // Otras operaciones de los mapas que no se incluyen
    // para limitar la complejidad de la practica
    //
    // public List<K> llaves();
    // public List<Entrada<K, V>> sucesores(K llave);
}
