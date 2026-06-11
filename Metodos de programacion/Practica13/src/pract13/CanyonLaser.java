package pract13;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import j2d.IControladoTeclado;
import j2d.JObjetoIcono;
import j2d.Juego;
import j2d.utils.Animacion;
import j2d.utils.Diapositiva;
import j2d.utils.Sonido;
import static pract13.JuegoMarcianitos.*;

/**
 * Canyon laser controlado por el jugador mediante el teclado.
 *    
 * @author Metodos de Programacion (UC)
 * @version abr-2024
 */
public class CanyonLaser extends JObjetoIcono implements IControladoTeclado {
	public static final String NOMBRE = "laser";

	// imagenes
	private static final Image IMG_EXPLOTA1 =
			new ImageIcon(PATH_ICONOS + "explosion1.png").getImage();
	private static final Image IMG_EXPLOTA2 =
			new ImageIcon(PATH_ICONOS + "explosion1.png").getImage();
	private static final Image IMG_LASER =
			new ImageIcon(PATH_ICONOS + "laser.png").getImage();	
	private static final float ESCALA_ICONO = 3.0F;	

	// animacion explosion
	private static final int MS_EXPLOSION = 700;	
	private final Animacion explosion = new Animacion(
			new Diapositiva(IMG_EXPLOTA1, ESCALA_ICONO, MS_EXPLOSION,
					new Sonido(PATH_SONIDOS +  "bse_boss_big_exp.wav")),
			new Diapositiva(IMG_EXPLOTA2, ESCALA_ICONO, MS_EXPLOSION));

	// sonidos
	private Sonido sonidoLaser =
			new Sonido(JuegoMarcianitos.PATH_SONIDOS + "cse_shot_hit.wav");

	private static final int VEL_X = 4;

	private static final int CICLOS_ENTRE_RAYOS = 30;
	private int contCiclosEntreRayos = -1;

	private enum Modo { MOVIMIENTO, EXPLOSION }

	private Modo modo = Modo.MOVIMIENTO;

	/**
	 * Construye el canyon.
	 */
	public CanyonLaser() {
		super(NOMBRE, IMG_LASER, ESCALA_ICONO);
	}

	@Override
	public void ciclo() {		
		// frena para no salirse de la pantalla
		if (posicion().x <= VEL_X && velX() < 0) {
			asignaVelX(0);
		} else if (posicion().x >= Juego.anchoPixelsX() - VEL_X - anchoX() &&
				velX() > 0) {
			asignaVelX(0);
		}
		
		// decrementa el contador de ciclos entre rayos
		contCiclosEntreRayos--;
		
		// elimina el objeto cuado termina la animacion de explosion
		if (modo == Modo.EXPLOSION && !animador().reproduciendo()) {
			escena().eliminaObj(this);
		}
	}

	@Override
	public void teclaPresionada(int codigoTecla) {
		switch (codigoTecla) {
		case KeyEvent.VK_LEFT:
			asignaVelX(-VEL_X);
			break;	
		case KeyEvent.VK_RIGHT:
			asignaVelX(VEL_X);
			break;
		case KeyEvent.VK_SPACE: // disparo
			if (contCiclosEntreRayos < 0) {
				sonidoLaser.suena();
				Point centro = centro();
				escena().incluyeObj(new RayoLaser(),
						centro.x, centro.y - altoY());
				contCiclosEntreRayos = CICLOS_ENTRE_RAYOS;
			}
			break;
		default:
			// no hace nada
		}
	}

	/**
	 * El laser ha sido destruido.
	 */
	public void destruido() {
		colisionador().desactiva();
		asignaVelX(0);
		modo = Modo.EXPLOSION;
		animador().reproduce(explosion);
		escena().controladoTecladoElimina(this);
	}

}
