package pract09;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Test de un mapa basdado en tabla de dispersion.
 * 
 * @author Estructuras de Datos (UC)
 * @version oct-2024
 */
public class MapaDispersionTest {
	private final int longTablaPorDefecto = 8;
	
	private void chkTamYMuestra(IMapa<Integer, String> mapa,
			int tamEsperado) {
		System.out.println(mapa);
		assertTrue(mapa.tamanho() == tamEsperado);	
	}

	@Test
	void testConstructor() {
		System.out.println("== testConstructor");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		assertTrue(mapa.busca(0) == null);
		assertTrue(mapa.busca(1) == null);
	}

	@Test
	void testTamanho1() {
		System.out.println("== testTamanho1");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);

		mapa.anhade(1, "A01");
		chkTamYMuestra(mapa, 1);
	}

	@Test
	void testTamanho2() {
		System.out.println("== testTamanho2");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(1, "A01");
		chkTamYMuestra(mapa, 1);
		
		mapa.elimina(1);
		chkTamYMuestra(mapa, 0);
	}

	@Test
	void testTamanho3() {
		System.out.println("== testTamanho3");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(1, "A01");
		chkTamYMuestra(mapa, 1);
		
		mapa.anhade(2, "A02");	
		mapa.anhade(5, "A05");	
		mapa.anhade(4, "A04");
		chkTamYMuestra(mapa, 4);
		
		mapa.anhade(2, "dos");
		chkTamYMuestra(mapa, 4);
		
		mapa.elimina(5);
		chkTamYMuestra(mapa, 3);
		
		mapa.anhade(11, "A011");
		chkTamYMuestra(mapa, 4);
		
		mapa.elimina(7);
		chkTamYMuestra(mapa, 4);
		
		mapa.elimina(1);		
		mapa.elimina(2);		
		mapa.elimina(4);		
		mapa.elimina(11);
		chkTamYMuestra(mapa, 0);	
	}

	@Test
	void testAnhadeBusca1() {
		System.out.println("== testAnhadeBusca1");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(3, "A03");
		chkTamYMuestra(mapa, 1);
		assertTrue(mapa.busca(Integer.valueOf(3)).equals("A03"));
	}

	@Test
	void testAnhadeBusca2() {
		System.out.println("== testAnhadeBusca2");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(1, "A01");
		chkTamYMuestra(mapa, 1);
		assertTrue(mapa.busca(1).equals("A01"));

		assertTrue(mapa.busca(2) == null);
				
		mapa.anhade(3, "A03");				
		mapa.anhade(5, "A05");
		chkTamYMuestra(mapa, 3);
		assertTrue(mapa.busca(5).equals("A05"));	
		
		mapa.anhade(15, "A015");
		chkTamYMuestra(mapa, 4);
		assertTrue(mapa.busca(5).equals("A05"));
		assertTrue(mapa.busca(15).equals("A015"));
		
		assertTrue(mapa.busca(45) == null);		
	}

	@Test
	void testAnhadeEliminaBusca1() {
		System.out.println("== testAnhadeEliminaBusca1");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(5, "A05");
		chkTamYMuestra(mapa, 1);
		assertTrue(mapa.busca(5).equals("A05"));
		
		// elimina primero
		mapa.elimina(5);
		assertTrue(mapa.busca(5) == null);
		chkTamYMuestra(mapa, 0);
	}

	@Test
	void testAnhadeEliminaBusca2() {
		System.out.println("== testAnhadeEliminaBusca2");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(1, "A01");
		chkTamYMuestra(mapa, 1);
		assertTrue(mapa.busca(1).equals("A01"));
		
		// elimina primero
		mapa.elimina(1);
		assertTrue(mapa.busca(1) == null);
		chkTamYMuestra(mapa, 0);
		
		// elimina uno que no existe
		mapa.elimina(1);
		assertTrue(mapa.busca(1) == null);
		chkTamYMuestra(mapa, 0);
				
		mapa.anhade(2, "A02");		
		mapa.anhade(13, "A013");
		chkTamYMuestra(mapa, 2);
		
		// elimina con más de una entrada
		mapa.elimina(13);
		assertTrue(mapa.busca(13) == null);
		chkTamYMuestra(mapa, 1);
		
		// busca eliminado
		assertTrue(mapa.busca(13) == null);
	
		mapa.anhade(14, "A014");	
		mapa.anhade(23, "A023");	
		mapa.anhade(44, "A044");	
		mapa.anhade(54, "A054");
		
		// busca en lista con más de una entrada
		assertTrue(mapa.busca(14).equals("A014"));
		assertTrue(mapa.busca(44).equals("A044"));
		assertTrue(mapa.busca(54).equals("A054"));
		chkTamYMuestra(mapa, 5);
		
		// elimina en lista con más de una entrada
		mapa.elimina(14);
		mapa.elimina(54);
		assertTrue(mapa.busca(14) == null);
		assertTrue(mapa.busca(54) == null);
		chkTamYMuestra(mapa, 3);
		
		// busca después de eliminar
		assertTrue(mapa.busca(14) == null);
		assertTrue(mapa.busca(44).equals("A044"));
		assertTrue(mapa.busca(54) == null);
		chkTamYMuestra(mapa, 3);
	}
	
	// prueba llaves negativas para simular funciones
	// hash que retornan un valor negativo que hay que
	// volver a poner en positivo
	@Test
	void testAnhadeLlaveNeg() {
		System.out.println("== testAnhadeLlaveNeg");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		chkTamYMuestra(mapa, 0);
		
		mapa.anhade(-4, "A0-4");	
		mapa.anhade(0, "A00");	
		mapa.anhade(-2, "A0-2");	
		mapa.anhade(-14, "A0-14");	
		mapa.anhade(-longTablaPorDefecto, "A0" + longTablaPorDefecto);
		chkTamYMuestra(mapa, 5);
		assertTrue(mapa.busca(-4).equals("A0-4"));	
		assertTrue(mapa.busca(-longTablaPorDefecto).equals("A0" + longTablaPorDefecto));
	}
	
	@Test
	void testHaceVacio() {
		System.out.println("== testHaceVacio");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
		
		mapa.anhade(4, "cuatro");
		mapa.haceVacio();

		assertTrue(mapa.tamanho() == 0);
		
		// Comprueba que se ha vaciado correctamente
		assertTrue(mapa.busca(4) == null);
	}
	
	@Test
	void testLlavesVacia() {
		System.out.println("== testLlavesVacia");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(5);
		
		List<Integer> listaLlaves = mapa.llaves();
		System.out.println("listaLlaves:" + listaLlaves);
		
		// comprueba que un mapa vacio no tiene ninguna llave
		assertEquals(Arrays.asList(), listaLlaves);
	}
	
	@Test
	void testLlavesUnaPorLista() {
		System.out.println("== testLlavesUnaPorLista");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(5);
				
		mapa.anhade(4, "cuatro");				
		mapa.anhade(10, "cero");				
		mapa.anhade(6, "uno");		
		mapa.anhade(2, "dos");
		mapa.anhade(13, "tres");
		chkTamYMuestra(mapa, 5);
		
		List<Integer> listaLlaves = mapa.llaves();
		System.out.println("listaLlaves:" + listaLlaves);
		
		// comprueba que un mapa vacio no tiene ninguna llave
		assertEquals(Arrays.asList(10, 6, 2, 13, 4), listaLlaves);
	}
	
	@Test
	void testLlaves() {
		System.out.println("== testLlaves");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
				
		mapa.anhade(4, "cuatro0");				
		mapa.anhade(8, "cero");				
		mapa.anhade(7, "siete");		
		mapa.anhade(12, "cuatro1");
		mapa.anhade(20, "cuatro2");
		chkTamYMuestra(mapa, 5);
		
		List<Integer> listaLlaves = mapa.llaves();
		System.out.println("listaLlaves:" + listaLlaves);

		
		// comprueba que el tamanho de la lista de llaves es el esperado
		assertEquals(5, listaLlaves.size());
		
		// comprueba que las llaves estan
		assertTrue(listaLlaves.contains(4));
		assertTrue(listaLlaves.contains(8));
		assertTrue(listaLlaves.contains(7));
		assertTrue(listaLlaves.contains(12));
		assertTrue(listaLlaves.contains(20));
	}
	
	@Test
	void testValoresVacia() {
		System.out.println("== testValoresVacia");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(5);
		
		List<String> listaValores = mapa.valores();
		System.out.println("listaValores:" + listaValores);
		
		// comprueba que un mapa vacio no tiene ninguna llave
		assertEquals(Arrays.asList(), listaValores);
	}
	
	@Test
	void testValoresUnaPorLista() {
		System.out.println("== testValoresUnaPorLista");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(5);
				
		mapa.anhade(4, "cuatro");				
		mapa.anhade(10, "cero");				
		mapa.anhade(6, "uno");		
		mapa.anhade(2, "dos");
		mapa.anhade(13, "tres");
		chkTamYMuestra(mapa, 5);
		
		List<String> listaValores = mapa.valores();
		System.out.println("listaValores:" + listaValores);
		
		// comprueba que un mapa vacio no tiene ninguna llave
		assertEquals(Arrays.asList("cero", "uno", "dos", "tres", "cuatro"), listaValores);
	}
	
	@Test
	void testValores() {
		System.out.println("== testValores");
		IMapa<Integer, String> mapa = new MapaDispersionAbierta<Integer, String>(8);
				
		mapa.anhade(4, "cuatro0");				
		mapa.anhade(8, "cero");				
		mapa.anhade(7, "siete");		
		mapa.anhade(12, "cuatro1");
		mapa.anhade(20, "cuatro2");
		chkTamYMuestra(mapa, 5);
		
		List<String> listaValores = mapa.valores();
		System.out.println("listaValores:" + listaValores);

		
		// comprueba que el tamanho de la lista de llaves es el esperado
		assertEquals(5, listaValores.size());
		
		// comprueba que las llaves estan
		assertTrue(listaValores.contains("cuatro0"));
		assertTrue(listaValores.contains("cero"));
		assertTrue(listaValores.contains("siete"));
		assertTrue(listaValores.contains("cuatro1"));
		assertTrue(listaValores.contains("cuatro2"));
	}
	
}
