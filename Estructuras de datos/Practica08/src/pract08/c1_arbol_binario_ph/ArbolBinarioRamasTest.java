package pract08.c1_arbol_binario_ph;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test de un arbol binario (Incluye el test de las
 * operaciones de ramas).
 * 
 * @author Estructuras de Datos (UC)
 * @version oct-2022
 */
public class ArbolBinarioRamasTest {

	@Test
	void testConstructorContenidoRaiz() {
		System.out.println("testConstructorContenidoRaiz");
		IArbolBinario<Integer> a = new ArbolBinarioPH<Integer>(5);

		assertTrue(a.tamanho() == 1);

		assertTrue(a.raiz().contenido() == 5);		
		assertTrue(a.raiz().padre() == null);		
		assertTrue(a.raiz().hijoIzq() == null);		
		assertTrue(a.raiz().hijoDer() == null);

		muestraEnPreordenLN(a.raiz());
	}

	@Test
	void testConstructorArbolVacio() {
		System.out.println("testConstructorArbolVacio");
		IArbolBinario<Integer> a = new ArbolBinarioPH<Integer>();

		assertTrue(a.tamanho() == 0);

		assertTrue(a.raiz() == null);
	}

	@Test
	void testTamanho() {
		System.out.println("testTamanho");
		IArbolBinario<Integer> a = new ArbolBinarioPH<Integer>(3);

		assertTrue(a.tamanho() == 1);

		INodoArbolBinario<Integer> nodo = a.raiz();
		// Crea paso a paso un arbol como este:
		//           3
		//    31          32
		// 311               322
		nodo.anhadeHijoIzq(31);
		muestraEnPreordenLN(a.raiz());
		assertTrue(a.tamanho() == 2);

		nodo = nodo.hijoIzq();
		nodo.anhadeHijoDer(311);
		muestraEnPreordenLN(a.raiz());
		assertTrue(a.tamanho() == 3);

		nodo = nodo.padre();
		nodo.anhadeHijoDer(32); 
		muestraEnPreordenLN(a.raiz());       
		assertTrue(a.tamanho() == 4);

		nodo = nodo.hijoDer();
		nodo.anhadeHijoDer(322); 
		muestraEnPreordenLN(a.raiz());
		assertTrue(a.tamanho() == 5);
	}

	@Test
	void testInsertaHijosYRecorre() {
		System.out.println("testInsertaHijosYRecorre");
		IArbolBinario<Integer> a = new ArbolBinarioPH<Integer>(3);
		muestraEnPreordenLN(a.raiz());

		INodoArbolBinario<Integer> nodo = a.raiz();	
		nodo.anhadeHijoIzq(31); 
		muestraEnPreordenLN(a.raiz());
		assertTrue(a.tamanho() == 2);

		nodo.anhadeHijoDer(32);
		muestraEnPreordenLN(a.raiz());
		assertTrue(a.tamanho() == 3);

		nodo = nodo.hijoDer();
		nodo.anhadeHijoDer(322);
		muestraEnPreordenLN(a.raiz());
		assertTrue(a.tamanho() == 4);
		//          3
		//    31          32
		//                  322

		// recorre el arbol creado
		nodo = a.raiz();
		assertTrue(nodo.contenido() == 3);
		nodo = nodo.hijoIzq();		
		assertTrue(nodo.contenido() == 31);
		nodo = nodo.padre();
		nodo = nodo.hijoDer();		
		assertTrue(nodo.contenido() == 32);
		nodo = nodo.hijoDer();		
		assertTrue(nodo.contenido() == 322);
	}

