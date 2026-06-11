package gui;

import fundamentos.Lectura;
import fundamentos.Mensaje;
import fundamentos.Menu;

public class GUI {
	public static final int ANHANDE = 0;
	public static final int OBTEN_PRECIO = 1;
	public static final int VEHICULO_PAS_MAS_BARATO = 2;
	public static final int VEHICULO_MAYOR_PMA = 3;
	public static void main(String[] args) {
		Menu menu = new Menu("Alquiler vehiculos");
		menu.insertaOpcion("Anhade vehiculo", ANHANDE);
		menu.insertaOpcion("Precio alquiler", OBTEN_PRECIO);
		menu.insertaOpcion("Vehiculo pasajeros barato", VEHICULO_PAS_MAS_BARATO);
		menu.insertaOpcion("Vehiculo mayor pma", VEHICULO_MAYOR_PMA);
		Lectura lect;
		int opcion;
		Empresa e;
		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case ANHANDE:
				lect = new Lectura("Nueva tarjeta");
				lect.creaEntrada("DNI", "12345678A");
				lect.esperaYCierra();
				break;

			case OBTEN_PRECIO:
				lect = new Lectura("Datos Compra");
				lect.creaEntrada("Codigo tarjeta", "TAR1");
				lect.creaEntrada("Producto", "Botas");
				lect.creaEntrada("Importe", "22.0");
				lect.esperaYCierra();
				break;

			case VEHICULO_PAS_MAS_BARATO:
				lect = new Lectura("Datos Tarjeta");
				lect.creaEntrada("Codigo tarjeta", "TAR1");
				lect.esperaYCierra();
				break;

			case VEHICULO_MAYOR_PMA:
				lect = new Lectura("Informacion compra");
				lect.creaEntrada("Codigo tarjeta", "TAR1");
				lect.creaEntrada("Pos. compra", "0");
				lect.esperaYCierra();
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


