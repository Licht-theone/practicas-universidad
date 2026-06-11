package mc20;

import fundamentos.*;
import mc20.Plataforma.MaxPeliculasDescargadas;
import mc20.Plataforma.NoExisteUsuario;
import mc20.Plataforma.PeliculaNoDescargada;

/**
 * Interfaz Grafica de Usuario (GUI) de la aplicacion para la gestion de
 * una plataforma de descarga de peliculas.
 * 
 * @author  Metodos de Programacion (UC) y <TODO: nombre alumno>
 * @version may-20
 */
public class GUIGestionDescargaPeliculas {

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del main (no usados)
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int DESCARGA_PELICULA = 0;
		final int FINALIZA_DESCARGA = 1;
		final int CONSULTA_PAGO = 2;

		// variables auxiliares
		String dniUsuario = null;
		String idPelicula = null;
		Lectura lect;
		Plataforma utorrent = new Plataforma();
		// crea la plataforma de descarga de peliculas
		// TODO

		// crea la ventana de menu
		Menu menu = new Menu("Gestion descarga peliculas");
		menu.insertaOpcion("Descarga pelicula", DESCARGA_PELICULA);
		menu.insertaOpcion("Finaliza descarga", FINALIZA_DESCARGA);
		menu.insertaOpcion("Consulta pago", CONSULTA_PAGO);

		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case DESCARGA_PELICULA:
				lect = new Lectura("Descarga pelicula");
				lect.creaEntrada("DNI usuario", "12345678A");
				lect.creaEntrada("Id pelicula", "P123");
				lect.esperaYCierra();
				dniUsuario = lect.leeString("DNI usuario");
				idPelicula = lect.leeString("Id pelicula");
				Pelicula p = new Pelicula("Peli", idPelicula);
				try {
					utorrent.descargaPeli(dniUsuario, p);
				} catch (NoExisteUsuario e) {
					mensaje("Error", "No existe usuario");
				} catch (MaxPeliculasDescargadas e1) {
					mensaje("Error", "No se pudo descargar");
				}
				break;

			case FINALIZA_DESCARGA:
				lect = new Lectura("Finaliza descarga");
				lect.creaEntrada("DNI usuario", "12345678A");
				lect.creaEntrada("Id pelicula", "P123");
				lect.esperaYCierra();
				dniUsuario = lect.leeString("DNI usuario");
				idPelicula = lect.leeString("Id pelicula");
				p = new Pelicula("Peli", idPelicula);
				try {
					utorrent.finalizaDescarga(dniUsuario, p);
				} catch (NoExisteUsuario e) {
					mensaje("Error", "No existe usuario");
				} catch (PeliculaNoDescargada e1) {
					mensaje("Error", "La pelicula no estaba descargada");
				}
				break;

			case CONSULTA_PAGO:
				lect = new Lectura("Consulta pago");
				lect.creaEntrada("DNI usuario", "12345678A");
				lect.esperaYCierra();
				dniUsuario = lect.leeString("DNI usuario");
				int cuota = 0;
				try {
					cuota = utorrent.cuotaUsu(dniUsuario);
				} catch (NoExisteUsuario e) {
					mensaje("Error", "No existe usuario");
				}
				mensaje("CUOTA", "La cuota fue de: " + cuota + "€");
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
