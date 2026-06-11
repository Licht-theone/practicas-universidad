package examen.c2;

/**
 * Prueba de mueve seccion.
 * 
 * @author Aaron Alegria
 * @version oct-2024
 */
public class Prueba {

	/**
	 * main.
	 * @param args args
	 */
	public static void main(String[] args) {
		ListaDobleEnlace<String> lista1 = new ListaDobleEnlace<String>();
		ListaDobleEnlace<String> lista2 = new ListaDobleEnlace<String>();
		lista1.anhade(0, "A");
		lista1.anhade(1, "B");
		lista1.anhade(2, "C");
		lista1.anhade(3, "D");
		lista1.anhade(4, "E");
		lista1.anhade(5, "F");
		lista2.anhade(0, "a");
		lista2.anhade(1, "b");
		System.out.println("Lista 1 antes del metodo\n" + lista1);
		System.out.println("Lista 2 antes del metodo\n" + lista2);
		lista1.mueveSeccion("E", 3, lista2); //O(n)
		System.out.println("Lista 1 tras del metodo\n" + lista1);
		System.out.println("Lista 2 tras del metodo\n" + lista2);
		
		//otro ejemplo
		lista2.mueveSeccion("C", 2, lista1); //O(n)
		System.out.println("Lista 1 tras del metodo\n" + lista1);
		System.out.println("Lista 2 tras del metodo\n" + lista2);
	}
	
	

}
