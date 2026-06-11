package examen.gui;

import java.io.FileNotFoundException;

import examen.modelo.Juego;
import examen.modelo.Juego.PersonajeNoExiste;
import examen.modelo.Juego.TipoNoValido;
import examen.modelo.Juego.YaMuerto;
import examen.modelo.Personaje;
import fundamentos_test.*;

/**
 * Interfaz Grafica de Usuario (GUI) de la aplicacion de prueba de los
 * ataques entre personajes de un juego multijugador.
 * 
 * @author  Metodos de Programacion (UC)
 * @version may-24
 */
public class GuiPruebaPersonajes {
	// opciones del menu
	public static final int REALIZA_ATAQUE = 0;
	public static final int RECARGA_ENERGIA = 1;
	public static final int CONSULTA_VIDA = 2;
	public static final int CONSULTA_ELIMINADOR = 3;
	public static final int FIN_APLICACION = 4;

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del programa principal (no usados)
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {

		// variables auxiliares
		Lectura lect;
		String nombre;

		Juego juego = null;
		// crea el juego
		try {
			juego = new Juego("src/examen/personajes.txt");
		} catch (FileNotFoundException e) {
			System.exit(-1);
		}

		// crea la ventana de menu
		Menu menu = FundamentosFactory.getMenu("Prueba personajes");
		menu.insertaOpcion("Realiza ataque", REALIZA_ATAQUE);
		menu.insertaOpcion("Recarga energia mago", RECARGA_ENERGIA);
		menu.insertaOpcion("Consulta vida", CONSULTA_VIDA);
		menu.insertaOpcion("Consula eliminador", CONSULTA_ELIMINADOR);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();
			if (opcion == FIN_APLICACION) {
				break;
			}

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case REALIZA_ATAQUE:
				lect = FundamentosFactory.getLectura("Realiza ataque");
				lect.creaEntrada("Nombre atacante", "Galadriel");
				lect.creaEntrada("Nombre atacado", "Conan");
				lect.esperaYCierra();
				String nombreAtacante = lect.leeString("Nombre atacante");
				String nombreAtacado = lect.leeString("Nombre atacado");

				// realiza el ataque entre los personajes
				try {
					juego.realizaAtaque(nombreAtacante, nombreAtacado);
					mensaje("Ataque realizado",
							"Realizado ataque de " + nombreAtacante +
							" a " +	nombreAtacado);
				} catch (PersonajeNoExiste e) {
					mensaje("ERROR", "Alguno de los nombres no corresponde " +
							"a ningun personaje");
				} catch (YaMuerto e) {
					mensaje("ERROR", "Alguno de los personajes ya ha sido " +
							"eliminado");
				}
				break;

			case RECARGA_ENERGIA:
				lect = FundamentosFactory.getLectura("Recarga energia");
				lect.creaEntrada("Nombre mago", "Merlin");
				lect.creaEntrada("Puntos recarga", 5);
				lect.esperaYCierra();
				nombre  = lect.leeString("Nombre mago");
				int puntosRecarga  = lect.leeInt("Puntos recarga");

				// Recarga la energia del mago
				try {
					juego.recargaEnergiaMago(nombre, puntosRecarga);
					mensaje("Energia recargada",
							"Recargados " + puntosRecarga + " puntos de " + 
									"energia a " + nombre);
				} catch (PersonajeNoExiste e) {
					mensaje("ERROR", "El nombre " + nombre +
							" no corresponde a ningun personaje");
				} catch (YaMuerto e) {
					mensaje("ERROR", "El personaje " + nombre + 
							" ya ha sido eliminado");
				} catch (TipoNoValido e) {
					mensaje("ERROR", "El personaje " + nombre +
							" no es mago");
				}
				break;

			case CONSULTA_VIDA:
				lect = FundamentosFactory.getLectura("Consulta vida personaje");
				lect.creaEntrada("Nombre personaje", "Conan");
				lect.esperaYCierra();
				nombre  = lect.leeString("Nombre personaje");

				// Muestra la vida del personaje
				Personaje p = juego.buscaPersonaje(nombre);
				if (p == null) {
					mensaje("ERROR", "El nombre " + nombre +
							" no corresponde a ningun personaje");
				} else {
					int vida = (int) p.puntosVida();
					if (vida < 0) {
						vida = 0;
					}
					mensaje("Vida personaje",
							"Personaje: " + nombre + ". Vida: " + 
									vida);
				}
				break;

			case CONSULTA_ELIMINADOR:
				lect = FundamentosFactory.getLectura("Consulta eliminador");
				lect.creaEntrada("Nombre personaje", "Conan");
				lect.esperaYCierra();
				nombre  = lect.leeString("Nombre personaje");

				// Muestra el personaje eliminador
				Personaje p2 = juego.buscaPersonaje(nombre);
				if (p2 == null) {
					mensaje("ERROR", "El nombre " + nombre +
							" no corresponde a ningun personaje");
				} else if (p2.eliminador() != null) {
					mensaje("Personaje eliminado",
							"El personaje " + nombre +
							" ha sido eliminado por: " +
							p2.eliminador().nombre());
				} else {
					mensaje("Personaje no eliminado",
							"El personaje " + nombre +
							" no ha sido eliminado aun.");
				}
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
		Mensaje msj = FundamentosFactory.getMensaje(titulo);
		msj.escribe(txt);
	}

}
