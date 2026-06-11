package pract13;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

import j2d.IControladoTeclado;
import j2d.JEscena;
import j2d.JObjeto;
import j2d.JObjetoTexto;
import j2d.JObjetoTextura;
import j2d.Juego;
import pract13.marcianitos.MarcianitoJefe;
import pract13.marcianitos.MarcianitoMaster;
import pract13.marcianitos.MarcianitoSoldado;

import static pract13.JuegoMarcianitos.*;

/**
 * Pantalla de juego con los marcianitos y el canyon.
 *    
 * @author Metodos de Programacion (UC) y Aaron Alegria Puente
 * @version may-2024
 */
public class Pantalla extends JEscena implements IControladoTeclado {
	private static final int Y_LASER = 60;
	public static final int Y_META_MARCIANOS =
			JuegoMarcianitos.ALTO_VENTANA - 100;
	public static final int Y_ALTO_EDIFICIOS = 64;
	private int numMarcianosVivos = 0;
	private CanyonLaser laser;
	private final String nomSigPantalla;

	// texto
	public static final int FUENTE_TAMANHO_GRANDE = 30;
	public static final int FUENTE_TAMANHO_PEQUENHO = 12;
	public static final int X_TEXTO_MARGEN_IZQ = 30;
	public static final int X_TEXTO_ESC = ANCHO_VENTANA - 400;
	public static final int Y_TEXTO_ESC = ALTO_VENTANA - 100;
	private static final int X_TEXTO_SIG_PANTALLA = ALTO_VENTANA - 500;

	/**
	 * Crea una pantalla.
	 * @param nombre nombre de la pantalla.
	 * @param nomSigPantalla nombre de la siguiente pantalla o null si esta
	 * es la ultima pantalla del juego.
	 */
	public Pantalla(String nombre, String nomSigPantalla) {
		super(nombre);
		this.nomSigPantalla = nomSigPantalla;

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
		// lee el fichero con la configuracion de la pantalla y crea
		// los marcianitos en sus posiciones.
		// El nombre de la pantalla actual puede obtenerse con el
		// metodo `nombre()`
		String nomPant;
		String tipo = null;;
		int x = 0;
		int y = 0;
		try (Scanner in = new Scanner(new FileReader("src/pract13/marcianitos.txt"))){
			in.useLocale(Locale.ENGLISH);
			nomPant = in.next();
			while (!nomPant.equals(nombre()) && in.hasNext()) {
				nomPant = in.next();
			}

			while (nomPant.equals(nombre()) && in.hasNext()) {
				tipo = in.next();
				x = in.nextInt();
				y = in.nextInt();
				nomPant = in.next();
				if (tipo.equals("soldado")) {
					MarcianitoSoldado m = new MarcianitoSoldado();
					incluyeObj(m, x, y);
					numMarcianosVivos++;
				} else if (tipo.equals("jefe")) {
					MarcianitoJefe m = new MarcianitoJefe();
					incluyeObj(m, x, y);
					numMarcianosVivos++;
				} else {
					MarcianitoMaster m = new MarcianitoMaster();
					incluyeObj(m, x, y);
					numMarcianosVivos++;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error, fichero no encontrado");
			System.exit(-1);
		}
		
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

			if (nomSigPantalla == null) {
				// mensaje fin juego
				incluyeObj(new JObjetoTexto("Has evitado la invasion!",
						Color.WHITE,
						new Font("Arcade Normal", Font.PLAIN, FUENTE_TAMANHO_GRANDE)),
						X_TEXTO_MARGEN_IZQ, altoY() / 2);
				incluyeObj(new JObjetoTexto("Presiona esc para salir...",
						Color.WHITE,
						new Font("Arcade Normal", Font.PLAIN, FUENTE_TAMANHO_PEQUENHO)),
						X_TEXTO_ESC, Y_TEXTO_ESC);
			} else {
				// mensaje para pasar a la siguiente pantalla
				incluyeObj(new JObjetoTexto("Presiona esc para pasar a la siguiente pantalla...",
						Color.WHITE,
						new Font("Arcade Normal", Font.PLAIN, FUENTE_TAMANHO_PEQUENHO)),
						X_TEXTO_SIG_PANTALLA, Y_TEXTO_ESC);
			}
		}
	}

	@Override
	public void teclaPresionada(int codigoTecla) {
		switch (codigoTecla) {
		case KeyEvent.VK_ESCAPE:
			if (nomSigPantalla == null || laser == null) {
				// estamos en la ultima pantalla
				Juego.finalizarJuego();
			} else {
				// pasa a la siguiente pantalla
				Juego.cambiaEscena(nomSigPantalla);
				controladosTecladoEliminaTodos();
			}
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
