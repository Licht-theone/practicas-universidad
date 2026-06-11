package pract08.c2_algoritmos_recursivos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import pract08.c1_arbol_binario_ph.ArbolBinarioPH;

/**
 * Test de algoritmos recursivos en arboles binarios.
 *
 * @author Estructuras de Datos (UC)
 * @version oct-2022
 */
public class AlgortimosRecursivosTest {

	@Test
	void testCuentaOcurrenciasElementoArbolVacio() {
		System.out.println("testCuentaOcurrenciasElementoArbolVacio");
		// crea un arbol binario
		ArbolBinarioPH<Integer> arbol = new ArbolBinarioPH<Integer>();
		
		// arbol vacio
		assertEquals(0, AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 1));
	}

	@Test
	void testCuentaOcurrenciasElementoSoloRaiz() {
		System.out.println("testCuentaOcurrenciasElementoSoloRaiz");
		// crea un arbol binario
		ArbolBinarioPH<Integer> arbol = new ArbolBinarioPH<Integer>();
		
		// arbol con solo la raiz
		arbol.anhadeRaiz(1);
		assertEquals(1, AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 1));
		assertEquals(0, AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 2));
	}

	@Test
	void testCuentaOcurrenciasElemento() {
		System.out.println("testCuentaOcurrenciasElemento");
		// crea un arbol binario
		ArbolBinarioPH<Integer> arbol = new ArbolBinarioPH<Integer>();
		
		// arbol vacio
		assertEquals(0, AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 1));
		
		// arbol con solo la raiz
		arbol.anhadeRaiz(1);
		assertEquals(1, AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 1));
		assertEquals(0, AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 2));
		
		// anhade mas nodos al arbol
		ArbolBinarioPH<Integer>.Nodo nodo = arbol.raiz();

		nodo.anhadeHijoIzq(2);
		nodo.anhadeHijoDer(3);

		nodo = nodo.hijoIzq(); // <- 2
		nodo.anhadeHijoIzq(4);  
		nodo.anhadeHijoDer(1);
		
		nodo = nodo.hijoDer(); // <- 1
		nodo.anhadeHijoIzq(1);  

		nodo = nodo.padre().padre().hijoDer(); // <- 3
		nodo.anhadeHijoDer(2);
		// Estado final del arbol
		//               1
		//             /   \
		//          2        3
		//        /   \        \
		//      4      1        2
		//            /
		//           1  
		
		// comprueba que las cocurrencias medidas son las correctas
		assertEquals(3, AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 1));
		assertEquals(2, AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 2));
		assertEquals(1, AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 3));
		assertEquals(1, AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 4));
		assertEquals(0, AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 5));		
	}
	
	@Test
	void testBuscaElemConDosHijosIgualesNoEncuentra() {
		System.out.println("testBuscaElemConDosHijosIgualesNoEncuentra");
		// crea un arbol binario
		ArbolBinarioPH<Integer> arbol = new ArbolBinarioPH<Integer>();
		
		// arbol vacio
		assertNull(AlgoritmosRecursivos.buscaElemConDosHijosIgualesInorden(arbol));
		
		// arbol con solo la raiz
		arbol.anhadeRaiz(1);
		assertNull(AlgoritmosRecursivos.buscaElemConDosHijosIgualesInorden(arbol));
		
		// anhade dos hijos distintos a la raiz
		ArbolBinarioPH<Integer>.Nodo nodo = arbol.raiz();

		nodo.anhadeHijoIzq(2);
		assertNull(AlgoritmosRecursivos.buscaElemConDosHijosIgualesInorden(arbol));
				
		nodo.anhadeHijoDer(3);
		assertNull(AlgoritmosRecursivos.buscaElemConDosHijosIgualesInorden(arbol));
	}
	
	@Test
	void testBuscaElemConDosHijosIgualesSubarbolIzq() {
		System.out.println("testBuscaElemConDosHijosIgualesSubarbolIzq");
		// crea un arbol binario
		ArbolBinarioPH<Integer> arbol = new ArbolBinarioPH<Integer>();
		
		// anhade nodos al arbol
		arbol.anhadeRaiz(1);
		ArbolBinarioPH<Integer>.Nodo nodo = arbol.raiz();

		nodo.anhadeHijoIzq(2);
		nodo.anhadeHijoDer(2);

		nodo = nodo.hijoIzq(); // <- 2
		nodo.anhadeHijoIzq(5);  
		nodo.anhadeHijoDer(5);
		
		nodo = nodo.hijoDer(); // <- 5
		nodo.anhadeHijoIzq(1);  

		nodo = nodo.padre().padre().hijoDer(); // <- 6
		nodo.anhadeHijoIzq(3);
		nodo.anhadeHijoDer(3);
		// Estado final del arbol
		//               1
		//             /   \
		//          2         2
		//        /   \      /  \
		//       5     5    3    3
		//            /
		//           1  
		
		// comprueba se retorna el elemento con dos hijos iguales
		assertEquals(2, AlgoritmosRecursivos.buscaElemConDosHijosIgualesInorden(arbol));
	}
	
	@Test
	void testBuscaElemConDosHijosIgualesRaiz() {
		System.out.println("testBuscaElemConDosHijosIgualesRaiz");
		// crea un arbol binario
		ArbolBinarioPH<Integer> arbol = new ArbolBinarioPH<Integer>();
		
		// anhade nodos al arbol
		arbol.anhadeRaiz(1);
		ArbolBinarioPH<Integer>.Nodo nodo = arbol.raiz();

		nodo.anhadeHijoIzq(2);
		nodo.anhadeHijoDer(2);

		nodo = nodo.hijoIzq(); // <- 2
		nodo.anhadeHijoIzq(5);  
		nodo.anhadeHijoDer(1);
		
		nodo = nodo.hijoDer(); // <- 1
		nodo.anhadeHijoIzq(5);  

		nodo = nodo.padre().padre().hijoDer(); // <- 2
		nodo.anhadeHijoIzq(3);
		nodo.anhadeHijoDer(3);
		// Estado final del arbol
		//               1
		//             /   \
		//          2         2
		//        /   \      /  \
		//       5     1    3    3
		//            /
		//           5  
		
		// comprueba se retorna el elemento con dos hijos iguales
		assertEquals(1, AlgoritmosRecursivos.buscaElemConDosHijosIgualesInorden(arbol));
	}
	
	@Test
	void testBuscaElemConDosHijosIgualesSubarbolDer() {
		System.out.println("testBuscaElemConDosHijosIgualesSubarbolDer");
		// crea un arbol binario
		ArbolBinarioPH<Integer> arbol = new ArbolBinarioPH<Integer>();
		
		// anhade nodos al arbol
		arbol.anhadeRaiz(1);
		ArbolBinarioPH<Integer>.Nodo nodo = arbol.raiz();

		nodo.anhadeHijoIzq(2);
		nodo.anhadeHijoDer(6);

		nodo = nodo.hijoIzq(); // <- 2
		nodo.anhadeHijoIzq(5);  
		nodo.anhadeHijoDer(4);
		
		nodo = nodo.hijoDer(); // <- 4
		nodo.anhadeHijoIzq(1);  

		nodo = nodo.padre().padre().hijoDer(); // <- 6
		nodo.anhadeHijoIzq(3);
		nodo.anhadeHijoDer(3);
		// Estado final del arbol
		//               1
		//             /   \
		//          2         6
		//        /   \      /  \
		//       5     4    3    3
		//            /
		//           1  
		
		// comprueba se retorna el elemento con dos hijos iguales
		assertEquals(6, AlgoritmosRecursivos.buscaElemConDosHijosIgualesInorden(arbol));
	}

}
