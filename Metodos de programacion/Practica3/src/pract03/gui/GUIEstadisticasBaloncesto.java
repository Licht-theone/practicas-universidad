package pract03.gui;

import fundamentos.*;
import pract03.modelo.Equipo;
import pract03.modelo.Jugador;

/**
 * Interfaz Gráfica de Usuario (GUI) para contabilizar las estadisticas de
 * anotacion y asistencias de los jugadores de un equipo de baloncesto.
 * 
 * @author Metodos de Programacion (UC)
 * @version sep-21
 */
public class GUIEstadisticasBaloncesto {

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del programa principal (no usados)
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int CONTABILIZA_PUNTOS = 0;
		final int CONTABILIZA_ASISTENCIA = 1;
		final int MUESTRA_MEJOR_JUGADOR = 2;
		final int VER_PUNTOS_EQUIPO = 3;

		// variables auxiliares
		int dorsal;
		int puntos;
		Lectura lect;
		Jugador jugador;
		boolean dorsalCorrecto;

		// crea el equipo
		Equipo equipo = new Equipo();

		// crea la ventana de menu
		Menu menu = new Menu("Estadisticas partido");
		menu.insertaOpcion("Contabiliza puntos", CONTABILIZA_PUNTOS);
		menu.insertaOpcion("Contabiliza asistencia", CONTABILIZA_ASISTENCIA);
		menu.insertaOpcion("Muestra mejor jugador", MUESTRA_MEJOR_JUGADOR);
		menu.insertaOpcion("Muestra puntos equipo", VER_PUNTOS_EQUIPO);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case CONTABILIZA_PUNTOS:
				lect = new Lectura("Contabiliza puntos");
				lect.creaEntrada("Dorsal", "1");
				lect.creaEntrada("Puntos", "2");
				lect.esperaYCierra();
				dorsal = lect.leeInt("Dorsal");
				puntos = lect.leeInt("Puntos");

				dorsalCorrecto = equipo.sumaPuntosAnotados(dorsal, puntos);
				if (!dorsalCorrecto) {
					mensaje("Error", "Dorsal incorrecto");
				}
				break;

			case CONTABILIZA_ASISTENCIA:
				lect = new Lectura("Contabiliza asitencia");
				lect.creaEntrada("Dorsal", "1");
				lect.esperaYCierra();
				dorsal = lect.leeInt("Dorsal");


				dorsalCorrecto = equipo.sumaAsistencia(dorsal);
				if (!dorsalCorrecto) {
					mensaje("Error", "Dorsal incorrecto");
				}
				break;

			case MUESTRA_MEJOR_JUGADOR:
				jugador = equipo.mejorJugador();
				mensaje("Mejor jugador", "Dorsal:" + jugador.dorsal() +
						" Puntos:" + jugador.puntos() +
						" Asistencias:" + jugador.asistencias());
				break;

			case VER_PUNTOS_EQUIPO:
				mensaje("Puntos del equipo:", "Puntos totales:" + equipo.puntos());
				break;

			default:
				throw new AssertionError("Opcion no esperada");
			}
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje.
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}
