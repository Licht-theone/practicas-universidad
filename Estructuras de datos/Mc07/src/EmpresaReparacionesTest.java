

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * Test de la clase EmpresaReparaciones.
 * 
 * @author Estructuras de Datos (UC)
 * @version oct-2020
 *
 */
class EmpresaReparacionesTest {

	private final String[] nombresOperarios = {"Pepa", "Lolo", "Chus"};
	// deben coincidir con los nombres de los operarios creados en el
	// constructor de EmpresaReparaciones.

	@Test
	void asignaOperarioNoExisteTest() throws FileNotFoundException {
		System.out.println("asignaOperarioNoExisteTest");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");
		Reparacion reparacion = new Reparacion("Descripcion", "Direccion");

		// prueba que no falla con los operarios que existen
		for (String nomOpe: nombresOperarios) {
			empresa.asignaReparacionAOperario(nomOpe, reparacion);
		}

		// prueba que falla con un nombre que no existe
		try {
			empresa.asignaReparacionAOperario("OpeNoExiste", reparacion);
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaReparaciones.OperarioNoExistente e) {
			// Lo correcto es que se lance la excepcion
		}
	}

	@Test
	void masAntiguaOperarioNoExisteTest() throws FileNotFoundException {
		System.out.println("masAntiguaOperarioNoExisteTest");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");

		// prueba que falla con un nombre que no existe
		try {
			empresa.operarioFinalizaReparacion("OpeNoExiste");
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaReparaciones.OperarioNoExistente e) {
			// Lo correcto es que se lance la excepcion
		}
	}

	@Test
	void finReparacionOperarioNoExisteTest() throws FileNotFoundException {
		System.out.println("finReparacionOperarioNoExisteTest");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");

		// prueba que falla con un nombre que no existe
		try {
			empresa.operarioFinalizaReparacion("OpeNoExiste");
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaReparaciones.OperarioNoExistente e) {
			// Lo correcto es que se lance la excepcion
		}
	}

	@Test
	void reparacionEnHistoricoNoExisteTest() throws FileNotFoundException {
		System.out.println("reparacionEnHistoricoNoExisteTest");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");

		// prueba que falla con un nombre que no existe
		try {
			empresa.reparacionEnHistorico("OpeNoExiste", 0);
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaReparaciones.OperarioNoExistente e) {
			// Lo correcto es que se lance la excepcion
		}
	}

	@Test
	void comienzanSinReparacionesTest() throws FileNotFoundException {
		System.out.println("comienzanSinReparacionesTest");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");

		// prueba que los operarios comienzan sin reparaciones pendientes
		for (String nomOpe: nombresOperarios) {
			try {
				empresa.operarioFinalizaReparacion(nomOpe);
				fail("Deberia haberse lanzado la excepcion");
			} catch (EmpresaReparaciones.NoHayReparacionesPendientes e) {
				// Lo correcto es que se lance la excepcion
			}
		}
	}

	@Test
	void asignaFinalizaTest() throws FileNotFoundException {
		System.out.println("finalizaNoAsignadaTest");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");

		// asigna y finaliza reparacion para todos
		for (String nomOpe: nombresOperarios) {
			empresa.asignaReparacionAOperario(nomOpe,
					new Reparacion("Descripcion", "Direccion"));
			empresa.operarioFinalizaReparacion(nomOpe);
		}

		// comprueba que los operarios no tienen reparaciones pendientes
		for (String nomOpe: nombresOperarios) {
			try {
				empresa.operarioFinalizaReparacion(nomOpe);
				fail("Deberia haberse lanzado la excepcion");
			} catch (EmpresaReparaciones.NoHayReparacionesPendientes e) {
				// Lo correcto es que se lance la excepcion
			}
		}
	}

