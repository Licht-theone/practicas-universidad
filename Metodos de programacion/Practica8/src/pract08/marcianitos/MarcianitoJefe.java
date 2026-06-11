package pract08.marcianitos;

import static pract08.JuegoMarcianitos.PATH_ICONOS;

import java.awt.Image;
import javax.swing.ImageIcon;

import pract08.proyectiles.Proyectil;

/**
 * Marcianito jefe. Dispara dos proyectiles con trayectoria oblicua.
 *    
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version abr-2024
 */
public class MarcianitoJefe extends MarcianitoMultiplesVidas{ 
	// imagenes
	private static final Image IMG_MARCIANO1 =
			new ImageIcon(PATH_ICONOS + "invasor2_1.png").getImage();	
	private static final Image IMG_MARCIANO2 =
			new ImageIcon(PATH_ICONOS + "invasor2_2.png").getImage();

	// constructor
	public MarcianitoJefe(int numVidas) {
		super(IMG_MARCIANO1, IMG_MARCIANO2, numVidas);
	}
	// metodo lanzaProyectiles()
	// Crea dos proyectiles que caen con trayectoria oblicua.

	@Override
	protected void lanzaProyectiles() {
		Proyectil p1 = new Proyectil(5, 5);
		Proyectil p2 = new Proyectil(-5, 5);
		escena().incluyeObj(p1, centro().x, centro().y);
		escena().incluyeObj(p2, centro().x, centro().y);
	}

}
