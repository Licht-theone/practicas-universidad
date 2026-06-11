package pract11.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import pract11.modelo.EmpresaAlquiler;
import pract11.modelo.Oficina;
import pract11.modelo.Vehiculo;
import pract11.modelo.Vehiculo.TipoCombustible;

/**
 * Test de la clase EmpresaAlquiler.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2021
 */
class EmpresaAlquilerTest {

	@Test
	void anhadeOficinaRepetidaTest() {
		System.out.println("anhadeOficinaRepetidatest");
		EmpresaAlquiler empresa = new EmpresaAlquiler();

		empresa.anhadeOficina(new Oficina("Una Ciudad"));

		// comprueba que no se pueden anhadir dos oficinas con el mismo nombre
		try {
			empresa.anhadeOficina(new Oficina("Una Ciudad"));
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaAlquiler.OficinaYaExistente e) {
			// El comportamiento correcto es que se lance la excepcion
		}
	}

	@Test
	void anhadeVehiculoRepetidoTest() {
		System.out.println("anhadeVehiculoRepetidoTest");
		EmpresaAlquiler empresa = new EmpresaAlquiler();

		empresa.anhadeOficina(new Oficina("Una Ciudad"));

		empresa.anhadeVehiculo(new Vehiculo("1A", TipoCombustible.DIESEL, 5), "Una Ciudad");

		// comprueba que no se pueden anhadir dos vehiculos con la misma matricula
		try {
			empresa.anhadeVehiculo(new Vehiculo("1A", TipoCombustible.DIESEL, 5), "Una Ciudad");
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaAlquiler.VehiculoYaExistente e) {
			// El comportamiento correcto es que se lance la excepcion
		}
	}

	@Test
	void anhadeVehiculoRepetido2Test() {
		System.out.println("anhadeVehiculoRepetido2Test");
		EmpresaAlquiler empresa = new EmpresaAlquiler();

		empresa.anhadeOficina(new Oficina("Una Ciudad"));		
		empresa.anhadeOficina(new Oficina("Otra Ciudad"));

		empresa.anhadeVehiculo(new Vehiculo("1A", TipoCombustible.DIESEL, 5), "Una Ciudad");

		// comprueba que no se pueden anhadir dos vehiculos con la misma matricula incluso si
		// es a una oficina diferente
		try {
			empresa.anhadeVehiculo(new Vehiculo("1A", TipoCombustible.DIESEL, 5), "Otra Ciudad");
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaAlquiler.VehiculoYaExistente e) {
			// El comportamiento correcto es que se lance la excepcion
		}
	}

	@Test
	void anhadeVehiculoRepetidoErrorOficinaTest() {
		System.out.println("anhadeVehiculoRepetidoErrorOficinaTest");
		EmpresaAlquiler empresa = new EmpresaAlquiler();

		// comprueba que no se puede anhadir un vehiculo a una oficina inexistente
		try {
			empresa.anhadeVehiculo(new Vehiculo("1A", TipoCombustible.DIESEL, 5), "Una Ciudad");
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaAlquiler.NombreOficinaIncorrecto e) {
			// El comportamiento correcto es que se lance la excepcion
		}	

		empresa.anhadeOficina(new Oficina("Una Ciudad"));
		try {
			empresa.anhadeVehiculo(new Vehiculo("1A", TipoCombustible.DIESEL, 5), "Otra Ciudad");
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaAlquiler.NombreOficinaIncorrecto e) {
			// El comportamiento correcto es que se lance la excepcion
		}	
	}

	@Test
	void devuelveVehiculoIncorrectoTest() {
		System.out.println("devuelveVehiculoIncorrectoTest");
		EmpresaAlquiler empresa = new EmpresaAlquiler();

		empresa.anhadeOficina(new Oficina("Una Ciudad"));

		// comprueba que no se puede devolver un vehiculo inexistente
		try {
			empresa.devuelveVehiculo("Una Ciudad", "Matricula No existente");
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaAlquiler.MatriculaVehiculoIncorrecta e) {
			// El comportamiento correcto es que se lance la excepcion
		}
	}

	@Test
	void devuelveVehiculoErrorOficinaTest() {
		System.out.println("devuelveVehiculoErrorOficinaTest");
		EmpresaAlquiler empresa = new EmpresaAlquiler();

		empresa.anhadeOficina(new Oficina("Una Ciudad"));

		empresa.anhadeVehiculo(new Vehiculo("1A", TipoCombustible.DIESEL, 5), "Una Ciudad");
		empresa.alquilaVehiculo("Una Ciudad", 5, TipoCombustible.DIESEL);

		// comprueba que no se puede devolver un vehiculo a una oficina inexistente
		try {
			empresa.devuelveVehiculo("OTRA Ciudad", "1A");
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaAlquiler.NombreOficinaIncorrecto e) {
			// El comportamiento correcto es que se lance la excepcion
		}	
	}

