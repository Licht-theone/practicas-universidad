package pract01;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.jupiter.api.Test;

/**
 * Test de una lista con acceso unicamente posicional (no tiene iterador).
 *
 * @author Estructuras de Datos (UC)
 * @version sep-2024
 */
class ListaNoIterTest {

	@Test
	void testConstructor() {
		System.out.println("testConstructor");

		IListaNoIter<String> lst = new ListaArrayNoIter<String>(5);

		// comprueba que la lista comienza vacia
		muestraLista(lst);
		int tam = lst.tamanho();
		assertTrue("Error: tamanho:" + tam + " deberia ser 0", tam == 0);

		// comprueba que se produce un error al acceder al primer elemento
		try {
			lst.obtenElemento(0);
			fail("No excepcion posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepcion
			// Simplemente la cojo para que no salga fuera del metodo y
			// JUnit lo interprete como un error
		}
	}

	@Test
	void testAnhadePrimerElemento() {
		System.out.println("testAnhadePrimerElemento");

		IListaNoIter<String> lst = new ListaArrayNoIter<String>(5);
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

		IListaNoIter<String> lst = new ListaArrayNoIter<String>(5);
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

		IListaNoIter<String> lst = new ListaArrayNoIter<String>(5);
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

		IListaNoIter<String> lst = new ListaArrayNoIter<String>(5);
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

		IListaNoIter<String> lst = new ListaArrayNoIter<String>(capacidadInicial);
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

		IListaNoIter<String> lst = new ListaArrayNoIter<String>(5);
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
			fail("No excepcion posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepcion
			// Simplemente la cojo para que no salga fuera del metodo y
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
			fail("No excepcion posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepcion
			// Simplemente la cojo para que no salga fuera del metodo y
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
			fail("No excepcion posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepcion
			// Simplemente la cojo para que no salga fuera del metodo y
			// JUnit lo interprete como un error
		}

		tam = lst.tamanho();
		assertTrue("Error: el tamanho deberia ser 1, pero es:" + tam, tam == 1);
	}

	@Test
	void testEliminaLimiteCapacidad() {
		System.out.println("testEliminaLimiteCapacidad");

		IListaNoIter<String> lst = new ListaArrayNoIter<String>(5);
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
			fail("No excepcion posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepcion
			// Simplemente la cojo para que no salga fuera del metodo y
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

		IListaNoIter<String> lst = new ListaArrayNoIter<String>(5);

		// posicion negativa
		try {
			lst.anhade(-1, "Z");
			fail("No excepcion posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepcion
			// Simplemente la cojo para que no salga fuera del metodo y
			// JUnit lo interprete como un error
		}	
		muestraLista(lst);

		try {
			lst.elimina(-1);
			fail("No excepcion posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepcion
			// Simplemente la cojo para que no salga fuera del metodo y
			// JUnit lo interprete como un error
		}
		try {
			lst.obtenElemento(-1);
			fail("No excepcion posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepcion
			// Simplemente la cojo para que no salga fuera del metodo y
			// JUnit lo interprete como un error
		}

		// posicion correcta
		lst.anhade(0, "A");
		muestraLista(lst);

		// posicion fuera de la lista
		try {
			lst.anhade(2, "B");
			fail("No excepcion posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepcion
			// Simplemente la cojo para que no salga fuera del metodo y
			// JUnit lo interprete como un error
		}
		muestraLista(lst);

		try {
			lst.elimina(2);
			fail("No excepcion posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepcion
			// Simplemente la cojo para que no salga fuera del metodo y
			// JUnit lo interprete como un error
		}

		try {
			lst.obtenElemento(2);
			fail("No excepcion posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepcion
			// Simplemente la cojo para que no salga fuera del metodo y
			// JUnit lo interprete como un error
		}	
	}

	@Test
	void testBusca() {
		System.out.println("testBusca");
		int pos;

		IListaNoIter<String> lst = new ListaArrayNoIter<String>(7);

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

		IListaNoIter<String> lst = new ListaArrayNoIter<String>(5);
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
			fail("No excepcion posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepcion
			// Simplemente la cojo para que no salga fuera del metodo y
			// JUnit lo interprete como un error
		}
	}

	/**
	 * Metodo auxiliar que muestra la lista por consola.
	 * @param lst lista a mostrar
	 */
	private void muestraLista(IListaNoIter<?> lst) {
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
