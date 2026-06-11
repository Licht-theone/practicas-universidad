package examen.gui;

import examen.modelo.Museo;
import examen.modelo.Grupo;
import examen.modelo.Visita;
import fundamentos_test.*;

/**
 * Interfaz Gráfica de Usuario (GUI) de la aplicación para la gestion de las
 * reservas de grupos para las visitas guiadas a un museo.
 * 
 * @author  Metodos de Programacion (UC) y Aaron Alegria Puente
 * @version mar-24
 */
public class GuiVisitasMuseo {
	// opciones del menu
	public static final int REALIZA_RESERVA = 0;
	public static final int ANULA_RESERVA = 1;
	public static final int NUMERO_GRUPOS_CON_TAMANHO = 2;
	public static final int BUSCA_VISITA_CON_CAPACIDAD = 3;
	public static final int FIN_APLICACION = 4;
	private static final int ERROR = -1;

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del programa principal (no usados)
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {
		// variables auxiliares
		int idVisita;
		Lectura lect;

		// crea el museo
		Museo m = new Museo();

		// crea la ventana de menu
		Menu menu = FundamentosFactory.getMenu("Visitas museo");
		menu.insertaOpcion("Realiza reserva grupo", REALIZA_RESERVA);
		menu.insertaOpcion("Anula reserva grupo", ANULA_RESERVA);
		menu.insertaOpcion("Consulta numero grupos", NUMERO_GRUPOS_CON_TAMANHO);
		menu.insertaOpcion("Busca visita con capacidad", BUSCA_VISITA_CON_CAPACIDAD);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();
			if (opcion == FIN_APLICACION) {
				break;
			}

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case REALIZA_RESERVA:
				lect = FundamentosFactory.getLectura("Realiza reserva");
				lect.creaEntrada("Id visita", 0);
				lect.creaEntrada("Num componentes grupo", 2);
				lect.esperaYCierra();
				idVisita = lect.leeInt("Id visita");
				int numComponentes = lect.leeInt("Num componentes grupo");
				Grupo g = new Grupo(numComponentes);
				// realiza la reserva para un grupo
				if (!m.realizaReservaGrupo(idVisita, g)) {
					mensaje("ERROR", "Id visita incorrecto o " +
							"visita sin capacidad suficiente");
				} else {
					mensaje("Reserva realizada",
							"Realizada reserva de grupo de " + numComponentes +
							" componentes para la visita " + idVisita);
				}
				break;

			case ANULA_RESERVA:
				lect = FundamentosFactory.getLectura("Anula reserva");
				lect.creaEntrada("Id visita", 0);
				lect.creaEntrada("Codigo grupo", "GRP1");
				lect.esperaYCierra();
				idVisita = lect.leeInt("Id visita");
				String codGrupo = lect.leeString("Codigo grupo");
				// Anula la reserva del grupo en la visita
				if (!m.anulaReserva(idVisita, codGrupo)) {
					mensaje("ERROR", "Id visita incorrecto o " +
							"grupo sin reserva en la visita");
				} else {
					mensaje("Reserva anulada",
							"Anulada reserva del grupo " + codGrupo +
							" en la visita " + idVisita);
				}
				break;

			case NUMERO_GRUPOS_CON_TAMANHO:
				lect = FundamentosFactory.getLectura("Consulta num grupos");
				lect.creaEntrada("Id visita", 0);
				lect.creaEntrada("Min. num. componentes grupo", 2);
				lect.esperaYCierra();
				idVisita = lect.leeInt("Id visita");
				int minNumComponentes =
						lect.leeInt("Min. num. componentes grupo");
				// Muestra el numero de grupos con mayor o igual num componentes
				int numGrupos = m.gruposConTamanho(idVisita, minNumComponentes);
				if (numGrupos == ERROR) {
					mensaje("ERROR", "Id visita incorrecto");
				} else {
					mensaje("Num grupos",
							"El numero de grupos con " + minNumComponentes +
							" o mas componentes de la visita " + idVisita +
							" es " + numGrupos);
				}
				break;

			case BUSCA_VISITA_CON_CAPACIDAD:
				lect = FundamentosFactory.getLectura("Busca visita");
				lect.creaEntrada("Numero visitantes", 2);
				lect.esperaYCierra();
				int numVisitantes = lect.leeInt("Numero visitantes");

				// Busca visita con capacidad suficiente
				Visita v = m.buscaVisitaConCapaciad(numVisitantes);
				if (v == null) {
					mensaje("ERROR", "No existe ninguna visita con capacidad " +
							"para " + numVisitantes + " visitantes");
				} else {
					mensaje("Visita con capacidad",
							"Hay capacidad para " + numVisitantes +
							" visitantes en la visita con id " + v.id());
				}
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
