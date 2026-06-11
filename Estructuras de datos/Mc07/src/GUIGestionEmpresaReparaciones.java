import fundamentos.*;

/**
 * Interfaz Grafica de Usuario (GUI) de la aplicacion de
 * gestion de una empresa de reparaciones a domicilio.
 * 
 * @author Estructuras de Datos (UC) y
 * @version oct-2018
 */
public class GUIGestionEmpresaReparaciones {

	public static void main(String[] args) {
		// opciones del menu
		final int ASIGNA_REPARACION=0, FINALIZA_REPARACION=1, CONSULTA_REPARACION=2,
				ELIMINA_OPERARIOS=4;

		// variables auxiliares
		Lectura lect;
		String nombreOperario;

		// Crea la empresa de reparaciones
		
		EmpresaReparaciones empresa = new EmpresaReparaciones("");

		// crea el menu
		Menu menu = new Menu("Gestion Empresa Reparaciones");
		menu.insertaOpcion("Asigna reparacion a operario", ASIGNA_REPARACION);
		menu.insertaOpcion("Operario realiza reparacion", FINALIZA_REPARACION);
		menu.insertaOpcion("Consulta reparacion realizada", CONSULTA_REPARACION);
		menu.insertaOpcion("Elimina operarios sin reparaciones", ELIMINA_OPERARIOS);
		int opcion = 0;

		// lazo de espera de comandos del usuario
		while(true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opción elegida
			switch (opcion) {
			case ASIGNA_REPARACION:
				lect = new Lectura("Asigna reparacion");
				lect.creaEntrada("Nombre operario", "Pepe");
				lect.creaEntrada("Direccion Reparacion", "Una Calle, s/n");
				lect.creaEntrada("Descripcion Reparacion", "Arreglo grifo");
				lect.esperaYCierra();
				nombreOperario = lect.leeString("Nombre operario");
				String direccion = lect.leeString("Direccion Reparacion");
				String descripcion = lect.leeString("Descripcion Reparacion");

				// asigna la reparacion al operario
				// TODO
				Reparacion rep = new Reparacion(descripcion, direccion);
				try {
					empresa.asignaReparacionAOperario(nombreOperario, rep);
				} catch (EmpresaReparaciones.OperarioNoExistente e) {
					mensaje("Error", "No existe opersrio");
				}
				break;

			case FINALIZA_REPARACION:
				lect = new Lectura("Finaliza reparacion");
				lect.creaEntrada("Nombre operario", "Pepe");
				lect.esperaYCierra();
				nombreOperario = lect.leeString("Nombre operario");

				// finaliza la reparacion
				// TODO
				try {
					Reparacion rep2 = empresa.operarioFinalizaReparacion(nombreOperario);
					mensaje("REPARACION" + rep2.direccion(), rep2.descripcion());
				} catch (EmpresaReparaciones.OperarioNoExistente e) {
					mensaje("Error", "No existe opersrio");
				} catch (EmpresaReparaciones.NoHayReparacionesPendientes e) {
					mensaje("Error", "Operario Sin Reparaciones asignadas");
				}
				break;

			case CONSULTA_REPARACION:
				lect = new Lectura("Consulta reparacion");
				lect.creaEntrada("Nombre operario", "Pepe");
				lect.creaEntrada("Posicion reparacion", 0);
				lect.esperaYCierra();
				nombreOperario = lect.leeString("Nombre operario");
				int posReparacion = lect.leeInt("Posicion reparacion");

				// muestra la reparacion
				// TODO
				try {
					Reparacion repHIs = empresa.reparacionEnHistorico(nombreOperario, posReparacion);
					mensaje("Posicion en historico reparaciones", "La reparacion en la posicion " + posReparacion + 
							" del historico es " + repHIs.toString());
				} catch (EmpresaReparaciones.OperarioNoExistente e) {
					mensaje("Error", "No existe opersrio");
				} catch (EmpresaReparaciones.PosReparacionIncorrecta e) {
					mensaje("Error", "Posicion incorrecta del historico");
				}
				break;

			case ELIMINA_OPERARIOS:
				// TODO
				mensaje("Operarios de la empresa", empresa.eliminaOperariosDesocupados().toString());
				break;
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
