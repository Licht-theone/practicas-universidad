package pract03.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pract03.modelo.Equipo;
import pract03.modelo.Jugador;

/**
 * Test de la clase Equipo.
 * Estadisticas de un equipo de baloncesto.
 * 
 * @author  Metodos de Programacion (UC)
 * @version sep-21
 */
class EquipoTest {
	private static final int PRIMER_DORSAL = 0;
	private static final int ULTIMO_DORSAL = 14;
	private static final int NUM_JUGADORES = ULTIMO_DORSAL - PRIMER_DORSAL + 1;
	private static final double VALORACION_POR_ASISTENCIA = 1.5;	

	@Test
	void testErroresSumaPuntosAnotados() {
		System.out.println("testErroresSumaPuntosAnotados");
		Equipo equipo = new Equipo();
		boolean correcto;

		// comprueba que un dorsal mayor que el ultimo produce error
		correcto = equipo.sumaPuntosAnotados(ULTIMO_DORSAL + 1, 2);
		assertTrue(!correcto);

		correcto = equipo.sumaPuntosAnotados(ULTIMO_DORSAL * 2, 2);
		assertTrue(!correcto);

		// comprueba que un dorsal menor que el primero produce error
		correcto = equipo.sumaPuntosAnotados(-10, 2);
		assertTrue(!correcto);

		correcto = equipo.sumaPuntosAnotados(PRIMER_DORSAL - 1, 2);
		assertTrue(!correcto);

		// comprueba que un dorsal correcto NO produce error
		for (int dorsal = PRIMER_DORSAL; dorsal <= ULTIMO_DORSAL; dorsal++) {
			correcto = equipo.sumaPuntosAnotados(dorsal, 2);
			assertTrue(correcto);
		}
	}

	@Test
	void testErroresSumaAsistencia() {
		System.out.println("testErroresSumaAsistencia");
		Equipo equipo = new Equipo();
		boolean correcto;

		// comprueba que un dorsal mayor que el ultimo produce error
		correcto = equipo.sumaAsistencia(ULTIMO_DORSAL + 1);
		assertTrue(!correcto);

		correcto = equipo.sumaAsistencia(ULTIMO_DORSAL * 2);
		assertTrue(!correcto);

		// comprueba que un dorsal menor que el primero produce error
		correcto = equipo.sumaAsistencia(-10);
		assertTrue(!correcto);

		correcto = equipo.sumaAsistencia(PRIMER_DORSAL - 1);
		assertTrue(!correcto);

		// comprueba que un dorsal correcto NO produce error
		for (int dorsal = PRIMER_DORSAL; dorsal <= ULTIMO_DORSAL; dorsal++) {
			correcto = equipo.sumaAsistencia(dorsal);
			assertTrue(correcto);
		}
	}	

	@Test
	void testSumaPuntosAnotadosSimple() {
		System.out.println("testSumaPuntosAnotadosSimple");
		final int dorsal3 = 3;
		Equipo equipo = new Equipo();
		Jugador jugador;

		// comprueba que los jugadores comienzan con 0 de valoracion
		jugador = equipo.mejorJugador();
		assertEquals(0, jugador.puntos());
		assertEquals(0,  jugador.asistencias());
		assertEquals(0.0, jugador.valoracion());

		// anota puntos para un jugador y comprueba que se anotan bien
		equipo.sumaPuntosAnotados(dorsal3, 2);
		jugador = equipo.mejorJugador();
		assertEquals(2, jugador.puntos());
		assertEquals(0,  jugador.asistencias());
		assertEquals(2.0, jugador.valoracion(), 0.001);
	}		

