package pract04.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pract04.modelo.*;

/**
 * Test de la clase Transbordador.
 * 
 * @author  Metodos de Programacion (UC)
 * @version sep-23
 */
class TransbordadorTest {

	@Test
	void superaPesoMaximo20000Test() {
		System.out.println("superaPesoMaximo20000Test");
		final int pesoMaxSoportado = 20000;
		final int maxCapacidadVehiculos = 10;
		double precio;
		Transbordador transbordador = new Transbordador(pesoMaxSoportado, maxCapacidadVehiculos);

		// comprueba que se pueden anhadir vehiculos hasta superar el peso maximo
		precio = transbordador.cargaVehiculo(new Vehiculo("A", pesoMaxSoportado / 4, 4));
		assertTrue(precio > 0);
		precio = transbordador.cargaVehiculo(new Vehiculo("B", pesoMaxSoportado / 4, 4));
		assertTrue(precio > 0);
		precio = transbordador.cargaVehiculo(new Vehiculo("C", pesoMaxSoportado / 4, 4));
		assertTrue(precio > 0);

		// este supera el peso maximo
		precio = transbordador.cargaVehiculo(new Vehiculo("D", pesoMaxSoportado / 3, 4));
		assertEquals(Transbordador.SUPERA_PESO_MAX,  precio);		
	}

	@Test
	void superaPesoMaximo200Test() {
		System.out.println("superaPesoMaximo200Test");
		final int pesoMaxSoportado = 200;
		final int maxCapacidadVehiculos = 10;
		double precio;
		Transbordador transbordador = new Transbordador(pesoMaxSoportado, maxCapacidadVehiculos);

		// comprueba que se pueden anhadir vehiculos hasta superar el peso maximo
		precio = transbordador.cargaVehiculo(new Vehiculo("A", pesoMaxSoportado / 4, 4));
		assertTrue(precio > 0);
		precio = transbordador.cargaVehiculo(new Vehiculo("B", pesoMaxSoportado / 4, 4));
		assertTrue(precio > 0);
		precio = transbordador.cargaVehiculo(new Vehiculo("C", pesoMaxSoportado / 4, 4));
		assertTrue(precio > 0);

		// este supera el peso maximo
		precio = transbordador.cargaVehiculo(new Vehiculo("D", pesoMaxSoportado / 3, 4));
		assertEquals(Transbordador.SUPERA_PESO_MAX,  precio);		
	}

	@Test
	void superaCapacidadSimpleTest() {
		System.out.println("superaCapacidadSimpleTest");
		final int pesoMaxSoportado = 20000;
		final int maxCapacidadVehiculos = 2;
		double precio;
		Transbordador transbordador = new Transbordador(pesoMaxSoportado, maxCapacidadVehiculos);

		// comprueba que se pueden anhadir vehiculos hasta igualar la capacidad
		precio = transbordador.cargaVehiculo(new Vehiculo("A", 1, 1));
		assertTrue(precio > 0);
		precio = transbordador.cargaVehiculo(new Vehiculo("B", 1, 1));
		assertTrue(precio > 0);

		// comprueba que detecta error cuando se supera la capacidad
		precio = transbordador.cargaVehiculo(new Vehiculo("C", 1, 1));
		assertEquals(Transbordador.SUPERA_NUM_MAX_VEHICULOS,  precio);		
	}

	@Test
	void superaCapacidad10Test() {
		System.out.println("superaCapacidad10Test");
		final int pesoMaxSoportado = 20000;
		final int maxCapacidadVehiculos = 10;
		double precio;
		Transbordador transbordador = new Transbordador(pesoMaxSoportado, maxCapacidadVehiculos);

		// comprueba que se pueden anhadir vehiculos hasta superar la capacidad
		for (int i = 0; i < maxCapacidadVehiculos - 1; i++) {
			precio = transbordador.cargaVehiculo(new Vehiculo("A" + i, 1, 4));
			assertTrue(precio > 0);
		}

		// con este se alcanza la capacidad, por lo que tampoco deberia retornar error
		precio = transbordador.cargaVehiculo(new Vehiculo("B", 1, 4));
		assertTrue(precio > 0);

		// este supera la capacidad
		precio = transbordador.cargaVehiculo(new Vehiculo("C", 1, 4));
		assertEquals(Transbordador.SUPERA_NUM_MAX_VEHICULOS,  precio);		
	}

