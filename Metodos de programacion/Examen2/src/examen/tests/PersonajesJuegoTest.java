package examen.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fundamentos_test.test.infraestructura.FundamentosTest;
import examen.gui.GuiPruebaPersonajes;
import static examen.gui.GuiPruebaPersonajes.*;

/**
 * Test de la aplicacion de prueba de los ataques entre personajes de un juego
 * multijugador..
 * (Test desde la interfaz grafica utilizando fundamentos_test)
 * 
 * @author  Metodos de Programacion (UC)
 * @version may-24
 */
class PersonajesJuegoTest {
	private static final int VIDA_GUERRERO = 55;
	private static final int VIDA_MAGO = 45;

	private static final int IND_CONAN = 0;		
	private static final int IND_MERLIN = 1;	
	private static final int IND_XENA = 2;
	private static final int IND_GALADRIEL = 3;
	
	private static final int DANHO_MAGO = 14;
	private static final int DANHO_MAGO_REDUCIDO = 4;

	private static final int DANHO_CONAN = 20;		
	private static final int DANHO_MERLIN = DANHO_MAGO;	
	private static final int DANHO_XENA = 24;
	private static final int DANHO_GALADRIEL = DANHO_MAGO;
	
	private static final int ENERGIA_MAGO = 10;

	private static final int REDUCCION_DANHO_GUERRERO = 2;

	private static final String[] NOMBRES =
		{"Conan", "Merlin", "Xena", "Galadriel"};

	private static final int[] VIDA_INI =
		{VIDA_GUERRERO, VIDA_MAGO, VIDA_GUERRERO, VIDA_MAGO};

	private static final int[] PTOS_DANHO =
		{DANHO_CONAN, DANHO_MERLIN, DANHO_XENA, DANHO_GALADRIEL};

	private static final int[] REDUCCION_DANHO =
		{REDUCCION_DANHO_GUERRERO, 1, REDUCCION_DANHO_GUERRERO, 1};

	private static final String[] OPTION_NAMES =
		{"REALIZA_ATAQUE", "RECARGA_ENERGIA", "CONSULTA_VIDA",
		"CONSULTA_ELIMINADOR"};

	// Mensajes GUI
	private static final String[][] msjsGUI =
		{
				{"Realizado", "ningun personaje", "eliminado"}, // REALIZA_ATAQUE
				{"Recargados", "ningun personaje", "eliminado",
				"no es mago"}, // RECARGA_ENERGIA
				{"Vida:", "ningun personaje"}, // CONSULTA_VIDA
				{"eliminado por:", "ningun personaje",
				"no ha sido eliminado"} // CONSULTA_ELIMINADOR
		};

