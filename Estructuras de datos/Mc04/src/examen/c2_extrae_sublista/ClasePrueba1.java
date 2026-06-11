package examen.c2_extrae_sublista;

/**
 * Clase de prueba.
 * @author Pablo
 * @version 21/10
 *
 */
public class ClasePrueba1 {
	
	/**
	 * Clase de prueba.
	 * @param args argumentos
	 */
	public static void main(String[] args) {

		ListaSimpleEnlace<String> lista = new ListaSimpleEnlace<String>();
		lista.anhade(0, "A");
		lista.anhade(1, "B");
		lista.anhade(2, "C");
		lista.anhade(3, "D");
		lista.anhade(4, "E");
		lista.anhade(5, "F");
		lista.anhade(6, "G");
		
		ListaSimpleEnlace<String> sublista = lista.extraeSublista("C", "E");
		
		
		System.out.println("Lista inicial: " + "\n"); 
		for (int i = 0; i < lista.tamanho(); i++) {
			System.out.println(lista.obtenElemento(i) + "\n");
		}
		
		System.out.println("Lista nueva: " + "\n"); 
		for (int i = 0; i < sublista.tamanho(); i++) {
			System.out.println(sublista.obtenElemento(i) + "\n");
		}
	}

}
