package examen.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import fundamentos_test.test.infraestructura.FundamentosTest;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import static examen.gui.GuiVisitasMuseo.*;
import examen.gui.GuiVisitasMuseo;
import examen.modelo.Grupo;

/**
 * Test de la aplicacion para la gestion de las reservas de grupos para las
 * visitas guiadas a un museo.
 * Usa "Fundamentos test".
 * 
 * @author  Metodos de Programacion (UC)
 * @version mar-24
 */
class VisitasMuseoTest {
	// datos globales para los tests
	private static final int NUM_VISITAS = 4;
	private static final int MX_CAPACIDAD_VISITA = 15;

	// opciones del menu
	private static final String[] OPTION_NAMES = {"REALIZA_RESERVA",
			"ANULA_RESERVA", "NUMERO_GRUPOS_CON_TAMANHO",
	"BUSCA_VISITA_CON_CAPACIDAD"};

	// Mensajes GUI
	private static final String[][] msjsGUI =
		{
				{"reserva", "incorrecto"}, // REALIZA_RESERVA
				{"Anulada", "incorrecto"}, // ANULA_RESERVA
				{"grupos con", "incorrecto"}, // NUMERO_GRUPOS_CON_TAMANHO		
				{"Hay capacidad", "No existe"} // BUSCA_VISITA_CON_CAPACIDAD	
		};

