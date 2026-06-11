package pract05.c3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import pract05.ilista.*;


/**
 * Test del metodo reemplazaParejaElementos.
 *
 * @author Estructuras de Datos (UC)
 * @version oct-2023
 */
public class ReemplazaParejaTest {

	@Test
	void listaVaciaTest() {
		System.out.println("listaVaciaTest");
		ListaSimpleEnlace<String> lst = new ListaSimpleEnlace<>();
		
		// prueba que funciona con lista vacía
		lst.reemplazaParejaElementos("A", "B", "X");
		muestraLista(lst);
		compruebaLista(lst, Arrays.asList());
	}

	@Test
	void listaConUnElementoTest() {
		System.out.println("listaConUnElementoTest");
		ListaSimpleEnlace<String> lst = new ListaSimpleEnlace<>();
		lst.anhade(0, "A");	
		
		// prueba que funciona con una lista con un elemento
		lst.reemplazaParejaElementos("A", "B", "X");
		muestraLista(lst);
		compruebaLista(lst, Arrays.asList("A"));
	}

	@Test
	void listaConDosElementosTest() {
		System.out.println("listaConDosElementosTest");

		ListaSimpleEnlace<String> lst = new ListaSimpleEnlace<>();
		lst.anhade(0, "A");	
		lst.anhade(1, "B");
		muestraLista(lst);
		
		// prueba que funciona con una lista con dos elementos
		lst.reemplazaParejaElementos("A", "B", "X");
		muestraLista(lst);
		compruebaLista(lst, Arrays.asList("X"));
	}

	@Test
	void parejaAlPrincipioTest() {
		System.out.println("parejaAlPrincipioTest");

		ListaSimpleEnlace<String> lst = new ListaSimpleEnlace<>();

		lst.anhade(0, "A");	
		lst.anhade(1, "B");
		lst.anhade(2, "C");
		lst.anhade(3, "A");
		lst.anhade(4, "D");
		lst.anhade(5, "B");
		muestraLista(lst);
		
		// prueba para una lista que tiene la pareja en los dos primeros elementos
		lst.reemplazaParejaElementos("A", "B", "X");
		muestraLista(lst);		
		compruebaLista(lst, Arrays.asList("X", "C", "A", "D", "B"));
	}

	@Test
	void parejaAlMedioTest() {
		System.out.println("parejaAlMedioTest");

		ListaSimpleEnlace<String> lst = new ListaSimpleEnlace<>();

		lst.anhade(0, "A");	
		lst.anhade(1, "D");
		lst.anhade(2, "A");
		lst.anhade(3, "B");
		lst.anhade(4, "E");
		lst.anhade(5, "B");
		muestraLista(lst);
		
		// prueba para una lista que tiene la pareja en los elementos centrales
		lst.reemplazaParejaElementos("A", "B", "X");
		muestraLista(lst);		
		compruebaLista(lst, Arrays.asList("A", "D", "X", "E", "B"));
	}

	@Test
	void parejaAlFinalTest() {
		System.out.println("parejaAlFinalTest");

		ListaSimpleEnlace<String> lst = new ListaSimpleEnlace<>();

		lst.anhade(0, "A");	
		lst.anhade(1, "C");
		lst.anhade(2, "B");
		lst.anhade(3, "D");
		lst.anhade(4, "A");
		lst.anhade(5, "B");
		muestraLista(lst);
		
		// prueba para una lista que tiene la pareja en los elementos finales
		lst.reemplazaParejaElementos("A", "B", "X");
		muestraLista(lst);		
		compruebaLista(lst, Arrays.asList("A", "C", "B", "D", "X"));
	}

	@Test
	void dosParejasTest() {
		System.out.println("dosParejasTest");

		ListaSimpleEnlace<String> lst = new ListaSimpleEnlace<>();

		lst.anhade(0, "A");	
		lst.anhade(1, "B");
		lst.anhade(2, "C");
		lst.anhade(3, "A");
		lst.anhade(4, "B");
		lst.anhade(5, "D");
		lst.anhade(6, "B");
		lst.anhade(7, "A");
		muestraLista(lst);
		
		// prueba para una lista que tiene dos ocurrencias de la pareja
		lst.reemplazaParejaElementos("A", "B", "X");
		muestraLista(lst);		
		compruebaLista(lst, Arrays.asList("X", "C", "X", "D", "B", "A"));
	}

	/**
	 * Comprueba que la lista esta correcta comparando
	 * sus contenidos con la lista esperada.
	 * @param <E> tipo de elementos de la lista.
	 * @param lista lista a comprobar.
	 * @param lstEsperada lista esperada.
	 */
	private static <E> void compruebaLista(ILista<E> lista, 
			List<E> lstEsperada) {
		assertEquals(lstEsperada.size(), lista.tamanho(), "Tamanho lista incorrecto");

		for (int i = 0; i < lista.tamanho(); i++) {
			assertEquals(lstEsperada.get(i), lista.obtenElemento(i), "Elemento " + i);
		}
	}


	/**
	 * Metodo auxiliar que muestra la lista por consola.
	 * @param lst lista a mostrar
	 */
	private static void muestraLista(ILista<?> lst) {
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
