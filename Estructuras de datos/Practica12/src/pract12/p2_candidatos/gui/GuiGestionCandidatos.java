package pract12.p2_candidatos.gui;

import fundamentos.*;
import pract12.p2_candidatos.modelo.*;

/**
 * Interfaz Grafica de Usuario (GUI) de la aplicacion que gestiona los
 * candidatos presentados a un puesto de trabajo.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2023
 */
public class GuiGestionCandidatos {
	public static final int MAX_NUM_CANDIDATOS = 30;

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del programa (no usados).
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int ANHADE_CANDIDATO = 0;
		final int OBTIENE_MEJOR = 1;

		// variables auxiliares
		Lectura lect;
		String nombreCandidato;
		int puntuacion;

		// crea la empresa de alquiler
		CandidatosPuesto candidatos = new CandidatosPuesto(MAX_NUM_CANDIDATOS);		

		// crea el menu
		Menu menu = new Menu("Gestion Candidatos");
		menu.insertaOpcion("Anhade Candidato", ANHADE_CANDIDATO);
		menu.insertaOpcion("Obtiene mejor candidato", OBTIENE_MEJOR);
		int opcion = 0;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opción elegida
			switch (opcion) {
			case ANHADE_CANDIDATO:
				lect = new Lectura("Anhade candidato");
				lect.creaEntrada("Nombre", "Pepa");
				lect.creaEntrada("Puntuacion", 2);
				lect.esperaYCierra();
				nombreCandidato = lect.leeString("Nombre");
				puntuacion = lect.leeInt("Puntuacion");

				// anhade la oficina a la empresa
				try {
					candidatos.anhadeCandidato(new Candidato(nombreCandidato, puntuacion));
				} catch (CandidatosPuesto.NombreCandidatoYaExistente e) {
					mensaje("Error", "Nombre de candidato ya existente");
				} catch (CandidatosPuesto.AlcanzadoMaximoNumCandidatos e) {
					mensaje("Error", "Alcanzado el maximo numero de candidatos");
				}
				break;

			case OBTIENE_MEJOR:
				Candidato candidato = candidatos.mejorValorado();
				if (candidato == null) {
					mensaje("Error", "No hay mas candidatos");			
				} else {
					mensaje("Mejor candidato", "Nombre: " + candidato.nombre() +
							"\nPuntuacion: " + candidato.puntos());	
				}
				break;

			default:
				throw new AssertionError("Opcion no esperada");
			}
		}
	}

	/**
	 * Muestra un ventana de Mensaje del paquete Fundamentos.
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);
	}

}
