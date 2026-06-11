package pract10.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fundamentos_test.test.infraestructura.FundamentosTest;
import pract10.gui.GUIGestionTarjetas;
import pract10.modelo.tarjetas.*;


/**
 * Test de la clase supermercado y de la jerarquía de clases de tarjetas.
 * 
 * @author Metodos de Programacion (UC)
 * @version nov-2023
 */
class SupermercadoTest {
	// datos globales para los tests
	private static String[] dnis =
		{"11111111A", "22222222A", "33333333A", "12345678A"};
	private static String[] dirs = {"dir0", "dir1", "dir2"};
	private static final double EUROS_POR_PUNTO_FAMILIA = 50;

	// opciones del menu
	private static final int REGISTRA_TARJETA = GUIGestionTarjetas.REGISTRA_TARJETA;
	private static final int RECARGA_SALDO = GUIGestionTarjetas.RECARGA_SALDO;
	private static final int REALIZA_COMPRA = GUIGestionTarjetas.REALIZA_COMPRA;
	private static final int CANJEA_PUNTOS = GUIGestionTarjetas.CANJEA_PUNTOS;
	private static final int SALDO_TARJETA = GUIGestionTarjetas.SALDO_TARJETA;
	private static final String[] OPTION_NAMES = {"REGISTRA_TARJETA", 
		"RECARGA_SALDO", "REALIZA_COMPRA", "CANJEA_PUNTOS", "SALDO_TARJETA"};

	// Mensajes GUI
	private static final String[][] msjsGUI =
		{
				{"exito", "incorrecto", "Ya existe"}, // REGISTRA_TARJETA
				{"exito", "No existe"}, // RECARGA_SALDO
				{"Saldo restante", "No existe", "no tiene"}, // REALIZA_COMPRA
				{"restantes", "No existe", "no es", "no tiene"}, // CANJEA_PUNTOS
				{"Saldo", "No existe"} // SALDO_TARJETA			
		};

	@Test
	void registraTarjetaErrorDNITest() {
		System.out.println("registraTarjetaErrorDNITest");
		// registra una tarjeta
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[0], dirs[0], "B");

		// comprueba que no es posible registrar dos veces la misma tarjeta		
		FundamentosTest.interaccionGUI(REGISTRA_TARJETA, "Ya existe",
				dnis[0], dirs[0], "B");
		

		// comprueba que no es posible registrar otra tarjeta con el mismo DNI
		FundamentosTest.interaccionGUI(REGISTRA_TARJETA, "Ya existe",
				dnis[0], dirs[1], "VF");
		