	@Test
	void reparacioneMasAntiguaTest() throws FileNotFoundException {
		System.out.println("reparacioneMasAntiguaTest");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");
		Reparacion rep1 = new Reparacion("Desc1", "Direc1");
		Reparacion rep2 = new Reparacion("Desc2", "Direc2");
		Reparacion rep3 = new Reparacion("Desc3", "Direc3");
		Reparacion rep;

		// comprueba que empiezan sin reparaciones asignadas
		for (String nomOpe: nombresOperarios) {
			try {
				empresa.operarioFinalizaReparacion(nomOpe);
				fail("Deberia haberse lanzado la excepcion");
			} catch (EmpresaReparaciones.NoHayReparacionesPendientes e) {
				// Lo correcto es que se lance la excepcion
			}
		}

		String nomOpe = nombresOperarios[2];

		// asigna reparacion y comprueba la mas antigua
		empresa.asignaReparacionAOperario(nomOpe, rep1);
		rep = empresa.operarioFinalizaReparacion(nomOpe);
		assertEquals(rep1, rep);	

		// asigna reparacion y comprueba que las mas antigua no cambia
		empresa.asignaReparacionAOperario(nomOpe, rep2);
		rep = empresa.operarioFinalizaReparacion(nomOpe);
		assertEquals(rep1, rep);

		// finaliza, asigna y comprueba que la mas antigua es la debida
		empresa.operarioFinalizaReparacion(nomOpe);
		empresa.asignaReparacionAOperario(nomOpe, rep3);
		rep = empresa.operarioFinalizaReparacion(nomOpe);
		assertEquals(rep2, rep);
	}

	@Test
	void ordenReparacionesTest() throws FileNotFoundException {
		System.out.println("ordenReparacionesTest");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");
		final String nomOpe = nombresOperarios[1];
		Reparacion rep1 = new Reparacion("Desc1", "Direc1");
		Reparacion rep2 = new Reparacion("Desc2", "Direc2");
		Reparacion rep3 = new Reparacion("Desc3", "Direc3");
		Reparacion rep;

		// asigna dos reparaciones
		empresa.asignaReparacionAOperario(nomOpe, rep1);
		empresa.asignaReparacionAOperario(nomOpe, rep2);

		// comprueba que se hace primero la primera
		
		rep = empresa.operarioFinalizaReparacion(nomOpe);
		assertEquals(rep1, rep);

		// asigna otra mas
		empresa.asignaReparacionAOperario(nomOpe, rep3);

		// comprueba que se hacen en orden
		
		rep = empresa.operarioFinalizaReparacion(nomOpe);
		assertEquals(rep2, rep);

		
		rep = empresa.operarioFinalizaReparacion(nomOpe);
		assertEquals(rep3, rep);
	}	

	@Test
	void historicoPosIncorrectaTest() throws FileNotFoundException {
		System.out.println("historicoNoReparacionesTest");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");

		// comprueba que los operarios empiezan con el historico vacio
		for (String nomOpe: nombresOperarios) {
			try {
				empresa.reparacionEnHistorico(nomOpe, 0);
				fail("Deberia haberse lanzado la excepcion");
			} catch (EmpresaReparaciones.PosReparacionIncorrecta e) {
				// Lo correcto es que se lance la excepcion
			}
		}

		final String nomOpe = nombresOperarios[0];
		Reparacion rep0 = new Reparacion("Desc0", "Direc0");
		Reparacion rep1 = new Reparacion("Desc1", "Direc1");
		Reparacion rep2 = new Reparacion("Desc2", "Direc2");

		// asigna y finaliza operacion
		empresa.asignaReparacionAOperario(nomOpe, rep0);
		empresa.operarioFinalizaReparacion(nomOpe);

		// comprueba posiciones correctas e incorrectas
		empresa.reparacionEnHistorico(nomOpe, 0);
		try {
			empresa.reparacionEnHistorico(nomOpe, -1);
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaReparaciones.PosReparacionIncorrecta e) {
			// Lo correcto es que se lance la excepcion

		}
		try {
			empresa.reparacionEnHistorico(nomOpe, 1);
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaReparaciones.PosReparacionIncorrecta e) {
			// Lo correcto es que se lance la excepcion
		}
		
		// asigna y finaliza dos operaciones
		empresa.asignaReparacionAOperario(nomOpe, rep1);
		empresa.operarioFinalizaReparacion(nomOpe);
		empresa.asignaReparacionAOperario(nomOpe, rep2);
		empresa.operarioFinalizaReparacion(nomOpe);	

		// comprueba posiciones correctas e incorrectas
		empresa.reparacionEnHistorico(nomOpe, 1);
		empresa.reparacionEnHistorico(nomOpe, 2);
		empresa.reparacionEnHistorico(nomOpe, 0);
		try {
			empresa.reparacionEnHistorico(nomOpe, -1);
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaReparaciones.PosReparacionIncorrecta e) {
			// Lo correcto es que se lance la excepcion

		}
		try {
			empresa.reparacionEnHistorico(nomOpe, 3);
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaReparaciones.PosReparacionIncorrecta e) {
			// Lo correcto es que se lance la excepcion
		}
	}