	@Test
	void testErrorAnhadeYaExistente() {
		System.out.println("testErrorAnhadeYaExistente");
		IArbolBinario<Integer> a = new ArbolBinarioPH<Integer>(1);

		INodoArbolBinario<Integer> nodo = a.raiz();
		nodo.anhadeHijoIzq(2);
		nodo.anhadeHijoDer(3);

		// Debe fallar al tratar de anhadir el hijo izquierdo otra vez
		try {
			nodo.anhadeHijoIzq(2);
			fail("No excepción UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		// Debe fallar al tratar de anhadir el hijo derecho otra vez
		try {
			nodo.anhadeHijoDer(3);
			fail("No excepción UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
	}

	@Test
	void testModifica() {
		System.out.println("testModifica");
		IArbolBinario<Integer> a = new ArbolBinarioPH<Integer>(4);
		INodoArbolBinario<Integer> nodo = a.raiz();

		nodo.anhadeHijoIzq(41);  //             4
		nodo.anhadeHijoDer(42);  //    41              42
		nodo = nodo.hijoIzq();   //      412              422
		nodo.anhadeHijoDer(412);
		nodo = nodo.padre();
		nodo = nodo.hijoDer();
		nodo.anhadeHijoDer(422);
		assertTrue(a.tamanho() == 5);

		muestraEnPreordenLN(a.raiz());

		// modifica el arbol
		nodo = a.raiz();            //             6
		nodo.cambiaContenido(6);    //    61              42
		nodo = nodo.hijoIzq();      //      412              622
		nodo.cambiaContenido(61);
		nodo = nodo.padre();
		nodo = nodo.hijoDer();
		nodo = nodo.hijoDer();
		nodo.cambiaContenido(622);

		muestraEnPreordenLN(a.raiz());

		// comprueba que esta bien
		nodo = a.raiz();
		assertTrue(nodo.contenido() == 6);
		nodo = nodo.hijoIzq();
		assertTrue(nodo.contenido() == 61);
		nodo = nodo.hijoDer();
		assertTrue(nodo.contenido() == 412);
		nodo = nodo.padre();
		nodo = nodo.padre();
		nodo = nodo.hijoDer();
		assertTrue(nodo.contenido() == 42);
		nodo = nodo.hijoDer();
		assertTrue(nodo.contenido() == 622);
	}

	@Test
	void testVaciaYCambiaRaiz() {
		System.out.println("testVaciaYCambiaRaiz");
		IArbolBinario<Integer> a = new ArbolBinarioPH<Integer>(4);
		INodoArbolBinario<Integer> nodo = a.raiz();
		nodo.anhadeHijoIzq(41);
		assertTrue(a.tamanho() == 2);

		// anhade raiz debe fallar en un arbol que no esta vacio
		try {
			a.anhadeRaiz(123);
			fail("No excepción UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		// vacia
		a.haceVacio();
		assertTrue(a.tamanho() == 0);
		assertTrue(a.raiz() == null);

		// al anhadir una nueva raiz es posible seguir trabajando con
		// el arbol
		a.anhadeRaiz(1);
		nodo = a.raiz();
		nodo.anhadeHijoIzq(2);
		nodo.anhadeHijoDer(3);
		muestraEnPreordenLN(a.raiz());
		assertTrue(a.tamanho() == 3);

		// se puede recorrer el arbol
		nodo = a.raiz();
		nodo = nodo.hijoDer();
		assertTrue(nodo.contenido() == 3);	
	}	

	@Test
	void testErrorAnhadeRamaYaExistente() {
		System.out.println("testErrorAnhadeRamaYaExistente");
		IArbolBinario<Integer> a = new ArbolBinarioPH<Integer>(1);

		INodoArbolBinario<Integer> nodo = a.raiz();
		nodo.anhadeHijoIzq(2);
		nodo.anhadeHijoDer(3);

		IArbolBinario<Integer> a2 = new ArbolBinarioPH<Integer>(3);

		// Debe fallar al tratar de anhadir la rama izq
		try {
			nodo.anhadeRamaIzq(a2);
			fail("No excepción UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		// Debe fallar al tratar de anhadir la rama der
		try {
			nodo.anhadeRamaDer(a2);
			fail("No excepción UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
	}	

	@Test
	void testCortaRamaNoExistente() {
		System.out.println("testErrorCortaRamaNoExistente");
		IArbolBinario<Integer> a = new ArbolBinarioPH<Integer>(1);

		INodoArbolBinario<Integer> nodo = a.raiz();

		// Debe retornar un arbol vacio al cortar la rama izq
		IArbolBinario<Integer> a2 = nodo.cortaRamaIzq();
		assertNull(a2.raiz());
		assertEquals(0, a2.tamanho());
		assertEquals(1, a.tamanho());

		// Debe retornar un arbol vacio al cortar la rama der
		IArbolBinario<Integer> a3 = nodo.cortaRamaIzq();
		assertNull(a3.raiz());
		assertEquals(0, a3.tamanho());
		assertEquals(1, a.tamanho());
	}

	@Test
	void testAnhadeRama() {
		System.out.println("testAnhadeRama");
		IArbolBinario<Integer> a = new ArbolBinarioPH<Integer>(4);
		{
			INodoArbolBinario<Integer> nodo = a.raiz();

			nodo.anhadeHijoIzq(41);  //             4
			nodo.anhadeHijoDer(42);  //    41              42
			nodo = nodo.hijoIzq();   //      412              422
			nodo.anhadeHijoDer(412);
			nodo = nodo.padre();
			nodo = nodo.hijoDer();
			nodo.anhadeHijoDer(422);
			assertTrue(a.tamanho() == 5);
		}
		final int tamanhoOrigArbol = a.tamanho();

		muestraEnPreordenLN(a.raiz());

		// crea una rama
		IArbolBinario<Integer> r1 = new ArbolBinarioPH<Integer>(5);
		{
			INodoArbolBinario<Integer> nodo2 = r1.raiz();
			nodo2.anhadeHijoIzq(51);   //          5
			nodo2.anhadeHijoDer(52);   //    51          52
			nodo2 = nodo2.hijoDer();   //             521
			nodo2.anhadeHijoIzq(521);
		}
		final int tamanhoRama1 = r1.tamanho();
		
		// anhade la rama como hijo izq del nodo 41
		{
			INodoArbolBinario<Integer> nodo = a.raiz();
			nodo = nodo.hijoIzq();
			nodo.anhadeRamaIzq(r1);

			muestraEnPreordenLN(a.raiz());
			assertTrue(nodo.hijoIzq().contenido().equals(5));
			assertTrue(a.tamanho() == tamanhoOrigArbol + tamanhoRama1);
			// el padre de la raiz de la rama debe ser el nodo
			assertTrue(nodo.hijoIzq().padre() == nodo);
		}
		
		// crea otra rama
		IArbolBinario<Integer> r2 = new ArbolBinarioPH<Integer>(6);
		{
			INodoArbolBinario<Integer> nodo2 = r2.raiz();   //     6
			nodo2.anhadeHijoIzq(61);                        //  61
		}
		final int tamanhoRama2 = r2.tamanho();
		
		// anhade la rama como hijo der del nodo 422
		{
			INodoArbolBinario<Integer> nodo = a.raiz();
			nodo = nodo.hijoDer().hijoDer();
			nodo.anhadeRamaDer(r2);

			muestraEnPreordenLN(a.raiz());
			assertTrue(nodo.hijoDer().contenido().equals(6));
			assertTrue(a.tamanho() == tamanhoOrigArbol + tamanhoRama1 +
					tamanhoRama2);
			// el padre de la raiz de la rama debe ser el nodo
			assertTrue(nodo.hijoDer().padre() == nodo);
		}	
	}

	@Test
	void testCortaRama() {
		System.out.println("testCortaRama");
		IArbolBinario<Integer> a = new ArbolBinarioPH<Integer>(4);
		INodoArbolBinario<Integer> nodo = a.raiz();

		nodo.anhadeHijoIzq(41);  //             4
		nodo.anhadeHijoDer(42);  //    41              42
		nodo = nodo.hijoIzq();   //      412              422
		nodo.anhadeHijoDer(412);
		nodo = nodo.padre();
		nodo = nodo.hijoDer();
		nodo.anhadeHijoDer(422);
		assertTrue(a.tamanho() == 5);

		muestraEnPreordenLN(a.raiz());

		// corta rama
		nodo = a.raiz();
		IArbolBinario<Integer> rama = nodo.cortaRamaDer();

		// comprueba que los tamanhos son correctos
		assertTrue(a.tamanho() == 3);
		assertTrue(rama.tamanho() == 2);

		// comprueba que los arboles estan bien
		assertTrue(a.raiz().contenido() == 4);
		assertTrue(a.raiz().hijoIzq().contenido() == 41);
		assertTrue(a.raiz().hijoIzq().hijoDer().contenido() == 412);
		assertTrue(rama.raiz().contenido() == 42);
		assertTrue(rama.raiz().hijoDer().contenido() == 422);

		// muestra arbol
		System.out.print("Arbol:");
		muestraEnPreordenLN(a.raiz());

		// muestra la rama
		System.out.print("Rama:");
		muestraEnPreordenLN(a.raiz());
	}

	/**
	 * Metodo auxiliar que muestra el arbol en preorden.
	 * Finaliza con un salto de linea.
	 * @param nodo raiz del arbol a mostrar.
	 */
	private static void muestraEnPreordenLN(INodoArbolBinario<Integer> nodo) {
		muestraEnPreorden(nodo);
		System.out.println();
	}

	/**
	 * Metodo auxiliar que muestra el arbol en preorden.
	 * @param nodo raiz del arbol a mostrar.
	 */
	private static void muestraEnPreorden(INodoArbolBinario<Integer> nodo) {
		// muestra el contenido del nodo actual
		System.out.print(nodo.contenido() + "  ");

		// muestra el subarbol del hijo izquierdo (si le hay)
		if (nodo.hijoIzq() != null) {
			muestraEnPreorden(nodo.hijoIzq());
		}

		// muestra el subarbol del hijo derecho (si le hay)
		if (nodo.hijoDer() != null) {
			muestraEnPreorden(nodo.hijoDer());
		}
	}
}
