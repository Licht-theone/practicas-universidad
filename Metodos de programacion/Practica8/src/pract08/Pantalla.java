package pract08;

import static pract08.JuegoMarcianitos.*;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import j2d.IControladoTeclado;
import j2d.JEscena;
import j2d.JObjeto;
import j2d.JObjetoTexto;
import j2d.JObjetoTextura;
import j2d.Juego;
import pract08.marcianitos.MarcianitoJefe;
import pract08.marcianitos.MarcianitoMaster;
import pract08.marcianitos.MarcianitoSoldado;

/**
 * Pantalla de juego con los marcianitos y el canyon.
 *    
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version abr-2024
 */
public class Pantalla extends JEscena implements IControladoTeclado {
	private static final int Y_LASER = 60;
	private static final int Y_MARCIANOS = 60;
	private static final int X_ENTRE_COLUMNAS = 80;
	public static final int Y_META_MARCIANOS =
			JuegoMarcianitos.ALTO_VENTANA - 100;
	public static final int Y_ALTO_EDIFICIOS = 64;
	private int numMarcianosVivos;
	private CanyonLaser laser;

	// texto
	public static final int FUENTE_TAMANHO_GRANDE = 30;
	public static final int FUENTE_TAMANHO_PEQUENHO = 12;
	public static final int X_TEXTO_MARGEN_IZQ = 30;
	public static final int X_TEXTO_ESC = ANCHO_VENTANA - 400;
	public static final int Y_TEXTO_ESC = ALTO_VENTANA - 100;

	/**
	 * Crea una pantalla.
	 * @param nombre nombre de la pantalla.
	 */
	public Pantalla(String nombre) {
		super(nombre);

		// fondo (cielo)
		JObjeto cielo = new JObjetoTextura(null, anchoX(), altoY(),
				PATH_ICONOS + "SpaceInvaders_Background.png", 1);
		cielo.colisionador().desactiva();
		incluyeObj(cielo, 0, 0);

		// fondo (franja edificios)
		JObjeto edificios = new JObjetoTextura(null,
				anchoX(), Y_ALTO_EDIFICIOS,
				PATH_ICONOS + "SpaceInvaders_BackgroundBuildings.png", 1);
		edificios.colisionador().desactiva();
		incluyeObj(edificios, 0, altoY() - Y_ALTO_EDIFICIOS);
	}

	@Override
	public void entraEscena() {
		super.entraEscena();
		// crea marcianitos
		MarcianitoSoldado m1 = new MarcianitoSoldado(2);
		MarcianitoSoldado m2 = new MarcianitoSoldado(2);
		MarcianitoJefe m3 = new MarcianitoJefe(3);
		MarcianitoMaster m4 = new MarcianitoMaster(4);
		numMarcianosVivos = 4;
		incluyeObj(m1, 0 * X_ENTRE_COLUMNAS, Y_MARCIANOS);
		incluyeObj(m2, X_ENTRE_COLUMNAS, Y_MARCIANOS);
		incluyeObj(m3, 2 * X_ENTRE_COLUMNAS, Y_MARCIANOS);
		incluyeObj(m4, 3 * X_ENTRE_COLUMNAS, Y_MARCIANOS);
		// crea el canhon laser
		laser = new CanyonLaser();
		incluyeObj(laser, anchoX() / 2, altoY() - Y_LASER);
		controladoTecladoAnhade(laser);
	}

	/**
	 * Un marcianito ha sido destruido.
	 */
	public void marcianoDestruido() {
		numMarcianosVivos--;
		if (numMarcianosVivos == 0) {

			// la pantalla actual pasa a ser el controlador
			controladoTecladoAnhade(this);

			// mensaje fin juego
			incluyeObj(new JObjetoTexto("Has evitado la invasion!",
					Color.WHITE, new Font("Arcade Normal", Font.PLAIN, FUENTE_TAMANHO_GRANDE)),
					X_TEXTO_MARGEN_IZQ, altoY() / 2);
			incluyeObj(new JObjetoTexto("Presiona esc para salir...",
					Color.WHITE, new Font("Arcade Normal", Font.PLAIN, FUENTE_TAMANHO_PEQUENHO)),
					X_TEXTO_ESC, Y_TEXTO_ESC);
		}
	}

	@Override
	public void teclaPresionada(int codigoTecla) {
		switch (codigoTecla) {
		case KeyEvent.VK_ESCAPE:
			Juego.finalizarJuego();
			break;
		default:
			break;
		}
	}

	/**
	 * El laser ha sido destruido.
	 */
	public void laserDestruido() {
		if (laser == null) {
			return; // ya ha sido destruido en este ciclo
		}

		laser.destruido();
		laser = null;
		incluyeObj(new JObjetoTexto("La Tierra ha sido invadida",
				Color.WHITE, new Font("Arcade Normal", Font.PLAIN, FUENTE_TAMANHO_GRANDE)),
				X_TEXTO_MARGEN_IZQ, altoY() / 2);
		incluyeObj(new JObjetoTexto("Presiona esc para salir...",
				Color.WHITE, new Font("Arcade Normal", Font.PLAIN, FUENTE_TAMANHO_PEQUENHO)),
				X_TEXTO_ESC, Y_TEXTO_ESC);
		controladoTecladoAnhade(this);
	}

	/**
	 * Retorna el valor de la coordenada Y que es la meta de los marcianos.
	 * @return el valor de la coordenada Y que es la meta de los marcianos.
	 */
	public int metaMarcianosY() {
		return altoY() - Y_META_MARCIANOS;
	}

}
