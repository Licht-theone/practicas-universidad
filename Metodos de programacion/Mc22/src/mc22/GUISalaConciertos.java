package mc22;

import java.io.FileNotFoundException;

import fundamentos_test.*;
import mc22.Abono.MaxEntradas;
import mc22.Concierto.ConciertoLleno;
import mc22.SalaConciertos.AbonoNoExiste;
import mc22.SalaConciertos.AbonoYaExiste;
import mc22.SalaConciertos.ConciertoNoExiste;
import mc22.SalaConciertos.YaTieneEntrada;

/**
 * Interfaz Grafica de Usuario (GUI) de la aplicacion para
 * la gestion de una sala de conciertos.
 * 
 * @author Metodos de Programacion (UC)
 * @version jun-2022
 */
public class GUISalaConciertos {
	// opciones del menu
	public static final int REGISTRA_ABONO = 0;
	public static final int COMPRA_ENTRADA = 1;
	public static final int PAGO_ABONADO = 2;
	public static final int CONCIERTO_CON_MAS_FIJOS = 3;
	public static final int FIN_APLICACION = 4;

	// fichero con los datos de los conciertos
	private static final String nomFichConciertos = "datos_conciertos.txt";

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del programa principal (no usados)
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {

		// variables auxiliares
		Lectura lect;
		String dni;
		Abono abono;

		// crea la sala de conciertos
		SalaConciertos salaConciertos;
		try {
			salaConciertos = new SalaConciertos(nomFichConciertos);
		} catch (FileNotFoundException e1) {
			mensaje("Error", "No se pudo abrir el fichero " + nomFichConciertos);
			return; // error faltal, finaliza el programa.
		}

		// crea la ventana de menu
		Menu menu = FundamentosFactory.getMenu("Biblioteca");
		menu.insertaOpcion("Registra abono", REGISTRA_ABONO);
		menu.insertaOpcion("Compra entrada", COMPRA_ENTRADA);
		menu.insertaOpcion("Pago abonado", PAGO_ABONADO);
		menu.insertaOpcion("Espectaculo con mas fijos", CONCIERTO_CON_MAS_FIJOS);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();
			if (opcion == FIN_APLICACION) {
				break;
			}

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case  REGISTRA_ABONO:
				lect = FundamentosFactory.getLectura("Datos abono");
				lect.creaEntrada("DNI", "12345678A");
				lect.creaEntrada("Tipo (F)ijo/Fle(X)ible/(B)asico", "F");
				lect.esperaYCierra();
				dni = lect.leeString("DNI");
				char tipo = lect.leeString("Tipo (F)ijo/Fle(X)ible/(B)asico").charAt(0);

				// crea el usuario y le registra en la biblioteca
				abono = null;
				try {
					switch (tipo) {
					case 'F':
					case 'f':
						abono = new AbonoFijo(dni);
						break;
					case 'X':
					case 'x':
						abono = new AbonoFlex(dni);
						break;
					case 'B':
					case 'b':
						abono = new AbonoBas(dni);
						break;
					default:
						abono = null;
					}
					if (abono != null) {
						salaConciertos.anhadeAbono(abono);
						mensaje("Abono registrado", "Abono registrado correctamente");
					} else {
						mensaje("ERROR", "Tipo de abono incorrecto, debe ser F, X o B");
					}

				} catch (AbonoYaExiste e) {
					mensaje("ERROR", "Abono con DNI ya registrado");
				}				
				break;

			case COMPRA_ENTRADA:
				lect = FundamentosFactory.getLectura("Compra entrada");
				lect.creaEntrada("DNI", "12345678A");
				lect.creaEntrada("Concierto", "Concierto1");
				lect.esperaYCierra();
				dni = lect.leeString("DNI");
				String nombreConcierto = lect.leeString("Concierto");

				// Presta el libro al usuario
				try {
					salaConciertos.compraEntrada(dni, nombreConcierto);
					mensaje("Entrada comprada", "Entrada comprada correctamente");
				} catch (AbonoNoExiste e) {
					mensaje("ERROR", "No existe ningun abono con el DNI indicado");

				} catch (ConciertoNoExiste e) {
					mensaje("ERROR", "No existe ningun concierto con el nombre indicado");

				} catch (ConciertoLleno e) {
					mensaje("ERROR", "No quedan entradas para el concierto");
				} catch (YaTieneEntrada e) {
					mensaje("ERROR",
							"El abonado ya tiene una entrada para ese concierto");
				} catch (MaxEntradas e) {
					mensaje("ERROR",
							"El abonado ha alcanzado su numero maximo de entradas");
				}
				break;

			case PAGO_ABONADO:
				lect = FundamentosFactory.getLectura("Consulta pago");
				lect.creaEntrada("DNI", "12345678A");
				lect.esperaYCierra();
				dni = lect.leeString("DNI");

				// Busca el usuario y muestra su cantidad a pagar
				abono = salaConciertos.buscaAbono(dni);
				if (abono == null) {
					mensaje("ERROR", "No existe ningun abono con el DNI indicado");

				} else {
					mensaje("Pago", "Pago: " + abono.precioTot());
				}
				break;

			case CONCIERTO_CON_MAS_FIJOS:
				Concierto concierto = salaConciertos.conciertoMasX("fijo");
				mensaje("Concierto con mas abonos fijos",
						concierto.getNombre());
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