	@Test
	void devuelveVehiculoNoAlquiladoTest() {
		System.out.println("devuelveVehiculoNoAlquiladoTest");
		EmpresaAlquiler empresa = new EmpresaAlquiler();
		Vehiculo vehiculoTest = new Vehiculo("1H", TipoCombustible.HIBRIDO, 5);
		Oficina oficina1 = new Oficina("Ciudad1");
		Oficina oficina2 = new Oficina("Ciudad2");	

		// anhade oficinas y vehiculo
		empresa.anhadeOficina(oficina1);	
		empresa.anhadeOficina(oficina2);
		empresa.anhadeVehiculo(vehiculoTest, "Ciudad2");

		// comprueba que no se puede devolver el vehiculo a ninguna oficina
		try {
			empresa.devuelveVehiculo("Ciudad1", "1H");
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaAlquiler.VehiculoNoAlquilado e) {
			// El comportamiento correcto es que se lance la excepcion
		}	
		try {
			empresa.devuelveVehiculo("Ciudad2", "1H");
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaAlquiler.VehiculoNoAlquilado e) {
			// El comportamiento correcto es que se lance la excepcion
		}	
	}

	@Test
	void buscaVehiculoSimpleTest() {
		System.out.println("buscaVehiculoSimpleTest");
		EmpresaAlquiler empresa = new EmpresaAlquiler();

		empresa.anhadeOficina(new Oficina("Una Ciudad"));

		empresa.anhadeVehiculo(new Vehiculo("1A", TipoCombustible.DIESEL, 5), "Una Ciudad");

		// comprueba que encuentra un vehiculo existente
		assertNotNull(empresa.buscaVehiculo("1A"));	

		// comprueba que NO encuentra un vehiculo no existente
		assertNull(empresa.buscaVehiculo("Matricula no existente"));
	}

	@Test
	void oficinasTest() {
		System.out.println("oficinasTest");
		EmpresaAlquiler empresa = new EmpresaAlquiler();
		ArrayList<Oficina> lstOficinas = new ArrayList<>(
				Arrays.asList(new Oficina("A"), new Oficina("C"),
						new Oficina("L"), new Oficina("X"), new Oficina("Z")));

		// anhade las oficinas desordenadas
		empresa.anhadeOficina(lstOficinas.get(3));
		empresa.anhadeOficina(lstOficinas.get(0));
		empresa.anhadeOficina(lstOficinas.get(4));
		empresa.anhadeOficina(lstOficinas.get(2));
		empresa.anhadeOficina(lstOficinas.get(1));

		Collection<Oficina> oficinas = empresa.oficinas();
		System.out.println(oficinas);

		// comprueba que las oficinas son las esperadas y estan ordenadas
		assertEquals(lstOficinas, new ArrayList<Oficina>(oficinas));
	}

	@Test
	void alquilaVehiculoNoCaracteristicasTest() {
		System.out.println("alquilaVehiculoNoCaracteristicasTest");
		EmpresaAlquiler empresa = new EmpresaAlquiler();

		empresa.anhadeOficina(new Oficina("Ciudad"));

		// comprueba que no encuentra vehiculo en oficina vacia
		assertNull(empresa.alquilaVehiculo("Ciudad", 5, TipoCombustible.DIESEL));

		// anhade vehiculos		
		empresa.anhadeVehiculo(new Vehiculo("1A", TipoCombustible.DIESEL, 6), "Ciudad");		
		empresa.anhadeVehiculo(new Vehiculo("1B", TipoCombustible.HIBRIDO, 5), "Ciudad");

		// compruea que no encuentra el vehiculo
		assertNull(empresa.alquilaVehiculo("Ciudad", 5, TipoCombustible.DIESEL));
		assertNull(empresa.alquilaVehiculo("Ciudad", 6, TipoCombustible.HIBRIDO));
	}

