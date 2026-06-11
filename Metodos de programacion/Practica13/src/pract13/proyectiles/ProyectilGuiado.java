package pract13.proyectiles;

import static pract13.JuegoMarcianitos.PATH_ICONOS;

import java.awt.Image;

import javax.swing.ImageIcon;

import j2d.JObjeto;
import pract13.CanyonLaser;

/**
 * Proyectil guiado que se dirige hacia el canhon laser.
 *    
 * @author Metodos de Programacion (UC)
 * @version abr-2024
 */
public class ProyectilGuiado extends Proyectil {
	private static final Image IMG_PROYECTIL =
			new ImageIcon(PATH_ICONOS + "proyectil2.png").getImage();
	private static final float V_X = 2.0F;
	
	/**
	 * Construye un proyectil con la velocidad indicada.
	 * @param velX componente X de la velocidad.
	 * @param velY componente Y de la velocidad.
	 */
	public ProyectilGuiado(float velX, float velY) {
		super(IMG_PROYECTIL, velX, velY);
	}

	@Override
	public void ciclo() {
		super.ciclo();

		JObjeto canhonLaser = escena().buscaObj(CanyonLaser.NOMBRE);
		if (canhonLaser != null) {
			if (canhonLaser.centro().x > centro().x) {
				asignaVelX(V_X);
			} else {
				asignaVelX(-V_X);
			}
		}
	}

}
