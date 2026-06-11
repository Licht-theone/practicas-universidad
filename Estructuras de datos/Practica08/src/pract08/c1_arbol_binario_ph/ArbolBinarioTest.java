package pract08.c1_arbol_binario_ph;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test de un arbol binario (No incluye el test de las
 * operaciones de ramas).
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2020
 */
public class ArbolBinarioTest {

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
