package pract12.p1_cola_prio_monticulo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Prueba de una cola de prioridad.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-24
 *
 */
public class ColaPrioMonticuloTest {

	/**
	 * Comprobaciones habituales sobre la cola. Prueba los metodos masPrioritario(), tamanho() y
	 * estaVacia(). Comprueba que el elemento menor es el esperado, que el tamanho es tambien
	 * el esperado y que la cola esta vacia si, y solo si, tamanho es 0.
	 * @param <T> tipo de elementos en la cola.
	 * @param cola cola a comprobar.
	 * @param menorEsperado elemento que debe ser el menor.
	 * @param tamanhoEsperado tamanho que debe tener la cola.
	 */
	private static <T> void compruebaCola(IColaPrioridad<T> cola,
			T menorEsperado, int tamanhoEsperado) {
		// muestra la cola, es necesario definir el metodo toString de la cola.
		System.out.println(cola);

		assertEquals("Error tamanho", tamanhoEsperado, cola.tamanho());

		T eleMasPrio = cola.masPrioritario();

		assertEquals("Error tamanho", tamanhoEsperado, cola.tamanho());

		// Salida mas explicativa para el menor esperado
		String menorEsperadoTexto = null;
		if (tamanhoEsperado == 0) {
			menorEsperadoTexto = String.valueOf(menorEsperado);
		} else {
			menorEsperadoTexto = "null (cola vacia)";
		}

		assertTrue("Error, menor encontrado: " + eleMasPrio +
				", menor esperado: " + menorEsperadoTexto, 
				(tamanhoEsperado == 0 && eleMasPrio == null) ||
				(tamanhoEsperado > 0 && eleMasPrio.equals(menorEsperado)));

		assertTrue("tamanho:" + tamanhoEsperado + " vacia:" + (cola.tamanho() == 0),
				(tamanhoEsperado == 0 && (cola.tamanho() == 0)) || 
				(tamanhoEsperado > 0 && (cola.tamanho() > 0)));
	}

	/**
	 * Prueba que el constructor crea una cola vacia.
	 */
	@Test
	public void testColaPrioMonticulo() {
		System.out.println("testColaPrioMonticulo");
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<>(8);
		compruebaCola(cola, null, 0);
	}

	/**
	 * Prueba a encolar y desencolar 1 elemento.
	 */
	@Test
	public void testColaSimple1Ele() {
		System.out.println("testColaSimple1Ele");
		IColaPrioridad<String> cola = new ColaPrioMonticulo<>(8);
		
		// encola
		cola.encolaConPrioridad("C");
		compruebaCola(cola, "C", 1);
		
		// desencola
		String ele = cola.desencolaMasPrioritario();
		assertEquals("Error ele desencolado", "C", ele);
		compruebaCola(cola, null, 0);		
	}

	/**
	 * Prueba a encolar y desencolar 2 elementos.
	 */
	@Test
	public void testColaSimple2Ele() {
		System.out.println("testColaSimple2Ele");
		IColaPrioridad<String> cola = new ColaPrioMonticulo<>(8);
		
		// encola un elemento
		cola.encolaConPrioridad("E");
		compruebaCola(cola, "E", 1);
		
		// encola un elemento mayor que la cabeza
		cola.encolaConPrioridad("X");
		compruebaCola(cola, "E", 2);
		
		// desencola el menor
		String ele = cola.desencolaMasPrioritario();
		assertEquals("E", ele);
		compruebaCola(cola, "X", 1);
		
		// encola un elemento menor que la cabeza
		cola.encolaConPrioridad("A");
		compruebaCola(cola, "A", 2);
		
		// desencola los 2 elementos
		ele = cola.desencolaMasPrioritario();
		assertEquals("A", ele);
		compruebaCola(cola, "X", 1);		
		ele = cola.desencolaMasPrioritario();
		assertEquals("X", ele);
		compruebaCola(cola, null, 0);
	}

