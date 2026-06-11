

import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.Test;


/**
 * Test de la clase EmpresaDistribucion.
 * El objetivo de esta clase es servir como ayuda al alumno para detectar errores
 * funcionales (no sirve para detectar otro tipo de errores, como errores de estructura,
 * eleccion incorrecta de EDs o TDAs, etc.).
 * Ademas, se debe tener en cuenta que el que los tests pasen sin errores no es una
 * garantia absoluta del correcto funcionamiento de la aplicacion, dado que es imposible
 * realizar una bateria de tests capaz de detectar todos los posibles errores que podrian
 * llegar cometer los programadores.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2023
 */
class EmpresaDistribucionTest {

	private static final String MAT1 = "1111-AAA";
	private static final String MAT2 = "2222-BBB";
	private static final String MAT3 = "3333-CCC";	
	private static final int NUM1 = 1234;
	private static final int NUM2 = 5678;

	@Test
	void registraEntregaPendienteErrorNumTest() {
		System.out.println("registraEntregaPendienteErrorNumTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// prueba que no se puede registrar una entrega para un cliente no valido
		try {
			empresa.registraEntregaPendiente(NUM1 + 1, "Descripcion");
			fail("Deberia haber lanzado la excepcion");
		} catch (EmpresaDistribucion.NumClienteIncorrecto e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// prueba que se puede registrar para un cliente valido
		empresa.registraEntregaPendiente(NUM1, "Descripcion");
		empresa.registraEntregaPendiente(NUM2, "Descripcion");
	}

	@Test
	void asignaSiguienteEntregaPendienteErrorMatTest() {
		System.out.println("asignaSiguienteEntregaPendienteErrorMatTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// registra una entrega
		empresa.registraEntregaPendiente(NUM1, "Descripcion");

		// prueba que no se puede asignar una entrega a una matricula no valida
		try {
			empresa.asignaSiguienteEntregaPendienteACamion(MAT1 + "A", 0);
			fail("Deberia haber lanzado la excepcion");
		} catch (EmpresaDistribucion.MatriculaIncorrecta e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// prueba que se puede asignar una entrega a una matricula valida
		empresa.asignaSiguienteEntregaPendienteACamion(MAT2, 0);
	}

	@Test
	void asignaSiguienteEntregaPendienteErrorPosTest() {
		System.out.println("asignaSiguienteEntregaPendienteErrorPosTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// registra cinco entregas
		empresa.registraEntregaPendiente(NUM1, "Descripcion1");
		empresa.registraEntregaPendiente(NUM1, "Descripcion2");
		empresa.registraEntregaPendiente(NUM2, "Descripcion3");
		empresa.registraEntregaPendiente(NUM2, "Descripcion4");
		empresa.registraEntregaPendiente(NUM1, "Descripcion5");

		// prueba que no se puede asignar la primera entrega con una posicion mayor que 0
		try {
			empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 1);
			fail("Deberia haber lanzado la excepcion");
		} catch (Camion.PosicionRutaIncorrecta e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// asigna en la posicion 0 (entregas asignadas = 1)
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 0);

		// prueba que no se permiten posiciones negativas
		try {
			empresa.asignaSiguienteEntregaPendienteACamion(MAT1, -1);
			fail("Deberia haber lanzado la excepcion");
		} catch (Camion.PosicionRutaIncorrecta e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// prueba que no se permite una posicion mayor que 1
		try {
			empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 2);
			fail("Deberia haber lanzado la excepcion");
		} catch (Camion.PosicionRutaIncorrecta e) {
			// el comportamiento esperado es que lance la excepcion
		}
		try {
			empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 4);
			fail("Deberia haber lanzado la excepcion");
		} catch (Camion.PosicionRutaIncorrecta e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// asigna en la posicion 1 (entregas asignadas = 2)
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 1);

		// asigna en la posicion 0 (entregas asignadas = 3)
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 0);

		// prueba que no se puede asignar en la posicion 5
		try {
			empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 5);
			fail("Deberia haber lanzado la excepcion");
		} catch (Camion.PosicionRutaIncorrecta e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// prueba que no se puede asignar en una posicion negativa
		try {
			empresa.asignaSiguienteEntregaPendienteACamion(MAT1, -5);
			fail("Deberia haber lanzado la excepcion");
		} catch (Camion.PosicionRutaIncorrecta e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// asigna en la posicion 1 (entregas asignadas = 4)
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 1);

		// asigna en la posicion 4 (entregas asignadas = 5)
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 4);	
	}

	@Test
	void asignaSiguienteEntregaPendienteErrorNoPendienteTest() {
		System.out.println("asignaSiguienteEntregaPendienteErrorNoPendienteTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// prueba que no se puede asignar si no hay entregas pendientes
		try {
			empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 0);
			fail("Deberia haber lanzado la excepcion");
		} catch (EmpresaDistribucion.NoHayEntregasPendientes e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// registra una entrega
		empresa.registraEntregaPendiente(NUM1, "Descripcion1");

		// prueba que ahora si se puede asignar
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 0);


		// prueba que otra vez no se puede asignar
		try {
			empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 0);
			fail("Deberia haber lanzado la excepcion");
		} catch (EmpresaDistribucion.NoHayEntregasPendientes e) {
			// el comportamiento esperado es que lance la excepcion
		}
	}

