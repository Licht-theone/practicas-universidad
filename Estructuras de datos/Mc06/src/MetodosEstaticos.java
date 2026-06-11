
public class MetodosEstaticos {
	public static <E> int numHojas(IArbolBinario<E> arbol) {
		if (arbol.raiz() == null) {
			return 0;
		}
		return numHojasRec(arbol.raiz());
	}

	private static <E> int numHojasRec(INodoArbolBinario<E> nodo) {
		if (nodo == null) {
			return 0;
		}
		int c = 0;
		if (nodo.hijoIzq() == null && nodo.hijoDer() == null) {
			c++;
		} 
		c += numHojasRec(nodo.hijoIzq());
		c += numHojasRec(nodo.hijoDer());
		return c;
	}
	
	public static <E> int numDes(IArbolBinario<E> arbol) {
		if (arbol.raiz() == null) {
			return 0;
		}
		return numDesRec(arbol.raiz());
	}

	private static <E> int numDesRec(INodoArbolBinario<E> nodo) {
		if (nodo == null) {
			return 0;
		}
		int c = 1;
		c += numDesRec(nodo.hijoIzq());
		c += numDesRec(nodo.hijoDer());
		return c;
	}
	
	public static <E> INodoArbolBinario<E> buscaPre(IArbolBinario<E> arbol, E bus) {
		if (arbol.raiz() == null) {
			return null;
		}
		return buscaPreRec(arbol.raiz(), bus);
		
	}
	
	private static <E> INodoArbolBinario<E> buscaPreRec(INodoArbolBinario<E> nodo, E bus) {
		if (nodo == null) {
			return null;
		}
		if (nodo.contenido().equals(bus)) {
			return nodo;
		}
		INodoArbolBinario<E> nodoAux = buscaPreRec(nodo.hijoIzq(), bus);
		if (nodoAux != null) {
			return nodoAux;
		}
		nodoAux = buscaPreRec(nodo.hijoDer(), bus);
		return nodoAux;
	}

	public static <E> INodoArbolBinario<E> buscaPos(IArbolBinario<E> arbol, E bus) {
		if (arbol.raiz() == null) {
			return null;
		}
		return buscaPosRec(arbol.raiz(), bus);
	}
	
	private static <E> INodoArbolBinario<E> buscaPosRec(INodoArbolBinario<E> nodo, E bus) {
		if (nodo == null) {
			return null;
		}
		INodoArbolBinario<E> nodoAux = buscaPosRec(nodo.hijoIzq(), bus);
		if (nodoAux != null) {
			return nodoAux;
		}
		nodoAux = buscaPosRec(nodo.hijoDer(), bus);
		if (nodoAux != null) {
			return nodoAux;
		}
		if (nodo.contenido().equals(bus)) {
			return nodo;
		}
		return null;
	}

	public static <E> INodoArbolBinario<E> buscaIn(IArbolBinario<E> arbol, E bus) {
		if (arbol.raiz() == null) {
			return null;
		}
		return buscaInRec(arbol.raiz(), bus);
		
	}

	private static <E> INodoArbolBinario<E> buscaInRec(INodoArbolBinario<E> nodo, E bus) {
		if (nodo == null) {
			return null;
		}
		INodoArbolBinario<E> nodoAux = buscaInRec(nodo.hijoIzq(), bus);
		if (nodoAux != null) {
			return nodoAux;
		}
		if (nodo.contenido().equals(bus)) {
			return nodo;
		}
		nodoAux = buscaInRec(nodo.hijoDer(), bus);
		return nodoAux;
	}
	
	
	@SuppressWarnings("unused")
	private static <E> int altura(IArbolBinario<E> arbol) {
		if (arbol.raiz() == null) {
			return -1;
		}
		
		return alturaRec(arbol.raiz());
	}

	private static <E> int alturaRec(INodoArbolBinario<E> nodo) {
		if (nodo == null) {
			return 0;
		}
		int c = 1;
		if (nodo.hijoDer() != null || nodo.hijoIzq() != null) {
			c++;
		}
		c += alturaRec(nodo.hijoIzq());
		c += alturaRec(nodo.hijoDer());
		return c;
	}
}
