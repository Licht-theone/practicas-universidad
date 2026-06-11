package pract06.torres_hanoi;

import j2d.Juego;
import pract06.torres_hanoi.gui.PantallaTorres;
import pract06.torres_hanoi.modelo.ControlMovimientos;

/**
 * Juego de las "Torres de Hanoi".
 * 
 * @author Metodos de Programacion (UC)
 * @version oct-2023
 * */
public class JuegoTorresHanoi {
	public static final int ANCHO_VENTANA = 800;
	public static final int ALTO_VENTANA = 600;
	public static final String PATH_SONIDOS = "src/pract06/torres_hanoi/sonidos/";
	public static final int MS_ENTRE_MOVIMIENTOS = 200;

	/**
	 * Programa principal del juego de  las "Torres de Hanoi".
	 * @param args argumentos de main (no usados)
	 */
	public static void main(String[] args) {
		Juego.asignaNombre("Torres Hanoi");
		Juego.asignaDimensiones(ANCHO_VENTANA, ALTO_VENTANA);
		
		// crea el controlador del juego
		ControlMovimientos controlador = new ControlMovimientos();

		// crea la pantalla con las torres y discos creados en el controlador
		PantallaTorres pantalla = new PantallaTorres(controlador);
		Juego.anhadeEscena(pantalla);
		
		// comienza el juego
		Juego.jugar();
	}

}
