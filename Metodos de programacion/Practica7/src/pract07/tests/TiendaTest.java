package pract07.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fundamentos_test.test.infraestructura.FundamentosTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import pract07.gui.GUIGestionComisiones;

/**
 * Test de la clase Tienda con excepciones recuperables.
 * Usa "Fundamentos test".
 * 
 * @author  Metodos de Programacion (UC)
 * @version oct-23
 */
class TiendaTest {
	// datos globales para los tests
	private static String[] nombres = {"Pepa", "Lolo", "John"};
	private static final double FACTOR_COMISION = 0.05;
	
	// opciones del menu
	private static final int NUEVO_VENDEDOR = GUIGestionComisiones.NUEVO_VENDEDOR;
	private static final int NUEVA_VENTA = GUIGestionComisiones.NUEVA_VENTA;
	private static final int DATOS_VENDEDOR = GUIGestionComisiones.DATOS_VENDEDOR;
	private static final int RANKING_VENDEDOR = GUIGestionComisiones.RANKING_VENDEDOR;
	private static final int ELIMINA_VENDEDOR = GUIGestionComisiones.ELIMINA_VENDEDOR;
	private static final String[] OPTION_NAMES = {"NUEVO_VENDEDOR",
		"NUEVA_VENTA", "DATOS_VENDEDOR", "RANKING_VENDEDOR", "ELIMINA_VENDEDOR"};

	// Mensajes GUI
	private static final String[][] msjsGUI =
		{
				{"anhadido", "Ya existe", "maximo de intentos"}, // NUEVO_VENDEDOR
				{"Venta anhadida", "No existe", "mayor que 0"}, // NUEVA_VENTA
				{"Nombre", "No existe"}, // DATOS_VENDEDOR
				{"Nombre", "No existe"}, // RANKING_VENDEDOR
				{"Eliminado", "No existe"} // ELIMINA_VENDEDOR			
		};
	
	@Test
	void nuevoVendedorNombreRepetido1Tests() {
		System.out.println("nuevoVendedorNombreRepetido1Tests");

		FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, "Pepa");

		FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, "Lolo");

		// repite Pepa
		FundamentosTest.interaccionGUI(NUEVO_VENDEDOR, "Ya existe", "Pepa");
		FundamentosTest.continuaInteraccionGuiOK("Otro");
	}
	
	@Test
	void nuevoVendedorNombreRepetido2Tests() {
		System.out.println("nuevoVendedorNombreRepetido2Tests");

		FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, "Pepa");

		FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, "Lolo");

		// repite Pepa
		FundamentosTest.interaccionGUI(NUEVO_VENDEDOR, "Ya existe", "Pepa");
		FundamentosTest.continuaInteraccionGUI("Ya existe", "Lolo");
		FundamentosTest.continuaInteraccionGuiOK("Otro");
	}
	
	@Test
	void nuevoVendedorNombreRepetidoFinIntentosTests() {
		System.out.println("nuevoVendedorNombreRepetidoFinIntentosTests");

		FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, "Pepa");

		FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, "Lolo");

		// repite Pepa
		FundamentosTest.interaccionGUI(NUEVO_VENDEDOR, "Ya existe", "Pepa");
		FundamentosTest.continuaInteraccionGUI("Ya existe", "Lolo");
		FundamentosTest.continuaInteraccionGUI("Ya existe", "Lolo");
		FundamentosTest.continuaInteraccionGUI("maximo de intentos");
	}

	@Test
	void nuevaVentaErrorNombre1Test() {
		System.out.println("nuevaVentaErrorNombre1Test");

		// anhade vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, nombre);
		}

		// comprueba error al anhadir venta a un vendedor no existente
		FundamentosTest.interaccionGUI(NUEVA_VENTA, "No existe",
				"Nombre Mal", 100.0);

		// anhade la venta a un vendedor valido
		FundamentosTest.continuaInteraccionGuiOK(nombres[1], 10.0);

		// comprueba que puede anhadir ventas a los vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVA_VENTA, nombre, 50.0);
		}
	}
	
	@Test
	void nuevaVentaErrorNombre3Test() {
		System.out.println("nuevaVentaErrorNombre3Test");

		// anhade vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, nombre);
		}

		// comprueba error al anhadir venta a un vendedores no existentes
		FundamentosTest.interaccionGUI(NUEVA_VENTA, "No existe",
				"Nombre Mal", 100.0);
		FundamentosTest.continuaInteraccionGUI("No existe", "Otro Mal", 1.0);
		FundamentosTest.continuaInteraccionGUI("No existe", "Otro Mas Mal", 5.0);

		// anhade la venta a un vendedor valido
		FundamentosTest.continuaInteraccionGuiOK(nombres[1], 10.0);

		// comprueba que puede anhadir ventas a los vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVA_VENTA, nombre, 50.0);
		}
	}
	
	/*
	 Falla con la vesion actual de fundamentos_test
	@Test
	void nuevaVentaNoNumeroTest() {
		System.out.println("nuevaVentaNoNumeroTest");

		// anhade vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, nombre);
		}

		// comprueba error al anhadir venta con importe no numerico
		FundamentosTest.interaccionGuiOK(NUEVA_VENTA, nombres[0], "x",
				nombres[0], 10.0);

		// comprueba que puede anhadir ventas a los vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVA_VENTA, nombre, 50.0);
		}
	}
	*/
	
	@Test
	void nuevaVentaImporteNegativo1Test() {
		System.out.println("nuevaVentaImporteNegativo1Test");

		// anhade vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, nombre);
		}

		// comprueba error al anhadir venta con importe negativo
		FundamentosTest.interaccionGUI(NUEVA_VENTA, "mayor que 0",
				nombres[0], -10.0);
		FundamentosTest.continuaInteraccionGuiOK(nombres[0], 10.0);

		// comprueba que puede anhadir ventas a los vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVA_VENTA, nombre, 50.0);
		}
	}
	
	@Test
	void nuevaVentaImporteNegativo3Test() {
		System.out.println("nuevaVentaImporteNegativo3Test");

		// anhade vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, nombre);
		}

		// comprueba error al anhadir venta con importe negativo
		FundamentosTest.interaccionGUI(NUEVA_VENTA, "mayor que 0",
				nombres[0], -10.0);
		FundamentosTest.continuaInteraccionGUI("mayor que 0", nombres[0], -2.0);
		FundamentosTest.continuaInteraccionGUI("mayor que 0", nombres[1], -20.0);
		FundamentosTest.continuaInteraccionGuiOK(nombres[0], 10.0);

		// comprueba que puede anhadir ventas a los vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVA_VENTA, nombre, 50.0);
		}
	}

	@Test
	void datosVendedorErrorNombreTest() {
		System.out.println("datosVendedorErrorNombreTest");

		// anhade vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, nombre);
		}

		// comprueba error al obtener los datos de un vendedor no existente
		FundamentosTest.interaccionGUI(DATOS_VENDEDOR, "No existe", "nombre mal");


		// comprueba que se puede obtener los datos de los vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(DATOS_VENDEDOR, nombre);
		}
	}

	@Test
	void rankingVendedorErrorNombreTest() {
		System.out.println("rankingVendedorErrorNombreTest");

		// anhade vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, nombre);
		}

		// comprueba error al obtener los datos de un vendedor no existente
		FundamentosTest.interaccionGUI(RANKING_VENDEDOR, "No existe",
				"nombre mal");


		// comprueba que se puede obtener los datos de los vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(RANKING_VENDEDOR, nombre);
		}
	}

	@Test
	void eliminaVendedorErrorNombreTest() {
		System.out.println("eliminaVendedorErrorNombreTest");

		// anhade vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, nombre);
		}

		// comprueba error al obtener los datos de un vendedor no existente
		FundamentosTest.interaccionGUI(ELIMINA_VENDEDOR, "No existe", 
				"nombre mal");


		// comprueba que se pueden eliminar los vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(ELIMINA_VENDEDOR, nombre);
		}
	}

	@Test
	void comisionVentaSimpleTest() {
		System.out.println("comisionVentaSimpleTest");
		final double importeVenta = 100.0;
		double comision;

		// anhade vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, nombre);
		}

		// comprueba que el vendedor comienza con 0 de comision
		comision = FundamentosTest.leeDoubleGuiOK(DATOS_VENDEDOR, nombres[1]);	
		assertEquals(0.0, comision);

		// anhade una venta
		FundamentosTest.interaccionGuiOK(NUEVA_VENTA, nombres[1], importeVenta);

		// comprueba que la comision es correcta
		comision = FundamentosTest.leeDoubleGuiOK(DATOS_VENDEDOR, nombres[1]);	
		assertEquals(importeVenta * FACTOR_COMISION, comision, 0.0001);
	}

	@Test
	void comisionesVentaTest() {
		System.out.println("comisionesVentaTest");
		final double importeVenta = 100.0;
		double comision;

		// anhade vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, nombre);
		}

		// comprueba que los vendedores comienzan con 0 de comision
		for (String nombre: nombres) {
			comision = FundamentosTest.leeDoubleGuiOK(DATOS_VENDEDOR, nombre);	
			assertEquals(0.0, comision);
		}

		// anhade ventas
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVA_VENTA, nombre, importeVenta);
		}

		// comprueba que las comisiones son correctas
		for (String nombre: nombres) {
			comision = FundamentosTest.leeDoubleGuiOK(DATOS_VENDEDOR, nombre);	
			assertEquals(importeVenta * FACTOR_COMISION, comision, 0.0001);
		}
		
		// anhade otra venta a uno de los vendedores
		FundamentosTest.interaccionGuiOK(NUEVA_VENTA, nombres[1], importeVenta);

		// comprueba que las comisiones son correctas
		for (String nombre: nombres) {
			comision = FundamentosTest.leeDoubleGuiOK(DATOS_VENDEDOR, nombre);	
			if (nombre.equals(nombres[1])) {
				assertEquals(importeVenta * 2 * FACTOR_COMISION, comision, 0.0001);
			} else {
				assertEquals(importeVenta * FACTOR_COMISION, comision, 0.0001);		
			}
		}	
	}

	@Test
	void rankingSimpleTest() {
		System.out.println("rankingSimpleTest");
		final double importeVenta = 100.0;
		
		// anhade vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, nombre);
		}
		
		// anhade una venta para uno de los vendedores
		FundamentosTest.interaccionGuiOK(NUEVA_VENTA, nombres[2], importeVenta);

		// comprueba que es el primero del ranking
		int posRanking = FundamentosTest.leeIntGuiOK(RANKING_VENDEDOR, nombres[2]);	
		assertEquals(1, posRanking);
	}

	@Test
	void rankingTest() {
		System.out.println("rankingTest");
		int posRanking;
		
		// anhade vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, nombre);
		}
		
		// anhade ventas diferentes para cada vendedor
		FundamentosTest.interaccionGuiOK(NUEVA_VENTA, nombres[0], 10.0);
		FundamentosTest.interaccionGuiOK(NUEVA_VENTA, nombres[1], 30.0);
		FundamentosTest.interaccionGuiOK(NUEVA_VENTA, nombres[2], 20.0);

		// comprueba que las posiciones en el ranking son correctas
		posRanking = FundamentosTest.leeIntGuiOK(RANKING_VENDEDOR, nombres[0]);	
		assertEquals(3, posRanking);
		posRanking = FundamentosTest.leeIntGuiOK(RANKING_VENDEDOR, nombres[1]);	
		assertEquals(1, posRanking);
		posRanking = FundamentosTest.leeIntGuiOK(RANKING_VENDEDOR, nombres[2]);	
		assertEquals(2, posRanking);
	}

	@Test
	void eliminaVendedorTest() {
		System.out.println("eliminaVendedorTest");

		// anhade vendedores
		for (String nombre: nombres) {
			FundamentosTest.interaccionGuiOK(NUEVO_VENDEDOR, nombre);
		}
		
		// elimina uno de ellos
		FundamentosTest.interaccionGuiOK(ELIMINA_VENDEDOR, nombres[1]);
		
		// comprueba que se pueden obtener los datos de los no eliminados
		FundamentosTest.interaccionGuiOK(DATOS_VENDEDOR, nombres[0]);	
		FundamentosTest.interaccionGuiOK(DATOS_VENDEDOR, nombres[2]);	
		
		// comprueba que no se puede hacer nada con el eliminado
		FundamentosTest.interaccionGUI(DATOS_VENDEDOR, "No existe", nombres[1]);
		FundamentosTest.interaccionGUI(ELIMINA_VENDEDOR, "No existe", nombres[1]);
		FundamentosTest.interaccionGUI(RANKING_VENDEDOR, "No existe", nombres[1]);
		FundamentosTest.interaccionGUI(NUEVA_VENTA, "No existe",
				nombres[1], 100.0);
		FundamentosTest.continuaInteraccionGuiOK(nombres[0], 10.0);
	}
	
	// metodos para la infraestructura de test

	@BeforeAll
	public static void preparaModoTest() {
		FundamentosTest.lanzaModoTest(OPTION_NAMES, msjsGUI, false);
	}

	@AfterAll
	public static void finalizaModoTest() {
		FundamentosTest.finalizaModoTest();
	}

	/**
	 * Se ejecuta antes de cada test.
	 */
	@BeforeEach
	public void lanzaGUI() {
		FundamentosTest.lanzaGUI(GUIGestionComisiones.class);
	}

	/**
	 * Se ejecuta despues de cada test.
	 * @throws InterruptedException error en thread main.
	 */
	@AfterEach
	public void finalizaGUI() throws InterruptedException {
		FundamentosTest.finalizaGUI(GUIGestionComisiones.FIN_APLICACION);
	}

}
