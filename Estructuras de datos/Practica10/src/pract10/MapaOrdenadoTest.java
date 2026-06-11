package pract10;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import pract10.IMapaOrdenado.IEntrada;

import java.util.Arrays;
import java.util.List;

/**
 * Test de un mapa ordenado basado en Arbol Binario de Busqueda (ABB).
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2024
 */
public class MapaOrdenadoTest {

	private <K extends Comparable<K>, V> void chkTamYMuestra(IMapaOrdenado<K, V> mapa,
			int tamEsperado, String msj) {
		System.out.println(msj + ":" + mapa);
		assertTrue("Error Tamanho:" + mapa.tamanho(), mapa.tamanho() == tamEsperado);	
	}

	@Test
	void testConstructor() {
		System.out.println("==testConstructor");
		IMapaOrdenado<Integer, String> mapa = new MapaABB<Integer, String>();
		chkTamYMuestra(mapa, 0, "Vacio");
		assertTrue(mapa.busca(0) == null);
		assertTrue(mapa.busca(1) == null);
	}

	@Test
	void testTamanho1() {
		System.out.println("==testTamanho1");
		IMapaOrdenado<Integer, String> mapa = new MapaABB<Integer, String>();
		chkTamYMuestra(mapa, 0, "Vacio");

		mapa.anhade(1, "A01");
		chkTamYMuestra(mapa, 1, "Una entrada");
	}

	@Test
	void testTamanho2() {
		System.out.println("==testTamanho2");
		IMapaOrdenado<Integer, String> mapa = new MapaABB<Integer, String>();
		chkTamYMuestra(mapa, 0, "Vacio");

		mapa.anhade(1, "A01");
		chkTamYMuestra(mapa, 1, "Una entrada");

		mapa.elimina(1);
		chkTamYMuestra(mapa, 0, "Despues de eliminar");
	}

	@Test
	void testAnhadeTamanho() {
		System.out.println("==testAnhadeTamanho");
		IMapaOrdenado<Integer, String> mapa = new MapaABB<Integer, String>();
		chkTamYMuestra(mapa, 0, "Vacio");

		mapa.anhade(1, "01");
		chkTamYMuestra(mapa, 1, "Una entrada");

		mapa.anhade(2, "02");	
		mapa.anhade(5, "05");	
		mapa.anhade(4, "04");
		chkTamYMuestra(mapa, 4, "Cuatro entradas");

		// añade repetido
		mapa.anhade(2, "dos");
		chkTamYMuestra(mapa, 4, "Despues de anhade repetido");

		mapa.elimina(5);
		chkTamYMuestra(mapa, 3, "Despues elimina(5)");

		mapa.anhade(11, "011");
		chkTamYMuestra(mapa, 4, "Depues anhade(11, ...)");

		mapa.elimina(7);
		chkTamYMuestra(mapa, 4, "Despues elimina(5)");

		mapa.elimina(1);		
		mapa.elimina(2);		
		mapa.elimina(4);		
		mapa.elimina(11);
		chkTamYMuestra(mapa, 0, "Despues elimina todos");	
	}

	@Test
	void testAnhadeBusca1() {
		System.out.println("==testAnhadeBusca1");
		IMapaOrdenado<String, String> mapa = new MapaABB<String, String>();

		mapa.anhade("3", "A03");
		chkTamYMuestra(mapa, 1, "Una entrada");
		assertTrue(mapa.busca(new String("3")).equals("A03"));
	}

