package pract01;

import fundamentos.*;

/**
 * Gestion de una agenda de contactos y numeros de telefono
 * @author MP
 * @version feb-14
 */
public class GestionAgenda {

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int NUEVO_CONTACTO = 0, CAMBIA_TLF = 1, CAMBIA_EMAIL = 2,
		BUSCA_CONTACTO = 3;

		// variables auxiliares
		String nombre;
		String tlf;
		String email;
		Lectura lect;

		// crea la agenda
		// TODO

		// crea la ventana de menú
		Menu menu = new Menu("Agenda");
		menu.insertaOpcion("Nuevo Contacto", NUEVO_CONTACTO);
		menu.insertaOpcion("Cambia tlf", CAMBIA_TLF);
		menu.insertaOpcion("Cambia e-mail", CAMBIA_EMAIL);
		menu.insertaOpcion("Busca contacto", BUSCA_CONTACTO);
		int opcion;

		// lazo de espera de comandos del usuario
		while(true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opción elegida
			switch (opcion) {
			case NUEVO_CONTACTO:
				lect = new Lectura("Datos Contacto");
				lect.creaEntrada("Nombre", "");
				lect.creaEntrada("Tlf", "");
				lect.creaEntrada("e-mail", "");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre");
				tlf = lect.leeString("Tlf");
				email = lect.leeString("e-mail");
				// TODO
				break;

			case CAMBIA_TLF:
				lect = new Lectura("Cambia tlf");
				lect.creaEntrada("Nombre", "");
				lect.creaEntrada("Tlf", "");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre");
				tlf = lect.leeString("Tlf");
				// TODO
				break;

			case CAMBIA_EMAIL:
				lect = new Lectura("Cambia e-mail");
				lect.creaEntrada("Nombre", "");
				lect.creaEntrada("e-mail", "");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre");
				email = lect.leeString("e-mail");
				// TODO
				break;

			case BUSCA_CONTACTO:
				lect = new Lectura("Busca contacto");
				lect.creaEntrada("Nombre", "");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre");
				// TODO
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