	@Test
	void alquilaVehiculoTest() {
		System.out.println("alquilaVehiculoTest");
		EmpresaAlquiler empresa = new EmpresaAlquiler();
		Vehiculo vehiculoBuscado = new Vehiculo("1E", TipoCombustible.ELECTRICO, 4);

		empresa.anhadeOficina(new Oficina("Ciudad"));

		// anhade vehiculos		
		empresa.anhadeVehiculo(new Vehiculo("1D", TipoCombustible.DIESEL, 6), "Ciudad");		
		empresa.anhadeVehiculo(new Vehiculo("1H", TipoCombustible.HIBRIDO, 5), "Ciudad");		
		empresa.anhadeVehiculo(vehiculoBuscado, "Ciudad");

		// compruea que encuentra el vehiculo
		Vehiculo vehiculo = empresa.alquilaVehiculo("Ciudad", 4, TipoCombustible.ELECTRICO);
		System.out.println(vehiculo);
		assertEquals(vehiculoBuscado, vehiculo);
	}

	@Test
	void alquilaDevuelveVehiculoTest() {
		System.out.println("alquilaDevuelveTest");
		EmpresaAlquiler empresa = new EmpresaAlquiler();
		Vehiculo vehiculoTest = new Vehiculo("1H", TipoCombustible.HIBRIDO, 5);
		Oficina oficina1 = new Oficina("Ciudad1");
		Oficina oficina2 = new Oficina("Ciudad2");

		// anhade oficinas y vehiculo
		empresa.anhadeOficina(oficina1);	
		empresa.anhadeOficina(oficina2);		
		empresa.anhadeVehiculo(vehiculoTest, "Ciudad1");

		// comprueba que el vehiculo esta libre
		Vehiculo vehiculo = empresa.buscaVehiculo("1H");
		assertEquals(vehiculoTest, vehiculo);
		assertEquals(false, vehiculo.estaAlquilado());
		assertEquals(oficina1, vehiculo.oficina());

		// alquila el vehiculo
		vehiculo = empresa.alquilaVehiculo("Ciudad1", 5, TipoCombustible.HIBRIDO);
		assertEquals(vehiculoTest, vehiculo);
		assertEquals(true, vehiculo.estaAlquilado());

		// devuelve vehiculo
		empresa.devuelveVehiculo("Ciudad2", "1H");
		assertEquals(vehiculoTest, vehiculo);
		assertEquals(false, vehiculo.estaAlquilado());
		assertEquals(oficina2, vehiculo.oficina());
	}

	@Test
	void numVehiculosDisponiblesTest() {
		System.out.println("numVehiculosDisponiblesTest");
		EmpresaAlquiler empresa = new EmpresaAlquiler();
		Vehiculo vehiculoTest = new Vehiculo("1H", TipoCombustible.HIBRIDO, 5);
		Oficina oficina1 = new Oficina("Ciudad1");
		Oficina oficina2 = new Oficina("Ciudad2");	

		// anhade oficinas
		empresa.anhadeOficina(oficina1);	
		empresa.anhadeOficina(oficina2);

		// comprueba que las oficinas tienen 0 vehiculos		
		Collection<Oficina> oficinas = empresa.oficinas();
		System.out.println(oficinas);
		assertEquals(2, oficinas.size());
		for (Oficina o: oficinas) {
			assertEquals(0, o.numVehiculosDisponibles());
		}

		// anhade un vehiculo
		empresa.anhadeVehiculo(vehiculoTest, "Ciudad2");

		// comprueba que una oficina tiene un vehiculo y la otra 0		
		oficinas = empresa.oficinas();
		System.out.println(oficinas);
		assertEquals(2, oficinas.size());
		for (Oficina o: oficinas) {
			if (o.nombre().equals("Ciudad1")) {
				assertEquals(0, o.numVehiculosDisponibles());
			} else if (o.nombre().equals("Ciudad2")) {
				assertEquals(1, o.numVehiculosDisponibles());
			} else {
				fail("nombre de oficina incorrecto:" + o.nombre());
			}
		}

		// alquila el vehiculo y comprueba que las oficinas vuelven a tener 0 vehiculos
		empresa.alquilaVehiculo("Ciudad2", 5, TipoCombustible.HIBRIDO);		
		oficinas = empresa.oficinas();
		System.out.println(oficinas);
		assertEquals(2, oficinas.size());
		for (Oficina o: oficinas) {
			assertEquals(0, o.numVehiculosDisponibles());
		}

		// devuelve el vehiculo en la otra oficina
		empresa.devuelveVehiculo("Ciudad1", "1H");
		oficinas = empresa.oficinas();
		System.out.println(oficinas);
		assertEquals(2, oficinas.size());
		for (Oficina o: oficinas) {
			if (o.nombre().equals("Ciudad1")) {
				assertEquals(1, o.numVehiculosDisponibles());
			} else if (o.nombre().equals("Ciudad2")) {
				assertEquals(0, o.numVehiculosDisponibles());
			} else {
				fail("nombre de oficina incorrecto:" + o.nombre());
			}
		}
	}

}
