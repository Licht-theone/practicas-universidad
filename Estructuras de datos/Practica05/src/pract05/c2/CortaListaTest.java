package pract05.c2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import pract05.ilista.ILista;


/**
 * Test del metodo cortaLista.
 *
 * @author Estructuras de Datos (UC)
 * @version oct-2023
 */
public class CortaListaTest {

	@Test
	void listaVaciaTest() {
		System.out.println("listaVaciaTest");
		ListaDobleEnlace<String> lstOrig = new ListaDobleEnlace<>();
		ListaDobleEnlace<String> lstRet;
		
		// prueba que funciona con lista vacía
		lstRet = lstOrig.cortaLista("X");
		muestraLista(" lstRet", lstRet);
		compruebaLista(lstRet, Arrays.asList());
		muestraLista(" lstOrig", lstOrig);
		compruebaLista(lstOrig, Arrays.asList());
	}

	@Test
	void listaSinElementoTest() {
		System.out.println("listaSinElementoTest");
		ListaDobleEnlace<String> lstOrig = new ListaDobleEnlace<>();
		ListaDobleEnlace<String> lstRet;

		lstOrig.anhade(0, "A");	
		lstOrig.anhade(1, "B");
		lstOrig.anhade(2, "C");
		lstOrig.anhade(3, "D");
		lstOrig.anhade(4, "E");
		lstOrig.anhade(5, "F");
		muestraLista(" lstOrig", lstOrig);
		
		// prueba con una lista sin el elemento de corte
		lstRet = lstOrig.cortaLista("X");
		muestraLista(" lstRet", lstRet);
		compruebaLista(lstRet, Arrays.asList());
		muestraLista(" lstOrig", lstOrig);
		compruebaLista(lstOrig, Arrays.asList("A", "B", "C", "D", "E", "F"));
	}

	@Test
	void cortePrimerEleTest() {
		System.out.println("cortePrimerEleTest");
		ListaDobleEnlace<String> lstOrig = new ListaDobleEnlace<>();
		ListaDobleEnlace<String> lstRet;

		lstOrig.anhade(0, "X");	
		lstOrig.anhade(1, "B");
		lstOrig.anhade(2, "C");
		lstOrig.anhade(3, "D");
		lstOrig.anhade(4, "E");
		lstOrig.anhade(5, "F");
		muestraLista(" lstOrig", lstOrig);
		
		// prueba con una lista con el elemento de corte al principio
		lstRet = lstOrig.cortaLista("X");
		muestraLista(" lstRet", lstRet);
		compruebaLista(lstRet, Arrays.asList("X", "B", "C", "D", "E", "F"));
		muestraLista(" lstOrig", lstOrig);
		compruebaLista(lstOrig, Arrays.asList());
	}

	@Test
	void corteEleCentralTest() {
		System.out.println("corteEleCentralTest");
		ListaDobleEnlace<String> lstOrig = new ListaDobleEnlace<>();
		ListaDobleEnlace<String> lstRet;

		lstOrig.anhade(0, "A");	
		lstOrig.anhade(1, "B");
		lstOrig.anhade(2, "C");
		lstOrig.anhade(3, "X");
		lstOrig.anhade(4, "E");
		lstOrig.anhade(5, "F");
		lstOrig.anhade(6, "G");
		muestraLista(" lstOrig", lstOrig);
		
		// prueba con una lista con el elemento de corte en el centro
		lstRet = lstOrig.cortaLista("X");
		muestraLista(" lstRet", lstRet);
		compruebaLista(lstRet, Arrays.asList("X", "E", "F", "G"));
		muestraLista(" lstOrig", lstOrig);
		compruebaLista(lstOrig, Arrays.asList("A", "B", "C"));
	}

	@Test
	void corteEleFinalTest() {
		System.out.println("corteEleFinalTest");
		ListaDobleEnlace<String> lstOrig = new ListaDobleEnlace<>();
		ListaDobleEnlace<String> lstRet;

		lstOrig.anhade(0, "A");	
		lstOrig.anhade(1, "B");
		lstOrig.anhade(2, "C");
		lstOrig.anhade(3, "D");
		lstOrig.anhade(4, "E");
		lstOrig.anhade(5, "F");
		lstOrig.anhade(6, "X");
		muestraLista(" lstOrig", lstOrig);
		
		// prueba con una lista con el elemento de corte al final
		lstRet = lstOrig.cortaLista("X");
		muestraLista(" lstRet", lstRet);
		compruebaLista(lstRet, Arrays.asList("X"));
		muestraLista(" lstOrig", lstOrig);
		compruebaLista(lstOrig, Arrays.asList("A", "B", "C", "D", "E", "F"));
	}
	
	@Test
	void punteroAnteriorTest() {
		System.out.println("punteroAnteriorTest");
		ListaDobleEnlace<String> lstOrig = new ListaDobleEnlace<>();
		ListaDobleEnlace<String> lstRet;

		lstOrig.anhade(0, "A");	
		lstOrig.anhade(1, "B");
		lstOrig.anhade(2, "X");
		lstOrig.anhade(3, "D");
		lstOrig.anhade(4, "E");
		lstOrig.anhade(5, "F");
		lstOrig.anhade(6, "G");
		muestraLista(" lstOrig", lstOrig);
		
		// corta la lista
		lstRet = lstOrig.cortaLista("X");
		muestraLista(" lstRet", lstRet);
		compruebaLista(lstRet, Arrays.asList("X", "D", "E", "F", "G"));
		muestraLista(" lstOrig", lstOrig);
		compruebaLista(lstOrig, Arrays.asList("A", "B"));
		
		// trata de anhadir en la posicion primera y ultima de ambas listas
		lstRet.anhade(0, "a");
		muestraLista(" lstRet", lstRet);
		compruebaLista(lstRet, Arrays.asList("a", "X", "D", "E", "F", "G"));
		lstRet.anhade(lstRet.tamanho(), "z");
		muestraLista(" lstRet", lstRet);
		compruebaLista(lstRet, Arrays.asList("a", "X", "D", "E", "F", "G", "z"));
		
		lstOrig.anhade(0, "a");
		muestraLista(" lstOrig", lstOrig);
		compruebaLista(lstOrig, Arrays.asList("a", "A", "B"));
		lstOrig.anhade(lstOrig.tamanho(), "z");
		muestraLista(" lstOrig", lstOrig);
		compruebaLista(lstOrig, Arrays.asList("a", "A", "B", "z"));
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
	 * @param titulo titulo antes de los contenidos de la lista.
	 * @param lst lista a mostrar
	 */
	private static void muestraLista(String titulo, ILista<?> lst) {
		String str = titulo + ":[";
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
