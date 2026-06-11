@SuppressWarnings("unused")
public class buscaElemConDosHijosIguales {
	private static <E> E buscaElemConDosHijosIgualesInordenRec(INodoArbolBinario<E> nodo) {
		if (nodo == null) {
			return null;
		}

		return null;
	}

	private static <E> E buscaElemConDosHijosIgualesPreordenRec(INodoArbolBinario<E> nodo) {
		if (nodo == null) {
			return null;
		}
		return null;
	}

	private static <E> E buscaElemConDosHijosIgualesPostordenRec(INodoArbolBinario<E> nodo) {
		if (nodo == null) {
			return null;
		}
		if (nodo.hijoIzq() != null && nodo.hijoDer() != null) {
			if (nodo.hijoIzq().contenido().equals(nodo.hijoDer().contenido())) {
				return nodo.contenido();
			}
		}
		E elto = buscaElemConDosHijosIgualesPreordenRec(nodo.hijoIzq());
		if (elto != null) {
			return elto;
		}
		return buscaElemConDosHijosIgualesPreordenRec(nodo.hijoDer());
	}
}

