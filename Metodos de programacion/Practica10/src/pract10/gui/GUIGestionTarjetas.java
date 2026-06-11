package pract10.gui;

import fundamentos_test.*;
import pract10.modelo.Supermercado;
import pract10.modelo.Supermercado.ClienteNoExiste;
import pract10.modelo.Supermercado.ClienteYaTieneTarjeta;
import pract10.modelo.Supermercado.TipoNoValido;
import pract10.modelo.tarjetas.Tarjeta;
import pract10.modelo.tarjetas.Tarjeta.SaldoInsuficiente;
import pract10.modelo.tarjetas.TarjetaViajes;
import pract10.modelo.tarjetas.TarjetaViajes.PuntosInsuficientes;

/**
 * Interfaz Gráfica de Usuario (GUI) de la aplicacion para gestion de las
 * tarjetas de los clientes de un supermercado.
 * 
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version abr-2023
 */
public class GUIGestionTarjetas {
	// opciones del menu
	public static final int REGISTRA_TARJETA = 0;
	public static final int RECARGA_SALDO = 1;
	public static final int REALIZA_COMPRA = 2;
	public static final int CANJEA_PUNTOS = 3;
	public static final int SALDO_TARJETA = 4;
	public static final int FIN_APLICACION = 5;

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del programa principal (no usados)
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {

		// variables auxiliares
		String dni;
		Lectura lect;

		// crea el supermercado
		Supermercado dia = new Supermercado();

		// crea la ventana de menu
		Menu menu = FundamentosFactory.getMenu("Tarjetas supermercado");
		menu.insertaOpcion("Registra tarjeta", REGISTRA_TARJETA);
		menu.insertaOpcion("Recaga saldo", RECARGA_SALDO);
		menu.insertaOpcion("Realiza compra", REALIZA_COMPRA);
		menu.insertaOpcion("Canjea puntos viajes", CANJEA_PUNTOS);
		menu.insertaOpcion("Listado saldo tarjetas", SALDO_TARJETA);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();
			if (opcion == FIN_APLICACION) {
				break;
			}

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case REGISTRA_TARJETA:
				// lee los datos de la nueva tarjeta
				lect = FundamentosFactory.getLectura("Datos nueva tarjeta");
				lect.creaEntrada("DNI cliente", "11111111A");
				lect.creaEntrada("Direccion cliente", "Su casa");
				lect.creaEntrada("Tipo Tarjeta: B | VN | VF", "B");
				lect.esperaYCierra();
				dni = lect.leeString("DNI cliente");
				String direccion = lect.leeString("Direccion cliente");
				String tipoTarjeta = lect.leeString("Tipo Tarjeta: B | VN | VF");
				// Crea la tarjeta del tipo apropiado y la registra en el
				// restaurante.
				// Mensajes producidos por la aplicacion:
				//  - Funcionamiento correcto:
				//    mensaje("Tarjeta anhadida",
				//		"La tarjeta " + dni + " anhadida con exito.");
				//  - Tipo de tarjeta no valido:
				//    mensaje("ERROR",
				//		"Tipo de tarjeta incorrecto, debe ser B, VN o VF");
				//  - DNI ya existente:
				//    mensaje("ERROR", 
				//		"Ya existe una tarjeta con DNI " + dni);
				try {
					dia.registraTarjeta(dni, direccion, tipoTarjeta);
				} catch (ClienteYaTieneTarjeta e) {
					mensaje("ERROR", 
							"Ya existe una tarjeta con DNI " + dni);
					break;
				} catch (TipoNoValido e) {
					mensaje("ERROR",
							"Tipo de tarjeta incorrecto, debe ser B, VN o VF");
					break;
				}
				mensaje("Tarjeta anhadida",
						"La tarjeta " + dni + " anhadida con exito.");
				break;

			case RECARGA_SALDO:
				lect = FundamentosFactory.getLectura("Recarga saldo");
				lect.creaEntrada("DNI cliente", "11111111A");
				lect.creaEntrada("Recarga", "150");
				lect.esperaYCierra();
				dni = lect.leeString("DNI cliente");
				double saldoRecarga = lect.leeDouble("Recarga");
				// Recarga el saldo de la tarjeta
				// Mensajes producidos por la aplicacion:
				//  - Funcionamiento correcto:
				//    mensaje("Recarga efectuada",
				//		"La recarga se ha realizado con exito.");
				//  - DNI no existente:
				//    mensaje("ERROR", 
				//		"No existe ninguna tarjeta con DNI " + dni);
				try {
					dia.recargaSaldo(dni, saldoRecarga);
				} catch (ClienteNoExiste e) {
					mensaje("ERROR", 
							"No existe ninguna tarjeta con DNI " + dni);
					break;
				}
				mensaje("Recarga efectuada",
						"La recarga se ha realizado con exito.");
				break;

			case REALIZA_COMPRA:
				lect = FundamentosFactory.getLectura("Realiza compra");
				lect.creaEntrada("DNI cliente", "11111111A");
				lect.creaEntrada("Precio compra", "100");
				lect.esperaYCierra();
				dni = lect.leeString("DNI cliente");
				double precioCompra = lect.leeDouble("Precio compra");
				// Realiza una compra con la tarjeta
				// Mensajes producidos por la aplicacion:
				//  - Funcionamiento correcto:
				//    mensaje("Saldo tarjeta", 
				//		"Saldo restante " + saldoRestante);
				//  - DNI no existente:
				//    mensaje("ERROR", 
				//		"No existe ninguna tarjeta con DNI " + dni);
				//  - Saldo insuficiente:
				//    mensaje("ERROR", "La tarjeta con DNI " + dni +
				//		" no tiene suficiente saldo");
				try {
					dia.realizaCompra(dni, precioCompra);
				} catch (ClienteNoExiste e) {
					mensaje("ERROR", 
							"No existe ninguna tarjeta con DNI " + dni);
					break;
				} catch (SaldoInsuficiente e) {
					mensaje("ERROR", "La tarjeta con DNI " + dni +
							" no tiene suficiente saldo");
					break;
				}
				Tarjeta t = dia.buscaTarjeta(dni);
				mensaje("Saldo tarjeta", 
						"Saldo restante " + t.saldo());
				break;

			case CANJEA_PUNTOS:
				lect = FundamentosFactory.getLectura("Canjea puntos");
				lect.creaEntrada("DNI cliente", "11111111A");
				lect.creaEntrada("Puntos a canjear", "2");
				lect.esperaYCierra();
				dni = lect.leeString("DNI cliente");
				int puntos = lect.leeInt("Puntos a canjear");
				// Canjea puntos de la tarjeta
				// Mensajes producidos por la aplicacion:
				//  - Funcionamiento correcto:
				//    mensaje("Puntos tarjeta", 
				//		"Puntos restantes " + puntosRestantes);
				//  - DNI no existente:
				//    mensaje("ERROR", 
				//		"No existe ninguna tarjeta con DNI " + dni);
				//  - Tipo incorrecto:
				//    mensaje("ERROR", "La tarjeta con DNI " + dni +
				//		" no es una tarjeta de viajes");
				//  - Puntos insuficientes:
				//    mensaje("ERROR", "La tarjeta con DNI " + dni +
				//		" no tiene suficientes puntos");
				try {
					dia.canjeaPuntosViajes(dni, puntos);
				} catch (ClienteNoExiste e) {
					mensaje("ERROR", 
							"No existe ninguna tarjeta con DNI " + dni);
					break;
				} catch (PuntosInsuficientes e) {
					mensaje("ERROR", "La tarjeta con DNI " + dni +
							" no tiene suficientes puntos");
					break;
				} catch (TipoNoValido e) {
					mensaje("ERROR", "La tarjeta con DNI " + dni +
							" no es una tarjeta de viajes");
					break;
				}
				t = dia.buscaTarjeta(dni);
				mensaje("Puntos tarjeta", 
						"Puntos restantes " + ((TarjetaViajes) t).puntosViajeAcumulados());
				break;

			case SALDO_TARJETA:
				lect = FundamentosFactory.getLectura("Consulta saldo");
				lect.creaEntrada("DNI cliente", "11111111A");
				lect.esperaYCierra();
				dni = lect.leeString("DNI cliente");
				t = dia.buscaTarjeta(dni);
				// Consulta el saldo de la tarjeta.
				// Mensajes producidos por la aplicacion:
				//  - Funcionamiento correcto:
				//    mensaje("Saldo tarjeta", 
				//		"Saldo: " + saldo);
				//  - DNI no existente:
				//    mensaje("ERROR", 
				//		"No existe ninguna tarjeta con DNI " + dni);
				if (t == null) {
					mensaje("ERROR", 
							"No existe ninguna tarjeta con DNI " + dni);
					break;
				}
				mensaje("Saldo tarjeta", 
						"Saldo: " + t.saldo());
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
