package examen.c1;

import java.util.*;

/**
 * Ejemplos mostrados por pantalla para ver su correcto funcionamiento.
 * @author javier sierra
 *@version octubre-2023
 */

public class EjemploDePrueba {
	
	/**
	 * es el main de la clase.
	 * @param args argumentos
	 */

	public static void main(String[] args) {

		List<String> lista = new LinkedList<String>();
		lista.add("a");
		lista.add("b");
		lista.add("X");
		lista.add("c");
		lista.add("d");
		lista.add("e");
		lista.add("f");
		
		List<String> lista2 = new LinkedList<String>();
		lista2.add("a");
		lista2.add("X");
		lista2.add("b");
		lista2.add("c");
		
		System.out.println("Lista Original antes del programa: " + lista.toString());
		System.out.println("Lista Destino antes del programa" + lista2.toString());
		Cuestion1.mueveElementos(lista, lista2, "X");;
		System.out.println("Lista Original despues del programa: " + lista.toString());
		System.out.println("Lista Destino despues del programa: " + lista2.toString());
	}

}