	/**
	 * Prueba a encolar y desencolar 3 elementos.
	 */
	@Test
	public void testColaSimple3Ele() {
		System.out.println("testColaSimple3Ele");
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(8);
		int ele;
		
		// encola tres elementos hijo izq < hijo der
		cola.encolaConPrioridad(3);
		cola.encolaConPrioridad(4);
		cola.encolaConPrioridad(5);
		compruebaCola(cola, 3, 3);
		
		// desencola los 3
		ele = cola.desencolaMasPrioritario();
		compruebaCola(cola, 4, 2);
		assertEquals("Error elemento desencolado", 3, ele);
		ele = cola.desencolaMasPrioritario();
		compruebaCola(cola, 5, 1);
		assertEquals("Error elemento desencolado", 4, ele);
		ele = cola.desencolaMasPrioritario();
		compruebaCola(cola, null, 0);
		assertEquals("Error elemento desencolado", 5, ele);
		
		// encola tres elementos hijo izq > hijo der
		cola.encolaConPrioridad(3);
		cola.encolaConPrioridad(5);
		cola.encolaConPrioridad(4);
		compruebaCola(cola, 3, 3);
		
		// desencola los 3
		ele = cola.desencolaMasPrioritario();
		compruebaCola(cola, 4, 2);
		assertEquals("Error elemento desencolado", 3, ele);
		ele = cola.desencolaMasPrioritario();
		compruebaCola(cola, 5, 1);
		assertEquals("Error elemento desencolado", 4, ele);
		ele = cola.desencolaMasPrioritario();
		compruebaCola(cola, null, 0);
		assertEquals("Error elemento desencolado", 5, ele);
	}

	/**
	 * Prueba general de la cola, prueba los metodos encolaConPrioridad(),
	 * desencolaMasPrioritario(), masPrioritario(), tamanho() y estaVacia().
	 */
	@Test
	public void testCola() {
		System.out.println("testCola");
		Integer i;
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(8);
		compruebaCola(cola, null, 0);

		cola.encolaConPrioridad(3);
		compruebaCola(cola, 3, 1);

		cola.encolaConPrioridad(5);
		cola.encolaConPrioridad(1);
		cola.encolaConPrioridad(2);

		compruebaCola(cola, 1, 4);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == 1);		
		compruebaCola(cola, 2, 3);


