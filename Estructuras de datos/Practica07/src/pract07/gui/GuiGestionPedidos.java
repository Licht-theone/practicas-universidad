package pract07.gui;

import java.io.FileNotFoundException;

import fundamentos.*;
import pract07.modelo.*;

/**
 * Interfaz Grafica de Usuario (GUI) de la aplicacion que gestiona
 * los pedidos y productos de una empresa.
 * 
 * @author Estructuras de Datos (UC)
 * @version oct-23
 */
public class GuiGestionPedidos {

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del programa (no usados).
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int REGISTRA_PEDIDO = 0;
		final int PROCESA_SIGUIENTE_PEDIDO = 1;
		final int DESCATALOGA_PRODUCTOS = 2;
		final int CONSULTA_PRODUCTO_DESCATALOGADO = 3;
		final String NOM_FICH_DATOS_PRODUCTOS =
				"src/pract07/datos_productos.txt";

		// variables auxiliares
		String codProducto;
		Lectura lect;

		// crea la empresa
		Empresa empresa = null;
		try {
			empresa = new Empresa(NOM_FICH_DATOS_PRODUCTOS);
		} catch (FileNotFoundException e1) {
			mensaje("Error fatal",
					"No se pudo leer el fichero " + NOM_FICH_DATOS_PRODUCTOS);
			System.exit(-1);				
		}

		// crea la ventana de menú
		Menu menu = new Menu("Gestion Pedidos");
		menu.insertaOpcion("Registar pedido", REGISTRA_PEDIDO);
		menu.insertaOpcion("Procesa siguiente pedido", PROCESA_SIGUIENTE_PEDIDO);
		menu.insertaOpcion("Descataloga productos", DESCATALOGA_PRODUCTOS);
		menu.insertaOpcion("Consulta producto descatalogado", CONSULTA_PRODUCTO_DESCATALOGADO);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opción elegida
			switch (opcion) {
			case REGISTRA_PEDIDO:
				final int unidadesPedidasPorDefecto = 12;
				lect = new Lectura("Registra pedido");
				lect.creaEntrada("Codigo producto", "A1");
				lect.creaEntrada("Unidades pedidas", unidadesPedidasPorDefecto);
				lect.esperaYCierra();
				codProducto = lect.leeString("Codigo producto");
				int numUnidades = lect.leeInt("Unidades pedidas");

				// Registra el pedido en la empresa
				try {
					empresa.registraPedido(codProducto, numUnidades);
				} catch (Empresa.CodigoProductoIncorrecto e) {
					mensaje("ERROR", "No exite ningun producto con el codigo indicado");
				} catch (Empresa.CantidadInsuficiente e) {
					mensaje("ERROR", "No hay suficientes unidades del producto solicitado");
				}
				break;

			case PROCESA_SIGUIENTE_PEDIDO:
				// Procesa el pedido pendiente más antiguo
				try {
					Pedido pedido = empresa.procesaSiguientePedido();
					mensaje("Pedido procesado",
							"Codigo:" + pedido.producto().codigo() +
							"\nNum unidades:" + pedido.numUnidades());
				} catch (Empresa.NoHayPedidosPendientes e) {
					mensaje("ERROR", "No hay ningun pedido pendiente para ser procesado");
				} catch (Empresa.CantidadInsuficiente e) {
					mensaje("ERROR", "No hay suficientes unidades del producto solicitado." +
							"\nEl pedido ha sido descartado.");
				}
				break;

			case DESCATALOGA_PRODUCTOS:
				final int umbralUnidadesPorDefecto = 10;
				lect = new Lectura("Descataloga productos");
				lect.creaEntrada("Umbral unidades", umbralUnidadesPorDefecto);
				lect.esperaYCierra();
				int umbralUnidades = lect.leeInt("Umbral unidades");

				// descataloga los productos con un numero de unidades disponibles inferior
				// al umbral
				empresa.descatalogaProductos(umbralUnidades);
				break;

			case CONSULTA_PRODUCTO_DESCATALOGADO:
				lect = new Lectura("Consulta producto descatalogado");
				lect.creaEntrada("Posicion en registro (hay " + 
						empresa.numProductosDescatalogados() + ")", 0);
				lect.esperaYCierra();
				int posicion = lect.leeInt("Posicion en registro (hay " + 
						empresa.numProductosDescatalogados() + ")");

				try {
					Producto producto = empresa.productoDescatalogado(posicion);
					mensaje("Producto descatalogado",
							"Codigo:" + producto.codigo() +
							"\nUnidades disponibles:" + producto.numUnidadesDisponibles());
				} catch (Empresa.PosicionIncorrecta e) {
					mensaje("ERROR",
							"No existe ningun producto descatalogado en la posicion indicada");
				}
				break;

			default:
				throw new AssertionError("Opcion no esperada");

			}
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje.
	 * @param titulo titulo de la ventana.
	 * @param txt texto contenido en la ventana.
	 */
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}