	@Test
	void superaCapacidad13Test() {
		System.out.println("superaCapacidad13Test");
		final int pesoMaxSoportado = 20000;
		final int maxCapacidadVehiculos = 13;
		double precio;
		Transbordador transbordador = new Transbordador(pesoMaxSoportado, maxCapacidadVehiculos);

		// comprueba que se pueden anhadir vehiculos hasta superar la capacidad
		for (int i = 0; i < maxCapacidadVehiculos - 1; i++) {
			precio = transbordador.cargaVehiculo(new Vehiculo("A" + i, 1, 4));
			assertTrue(precio > 0);
		}

		// con este se alcanza la capacidad, por lo que tampoco deberia retornar error
		precio = transbordador.cargaVehiculo(new Vehiculo("B", 1, 4));
		assertTrue(precio > 0);

		// este supera la capacidad
		precio = transbordador.cargaVehiculo(new Vehiculo("C", 1, 4));
		assertEquals(Transbordador.SUPERA_NUM_MAX_VEHICULOS,  precio);		
	}

	@Test
	void matriculaRepetidaSimpleTest() {
		System.out.println("matriculaRepetidaSimpleTest");
		final int pesoMaxSoportado = 20000;
		final int maxCapacidadVehiculos = 10;
		double precio;
		Transbordador transbordador = new Transbordador(pesoMaxSoportado, maxCapacidadVehiculos);

		// carga un vehiculo
		precio = transbordador.cargaVehiculo(new Vehiculo("S-1234-S", 1, 1));
		assertTrue(precio > 0);

		// comprueba que falla al tratar de cargar un vehiculo con la misma matricula
		precio = transbordador.cargaVehiculo(new Vehiculo("S-1234-S", 1, 1));
		assertEquals(Transbordador.VEHICULO_YA_EN_TRANSBORDADOR,  precio);
	}

	@Test
	void matriculaRepetidaTest() {
		System.out.println("matriculaRepetidaTest");
		final int pesoMaxSoportado = 20000;
		final int maxCapacidadVehiculos = 10;
		double precio;
		Transbordador transbordador = new Transbordador(pesoMaxSoportado, maxCapacidadVehiculos);

		// comprueba que se pueden anhadir vehiculos mientras no se repita la matricula
		for (int i = 0; i < maxCapacidadVehiculos - 3; i++) {
			precio = transbordador.cargaVehiculo(new Vehiculo("A" + i, 1, 4));
			assertTrue(precio > 0);
		}

		// comprueba que se no pueden anhadir vehiculos con matricula repetida
		for (int i = 0; i < maxCapacidadVehiculos - 3; i++) {
			precio = transbordador.cargaVehiculo(new Vehiculo("A" + i, 1, 4));
			assertEquals(Transbordador.VEHICULO_YA_EN_TRANSBORDADOR,  precio);
		}
	}

	@Test
	void precioTest() {
		System.out.println("precioTest");
		final int pesoMaxSoportado = 20000;
		final int maxCapacidadVehiculos = 10;
		final double precioPorOcupante = 1.2;
		final double precioPorKg = 0.003;
		double precio;
		double precioEsperado;
		Vehiculo vehiculo;
		Transbordador transbordador = new Transbordador(pesoMaxSoportado, maxCapacidadVehiculos);

		// comprueba que el precio calculado es correcto
		final double peso = 1002.4;
		final int numOcupantes = 3;
		vehiculo = new Vehiculo("A", peso, numOcupantes);
		precioEsperado = numOcupantes * precioPorOcupante +
				peso * precioPorKg;
		precio = transbordador.cargaVehiculo(vehiculo);
		assertEquals(precioEsperado, precio);
	}

