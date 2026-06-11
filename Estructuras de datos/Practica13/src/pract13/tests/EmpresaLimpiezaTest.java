package pract13.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import pract13.modelo.*;

/**
 * Prueba la clase EmpresaLimpieza.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2021
 */
class EmpresaLimpiezaTest {

	private String[] idEquipos = {"E01", "E02", "E03"};

	@Test
	void constructorTest() {
		System.out.println("==constructorTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();
		EquipoLimpieza equipo;

		for (String idEquipo: idEquipos) {
			equipo = empresa.buscaEquipo(idEquipo);
			assertNotEquals(null, equipo);
			assertEquals(idEquipo, equipo.id());
		}
	}

	@Test
	void anhadeClienteErrorNombreTest() {
		System.out.println("==anhadeClienteErrorNombreTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();

		empresa.anhadeCliente(new Cliente("Pepa", "D1"));		
		empresa.anhadeCliente(new Cliente("Lolo", "D2"));

		try {		
			empresa.anhadeCliente(new Cliente("Lolo", "D3"));
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaLimpieza.NombreClienteYaExistente e) {
			// el comportamiento correcto es que se lance la excepcion
		}

		try {		
			empresa.anhadeCliente(new Cliente("Pepa", "D4"));
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaLimpieza.NombreClienteYaExistente e) {
			// el comportamiento correcto es que se lance la excepcion
		}
	}

	@Test
	void asignaServicioLimpiezaErrorNombreTest() {
		System.out.println("==asignaServicioLimpiezaErrorNombreTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();

		empresa.anhadeCliente(new Cliente("Pepa", "D1"));		
		empresa.anhadeCliente(new Cliente("Lolo", "D2"));

		empresa.asignaServicioLimpieza("Pepa");		
		empresa.asignaServicioLimpieza("Lolo");

		try {		
			empresa.asignaServicioLimpieza("X");
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaLimpieza.NombreClienteNoValido e) {
			// el comportamiento correcto es que se lance la excepcion
		}
	}

	@Test
	void equipoFinalizaServicioErrorIdTest() {
		System.out.println("==equipoFinalizaServicioErrorIdTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();

		empresa.anhadeCliente(new Cliente("Pepa", "D1"));		
		empresa.anhadeCliente(new Cliente("Lolo", "D2"));

		EquipoLimpieza equipo1 = empresa.asignaServicioLimpieza("Pepa");		
		EquipoLimpieza equipo2 = empresa.asignaServicioLimpieza("Lolo");

		empresa.equipoFinalizaServicio(equipo1.id(), 4);		
		empresa.equipoFinalizaServicio(equipo2.id(), 3);

		try {		
			empresa.equipoFinalizaServicio("X", 4);	
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaLimpieza.IdEquipoNoValido e) {
			// el comportamiento correcto es que se lance la excepcion
		}
	}

	@Test
	void equipoFinalizaServicioErrorSinServicioTest() {
		System.out.println("==equipoFinalizaServicioErrorSinServicioTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();

		try {		
			empresa.equipoFinalizaServicio(idEquipos[0], 4);	
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaLimpieza.EquipoSinServicioAsignado e) {
			// el comportamiento correcto es que se lance la excepcion
		}

		empresa.anhadeCliente(new Cliente("Pepa", "D1"));

		EquipoLimpieza equipo1 = empresa.asignaServicioLimpieza("Pepa");
		System.out.println("Equipo antes de finalizar:" + equipo1);
		empresa.equipoFinalizaServicio(equipo1.id(), 4);
		System.out.println("Equipo despues de finalizar:" + equipo1);

		try {		
			empresa.equipoFinalizaServicio(equipo1.id(), 4);	
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaLimpieza.EquipoSinServicioAsignado e) {
			// el comportamiento correcto es que se lance la excepcion
		}
	}

	@Test
	void buscaEquipoTest() {
		System.out.println("==buscaEquipoTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();		
		EquipoLimpieza equipo;

		for (String id: idEquipos) {
			equipo = empresa.buscaEquipo(id);
			System.out.println("buscaEquipo(" + id + ")->" + equipo);
			assertEquals(id, equipo.id());
		}

		equipo = empresa.buscaEquipo("XXX");
		assertNull(equipo);
	}

	@Test
	void clienteEnPosRankingErrorPosTest() {
		System.out.println("==clienteEnPosRankingErrorPosTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();

		empresa.anhadeCliente(new Cliente("Pepa", "D1"));		
		empresa.anhadeCliente(new Cliente("Lolo", "D2"));		
		empresa.anhadeCliente(new Cliente("Otro", "D3"));

		try {		
			empresa.clienteEnPosRanking(-1);	
			fail("Las posiciones empiezan en 1");
		} catch (EmpresaLimpieza.PosicionRankingIncorrecta e) {
			// el comportamiento correcto es que se lance la excepcion
		}

		try {		
			empresa.clienteEnPosRanking(0);	
			fail("Las posiciones empiezan en 1");
		} catch (EmpresaLimpieza.PosicionRankingIncorrecta e) {
			// el comportamiento correcto es que se lance la excepcion
		}

		try {		
			empresa.clienteEnPosRanking(4);	
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaLimpieza.PosicionRankingIncorrecta e) {
			// el comportamiento correcto es que se lance la excepcion
		}

		// prueba con posiciones correctas
		empresa.clienteEnPosRanking(1);	
		empresa.clienteEnPosRanking(2);		
		empresa.clienteEnPosRanking(3);	
	}

	@Test
	void posClienteEnRankingErrorNombreTest() {
		System.out.println("==posClienteEnRankingErrorNombreTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();

		empresa.anhadeCliente(new Cliente("Pepa", "D1"));		
		empresa.anhadeCliente(new Cliente("Lolo", "D2"));

		// prueba con clientes que existen
		empresa.posClienteEnRanking("Pepa");		
		empresa.posClienteEnRanking("Lolo");

		// prueba con un nombre de cliente que no existe
		try {		
			empresa.posClienteEnRanking("Y");
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaLimpieza.NombreClienteNoValido e) {
			// el comportamiento correcto es que se lance la excepcion
		}
	}

	@Test
	void acumulaHorasEquipoTest() {
		System.out.println("==acumulaHorasEquipoTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();
		Map<String, Integer> horasEquipos = new HashMap<>();
		horasEquipos.put(idEquipos[0], 0);
		horasEquipos.put(idEquipos[1], 0);
		horasEquipos.put(idEquipos[2], 0);

		empresa.anhadeCliente(new Cliente("Pepa", "D1"));		
		empresa.anhadeCliente(new Cliente("Lolo", "D2"));

		// comprueba que los equipos empiezan con 0 horas
		for (String idEquipo: idEquipos) {
			assertEquals(0, empresa.buscaEquipo(idEquipo).horasTrabajadas());
		}

		// anhade horas y comprueba
		for (int i = 1; i <= 5; i++) {
			EquipoLimpieza equipo = empresa.asignaServicioLimpieza("Pepa");
			System.out.println("Asigna " +  i + "h a equipo " + equipo);
			empresa.equipoFinalizaServicio(equipo.id(), i);
			System.out.println("Desp finalizar: " + equipo);

			horasEquipos.put(equipo.id(), horasEquipos.get(equipo.id()) + i);
			assertEquals(horasEquipos.get(equipo.id()), equipo.horasTrabajadas());
		}
	}

	@Test
	void ordenAsignaEquiposSimpleTest() {
		System.out.println("==ordenAsignaEquiposTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();
		empresa.anhadeCliente(new Cliente("Pepa", "D1"));		
		empresa.anhadeCliente(new Cliente("Lolo", "D2"));

		// asigna y finaliza un trabajo
		EquipoLimpieza equipo1 = empresa.asignaServicioLimpieza("Pepa");
		System.out.println("Equipo asignado:" + equipo1);
		empresa.equipoFinalizaServicio(equipo1.id(), 3);
		muestraEstadoEquipos(empresa);

		// comprueba que un nuevo trabajo se asigna a un equipo distinto
		EquipoLimpieza equipo2 = empresa.asignaServicioLimpieza("Pepa");
		System.out.println("Equipo asignado:" + equipo2);
		assertNotEquals(equipo1, equipo2);
		empresa.equipoFinalizaServicio(equipo2.id(), 2);
		muestraEstadoEquipos(empresa);

		// comprueba que un nuevo trabajo se asigna a un equipo distinto
		EquipoLimpieza equipo3 = empresa.asignaServicioLimpieza("Lolo");
		System.out.println("Equipo asignado:" + equipo3);
		assertNotEquals(equipo1, equipo3);
		assertNotEquals(equipo2, equipo3);
		empresa.equipoFinalizaServicio(equipo3.id(), 4);
		muestraEstadoEquipos(empresa);

		// comprueba que asigna al equipo con menos horas
		EquipoLimpieza equipo4 = empresa.asignaServicioLimpieza("Lolo");
		System.out.println("Equipo asignado:" + equipo4);
		assertEquals(equipo2, equipo4);	
	}


	@Test
	void ordenAsignaEquiposTest() {
		System.out.println("==ordenAsignaEquiposTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();
		EquipoLimpieza equipo;

		empresa.anhadeCliente(new Cliente("Pepa", "D1"));		
		empresa.anhadeCliente(new Cliente("Lolo", "D2"));

		// asigna los tres primeros trabajos
		EquipoLimpieza equipo1 = empresa.asignaServicioLimpieza("Pepa");
		empresa.equipoFinalizaServicio(equipo1.id(), 1);
		EquipoLimpieza equipo2 = empresa.asignaServicioLimpieza("Pepa");
		empresa.equipoFinalizaServicio(equipo2.id(), 10);
		EquipoLimpieza equipo3 = empresa.asignaServicioLimpieza("Pepa");
		empresa.equipoFinalizaServicio(equipo3.id(), 20);

		muestraEstadoEquipos(empresa);

		// comprueba que los equipos asignados son distintos
		assertNotEquals(equipo1, equipo2);
		assertNotEquals(equipo1, equipo3);

		// prueba que asigna de menos a mas horas trabajadas
		equipo = empresa.asignaServicioLimpieza("Pepa");
		System.out.println("Equipo asignado:" + equipo);
		assertEquals(equipo1, equipo);
		empresa.equipoFinalizaServicio(equipo.id(), 12);

		muestraEstadoEquipos(empresa);

		equipo = empresa.asignaServicioLimpieza("Pepa");
		System.out.println("Equipo asignado:" + equipo);
		assertEquals(equipo2, equipo);
		empresa.equipoFinalizaServicio(equipo.id(), 1);

		muestraEstadoEquipos(empresa);

		equipo = empresa.asignaServicioLimpieza("Pepa");
		System.out.println("Equipo asignado:" + equipo);
		assertEquals(equipo2, equipo);
		empresa.equipoFinalizaServicio(equipo.id(), 11);

		muestraEstadoEquipos(empresa);

		equipo = empresa.asignaServicioLimpieza("Pepa");
		System.out.println("Equipo asignado:" + equipo);
		assertEquals(equipo1, equipo);
		empresa.equipoFinalizaServicio(equipo.id(), 11);

		muestraEstadoEquipos(empresa);

		equipo = empresa.asignaServicioLimpieza("Pepa");
		System.out.println("Equipo asignado:" + equipo);
		assertEquals(equipo3, equipo);
		empresa.equipoFinalizaServicio(equipo.id(), 11);

		muestraEstadoEquipos(empresa);			
	}

	@Test
	void ordenClientesTest() {
		System.out.println("==ordenClientesTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();
		String[] nombresClientes = {"AB", "Z", "AC", "W", "G", "BA"};

		for (String nomCli: nombresClientes) {
			empresa.anhadeCliente(new Cliente(nomCli, "Dir " + nomCli));		
		}

		Collection<Cliente> clientes = empresa.clientes();
		Arrays.sort(nombresClientes);
		System.out.println("Orden esperado:" + Arrays.toString(nombresClientes));
		System.out.println("clientes:" + clientes);
		int i = 0;
		for (Cliente cliente: clientes) {
			assertEquals(nombresClientes[i], cliente.nombre());
			i++;
		}
	}

	@Test
	void rankingClientesTest() {
		System.out.println("==rankingClientesTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();
		EquipoLimpieza equipo;

		Cliente c1 = new Cliente("c1", "D1");
		empresa.anhadeCliente(c1);	
		Cliente c2 = new Cliente("c2", "D2");
		empresa.anhadeCliente(c2);	
		Cliente c3 = new Cliente("c3", "D3");
		empresa.anhadeCliente(c3);	
		Cliente c4 = new Cliente("c4", "D4");
		empresa.anhadeCliente(c4);
		printRankingClientes(empresa, 4);

		// asigna trabajo a c3
		equipo = empresa.asignaServicioLimpieza("c3");
		empresa.equipoFinalizaServicio(equipo.id(), 11);
		System.out.println("Asignado trabajo para:" + c3);
		printRankingClientes(empresa, 4);
		assertEquals(1, empresa.posClienteEnRanking("c3"));
		assertEquals(c3, empresa.clienteEnPosRanking(1));

		// asigna dos trabajos a c2
		equipo = empresa.asignaServicioLimpieza("c2");
		empresa.equipoFinalizaServicio(equipo.id(), 11);
		equipo = empresa.asignaServicioLimpieza("c2");
		empresa.equipoFinalizaServicio(equipo.id(), 11);
		System.out.println("Asignados dos trabajos para:" + c2);
		printRankingClientes(empresa, 4);
		assertEquals(1, empresa.posClienteEnRanking("c2"));
		assertEquals(c2, empresa.clienteEnPosRanking(1));
		assertEquals(2, empresa.posClienteEnRanking("c3"));
		assertEquals(c3, empresa.clienteEnPosRanking(2));

		// asigna tres trabajos a c4
		equipo = empresa.asignaServicioLimpieza("c4");
		empresa.equipoFinalizaServicio(equipo.id(), 11);
		equipo = empresa.asignaServicioLimpieza("c4");
		empresa.equipoFinalizaServicio(equipo.id(), 11);
		equipo = empresa.asignaServicioLimpieza("c4");
		empresa.equipoFinalizaServicio(equipo.id(), 11);
		System.out.println("Asignados tres trabajos para:" + c4);
		printRankingClientes(empresa, 4);
		assertEquals(1, empresa.posClienteEnRanking("c4"));
		assertEquals(c4, empresa.clienteEnPosRanking(1));
		assertEquals(2, empresa.posClienteEnRanking("c2"));
		assertEquals(c2, empresa.clienteEnPosRanking(2));
		assertEquals(3, empresa.posClienteEnRanking("c3"));
		assertEquals(c3, empresa.clienteEnPosRanking(3));
		assertEquals(4, empresa.posClienteEnRanking("c1"));
		assertEquals(c1, empresa.clienteEnPosRanking(4));

		// asigna tres trabajos a c2
		equipo = empresa.asignaServicioLimpieza("c2");
		empresa.equipoFinalizaServicio(equipo.id(), 11);
		equipo = empresa.asignaServicioLimpieza("c2");
		empresa.equipoFinalizaServicio(equipo.id(), 11);
		equipo = empresa.asignaServicioLimpieza("c2");
		empresa.equipoFinalizaServicio(equipo.id(), 11);
		System.out.println("Asignados tres trabajos para:" + c2);
		printRankingClientes(empresa, 4);
		assertEquals(1, empresa.posClienteEnRanking("c2"));
		assertEquals(c2, empresa.clienteEnPosRanking(1));
		assertEquals(2, empresa.posClienteEnRanking("c4"));
		assertEquals(c4, empresa.clienteEnPosRanking(2));
		assertEquals(3, empresa.posClienteEnRanking("c3"));
		assertEquals(c3, empresa.clienteEnPosRanking(3));
		assertEquals(4, empresa.posClienteEnRanking("c1"));
		assertEquals(c1, empresa.clienteEnPosRanking(4));

	}

	@Test
	void clientesEquipoNoRepiteTest() {
		System.out.println("==clientesEquipoNoRepiteTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();

		// crea cliente
		Cliente c1 = new Cliente("c1", "D1");
		empresa.anhadeCliente(c1);

		// asigna cliente una vez mas que equipos
		EquipoLimpieza equipo1 = null;
		for (int i = 0; i < idEquipos.length + 1; i++) {
			equipo1 = empresa.asignaServicioLimpieza("c1");
			empresa.equipoFinalizaServicio(equipo1.id(), 1);
		}
		// al finalizar el lazo, equipo1 apunta al equipo que le ha sido
		// asignado el cliente 2 veces
		
		// comprueba que todos los equipos solo tienen al c1
		for (String idEquipo: idEquipos) {
			EquipoLimpieza equipo = empresa.buscaEquipo(idEquipo);
			System.out.println(equipo + " clientes:" + equipo.clientes());
			assertEquals(1, equipo.clientes().size());
			assertTrue(equipo.clientes().contains(c1));
			if (equipo == equipo1) {
				assertEquals(2, equipo.horasTrabajadas());
			}
		}
	}

	@Test
	void clientesEquipoTest() {
		System.out.println("==clientesEquipoTest");
		EmpresaLimpieza empresa = new EmpresaLimpieza();

		// comprueba que al principio no tienen ningun cliente
		for (String idEquipo: idEquipos) {
			EquipoLimpieza equipo = empresa.buscaEquipo(idEquipo);
			System.out.println(equipo + " clientes:" + equipo.clientes());
			assertEquals(0, equipo.clientes().size());
		}

		// crea clientes
		Cliente c1 = new Cliente("c1", "D1");
		empresa.anhadeCliente(c1);	
		Cliente c2 = new Cliente("c2", "D2");
		empresa.anhadeCliente(c2);	
		Cliente c3 = new Cliente("c3", "D3");
		empresa.anhadeCliente(c3);	
		Cliente c4 = new Cliente("c4", "D4");
		empresa.anhadeCliente(c4);

		// asigna cliente a un equipo
		EquipoLimpieza equipo1 = empresa.asignaServicioLimpieza("c4");
		empresa.equipoFinalizaServicio(equipo1.id(), 1);
		
		// comprueba que equipo1 tiene de cliente a c4
		for (String idEquipo: idEquipos) {
			EquipoLimpieza equipo = empresa.buscaEquipo(idEquipo);
			System.out.println(equipo + " clientes:" + equipo.clientes());
			if (equipo == equipo1) {
				assertEquals(1, equipo.clientes().size());
				assertTrue(equipo.clientes().contains(c4));
			}
		}

		// asigna cliente a otro equipo
		EquipoLimpieza equipo2 = empresa.asignaServicioLimpieza("c2");
		assertNotEquals(equipo1, equipo2);
		empresa.equipoFinalizaServicio(equipo2.id(), 2);
		
		// comprueba que los clientes estan bien
		for (String idEquipo: idEquipos) {
			EquipoLimpieza equipo = empresa.buscaEquipo(idEquipo);
			System.out.println(equipo + " clientes:" + equipo.clientes());
			if (equipo == equipo1) {
				assertEquals(1, equipo.clientes().size());
				assertTrue(equipo.clientes().contains(c4));
			}
			if (equipo == equipo2) {
				assertEquals(1, equipo.clientes().size());
				assertTrue(equipo.clientes().contains(c2));
			}
		}
		
		// asigna otros dos clientes
		EquipoLimpieza equipo3 = empresa.asignaServicioLimpieza("c1");
		empresa.equipoFinalizaServicio(equipo3.id(), 3);
		assertNotEquals(equipo1, equipo3);
		assertNotEquals(equipo2, equipo3);
		EquipoLimpieza equipo4 = empresa.asignaServicioLimpieza("c3");
		assertEquals(equipo1, equipo4);
		empresa.equipoFinalizaServicio(equipo4.id(), 3);
		
		System.out.println(equipo1 + " clientes:" + equipo1.clientes());		
		System.out.println(equipo2 + " clientes:" + equipo2.clientes());		
		System.out.println(equipo3 + " clientes:" + equipo3.clientes());
		
		// comprueba que los clientes estan bien
		assertEquals(2, equipo1.clientes().size());
		assertTrue(equipo1.clientes().contains(c4));
		assertTrue(equipo1.clientes().contains(c3));
		
		assertEquals(1, equipo2.clientes().size());
		assertTrue(equipo2.clientes().contains(c2));
		
		assertEquals(1, equipo3.clientes().size());
		assertTrue(equipo3.clientes().contains(c1));
	}

	/**
	 * Muestra por consola el ranking de clientes.
	 * @param empresa empresa de la que mostrar el ranking.
	 * @param numClientes numero de clientes en el ranking.
	 */
	private void printRankingClientes(EmpresaLimpieza empresa,
			int numClientes) {
		System.out.print("Ranking clientes:");
		for (int i = 1; i <= numClientes; i++) {
			System.out.print(" " + i + "->" + empresa.clienteEnPosRanking(i));
		}
		System.out.println();
	}

	/**
	 * Muestra por consola el estado de los equipos.
	 * @param empresa empresa de la que mostrar el ranking.
	 */
	private void muestraEstadoEquipos(EmpresaLimpieza empresa) {
		System.out.print("Equipos:");
		for (String idEquipo: idEquipos) {
			System.out.print(empresa.buscaEquipo(idEquipo));
		}
		System.out.println();
	}

}
