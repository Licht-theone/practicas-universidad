package pract07.gui;

import fundamentos_test.*;
import pract07.modelo.Tienda;
import pract07.modelo.Tienda.NombreVendedorIncorrecto;
import pract07.modelo.Tienda.NombreYaExistente;
import pract07.modelo.Vendedor;
/**
 * Interfaz Gráfica de Usuario (GUI) de la aplicación para la gestion de las
 * comisiones de los vendedores de una tienda.
 * 
 * @author  Metodos de Programacion (UC)
 * @version abr-22
 */

public class GUIGestionComisiones {
	// opciones del menu
	public static final int NUEVO_VENDEDOR = 0;
	public static final int NUEVA_VENTA = 1;
	public static final int DATOS_VENDEDOR = 2;
	public static final int RANKING_VENDEDOR = 3;
	public static final int ELIMINA_VENDEDOR = 4;
	public static final int FIN_APLICACION = 5;

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del programa principal (no usados)
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {

		// variables auxiliares
		String nombre;
		Lectura lect;

		// crea la tienda
		Tienda tienda = new Tienda();

		// crea la ventana de menu
		Menu menu = FundamentosFactory.getMenu("Comisiones tienda");
		menu.insertaOpcion("Nuevo Vendedor", NUEVO_VENDEDOR);
		menu.insertaOpcion("Nueva venta", NUEVA_VENTA);
		menu.insertaOpcion("Datos Vendedor", DATOS_VENDEDOR);
		menu.insertaOpcion("Ranking vendedor", RANKING_VENDEDOR);
		menu.insertaOpcion("Elimina vendedor", ELIMINA_VENDEDOR);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();
			if (opcion == FIN_APLICACION) {
				break;
			}

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case NUEVO_VENDEDOR:
				lect = FundamentosFactory.getLectura("Nuevo Vendedor");
				lect.creaEntrada("Nombre", "Pepe");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre");
				// anhade el vendedor a la tienda
				try {
					tienda.anhadeVendedor(new Vendedor(nombre));
				} catch (NombreYaExistente e) {
					mensaje("ERROR", "Ya existe el vendedor");
					lect = FundamentosFactory.getLectura("Nuevo Vendedor");
					lect.creaEntrada("Nombre", "Pepe");
					lect.esperaYCierra();
					nombre = lect.leeString("Nombre");
					try {
						tienda.anhadeVendedor(new Vendedor(nombre));
					} catch (NombreYaExistente e2) {
						mensaje("ERROR", "Ya existe el vendedor");
						lect = FundamentosFactory.getLectura("Nuevo Vendedor");
						lect.creaEntrada("Nombre", "Pepe");
						lect.esperaYCierra();
						nombre = lect.leeString("Nombre");
						try {
							tienda.anhadeVendedor(new Vendedor(nombre));
						} catch (NombreYaExistente e3) {
							mensaje("ERROR", "Ya existe el vendedor");
							mensaje("Operacion cancelada", 
									"Alcanzado el numero maximo de intentos");
							break;
						}
					}
				}
				// do while tmb se puede
				mensaje("Vendedor anhadido", nombre + " anhadido correctamente");
				break;

			case NUEVA_VENTA:
				lect = FundamentosFactory.getLectura("Datos Venta");
				lect.creaEntrada("Nombre Vendedor", "Pepe");
				lect.creaEntrada("Importe", "100.0");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre Vendedor");
				double importe = lect.leeDouble("Importe");
				boolean ventaCorrecta = false;
				// anhade la venta al vendedor
				do {
					try {
						tienda.anhadeVenta(nombre, importe);
						if (importe <= 0) {
							mensaje("ERROR", "El importe debe ser mayor que 0");
							lect = FundamentosFactory.getLectura("Datos Venta");
							lect.creaEntrada("Nombre Vendedor", "Pepe");
							lect.creaEntrada("Importe", "100.0");
							lect.esperaYCierra();
							nombre = lect.leeString("Nombre Vendedor");
							importe = lect.leeDouble("Importe");
						} else {
							mensaje("Venta anhadida", "Venta anhadida a " + nombre);
							ventaCorrecta = true;
						}
					} catch (NombreVendedorIncorrecto e) {
						mensaje("ERROR", "No existe el vendedor " + nombre);
						lect = FundamentosFactory.getLectura("Datos Venta");
						lect.creaEntrada("Nombre Vendedor", "Pepe");
						lect.creaEntrada("Importe", "100.0");
						lect.esperaYCierra();
						nombre = lect.leeString("Nombre Vendedor");
						importe = lect.leeDouble("Importe");
					}
					
				} while (!ventaCorrecta);
				break;

			case DATOS_VENDEDOR:
				lect = FundamentosFactory.getLectura("Datos Vendedor");
				lect.creaEntrada("Nombre Vendedor", "Pepe");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre Vendedor");

				// busca el vendedor y muestra sus datos
				Vendedor vendedor = tienda.buscaVendedor(nombre);
				if (vendedor == null) {
					mensaje("ERROR", "No existe el vendedor " + nombre);
				} else {
					mensaje("Datos vendedor",
							"Nombre:" + vendedor.nombre() +
							" Comision: " + vendedor.comisionAcumulada());
				}
				break;

			case RANKING_VENDEDOR:
				lect = FundamentosFactory.getLectura("Ranking Vendedor");
				lect.creaEntrada("Nombre Vendedor", "Pepe");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre Vendedor");
				int posRanking = 0;
				// muestra la posicion en el ranking del vendedor
				try {
					posRanking = tienda.posRankingVendedor(nombre);
				} catch (NombreVendedorIncorrecto e) {
					mensaje("ERROR", "No existe el vendedor " + nombre);
					break;
				}
				mensaje("Ranking vendedor",
						"Nombre:" + nombre +
						" Posicion Ranking: " + posRanking);
				break;

			case ELIMINA_VENDEDOR:
				lect = FundamentosFactory.getLectura("Elimina Vendedor");
				lect.creaEntrada("Nombre Vendedor", "Pepe");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre Vendedor");
				Vendedor v = null;
				// elimina el vendedor
				try {
					v = tienda.eliminaVendedor(nombre);
				} catch (NombreVendedorIncorrecto e) {
					mensaje("ERROR", "No existe el vendedor " + nombre);
					break;
				}
				mensaje("Vendedor eliminado",
						"Eliminado " + v.nombre());
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
