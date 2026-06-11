package pract03.modelo;

/**
 * Equipo de baloncesto.
 * 
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version sep-21
 *
 */
public class Equipo {
	private static final int MAXDORSALES = 15;
	private Jugador[] jugadores = new Jugador[MAXDORSALES];


	/**
	 * Construye un equipo. Los jugadores comienzan con 0 puntos y
	 * 0 asistencias.
	 */
	public Equipo() {
		for (int i = 0; i < MAXDORSALES; i++) {
			jugadores[i] = new Jugador(i);
		}
	}

	/**
	 * Suma puntos anotados al jugador con el dorsal indicado.
	 * @param dorsal dorsal del jugador que ha anotado los puntos.
	 * @param puntos numero de puntos anotados.
	 * @return verdadero si el dorsal es correcto (es un numero entre 0 y el
	 * numero de jugadores del equipo menos 1) y falso en caso contrario.
	 */
	public boolean sumaPuntosAnotados(int dorsal, int puntos) {
		Jugador jug = buscaJugador(dorsal);
		if (jug == null) {
			return false;
		}
		jug.sumaPuntos(puntos);
		return true;
	}

	/**
	 * Suma una asistencia al jugador con el dorsal indicado.
	 * @param dorsal dorsal del jugador al que sumar la asistencia.
	 * @return verdadero si el dorsal es correcto (es un numero entre 0 y el
	 * numero de jugadores del equipo menos 1) y falso en caso contrario.
	 */
	public boolean sumaAsistencia(int dorsal) {
		Jugador jug = buscaJugador(dorsal);
		if (jug == null) {
			return false;
		}
		jug.sumaAsistencia();
		return true;
	}

	/**
	 * Retorna los puntos anotados por el equipo.
	 * @return puntos anotados por el equipo.
	 */
	public int puntos() {
		int puntos = 0;
		for (int i = 0; i < MAXDORSALES; i++) {
			puntos += jugadores[i].puntos();
		}
		return puntos;
	}

	/**
	 * Busca el mejor jugador (el de mayor valoracion).
	 * @return mejor jugador del equipo.
	 */
	public Jugador mejorJugador() {
		double max = -1;
		double nuevoMax;
		Jugador jug = null;
		for (int i = 0; i < MAXDORSALES; i++) {
			nuevoMax = jugadores[i].valoracion();
			if (nuevoMax > max) {
				max = nuevoMax;
				jug = jugadores[i];
			}
		}
		return jug;
	}

	/**
	 * busca jugador con su numero de dorsal.
	 * @param dorsal numero de dorsal
	 * @return el jugador con ese dorsal o null en caso de que no exista
	 */
	public Jugador buscaJugador(int dorsal) {
		Jugador jug = null;
		for (int i = 0; i < MAXDORSALES; i++) {
			if (jugadores[i].dorsal() == dorsal) {
				jug = jugadores[i];
			}
		}
		return jug;
	}

}