	@Test
	void testSumaAsistenciaSimple() {
		System.out.println("testSumaAsistenciaSimple");
		final int dorsal8 = 8;
		Equipo equipo = new Equipo();
		Jugador jugador;

		// comprueba que los jugadores comienzan con 0 de valoracion
		jugador = equipo.mejorJugador();
		assertEquals(0, jugador.puntos());
		assertEquals(0,  jugador.asistencias());
		assertEquals(0.0, jugador.valoracion());

		// anota puntos para un jugador y comprueba que se anotan bien
		equipo.sumaAsistencia(dorsal8);
		jugador = equipo.mejorJugador();
		assertEquals(0, jugador.puntos());
		assertEquals(1,  jugador.asistencias());
		assertEquals(VALORACION_POR_ASISTENCIA, jugador.valoracion(), 0.001);
	}		

	@Test
	void testValoracion() {
		System.out.println("testValoracion");
		final int dorsal7 = 7;
		final int dorsal4 = 4;
		Equipo equipo = new Equipo();
		Jugador jugador;

		// suma puntos a un jugador y comprueba que sus datos son correctos
		equipo.sumaPuntosAnotados(dorsal7, 2);
		jugador = equipo.mejorJugador();
		assertEquals(dorsal7, jugador.dorsal());
		assertEquals(2, jugador.puntos());
		assertEquals(0,  jugador.asistencias());
		assertEquals(2.0, jugador.valoracion(), 0.001);

		// suma puntos y asistencias a un jugador y comprueba que sus datos
		// son correctos
		equipo.sumaPuntosAnotados(dorsal4, 3);
		equipo.sumaPuntosAnotados(dorsal4, 2);
		equipo.sumaAsistencia(dorsal4);
		equipo.sumaAsistencia(dorsal4);
		equipo.sumaAsistencia(dorsal4);
		jugador = equipo.mejorJugador();
		assertEquals(dorsal4, jugador.dorsal());
		assertEquals(5, jugador.puntos());
		assertEquals(3,  jugador.asistencias());
		assertEquals(9.5, jugador.valoracion(), 0.001);
	}

	@Test
	void testMejorJugador() {
		System.out.println("testMejorJugador");
		final int dorsalMitad = ULTIMO_DORSAL / 2;
		Equipo equipo = new Equipo();
		Jugador jugador;

		// suma puntos a un jugador y comprueba que es el mejor
		equipo.sumaPuntosAnotados(dorsalMitad, 2);
		jugador = equipo.mejorJugador();
		assertEquals(dorsalMitad, jugador.dorsal());

		// suma una asistencia a otro jugador comprueba que el mejor sigue
		// siendo el que tiene 2 puntos
		equipo.sumaAsistencia(PRIMER_DORSAL);
		jugador = equipo.mejorJugador();
		assertEquals(dorsalMitad, jugador.dorsal());

		// suma otra asistencia y comprueba que pasa a ser el mejor
		equipo.sumaAsistencia(PRIMER_DORSAL);
		jugador = equipo.mejorJugador();
		assertEquals(PRIMER_DORSAL, jugador.dorsal());

		// suma 4 puntos a otro jugador y comprueba que pasa a ser el mejor
		equipo.sumaPuntosAnotados(ULTIMO_DORSAL, 2);
		equipo.sumaPuntosAnotados(ULTIMO_DORSAL, 2);
		jugador = equipo.mejorJugador();
		assertEquals(ULTIMO_DORSAL, jugador.dorsal());
	}

	@Test
	void testPuntosEquipo() {
		System.out.println("testPuntosEquipo");
		Equipo equipo = new Equipo();

		// comprueba que el equipo empieza con 0 puntos
		assertEquals(0, equipo.puntos());

		// comprueba que se suman los puntos de todos los jugadores
		for (int dorsal = PRIMER_DORSAL; dorsal <= ULTIMO_DORSAL; dorsal++) {
			equipo.sumaPuntosAnotados(dorsal, 2);
		}
		assertEquals(2 * NUM_JUGADORES, equipo.puntos());

		// comprueba que se acumulan bien los puntos 
		for (int dorsal = PRIMER_DORSAL; dorsal <= ULTIMO_DORSAL; dorsal++) {
			equipo.sumaPuntosAnotados(dorsal, 3);
		}
		assertEquals((2 + 3) * NUM_JUGADORES, equipo.puntos());
	}

}
