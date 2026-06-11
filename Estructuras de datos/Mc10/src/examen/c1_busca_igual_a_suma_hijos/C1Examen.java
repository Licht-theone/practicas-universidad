package examen.c1_busca_igual_a_suma_hijos;

/**
 * Cuestion 1.
 * 
 * @author Estructuras de Datos (UC) y
 * @version dic-2020
 */
public class C1Examen {

	/**
	 * Retorna el contenido del primer nodo del arbol (segun la ordenacion
	 * en inorden) que tenga dos hijos y su contenido sea igual a la suma de los
	 * contenidos de sus hijos.
	 * @param arbol arbol en el que realizar la busqueda.
	 * @return contenido del nodo que cumple la condicion buscada o null si no hay ningun nodo
	 * que cumpla dicha condicion.
	 */
	public static Integer primerNodoEnInordenIgualSumaHijos(IArbolBinario<Integer> arbol) {
		if (arbol.raiz() == null) {
			return null;
		}

		return primerNodoEnInordenIgualSumaHijosRec(arbol.raiz());
	}

	private static Integer primerNodoEnInordenIgualSumaHijosRec(INodoArbolBinario<Integer> raiz) {
		if (raiz == null) {
			return null;
		}
		Integer suma = null;
		if (raiz.hijoIzq() != null) {
			suma = primerNodoEnInordenIgualSumaHijosRec(raiz.hijoIzq());
		}
		if (raiz.hijoIzq() != null && raiz.hijoDer() != null) {
			suma = raiz.hijoIzq().contenido() + raiz.hijoDer().contenido();
			if (suma == raiz.contenido()) {
				return suma;
			}
		}
		if (raiz.hijoDer() != null) {
			suma = primerNodoEnInordenIgualSumaHijosRec(raiz.hijoDer());
		}
		return suma;
	}

}
