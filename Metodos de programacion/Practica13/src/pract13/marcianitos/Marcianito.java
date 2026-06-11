package pract13.marcianitos;

import static pract13.JuegoMarcianitos.PATH_ICONOS;
import static pract13.JuegoMarcianitos.PATH_SONIDOS;

import java.awt.Image;

import javax.swing.ImageIcon;

import j2d.utils.Sonido;
import pract13.Pantalla;
import j2d.JObjeto;
import j2d.JObjetoIcono;
import j2d.Juego;
import j2d.utils.Animacion;
import j2d.utils.Diapositiva;

/**
 * Clase que define el comportamiento basico de los marcianitos.
 *    
 * @author Metodos de Programacion (UC)
 * @version abr-2024
 */
public abstract class Marcianito extends JObjetoIcono {
	// imagenes
	private static final Image IMG_EXPLOTA1 =
			new ImageIcon(PATH_ICONOS + "explosion1.png").getImage();
	private static final Image IMG_EXPLOTA2 =
			new ImageIcon(PATH_ICONOS + "explosion2.png").getImage();
	private static final float ESCALA_ICONO = 3.0F;

	private static final int MS_MOVIMIENTO = 500;
	private final int ciclosPorIcono = (int) (MS_MOVIMIENTO /
			1000.0 * Juego.ciclosPorSegundo()); // 0.5 s * 30 c/s
	private final int ciclosPorSentido = ciclosPorIcono * 4;	
	private static final int MS_EXPLOSION = MS_MOVIMIENTO;

	private static final int MAX_CICLOS_ENTRE_BOMBAS = 390;
	private static final int MIN_CICLOS_ENTRE_BOMBAS = 40;
	private int contCiclosEntreBombas = -1;	

	private static final float VEL_X = 2;
	private static final float VEL_Y = 0.2F;

	private int sentido = 0; // 0 = derecha, 1 = izquierda
	private int contCiclos = 0;

	private enum Modo { MOVIMIENTO, EXPLOSION }

	private Modo modo = Modo.MOVIMIENTO;

	// sonidos
	private static Sonido sonidoProyectil =
			new Sonido(PATH_SONIDOS + "cse_select.wav");

	// animaciones
	private final Animacion movimiento;
	private static final Animacion explosion = new Animacion(
			new Diapositiva(IMG_EXPLOTA1, ESCALA_ICONO, MS_EXPLOSION,
					new Sonido(PATH_SONIDOS + "cse_exp.wav")),
			new Diapositiva(IMG_EXPLOTA2, ESCALA_ICONO, MS_EXPLOSION));

	/**
	 * Construye un marcianito.
	 * El marcianito va cambiando su imagen entre las dos imagenes indicadas. 
	 * @param imagen1 primera imagen de la animacion del marcianito.
	 * @param imagen2 segunda imagen de la animacion del marcianito.
	 */
	public Marcianito(Image imagen1, Image imagen2) {
		super(null, imagen1, ESCALA_ICONO);		
		asignaVel(VEL_X, VEL_Y);
		contCiclosEntreBombas = (int) (MAX_CICLOS_ENTRE_BOMBAS * Math.random())
				+ MIN_CICLOS_ENTRE_BOMBAS;

		// crea la animacion del movimiento
		movimiento = new Animacion(true,
				new Diapositiva(imagen1, ESCALA_ICONO, MS_MOVIMIENTO),
				new Diapositiva(imagen2, ESCALA_ICONO, MS_MOVIMIENTO));

		// inicia la animacion de movimiento
		animador().reproduce(movimiento);
	}

	@Override
	public void ciclo() {
		contCiclosEntreBombas--;
		contCiclos++;

		// sentido del movimiento
		if (contCiclos % ciclosPorSentido == 0) {
			sentido = (sentido + 1) % 2;
			asignaVelX(-velX());
		}

		// elimina marciano
		if (modo == Modo.EXPLOSION && !animador().reproduciendo()) {
			escena().eliminaObj(this);
		}

		if (modo == Modo.MOVIMIENTO) {
			// disparo proyectil
			if (contCiclosEntreBombas < 0) {
				sonidoProyectil.suena();
				lanzaProyectiles();
				contCiclosEntreBombas = (int)
						(MAX_CICLOS_ENTRE_BOMBAS * Math.random())
						+ MIN_CICLOS_ENTRE_BOMBAS;
			}

			// llegada a meta
			if (posicion().y > Pantalla.Y_META_MARCIANOS) {
				((Pantalla) Juego.escenaActual()).laserDestruido();
			}
		}
	}

	@Override
	public void colision(JObjeto otroObj, boolean causante) {
		if (!causante) {
			alcanzadoPorRayoLaser();
		}
	}

	/**
	 * El marcianito ha sido alcanzado por un rallo laser disparado por el
	 * cayon.
	 */
	protected void alcanzadoPorRayoLaser() {
		// elimina el marcianito
		colisionador().desactiva();
		modo = Modo.EXPLOSION;
		animador().reproduce(explosion);

		// informa a la escena de que un marcianito ha sido destruido
		((Pantalla) Juego.escenaActual()).marcianoDestruido();	
	}

	/**
	 * Lanza proyectiles.
	 */
	protected abstract void lanzaProyectiles();

}