	@Test
	void buscaVehiculoConCaracteristicas1Test() {
		System.out.println("buscaVehiculoConCaracteristicas1Test");
		final int pesoMaxSoportado = 20000;
		final int maxCapacidadVehiculos = 10;
		Vehiculo vehiculo;
		Transbordador transbordador = new Transbordador(pesoMaxSoportado, maxCapacidadVehiculos);

		// comprueba que no encuentra ningun vehiculo con el transbordador vacio
		vehiculo = transbordador.buscaVehiculoConCaracteristicas(2, 1000);
		assertEquals(null, vehiculo);

		// comprueva que encuentra el unico vehiculo que existe
		Vehiculo v1 = new Vehiculo("A", 1000, 2);
		transbordador.cargaVehiculo(v1);
		vehiculo = transbordador.buscaVehiculoConCaracteristicas(2, 500);
		assertEquals(v1, vehiculo);
		vehiculo = transbordador.buscaVehiculoConCaracteristicas(2, 1000);
		assertEquals(v1, vehiculo);

		// comprueba que no lo encuentra si el peso es mayor o el num ocupantes distinto
		vehiculo = transbordador.buscaVehiculoConCaracteristicas(2, 1001);
		assertEquals(null, vehiculo);
		vehiculo = transbordador.buscaVehiculoConCaracteristicas(3, 500);
		assertEquals(null, vehiculo);
		vehiculo = transbordador.buscaVehiculoConCaracteristicas(1, 500);
		assertEquals(null, vehiculo);
	}

	@Test
	void buscaVehiculoConCaracteristicas3Test() {
		System.out.println("buscaVehiculoConCaracteristicas3Test");
		final int pesoMaxSoportado = 20000;
		final int maxCapacidadVehiculos = 10;
		Vehiculo vehiculo;
		Transbordador transbordador = new Transbordador(pesoMaxSoportado, maxCapacidadVehiculos);

		// carga 3 vehiculos
		Vehiculo v1 = new Vehiculo("A", 1000, 3);
		transbordador.cargaVehiculo(v1);
		Vehiculo v2 = new Vehiculo("B", 2000, 2);
		transbordador.cargaVehiculo(v2);
		Vehiculo v3 = new Vehiculo("C", 1000, 1);
		transbordador.cargaVehiculo(v3);

		// comprueba que encuentra el primer vehiculo
		vehiculo = transbordador.buscaVehiculoConCaracteristicas(3, 1000);
		assertEquals(v1, vehiculo);
		vehiculo = transbordador.buscaVehiculoConCaracteristicas(3, 900);
		assertEquals(v1, vehiculo);

		// comprueba que encuentra el segundo vehiculo
		vehiculo = transbordador.buscaVehiculoConCaracteristicas(2, 1000);
		assertEquals(v2, vehiculo);
		vehiculo = transbordador.buscaVehiculoConCaracteristicas(2, 2000);
		assertEquals(v2, vehiculo);

		// comprueba que encuentra el tercer vehiculo
		vehiculo = transbordador.buscaVehiculoConCaracteristicas(1, 1000);
		assertEquals(v3, vehiculo);
		vehiculo = transbordador.buscaVehiculoConCaracteristicas(1, 10);
		assertEquals(v3, vehiculo);

		// comprueba que no encuentra uno que no coincide
		vehiculo = transbordador.buscaVehiculoConCaracteristicas(1, 2000);
		assertEquals(null, vehiculo);
		vehiculo = transbordador.buscaVehiculoConCaracteristicas(4, 1000);
		assertEquals(null, vehiculo);
	}

	@Test
	void vaciaTransbordadorTest() {
		System.out.println("vaciaTransbordadorTest");
		final int pesoMaxSoportado = 20000;
		final int maxCapacidadVehiculos = 10;
		final int pesoPorVehiculo = pesoMaxSoportado / maxCapacidadVehiculos - 1;
		double precio;
		Transbordador transbordador = new Transbordador(pesoMaxSoportado, maxCapacidadVehiculos);

		// casi llena el transbordador
		for (int i = 0; i < maxCapacidadVehiculos; i++) {
			transbordador.cargaVehiculo(new Vehiculo("B" + i, pesoPorVehiculo, 1));
		}

		// comprueba que no cabe otro
		precio = transbordador.cargaVehiculo(new Vehiculo("C", pesoPorVehiculo, 1));
		assertTrue(Transbordador.SUPERA_NUM_MAX_VEHICULOS == precio ||
				Transbordador.SUPERA_PESO_MAX == precio);

		// vacia el transbordador y comprueba que ahora si cabe
		transbordador.vaciaTransbordador();
		precio = transbordador.cargaVehiculo(new Vehiculo("C", pesoPorVehiculo, 1));
		assertTrue(precio > 0);
	}

}
