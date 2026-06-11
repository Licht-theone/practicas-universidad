package pract11.gui;

import fundamentos_test.*;
import pract11.modelo.Actividad.ActividadMaxSocios;
import pract11.modelo.Gimnasio;
import pract11.modelo.Gimnasio.NoExisteActividad;
import pract11.modelo.Gimnasio.NoExisteSocio;
import pract11.modelo.Socio;
import pract11.modelo.Socio.SocioMaxActividades;
import pract11.modelo.Socio.SocioYaInscrito;
import pract11.modelo.SocioPlus;

/**
 * Interfaz Gráfica de Usuario (GUI) de la aplicación para gestion
 * de de los socios de un gimnasio.
 * 
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version nov-2023
 */
public class GUIGestionGimnasio {
	// opciones del menu
	public static final int INSCRIBE_EN_ACTIVIDAD = 0;
	public static final int MUESTRA_DATOS_SOCIO = 1;
	public static final int INSCRIPCION_SOCIO = 2;
	public static final int FIN_APLICACION = 3;

	/**
	 * Programa principal.
	 * @param args argumentos del main (no utilizados)
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {

		// variables auxiliares
		String dniSocio;
		String nombreActividad;
		Lectura lect;

		// Crea el gimnasio
		Gimnasio smartfit = new Gimnasio();

		// crea la ventana de menu
		Menu menu = FundamentosFactory.getMenu("Gestion Gimnasio");
		menu.insertaOpcion("Inscribe socio en actividad", INSCRIBE_EN_ACTIVIDAD);
		menu.insertaOpcion("Muestra datos socio", MUESTRA_DATOS_SOCIO);
		menu.insertaOpcion("Consulta inscripcion socio", INSCRIPCION_SOCIO);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();
			if (opcion == FIN_APLICACION) {
				break;
			}

			// realiza las acciones dependiendo de la opción elegida
			switch (opcion) {

			case INSCRIBE_EN_ACTIVIDAD:
				// lee los datos del socio y de la activdad
				lect = FundamentosFactory.getLectura("Inscribe socio en actividad");
				lect.creaEntrada("DNI socio", "10000000A");
				lect.creaEntrada("Nombre actividad", "Actividad0");
				lect.esperaYCierra();
				dniSocio = lect.leeString("DNI socio");
				nombreActividad = lect.leeString("Nombre actividad");
				// Inscribe el socio en la actividad
				try {
					smartfit.inscribeSocio(dniSocio, nombreActividad);
					mensaje("Socio inscrito",
							"El socio " + dniSocio +
							" se ha inscrito correctamente en " +
							nombreActividad);
				} catch (SocioYaInscrito e) {
					mensaje("Error",
							"El socio ya esta inscrito en la actividad");
				} catch (SocioMaxActividades e) {
					mensaje("Error",
							"El socio ha alcanzado el limite de actividades");
				} catch (NoExisteSocio e) {
					mensaje("Error",
							"No existe ningun socio con el DNI indicado");
				} catch (NoExisteActividad e) {
					mensaje("Error",
							"No existe ninguna actividad con el nombre indicado");
				} catch (ActividadMaxSocios e) {
					mensaje("Error",
							"La actividad ha completado sus plazas");
				}
				break;

			case MUESTRA_DATOS_SOCIO:
				// lee el DNI del socio
				lect = FundamentosFactory.getLectura("Muestra datos socio");
				lect.creaEntrada("DNI socio", "10000000A");
				lect.esperaYCierra();
				dniSocio = lect.leeString("DNI socio");
				Socio s = smartfit.buscaSocio(dniSocio);
				// busca el socio y muestra sus datos
				if (s == null) {
					mensaje("Error",
							"No existe ningun socio con el DNI indicado");
					break;
				}
				if (s instanceof SocioPlus) {
					mensaje("Datos socio",
							"Pago: " + s.cuotaMensual() + 
							" Num actividades: " + s.numActividades() +
							" Nombre: " + ((SocioPlus) s).nombre());
				} else {
					mensaje("Datos socio",
							"Pago: " + s.cuotaMensual() + 
							" Num actividades: " + s.numActividades());
				}
				break;

			case INSCRIPCION_SOCIO:
				// lee el DNI del socio y el nombre de la actividad
				lect = FundamentosFactory.getLectura("Consulta inscripcion socio");
				lect.creaEntrada("DNI socio", "10000000A");
				lect.creaEntrada("Nombre actividad", "Actividad0");
				lect.esperaYCierra();
				dniSocio = lect.leeString("DNI socio");
				nombreActividad = lect.leeString("Nombre actividad");
				boolean inscrito;
				// consulta si el socio esta inscrito en la actividad
				try {
					inscrito = smartfit.consultaInscripcion(dniSocio, nombreActividad);
					if (inscrito) {
						mensaje("Socio inscrito",
								"El socio esta inscrito en la actividad");
					} else {
						mensaje("Socio no inscrito",
								"El socio no esta inscrito en la actividad");
					}
				} catch (NoExisteSocio e) {
					mensaje("Error",
							"No existe ningun socio con el DNI indicado");
				} catch (NoExisteActividad e) {
					mensaje("Error",
							"No existe ninguna actividad con el nombre indicado");
				}
				break;

			default:
				throw new AssertionError("Opcion no esperada");	
			}

		} // while

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