		cola.encolaConPrioridad(2);
		cola.encolaConPrioridad(1);		
		i = cola.desencolaMasPrioritario();
		assertTrue(i == 1);		
		compruebaCola(cola, 2, 4);		
		i = cola.desencolaMasPrioritario();		
		i = cola.desencolaMasPrioritario();
		assertTrue(i == 2);		
		compruebaCola(cola, 3, 2);		
		i = cola.desencolaMasPrioritario();
		assertTrue(i == 3);		
		compruebaCola(cola, 5, 1);		
		i = cola.desencolaMasPrioritario();
		assertTrue(i == 5);		
		compruebaCola(cola, -3, 0);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == null);		
		compruebaCola(cola, -2, 0);
	}

	/**
	 * Prueba que la cola se puede llenar completamente y que sigue funcionando
	 * bien.
	 */
	@Test
	public void testColaLlena() {
		System.out.println("testColaLlena");
		Integer i;
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(8);

		// llena cola
		cola.encolaConPrioridad(5);
		cola.encolaConPrioridad(1);
		cola.encolaConPrioridad(2);
		cola.encolaConPrioridad(4);
		cola.encolaConPrioridad(2);
		cola.encolaConPrioridad(3);
		cola.encolaConPrioridad(2);
		cola.encolaConPrioridad(3);		
		compruebaCola(cola, 1, 8);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == 1);		
		compruebaCola(cola, 2, 7);

		cola.encolaConPrioridad(3);		
		compruebaCola(cola, 2, 8);
	}

	/**
	 * Prueba para superar la capacidad de la cola.
	 */
	@Test
	public void testColaSuperaCapacidad() {
		System.out.println("testColaSuperaCapacidad");
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(3);

		// cola llena tras encolar
		cola.encolaConPrioridad(5);
		cola.encolaConPrioridad(1);
		cola.encolaConPrioridad(4);
		compruebaCola(cola, 1, 3);

		// trata de anhadir un elemento a una cola llena
		try {
			cola.encolaConPrioridad(3);	
			fail("No excepcion cola llena");
		} catch (UnsupportedOperationException e) {
			// El comportamiento correcto es que se lance la excepcion
			// Simplemente la cojo para que no salga fuera del metodo y
			// JUnit lo interprete como un error
		}

		// cola llena tras haceVacia
		cola.haceVacia();
		compruebaCola(cola, null, 0);
		cola.encolaConPrioridad(5);
		cola.encolaConPrioridad(1);
		cola.encolaConPrioridad(4);
		compruebaCola(cola, 1, 3);
		// trata de anhadir un elemento a una cola llena
		try {
			cola.encolaConPrioridad(3);	
			fail("No excepcion cola llena");
		} catch (UnsupportedOperationException e) {
			// El comportamiento correcto es que se lance la excepcion
			// Simplemente la cojo para que no salga fuera del metodo y
			// JUnit lo interprete como un error
		}

	}

	/**
	 * Prueba la situacion en que el hueco creado al desencolar no hay que hundirle
	 * hasta el ultimo nivel.
	 */
	@Test
	public void testDesencolaMasPrioritario() {
		System.out.println("testDesencolaMasPrioritario");
		Integer i;
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(8);
		cola.encolaConPrioridad(1);
		cola.encolaConPrioridad(2);
		cola.encolaConPrioridad(3);
		cola.encolaConPrioridad(5);
		cola.encolaConPrioridad(6);
		cola.encolaConPrioridad(4);		
		compruebaCola(cola, 1, 6);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == 1);		
		compruebaCola(cola, 2, 5);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == 2);		
		compruebaCola(cola, 3, 4);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == 3);		
		compruebaCola(cola, 4, 3);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == 4);		
		compruebaCola(cola, 5, 2);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == 5);		
		compruebaCola(cola, 6, 1);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == 6);		
		compruebaCola(cola, -7, 0);
	}

	/**
	 * Prueba la situacion en que tenemos varios elementos iguales.
	 */
	@Test
	public void testDesencolaMenorIguales() {
		System.out.println("testDesencolaMenorIguales");
		Integer i;
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(8);
		cola.encolaConPrioridad(1);
		cola.encolaConPrioridad(2);
		cola.encolaConPrioridad(3);
		cola.encolaConPrioridad(5);
		cola.encolaConPrioridad(6);
		cola.encolaConPrioridad(3);		
		compruebaCola(cola, 1, 6);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == 1);		
		compruebaCola(cola, 2, 5);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == 2);		
		compruebaCola(cola, 3, 4);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == 3);		
		compruebaCola(cola, 3, 3);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == 3);		
		compruebaCola(cola, 5, 2);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == 5);		
		compruebaCola(cola, 6, 1);

		i = cola.desencolaMasPrioritario();
		assertTrue(i == 6);		
		compruebaCola(cola, -7, 0);
	}

	/**
	 * Prueba que haceVacia funciona correctamente.
	 */
	@Test
	public void testHaceVacia() {
		System.out.println("testHaceVacia");
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(8);
		compruebaCola(cola, null, 0);

		cola.encolaConPrioridad(3);		
		cola.encolaConPrioridad(2);		
		cola.desencolaMasPrioritario();		
		cola.encolaConPrioridad(2);

		cola.haceVacia();
		compruebaCola(cola, -3, 0);	

		cola.encolaConPrioridad(5);
		compruebaCola(cola, 5, 1);				
	}

	/**
	 * Prueba que haceVacia funciona correctamente sin desencolar.
	 */
	@Test
	public void testHaceVaciaSimple() {
		System.out.println("testHaceVaciaSimple");
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(8);
		compruebaCola(cola, null, 0);

		cola.encolaConPrioridad(3);		
		cola.encolaConPrioridad(2);		
		Integer i = cola.masPrioritario();
		assertTrue(i == 2);

		cola.haceVacia();
		compruebaCola(cola, null, 0);
		i = cola.masPrioritario();
		assertTrue(i == null);

		cola.encolaConPrioridad(5);
		compruebaCola(cola, 5, 1);				
	}

	/**
	 * Detecta el uso (erroneo) de "elementos[posHijoIzq(posHueco)] != null" en lugar del
	 * uso (correcto) "posHijoIzq(posHueco) <= numEle" para saber si un nodo tiene hijos.
	 */
	@Test
	public void testHijoFueraArray() {
		System.out.println("testHijoFueraArray");
		Integer ele;	
		
		// hijo derecho fuera del array
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(4);
		compruebaCola(cola, null, 0);

		cola.encolaConPrioridad(2);		
		cola.encolaConPrioridad(1);	
		cola.encolaConPrioridad(3);		
		cola.encolaConPrioridad(4);	
		compruebaCola(cola, 1, 4);
		
		ele = cola.desencolaMasPrioritario();
		assertEquals(1, ele.intValue()); 
		// XXX intValue es necesario para evitar error de compilacion "reference to
		// assertEquals is ambiguous" en el moodle de autoevaluacion
		
		ele = cola.desencolaMasPrioritario();
		assertEquals(2, ele.intValue());
		
		ele = cola.desencolaMasPrioritario();
		assertEquals(3, ele.intValue());	
		
		ele = cola.desencolaMasPrioritario();
		assertEquals(4, ele.intValue());
		
		// hijo izquierdo fuera del array
		cola = new ColaPrioMonticulo<Integer>(3);
		compruebaCola(cola, null, 0);

		cola.encolaConPrioridad(2);		
		cola.encolaConPrioridad(1);	
		cola.encolaConPrioridad(3);	
		compruebaCola(cola, 1, 3);
		
		ele = cola.desencolaMasPrioritario();
		assertEquals(1, ele.intValue());
		
		ele = cola.desencolaMasPrioritario();
		assertEquals(2, ele.intValue());
		
		ele = cola.desencolaMasPrioritario();
		assertEquals(3, ele.intValue());
	}

}
