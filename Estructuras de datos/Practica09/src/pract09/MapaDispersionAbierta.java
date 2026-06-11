package pract09;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Implementacion de mapa basado en tabla de dispersion abierta.
 *
 * @param <K> clase de elementos usados como llaves del mapa
 * @param <V> clase de elementos usados como valores del mapa
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version nov-2023
 */
public class MapaDispersionAbierta<K, V> implements IMapa<K, V> {
	// tabla con las listas de entradas
	private List<Entrada<K, V>>[] tabla;

	// numero de entradas en el mapa
	private int numEntradas = 0;

	// entrada en el mapa
	private static class Entrada<K, V> {
		private K llave;
		private V valor;

		public Entrada(K llave, V valor) {
			this.llave = llave;
			this.valor = valor;
		}

		@Override
		public String toString() {
			return "(k:" + llave + ",v:" + valor + ")";
		}
	}
	
	private static final int TAMANO_MAPA_POR_DEFECTO = 8;

	/**
	 * Construye un mapa con el tamanho de tabla por defecto.
	 */
	public MapaDispersionAbierta() {
		this(TAMANO_MAPA_POR_DEFECTO);
	}

	/**
	 * Constructor al que se le pasa la longitud de la tabla.
	 * @param longTabla longitud de la tabla de listas de entradas.
	 */
	@SuppressWarnings("unchecked")
	public MapaDispersionAbierta(int longTabla) {
		tabla = new List[longTabla]; // el compilador pone un warning, pero esta bien
		for (int i = 0; i < longTabla; i++) {
			tabla[i] = new LinkedList<Entrada<K, V>>();
		}
	}

	/**
	 * Si NO existe la entrada para la llave, anhade la entrada (llave,valor) al mapa.
	 * Si existe la entrada para la llave, pone valor como nuevo valor asociado con la llave.
	 * 
	 * @param llave llave con la que asociar el valor.
	 * @param valor valor asociado a la llave.
	 */
	//O(n) si hay colision sino O(1)
	@Override
	public void anhade(K llave, V valor) {
		int cod = hash(llave);
		Entrada<K, V> ent = buscaEntradaEnLista(tabla[cod], llave);
		if (ent == null) {
			Entrada<K, V> nuevo = new Entrada<K, V>(llave, valor);
			tabla[cod].add(nuevo);
			numEntradas++;
		} else {
			ent.valor = valor;
		}
		
	}

	/**
	 * Si existe la entrada para la llave, la elimina.
	 * 
	 * @param llave llave de la entrada a eliminar.
	 */
	//O(n) si hay colision sino O(1)
	@Override
	public void elimina(K llave) {
		int cod = hash(llave);
		Entrada<K, V> ent = eliminaEntradaEnLista(tabla[cod], llave);
		if (ent != null) {
			numEntradas--;
		} 
	}

	/**
	 * Retorna el valor asociado con la llave.
	 * 
	 * @param llave llave de la entrada buscada.
	 * @return valor asociado con la llave o null si no hay ninguna
	 * entrada para la llave indicada.
	 */
	//O(n) si hay colision sino O(1)
	@Override
	public V busca(K llave) {
		int cod = hash(llave);
		Entrada<K, V> ent = buscaEntradaEnLista(tabla[cod], llave);
		if (ent == null) {
			return null;
		}
		return ent.valor;
	}

	/**
	 * Retorna el numero de entradas en el mapa.
	 *	
	 * @return numero de entradas en el mapa.
	 */
	//O(1)
	@Override
	public int tamanho() {
		return numEntradas;
	}

	/**
	 * Vacia el mapa (pasa a tener 0 entradas).
	 */
	@Override
	//O(n)
	public void haceVacio() {
		for (List<Entrada<K, V>> lista: tabla) {
			lista.clear();
		}
		numEntradas = 0;
	}

	/**
	 * Retorna una lista con todas las llaves de las
	 * entradas existentes en el mapa. 
	 * @return lista con todas las llaves de las
	 * entradas existentes en el mapa.
	 */
	//O(n^2) si hay colision sino O(n)
	@Override
	public LinkedList<K> llaves() {
		LinkedList<K> llaves = new LinkedList<K>();
		for (List<Entrada<K, V>> lista: tabla) {
			if (lista != null) {
				ListIterator<Entrada<K, V>> iter = lista.listIterator();
				while (iter.hasNext()) {
					Entrada<K, V> e = iter.next();
					llaves.add(e.llave);
				}
			}
		}
		return llaves;
	}
	
	/**
	 * Retorna una lista con todos los valores de las
	 * entradas existentes en el mapa. 
	 * @return lista con todos los valores de las
	 * entradas existentes en el mapa.
	 */
	//O(n^2) si hay colision sino O(n)
	@Override
	public LinkedList<V> valores() {
		LinkedList<V> valores = new LinkedList<V>();
		for (List<Entrada<K, V>> lista: tabla) {
			if (lista != null) {
				ListIterator<Entrada<K, V>> iter = lista.listIterator();
				while (iter.hasNext()) {
					Entrada<K, V> e = iter.next();
					valores.add(e.valor);
				}
			}
		}
		return valores;
	}
	
	@Override
	public String toString() {
		String str = "[ ";
		for (int i = 0; i < tabla.length; i++) {
			str += i + "->" + tabla[i] + " ";
		}
		return str + "]";
	}
	
	/**
	 * metodo que busca una entrada en una lista.
	 * @param lst lista con las entradas
	 * @param llave llave de la entrada
	 * @return entrada o null
	 */
	//O(n) si hay colision sino O(1)
	private Entrada<K, V> buscaEntradaEnLista(List<Entrada<K, V>> lst, K llave) {
		ListIterator<Entrada<K, V>> iter = lst.listIterator();
		while (iter.hasNext()) {
			Entrada<K, V> e = iter.next();
			if (e.llave.equals(llave)) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * elimina la entrada que se le pasa.
	 * @param lst lista
	 * @param llave llave
	 * @return la entrada o null
	 */
	//O(n) si hay colision sino O(1)
	private Entrada<K, V> eliminaEntradaEnLista(List<Entrada<K, V>> lst, K llave) {
		ListIterator<Entrada<K, V>> iter = lst.listIterator();
		while (iter.hasNext()) {
			Entrada<K, V> e = iter.next();
			if (e.llave.equals(llave)) {
				iter.remove();
				return e;
			}
		}
		return null;
	}
	
	/**
	 * hash para la posicion.
	 * @param llave la clave
	 * @return el hash
	 */
	//O(1)
	private int hash(K llave) {
		return Math.abs(llave.hashCode()) % tabla.length;
		
	}
}
