package pract06.cola_array_circular;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test de implementaciones del TDA Cola.
 *
 * @author Estructuras de Datos (UC)
 * @version oct-2021
 */
public class ColaTest {
	
	@Test
	void testConstructor() {
		System.out.println("testConstructor");
		
		ICola<Integer> cola = new ColaArrayCircular<Integer>();
		assertTrue(cola.tamanho() == 0);
	
		System.out.println(" nueva:" + cola);
	}

	@Test
	void testHaceVacia() {
		System.out.println("testHaceVacia");
		
		ICola<Integer> cola = new ColaArrayCircular<Integer>();
		assertTrue(cola.tamanho() == 0);
		
		System.out.println(" nueva:" + cola);
		
		// encola un elemento
		cola.encola(1);
		assertTrue(cola.tamanho() == 1);
		
		System.out.println(" encola:" + cola);
		
		// vacia la cola y comprueba que esta vacia
		cola.haceVacia();
		assertTrue(cola.tamanho() == 0);
		
		System.out.println(" vacia:" + cola);
	}
	
	@Test
	void testDesencolaErrorVacia() {
		System.out.println("testDesencolaErrorVacia");
		ICola<Character> cola = new ColaArrayCircular<Character>();
		Character frente;
		
		// comprueba que la cima de una pila vacia es null
		frente = cola.desencola();
		assertNull(frente);
		assertEquals(0, cola.tamanho());
		
		// encola, desencola y comprueba que frente es null
		cola.encola('a');
		assertEquals(1, cola.tamanho());
		frente = cola.desencola();
		assertEquals('a', frente);
		assertEquals(0, cola.tamanho());
		frente = cola.desencola();
		assertNull(frente);
		assertEquals(0, cola.tamanho());
	}
	
	@Test
	void testFrenteErrorVacia() {
		System.out.println("testFrenteErrorVacia");
		ICola<Character> cola = new ColaArrayCircular<Character>();
		Character frente;
		
		// comprueba que la cima de una pila vacia es null
		frente = cola.frente();
		assertNull(frente);
		assertEquals(0, cola.tamanho());
		
		// encola, desencola y comprueba que frente es null
		cola.encola('a');;
		frente = cola.desencola();
		assertEquals('a', frente);
		frente = cola.frente();
		assertNull(frente);
		assertEquals(0, cola.tamanho());
	}

	@Test
	void testEncolaDesencola() {
		System.out.println("testEncolaDesencola");
		
		ICola<Integer> cola = new ColaArrayCircular<Integer>();
		assertTrue(cola.tamanho() == 0);
		assertTrue(cola.frente() == null);
		
		System.out.println(" nueva:" + cola);
		
		// encola elemento
		cola.encola(1);
		assertTrue(cola.tamanho() == 1);
		assertTrue(cola.frente() == 1);
		
		System.out.println(" encola:" + cola);
		
		// desencola dejando la cola vacia
		cola.desencola();
		assertTrue(cola.tamanho() == 0);
		assertTrue(cola.frente() == null);
		
		System.out.println(" desencola:" + cola);
		
		// encola primer elemento
		cola.encola(2);
		assertTrue(cola.tamanho() == 1);
		assertTrue(cola.frente() == 2);
		
		System.out.println(" encola:" + cola);
		
		// encola segundo elemento
		cola.encola(3);
		assertTrue(cola.tamanho() == 2);
		assertTrue(cola.frente() == 2);
		
		System.out.println(" encola:" + cola);
		
		// encola tercer elemento
		cola.encola(4);
		assertTrue(cola.tamanho() == 3);
		assertTrue(cola.frente() == 2);
		
		System.out.println(" encola:" + cola);
		
		// desencola primer elemento
		Integer e;
		e = cola.desencola();
		assertTrue(cola.tamanho() == 2);
		assertTrue(e == 2);
		assertTrue(cola.frente() == 3);
		
		System.out.println(" desencola:" + e + " cola:" + cola);
		
		// desencola segundo elemento
		e = cola.desencola();
		assertTrue(cola.tamanho() == 1);
		assertTrue(e == 3);
		assertTrue(cola.frente() == 4);
		
		System.out.println(" desencola:" + e + " cola:" + cola);
		
		// desencola tercer elemento
		e = cola.desencola();
		assertTrue(cola.tamanho() == 0);
		assertTrue(e == 4);
		assertTrue(cola.frente() == null);
		
		System.out.println(" desencola:" + e + " cola:" + cola);	
	}
	
