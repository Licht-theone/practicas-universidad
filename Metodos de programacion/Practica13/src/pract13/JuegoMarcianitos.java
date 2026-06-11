package pract13;

import j2d.Juego;
import j2d.utils.FuentesUtils;

/**
 * Juego inspirado en Space Invaders.
 * Basado en el motor de videojuegos j2d.
 * <br>
 * Creditos:
 *  - Iconos:
 *    https://comp3interactive.itch.io/invaders-from-outerspace-full-project-asset-pack
 *  - Sonidos: "The sounds resource"
 *    https://www.sounds-resource.com/pc_computer/spaceinvadersextreme/
 *  - Fuente "Arcade Normal": "Yuji Adachi"
 *    https://www.fontspace.com/category/arcade
 *    
 * @author Metodos de Programacion (UC)
 * @version may-2024
 */
public class JuegoMarcianitos {
	public static final int ANCHO_VENTANA = 800;
	public static final int ALTO_VENTANA = 600;
	public static final String PATH_ICONOS = "src/recursos/iconos/space_invaders/";
	public static final String PATH_SONIDOS = "src/recursos/sonidos/space_invaders/";
	public static final String PATH_FUENTES = "src/recursos/fuentes/";	
	
	/**
	 * Metodo principal del juego.
	 * @param args argumentos (no utilizados).
	 */
	public static void main(String[] args) {
		Juego.asignaNombre("Marcianitos");
		Juego.asignaDimensiones(ANCHO_VENTANA, ALTO_VENTANA);
		
		// registra la fuente "Arcade Normal"
		FuentesUtils.registaFuente(PATH_FUENTES + "Arcade/ARCADE_N.TTF");

		// crea las pantallas del juego
		// TODO: pueden crearse mas pantallas si asi se desea
		Pantalla pantalla1 = new Pantalla("pantalla1", "pantalla2");
		Juego.anhadeEscena(pantalla1);
		Pantalla pantalla2 = new Pantalla("pantalla2", "pantalla3");
		Juego.anhadeEscena(pantalla2);
		Pantalla pantalla3 = new Pantalla("pantalla3", null);
		Juego.anhadeEscena(pantalla3);
		
		// comienza el juego
		Juego.jugar();
	}

}
