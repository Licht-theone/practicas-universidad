package pract08.proyectiles;

import j2d.JObjeto;
import j2d.JObjetoIcono;
import j2d.Juego;
import pract08.CanyonLaser;
import pract08.Pantalla;

import static pract08.JuegoMarcianitos.PATH_ICONOS;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Proyectil basico lanzado por los marcianitos.
 *    
 * @author Metodos de Programacion (UC)
 * @version abr-2024
 */
public class Proyectil extends JObjetoIcono {
	private static final Image IMG_PROYECTIL =
			new ImageIcon(PATH_ICONOS + "proyectil.png").getImage();

	/**
	 * Construye un proyectil con la velocidad indicada y la imagen de
	 * proyectil basico.
	 * @param velX componente X de la velocidad.
	 * @param velY componente Y de la velocidad.
	 */
	public Proyectil(float velX, float velY) {
		this(IMG_PROYECTIL, velX, velY);
	}

	/**
	 * Construye un proyectil con la velocidad e imagen indicada.
	 * @param img imagen del proyectil.
	 * @param velX componente X de la velocidad.
	 * @param velY componente Y de la velocidad.
	 */
	public Proyectil(Image img, float velX, float velY) {
		super(null, img, 1);
		asignaVel(velX, velY);

		// colisionador desactivado para no chocar con otros marcianitos
		colisionador().desactiva();
	}

	@Override
	public void ciclo() {
		if (posicion().y > Pantalla.Y_META_MARCIANOS) {
			// activa el colisionador al llegar a la zona en que puede
			// colisionar con el canhon
			colisionador().activa();
		}
	}

	@Override
	public void colision(JObjeto otroObj, boolean causante) {
		if (otroObj instanceof CanyonLaser) {
			colisionador().desactiva();
			escena().eliminaObj(this);
			((Pantalla) Juego.escenaActual()).laserDestruido();
		}
	}

}