	@Test
	void entregaRealizadaErrorMatTest() {
		System.out.println("entregaRealizadaErrorMatTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// comprueba que falla para una matricula no valida
		try {
			empresa.camionRealizaEntrega(MAT1 + "Z");
			fail("Deberia haber lanzado la excepcion");
		} catch (EmpresaDistribucion.MatriculaIncorrecta e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// comprueba que funciona para una valida
		empresa.registraEntregaPendiente(NUM2, "Descripcion");
		empresa.asignaSiguienteEntregaPendienteACamion(MAT3, 0);
		empresa.camionRealizaEntrega(MAT3);
	}

	@Test
	void entregaRealizadaErrorNoRutaTest() {
		System.out.println("entregaRealizadaErrorNoRutaTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// comprueba que falla para un camion sin entregas en su ruta
		try {
			empresa.camionRealizaEntrega(MAT1);
			fail("Deberia haber lanzado la excepcion");
		} catch (Camion.RutaEntregasVacia e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// comprueba que funciona para un camion con una entrega en su ruta
		empresa.registraEntregaPendiente(NUM2, "Descripcion");
		empresa.asignaSiguienteEntregaPendienteACamion(MAT2, 0);
		empresa.camionRealizaEntrega(MAT2);

		// comprueba que falla para un camion sin entregas en su ruta
		try {
			empresa.camionRealizaEntrega(MAT1);
			fail("Deberia haber lanzado la excepcion");
		} catch (Camion.RutaEntregasVacia e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// comprueba que falla despues de realizar la entrega del camion MAT2
		try {
			empresa.camionRealizaEntrega(MAT2);
			fail("Deberia haber lanzado la excepcion");
		} catch (Camion.RutaEntregasVacia e) {
			// el comportamiento esperado es que lance la excepcion
		}
	}

	@Test
	void ultimasEntregasErrorNumTest() {
		System.out.println("ultimasEntregasErrorNumTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// prueba que falla para un cliente no valido
		try {
			empresa.ultimasEntregas(NUM1 + 3);
			fail("Deberia haber lanzado la excepcion");
		} catch (EmpresaDistribucion.NumClienteIncorrecto e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// comprueba que funciona para un cliente valido
		empresa.ultimasEntregas(NUM2);
		empresa.ultimasEntregas(NUM1);
	}

	@Test
	void eliminaEntregasClienteAsignadasErrorNumTest() {
		System.out.println("eliminaEntregasClienteAsignadasErrorNumTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// prueba que falla para un cliente no valido
		try {
			empresa.eliminaEntregasClienteAsignadas(NUM1 + 3);
			fail("Deberia haber lanzado la excepcion");
		} catch (EmpresaDistribucion.NumClienteIncorrecto e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// comprueba que funciona para un cliente valido
		empresa.eliminaEntregasClienteAsignadas(NUM2);
		empresa.eliminaEntregasClienteAsignadas(NUM1);
	}

	@Test
	void registraAsignaRealizaSimpleTest() {
		System.out.println("registraAsignaRealizaSimpleTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// registra, asigna y realiza una entrega
		empresa.registraEntregaPendiente(NUM1, "Descripcion1");
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 0);
		empresa.camionRealizaEntrega(MAT1);

		// comprueba que el cliente tiene la entrega en sus ultimas entregas
		Collection<Entrega> entregas1 = empresa.ultimasEntregas(NUM1);
		System.out.println(" entregas1:" + entregas1);
		assertEquals(1, entregas1.size());
		assertEquals("Descripcion1", entregas1.iterator().next().descripcion());
	}

	@Test
	void ordenAsignacionTest() {
		System.out.println("ordenAsignacionTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// registra varias entregas
		empresa.registraEntregaPendiente(NUM1, "Descripcion1");
		empresa.registraEntregaPendiente(NUM2, "Descripcion2");
		empresa.registraEntregaPendiente(NUM1, "Descripcion3");

		// asigna y realiza la primera entrega (NUM1, "Descripcion1")
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 0);
		empresa.camionRealizaEntrega(MAT1);

		// obtiene las entregas de cada cliente
		Collection<Entrega> entregas1 = empresa.ultimasEntregas(NUM1);
		System.out.println(" entregas1:" + entregas1);
		Collection<Entrega> entregas2 = empresa.ultimasEntregas(NUM2);
		System.out.println(" entregas2:" + entregas2);

		// el cliente NUM1 tiene que tener la entrega "Descripcion1"
		assertEquals("Descripcion1", entregas1.iterator().next().descripcion());
		// el cliente NUM2 no debe tener ninguna entrega
		assertEquals(0, entregas2.size());

		// asigna realiza la segunda entrega (NUM2, "Descripcion2")
		empresa.asignaSiguienteEntregaPendienteACamion(MAT2, 0);
		empresa.camionRealizaEntrega(MAT2);

		// obtiene las entregas de cada cliente
		entregas1 = empresa.ultimasEntregas(NUM1);
		System.out.println(" entregas1:" + entregas1);
		entregas2 = empresa.ultimasEntregas(NUM2);
		System.out.println(" entregas2:" + entregas2);

		// el cliente NUM1 tiene que tener la entrega "Descripcion1"
		assertEquals("Descripcion1", entregas1.iterator().next().descripcion());

		// el cliente NUM2 tiene que tener la entrega "Descripcion2"
		assertEquals("Descripcion2", entregas2.iterator().next().descripcion());

		// asigna realiza la segunda entrega (NUM1, "Descripcion3")
		empresa.asignaSiguienteEntregaPendienteACamion(MAT3, 0);
		empresa.camionRealizaEntrega(MAT3);

		// obtiene las entregas de cada cliente
		entregas1 = empresa.ultimasEntregas(NUM1);
		System.out.println(" entregas1:" + entregas1);
		entregas2 = empresa.ultimasEntregas(NUM2);
		System.out.println(" entregas2:" + entregas2);

		// el cliente NUM1 tiene que tener la entrega "Descripcion3"
		Iterator<Entrega> iter = entregas1.iterator();
		final String descripcion1 = iter.next().descripcion();
		final String descripcion2 = iter.next().descripcion();
		assertTrue(descripcion1.equals("Descripcion3") || 
				descripcion2.equals("Descripcion3"));

		// el cliente NUM2 tiene que tener la entrega "Descripcion2"
		assertEquals("Descripcion2", entregas2.iterator().next().descripcion());
	}

	@Test
	void ultimasEntregasSimpleTest() {
		System.out.println("ultimasEntregasSimpleTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// registra, asigna y realiza una entrega
		empresa.registraEntregaPendiente(NUM2, "Descripcion1");
		empresa.asignaSiguienteEntregaPendienteACamion(MAT2, 0);
		empresa.camionRealizaEntrega(MAT2);

		// obtiene las entregas del cliente
		Collection<Entrega> entregas = empresa.ultimasEntregas(NUM2);
		System.out.println(" entregas1:" + entregas);

		// el cliente tiene que tener la entrega "Descripcion1"
		assertEquals(1, entregas.size());
		assertEquals("Descripcion1", entregas.iterator().next().descripcion());	

		// registra, asigna y realiza otra entrega para el mismo cliente y el mismo camion
		empresa.registraEntregaPendiente(NUM2, "Descripcion2");
		empresa.asignaSiguienteEntregaPendienteACamion(MAT2, 0);
		empresa.camionRealizaEntrega(MAT2);

		// el cliente tiene que tener la entrega "Descripcion2"
		assertEquals(1, entregas.size());
		assertEquals("Descripcion2", entregas.iterator().next().descripcion());
	}

	@Test
	void ultimasEntregasTest() {
		System.out.println("ultimasEntregasTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// registra, asigna y realiza entregas
		empresa.registraEntregaPendiente(NUM1, "Descripcion1");
		empresa.registraEntregaPendiente(NUM2, "Descripcion2");
		empresa.registraEntregaPendiente(NUM1, "Descripcion3");
		empresa.registraEntregaPendiente(NUM2, "Descripcion4");
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 0); // (NUM1, "Descripcion1")
		empresa.asignaSiguienteEntregaPendienteACamion(MAT2, 0); // (NUM2, "Descripcion2")
		empresa.asignaSiguienteEntregaPendienteACamion(MAT3, 0); // (NUM1, "Descripcion3")
		empresa.asignaSiguienteEntregaPendienteACamion(MAT2, 1); // (NUM2, "Descripcion4")
		empresa.camionRealizaEntrega(MAT2); // (NUM2, "Descripcion2")
		empresa.camionRealizaEntrega(MAT3); // (NUM1, "Descripcion3")

		// obtiene las entregas de cada cliente
		Collection<Entrega> entregas1 = empresa.ultimasEntregas(NUM1);
		System.out.println(" entregas1:" + entregas1);
		Collection<Entrega> entregas2 = empresa.ultimasEntregas(NUM2);
		System.out.println(" entregas2:" + entregas2);

		// el cliente NUM1 tiene que tener la entrega "Descripcion3"
		assertEquals("Descripcion3", entregas1.iterator().next().descripcion());

		// el cliente NUM2 tiene que tener la entrega "Descripcion2"
		assertEquals("Descripcion2", entregas2.iterator().next().descripcion());

		// realiza las dos entregas que faltan
		empresa.camionRealizaEntrega(MAT1); // (NUM1, "Descripcion1")
		empresa.camionRealizaEntrega(MAT2); // (NUM2, "Descripcion4")

		// obtiene las entregas de cada cliente
		entregas1 = empresa.ultimasEntregas(NUM1);
		System.out.println(" entregas1:" + entregas1);
		entregas2 = empresa.ultimasEntregas(NUM2);
		System.out.println(" entregas2:" + entregas2);

		// el cliente NUM1 tiene que tener las entregas "Descripcion1" y "Descripcion3"
		Iterator<Entrega> iter = entregas1.iterator();
		final String descripcion1 = iter.next().descripcion();
		final String descripcion2 = iter.next().descripcion();
		assertTrue(descripcion1.equals("Descripcion1") || 
				descripcion2.equals("Descripcion1"));
		assertTrue(descripcion1.equals("Descripcion3") || 
				descripcion2.equals("Descripcion3"));

		// el cliente NUM2 tiene que tener la entrega "Descripcion4"
		assertEquals("Descripcion4", entregas2.iterator().next().descripcion());
	}

	@Test
	void ultimasEntregasOrdenSimpleTest() {
		System.out.println("ultimasEntregasOrdenSimpleTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// registra, asigna y realiza entregas para el mismo cliente y distintos camiones
		empresa.registraEntregaPendiente(NUM2, "D1Mat2");
		empresa.registraEntregaPendiente(NUM2, "D2Mat3");
		empresa.registraEntregaPendiente(NUM2, "D3Mat1");
		empresa.asignaSiguienteEntregaPendienteACamion(MAT2, 0); // (NUM2, "D1Mat2")
		empresa.asignaSiguienteEntregaPendienteACamion(MAT3, 0); // (NUM2, "D2Mat3")
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 0); // (NUM2, "D3Mat1")
		empresa.camionRealizaEntrega(MAT3); // (NUM2, "D2Mat3")
		empresa.camionRealizaEntrega(MAT1); // (NUM2, "D3Mat1")
		empresa.camionRealizaEntrega(MAT2); // (NUM2, "D1Mat2")

		// obtiene las entregas del cliente y comprueba que estan el orden de las matriculas
		Collection<Entrega> entregas = empresa.ultimasEntregas(NUM2);
		System.out.println(" entregas:" + entregas);
		Iterator<Entrega> iter = entregas.iterator();
		assertEquals("D3Mat1", iter.next().descripcion());
		assertEquals("D1Mat2", iter.next().descripcion());
		assertEquals("D2Mat3", iter.next().descripcion());
	}

	@Test
	void ultimasEntregasOrdenTest() {
		System.out.println("ultimasEntregasOrdenTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// registra, asigna y realiza entregas dos clientes y distintos camiones
		empresa.registraEntregaPendiente(NUM2, "D1Cli2Mat2");
		empresa.registraEntregaPendiente(NUM1, "D2Cli1Mat1");
		empresa.registraEntregaPendiente(NUM1, "D3Cli1Mat3");
		empresa.registraEntregaPendiente(NUM2, "D4Cli2Mat3");
		empresa.registraEntregaPendiente(NUM2, "D5Cli2Mat1");
		empresa.registraEntregaPendiente(NUM1, "D6Cli1Mat2");
		empresa.asignaSiguienteEntregaPendienteACamion(MAT2, 0); // (NUM2, "D1Cli2Mat2")
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 0); // (NUM1, "D2Cli1Mat1")
		empresa.asignaSiguienteEntregaPendienteACamion(MAT3, 0); // (NUM1, "D3Cli1Mat3")
		empresa.asignaSiguienteEntregaPendienteACamion(MAT3, 1); // (NUM2, "D4Cli2Mat3")
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 0); // (NUM2, "D5Cli2Mat1")
		empresa.asignaSiguienteEntregaPendienteACamion(MAT2, 1); // (NUM1, "D6Cli1Mat2")
		empresa.camionRealizaEntrega(MAT3);
		empresa.camionRealizaEntrega(MAT1);
		empresa.camionRealizaEntrega(MAT2);
		empresa.camionRealizaEntrega(MAT2);
		empresa.camionRealizaEntrega(MAT3);
		empresa.camionRealizaEntrega(MAT1);

		// comprueba que las entregas de los clientes estan en el orden de las matriculas
		Collection<Entrega> entregas1 = empresa.ultimasEntregas(NUM1);
		Collection<Entrega> entregas2 = empresa.ultimasEntregas(NUM2);
		System.out.println(" entregas1:" + entregas1);
		System.out.println(" entregas2:" + entregas2);
		Iterator<Entrega> iter1 = entregas1.iterator();
		assertEquals("D2Cli1Mat1", iter1.next().descripcion());
		assertEquals("D6Cli1Mat2", iter1.next().descripcion());
		assertEquals("D3Cli1Mat3", iter1.next().descripcion());
		Iterator<Entrega> iter2 = entregas2.iterator();
		assertEquals("D5Cli2Mat1", iter2.next().descripcion());
		assertEquals("D1Cli2Mat2", iter2.next().descripcion());
		assertEquals("D4Cli2Mat3", iter2.next().descripcion());
	}

	@Test
	void eliminaEntregasClienteAsignadasSimpleTest() {
		System.out.println("eliminaEntregasClienteAsignadasSimpleTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// registra y asigna entregas al mismo camion y distintos clienes
		empresa.registraEntregaPendiente(NUM2, "D1Cli2");
		empresa.registraEntregaPendiente(NUM1, "D2Cli1");
		empresa.registraEntregaPendiente(NUM1, "D3Cli1");
		empresa.registraEntregaPendiente(NUM2, "D4Cli2");
		empresa.registraEntregaPendiente(NUM1, "D5Cli1");
		empresa.registraEntregaPendiente(NUM2, "D6Cli2");
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 0);
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 1);
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 2);
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 3);
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 4);
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 5);

		// elimina las entregas del cliente NUM2
		empresa.eliminaEntregasClienteAsignadas(NUM2);

		// realiza las entregas y comprueba que son las esperadas
		empresa.camionRealizaEntrega(MAT1);
		Collection<Entrega> entregas = empresa.ultimasEntregas(NUM1);
		System.out.println(" entregas:" + entregas);
		assertEquals("D2Cli1", entregas.iterator().next().descripcion());

		empresa.camionRealizaEntrega(MAT1);
		entregas = empresa.ultimasEntregas(NUM1);
		System.out.println(" entregas:" + entregas);
		assertEquals("D3Cli1", entregas.iterator().next().descripcion());

		empresa.camionRealizaEntrega(MAT1);
		entregas = empresa.ultimasEntregas(NUM1);
		System.out.println(" entregas:" + entregas);
		assertEquals("D5Cli1", entregas.iterator().next().descripcion());
	}

	@Test
	void eliminaEntregasClienteAsignadasTest() {
		System.out.println("eliminaEntregasClienteAsignadasTest");
		EmpresaDistribucion empresa = new EmpresaDistribucion();

		// registra y asigna entregas al distintos camiones y clienes
		empresa.registraEntregaPendiente(NUM2, "D1Cli2Mat2");
		empresa.registraEntregaPendiente(NUM1, "D2Cli1Mat1");
		empresa.registraEntregaPendiente(NUM1, "D3Cli1Mat3");
		empresa.registraEntregaPendiente(NUM2, "D4Cli2Mat1");
		empresa.registraEntregaPendiente(NUM1, "D5Cli1Mat2");
		empresa.registraEntregaPendiente(NUM2, "D6Cli2Mat2");
		empresa.asignaSiguienteEntregaPendienteACamion(MAT2, 0); // "D1Cli2Mat2"
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 0); // "D2Cli1Mat1"
		empresa.asignaSiguienteEntregaPendienteACamion(MAT3, 0); // "D3Cli1Mat3"
		empresa.asignaSiguienteEntregaPendienteACamion(MAT1, 1); // "D4Cli2Mat1"
		empresa.asignaSiguienteEntregaPendienteACamion(MAT2, 1); // "D5Cli1Mat2"
		empresa.asignaSiguienteEntregaPendienteACamion(MAT2, 2); // "D6Cli2Mat2"

		// elimina las entregas del cliente NUM1
		empresa.eliminaEntregasClienteAsignadas(NUM1);

		// realiza la primera entrega de MAT1 y comprueba que es la esperada
		empresa.camionRealizaEntrega(MAT1); // "D4Cli2Mat1"
		Collection<Entrega> entregas2 = empresa.ultimasEntregas(NUM2);
		System.out.println(" entregas2:" + entregas2);
		assertEquals(1, entregas2.size());
		assertEquals("D4Cli2Mat1", entregas2.iterator().next().descripcion());

		// realiza la primera entrega de MAT2 y comprueba que es la esperada
		empresa.camionRealizaEntrega(MAT2); // "D1Cli2Mat2"
		entregas2 = empresa.ultimasEntregas(NUM2);
		Iterator<Entrega> iter = entregas2.iterator();
		String descripcion1 = iter.next().descripcion();
		String descripcion2 = iter.next().descripcion();
		assertTrue(descripcion1.equals("D4Cli2Mat1") || 
				descripcion2.equals("D4Cli2Mat1"));
		assertTrue(descripcion1.equals("D1Cli2Mat2") || 
				descripcion2.equals("D1Cli2Mat2"));

		// comprueba que MAT3 se ha quedado con su ruta vacia
		try {
			empresa.camionRealizaEntrega(MAT3);
			fail("Deberia haber lanzado la excepcion");
		} catch (Camion.RutaEntregasVacia e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// comprueba que MAT1 se ha quedado con su ruta vacia
		try {
			empresa.camionRealizaEntrega(MAT1);
			fail("Deberia haber lanzado la excepcion");
		} catch (Camion.RutaEntregasVacia e) {
			// el comportamiento esperado es que lance la excepcion
		}

		// realiza la primera entrega de MAT2 y comprueba que es la esperada
		empresa.camionRealizaEntrega(MAT2); // "D6Cli2Mat2"
		entregas2 = empresa.ultimasEntregas(NUM2);
		iter = entregas2.iterator();
		descripcion1 = iter.next().descripcion();
		descripcion2 = iter.next().descripcion();
		assertTrue(descripcion1.equals("D4Cli2Mat1") || 
				descripcion2.equals("D4Cli2Mat1"));
		assertTrue(descripcion1.equals("D6Cli2Mat2") || 
				descripcion2.equals("D6Cli2Mat2"));

		// comprueba que MAT2 se ha quedado con su ruta vacia
		try {
			empresa.camionRealizaEntrega(MAT2);
			fail("Deberia haber lanzado la excepcion");
		} catch (Camion.RutaEntregasVacia e) {
			// el comportamiento esperado es que lance la excepcion
		}
	}

}
