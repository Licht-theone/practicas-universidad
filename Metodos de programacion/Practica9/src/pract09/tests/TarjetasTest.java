package pract09.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pract09.tarjetas.*;


/**
 * Test de jerarquía de clases de tarjetas.
 * 
 * @author Metodos de Programacion (UC)
 * @version abr-20
 */
class TarjetasTest {
	private final double eurosPorPuntoFamilia = 50;

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
		tvf.realizaCompra(eurosPorPuntoFamilia + 1);
		assertEquals(1, tvf.puntosViajeAcumulados());

		tvf.realizaCompra(eurosPorPuntoFamilia - 1);
		assertEquals(1, tvf.puntosViajeAcumulados());

		tvf.realizaCompra(eurosPorPuntoFamilia * 5 + 1);
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
		tvf.realizaCompra(eurosPorPuntoFamilia * 3 + 1);
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
		tvf.realizaCompra(eurosPorPuntoFamilia * 4 + 1);
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

}
