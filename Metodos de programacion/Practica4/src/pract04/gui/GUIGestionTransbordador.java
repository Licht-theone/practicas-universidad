package pract04.gui;

import fundamentos.*;
import pract04.modelo.Transbordador;
import pract04.modelo.Vehiculo;

/**
 * Interfaz Gráfica de Usuario (GUI) para la gestion de de la carga de
 * vehiculos en un barco transbordador.
 * 
 * @author Metodos de Programacion (UC)
 * @version mar-21
 */
public class GUIGestionTransbordador {
	private static final double PESO_MAXIMO_TRANSBORDADOR = 20000.0;
	private static final int MAX_CAPACIDAD_VEHICULOS_TRANSBORDADOR = 10;

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del programa principal (no usados)
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int CARGA_VEHICULO = 0;
		final int BUSCA_VEHICULO_CON_CARACTERISTICAS = 1;
		final int VACIA_TRANSBORDADOR = 3;

		// variables auxiliares
		Lectura lect;
		int numOcupantes;
		double peso;

		// crea el transbordador
		Transbordador transbordador = 
				new Transbordador(PESO_MAXIMO_TRANSBORDADOR, MAX_CAPACIDAD_VEHICULOS_TRANSBORDADOR);

		// crea la ventana de menú
		Menu menu = new Menu("Carga transbordador");
		menu.insertaOpcion("Carga vehiculo", CARGA_VEHICULO);
		menu.insertaOpcion("Busca vehiculo con caracteristicas",
				BUSCA_VEHICULO_CON_CARACTERISTICAS);
		menu.insertaOpcion("Vacia transbordador", VACIA_TRANSBORDADOR);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opción elegida
			switch (opcion) {
			case CARGA_VEHICULO:
				lect = new Lectura("Datos vehiculo");
				lect.creaEntrada("Matricula", "1234-ABC");
				lect.creaEntrada("Num ocupantes", "3");
				lect.creaEntrada("Peso (Kg)", "10000");
				lect.esperaYCierra();
				String matricula = lect.leeString("Matricula");
				numOcupantes = lect.leeInt("Num ocupantes");
				peso = lect.leeDouble("Peso (Kg)");

				// Crea el vehiculo y lo carga en el transbordador
				double precio =
						transbordador.cargaVehiculo(new Vehiculo(matricula, peso, numOcupantes));

				// muestra informacion sobre la carga
				if (precio < 0) {
					mensaje("Error", "Alcanzado peso o capacidad máximos o matricula ya existente");
				} else {
					mensaje("Precio vehiculo", "Precio:" + precio);
				}
				break;

			case BUSCA_VEHICULO_CON_CARACTERISTICAS:
				lect = new Lectura("Busca vehiculo por caracteristicas");
				lect.creaEntrada("Num ocupantes", "3");
				lect.creaEntrada("Peso (Kg)", "900");
				lect.esperaYCierra();
				numOcupantes = lect.leeInt("Num ocupantes");
				double pesoMinimo = lect.leeDouble("Peso (Kg)");

				// busca el vehiculo con las caracteristicas indicadas
				Vehiculo vehiculo =
						transbordador.buscaVehiculoConCaracteristicas(numOcupantes, pesoMinimo);

				// muestra datos del vehiculo buscado
				if (vehiculo == null) {
					mensaje("Error", "No hay ningun vehiculo con las caracteristicas buscadas");
				} else {
					mensaje("Datos vehiculo", "Matricula:" + vehiculo.matricula() +
							"\nNum ocupantes:" + vehiculo.numOcupantes() +
							"\nPeso (Kg):" + vehiculo.peso());
				}				
				break;

			case VACIA_TRANSBORDADOR:
				// vacia el transbordador y lo deja preparado para comenzar una nueva carga
				transbordador.vaciaTransbordador();

				mensaje("Informacion", "El transbordador ha sido vaciado");
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
