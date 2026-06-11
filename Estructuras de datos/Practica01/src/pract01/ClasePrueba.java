package pract01;

/**
 * Prueba de una lista basada en array que no implementa el iterador.
 * 
 * 
 * @author Aaron Alegria
 * @version sep-2021
 */
public class ClasePrueba {
	
	/**
	 * funcion principal.
	 * @param args argumentos.
	 */
	public static void main(String[] args) {
		ListaArrayNoIter<Integer> listaInt = new ListaArrayNoIter<Integer>(5);
		ListaArrayNoIter<String> listaString = new ListaArrayNoIter<String>(5);
		
		for (int i = 0; i < 5; i++) {
			listaInt.anhade(i, i);
		}
		
		for (int i = 0; i < 5; i++) {
			listaString.anhade(i, "A");
		}
		
		System.out.println("Lista de string\n");
		for (int i = 0; i < 5; i++) {
			System.out.println(listaString.obtenElemento(i) + "\n");
		}
		System.out.println("Lista de int\n");
		System.out.println(listaInt.toString() + "\n");
	}
	
}
