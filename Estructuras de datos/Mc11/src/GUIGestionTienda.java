

import java.util.List;
import fundamentos.*;

/**
 * Interfaz Grafica de Usuario (GUI) del programa de gestion de  las compras
 * y reposiciones de productos en una tienda.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2018
 */
public class GUIGestionTienda {

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int ANHADE_PRODUCTO = 0, ANHADE_CLIENTE = 1, NUEVA_VENTA = 2, 
				ACTUALIZA_STOCK = 3, PRODUCTOS_RESERVA=4;

		// variables auxiliares
		String dni = "12345678A";
		Lectura lect;
		int unidades;
		int codigo = 0;

		// crea el registro con las tarjetas
		Tienda tienda = new Tienda();

		// crea la ventana de menú
		Menu menu = new Menu("Tienda");
		menu.insertaOpcion("Anhade producto", ANHADE_PRODUCTO);
		menu.insertaOpcion("Anhade cliente", ANHADE_CLIENTE);
		menu.insertaOpcion("Nueva venta", NUEVA_VENTA);
		menu.insertaOpcion("Actualiza stock", ACTUALIZA_STOCK);
		menu.insertaOpcion("Productos reserva", PRODUCTOS_RESERVA);

		int opcion;

		// lazo de espera de comandos del usuario
		while(true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opción elegida
			switch (opcion) {
			case ANHADE_PRODUCTO:
				lect = new Lectura("Datos producto");
				lect.creaEntrada("Descripcion", "Gafas");
				lect.creaEntrada("Unidades", 1);
				lect.esperaYCierra();
				String descripcion = lect.leeString("Descripcion");
				unidades = lect.leeInt("Unidades");

				codigo = tienda.anhadeProducto(new Producto(descripcion, unidades));
				mensaje("Producto", "Codigo: " + codigo);

				break;

			case ANHADE_CLIENTE:
				lect = new Lectura("Datos cliente");
				lect.creaEntrada("Nombre", "Lolo");
				lect.creaEntrada("DNI", dni);
				lect.esperaYCierra();
				dni = lect.leeString("DNI");
				String nombre = lect.leeString("Nombre");

				try  {
					tienda.anhadeCliente(new Cliente(nombre, dni));
				} catch (Tienda.ClienteYaExiste e) {
					mensaje("Error", "Ya existe un cliente con el DNI indicado");
				}

				break;

			case NUEVA_VENTA:
				lect = new Lectura("Datos venta");
				lect.creaEntrada("DNI cliente", dni);
				lect.creaEntrada("Codigo producto", codigo);
				lect.creaEntrada("Unidades", 1);
				lect.esperaYCierra();
				dni = lect.leeString("DNI cliente");
				codigo = lect.leeInt("Codigo producto");
				unidades = lect.leeInt("Unidades");

				try  {
					boolean ventaRealizada = tienda.nuevaVenta(codigo, dni, unidades);
					if (ventaRealizada) {
						mensaje("Venta realizada",
								"Cod. producto:" + codigo +
								"\nUnidades vendidas:" + unidades +
								"\nDNI cliente:" + dni);
					} else {
						mensaje("Creada reserva",
								"Cod. producto:" + codigo +
								"\nUnidades reservadas:" + unidades +
								"\nDNI cliente:" + dni);

					}
				} catch (Tienda.ClienteNoExiste e) {
					mensaje("Error", "No existe un cliente con el DNI indicado");	
				} catch (Tienda.ProductoNoExiste p) {
					mensaje("Error", "No existe el producto con el codigo indicado");	
				}
				break;


			case ACTUALIZA_STOCK:
				lect = new Lectura("Datos stock");
				lect.creaEntrada("Codigo producto", codigo);
				lect.creaEntrada("Unidades", 1);
				lect.esperaYCierra();
				codigo = lect.leeInt("Codigo producto");
				unidades = lect.leeInt("Unidades");

				try  {
					List<Reserva> reservas = tienda.actualizaStock(codigo, unidades);
					String reservasString = "";
					for(Reserva r: reservas) {
						reservasString = reservasString +
								"Cliente: " + r.getCliente().getNombre() +
								"  Unidades: " + r.getUnidades() + "\n"; 
					}
					mensaje("Reservas atendidas", reservasString);

				} catch (Tienda.ClienteNoExiste e) {
					mensaje("Error", "No existe un cliente con el DNI indicado");	
				} catch (Tienda.ProductoNoExiste p) {
					mensaje("Error", "No existe el producto con el codigo indicado");	
				}
				break;


			case PRODUCTOS_RESERVA:
				List<Producto> productos = tienda.productosConReserva();
				String productosString = "";
				for(Producto p: productos) {
					productosString = productosString +
							"Producto: " + p.getDescripcion() +
							"  Unidades reservadas: "+p.unidadesReservadas() + "\n" ; 
				}
				mensaje("Productos", productosString);

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