	@Test
	void testDesencolaEnColaVacia() {
		System.out.println("testDesencolaEnColaVacia");
		
		Integer e;
		ICola<Integer> cola = new ColaArrayCircular<Integer>();
		assertTrue(cola.tamanho() == 0);
		assertTrue(cola.frente() == null);
		
		System.out.println(" nueva:" + cola);
		
		// desencola en cola recien creada
		e = cola.desencola();
		assertTrue(cola.tamanho() == 0);
		assertTrue(e == null);
		assertTrue(cola.frente() == null);
				
		// encola elemento
		cola.encola(1);
		assertTrue(cola.tamanho() == 1);
		assertTrue(cola.frente() == 1);
		
		System.out.println(" encola:" + cola);
		
		// desencola dejando la cola vacia
		cola.desencola();
		assertTrue(cola.tamanho() == 0);
		assertTrue(cola.frente() == null);
		
		System.out.println(" desencola:" + cola);
		
		// desencola con cola vacia
		e = cola.desencola();
		assertTrue(cola.tamanho() == 0);
		assertTrue(e == null);
		assertTrue(cola.frente() == null);
		
		System.out.println(" desencola:" + e + " cola:" + cola);		
	}
	
	@Test
	void testEncolaDesencolaSimple() {
		System.out.println("testEncolaDesencolaSimple");
		
		ICola<Integer> cola = new ColaArrayCircular<Integer>();
		assertTrue(cola.tamanho() == 0);
		assertTrue(cola.frente() == null);
		
		System.out.println(" nueva:" + cola);
		
		// encola elemento
		cola.encola(1);
		assertTrue(cola.tamanho() == 1);
		assertTrue(cola.frente() == 1);
		
		System.out.println(" encola:" + cola);
		
		// encola segundo elemento
		cola.encola(2);
		assertTrue(cola.tamanho() == 2);
		assertTrue(cola.frente() == 1);
		
		System.out.println(" encola:" + cola);
		
		// desencola primer elemento
		Integer e;
		e = cola.desencola();
		assertTrue(cola.tamanho() == 1);
		assertTrue(e == 1);
		assertTrue(cola.frente() == 2);
				
		System.out.println(" desencola:" + e + " cola:" + cola);
				
		// desencola el segundo
		e = cola.desencola();
		assertTrue(cola.tamanho() == 0);
		assertTrue(e == 2);
		assertTrue(cola.frente() == null);
				
		System.out.println(" desencola:" + e + " cola:" + cola);
		
	}
	
	@Test
	void testEncolaLimites() {
		System.out.println("testEncolaLimites");
		
		ICola<Integer> cola = new ColaArrayCircular<Integer>(10);
		assertTrue(cola.tamanho() == 0);
		assertTrue(cola.frente() == null);
		
		System.out.println(" nueva:" + cola);
		
		// encola elementos
		cola.encola(1);
		cola.encola(2);
		cola.encola(3);
		cola.encola(4);
		cola.encola(5);
		assertTrue(cola.tamanho() == 5);
		assertTrue(cola.frente() == 1);
		
		System.out.println(" encola 5 elementos:" + cola);
		
		
		// desencola primer elemento
		Integer e;
		e = cola.desencola();
		assertTrue(cola.tamanho() == 4);
		assertTrue(e == 1);
		assertTrue(cola.frente() == 2);
				
		System.out.println(" desencola:" + e + " cola:" + cola);
				
		// desencola el segundo
		e = cola.desencola();
		assertTrue(cola.tamanho() == 3);
		assertTrue(e == 2);
		assertTrue(cola.frente() == 3);
				
		System.out.println(" desencola:" + e + " cola:" + cola);
		
		//encolamos otros 7 
		cola.encola(6);
		cola.encola(7);
		cola.encola(8);
		cola.encola(9);
		cola.encola(10);
		cola.encola(11);
		cola.encola(12);
		assertTrue(cola.tamanho() == 10);
		assertTrue(cola.frente() == 3);
		System.out.println(" encola otros 7 elementos:" + cola);
		
		//encolamos con la cola llena
		try {
			cola.encola(13);
			fail("No excepción. No se puede encolar con la cola llena.");
		} catch (UnsupportedOperationException ex) {
			//debe saltar la excepción. No hago nada para que el codigo continue.
		}
		
		//desencolamos hasta que quede uno
		cola.desencola();
		cola.desencola();
		cola.desencola();
		cola.desencola();
		cola.desencola();
		cola.desencola();
		cola.desencola();
		cola.desencola();
		cola.desencola();
		assertTrue(cola.tamanho() == 1);
		assertTrue(cola.frente() == 12);
		System.out.println(" Desencolamos hasta que quede uno:" + cola);
		
		//dejamos vacia
		e = cola.desencola();
		assertTrue(cola.tamanho() == 0);
		assertTrue(e == 12);
		assertTrue(cola.frente() == null);
				
		System.out.println(" desencola:" + e + " cola:" + cola);
	}
	
}
