
public class ClasePrueba {
	
	public static void main (String[] args) {
		IArbolBinario<String> arbol = new ArbolBinarioPH<String>("A"); 
		INodoArbolBinario<String> nodo = arbol.raiz(); 
		nodo.anhadeHijoIzq("B");
		nodo.anhadeHijoDer("C");
		nodo = nodo.hijoDer();
		nodo.anhadeHijoIzq("D");
		nodo.anhadeHijoDer("E");
		
		int hojas = MetodosEstaticos.numHojas(arbol); 
		// tiene que imprimir 3
		System.out.println(hojas + "\n"); 
		
		int des = MetodosEstaticos.numDes(arbol); 
		// tiene que imprimir 5 
		System.out.println(des + "\n"); 
		
		INodoArbolBinario<String> aux = MetodosEstaticos.buscaPos(arbol, "D");
		System.out.println(aux.contenido());
	}
	
}