	@Test
	void historico1Test() throws FileNotFoundException {
		System.out.println("historico1Test");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");
		final String nomOpe = nombresOperarios[2];
		Reparacion rep0 = new Reparacion("Desc0", "Direc0");
		Reparacion rep1 = new Reparacion("Desc1", "Direc1");
		Reparacion rep2 = new Reparacion("Desc2", "Direc2");
		Reparacion rep;

		// asigna y finaliza operacion
		empresa.asignaReparacionAOperario(nomOpe, rep0);
		empresa.operarioFinalizaReparacion(nomOpe);

		// comprueba posicion 0 del historico
		rep = empresa.reparacionEnHistorico(nomOpe, 0);
		assertEquals(rep0, rep);

		// asigna y finaliza dos operaciones
		empresa.asignaReparacionAOperario(nomOpe, rep1);
		empresa.operarioFinalizaReparacion(nomOpe);
		empresa.asignaReparacionAOperario(nomOpe, rep2);
		empresa.operarioFinalizaReparacion(nomOpe);	

		// comprueba posiciones del historico
		rep = empresa.reparacionEnHistorico(nomOpe, 0);
		assertEquals(rep0, rep);
		rep = empresa.reparacionEnHistorico(nomOpe, 1);
		assertEquals(rep1, rep);
		rep = empresa.reparacionEnHistorico(nomOpe, 2);
		assertEquals(rep2, rep);
	}	

	@Test
	void historico2Test() throws FileNotFoundException {
		System.out.println("historico1Test");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");
		Reparacion rep0 = new Reparacion("Desc0", "Direc0");
		Reparacion rep1 = new Reparacion("Desc1", "Direc1");
		Reparacion rep2 = new Reparacion("Desc2", "Direc2");
		Reparacion rep;

		// asigna y finaliza operacion a dos operarios
		empresa.asignaReparacionAOperario(nombresOperarios[0], rep0);
		empresa.operarioFinalizaReparacion(nombresOperarios[0]);
		empresa.asignaReparacionAOperario(nombresOperarios[2], rep2);
		empresa.operarioFinalizaReparacion(nombresOperarios[2]);

		// comprueba posicion 0 del historico de los dos
		rep = empresa.reparacionEnHistorico(nombresOperarios[0], 0);
		assertEquals(rep0, rep);
		rep = empresa.reparacionEnHistorico(nombresOperarios[2], 0);
		assertEquals(rep2, rep);

		// asigna y finaliza otra operacion
		empresa.asignaReparacionAOperario(nombresOperarios[0], rep1);
		empresa.operarioFinalizaReparacion(nombresOperarios[0]);

		// comprueba posiciones del historico
		rep = empresa.reparacionEnHistorico(nombresOperarios[0], 0);
		assertEquals(rep0, rep);
		rep = empresa.reparacionEnHistorico(nombresOperarios[0], 1);
		assertEquals(rep1, rep);
		rep = empresa.reparacionEnHistorico(nombresOperarios[2], 0);
		assertEquals(rep2, rep);
		try {
			empresa.reparacionEnHistorico(nombresOperarios[2], 1);
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaReparaciones.PosReparacionIncorrecta e) {
			// Lo correcto es que se lance la excepcion
		}
	}