	@Test
	void testAnhadeBusca2() {
		System.out.println("==testAnhadeBusca2");
		IMapaOrdenado<Integer, String> mapa = new MapaABB<Integer, String>();

		mapa.anhade(21, "021");
		chkTamYMuestra(mapa, 1, "Una entrada");
		assertTrue(mapa.busca(21).equals("021"));

		assertTrue(mapa.busca(2) == null);

		mapa.anhade(3, "03");				
		mapa.anhade(5, "05");
		chkTamYMuestra(mapa, 3, "Tres entradas");
		assertTrue(mapa.busca(5).equals("05"));	

		mapa.anhade(15, "015");
		chkTamYMuestra(mapa, 4, "Cuatro entradas");
		assertTrue(mapa.busca(5).equals("05"));
		assertTrue(mapa.busca(15).equals("015"));

		assertTrue(mapa.busca(45) == null);

		// árbol grande			
		mapa.anhade(3, "tres");				
		mapa.anhade(25, "025");				
		mapa.anhade(17, "017");				
		mapa.anhade(2, "02");				
		mapa.anhade(22, "022");				
		mapa.anhade(13, "013");				
		mapa.anhade(45, "045");				
		mapa.anhade(37, "037");
		chkTamYMuestra(mapa, 11, "Grade");

		// busca menor
		assertTrue(mapa.busca(2).equals("02"));

		// busca mayor
		assertTrue(mapa.busca(45).equals("045"));

		// busca hoja central
		assertTrue(mapa.busca(17).equals("017"));

		// busca nudo central
		assertTrue(mapa.busca(37).equals("037"));	
	}

	@Test
	void testAnhadeEliminaBusca1() {
		System.out.println("==testAnhadeEliminaBusca1");
		IMapaOrdenado<Integer, String> mapa = new MapaABB<Integer, String>();

		mapa.anhade(5, "A05");
		chkTamYMuestra(mapa, 1, "Una entrada");
		assertTrue(mapa.busca(5).equals("A05"));

		// elimina primero
		mapa.elimina(5);
		chkTamYMuestra(mapa, 0, "Despues eliminar");
	}

	@Test
	void testAnhadeEliminaBusca2() {
		System.out.println("==testAnhadeEliminaBusca2");
		IMapaOrdenado<Integer, String> mapa = new MapaABB<Integer, String>();

		mapa.anhade(1, "01");
		chkTamYMuestra(mapa, 1, "Una entrada");
		assertTrue(mapa.busca(1).equals("01"));

		// elimina primero
		mapa.elimina(1);
		chkTamYMuestra(mapa, 0, "Despues elimina(1)");

		// elimina en vacío
		mapa.elimina(1);
		chkTamYMuestra(mapa, 0, "Despues elimina(1)");

		mapa.anhade(2, "02");		
		mapa.anhade(13, "013");
		chkTamYMuestra(mapa, 2, "Despues anhadir 2");

		// elimina con más de una entrada
		mapa.elimina(13);
		chkTamYMuestra(mapa, 1, "Despues elimina(13)");

		// busca eliminado
		assertTrue(mapa.busca(13) == null);

		mapa.haceVacio();
		mapa.anhade(34, "034");	
		mapa.anhade(23, "023");	
		mapa.anhade(44, "044");	
		mapa.anhade(54, "054");
		chkTamYMuestra(mapa, 4, "Nuevo mapa");

		// busca en árbol con varias entradas
		assertTrue(mapa.busca(34).equals("034"));
		assertTrue(mapa.busca(44).equals("044"));
		assertTrue(mapa.busca(23).equals("023"));
		assertTrue(mapa.busca(54).equals("054"));
		chkTamYMuestra(mapa, 4, "Despues de buscar");

		// elimina raíz con árbol dos hijos
		mapa.elimina(34);
		assertTrue(mapa.busca(34) == null);
		assertTrue(mapa.busca(44).equals("044"));
		chkTamYMuestra(mapa, 3, "Despues elimina(34)");

		// árbol con sólo la raíz
		mapa.haceVacio();
		mapa.anhade(23, "023");
		chkTamYMuestra(mapa, 1, "Despues hace vacio y anhade(23, ...)");

		// busca en árbol con sólo la raíz
		assertTrue(mapa.busca(23).equals("023"));
		assertTrue(mapa.busca(24) == null);

		// elimina en árbol con sólo la raíz
		mapa.elimina(23);
		assertTrue(mapa.busca(34) == null);
		chkTamYMuestra(mapa, 0, "Despues elimina raiz");

		// árbol con raíz y hijo izq
		mapa.anhade(11, "011");
		mapa.anhade(1, "01");
		chkTamYMuestra(mapa, 2, "Dos entradas");

		// elimina ráiz en árbol con raíz e hijo izq
		mapa.elimina(11);
		assertTrue(mapa.busca(1).equals("01"));
		chkTamYMuestra(mapa, 1, "Despues elimina raiz");

		// árbol con raíz y hijo der
		mapa.anhade(22, "022");
		chkTamYMuestra(mapa, 2, "Dos entradas");

		// elimina ráiz en árbol con raíz e hijo der
		mapa.elimina(1);
		assertTrue(mapa.busca(22).equals("022"));
		chkTamYMuestra(mapa, 1, "Despues elimina raiz");

		// árbol grande				
		mapa.anhade(21, "021");					
		mapa.anhade(3, "03");					
		mapa.anhade(7, "07");				
		mapa.anhade(25, "025");				
		mapa.anhade(17, "017");				
		mapa.anhade(2, "02");				
		mapa.anhade(22, "022");				
		mapa.anhade(13, "013");				
		mapa.anhade(45, "045");				
		mapa.anhade(37, "037");				
		mapa.anhade(1, "01");		
		mapa.anhade(3, "tres");
		chkTamYMuestra(mapa, 11, "Grande");	

		// elimina menor
		mapa.elimina(1);
		assertTrue(mapa.busca(1) == null);

		// elimina mayor
		mapa.elimina(45);
		assertTrue(mapa.busca(45) == null);

		// elimina nodo interno
		mapa.elimina(3);
		assertTrue(mapa.busca(3) == null);

		// elimina hoja central
		mapa.elimina(13);
		assertTrue(mapa.busca(13) == null);

		chkTamYMuestra(mapa, 7, "Final");
	}