	@Test
	void reservaGrupoErrorIdVisitaTest() {
		System.out.println("reservaGrupoErrorIdVisitaTest");

		// prueba con Id -1
		FundamentosTest.interaccionGUI(REALIZA_RESERVA, "incorrecto", -1, 1);

		// prueba con linea NUM_VISITAS
		FundamentosTest.interaccionGUI(REALIZA_RESERVA, "incorrecto",
				NUM_VISITAS, 1);

		// prueba con Ids validos
		for (int id = 0; id < NUM_VISITAS; id++) {
			FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, id, id + 1);
		}
	}

	@Test
	void reservaGrupoErrorCapacidadSimpleTest() {
		System.out.println("reservaGrupoErrorCapacidadSimpleTest");
		final int idVisita = 1;

		// realiza varias reservas
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 4);
		int numVisitantes = 4;
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 6);
		numVisitantes = 10;
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 2);
		numVisitantes = 12;

		// comprueba que falla con un grupo demasiado grande
		FundamentosTest.interaccionGUI(REALIZA_RESERVA, "incorrecto",
				idVisita, MX_CAPACIDAD_VISITA - numVisitantes + 1);

		// comprueba que puede llenar la visita
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA,
				idVisita, MX_CAPACIDAD_VISITA - numVisitantes);

		// comprueba que no se pueden hacer mas reservas
		FundamentosTest.interaccionGUI(REALIZA_RESERVA, "incorrecto",
				idVisita, 1);	
	}

	@Test
	void reservaGrupoErrorCapacidadTest() {
		System.out.println("reservaGrupoErrorCapacidadTest");
		final int idVisita1 = 1;
		final int idVisita2 = 2;

		// realiza varias reservas en dos visitas
		int numVisitantes1 = 0;
		int numVisitantes2 = 0;
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita1, 4);
		numVisitantes1 = 2;
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita2, 6);
		numVisitantes2 = 6;

		// comprueba que falla con un grupo demasiado grande en idVisita2
		FundamentosTest.interaccionGUI(REALIZA_RESERVA, "incorrecto",
				idVisita2, MX_CAPACIDAD_VISITA - numVisitantes2 + 1);

		// pero no falla en idVisita1
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA,
				idVisita1, MX_CAPACIDAD_VISITA - numVisitantes2 + 1);
		numVisitantes1 += MX_CAPACIDAD_VISITA - numVisitantes2 + 1;

		// comprueba que puede reservar en idVisita2 lo que no puede idVisita1
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA,
				idVisita2, MX_CAPACIDAD_VISITA - numVisitantes1 + 1);
		FundamentosTest.interaccionGUI(REALIZA_RESERVA, "incorrecto",
				idVisita1, MX_CAPACIDAD_VISITA - numVisitantes1 + 1);
	}

	@Test
	void anulaReservaGrupoErrorIdVisitaTest() {
		System.out.println("anulaReservaGrupoErrorIdVisitaTest");

		// prueba con Id -1
		FundamentosTest.interaccionGUI(ANULA_RESERVA, "incorrecto", -1, "GRPx");

		// prueba con linea NUM_VISITAS
		FundamentosTest.interaccionGUI(ANULA_RESERVA, "incorrecto",
				NUM_VISITAS, "GRPx");

		// prueba con Ids validos
		for (int id = 0; id < NUM_VISITAS; id++) {
			FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, id, id + 1);
			FundamentosTest.interaccionGuiOK(ANULA_RESERVA, id,
					"GRP" + (id + 1));
		}
	}

	@Test
	void anulaReservaGrupoErrorCodGrupoSimpleTest() {
		System.out.println("anulaReservaGrupoErrorCodGrupoTest");
		final int idVisita = 2;

		// prueba con un grupo si reserva
		FundamentosTest.interaccionGUI(ANULA_RESERVA, "incorrecto", -1, "GRPx");

		// hace una reserva 
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 2);

		// prueba con el grupo correcto y con otro sin reserva
		FundamentosTest.interaccionGuiOK(ANULA_RESERVA, idVisita, "GRP1");
		FundamentosTest.interaccionGUI(ANULA_RESERVA, "incorrecto",
				idVisita, "GRPx");
	}

	@Test
	void anulaReservaGrupoErrorCodGrupoTest() {
		System.out.println("anulaReservaGrupoErrorCodGrupoTest");
		final int idVisita1 = 2;
		final int idVisita2 = 0;

		// realiza reservas en dos visitas 
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita1, 2); 
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita2, 2);

		// prueba en cada visita con los grupos GPR1, GPR2 y GPR3
		FundamentosTest.interaccionGUI(ANULA_RESERVA, "incorrecto",
				idVisita2, "GRP1");
		FundamentosTest.interaccionGuiOK(ANULA_RESERVA, idVisita1, "GRP1");

		FundamentosTest.interaccionGUI(ANULA_RESERVA, "incorrecto",
				idVisita1, "GRP2");
		FundamentosTest.interaccionGuiOK(ANULA_RESERVA, idVisita2, "GRP2");

		FundamentosTest.interaccionGUI(ANULA_RESERVA, "incorrecto",
				idVisita1, "GRP3");
		FundamentosTest.interaccionGUI(ANULA_RESERVA, "incorrecto",
				idVisita1, "GRP3");
	}

	@Test
	void numeroGruposErrorIdVisitaTest() {
		System.out.println("numeroGruposErrorIdVisitaTest");

		// prueba con Id -1
		FundamentosTest.interaccionGUI(NUMERO_GRUPOS_CON_TAMANHO,
				"incorrecto", -1, 2);

		// prueba con linea NUM_VISITAS
		FundamentosTest.interaccionGUI(NUMERO_GRUPOS_CON_TAMANHO,
				"incorrecto", NUM_VISITAS, 2);

		// prueba con Ids validos
		for (int id = 0; id < NUM_VISITAS; id++) {
			FundamentosTest.interaccionGuiOK(NUMERO_GRUPOS_CON_TAMANHO, id, 2);
		}
	}

	@Test
	void buscaVisitaConCapacidadErrorCapacidadTest() {
		System.out.println("buscaVisitaConCapacidadErrorCapacidadTest");
		final int numComponentes = 4;

		// comprueba que ninguna visita puede aceptar un grupo de
		// MX_CAPACIDAD_VISITA + 1
		FundamentosTest.interaccionGUI(BUSCA_VISITA_CON_CAPACIDAD,
				"No existe", MX_CAPACIDAD_VISITA + 1);

		// realiza reserva en todas las visitas
		for (int id = 0; id < NUM_VISITAS; id++) {
			FundamentosTest.interaccionGuiOK(REALIZA_RESERVA,
					id, numComponentes);
		}

		// comprueba que ninguna visita puede aceptar un grupo de
		// MX_CAPACIDAD_VISITA - numComponentes + 1
		FundamentosTest.interaccionGUI(BUSCA_VISITA_CON_CAPACIDAD,
				"No existe", MX_CAPACIDAD_VISITA - numComponentes + 1);

		// comprueba que si se encuentra alguna para un grupo de 
		// MX_CAPACIDAD_VISITA - numComponentes
		FundamentosTest.interaccionGuiOK(BUSCA_VISITA_CON_CAPACIDAD, 
				MX_CAPACIDAD_VISITA - numComponentes);
	}

	@Test
	void reservaAnulaTest() {
		System.out.println("reservaAnulaTest");
		final int idVisita = 1;
		final int idVisitaOtra = 2;

		// realiza reservas 
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 1); // GRP1
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 2); // GRP2
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 2); // GRP3
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 4); // GRP4
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 6); // GRP5

		// realiza una reserva en otra visita 
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA,
				idVisitaOtra, 6); // GRP6

		// anula dos visitas (con 6 componentes entre las 2)
		FundamentosTest.interaccionGuiOK(ANULA_RESERVA, idVisita, "GRP3");
		FundamentosTest.interaccionGuiOK(ANULA_RESERVA, idVisita, "GRP4");

		// comprueba que se pueden reservar 5 grupos de 1 componente
		for (int i = 0; i < 5; i++) {
			FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 1);
		}

		// anula dos visitas (con 7 componentes entre las 2)
		FundamentosTest.interaccionGuiOK(ANULA_RESERVA, idVisita, "GRP1");
		FundamentosTest.interaccionGuiOK(ANULA_RESERVA, idVisita, "GRP5");

		// comprueba que puede hacer una reserva para un grupo de 7 componentes
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 7);
	}

	@Test
	void reservaAnulaNumGruposTest() {
		System.out.println("reservaAnulaNumGruposTest");
		final int idVisita = 1;
		final int numComponentesGrupo = 4;

		// hace una reserva
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA,
				idVisita, numComponentesGrupo);

		// comprueba que hay un grupo con numComponentesGrupo componentes
		final int numGrupos1 =
				FundamentosTest.leeIntGuiOK(NUMERO_GRUPOS_CON_TAMANHO,
						idVisita, numComponentesGrupo);
		assertEquals(1, numGrupos1);

		// anula la reserva
		FundamentosTest.interaccionGuiOK(ANULA_RESERVA, idVisita, "GRP1");

		// comprueba que hay un grupo con numComponentesGrupo componentes
		final int numGrupos2 =
				FundamentosTest.leeIntGuiOK(NUMERO_GRUPOS_CON_TAMANHO,
						idVisita, numComponentesGrupo);
		assertEquals(0, numGrupos2);	
	}

	@Test
	void numeroGruposTest() {
		System.out.println("numeroGruposTest");
		final int idVisita = 1;
		final int idVisitaOtra = 2;

		// realiza reservas 
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 1); // GRP1
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 2); // GRP2
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 2); // GRP3
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 4); // GRP4
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 6); // GRP5

		// realiza una reserva en otra visita 
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA,
				idVisitaOtra, 6); // GRP6

		// anula dos visitas (con 6 componentes entre las 2)
		FundamentosTest.interaccionGuiOK(ANULA_RESERVA, idVisita, "GRP3");
		FundamentosTest.interaccionGuiOK(ANULA_RESERVA, idVisita, "GRP4");

		// comprueba que se pueden reservar 5 grupos de 1 componente
		for (int i = 0; i < 5; i++) {
			FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 1);
		}

		// anula dos visitas (con 7 componentes entre las 2)
		FundamentosTest.interaccionGuiOK(ANULA_RESERVA, idVisita, "GRP1");
		FundamentosTest.interaccionGuiOK(ANULA_RESERVA, idVisita, "GRP5");

		// comprueba que puede hacer una reserva para un grupo de 7 componentes
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA, idVisita, 7);
	}

	@Test
	void buscaVisitaConCapacidadSimpleTest() {
		System.out.println("buscaVisitaConCapacidadSimpleTest");
		final int idVisita1 = 1;
		final int idVisita2 = 2;
		final int numComponentes = 6;

		// realiza una reserva en todas las visitas menos idVisita1
		for (int id = 0; id < NUM_VISITAS; id++) {
			if (id != idVisita1) {
				FundamentosTest.interaccionGuiOK(REALIZA_RESERVA,
						id, numComponentes);
			}
		}

		// comprueba que solo hay hueco en idVisita1
		final int id1 = FundamentosTest.leeIntGuiOK(BUSCA_VISITA_CON_CAPACIDAD,
				MX_CAPACIDAD_VISITA - numComponentes + 1);
		assertEquals(idVisita1, id1);

		// realiza reserva en todas las visitas menos idVisita2
		for (int id = 0; id < NUM_VISITAS; id++) {
			if (id != idVisita2) {
				FundamentosTest.interaccionGuiOK(REALIZA_RESERVA,
						id, numComponentes + 1);
			}
		}

		// comprueba que solo hay hueco en idVisita2
		final int id2 = FundamentosTest.leeIntGuiOK(BUSCA_VISITA_CON_CAPACIDAD,
				MX_CAPACIDAD_VISITA - numComponentes);
		assertEquals(idVisita2, id2);
	}

	@Test
	void buscaVisitaConCapacidadAnulaTest() {
		System.out.println("buscaVisitaConCapacidadAnulaTest");
		final int idVisita1 = 3;
		final int idVisita2 = 2;
		final int numComponentes = 7;

		// realiza una reserva en todas las visitas
		for (int id = 0; id < NUM_VISITAS; id++) {
			FundamentosTest.interaccionGuiOK(REALIZA_RESERVA,
					id, numComponentes);
		}
		
		// anula la reserva en idVisita1
		FundamentosTest.interaccionGuiOK(ANULA_RESERVA,
				idVisita1, "GRP" + (idVisita1 + 1));

		// comprueba que solo hay hueco en idVisita1
		final int id1 = FundamentosTest.leeIntGuiOK(BUSCA_VISITA_CON_CAPACIDAD,
				MX_CAPACIDAD_VISITA - numComponentes + 1);
		assertEquals(idVisita1, id1);
		
		// reserva en idVisita1 y anula la reserva en idVisita2
		FundamentosTest.interaccionGuiOK(REALIZA_RESERVA,
				idVisita1, numComponentes);
		FundamentosTest.interaccionGuiOK(ANULA_RESERVA,
				idVisita2, "GRP" + (idVisita2 + 1));

		// comprueba que solo hay hueco en idVisita2
		final int id2 = FundamentosTest.leeIntGuiOK(BUSCA_VISITA_CON_CAPACIDAD,
				MX_CAPACIDAD_VISITA - numComponentes + 1);
		assertEquals(idVisita2, id2);
	}

	/**
	 * Pone el atributo estatico ultimoCodigo a 0 para que en todos los metodos
	 * de test los grupos empiecen por el codigo "GRP1".
	 * @throws Exception si se produce un error tratando de modificar
	 * el atributo estatico
	 */
	@BeforeEach
	void setUp() throws Exception {
		Field field = Grupo.class.getDeclaredField("ultimoCodigo");
		field.setAccessible(true);
		field.set(null, 0);
	}

	/*
	 *  metodos para la infraestructura de test
	 */

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
		FundamentosTest.lanzaGUI(GuiVisitasMuseo.class);
	}

	/**
	 * Se ejecuta despues de cada test.
	 * @throws InterruptedException error en thread main.
	 */
	@AfterEach
	public void finalizaGUI() throws InterruptedException {
		FundamentosTest.finalizaGUI(GuiVisitasMuseo.FIN_APLICACION);
	}

}
