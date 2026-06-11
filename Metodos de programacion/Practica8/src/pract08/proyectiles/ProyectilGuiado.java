package pract08.proyectiles;

import static pract08.JuegoMarcianitos.PATH_ICONOS;

import java.awt.Image;
import javax.swing.ImageIcon;

import pract08.CanyonLaser;

/**
 * Proyectil guiado que se dirige hacia el canhon laser.
 *    
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version abr-2024
 */
public class ProyectilGuiado extends Proyectil{


	private static final Image IMG_PROYECTIL =
			new ImageIcon(PATH_ICONOS + "proyectil2.png").getImage();

	public ProyectilGuiado(float velX, float velY) {
		super(IMG_PROYECTIL, velX, velY);

	}

	// Sobrescribe el metodo ciclo()
	public void ciclo() {
		CanyonLaser l = (CanyonLaser) escena().buscaObj("laser");
		if (l.centro().x < centro().x) {
			asignaVelX(-2);
		}
		else {
			asignaVelX(2);
		}
		super.ciclo();
	}

}
