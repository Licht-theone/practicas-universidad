package mc3;

import fundamentos.*;

/**
 * Gestion de una inmobiliaria
 * @author MP
 * @version feb-14
 */
public class GUImc3 {

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int AÑADE_PISO = 0, AÑADE_AGENTE = 1, ASIGNA_PISO = 2,
				PISO_VENDIDO = 3, DATOS_AGENTE= 4;

		// variables auxiliares
		String ref;
		String dni;
		Lectura lect;

		// crea la inmobiliaria
		Inmo keli = new Inmo(20);

		// crea la ventana de menu
		Menu menu = new Menu("Inmobiliaria");
		menu.insertaOpcion("Añade piso", AÑADE_PISO);
		menu.insertaOpcion("Añade agente", AÑADE_AGENTE);
		menu.insertaOpcion("Asigna piso a agente", ASIGNA_PISO);
		menu.insertaOpcion("Piso vendido", PISO_VENDIDO);
		menu.insertaOpcion("Datos Agente", DATOS_AGENTE);

		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opciÃ³n elegida
			switch (opcion) {
			case  AÑADE_PISO:
				lect = new Lectura("Datos Piso");
				lect.creaEntrada("Precio", 0);
				lect.creaEntrada("Superficie", 0);
				lect.creaEntrada("Habitaciones", 1);
				lect.creaEntrada("Baños", 1);
				lect.esperaYCierra();
				int precio = lect.leeInt("Precio");
				int superficie = lect.leeInt("Superficie");
				int habitaciones = lect.leeInt("Habitaciones");
				int banos = lect.leeInt("Baños");
				Piso p = new Piso(precio, superficie, habitaciones, banos);
				ref = keli.anhadePiso(p);
				mensaje("Piso ref:" + ref, "Añadido correctamente");
				break;

			case AÑADE_AGENTE:
				lect = new Lectura("Datos agente");
				lect.creaEntrada("Nombre", "");
				lect.creaEntrada("DNI", "");
				lect.esperaYCierra();
				String nombre = lect.leeString("Nombre");
				dni = lect.leeString("DNI");
				Agente ag = new Agente(nombre, dni);
				if (!keli.anhadeAgente(ag)) {
					mensaje("ERROR", "Ese agente ya existe");
					break;
				}
				mensaje("Agente con dni" + dni, "Añadido correctamente");
				break;

			case ASIGNA_PISO:
				lect = new Lectura("Asigna Piso");
				lect.creaEntrada("Ref Piso", "");
				lect.creaEntrada("DNI", "");
				lect.esperaYCierra();
				ref = lect.leeString("Ref Piso");
				dni = lect.leeString("DNI");
				if (!keli.asignaPiso(ref, dni)) {
					mensaje("Error", "No se pudo asignar");
					break;
				}
				mensaje("Piso:" + ref, "Añadido al agente:" + dni);
				break;

			case PISO_VENDIDO:
				lect = new Lectura("Venta piso");
				lect.creaEntrada("Ref Piso", "");
				lect.esperaYCierra();
				ref = lect.leeString("Ref Piso");
				if (!keli.vendePiso(ref)) {
					mensaje("Error", "No se pudo vender");
					break;
				}
				mensaje("Venta exitosa", "Piso vendido:" + ref);
				break;

			case DATOS_AGENTE:
				lect = new Lectura("DNI Agente");
				lect.creaEntrada("DNI", "");
				lect.esperaYCierra();
				dni = lect.leeString("DNI");
				Agente agbs = keli.buscaAgente(dni);
				if (agbs == null) {
					mensaje("ERROR", "No encontrado");
					break;
				}
				mensaje("Agente", agbs.toString());
				break;
			default:
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

