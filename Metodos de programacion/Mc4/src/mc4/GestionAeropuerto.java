package mc4;

import fundamentos.*;

/**
 * Gestion de la asignacion de vuelos a las pistas de aterrizaje de un aeropuerto
 * @author MP
 * @version mar-14
 */
public class GestionAeropuerto {

	/**
	 * Programa principal basado en menu
	 * @param args a
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int VUELO_SOLICITA_PISTA = 0, VUELO_ATERRIZADO = 1, MUESTRA_REGISTRO_VUELOS = 2;

		// variables auxiliares
		String codVuelo;
		int idPista;
		Lectura lect;

		// crea el aeropuerto
		Aeropuerto sdr = new Aeropuerto(2);

		// crea la ventana de menú
		Menu menu = new Menu("Aeropuerto");
		menu.insertaOpcion("Vuelo solicita pista", VUELO_SOLICITA_PISTA);
		menu.insertaOpcion("Vuelo aterrizado", VUELO_ATERRIZADO);
		menu.insertaOpcion("Muesra registro vuelos", MUESTRA_REGISTRO_VUELOS);
		int opcion;

		// lazo de espera de comandos del usuario
		while(true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opción elegida
			switch (opcion) {
			case VUELO_SOLICITA_PISTA:
				lect = new Lectura("Codigo vuelo");
				lect.creaEntrada("Codigo", "");
				lect.esperaYCierra();
				codVuelo = lect.leeString("Codigo");
				idPista = sdr.asignaVueloPista(codVuelo);
				if (idPista == -1) {
					mensaje("ERROR", "No se pudo asignar");
					break;
				}
				mensaje("VUELO " + codVuelo + " ASIGNADO", "Pista:" + idPista);
				break;

			case VUELO_ATERRIZADO:
				lect = new Lectura("Aterriza vuelo");
				lect.creaEntrada("Codigo", "");
				lect.creaEntrada("Id pista", 0);
				lect.esperaYCierra();
				codVuelo = lect.leeString("Codigo");
				idPista = lect.leeInt("Id pista");
				if (!sdr.aterrizaVueloPista(idPista, codVuelo)) {
					mensaje("ERROR", "No se pudo aterrizar");
					break;
				}
				mensaje("VUELO" + codVuelo + "ATERRIZADO", "Ha aterrizado en la pista:" + idPista);
				break;

			case MUESTRA_REGISTRO_VUELOS:
				mensaje("REGISTRO", sdr.toString());
				break;
			}
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);
	}

}