	@Test
	void eliminaOperarioDesocupadosPrimeroTest() throws FileNotFoundException {
		System.out.println("eliminaOperarioDesocupadosPrimeroTest");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");

		empresa.asignaReparacionAOperario(nombresOperarios[1],
				new Reparacion("Descripcion", "Direccion"));		
		empresa.asignaReparacionAOperario(nombresOperarios[2],
				new Reparacion("Descripcion", "Direccion"));

		empresa.eliminaOperariosDesocupados();

		// comprueba que ha eliminado el primero
		try {
			empresa.asignaReparacionAOperario(nombresOperarios[0], 
					new Reparacion("Descripcion", "Direccion"));
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaReparaciones.OperarioNoExistente e) {
			// Lo correcto es que se lance la excepcion
		}
		empresa.asignaReparacionAOperario(nombresOperarios[1], 
				new Reparacion("Descripcion", "Direccion"));
		empresa.asignaReparacionAOperario(nombresOperarios[2], 
				new Reparacion("Descripcion", "Direccion"));
	}

	@Test
	void eliminaOperarioDesocupadosMedioTest() throws FileNotFoundException {
		System.out.println("eliminaOperarioDesocupadosMedioTest");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");

		empresa.asignaReparacionAOperario(nombresOperarios[0],
				new Reparacion("Descripcion", "Direccion"));		
		empresa.asignaReparacionAOperario(nombresOperarios[2],
				new Reparacion("Descripcion", "Direccion"));

		empresa.eliminaOperariosDesocupados();

		// comprueba que ha eliminado el del medio
		empresa.asignaReparacionAOperario(nombresOperarios[0], 
				new Reparacion("Descripcion", "Direccion"));
		try {
			empresa.asignaReparacionAOperario(nombresOperarios[1], 
					new Reparacion("Descripcion", "Direccion"));
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaReparaciones.OperarioNoExistente e) {
			// Lo correcto es que se lance la excepcion
		}
		empresa.asignaReparacionAOperario(nombresOperarios[2], 
				new Reparacion("Descripcion", "Direccion"));
	}

	@Test
	void eliminaOperariosDesocupadosUltimoTest() throws FileNotFoundException {
		System.out.println("eliminaOperariosDesocupadosUltimoTest");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");

		empresa.asignaReparacionAOperario(nombresOperarios[0],
				new Reparacion("Descripcion", "Direccion"));		
		empresa.asignaReparacionAOperario(nombresOperarios[1],
				new Reparacion("Descripcion", "Direccion"));

		empresa.eliminaOperariosDesocupados();

		// comprueba que ha eliminado el ultimo
		empresa.asignaReparacionAOperario(nombresOperarios[0], 
				new Reparacion("Descripcion", "Direccion"));
		empresa.asignaReparacionAOperario(nombresOperarios[1], 
				new Reparacion("Descripcion", "Direccion"));
		try {
			empresa.asignaReparacionAOperario(nombresOperarios[2], 
					new Reparacion("Descripcion", "Direccion"));
			fail("Deberia haberse lanzado la excepcion");
		} catch (EmpresaReparaciones.OperarioNoExistente e) {
			// Lo correcto es que se lance la excepcion
		}
	}

	@Test
	void eliminaOperariosDesocupadosTodosTest() throws FileNotFoundException {
		System.out.println("eliminaOperariosDesocupadosUltimoTest");
		EmpresaReparaciones empresa = new EmpresaReparaciones("");

		// asigna y finaliza reparacion para todos
		for (String nomOpe: nombresOperarios) {
			empresa.asignaReparacionAOperario(nomOpe,
					new Reparacion("Descripcion", "Direccion"));
			empresa.operarioFinalizaReparacion(nomOpe);
		}

		empresa.eliminaOperariosDesocupados();

		// comprueba que ha eliminado todos
		for (String nomOpe: nombresOperarios) {
			try {
				empresa.asignaReparacionAOperario(nomOpe, 
						new Reparacion("Descripcion", "Direccion"));
				fail("Deberia haberse lanzado la excepcion");
			} catch (EmpresaReparaciones.OperarioNoExistente e) {
				// Lo correcto es que se lance la excepcion
			}
		}
	}


}
