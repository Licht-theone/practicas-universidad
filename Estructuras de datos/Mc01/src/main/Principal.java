package main;

import java.util.Arrays;
import java.util.Collections;

public class Principal {

	public static void main(String[] args) {
		Persona[] lista = new Persona[5];
		lista[0] = new Persona("Eva", 20);
		lista[1] = new Persona("Juan", 18);
		lista[2] = new Persona("Fredo", 35);
		lista[3] = new Persona("Ana", 25);
		lista[4] = new Persona("Carlos",22);

		System.out.println("Lista original\n");

		for (Persona p : lista) {
			System.out.println(p.toString());
		}

		Persona p1 = lista[2];
		if (p1 == lista[2]) {
			System.out.println("P1 y lista2 son iguales");
		}

		if (p1.equals(lista[2])) {
			System.out.println("P1 y lista2 son equals");
		}

		if (p1 == lista[3]) {
			System.out.println("P1 y lista3 son iguales");
		}

		if (p1.equals(lista[3])) {
			System.out.println("P1 y lista3 son equals");
		}
		System.out.println("Lista ordenada");
		Arrays.sort(lista, Collections.reverseOrder());
		for (Persona p: lista) {
			System.out.println(p.toString());
		}
	}


}
