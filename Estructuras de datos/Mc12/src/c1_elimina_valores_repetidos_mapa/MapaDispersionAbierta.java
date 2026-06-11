package c1_elimina_valores_repetidos_mapa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Implementacion de mapa basado en tabla de dispersion abierta.
 *
 * @param <K> clase de elementos usados como llaves del mapa
 * @param <V> clase de elementos usados como valores del mapa
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2020
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

	/**
	 * Constructor al que se le pasa la longitud de la tabla.
	 * 
	 * @param longTabla longitud de la tabla de listas de entradas.
	 */
	// Complejidad temporal: O(longTabla)
	@SuppressWarnings("unchecked")
	public MapaDispersionAbierta(int longTabla) {
		tabla = new List[longTabla]; // el compilador pone un warning, pero esta bien
		for (int i = 0; i < longTabla; i++) {
			tabla[i] = new LinkedList<Entrada<K, V>>();
		}
	}

	/**
	 * Deja el mapa sin entradas con valores repetidos. Es decir, para cada grupo de
	 * entradas con el mismo valor se eliminan todas menos una. No se impone ningun
	 * criterio a la hora de elegir las entradas que se eliminan y las que se
	 * conservan.
	 */
	public void eliminaEntradasConValorRepetido() {
		Map <V, Integer> miMapa = new HashMap<V, Integer>();
		for (List<Entrada<K, V>> lista: tabla) {
			for (Entrada<K, V> ent: lista) {
				if (!miMapa.containsKey(ent.valor)) {
					miMapa.put(ent.valor, 1);
				} else {
					numEntradas--;
					lista.remove(ent);
				}
			}
				
		}
		
	}

	/**
	 * Si NO existe la entrada para la llave, anhade la entrada (llave,valor) al
	 * mapa. Si existe la entrada para la llave, pone valor como nuevo valor
	 * asociado con la llave.
	 * 
	 * @param llave llave con la que asociar el valor.
	 * @param valor valor asociado a la llave.
	 */
	// Complejidad temporal: O(1) (asumiendo que la funcion de dispersion dispersa
	// las
	// llaves apropiadamente).
	// (En el caso peor seria O(tabla[cod].tamanho), pero en una tabla bien
	// dimensionada y con
	// una buena funcion de dispersion, el tamanho de las listas es muy pequenho y,
	// en todo
	// caso, mucho menor que el numero total de entradas en la tabla)
	@Override
	public void anhade(K llave, V valor) {
		int cod = hash(llave);
		Entrada<K, V> entrada = buscaEntradaEnLista(tabla[cod], llave);
		if (entrada == null) {
			// no existe entrada para la llave indicada: se crea una nueva
			Entrada<K, V> nuevaEntrada = new Entrada<K, V>(llave, valor);
			tabla[cod].add(nuevaEntrada);
			numEntradas++;

		} else {
			// existe entrada para la llave indicada: se cambia el valor asociado
			entrada.valor = valor;
		}
	}

	/**
	 * Si existe la entrada para la llave, la elimina.
	 * 
	 * @param llave llave de la entrada a eliminar.
	 */
	// Complejidad temporal: O(1) (asumiendo que la funcion de dispersion dispersa
	// las
	// llaves apropiadamente).
	// (En el caso peor seria O(tabla[cod].tamanho), pero en una tabla bien
	// dimensionada y con
	// una buena funcion de dispersion, el tamanho de las listas es muy pequenho y,
	// en todo
	// caso, mucho menor que el numero total de entradas en la tabla)
	@Override
	public void elimina(K llave) {
		int cod = hash(llave);

		Entrada<K, V> entrada = eliminaEntradaDeLista(tabla[cod], llave);

		if (entrada != null) {
			// habia entrada para la llave indicada y fue eliminada
			numEntradas--;
		}
	}

	/**
	 * Retorna el valor asociado con la llave.
	 * 
	 * @param llave llave de la entrada buscada.
	 * @return valor asociado con la llave o null si no hay ninguna entrada para la
	 *         llave indicada.
	 */
	// Complejidad temporal: O(1) (asumiendo que la funcion de dispersion dispersa
	// las llaves apropiadamente).
	// (En el caso peor seria O(tabla[cod].tamanho), pero en una tabla bien
	// dimensionada y con una buena funcion de dispersion, el tamanho de las listas
	// es muy pequenho y, en todo caso, mucho menor que el numero total de entradas
	// en la tabla)
	@Override
	public V busca(K llave) {
		int cod = hash(llave);
		Entrada<K, V> e = buscaEntradaEnLista(tabla[cod], llave);
		if (e == null) {
			return null;
		} else {
			return e.valor;
		}
	}

	/**
	 * Retorna el numero de entradas en el mapa.
	 * 
	 * @return numero de entradas en el mapa.
	 */
	// Complejidad temporal: O(1)
	@Override
	public int tamanho() {
		return numEntradas;
	}

	/**
	 * Vacia el mapa (pasa a tener 0 entradas).
	 */
	// Complejidad temporal: O(tabla.length)
	@Override
	public void haceVacio() {
		for (List<Entrada<K, V>> lista : tabla) {
			lista.clear();
		}
		numEntradas = 0;
	}

	/**
	 * Retorna una lista con todas las llaves de las entradas existentes en el mapa.
	 * 
	 * @return lista con todas las llaves de las entradas existentes en el mapa.
	 */
	// Complejidad temporal: O(tabla.length + numEntradas)
	@Override
	public LinkedList<K> llaves() {
		LinkedList<K> listaLlaves = new LinkedList<>();
		for (List<Entrada<K, V>> lista : tabla) {
			for (Entrada<K, V> entrada : lista) {
				listaLlaves.add(entrada.llave);
			}
		}
		return listaLlaves;
	}

	/**
	 * Retorna una lista con todos los valores de las entradas existentes en el
	 * mapa.
	 * 
	 * @return lista con todos los valores de las entradas existentes en el mapa.
	 */
	// Complejidad temporal: O(tabla.length + numEntradas)
	@Override
	public LinkedList<V> valores() {
		LinkedList<V> listaValores = new LinkedList<>();
		for (List<Entrada<K, V>> lista : tabla) {
			for (Entrada<K, V> entrada : lista) {
				listaValores.add(entrada.valor);
			}
		}
		return listaValores;
	}

	/**
	 * Retorna el codigo de dispersion correspondiente a la llave. El valor
	 * retornado ya esta "normalizado": puede ser utilizado directamente como indice
	 * de la tabla (valor en el rango 0..tabla.length-1)
	 * 
	 * @param llave llave de la que calcular el codigo de dispersion
	 * @return codigo de dispersion normalizado
	 */
	// Complejidad temporal: O(1)
	private int hash(K llave) {
		return Math.abs(llave.hashCode()) % tabla.length;
	}

	/**
	 * Busca en la lista una entrada con la llave indicada.
	 * 
	 * @param lst   lista en la que buscar.
	 * @param llave llave buscada.
	 * @return entrada con la llave buscada o null si no existe esa entrada.
	 */
	// Complejidad temporal: O(1) (asumiendo que la funcion de dispersion dispersa
	// las llaves apropiadamente).
	// (En el caso peor seria O(lst.tamanho), pero en una tabla bien dimensionada y
	// con una buena funcion de dispersion, el tamanho de las listas es muy pequenho y,
	// en todo caso, mucho menor que el numero total de entradas en la tabla)
	private Entrada<K, V> buscaEntradaEnLista(List<Entrada<K, V>> lst, K llave) {
		for (Entrada<K, V> entrada : lst) {
			if (entrada.llave.equals(llave)) {
				return entrada;
			}
		}
		return null;
	}

	/**
	 * Busca en la lista una entrada con la llave indicada y la elimina.
	 * 
	 * @param lst   lista en la que buscar.
	 * @param llave llave buscada.
	 * @return entrada con la llave buscada y eliminada o null si no existe esa
	 *         entrada.
	 */
	// Complejidad temporal: O(1) (asumiendo que la funcion de dispersion dispersa
	// las llaves apropiadamente).
	// (En el caso peor seria O(lst.tamanho), pero en una tabla bien dimensionada y
	// con una buena funcion de dispersion, el tamanho de las listas es muy pequenho y,
	// en todo caso, mucho menor que el numero total de entradas en la tabla)
	private Entrada<K, V> eliminaEntradaDeLista(List<Entrada<K, V>> lst, K llave) {
		Iterator<Entrada<K, V>> iter = lst.iterator();
		while (iter.hasNext()) {
			Entrada<K, V> e = iter.next();
			if (e.llave.equals(llave)) {
				// existe entrada para la llave indicada: se elimina
				iter.remove();
				return e;
			}
		}
		// no existe entrada para la llave indicada
		return null;
	}

	@Override
	public String toString() {
		return Arrays.toString(tabla);
	}
}