	@Test
	void testHaceVacio() {
		System.out.println("==testHaceVacio");
		IMapaOrdenado<Integer, String> mapa = new MapaABB<Integer, String>();

		mapa.anhade(4, "cuatro");
		chkTamYMuestra(mapa, 1, "Antes haceVacio:");
		mapa.haceVacio();
		chkTamYMuestra(mapa, 0, "Despues haceVacio:");

		assertTrue(mapa.tamanho() == 0);

		// Comprueba que se ha vaciado correctamente
		assertTrue(mapa.busca(4) == null);
	}

	@Test
	void testBuscaMenorMayor1() {
		System.out.println("==testBuscaMenorMayor1");
		IMapaOrdenado<Integer, String> mapa = new MapaABB<Integer, String>();

		// mapa vacio
		IEntrada<Integer, String> primera = mapa.primeraEntrada();
		assertTrue(primera == null);
		IEntrada<Integer, String> ultima = mapa.ultimaEntrada();
		assertTrue(ultima == null);

		// mapa con un elemento
		mapa.anhade(5, "cinco");
		primera = mapa.primeraEntrada();
		System.out.println("Primera:" + primera);
		assertTrue(primera.llave().equals(5) && primera.valor().equals("cinco"));
		ultima = mapa.ultimaEntrada();
		System.out.println("Ultima:" + ultima);
		assertTrue(ultima.llave().equals(5) && ultima.valor().equals("cinco"));
	}

	@Test
	void testBuscaMenorMayor() {
		System.out.println("==testBuscaMenorMayor");	
		IMapaOrdenado<Integer, String> mapa = new MapaABB<Integer, String>();		
		mapa.anhade(21, "021");					
		mapa.anhade(3, "03");					
		mapa.anhade(7, "07");				
		mapa.anhade(25, "025");				
		mapa.anhade(17, "017");				
		mapa.anhade(2, "02");				
		mapa.anhade(22, "022");				
		mapa.anhade(13, "013");				
		mapa.anhade(45, "045");				
		mapa.anhade(37, "037");				
		mapa.anhade(1, "01");		
		mapa.anhade(3, "tres");
		chkTamYMuestra(mapa, 11, "Muchas entradas");	

		IEntrada<Integer, String> primera = mapa.primeraEntrada();
		System.out.println("Primera:" + primera);
		assertTrue(primera.llave().equals(1) && primera.valor().equals("01"));
		IEntrada<Integer, String> ultima = mapa.ultimaEntrada();
		System.out.println("Ultima:" + ultima);
		assertTrue(ultima.llave().equals(45) && ultima.valor().equals("045"));
	}

