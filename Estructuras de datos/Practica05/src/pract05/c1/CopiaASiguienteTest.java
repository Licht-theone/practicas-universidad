package pract05.c1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Test del metodo copiaASiguiente.
 *
 * @author Estructuras de Datos (UC)
 * @version oct-2020
 */
public class CopiaASiguienteTest {

	@Test
	void listaVaciaTest() {
		System.out.println("listaVaciaTest");
		List<String> lista = new LinkedList<>();
		
		// prueba que funciona con lista vacía
		CopiaASiguiente.copiaASiguiente(lista, new String("X"));
		System.out.println(" Lista final:" + lista);
		assertEquals(Arrays.asList(), lista);
	}

	@Test
	void listaConUnElementoTest() {
		System.out.println("listaConUnElementoTest");
		List<String> lista = new LinkedList<>();
		lista.add("A");	
		System.out.println(" Lista original:" + lista);
		
		// prueba que funciona con una lista con un elemento
		CopiaASiguiente.copiaASiguiente(lista, new String("A"));
		System.out.println(" Lista final:" + lista);
		assertEquals(Arrays.asList("A"), lista);
	}

	@Test
	void listaConDosElementosTest() {
		System.out.println("listaConDosElementosTest");

		List<String> lista = new LinkedList<>();
		lista.add("A");	
		lista.add("B");
		System.out.println(" Lista original:" + lista);
		
		// prueba que funciona con una lista con dos elementos
		CopiaASiguiente.copiaASiguiente(lista, new String("A"));
		System.out.println(" Lista final:" + lista);
		assertEquals(Arrays.asList("A", "A"), lista);
	}

	@Test
	void listaSinElementoTest() {
		System.out.println("listaSinElementoTest");

		List<String> lista = new LinkedList<>();
		lista.add("A");	
		lista.add("B");	
		lista.add("C");
		lista.add("D");
		lista.add("E");
		System.out.println(" Lista original:" + lista);
		
		CopiaASiguiente.copiaASiguiente(lista, new String("X"));
		System.out.println(" Lista final:" + lista);
		assertEquals(Arrays.asList("A", "B", "C", "D", "E"), lista);
	}

	@Test
	void elementoAlPrincipioTest() {
		System.out.println("elementoAlPrincipioTest");

		List<String> lista = new LinkedList<>();
		lista.add("B");	
		lista.add("C");
		lista.add("D");
		System.out.println(" Lista original:" + lista);
		
		CopiaASiguiente.copiaASiguiente(lista, new String("B"));
		System.out.println(" Lista final:" + lista);
		assertEquals(Arrays.asList("B", "B", "D"), lista);
	}

	@Test
	void elementoAlMedioTest() {
		System.out.println("elementoAlMedioTest");

		List<String> lista = new LinkedList<>();
		lista.add("B");			
		lista.add("C");	
		lista.add("D");
		lista.add("E");
		System.out.println(" Lista original:" + lista);
		
		CopiaASiguiente.copiaASiguiente(lista, new String("C"));
		System.out.println(" Lista final:" + lista);
		assertEquals(Arrays.asList("B", "C", "C", "E"), lista);
	}

	@Test
	void elementoAlMedioNoEqualsTest() {
		// funciona aunque no se haya usado equals en la comparacion
		System.out.println("elementoAlMedioNoEqualsTest");

		List<String> lista = new LinkedList<>();
		lista.add("B");			
		lista.add("C");	
		lista.add("D");
		lista.add("E");
		System.out.println(" Lista original:" + lista);
		
		CopiaASiguiente.copiaASiguiente(lista, "C");
		System.out.println(" Lista final:" + lista);
		assertEquals(Arrays.asList("B", "C", "C", "E"), lista);
	}

	@Test
	void elementoAlFinalTest() {
		System.out.println("elementoAlFinalTest");

		List<String> lista = new LinkedList<>();
		lista.add("A");			
		lista.add("B");			
		lista.add("C");	
		lista.add("D");
		lista.add("E");
		System.out.println(" Lista original:" + lista);
		
		CopiaASiguiente.copiaASiguiente(lista, new String("E"));
		System.out.println(" Lista final:" + lista);
		assertEquals(Arrays.asList("A", "B", "C", "D", "E"), lista);
	}

	@Test
	void dosOcurrenciasTest() {
		System.out.println("dosOcurrenciasTest");

		List<String> lista = new LinkedList<>();
		lista.add("A");				
		lista.add("B");				
		lista.add("C");			
		lista.add("D");	
		lista.add("B");
		lista.add("E");
		lista.add("F");
		lista.add("B");
		System.out.println(" Lista original:" + lista);
		
		// prueba para una lista que tiene dos ocurrencias de la pareja
		CopiaASiguiente.copiaASiguiente(lista, new String("B"));
		System.out.println(" Lista final:" + lista);
		assertEquals(Arrays.asList("A", "B", "B", "D", "B", "B", "F", "B"), lista);
	}

}
