package pract04.lista_array;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import pract04.ilista.IIteradorSimple;
import pract04.ilista.ILista;

import java.util.NoSuchElementException;

/**
 * Test de una Lista con iterador sencillo.
 *
 * @author Estructuras de Datos (UC)
 * @version sep-2022
 */
public class ListaTest {

	@Test
	void testConstructor() {
		System.out.println("testConstructor");

		ILista<String> lst = new ListaArray<String>(5);

		// comprueba que la lista comienza vacia
		muestraLista(lst);
		int tam = lst.tamanho();
		assertTrue("Error: tamanho:" + tam + " deberia ser 0", tam == 0);

		// comprueba que se produce un error al acceder al primer elemento
		try {
			lst.obtenElemento(0);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
	}

	@Test
	void testAnhadePrimerElemento() {
		System.out.println("testAnhadePrimerElemento");

		ILista<String> lst = new ListaArray<String>(5);
		int tam;
		String str;

		// anhade primer elemento en lista vacia
		lst.anhade(0, "A");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(0);
		assertTrue("Error: el tamanho deberia ser 1, pero es:" + tam, tam == 1);
		assertTrue("Error: str deberia ser A, pero es:" + str, str.equals("A"));
	}
	
	@Test
	void testAnhadePrimeraPos() {
		System.out.println("testAnhadePrimeraPos");

		ILista<String> lst = new ListaArray<String>(5);
		int tam;
		String str;

		// anhade primer elemento en lista vacia
		lst.anhade(0, "A");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(0);
		assertTrue("Error: el tamanho deberia ser 1, pero es:" + tam, tam == 1);
		assertTrue("Error: str deberia ser A, pero es:" + str, str.equals("A"));

		// anhade otros dos elementos en la primera posicion
		lst.anhade(0, "B");
		lst.anhade(0, "C");
		muestraLista(lst);  // [C, B, A]
		
		// comprueba que el tamanho es correcto
		tam = lst.tamanho();
		assertTrue("Error: el tamanho deberia ser 3, pero es:" + tam, tam == 3);
		
		// comprueba que los elementos estan donde deben
		str = lst.obtenElemento(0);
		assertTrue("Error: el elemento 0 deberia ser C, pero es:" + str,
				str.equals("C"));
		str = lst.obtenElemento(1);
		assertTrue("Error: el elemento 1 deberia ser B, pero es:" + str,
				str.equals("B"));
		str = lst.obtenElemento(2);		
		assertTrue("Error: el elemento 2 deberia ser A, pero es:" + str,
				str.equals("A"));
	}

	@Test
	void testAnhade() {
		System.out.println("testAnhade");

		ILista<String> lst = new ListaArray<String>(5);
		int tam;
		String str;

		// anhade primer elemento en lista vacia
		lst.anhade(0, "A");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(0);
		assertTrue("Error: el tamanho deberia ser 1, pero es:" + tam, tam == 1);
		assertTrue("Error: str deberia ser A, pero es:" + str, str.equals("A"));

		// anhade elemento final en lista con un elemento
		lst.anhade(1, "C");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(1);
		assertTrue("Error: el tamanho deberia ser 2, pero es:" + tam, tam == 2);
		assertTrue("Error: el ultimo elemento deberia ser C, pero es:" + str,
				str.equals("C"));

		// anhade elemento intermedio
		lst.anhade(1, "B");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(1);
		assertTrue("Error: el tamanho deberia ser 3, pero es:" + tam, tam == 3);

		// comprueba que los elementos estan donde deben
		str = lst.obtenElemento(0);
		assertTrue("Error: el elemento 0 deberia ser A, pero es:" + str,
				str.equals("A"));
		str = lst.obtenElemento(1);
		assertTrue("Error: el elemento 1 deberia ser B, pero es:" + str,
				str.equals("B"));
		str = lst.obtenElemento(2);		
		assertTrue("Error: el elemento 2 deberia ser C, pero es:" + str,
				str.equals("C"));
	}

	@Test
	void testAnhadePosIntermedia() {
		System.out.println("testAnhadePosIntermedia");

		ILista<String> lst = new ListaArray<String>(5);
		int tam;
		String str;

		// anhade en lista vacia
		lst.anhade(0, "C");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(0);
		assertTrue("Error: el tamanho deberia ser 1, pero es:" + tam, tam == 1);
		assertTrue("Error: str deberia ser C, pero es:" + str, str.equals("C"));

		// anhade primero en lista con un elemento
		lst.anhade(0, "A");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(0);
		assertTrue("Error: el tamanho deberia ser 2, pero es:" + tam, tam == 2);
		assertTrue("Error: el primer elemento deberia ser A, pero es:" + str,
				str.equals("A"));

		// anhade elemento intermedio
		lst.anhade(1, "B");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(1);
		assertTrue("Error: el tamanho deberia ser 3, pero es:" + tam, tam == 3);
		assertTrue("Error: el elemento 1 deberia ser B, pero es:" + str,
				str.equals("B"));

		// anhade elemento final
		lst.anhade(3, "D");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(3);
		assertTrue("Error: el tamanho deberia ser 4, pero es:" + tam, tam == 4);
		assertTrue("Error: el elemento deberia ser D, pero es:" + str,
				str.equals("D"));		
	}

	@Test
	void testSuperaCapacidad() {
		final int capacidadInicial = 10;
		final int posInsercion = 5;
		System.out.println("testSuperaCapacidad");

		ILista<String> lst = new ListaArray<String>(capacidadInicial);
		String c;

		// llena la lista alcanzando su capacidad inicial
		for (int i = 0; i < capacidadInicial; i++) {
			lst.anhade(0, "" + (char) ('A' + capacidadInicial - 1 - i));
		}
		muestraLista(lst);

		// anhade el elemento que supera la capacidad
		lst.anhade(posInsercion, "X");
		muestraLista(lst);

		// comprueba que los elementos estan donde deben
		for (int i = 0; i < posInsercion; i++) {
			c = lst.obtenElemento(i);
			assertTrue("Error: el elemento " + i + " deberia ser " + (char) ('A' + i) +
					", pero es:" + c, c.equals("" + (char) ('A' + i)));
		}
		c = lst.obtenElemento(posInsercion);
		assertTrue("Error: el elemento" + posInsercion + " deberia ser " + 'X' +
				", pero es:" + c, c.equals("X"));
		for (int i = posInsercion + 1; i < lst.tamanho(); i++) {
			c = lst.obtenElemento(i);
			assertTrue("Error: el elemento " + i + " deberia ser " + (char) ('A' + i - 1) +
					", pero es:" + c, c.equals("" + (char) ('A' + i - 1)));
		}
	}

	@Test
	void testElimina() {
		System.out.println("testElimina");

		ILista<String> lst = new ListaArray<String>(5);
		int tam;
		String str;

		// elimina primero dejando la lista vacia
		lst.anhade(0, "Z");
		str = lst.elimina(0);

		tam = lst.tamanho();
		assertTrue("Error: el tamanho deberia ser 0, pero es:" + tam, tam == 0);	

		assertTrue("Error: el elemento eliminado deberia ser Z, pero es:" + str,
				str.equals("Z"));
		muestraLista(lst);

		// trata te obtener un elemento que ya ha sido eliminado
		try {
			str = lst.obtenElemento(0);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}	

		tam = lst.tamanho();
		assertTrue("Error: el tamanho deberia ser 0, pero es:" + tam, tam == 0);	

		// anhadimos elementos a la lista
		lst.anhade(0, "A");
		lst.anhade(1, "B");
		lst.anhade(2, "C");
		lst.anhade(3, "D");

		// eliminamos pos = numEle
		tam = lst.tamanho();
		try {
			str = lst.elimina(tam);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}	

		// elimina primero dejando lista con elementos
		str = lst.elimina(0);
		assertTrue("Error: el elemento eliminado deberia ser A, pero es:" + str,
				str.equals("A"));
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(0);
		assertTrue("Error: el tamanho deberia ser 3, pero es:" + tam, tam == 3);
		assertTrue("Error: el primer elemento deberia ser B, pero es:" + str, str.equals("B"));

		// elimina intermedio
		str = lst.elimina(1);
		assertTrue("Error: el elemento eliminado deberia ser C, pero es:" + str,
				str.equals("C"));
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(1);
		assertTrue("Error: el tamanho deberia ser 2, pero es:" + tam, tam == 2);
		assertTrue("Error: el elemento 1 deberia ser D, pero es:" + str, str.equals("D"));

		// elimina ultimo
		str = lst.elimina(1);
		assertTrue("Error: el elemento eliminado deberia ser D, pero es:" + str,
				str.equals("D"));
		muestraLista(lst);
		try {
			str = lst.obtenElemento(1);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		tam = lst.tamanho();
		assertTrue("Error: el tamanho deberia ser 1, pero es:" + tam, tam == 1);
	}

	@Test
	void testEliminaLimiteCapacidad() {
		System.out.println("testEliminaLimiteCapacidad");

		ILista<String> lst = new ListaArray<String>(5);
		int tam;
		String str;

		// anhade elementos hasta llenar la lista
		lst.anhade(0, "C");
		lst.anhade(0, "A");
		lst.anhade(1, "B");
		lst.anhade(3, "D");
		lst.anhade(4, "E");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(4);
		assertTrue("Error: el tamanho deberia ser 5, pero es:" + tam, tam == 5);
		assertTrue("Error: el elemento deberia ser E, pero es:" + str,
				str.equals("E"));

		// intenta eliminar un elemento fuera de la capacidad de la lista
		try {
			str = lst.elimina(5);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}	
		tam = lst.tamanho();
		assertTrue("Error: el tamanho deberia ser 5, pero es:" + tam, tam == 5);

		// elimina el ultimo elemento
		str = lst.elimina(4);
		muestraLista(lst);
		tam = lst.tamanho();
		assertTrue("Error: el elemento eliminado deberia ser E, pero es:" + str,
				str.equals("E"));
		assertTrue("Error: el tamanho deberia ser 4, pero es:" + tam, tam == 4);	
	}

	@Test
	void testPosIncorrecta() {
		System.out.println("testAnhadePosIncorrecta");

		ILista<String> lst = new ListaArray<String>(5);

		// posicion negativa
		try {
			lst.anhade(-1, "Z");
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}	
		muestraLista(lst);

		try {
			lst.elimina(-1);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
		try {
			lst.obtenElemento(-1);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		// posicion correcta
		lst.anhade(0, "A");
		muestraLista(lst);

		// posicion fuera de la lista
		try {
			lst.anhade(2, "B");
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
		muestraLista(lst);

		try {
			lst.elimina(2);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		try {
			lst.obtenElemento(2);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}	
	}

	@Test
	void testBusca() {
		System.out.println("testBusca");
		int pos;

		ILista<String> lst = new ListaArray<String>(5);

		// mete varios elementos en la lista
		lst.anhade(0, "A");
		lst.anhade(1, "B");
		lst.anhade(2, "C");
		lst.anhade(3, "D");
		lst.anhade(4, "C"); // repito C a proposito
		lst.anhade(5, "F");
		lst.anhade(6, "G");
		muestraLista(lst);

		// comprueba que encuentra el primer elemento
		pos = lst.busca(new String("A"));
		assertTrue("Error: deberia estar en la pos 0, pero esta en:" + pos, pos == 0);

		// comprueba que encuentra un elemento intermedio que ademas esta repetido
		pos = lst.busca(new String("C"));
		assertTrue("Error: deberia estar en la pos 2, pero esta en:" + pos, pos == 2);

		// comprueba que encuentra el ultimo elemento
		pos = lst.busca(new String("G"));
		assertTrue("Error: deberia estar en la pos 6, pero esta en:" + pos, pos == 6);

		// comprueba que no encuentra un elemento que no existe
		pos = lst.busca(new String("X"));
		assertTrue("Error: no deberia haber encontrado el elemento", pos == -1);

	}

	@Test
	void testHaceVacia() {
		System.out.println("testHaceVacia");

		ILista<String> lst = new ListaArray<String>(5);
		int tam;

		// hace vacia una lista con varios elementos
		lst.anhade(0, "A");
		lst.anhade(1, "B");
		lst.anhade(2, "C");
		lst.anhade(3, "D");
		lst.haceVacia();
		muestraLista(lst);

		// comprueba el tamanho
		tam = lst.tamanho();
		assertTrue("Error: el tamanho deberia ser 0, pero es:" + tam, tam == 0);

		// comprueba que se produce un error al acceder al primer elemento
		try {
			lst.obtenElemento(0);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
	}

	@Test
	void testIteradorSimple() {
		System.out.println("testIteradorSimple");

		// crea una lista con un unico elemento
		ILista<Character> lst = new ListaArray<Character>(5);
		lst.anhade(0, 'A');
		muestraLista(lst);

		// crea un iterador
		IIteradorSimple<Character> iter = lst.iterador();
		
		// comprueba que hay siguiente
		assertTrue("Deberia haber siguiente", iter.haySiguiente());
		
		// comprueba que el primer siguiente() retorna 'A'
		char c = iter.siguiente();
		assertEquals('A', c);
		
		// comprueba que ha llegado al final de la lista (no hay siguiente)
		assertFalse(iter.haySiguiente(), "No deberia haber siguiente");
	}

	@Test
	void testIteradorRecorre() {
		System.out.println("testIteradorRecorre");

		ILista<Character> lst = new ListaArray<Character>(5);

		// mete varios elementos en la lista
		lst.anhade(0, 'A');
		lst.anhade(1, 'B');
		lst.anhade(2, 'C');
		lst.anhade(3, 'D');
		muestraLista(lst);

		// recorre con el iterador y comprueba que los elementos estan en su sitio	
		char charEsperado = 'A';
		IIteradorSimple<Character> iter = lst.iterador();
		int cont = 0;
		while (iter.haySiguiente()) {
			char c = iter.siguiente();
			assertTrue("Error: c deberia ser " + charEsperado + ", pero es:" + c,
					c == charEsperado);
			charEsperado++; // pasa a la siguiente letra
			cont++; // cuenta los elementos iterados
		}
		assertTrue("Error: se han hecho " + cont + " iteraciones (deberían haber sido 4)",
				cont == 4);
	}

	@Test
	void testIteradorAnhade() {
		System.out.println("testIteradorAnhade");

		ILista<Character> lst = new ListaArray<Character>(5);

		// mete varios elementos en la lista
		lst.anhade(0, 'A');
		lst.anhade(1, 'B');
		lst.anhade(2, 'C');
		muestraLista(lst);

		// anhade un elemento en las posiciones impares
		IIteradorSimple<Character> iter = lst.iterador();
		while (iter.haySiguiente()) {
			iter.siguiente();

			iter.anhade('X');
		}
		muestraLista(lst); // [A, X, B, X, C, X]

		// comprueba el tamanho
		int tam = lst.tamanho();
		assertTrue("Error: el tamanho deberia ser 6, pero es:" + tam, tam == 6);

		// recorre con el iterador y comprueba que los elementos estan en su sitio	
		char charEsperado = 'A';
		iter = lst.iterador();
		int cont = 0;
		while (iter.haySiguiente()) {
			char c = iter.siguiente();
			assertTrue("Error: c deberia ser " + charEsperado + ", pero es:" + c,
					c == charEsperado);

			c = iter.siguiente();
			assertTrue("Error: c deberia ser X, pero es:" + c,
					c == 'X');

			charEsperado++; // pasa a la siguiente letra
			cont++; // cuenta las iteraciones
		}
		assertTrue("Error: se han hecho " + cont + " iteraciones (deberían haber sido 3)",
				cont == 3);
	}

	@Test
	void testIteradorAsigna() {
		System.out.println("testIteradorAsigna");

		ILista<Character> lst = new ListaArray<Character>(5);

		// mete varios elementos en la lista
		lst.anhade(0, 'A');
		lst.anhade(1, 'B');
		lst.anhade(2, 'C');
		lst.anhade(3, 'D');
		muestraLista(lst);
		
		// comprueba el tamanho
		int tam = lst.tamanho();
		assertTrue("Error: el tamanho deberia ser 4, pero es:" + tam, tam == 4);

		IIteradorSimple<Character> iter = lst.iterador();
		char newChar = 'D';
		try {	
			iter.asigna(newChar);
			fail("No excepción posicion incorrecta");
		} catch (NoSuchElementException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
		
		// asignamos elementos a la lista
		while (iter.haySiguiente()) {
			iter.siguiente();
			iter.asigna(newChar);
			newChar--;
		}
		muestraLista(lst);
		
		// recorre con el iterador y comprueba que los elementos estan en su sitio	
		char charEsperado = 'D';
		int contador = 0;
		IIteradorSimple<Character> iter2 = lst.iterador();
		while (iter2.haySiguiente()) {
			char c = iter2.siguiente();
			assertTrue("Error: c deberia ser " + charEsperado + ", pero es:" + c,
					c == charEsperado);
			charEsperado--; // pasa a la siguiente letra
			contador++;
		}
		assertTrue("Error: se han hecho " + contador + " iteraciones (deberían haber sido 4)",
				contador == 4);
	}

	/**
	 * Metodo auxiliar que muestra la lista por consola.
	 * @param lst lista a mostrar
	 */
	private void muestraLista(ILista<?> lst) {
		String str = "[";
		for (int i = 0; i < lst.tamanho(); i++) {
			try {
				str += lst.obtenElemento(i);
			} catch (Exception e) {
				// obtenElemento ha lanzado una excepcion
				str += "error";
			}
			if (i < lst.tamanho() - 1) {
				str = str + ", ";
			}
		}
		str += "]";
		System.out.println(str);
	}

}