	/**
	 * Clase parecida a IEntrada, usada para facilitar los tests.
	 */
	private static class Entrada {
		private Integer llave;
		private String valor;

		public Entrada(Integer llave, String valor) {
			this.llave = llave;
			this.valor = valor;
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			IMapaOrdenado.IEntrada<Integer, String> entrada;
			entrada = (IMapaOrdenado.IEntrada<Integer, String>) obj;
			return this.llave.equals(entrada.llave()) && this.valor.equals(entrada.valor());
		}

		@Override
		public String toString() {
			return "(" + llave + "," + valor + ")";
		}
	}

	@Test
	void testEntradas() {
		System.out.println("==testEntradas");	
		IMapaOrdenado<Integer, String> mapa = new MapaABB<Integer, String>();
		List<Entrada> in = Arrays.asList(
				new Entrada(1, "uno"),
				new Entrada(2, "dos"),
				new Entrada(3, "tres"),
				new Entrada(4, "cuatro"),
				new Entrada(5, "cinco"),
				new Entrada(6, "seis"),
				new Entrada(7, "siete"),
				new Entrada(8, "ocho"));
		for (int i = 0; i < in.size(); i += 2) {
			mapa.anhade(in.get(i).llave, in.get(i).valor);
		}
		for (int i = 1; i < in.size(); i += 2) {
			mapa.anhade(in.get(i).llave, in.get(i).valor);
		}

		List<IEntrada<Integer, String>> out = mapa.entradas();
		System.out.println("Entradas:" + out);
		assertTrue(in.size() == out.size());
		for (int i = 0; i < out.size(); i++) {
			assertEquals(in.get(i), out.get(i));
		}
	}

	@Test
	void testPredecesores() {
		System.out.println("==testPredecesores");	
		IMapaOrdenado<Integer, String> mapa = new MapaABB<Integer, String>();
		List<Entrada> in = Arrays.asList(
				new Entrada(1, "uno"),
				new Entrada(2, "dos"),
				new Entrada(3, "tres"),
				new Entrada(4, "cuatro"),
				new Entrada(5, "cinco"),
				new Entrada(6, "seis"),
				new Entrada(7, "siete"),
				new Entrada(8, "ocho"));
		for (int i = 0; i < in.size(); i += 2) {
			mapa.anhade(in.get(i).llave, in.get(i).valor);
		}
		for (int i = 1; i < in.size(); i += 2) {
			mapa.anhade(in.get(i).llave, in.get(i).valor);
		}

		for (int i = 0; i < in.size(); i++) {
			List<IEntrada<Integer, String>> out = mapa.predecesores(i + 1);
			assertTrue("predecesores.size=" + out.size() + "(esperado:" + i + ")",
					out.size() == i);
			System.out.println("Predecesores de " + i + ":" + out);
			for (int j = 0; j < i; j++) {
				assertEquals(in.get(j), out.get(j));
			}
		}
	}

	@Test
	void testValores() {
		System.out.println("==testValores");	
		IMapaOrdenado<Integer, String> mapa = new MapaABB<Integer, String>();
		List<Entrada> in = Arrays.asList(
				new Entrada(1, "uno"),
				new Entrada(2, "dos"),
				new Entrada(3, "tres"),
				new Entrada(4, "cuatro"),
				new Entrada(5, "cinco"),
				new Entrada(6, "seis"),
				new Entrada(7, "siete"),
				new Entrada(8, "ocho"));
		for (int i = 0; i < in.size(); i += 2) {
			mapa.anhade(in.get(i).llave, in.get(i).valor);
		}
		for (int i = 1; i < in.size(); i += 2) {
			mapa.anhade(in.get(i).llave, in.get(i).valor);
		}

		List<String> out = mapa.valores();
		System.out.println("Valores:" + out);
		assertTrue(in.size() == out.size());
		for (int i = 0; i < out.size(); i++) {
			assertTrue(in.get(i).valor.equals(out.get(i)));
		}
	}

}
