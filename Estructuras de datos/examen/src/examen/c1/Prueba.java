package examen.c1;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase Prueba para el poneEntreIguales.
 * 
 * @author Aaron Alegria
 * @version oct-2024
 */
public class Prueba {
	
	/**
	 * metodo main.
	 * @param args argumentos de entrada (no hay solo es por el chekstyle)
	 */
	public static void main(String[] args) {
		List<String> lista = new LinkedList<String>();
		lista.add("a");
		lista.add("b");
		lista.add("X");
		lista.add("c");
		lista.add("d");
		lista.add("X");
		lista.add("e");
		lista.add("X");
		lista.add("f");
		lista.add("X");
		System.out.println("Lista sin modificar\n" + lista);
		Cuestion1.poneEntreIguales(lista, "X"); //O(n)
		System.out.println("Lista modificada\n" + lista);
	}

}