		// comprueba que se puede registrar una tarjeta con distinto DNI
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[1], dirs[0], "VF");

		// comprueba que no es posible registrar otra tarjeta con el mismo DNI
		FundamentosTest.interaccionGUI(REGISTRA_TARJETA, "Ya existe",
				dnis[1], dirs[0], "VN");
	}

	@Test
	void recargaSaldoErrorDNITest() {
		System.out.println("recargaSaldoErrorDNITest");
		// comprueba que no es posible recargar saldo de una tarjeta que no existe
		FundamentosTest.interaccionGUI(RECARGA_SALDO, "No existe", dnis[0], 10.0);

		// registra tarjetas
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[0], dirs[0], "B");
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[1], dirs[0], "VF");
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[2], dirs[0], "VN");

		// comprueba que es posible recargar saldo de las tarjetas que existen
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[0], 10.0);
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[1], 20.0);
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[2], 3.0);

		// comprueba que no es posible recargar saldo de una tarjeta que no existe
		FundamentosTest.interaccionGUI(RECARGA_SALDO, "No existe", dnis[3], 10.0);
	}

	@Test
	void realizaCompraErrorDNITest() {
		System.out.println("realizaCompraErrorDNITest");
		// comprueba que no es posible comprar con una tarjeta que no existe
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "No existe", dnis[1], 10.0);

		// regista las tarjetas y las carga con saldo
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[0], dirs[0], "B");
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[1], dirs[0], "VF");
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[2], dirs[0], "VN");
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[0], 10.0);
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[1], 20.0);
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[2], 3.0);		

		// comprueba que es posible comprar con tarjetas que existen
		FundamentosTest.interaccionGuiOK(REALIZA_COMPRA, dnis[0], 9.0);
		FundamentosTest.interaccionGuiOK(REALIZA_COMPRA, dnis[1], 12.0);
		FundamentosTest.interaccionGuiOK(REALIZA_COMPRA, dnis[2], 2.0);
	}

	@Test
	void realizaCompraErrorSaldoTest() {
		System.out.println("realizaCompraErrorSaldoTest");
		// regista las tarjetas
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[0], dirs[0], "B");
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[1], dirs[0], "VF");
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[2], dirs[0], "VN");

		// comprueba que las tarjetas no tienen saldo inicial
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[0], 1.0);
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[1], 1.0);
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[2], 1.0);

		// carga las tarjetas con saldo
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[0], 10.0);
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[1], 20.0);
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[2], 3.0);

		// comprueba que es posible comprar
		FundamentosTest.interaccionGuiOK(REALIZA_COMPRA, dnis[0], 9.0);
		FundamentosTest.interaccionGuiOK(REALIZA_COMPRA, dnis[1], 12.0);
		FundamentosTest.interaccionGuiOK(REALIZA_COMPRA, dnis[2], 2.0);	

		// comprueba que ahora no tienen suficiente saldo
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[0], 9.0);
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[1], 12.0);
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[2], 2.0);
	}

	@Test
	void canjeaPuntosErrorDNITest() {
		System.out.println("canjeaPuntosErrorDNITest");
		// comprueba que no se pueden canjear puntos de una tarjeta que no existe
		FundamentosTest.interaccionGUI(CANJEA_PUNTOS, "No existe", dnis[0], 1);

		// registra las tarjetas
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[0], dirs[0], "VF");
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[1], dirs[0], "VN");

		// comprueba que no se pueden canjear puntos de una tarjeta que no existe
		FundamentosTest.interaccionGUI(CANJEA_PUNTOS, "No existe", dnis[3], 1);
	}

	@Test
	void canjeaPuntosErrorNoViajeTest() {
		System.out.println("canjeaPuntosErrorNoViajeTest");
		// registra las tarjetas
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[0], dirs[0], "VF");
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[1], dirs[0], "B");
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[2], dirs[0], "VN");

		// comprueba que no se pueden canjear los puntos de una tarjeta basica
		FundamentosTest.interaccionGUI(CANJEA_PUNTOS, "no es", dnis[1], 1);
	}

	@Test
	void saldoInicialTest() {
		System.out.println("saldoInicialTest");
		// registra las tarjetas
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[0], dirs[0], "VF");
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[1], dirs[0], "B");
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[2], dirs[0], "VN");

		// comprueba que empiezan con saldo 0
		double saldo;
		saldo = FundamentosTest.leeDoubleGuiOK(SALDO_TARJETA, dnis[0]);
		assertEquals(0, saldo, 0.001);
		saldo = FundamentosTest.leeDoubleGuiOK(SALDO_TARJETA, dnis[1]);
		assertEquals(0, saldo, 0.001);
		saldo = FundamentosTest.leeDoubleGuiOK(SALDO_TARJETA, dnis[2]);
		assertEquals(0, saldo, 0.001);
	}

	@Test
	void listadoSaldoTrasRecargaTest() {
		System.out.println("listadoSaldoTrasRecargaTest");
		// regista las tarjetas
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[0], dirs[0], "VF");
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[1], dirs[0], "B");
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[2], dirs[0], "VN");

		// carga las tarjetas con saldo
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[0], 9.0);
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[1], 1.0);
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[2], 3.0);
		
		// comprueba que los saldos son correctos
		double saldo;
		saldo = FundamentosTest.leeDoubleGuiOK(SALDO_TARJETA, dnis[0]);
		assertEquals(9, saldo, 0.001);
		saldo = FundamentosTest.leeDoubleGuiOK(SALDO_TARJETA, dnis[1]);
		assertEquals(1, saldo, 0.001);
		saldo = FundamentosTest.leeDoubleGuiOK(SALDO_TARJETA, dnis[2]);
		assertEquals(3, saldo, 0.001);
	}

	@Test
	void comprasSaldoTarjetaBasicaTest() {
		System.out.println("comprasSaldoTarjetaBasicaTest");
		final double PORCENTAJE_PRECIO = 0.95;

		// registra tarjeta basica
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[3], dirs[1], "B");

		// comprueba que no puede realizar niguna compra con saldo 0
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[3], 1.0);

		// comprueba que no puede hacer una compra superior al saldo
		final double saldo100 = 100;
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[3], saldo100);
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[3], (saldo100 + 10));

		// hace compra valida y otra no valida
		double saldo = FundamentosTest.leeDoubleGuiOK(REALIZA_COMPRA, dnis[3], 25.0);
		assertEquals(saldo100 - 25 * PORCENTAJE_PRECIO, saldo, 0.001);
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[3], saldo100);

		// comprueba que puede hacer una compra valida
		double saldoAnterior = saldo;
		final double compra1 = 60;
		saldo = FundamentosTest.leeDoubleGuiOK(REALIZA_COMPRA, dnis[3], compra1);
		assertEquals(saldoAnterior - compra1 * PORCENTAJE_PRECIO, saldo, 0.001);

		// acumula mas y hace otra compra
		final double recarga = 30;
		final double compra2 = saldo + recarga;
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[3], recarga);
		final double saldoEsperado = saldo + recarga;
		saldo = FundamentosTest.leeDoubleGuiOK(REALIZA_COMPRA, dnis[3], compra2);
		assertEquals(saldoEsperado - compra2 * PORCENTAJE_PRECIO, saldo, 0.001);
	}

	@Test
	void comprasSaldoTarjetaViajesNormalTest() {
		System.out.println("comprasSaldoTarjetaViajesNormalTest");
		// registra tarjeta Viajes normal
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[3], dirs[1], "VN");

		// comprueba que no puede realizar niguna compra con saldo 0
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[3], 1.0);

		// comprueba que no puede hacer una compra superior al saldo
		final double saldo100 = 100;
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[3], saldo100);
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[3], (saldo100 + 10));

		// hace compra valida y otra no valida
		double saldo = FundamentosTest.leeDoubleGuiOK(REALIZA_COMPRA, dnis[3], 25.0);
		assertEquals(saldo100 - 25, saldo, 0.001);
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[3], saldo100);

		// comprueba que puede hacer una compra valida
		double saldoAnterior = saldo;
		final double compra1 = 60;
		saldo = FundamentosTest.leeDoubleGuiOK(REALIZA_COMPRA, dnis[3], compra1);
		assertEquals(saldoAnterior - compra1, saldo, 0.001);

		// acumula mas y hace otra compra
		final double recarga = 30;
		final double compra2 = saldo + recarga - 1;
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[3], recarga);
		final double saldoEsperado = saldo + recarga;
		saldo = FundamentosTest.leeDoubleGuiOK(REALIZA_COMPRA, dnis[3], compra2);
		assertEquals(saldoEsperado - compra2, saldo, 0.001);
	}

	@Test
	void comprasSaldoTarjetaViajesFamiliaTest() {
		System.out.println("comprasSaldoTarjetaViajesFamiliaTest");
		// registra tarjeta Viajes Familia
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[3], dirs[1], "VF");

		// comprueba que no puede realizar niguna compra con saldo 0
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[3], 1.0);

		// comprueba que no puede hacer una compra superior al saldo
		final double saldo100 = 100;
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[3], saldo100);
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[3], (saldo100 + 10));

		// hace compra valida y otra no valida
		double saldo = FundamentosTest.leeDoubleGuiOK(REALIZA_COMPRA, dnis[3], 25.0);
		assertEquals(saldo100 - 25, saldo, 0.001);
		FundamentosTest.interaccionGUI(REALIZA_COMPRA, "no tiene", dnis[3], saldo100);

		// comprueba que puede hacer una compra valida
		double saldoAnterior = saldo;
		final double compra1 = 60;
		saldo = FundamentosTest.leeDoubleGuiOK(REALIZA_COMPRA, dnis[3], compra1);
		assertEquals(saldoAnterior - compra1, saldo, 0.001);

		// acumula mas y hace otra compra
		final double recarga = 30;
		final double compra2 = saldo + recarga - 1;
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[3], recarga);
		final double saldoEsperado = saldo + recarga;
		saldo = FundamentosTest.leeDoubleGuiOK(REALIZA_COMPRA, dnis[3], compra2);
		assertEquals(saldoEsperado - compra2, saldo, 0.001);
	}

	@Test
	void canjeaPuntosFamiliaTest() {
		System.out.println("canjeaPuntosFamiliaTest");
		// registra tarjeta Viajes normal y carga saldo
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[3], dirs[1], "VF");
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[3], 3000.0);

		// comprueba que al principio no puede gastar ningun punto
		FundamentosTest.interaccionGUI(CANJEA_PUNTOS, "no tiene", dnis[3], 1);

		// hace una compra (3 puntos) y gasta 2 puntos
		FundamentosTest.interaccionGuiOK(REALIZA_COMPRA,
				dnis[3], (EUROS_POR_PUNTO_FAMILIA * 3 + 1));
		int puntos = FundamentosTest.leeIntGuiOK(CANJEA_PUNTOS, dnis[3], 2);
		assertEquals(1, puntos);
		
		// intenta gastar 2 puntos (mas de los que quedan)
		FundamentosTest.interaccionGUI(CANJEA_PUNTOS, "no tiene", dnis[3], 2);

		// hace otra compra (4 puntos) y agota los 5 puntos que tiene
		FundamentosTest.interaccionGuiOK(REALIZA_COMPRA,
				dnis[3], (EUROS_POR_PUNTO_FAMILIA * 4 + 1));
		puntos = FundamentosTest.leeIntGuiOK(CANJEA_PUNTOS, dnis[3], 5);
		assertEquals(0, puntos);	
	}

	@Test
	void canjeaPuntosNormalTest() {
		System.out.println("canjeaPuntosNormalTest");
		// registra tarjeta Viajes normal y carga saldo
		FundamentosTest.interaccionGuiOK(REGISTRA_TARJETA, dnis[3], dirs[1], "VN");
		FundamentosTest.interaccionGuiOK(RECARGA_SALDO, dnis[3], 3000.0);

		// comprueba que al principio no puede gastar ningun punto
		FundamentosTest.interaccionGUI(CANJEA_PUNTOS, "no tiene", dnis[3], 1);

		// hace tres compras (3 puntos) y gasta 2 puntos
		FundamentosTest.interaccionGuiOK(REALIZA_COMPRA, dnis[3], 1.0);
		FundamentosTest.interaccionGuiOK(REALIZA_COMPRA, dnis[3], 20.0);
		FundamentosTest.interaccionGuiOK(REALIZA_COMPRA, dnis[3], 200.0);
		int puntos = FundamentosTest.leeIntGuiOK(CANJEA_PUNTOS, dnis[3], 2);
		assertEquals(1, puntos);
		
		// intenta gastar 2 puntos (mas de los que quedan)
		FundamentosTest.interaccionGUI(CANJEA_PUNTOS, "no tiene", dnis[3], 2);

		// hace otra compra (2 puntos) y agota los 2 puntos que tiene
		FundamentosTest.interaccionGuiOK(REALIZA_COMPRA, dnis[3], 120.0);
		puntos = FundamentosTest.leeIntGuiOK(CANJEA_PUNTOS, dnis[3], 2);
		assertEquals(0, puntos);	
	}

	@Test
	void saldoTarjetaBasicaTest() {
		TarjetaBasica tb = new TarjetaBasica("12345678A");

		// comprueba que el saldo inicial es 0
		assertEquals(0.0, tb.saldo());

		// comprueba que no puede realizar niguna compra con saldo 0
		try {
			tb.realizaCompra(1);
			fail("No deberia haber permitido la compra");
		} catch (Tarjeta.SaldoInsuficiente e) {
			// el comportamiento esperado es que se lance la excepcion
		}

		// comprueba que no puede hacer una compra superior al saldo
		tb.anhadeSaldo(100);
		assertEquals(100, tb.saldo());

		try {
			tb.realizaCompra(110);
			fail("No deberia haber permitido la compra");
		} catch (Tarjeta.SaldoInsuficiente e) {
			// el comportamiento esperado es que se lance la excepcion
		}
		assertEquals(100, tb.saldo());

		// hace compra valida y otra no valida
		tb.realizaCompra(25);
		try {
			tb.realizaCompra(90);
			fail("No deberia haber permitido la compra");
		} catch (Tarjeta.SaldoInsuficiente e) {
			// el comportamiento esperado es que se lance la excepcion
		}

		// comprueba que puede hacer una compra valida
		tb.realizaCompra(75);
		assertEquals(5.0, tb.saldo(), 0.001);

		// acumula mas y hace otra compra
		tb.anhadeSaldo(5.0);
		tb.realizaCompra(9.0);
	}

	@Test
	void saldoTarjetaViajesNormalTest() {
		final double saldoInicial = 100;
		final double primeraCompra = 25;
		final double segundaCompra = 85;
		TarjetaViajesNormal tvn = new TarjetaViajesNormal("12345678A", "Una calle");

		// comprueba que el saldo inicial es 0
		assertEquals(0.0, tvn.saldo());

		// ccomprueba que no puede realizar niguna compra con saldo 0
		try {
			tvn.realizaCompra(1);
			fail("No deberia haber permitido la compra");
		} catch (Tarjeta.SaldoInsuficiente e) {
			// el comportamiento esperado es que se lance la excepcion
		}

		// comprueba que no puede hacer una compra superior al saldo
		tvn.anhadeSaldo(saldoInicial);
		assertEquals(saldoInicial, tvn.saldo());

		try {
			tvn.realizaCompra(saldoInicial + 1);
			fail("No deberia haber permitido la compra");
		} catch (Tarjeta.SaldoInsuficiente e) {
			// el comportamiento esperado es que se lance la excepcion
		}

		// hace compra valida y otra no valida
		tvn.realizaCompra(primeraCompra);
		try {
			tvn.realizaCompra(saldoInicial - primeraCompra + 1);
			fail("No deberia haber permitido la compra");
		} catch (Tarjeta.SaldoInsuficiente e) {
			// el comportamiento esperado es que se lance la excepcion
		}
		assertEquals(saldoInicial - primeraCompra, tvn.saldo(), 0.001);

		// comprueba que puede agotar el saldo
		tvn.realizaCompra(saldoInicial - primeraCompra);
		assertEquals(0.0, tvn.saldo(), 0.001);

		// acumula mas y hace otra compra
		tvn.anhadeSaldo(segundaCompra + 10);
		tvn.realizaCompra(segundaCompra);
		assertEquals(10.0, tvn.saldo(), 0.001);
	}

	@Test
	void saldoTarjetaViajesFamiliaTest() {
		final double saldoInicial = 100;
		final double primeraCompra = 25;
		final double segundaCompra = 85;
		TarjetaViajesFamilia tvf = new TarjetaViajesFamilia("12345678A", "Una calle");

		// comprueba que el saldo inicial es 0
		assertEquals(0.0, tvf.saldo());

		// comprueba que no puede realizar niguna compra con saldo 0
		try {
			tvf.realizaCompra(1);
			fail("No deberia haber permitido la compra");
		} catch (Tarjeta.SaldoInsuficiente e) {
			// el comportamiento esperado es que se lance la excepcion
		}

		// comprueba que no puede hacer una compra superior al saldo
		tvf.anhadeSaldo(saldoInicial);
		assertEquals(saldoInicial, tvf.saldo());

		try {
			tvf.realizaCompra(saldoInicial + 1);
			fail("No deberia haber permitido la compra");
		} catch (Tarjeta.SaldoInsuficiente e) {
			// el comportamiento esperado es que se lance la excepcion
		}

		// hace compra valida y otra no valida
		tvf.realizaCompra(primeraCompra);
		try {
			tvf.realizaCompra(saldoInicial - primeraCompra + 1);
			fail("No deberia haber permitido la compra");
		} catch (Tarjeta.SaldoInsuficiente e) {
			// el comportamiento esperado es que se lance la excepcion
		}

		// comprueba que puede agotar el saldo
		tvf.realizaCompra(saldoInicial - primeraCompra);
		assertEquals(0.0, tvf.saldo(), 0.001);

		// acumula mas y hace otra compra
		tvf.anhadeSaldo(segundaCompra + 10);
		tvf.realizaCompra(segundaCompra);
		assertEquals(10.0, tvf.saldo(), 0.001);
	}

	@Test
	void observadoresTest() {
		final double saldoInicial = 70;
		TarjetaBasica tb = new TarjetaBasica("11111111A");
		TarjetaViajesNormal tvn = new TarjetaViajesNormal("22222222A", "Una calle");
		TarjetaViajesFamilia tvf = new TarjetaViajesFamilia("33333333A", "Otra calle");

		assertEquals("11111111A", tb.dni());		
		assertEquals("22222222A", tvn.dni());		
		assertEquals("33333333A", tvf.dni());

		assertEquals("Una calle", tvn.direccion());		
		assertEquals("Otra calle", tvf.direccion());

		assertEquals(0.0, tb.saldo());	
		assertEquals(0.0, tvn.saldo());	
		assertEquals(0.0, tvf.saldo());

		tb.anhadeSaldo(saldoInicial);
		tvn.anhadeSaldo(saldoInicial);
		tvf.anhadeSaldo(saldoInicial);

		assertEquals(saldoInicial, tb.saldo());	
		assertEquals(saldoInicial, tvn.saldo());	
		assertEquals(saldoInicial, tvf.saldo());
	}

	@Test
	void anhadePuntosFamiliaTest() {
		TarjetaViajesFamilia tvf =
				new TarjetaViajesFamilia("33333333A", "Otra calle");

		// comprueba que comienza con 0 puntos
		assertEquals(0, tvf.puntosViajeAcumulados());

		// carga la tarjeta con mucho saldo para que permita compras
		tvf.anhadeSaldo(1000.0);

		// comprueba que sigue teniendo 0 puntos
		assertEquals(0, tvf.puntosViajeAcumulados());

		// Realiza compras y comprueba puntos
		tvf.realizaCompra(EUROS_POR_PUNTO_FAMILIA + 1);
		assertEquals(1, tvf.puntosViajeAcumulados());

		tvf.realizaCompra(EUROS_POR_PUNTO_FAMILIA - 1);
		assertEquals(1, tvf.puntosViajeAcumulados());

		tvf.realizaCompra(EUROS_POR_PUNTO_FAMILIA * 5 + 1);
		assertEquals(6, tvf.puntosViajeAcumulados());
	}

	@Test
	void anhadePuntosNormalTest() {
		TarjetaViajesNormal tvn =
				new TarjetaViajesNormal("33333333A", "Otra calle");

		// comprueba que comienza con 0 puntos
		assertEquals(0, tvn.puntosViajeAcumulados());

		// carga la tarjeta con mucho saldo para que permita compras
		tvn.anhadeSaldo(1000.0);

		// comprueba que sigue teniendo 0 puntos
		assertEquals(0, tvn.puntosViajeAcumulados());

		// Realiza compras y comprueba puntos
		tvn.realizaCompra(100);
		assertEquals(1, tvn.puntosViajeAcumulados());

		tvn.realizaCompra(1);
		assertEquals(2, tvn.puntosViajeAcumulados());

		tvn.realizaCompra(200);
		assertEquals(3, tvn.puntosViajeAcumulados());
	}

	@Test
	void gastaPuntosFamiliaTest() {
		TarjetaViajesFamilia tvf =
				new TarjetaViajesFamilia("33333333A", "Otra calle");

		// carga la tarjeta con mucho saldo para que permita compras
		tvf.anhadeSaldo(1000.0);

		// comprueba que al principio no puede gastar ningun punto
		try {
			tvf.gastaPuntosViaje(1);
			fail("No deberia haber permitido gastar puntos");
		} catch (TarjetaViajes.PuntosInsuficientes e) {
			// el comportamiento esperado es que se lance la excepcion
		}

		// hace una compra y gasta puntos
		tvf.realizaCompra(EUROS_POR_PUNTO_FAMILIA * 3 + 1);
		tvf.gastaPuntosViaje(2);
		assertEquals(1, tvf.puntosViajeAcumulados());		

		// intenta gastar mas de los que quedan
		try {
			tvf.gastaPuntosViaje(2);
			fail("No deberia haber permitido gastar puntos");
		} catch (TarjetaViajes.PuntosInsuficientes e) {
			// el comportamiento esperado es que se lance la excepcion
		}
		assertEquals(1, tvf.puntosViajeAcumulados());

		// hace otra compra y agota los puntos
		tvf.realizaCompra(EUROS_POR_PUNTO_FAMILIA * 4 + 1);
		assertEquals(5, tvf.puntosViajeAcumulados());

		tvf.gastaPuntosViaje(5);
		assertEquals(0, tvf.puntosViajeAcumulados());	
	}

	@Test
	void gastaPuntosNormalTest() {
		TarjetaViajesNormal tvn =
				new TarjetaViajesNormal("33333333A", "Otra calle");

		// carga la tarjeta con mucho saldo para que permita compras
		tvn.anhadeSaldo(1000.0);

		// comprueba que al principio no puede gastar ningun punto
		try {
			tvn.gastaPuntosViaje(1);
			fail("No deberia haber permitido gastar puntos");
		} catch (TarjetaViajes.PuntosInsuficientes e) {
			// el comportamiento esperado es que se lance la excepcion
		}

		// hace varias compras y gasta puntos
		tvn.realizaCompra(1);
		tvn.realizaCompra(123);
		tvn.realizaCompra(234);
		tvn.gastaPuntosViaje(2);
		assertEquals(1, tvn.puntosViajeAcumulados());		

		// intenta gastar mas de los que quedan
		try {
			tvn.gastaPuntosViaje(2);
			fail("No deberia haber permitido gastar puntos");
		} catch (TarjetaViajes.PuntosInsuficientes e) {
			// el comportamiento esperado es que se lance la excepcion
		}
		assertEquals(1, tvn.puntosViajeAcumulados());

		// hace otras compra y agota los puntos
		tvn.realizaCompra(1);
		tvn.realizaCompra(5);
		assertEquals(3, tvn.puntosViajeAcumulados());

		tvn.gastaPuntosViaje(3);
		assertEquals(0, tvn.puntosViajeAcumulados());	
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
		FundamentosTest.lanzaGUI(GUIGestionTarjetas.class);
	}

	/**
	 * Se ejecuta despues de cada test.
	 * @throws InterruptedException error en thread main.
	 */
	@AfterEach
	public void finalizaGUI() throws InterruptedException {
		FundamentosTest.finalizaGUI(GUIGestionTarjetas.FIN_APLICACION);
	}

}
