package pract05.gui;

import fundamentos.*;
import pract05.modelo.CadenaTiendas;
import pract05.modelo.Compra;
import pract05.modelo.Tarjeta;

/**
 * Interfaz Gráfica de Usuario (GUI) de la aplicación para la gestion de las
 * tarjetas de fidelidad de una cadena de tiendas.
 * 
 * @author  Metodos de Programacion (UC)
 * @version mar-22
 */
public class GUIGestionTarjetas {

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del programa principal (no usados)
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int ANHADE_TARJETA = 0;
		final int NUEVA_COMPRA = 1;
		final int DATOS_TARJETA = 2;
		final int INFO_COMPRA = 3;

		// variables auxiliares
		String dni;
		String codigo;
		Lectura lect;
		boolean correcto;

		// crea la tienda
		CadenaTiendas cadenaTiendas = new CadenaTiendas();

		// crea la ventana de menu
		Menu menu = new Menu("Tarjetas fidelidad");
		menu.insertaOpcion("Anhade tarjeta", ANHADE_TARJETA);
		menu.insertaOpcion("Nueva compra", NUEVA_COMPRA);
		menu.insertaOpcion("Datos tarjeta", DATOS_TARJETA);
		menu.insertaOpcion("Info compra", INFO_COMPRA);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case ANHADE_TARJETA:
				lect = new Lectura("Nueva tarjeta");
				lect.creaEntrada("DNI", "12345678A");
				lect.esperaYCierra();
				dni = lect.leeString("DNI");

				// regitra la tarjeta
				cadenaTiendas.anhadeTarjeta(new Tarjeta(dni));
				break;

			case NUEVA_COMPRA:
				lect = new Lectura("Datos Compra");
				lect.creaEntrada("Codigo tarjeta", "TAR1");
				lect.creaEntrada("Producto", "Botas");
				lect.creaEntrada("Importe", "22.0");
				lect.esperaYCierra();
				codigo = lect.leeString("Codigo tarjeta");
				String producto = lect.leeString("Producto");
				double importe = lect.leeDouble("Importe");

				// registra la compra
				correcto = cadenaTiendas.registraCompra(codigo,
						new Compra(producto, importe));
				if (!correcto) {
					mensaje("ERROR", "No hay ninguna tarjeta con ese codigo");
				}
				break;

			case DATOS_TARJETA:
				lect = new Lectura("Datos Tarjeta");
				lect.creaEntrada("Codigo tarjeta", "TAR1");
				lect.esperaYCierra();
				codigo = lect.leeString("Codigo tarjeta");

				// busca la tarjeta y muestra sus datos
				Tarjeta tarjeta = cadenaTiendas.buscaTarjeta(codigo);
				if (tarjeta == null) {
					mensaje("ERROR", "No existe ninguna tarjeta con ese DNI");
				} else {
					mensaje("Datos tarjeta",
							"Codigo:" + tarjeta.codigo() +
							"\nDNI:" + tarjeta.dni() +
							"\nPuntos:" + tarjeta.puntosAcumulados());
				}
				break;

			case INFO_COMPRA:
				lect = new Lectura("Informacion compra");
				lect.creaEntrada("Codigo tarjeta", "TAR1");
				lect.creaEntrada("Pos. compra", "0");
				lect.esperaYCierra();
				codigo = lect.leeString("Codigo tarjeta");
				int posCompra = lect.leeInt("Pos. compra");

				// muestra la informacion sobre la compra
				Compra compra = cadenaTiendas.buscaCompraDeTarjeta(codigo, posCompra);
				if (compra == null) {
					mensaje("ERROR", "No existe ninguna tarjeta con ese Codigo o" +
							"\nla posicion de la compra no es valida.");
				} else {
					mensaje("Informacion compra",
							"Producto:" + compra.producto() + 
							"\nImporte:" + compra.importe());
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