	@Test
	void ataqueErrorNombreTest() {
		System.out.println("ataqueErrorNombreTest");

		// comprueba que falla si algun nombre no es valido
		FundamentosTest.interaccionGUI(REALIZA_ATAQUE, "ningun personaje",
				NOMBRES[1], "XX");
		FundamentosTest.interaccionGUI(REALIZA_ATAQUE, "ningun personaje",
				"YY", NOMBRES[3]);

		// comprueba que se realiza el ataque cuando ambos nombres son validos
		FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, NOMBRES[0], NOMBRES[2]);		
	}

	@Test
	void ataqueErrorEliminadoTest() {
		System.out.println("ataqueErrorEliminadoTest");

		// elimina un personaje
		eliminaPersonaje(IND_XENA, IND_MERLIN); // usa "Xena" para eliminar a "Merlin"

		// comprueba que falla al tratar de atacar un jugaro eliminado
		FundamentosTest.interaccionGUI(REALIZA_ATAQUE, "eliminado",
				"Conan", "Merlin");
		// comprueba que falla al tratar de atacar un jugaro eliminado
		FundamentosTest.interaccionGUI(REALIZA_ATAQUE, "eliminado",
				"Merlin", "Xena");

		// comprueba que se realiza el ataque cuando ambos nombres son validos
		FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, "Conan", "Xena");		
	}

	@Test
	void recargaEnergiaErrorNombreTest() {
		System.out.println("recargaEnergiaErrorNombreTest");

		// comprueba que falla si el nombre no es valido
		FundamentosTest.interaccionGUI(RECARGA_ENERGIA, "ningun personaje",
				"XX", 4);

		// comprueba que se realiza la recarga si el nombre es valido
		FundamentosTest.interaccionGuiOK(RECARGA_ENERGIA, "Galadriel", 4);		
	}

	@Test
	void recargaEnergiaErrorEliminadoTest() {
		System.out.println("recargaEnergiaErrorEliminadoTest");

		// elimina un personaje (usa "Xena" para eliminar a "Galadriel")
		eliminaPersonaje(IND_XENA, IND_GALADRIEL);

		// comprueba que falla si mago ha sido eliminado
		FundamentosTest.interaccionGUI(RECARGA_ENERGIA, "eliminado",
				"Galadriel", 4);

		// comprueba que se realiza la recarga si el nombre es valido
		FundamentosTest.interaccionGuiOK(RECARGA_ENERGIA, "Merlin", 2);		
	}

	@Test
	void recargaEnergiaErrorNoMagoTest() {
		System.out.println("recargaEnergiaErrorNoMagoTest");

		// comprueba que falla si el personaje no es mago
		FundamentosTest.interaccionGUI(RECARGA_ENERGIA, "no es mago",
				"Xena", 4);

		// comprueba que se realiza la recarga si es mago
		FundamentosTest.interaccionGuiOK(RECARGA_ENERGIA, "Galadriel", 2);		
	}

	@Test
	void consultaVidaErrorNombreTest() {
		System.out.println("consultaVidaErrorNombreTest");

		// comprueba que falla si el nombre no es valido
		FundamentosTest.interaccionGUI(CONSULTA_VIDA, "ningun personaje",
				"Nadie");

		// comprueba que no falla si el nombre es valido
		FundamentosTest.interaccionGuiOK(CONSULTA_VIDA, "Galadriel");		
	}

	@Test
	void consultaEliminadorErrorNombreTest() {
		System.out.println("consultaEliminadorErrorNombreTest");

		// comprueba que falla si el nombre no es valido
		FundamentosTest.interaccionGUI(CONSULTA_ELIMINADOR, "ningun personaje",
				"Nadie");

		// comprueba que no falla si el nombre es valido
		FundamentosTest.interaccionGUI(CONSULTA_ELIMINADOR,
				"no ha sido eliminado", "Galadriel");		
	}

	@Test
	void consultaEliminadorNoEliminadoTest() {
		System.out.println("consultaEliminadorNoEliminadoTest");

		// comprueba que notifica que no ha sido eliminado
		FundamentosTest.interaccionGUI(CONSULTA_ELIMINADOR,
				"no ha sido eliminado", "Conan");

		// elimina y comprueba que ya ha sido eliminado
		eliminaPersonaje(IND_GALADRIEL, IND_CONAN);
		FundamentosTest.interaccionGuiOK(CONSULTA_ELIMINADOR, "Conan");
	}

	@Test
	void vidaInicialGuerreroTest() {
		System.out.println("vidaInicialGuerreroTest");

		// comprueba que la vida inicial de los guerreros es correcta
		final int vida1 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA,
				"Conan");
		assertEquals(VIDA_GUERRERO, vida1);
		final int vida2 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA,
				"Xena");
		assertEquals(VIDA_GUERRERO, vida2);
	}

	@Test
	void vidaInicialMagoTest() {
		System.out.println("vidaInicialMagoTest");

		// comprueba que la vida inicial de los guerreros es correcta
		final int vida1 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA,
				"Merlin");
		assertEquals(VIDA_MAGO, vida1);
		final int vida2 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA,
				"Galadriel");
		assertEquals(VIDA_MAGO, vida2);
	}

	@Test
	void danhoAtaqueGuerreroMagoTest() {
		System.out.println("danhoAtaqueGuerreroMagoTest");

		final int vida1 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Merlin");

		// Ataque Conan -> Merlin
		FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, "Conan", "Merlin");

		// comprueba que la reduccion de vida es la esperada
		final int vida2 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Merlin");
		assertEquals(DANHO_CONAN, vida1 - vida2, "Danho incorrecto");
	}

	@Test
	void danhoAtaqueMagoMagoTest() {
		System.out.println("danhoAtaqueMagoMagoTest");

		final int vida1 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Galadriel");

		// Ataque Merlin -> Galadriel 
		FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, "Merlin", "Galadriel");

		// comprueba que la reduccion de vida es la esperada
		final int vida2 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Galadriel");
		assertEquals(DANHO_MERLIN, vida1 - vida2, "Danho incorrecto");
	}

	@Test
	void danhoAtaqueGuerreroGuerreroTest() {
		System.out.println("danhoAtaqueGuerreroGuerreroTest");

		final int vida1 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Conan");

		// Ataque Xena -> Conan
		FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, "Xena", "Conan");

		// comprueba que la reduccion de vida es la esperada
		final int vida2 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Conan");
		assertEquals(DANHO_XENA / REDUCCION_DANHO_GUERRERO,
				vida1 - vida2, "Danho incorrecto");
	}

	@Test
	void danhoAtaqueMagoGuerreroTest() {
		System.out.println("danhoAtaqueMagoGuerreroTest");

		final int vida1 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Xena");

		// Ataque Galadriel -> Xena
		FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, "Galadriel", "Xena");

		// comprueba que la reduccion de vida es la esperada
		final int vida2 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Xena");
		assertEquals(DANHO_GALADRIEL / REDUCCION_DANHO_GUERRERO,
				vida1 - vida2, "Danho incorrecto");
	}

	@Test
	void danhoAtaqueMagoSinEnergiaTest() {
		System.out.println("danhoAtaqueMagoSinEnergiaTest");

		// realiza varios ataques hasta eliminar a "Xena"
		final int numAtaques = eliminaPersonaje(IND_GALADRIEL, IND_XENA);
		
		// agota la energia con mas ataques
		int vida1 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Conan");
		int vida2;
		for (int i = numAtaques; i < ENERGIA_MAGO; i++) {
			FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, "Galadriel", "Conan");
			vida2 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Conan");
			assertEquals(DANHO_GALADRIEL / REDUCCION_DANHO_GUERRERO,
					vida1 - vida2, "Danho incorrecto");
			vida1 = vida2;
		}
		
		// comprueba que ahora hace menos danho
		System.out.println(" Energia de Galadriel agotada");
		FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, "Galadriel", "Conan");
		vida2 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Conan");
		assertEquals(DANHO_MAGO_REDUCIDO / REDUCCION_DANHO_GUERRERO,
				vida1 - vida2, "Danho incorrecto");
		FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, "Galadriel", "Conan");
		final int vida3 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Conan");
		assertEquals(DANHO_MAGO_REDUCIDO / REDUCCION_DANHO_GUERRERO,
				vida2 - vida3, "Danho incorrecto");
	}

	@Test
	void danhoAtaqueMagoRecargaEnergiaTest() {
		System.out.println("danhoAtaqueMagoRecargaEnergiaTest");

		// realiza varios ataques hasta eliminar a "Xena"
		final int numAtaques = eliminaPersonaje(IND_GALADRIEL, IND_XENA);
		
		// agota la energia con mas ataques
		int vida1 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Conan");
		int vida2;
		for (int i = numAtaques; i < ENERGIA_MAGO; i++) {
			FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, "Galadriel", "Conan");
			vida2 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Conan");
			assertEquals(DANHO_GALADRIEL / REDUCCION_DANHO_GUERRERO,
					vida1 - vida2, "Danho incorrecto");
			vida1 = vida2;
		}
		
		// comprueba que ahora hace menos danho
		System.out.println(" Energia de Galadriel agotada");
		FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, "Galadriel", "Conan");
		vida2 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Conan");
		assertEquals(DANHO_MAGO_REDUCIDO / REDUCCION_DANHO_GUERRERO,
				vida1 - vida2, "Danho incorrecto");
		FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, "Galadriel", "Conan");
		vida1 = vida2;
		vida2 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Conan");
		assertEquals(DANHO_MAGO_REDUCIDO / REDUCCION_DANHO_GUERRERO,
				vida1 - vida2, "Danho incorrecto");
		
		// recarga energia y comprueba que hace mas danho
		FundamentosTest.interaccionGuiOK(RECARGA_ENERGIA, "Galadriel", 2);
		FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, "Galadriel", "Conan");
		vida1 = vida2;
		vida2 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Conan");
		assertEquals(DANHO_MAGO / REDUCCION_DANHO_GUERRERO,
				vida1 - vida2, "Danho incorrecto");
		FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, "Galadriel", "Conan");
		vida1 = vida2;
		vida2 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Conan");
		assertEquals(DANHO_MAGO / REDUCCION_DANHO_GUERRERO,
				vida1 - vida2, "Danho incorrecto");
		
		// comprueba que vuelve a hacer menos danho
		System.out.println(" Energia de Galadriel agotada otra vez");
		FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE, "Galadriel", "Conan");
		vida1 = vida2;
		vida2 = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA, "Conan");
		assertEquals(DANHO_MAGO_REDUCIDO / REDUCCION_DANHO_GUERRERO,
				vida1 - vida2, "Danho incorrecto");
		
	}

	@Test
	void consultaEliminadorSimpleTest() {
		System.out.println("consultaEliminadorSimpleTest");
		
		// comprueba que Merlin no esta eliminado
		FundamentosTest.interaccionGUI(CONSULTA_ELIMINADOR,
				"no ha sido eliminado", "Merlin");

		// elimina a Merlin con Xena
		eliminaPersonaje(IND_XENA, IND_MERLIN);
		
		// comprueba que ha sido eliminado por Xena
		final String respuesta =
				FundamentosTest.interaccionGuiOK(CONSULTA_ELIMINADOR, "Merlin");
		final String nombreEliminador =
				respuesta.substring(respuesta.lastIndexOf(' ') + 1);
		assertEquals("Xena", nombreEliminador);
	}

	@Test
	void consultaEliminadorTest() {
		System.out.println("consultaEliminadorTest");

		// elimina a Galadriel con Conan
		eliminaPersonaje(IND_CONAN, IND_GALADRIEL);

		// elimina a Conan con Xena
		eliminaPersonaje(IND_XENA, IND_CONAN);

		// elimina a Merlin con Xena
		eliminaPersonaje(IND_XENA, IND_MERLIN);
		
		// comprueba que Galadriel ha sido eliminada por Conan
		final String respuesta1 =
				FundamentosTest.interaccionGuiOK(CONSULTA_ELIMINADOR, "Galadriel");
		final String nombreEliminador1 =
				respuesta1.substring(respuesta1.lastIndexOf(' ') + 1);
		assertEquals("Conan", nombreEliminador1);
		
		// comprueba que Conan ha sido eliminada por Xena
		final String respuesta2 =
				FundamentosTest.interaccionGuiOK(CONSULTA_ELIMINADOR, "Conan");
		final String nombreEliminador2 =
				respuesta2.substring(respuesta2.lastIndexOf(' ') + 1);
		assertEquals("Xena", nombreEliminador2);
		
		// comprueba que Merlin ha sido eliminada por Xena
		final String respuesta3 =
				FundamentosTest.interaccionGuiOK(CONSULTA_ELIMINADOR, "Merlin");
		final String nombreEliminador3 =
				respuesta3.substring(respuesta3.lastIndexOf(' ') + 1);
		assertEquals("Xena", nombreEliminador3);
	}

	private int eliminaPersonaje(int indAtacante, int indAtacado) {
		final int danhoAtaque =  PTOS_DANHO[indAtacante] /
				REDUCCION_DANHO[indAtacado];
		final int numAtaques = (VIDA_INI[indAtacado] + danhoAtaque - 1) /
				danhoAtaque;

		final String nomAtacante = NOMBRES[indAtacante];
		final String nomAtacado = NOMBRES[indAtacado];
		System.out.println(" Eliminando a " + nomAtacado + " con " +
				nomAtacante + " en " + numAtaques + " ataques.");
		for (int i = 0; i < numAtaques; i++) {
			final int vida = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA,
					nomAtacado);
			assertTrue(vida > 0,
					"La vida de " + nomAtacado + " es " + vida +
					" pero deberia ser mayor que 0");
			FundamentosTest.interaccionGuiOK(REALIZA_ATAQUE,
					nomAtacante, nomAtacado);
		}
		final int vida = FundamentosTest.leeIntGuiOK(CONSULTA_VIDA,
				nomAtacado);
		assertTrue(vida <= 0, "La vida de " + nomAtacado + " es " + vida +
				" pero deberia haber sido 0");
		
		return numAtaques;
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
		FundamentosTest.lanzaGUI(GuiPruebaPersonajes.class);
	}

	/**
	 * Se ejecuta despues de cada test.
	 * @throws InterruptedException error en thread main.
	 */
	@AfterEach
	public void finalizaGUI() throws InterruptedException {
		FundamentosTest.finalizaGUI(GuiPruebaPersonajes.FIN_APLICACION);
	}
}
