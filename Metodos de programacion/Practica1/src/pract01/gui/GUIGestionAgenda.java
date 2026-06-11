package pract01.gui;

import fundamentos.*;
import pract01.modelo.Agenda;
import pract01.modelo.Contacto;

/**
 * Interfaz Gráfica de Usuario (GUI) de la aplicación de gestion
 * de una agenda de contactos y numeros de telefono.
 * 
 * @author Metodos de Programacion (UC)
 * @version sep-2023
 */
public class GUIGestionAgenda {

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del programa principal (no usados)
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int ANHADE_CONTACTO = 0;
		final int CAMBIA_TLF = 1;
		final int CAMBIA_EMAIL = 2;
		final int BUSCA_CONTACTO = 3;

		// variables auxiliares
		String nombre;
		String tlf;
		String email;
		Contacto c;
		Lectura lect;

		// crea la agenda
		Agenda agenda = new Agenda();

		// crea la ventana de menu
		Menu menu = new Menu("Agenda");
		menu.insertaOpcion("Anhade Contacto", ANHADE_CONTACTO);
		menu.insertaOpcion("Cambia tlf", CAMBIA_TLF);
		menu.insertaOpcion("Cambia e-mail", CAMBIA_EMAIL);
		menu.insertaOpcion("Busca contacto", BUSCA_CONTACTO);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opción elegida
			switch (opcion) {
			case ANHADE_CONTACTO:
				lect = new Lectura("Datos Contacto");
				lect.creaEntrada("Nombre", "Pepe");
				lect.creaEntrada("Tlf", "1234567890");
				lect.creaEntrada("e-mail", "pepe@gmail.es");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre");
				tlf = lect.leeString("Tlf");
				email = lect.leeString("e-mail");
				
				c = new Contacto(nombre, tlf, email);
				if (!agenda.anhadeContacto(c)) {
					mensaje("ERROR", "Ya existe un contacto con ese nombre");
				} else {
					mensaje("Contacto anhadido",
							"El contacto " + nombre + " ha sido anhadido.");
				}
				break;

			case CAMBIA_TLF:
				lect = new Lectura("Cambia tlf");
				lect.creaEntrada("Nombre", "Pepe");
				lect.creaEntrada("Tlf", "555123456");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre");
				tlf = lect.leeString("Tlf");
				
				if (!agenda.cambiaTlfContacto(nombre, tlf)) {
					mensaje("ERROR", "No existe ningún contacto con el nombre indicado");
				} else {
					mensaje("Telefono cambiado",
							"El nuevo telefono de " + nombre + " es " + tlf);
				}
				break;

			case CAMBIA_EMAIL:
				lect = new Lectura("Cambia e-mail");
				lect.creaEntrada("Nombre", "Pepe");
				lect.creaEntrada("e-mail", "pepe@hotmail.es");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre");
				email = lect.leeString("e-mail");
				
				if (!agenda.cambiaEmailContacto(nombre, email)) {
					mensaje("ERROR", "No existe ningún contacto con el nombre indicado");
				} else {
					mensaje("e-mail cambiado",
							"El nuevo e-mail de " + nombre + " es " + email);
				}
				break;

			case BUSCA_CONTACTO:
				lect = new Lectura("Busca contacto");
				lect.creaEntrada("Nombre", "Pepe");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre");
				
				c = agenda.buscaContacto(nombre);
				if (c == null) {
					mensaje("ERROR",
							"No existe ningún contacto con el nombre indicado");					
				} else {
					mensaje("Datos contacto", "Nombre:" + c.nombre() +
							"\nTlf:" + c.tlf() +
							"\ne-mail:" + c.email());
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
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}
